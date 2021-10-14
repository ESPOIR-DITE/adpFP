package com.mycompany.adpfp.io.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.adpfp.datas.customer.Customer;
import com.mycompany.adpfp.datas.factory.UserCredentialFactory;
import com.mycompany.adpfp.datas.factory.UserFactory;
import com.mycompany.adpfp.datas.factory.customer.CustomerFactory;
import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.io.NewClient;

import java.util.ArrayList;
import java.util.List;

public class CustomerIO {
    public String updateCustomer(NewClient newClient, Customer customer){
        String customerString = CustomerFactory.updateNewCustomer(customer);
        System.out.println(customerString);
        String result = newClient.communicate(customerString);
        if( result.equals("true")){
            return "You have successfully created a new customer";
        }
        return "Error occurred";
    }
    public String deleteCustomer(NewClient newClient, String id){
        String customerString = CustomerFactory.deleteCustomer(id);
        System.out.println(customerString);
        String result = newClient.communicate(customerString);
        if( result.equals("true")){
            return "You have successfully deleted customer";
        }
        return "Error occurred";
    }
    public String createCustomer(NewClient newClient, Customer customer){
        String customerString = CustomerFactory.createNewCustomer(customer);
        System.out.println(customerString);
        String result = newClient.communicate(customerString);
        if( result.equals("true")){
            return "You have successfully created a new customer";
        }
        return "Error occurred";
    }
    public List<Customer> readCustomer(NewClient newClient) throws JsonProcessingException {
        String customerString = CustomerFactory.readAllCustomer();
        String result = newClient.communicate(customerString);
        List<Customer> usersList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Customer[] users = mapper.readValue(result, Customer[].class);
        for(Customer users1: users){
            usersList.add(users1);
        }
        return usersList;
    }
    public List<String> readAllCustomer(NewClient newClient) throws JsonProcessingException {
        String customerString = CustomerFactory.readAllCustomer();
        String result = newClient.communicate(customerString);
        List<String> usersList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Customer[] users = mapper.readValue(result, Customer[].class);
        for(Customer users1: users){
            usersList.add(users1.getEmail()+"#"+users1.getName()+"#"+users1.getSurname());
        }
        return usersList;
    }
}
