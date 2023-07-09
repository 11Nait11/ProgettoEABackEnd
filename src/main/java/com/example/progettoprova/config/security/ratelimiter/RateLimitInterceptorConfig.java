package com.example.progettoprova.config.security.ratelimiter;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//@Component
//@RequiredArgsConstructor
//public class ExternalAPICaller {
//    private final RestTemplate restTemplate;
//
//    public String callApi() {
//        return restTemplate.getForObject("/utente-api/test-lang", String.class);
//    }
//
//}

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;

//@Configuration
//public class ExternalAPICaller implements WebMvcConfigurer {
//
//    private final RateLimiterRegistry rateLimiterRegistry;
//
//    public ExternalAPICaller(RateLimiterRegistry rateLimiterRegistry) {
//        this.rateLimiterRegistry = rateLimiterRegistry;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor((request, response, handler) -> {
//            String rateLimiterName = "globalRateLimiter";
//            io.github.resilience4j.ratelimiter.RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter(rateLimiterName);
//            if (rateLimiter.acquirePermission()) {
//                return true;
//            } else {
//                throw new RequestNotPermitted("Rate limit exceeded");
//            }
//        });
//    }
//}

import org.springframework.web.servlet.ModelAndView;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class RateLimitInterceptorConfig implements WebMvcConfigurer {

    private final RateLimiterRegistry rateLimiterRegistry;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RateLimitInterceptor(rateLimiterRegistry));
    }

    @RequiredArgsConstructor
    private static class RateLimitInterceptor implements HandlerInterceptor {
        private final RateLimiterRegistry rateLimiterRegistry;


        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String rateLimiterName = "globalRateLimiter";
            io.github.resilience4j.ratelimiter.RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter(rateLimiterName);
            if (rateLimiter.acquirePermission()) {
                return true;
            } else {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write("Rate limit exceeded");
                log.info("Rate limit exceeded");
                return false;
            }
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            // Logica post-handle
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            // Logica after-completion
        }
    }
}

