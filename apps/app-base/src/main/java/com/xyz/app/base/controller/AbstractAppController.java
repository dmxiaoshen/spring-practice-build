package com.xyz.app.base.controller;

import com.xyz.common.auth.common.TokenHolder;
import com.xyz.common.auth.exception.AuthException;
import com.xyz.common.base.entity.AbstractController;

import java.util.Map;

public abstract class AbstractAppController extends AbstractController {

    protected String getCurrentUserId(){
        Map<String,Object> claims = TokenHolder.getToken();
        if(claims==null){
            throw new AuthException();
        }
        return String.valueOf(claims.get("userId"));
    }
}
