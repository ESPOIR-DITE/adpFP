package com.mycompany.adpfp.datas.factory;


import com.mycompany.adpfp.datas.user.UserCredentials;
import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.util.ServerToken;
import com.mycompany.adpfp.util.ServerTokenFactory;

import java.util.Date;
import java.util.StringTokenizer;

public class UserCredentialFactory {
    public static String getUserCredentialFromObject(UserCredentials userCredentials){
        return  userCredentials.getId()+"/"+userCredentials.getEmail()+"/"+userCredentials.getPassword()+"/"+userCredentials.getActive()+"/"+userCredentials.getCreator()+"/"+userCredentials.getUserTypeId();
    }
    public static UserCredentials getUserCredentialFromToken(String token){
        try{
            StringTokenizer st = new StringTokenizer(token,"/");
            return UserCredentials.builder()
                    .id(st.nextToken())
                    .email(st.nextToken())
                    .password(st.nextToken())
                    .active(Boolean.parseBoolean(st.nextToken()))
                    .creator(st.nextToken())
                    .userTypeId(st.nextToken()).build();
        }catch (NullPointerException nullPointerException){
            return null;
        }

    }
    public static String readUserCredential(String email){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("read")
                .date(new Date())
                .domain("user-credential")
                .value(email)
                .build());
    }
    public static String createNewUserCredential(UserCredentials users){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("create")
                .date(new Date())
                .domain("user-credential")
                .value(getUserCredentialFromObject(users))
                .build());
    }
    public static String activateUser(String email){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("activate")
                .date(new Date())
                .domain("user-credential")
                .value(email)
                .build());
    }
    public static String deactivateUser(String email){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("deactivate")
                .date(new Date())
                .domain("user-credential")
                .value(email)
                .build());
    }

}
