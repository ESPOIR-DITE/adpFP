/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adpfp.io;

/**
 *
 * @author 212195131
 */
/*
 * ClientApp.java
 *
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

/**

 * @author
 */
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;

public abstract class ClientApp extends JFrame implements ActionListener {
   JFrame frame = new JFrame("Main Interface");
     
     private JLabel inputClient = new JLabel("Client data");
     private JTextField textField = new JTextField(10);
     
     private JPanel panelText = new JPanel();
     
     private JPanel displayPanel = new JPanel();
     private JTextArea textArea = new JTextArea(4,40);
    
    
    private Socket server;
    
    /** Creates a new instance of ClientApp */
    public ClientApp() {
         //super("Main Interface");
        frame.setLayout(new GridLayout(3,1,10,10));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(600,500);
        
        panelText.setLayout(new GridLayout(2,2,10,10));
        displayPanel.setLayout(new FlowLayout());
        
        panelText.add(inputClient);
        panelText.add(textField);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        displayPanel.add(scrollPane);
        
        frame.add(panelText);
        frame.add(displayPanel);
        
        
        //textField.addActionListener((ActionListener) this);
        
        // Attempt to establish connection to server
        try
        {
            // Create socket
            server = new Socket("127.0.0.1", 12345);
        }
        catch (IOException ioe)
        {
            System.out.println("IOException: " + ioe.getMessage());
        }
    }
    
    public void communicate()
    {
        // The connection has been established - now send/receive.
        
        try
        {        
            // Step 1: create channels
            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());
            
            textField.addKeyListener(new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent e) {
                        
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode()== KeyEvent.VK_ENTER){
                            try {
                                out.writeObject(textField.getText());
                                out.flush();
                            } catch (IOException ex) {
                                Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        
                    }
                    
                });
        
            
            // Step 2: communicate
            
            out.writeObject(textField.getText());
            out.flush();
            String response = (String)in.readObject();
            System.out.println("From SERVER>> " + response);
            
            do{
            response = (String)in.readObject();
            
            //System.out.println("From SERVER>> " + response);
            textArea.append(response+"\n");
            }while(!response.equals("TERMINATE"));
            
             if(response.equals("TERMINATE")){
                 frame.dispose();
             }
            
            // Step 3: close down
            out.close();
            in.close();
            server.close();        
        }
        catch (IOException ioe)
        {
            System.out.println("IO Exception: " + ioe.getMessage());
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Class not found: " + cnfe.getMessage());
        }
    }
      
    public static void main(String[] args)
    {
        ClientApp client = new ClientApp() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        client.communicate();
    }    
}