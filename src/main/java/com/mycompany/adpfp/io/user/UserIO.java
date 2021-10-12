package com.mycompany.adpfp.io.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.adpfp.datas.factory.UserCredentialFactory;
import com.mycompany.adpfp.datas.factory.UserFactory;
import com.mycompany.adpfp.datas.user.UserCredentials;
import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.io.NewClient;

import java.util.ArrayList;
import java.util.List;

public class UserIO {

    public String createUser(NewClient newClient, Users users,String password, String userType){
        String userString = UserFactory.createNewUser(users);
        System.out.println(userString);
        String result = newClient.communicate(userString);

        String userCredentialString = UserCredentialFactory.createNewUserCredential(getUserCredentialObject(password,userType,users.getEmail()));
        String result1 = newClient.communicate(userCredentialString);
        System.out.println(result);
        System.out.println(result1);
        if( result.equals("true")&&result1.equals("true")){
            return "You have successfully created a new User";
        }
        return "Error occurred";
    }
    UserCredentials getUserCredentialObject(String password,String userType,String email){
        return UserCredentials.builder()
                .password(password)
                .userTypeId(userType)
                .email(email)
                .creator("admin")
                .active(true)
                .build();
    }

    public List<Users> readUsers(NewClient newClient) throws JsonProcessingException {
        String userString = UserFactory.readAllUser();
        String result = newClient.communicate(userString);
        List<Users> usersList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Users[] users = mapper.readValue(result, Users[].class);
        for(Users users1: users){
            usersList.add(users1);
            //System.out.println(users1.getEmail());
        }
        return usersList;
    }
}
