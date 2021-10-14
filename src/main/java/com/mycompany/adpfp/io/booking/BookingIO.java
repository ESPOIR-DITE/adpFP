package com.mycompany.adpfp.io.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.adpfp.datas.Booking;
import com.mycompany.adpfp.datas.customer.Customer;
import com.mycompany.adpfp.datas.factory.booking.BookingFactory;
import com.mycompany.adpfp.datas.factory.customer.CustomerFactory;
import com.mycompany.adpfp.io.NewClient;
import com.mycompany.adpfp.io.venue.VenueIO;

import java.util.ArrayList;
import java.util.List;

public class BookingIO {
    private VenueIO venueIO = new VenueIO();
    public String updateCustomer(NewClient newClient, Booking booking){
        String customerString = BookingFactory.updateNewBooking(booking);
        System.out.println(customerString);
        String result = newClient.communicate(customerString);
        if( result.equals("true")){
            return "You have successfully created a new Booking";
        }
        return "Error occurred";
    }
    public String deleteBooking(NewClient newClient, String id){
        String customerString = BookingFactory.deleteBooking(id);
        System.out.println(customerString);
        String result = newClient.communicate(customerString);
        if( result.equals("true")){
            return "You have successfully created a new booking";
        }
        return "Error occurred";
    }
    public String createBooking(NewClient newClient, Booking customer){
        String customerString = BookingFactory.createNewBooking(customer);
        System.out.println(customerString);
        String result = newClient.communicate(customerString);
        if( result.equals("true")){
            venueIO.updateUnavailable(newClient,customer.getVenueId());
            return "You have successfully created a new Booking";
        }
        return "Error occurred";
    }
    public List<Booking> readAllBooking(NewClient newClient) throws JsonProcessingException {
        String customerString = BookingFactory.readAllBooking();
        String result = newClient.communicate(customerString);
        List<Booking> usersList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Booking[] users = mapper.readValue(result, Booking[].class);
        for(Booking users1: users){
            usersList.add(users1);
        }
        return usersList;
    }
}
