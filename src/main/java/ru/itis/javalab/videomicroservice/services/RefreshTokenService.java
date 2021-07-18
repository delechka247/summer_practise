package ru.itis.javalab.videomicroservice.services;

import ru.itis.javalab.videomicroservice.dto.TokensDto;
import ru.itis.javalab.videomicroservice.models.User;

public interface RefreshTokenService {
    boolean accessCheck(String accessTokenValue);
    boolean refreshCheck(String refreshTokenValue);
    TokensDto getNewTokens(Long id, String refreshTokenValue);
    TokensDto generateTokens(User user);
}
