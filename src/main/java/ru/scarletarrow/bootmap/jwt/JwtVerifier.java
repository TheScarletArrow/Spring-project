package ru.scarletarrow.bootmap.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtVerifier extends OncePerRequestFilter {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public JwtVerifier(SecretKey secretKey, JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = httpServletRequest.getHeader(jwtConfig.getAuthHeader());

        if (Strings.isEmpty(authHeader) ||Strings.isBlank(authHeader) || !authHeader.startsWith(jwtConfig.getTokenPrefix())){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String token = authHeader.replace(jwtConfig.getTokenPrefix(), "");

        try{
          //  String key = "secureCodeFromScarletToHimselfsecureCodeFromScarletToHimselfsecureCodeFromScarletToHimselfsecureCodeFromScarletToHimself";
           var claimsJwt = Jwts.parser().setSigningKey(secretKey)
                   .parseClaimsJws(token);
            Claims body = claimsJwt.getBody();
            String subject = body.getSubject();
            var authorities = (List<Map<String, String>>)  body.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities =
                    authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(subject,null, simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e){
            throw new IllegalStateException("Token can't be truest "+ token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
