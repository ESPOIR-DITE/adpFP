package com.mycompany.adpfp.datas.factory;


import com.mycompany.adpfp.datas.customer.Customer;
import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.util.ServerToken;
import com.mycompany.adpfp.util.ServerTokenFactory;

import java.util.Date;
import java.util.StringTokenizer;

public class UserFactory {
    public static String getUserFromObject(Users users){
        return users.getEmail()+"/"+ users.getName()+"/"+ users.getSurname()+"/"+ new Date().toString();
    }
    public static String getUserFromObjectUpdate(Users users){
        return users.getEmail()+"/"+ users.getName()+"/"+ users.getSurname()+"/"+users.getDate();
    }
    public static String updateUser(Users users){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("update")
                .date(new Date())
                .domain("user")
                .value(getUserFromObjectUpdate(users))
                .build());
    }
    public static String deleteUser(String email){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("delete")
                .date(new Date())
                .domain("user")
                .value(email)
                .build());
    }
    public static Users getUserFromValue(String value){
        StringTokenizer st = new StringTokenizer(value,"/");
        return Users.builder()
                .email(st.nextToken())
                .name(st.nextToken())
                .surname(st.nextToken()) // for now.
                .date(st.nextToken()) // for now.
                .build();
    }
    public static String createNewUser(Users users){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("create")
                .date(new Date())
                .domain("user")
                .value(getUserFromObject(users))
                .build());
    }
    public static String readAllUser(){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("reads")
                .date(new Date())
                .domain("user")
                .build());
    }
}
