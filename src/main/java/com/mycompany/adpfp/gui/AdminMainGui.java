/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adpfp.gui;

import com.mycompany.adpfp.gui.user.user.UserGui;
import com.mycompany.adpfp.gui.venue.VenueGui;
import com.mycompany.adpfp.io.NewClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import static com.mycompany.adpfp.util.Images.getAdminImage;

/**
 *
 * @author Marilyn
 */
public class AdminMainGui extends JFrame implements ActionListener {
    private JPanel menu = new JPanel();
    private JPanel centre = new JPanel();

    private JLabel title = new JLabel("ADMIN MENU",JLabel.CENTER);
    private JButton venue = new JButton("Venue");
    private JButton user = new JButton("User");
    private JButton logout = new JButton("Logout");
    private JTextArea menuSpace = new JTextArea(20,7);

    //
    VenueGui venueGui = null;
    //panels
    private JPanel venuePanel = null;
    private JPanel userPanel;
    UserGui userGui;
    private JLabel pic;
    private Color btnBrown = new Color(81,43,40);
    private Color btnBrownSelected = new Color(166,123,119);

    private JButton newUser = new JButton("Create");

    Font f = new Font("Verdana",Font.BOLD,15);
    Border blackline = BorderFactory.createLineBorder(Color.black);
    private NewClient newClient;
    public AdminMainGui(String userName, String password, NewClient newClient) throws HeadlessException, IOException {
        super("ADMIN POSTAL");
        this.venueGui = new VenueGui(newClient);
        this.venuePanel = new VenueGui(newClient).getCreateVenuePanel();
        userPanel = new UserGui(newClient).getUserGui();
        userGui = new UserGui(newClient);
        this.newClient = newClient;
        setLayout(new BorderLayout(5,5));
        setSize(700,480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setVisible(true);

        userGui.setClient(this.newClient);

        title.setSize(4,4);
        title.setFont(f);
        this.menu = getMenu();
        this.menu.setBorder(blackline);
        BufferedImage myPicture = ImageIO.read(getAdminImage());
        pic = new JLabel(new ImageIcon(myPicture));

        buttonsColor();
        centerPanelDeal();

        add(centre,BorderLayout.CENTER);
        add(new JPanel(),BorderLayout.NORTH);
        add(new JPanel(),BorderLayout.EAST);
        add(new JPanel(),BorderLayout.SOUTH);
        add(menu,BorderLayout.WEST);

    }
//    add panels and components that are invisible.
    private void centerPanelDeal(){
        venuePanel.setVisible(false);
        userPanel.setVisible(false);
        centre.add(pic);
        centre.add(userPanel);
        centre.add(venuePanel);
    }
//    Decorate buttons
    private void buttonsColor(){
        newUser.setBackground(btnBrown);
        venue.setBackground(btnBrown);
        logout.setBackground(btnBrown);
        venue.setForeground(Color.WHITE);
        user.setForeground(Color.WHITE);
        logout.setForeground(Color.WHITE);
        user.setBackground(btnBrown);
        title.setForeground(btnBrown);
        venue.addActionListener(this);
        user.addActionListener(this);
        logout.addActionListener(this);
    }
    //returns a menu panel
    public JPanel getMenu(){
        menu.setLayout(new GridLayout(10,1));
        menu.add(title);
        menu.add(venue);
        menu.add(user);
        menu.add(logout);
        return menu;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource()==user){
        user.setBackground(btnBrownSelected);
        venue.setBackground(btnBrown);
        visibilitize("userPanel");
    }
    if(e.getSource()==venue){
        //add(new Venue().getCreateVenuePanel(),BorderLayout.CENTER);
        System.out.println("venue clicked");
        visibilitize("venue");
        venue.setBackground(btnBrownSelected);
        user.setBackground(btnBrown);

        }
    if(e.getSource()==logout){
        this.dispose();
        //Todo will have to remove the comment bellow.
        LoginPage loginPage = new LoginPage(newClient);
    }
    }
    void visibilitize(String panelName){
        switch (panelName){
            case "userPanel":
                userPanel.setVisible(true);
                pic.setVisible(false);
                venuePanel.setVisible(false);
                break;
            case "venue":
                venuePanel.setVisible( true);
                userPanel.setVisible(false);
                pic.setVisible(false);
                break;
        }
    }
}

