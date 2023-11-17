package com.cleverpine.template.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cleverpine.template.auth.authenticator.JWTAuthenticator;
import com.cleverpine.template.auth.roles.Resources;
import com.cleverpine.template.auth.roles.Roles;
import com.cleverpine.viravaspringhelper.config.AuthTokenConfig;
import com.cleverpine.viravaspringhelper.config.RoleConfig;
import com.cleverpine.viravaspringhelper.core.TokenAuthenticator;
import com.cleverpine.viravaspringhelper.filter.ViravaFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Setter
@Configuration
@ConfigurationProperties(prefix = "auth.token")
public class ViravaHelperConfig {

    private String usernamePath;
    private String rolesPath;
    private String secret;
    private String issuer;

    @Bean
    public RoleConfig<Roles, Resources> roleConfig() {
        return new RoleConfig<>(Roles.values(), Resources.values());
    }

    @Primary
    @Bean(name = "decodingAuthTokenConfig")
    public AuthTokenConfig decodingAuthTokenConfig() {
        return new AuthTokenConfig.Builder().withUsernamePath(usernamePath).withRolesPath(rolesPath).build();
    }

    @Bean(name = "verifyingAuthTokenConfig")
    public AuthTokenConfig verifyingAuthTokenConfig() {
        return new AuthTokenConfig.Builder().withUsernamePath(usernamePath).withRolesPath(rolesPath).withSecret(secret).withIssuer(issuer).build();
    }

    @Bean
    public ViravaFilter viravaFilter(RoleConfig<Roles, Resources> roleConfig, ObjectMapper objectMapper, AuthTokenConfig authTokenConfig, TokenAuthenticator<DecodedJWT> tokenAuthenticator) {
        return new ViravaFilter(roleConfig, objectMapper, authTokenConfig, tokenAuthenticator);
    }

    @Bean
    public TokenAuthenticator<DecodedJWT> tokenAuthenticator() {
        return new JWTAuthenticator();
    }

}