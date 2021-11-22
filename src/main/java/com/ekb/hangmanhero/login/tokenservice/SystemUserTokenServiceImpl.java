package com.ekb.hangmanhero.login.tokenservice;

import static com.ekb.hangmanhero.login.security.UserTokenProvider.getNowPlusMinutes;
import com.ekb.hangmanhero.login.controller.AccessTokenResponse;
import com.ekb.hangmanhero.login.controller.LoginRequest;
import com.ekb.hangmanhero.login.model.SystemUser;
import com.ekb.hangmanhero.login.model.SystemUserToken;
import com.ekb.hangmanhero.login.repository.SystemUserRepository;
import com.ekb.hangmanhero.login.security.BasicTokenProvider;
import com.ekb.hangmanhero.login.security.UserTokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static java.util.Objects.isNull;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class SystemUserTokenServiceImpl implements SystemUserTokenService {

    private SystemUserRepository systemUserRepository;
    private PasswordEncoder passwordEncoder;
    private SecurityProperties securityProperties;

    @Override
    public AccessTokenResponse grantActualToken(LoginRequest loginRequest) {
        log.debug("starting grantActualToken for {user: {}}", loginRequest.getUsername());

        SystemUser systemUser = systemUserRepository.findByLoginAndDeletedIsFalse(loginRequest.getUsername())
                .orElseThrow(this::getBadCredentialsException);

        if (!passwordEncoder.matches(loginRequest.getPassword(), systemUser.getPassword())) {
            throw getBadCredentialsException();
        }

        return createUserAccessToken(systemUser);
    }

    private AccessTokenResponse createUserAccessToken(SystemUser systemUser) {
        AccessTokenResponse accessTokenResponse = UserTokenProvider.createToken(systemUser.getLogin());

        createOrUpdateToken(systemUser, accessTokenResponse);

        log.info("grantActualToken finished with success for {user: {}}", systemUser.getLogin());
        return accessTokenResponse;
    }

    private void createOrUpdateToken(SystemUser systemUser, AccessTokenResponse accessTokenResponse) {
        Timestamp validTo = getNowPlusMinutes(securityProperties.getTokenValidityInMinutes());

        SystemUserToken systemUserToken = systemUser.getSystemUserToken();

        if (isNull(systemUserToken)) {
            systemUserToken = SystemUserToken.builder()
                    .token(accessTokenResponse.getToken())
                    .validTo(validTo)
                    .systemUser(systemUser)
                    .build();

            systemUserTokenRepository.save(systemUserToken);
        } else {
            systemUserToken.setToken(accessTokenResponse.getToken());
            systemUserToken.setValidTo(validTo);
        }
    }
    private BadCredentialsException getBadCredentialsException() {
        return new BadCredentialsException("grantActualToken failed, not valid username or password");
    }
}
