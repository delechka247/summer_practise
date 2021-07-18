package ru.itis.javalab.videomicroservice.services;

import ru.itis.javalab.videomicroservice.dto.EmailPasswordDto;
import ru.itis.javalab.videomicroservice.dto.TokensDto;

public interface SignInService {
    TokensDto signIn(EmailPasswordDto emailPassword);
}
