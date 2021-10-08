package com.mycompany.adpfp.io;

import com.mycompany.adpfp.util.ServerToken;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewClient {
    private Socket server;

    public NewClient() {
    try{
        // Create socket
        server = new Socket("127.0.0.1", 12345);
    }
    catch (IOException ioe)
    {
        System.out.println("IOException: " + ioe.getMessage());
    }
    }
    public void communicate(ServerToken serverToken) {
        try {
            // Step 1: create channels
            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());
            try {
                out.writeObject(serverToken);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            ServerToken response = (ServerToken)in.readObject();
            System.out.println("From SERVER>> " + response);
            do{
                 response = (ServerToken)in.readObject();
                //Processing

            }while(!response.equals("TERMINATE"));

            // Step 3: close down
            out.close();
            in.close();
            server.close();
        }catch (IOException ioe)
        {
            System.out.println("IO Exception: " + ioe.getMessage());
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Class not found: " + cnfe.getMessage());
        }
    }
}
