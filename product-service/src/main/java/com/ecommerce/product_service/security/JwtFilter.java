package com.ecommerce.product_service.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final String SECRET = "ThisIsA256BitSecretKeyForJwtMustBeSameEverywhere123";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("🔥 Product Service Hit");
        System.out.println("➡ URI: " + request.getRequestURI());
        System.out.println("➡ Auth Header: " + request.getHeader("Authorization"));

        String path = request.getRequestURI();
        System.out.println("➡ Product Service Hit: " + request.getRequestURI());
        System.out.println("➡ Header: " + request.getHeader("Authorization"));

        if (path.startsWith("/products")
                && !path.startsWith("/products/internal"))  {

            String authHeader = request.getHeader("Authorization");
            System.out.println("➡ Product Service Header: " + authHeader);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(401);
                response.getWriter().write("Missing token");
                return;
            }

            String token = authHeader.substring(7);

            try {
                Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token);
            } catch (Exception e) {
                response.setStatus(401);
                response.getWriter().write("Invalid token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}