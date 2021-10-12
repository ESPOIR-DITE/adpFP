package com.mycompany.adpfp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.adpfp.datas.user.UserCredentials;
import com.mycompany.adpfp.datas.user.Users;

import java.io.File;
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
    public static String logIn(String email,String password){
        return  getJson(ServerToken.builder()
                .request("log-in")
                .date(new Date())
                .domain("user-credential")
                .value(email+"/"+password)
                .build());
    }

    public static String getJson(ServerToken serverToken){
        ObjectMapper objectMapper = new ObjectMapper();
        String serverTokenObject = null;
        try {
            serverTokenObject = objectMapper.writeValueAsString(serverToken);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return serverTokenObject;
    }
    public static ServerToken getServerToken(String value){
        ServerToken parties1 = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            parties1 = mapper.readValue(value,ServerToken.class);
            System.out.println(parties1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return parties1;
    }
}
