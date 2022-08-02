package com.portfolio.backend.security.jwt;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.portfolio.backend.security.model.MainLoginUser;

import java.util.Date;

@Component
public class JwtProvider {

    private static final String SIGNATURE_ERROR_MESSAGE = "Firma no autorizada";
    private static final String ILEGAL_TOKEN_ERROR_MESSAGE = "Error de Token";
    private static final String EXPIRED_ERROR_MESSAGE = "Token expirado";
    private static final String UNSUPPORTED_ERROR_MESSAGE = "Token no soportado";
    private static final String MALFORMED_ERROR_MESSAGE = "El token no esta correctamente formado";

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private int EXPIRATION;

    public String generateToken(Authentication authentication){
        MainLoginUser usuarioPrincipal = (MainLoginUser) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRATION * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error(MALFORMED_ERROR_MESSAGE);
        }catch (UnsupportedJwtException e){
            logger.error(UNSUPPORTED_ERROR_MESSAGE);
        }catch (ExpiredJwtException e){
            logger.error(EXPIRED_ERROR_MESSAGE);
        }catch (IllegalArgumentException e){
            logger.error(ILEGAL_TOKEN_ERROR_MESSAGE);
        }catch (SignatureException e){
            logger.error(SIGNATURE_ERROR_MESSAGE);
        }
        return false;
    }
}
