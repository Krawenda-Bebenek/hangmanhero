package com.ekb.hangmanhero.login.security;

import static java.lang.String.format;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.crypto.codec.Hex;

public class BasicTokenProvider {

    private static final String SEPARATOR = "_";
    private static final String ALGORITHM = "SHA-256";

    static String computeToken(String screenName, Long creationTime, String secretKey) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            String tokenInput = createSeparatedString(screenName, creationTime.toString(), secretKey);
            return new String(Hex.encode(messageDigest.digest(tokenInput.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(format("No {algorithm: %s} available", ALGORITHM));
        }
    }

    private static String createSeparatedString(String... values) {
        return Stream.of(values).collect(Collectors.joining(SEPARATOR));
    }
}
