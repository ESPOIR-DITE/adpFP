package com.mycompany.adpfp.io.venue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.adpfp.datas.factory.UserFactory;
import com.mycompany.adpfp.datas.factory.venue.VenueFactory;
import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.datas.venue.Venue;
import com.mycompany.adpfp.io.NewClient;

import java.util.ArrayList;
import java.util.List;

public class VenueIO {
    public boolean createVenue(NewClient newClient, Venue venue){
        String venueString = VenueFactory.createNewVenue(venue);
        String result = newClient.communicate(venueString);
        System.out.println(result);
    return true;
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
}
