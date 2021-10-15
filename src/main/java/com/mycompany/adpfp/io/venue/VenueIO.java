package com.mycompany.adpfp.io.venue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.adpfp.datas.Booking;
import com.mycompany.adpfp.datas.factory.UserFactory;
import com.mycompany.adpfp.datas.factory.booking.BookingFactory;
import com.mycompany.adpfp.datas.factory.venue.VenueFactory;
import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.datas.venue.Venue;
import com.mycompany.adpfp.io.NewClient;

import java.util.ArrayList;
import java.util.List;

public class VenueIO {
    public boolean updateVenue(NewClient newClient, Venue venue){
        String venueString = VenueFactory.createNewVenue(venue);
        String result = newClient.communicate(venueString);
        System.out.println(result);
        return true;
    }
    public boolean createVenue(NewClient newClient, Venue venue){
        String venueString = VenueFactory.createNewVenue(venue);
        String result = newClient.communicate(venueString);
        System.out.println(result);
    return true;
    }
    public List<String> readAvailableVenues(NewClient newClient)  {
        String venuesString = VenueFactory.readAllAvailableVenues();
        String result = newClient.communicate(venuesString);
        //System.out.println("Result: "+result);
        List<String> venueList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Venue[] users = new Venue[0];
        try {
            users = mapper.readValue(result, Venue[].class);
            for(Venue users1: users){
                venueList.add(users1.getId()+"#"+users1.getName());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(venuesString.toString());
        return venueList;
    }
    public List<Venue> readVenues(NewClient newClient)  {
        String venuesString = VenueFactory.readAllVenues();
        String result = newClient.communicate(venuesString);
        List<Venue> venueList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Venue[] users = new Venue[0];
        try {
            users = mapper.readValue(result, Venue[].class);
            for(Venue users1: users){
                venueList.add(users1);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return venueList;
    }
    public String update(NewClient newClient, Venue venue){
        String venueString = VenueFactory.updateVenue(venue);
        System.out.println(venueString);
        String result = newClient.communicate(venueString);
        try {
            if( result.equals("true")){
                return "You have successfully updates";
            }
        }catch (NullPointerException nullPointerException){
            return "Error occurred";
        }
        return "Error occurred";
    }
    public String updateUnavailable(NewClient newClient, String venueId){
        String venueString = VenueFactory.updateSetUnavailableVenue(venueId);
        System.out.println(venueString);
        String result = newClient.communicate(venueString);
        try {
            if( result.equals("true")){
                return "You have successfully updates to unavailable";
            }
        }catch (NullPointerException nullPointerException){
            return "Error occurred";
        }
        return "Error occurred";
    }
    public String updateAvailable(NewClient newClient, String venueId){
        String venueString = VenueFactory.updateSetAvailableVenue(venueId);
        System.out.println(venueString);
        String result = newClient.communicate(venueString);
        try {
            if( result.equals("true")){
                return "You have successfully updated venue";
            }
        }catch (NullPointerException nullPointerException){
            return "Error occurred";
        }
        return "Error occurred";
    }
    public String delete(NewClient newClient, String id){
        String customerString = VenueFactory.deleteVenue(id);
        System.out.println(customerString);
        String result = newClient.communicate(customerString);
        try {
            if( result.equals("true")){
                return "You have successfully delete";
            }
        }catch (NullPointerException nullPointerException){
            return "Error occurred";
        }
        return "Error occurred";
    }
}
