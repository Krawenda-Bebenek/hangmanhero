package com.ekb.hangmanhero;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/auth")
public class AuthorizationController {

    private static final String AUTH_HEADER = "X-Auth-Token";

    private SystemUserTokenService systemUserTokenService;


    @PostMapping(value = "/login", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public AccessTokenResponse login(@NotNull @Valid @RequestBody LoginRequest loginRequest) {
        log.info("received login request from user with {login: {}}", loginRequest.getUsername());

        return systemUserTokenService.grantActualToken(loginRequest);
    }
