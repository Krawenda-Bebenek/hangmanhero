package com.ekb.hangmanhero.login.security;

import com.ekb.hangmanhero.login.controller.AccessTokenResponse;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

public class UserTokenProvider extends BasicTokenProvider {

    private static final String SECRET_KEY = "kT8Nh721IInb4pcy91X8";

    public static AccessTokenResponse createToken(String username) {
        Long creationTime = Instant.now().toEpochMilli();
        return new AccessTokenResponse(computeToken(username, creationTime, SECRET_KEY));
    }

    public static Timestamp getNowPlusMinutes(Long numberOfHours) {
        return Timestamp.from(Instant.now().plus(Duration.ofMinutes(numberOfHours)));
    }
}
