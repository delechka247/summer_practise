package ru.itis.javalab.videomicroservice.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.videomicroservice.security.jwt.utils.JwtDecoder;
import ru.itis.javalab.videomicroservice.services.RefreshTokenService;

@RestController
public class NewTokensController {
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtDecoder jwtDecoder;

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("REFRESH-TOKEN") String refreshToken) {
        if (refreshToken != null) {
            DecodedJWT decodedJWT = jwtDecoder.getDecodedJwt(refreshToken);
            String id = decodedJWT.getSubject();
            return ResponseEntity.ok(refreshTokenService.getNewTokens(Long.parseLong(id), refreshToken));
        } else {
            return ResponseEntity.ok("Bad refresh token");
        }
    }

}
