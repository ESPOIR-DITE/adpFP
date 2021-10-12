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
        StringTokenizer st = new StringTokenizer(token,"/");
        return UserCredentials.builder()
                .id(st.nextToken())
                .email(st.nextToken())
                .password(st.nextToken())
                .active(Boolean.parseBoolean(st.nextToken()))
                .creator(st.nextToken())
                .userTypeId(st.nextToken()).build();
    }
    public static String createNewUserCredential(UserCredentials users){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("create")
                .date(new Date())
                .domain("user-credential")
                .value(getUserCredentialFromObject(users))
                .build());
    }

}
