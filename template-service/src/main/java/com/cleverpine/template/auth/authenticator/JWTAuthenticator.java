package com.cleverpine.template.auth.authenticator;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cleverpine.viravaspringhelper.core.TokenAuthenticator;

public class JWTAuthenticator implements TokenAuthenticator<DecodedJWT> {

    @Override
    public DecodedJWT process(String s) {
        // TODO
        return null;
    }
}
