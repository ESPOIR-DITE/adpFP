package com.mycompany.adpfp.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void sendMessage() throws IOException {
        Client client = new Client();
        client.startConnection();
        String response = client.sendMessage("hello server");
        System.out.println(response);
        assertEquals("hello client", response);
    }
}