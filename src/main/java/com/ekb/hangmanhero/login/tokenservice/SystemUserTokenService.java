package com.ekb.hangmanhero.login.tokenservice;

import com.ekb.hangmanhero.login.controller.AccessTokenResponse;
import com.ekb.hangmanhero.login.controller.LoginRequest;

public interface SystemUserTokenService {
    AccessTokenResponse grantActualToken(LoginRequest loginRequest);

}
