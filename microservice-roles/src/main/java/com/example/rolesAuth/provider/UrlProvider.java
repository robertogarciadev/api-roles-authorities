package com.example.rolesAuth.provider;

import java.util.List;

public class UrlProvider {

    public static final String AUTHENTICATION_CONTROLLER_SING_UP = "/api/authentication/sing-up";
    public static final String AUTHENTICATION_CONTROLLER_SING_IN = "/api/authentication/sing-in";
    public static final String PERMISSION_CONTROLLER_ALL_PERMISSION = "/api/permission/all-permission";
    public static final String ROLE_CONTROLLER_ALL_ROLES = "/api/role/all-roles";

    public static List<String> urisPermitsAll(){
        return  List.of(
                AUTHENTICATION_CONTROLLER_SING_IN,
                AUTHENTICATION_CONTROLLER_SING_UP,
                PERMISSION_CONTROLLER_ALL_PERMISSION,
                ROLE_CONTROLLER_ALL_ROLES
        );
    }
}
