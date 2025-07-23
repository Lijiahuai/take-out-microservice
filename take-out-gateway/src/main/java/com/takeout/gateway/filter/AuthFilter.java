package com.takeout.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeout.common.dto.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * 认证过滤器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {
    
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    
    // 无需认证的路径
    private static final List<String> ALLOW_PATHS = Arrays.asList(
            "/auth/login",
            "/auth/register",
            "/admin/login"
    );
    
    // JWT密钥
    private static final String JWT_SECRET_KEY = "takeoutSecretKey12345678901234567890123456789012";
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        
        // 判断路径是否需要认证
        for (String allowPath : ALLOW_PATHS) {
            if (path.contains(allowPath)) {
                return chain.filter(exchange);
            }
        }
        
        // 获取token
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) {
            return unauthorized(exchange, "未认证，请先登录");
        }
        
        token = token.substring(7);
        
        try {
            // 验证token
            SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(JWT_SECRET_KEY), "HmacSHA256");
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            
            // 验证token是否在Redis中存在
            String userId = claims.getSubject();
            String redisKey = "user:token:" + userId;
            String savedToken = (String) redisTemplate.opsForValue().get(redisKey);
            
            if (savedToken == null || !savedToken.equals(token)) {
                return unauthorized(exchange, "认证已失效，请重新登录");
            }
            
            // 将用户ID传递给后续服务
            ServerHttpRequest newRequest = request.mutate()
                    .header("X-User-Id", userId)
                    .build();
            
            return chain.filter(exchange.mutate().request(newRequest).build());
            
        } catch (ExpiredJwtException e) {
            return unauthorized(exchange, "认证已过期，请重新登录");
        } catch (Exception e) {
            log.error("Token验证异常", e);
            return unauthorized(exchange, "认证失败，请重新登录");
        }
    }
    
    @Override
    public int getOrder() {
        return 0;
    }
    
    /**
     * 返回未认证响应
     */
    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        
        Result<Object> result = Result.error(401, message);
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(result);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            log.error("响应异常", e);
            return response.writeWith(Mono.just(
                    response.bufferFactory().wrap(
                            "{\"code\":401,\"message\":\"未认证\",\"data\":null}".getBytes(StandardCharsets.UTF_8)
                    )
            ));
        }
    }
} 