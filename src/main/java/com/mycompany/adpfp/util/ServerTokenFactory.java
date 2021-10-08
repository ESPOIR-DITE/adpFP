package com.mycompany.adpfp.util;

import com.mycompany.adpfp.datas.user.UserCridentials;
import com.mycompany.adpfp.datas.user.Users;

import java.util.Date;

public class ServerTokenFactory {
    public ServerToken makeRequest(Users users, String requestType, String value){
        return ServerToken.builder()
                .date(new Date())
                .requestType(requestType)
                .users(users)
                .value(value)
                .build();
    }
    public static ServerToken logIn(String email,String password){
        return ServerToken.builder()
                .request("log-in")
                .domain("user")
                .value(UserCridentials.builder().email(email).password(password).build())
                .build();
    }
}
