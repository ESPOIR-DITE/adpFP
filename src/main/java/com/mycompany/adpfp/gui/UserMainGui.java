/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adpfp.gui;

import com.mycompany.adpfp.gui.user.customer.CustomerGui;
import com.mycompany.adpfp.gui.user.booking.UserVenueGui;
import com.mycompany.adpfp.io.NewClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.mycompany.adpfp.util.Images.getUserImage;

/**
 *
 * @author Marilyn
 */
public class UserMainGui extends JFrame implements ActionListener {
    private JPanel menu = new JPanel();
    private JPanel centre = new JPanel();

    private JLabel title = new JLabel("USER MENU",JLabel.CENTER);
    private JButton bookingBtn = new JButton("Booking");
    private JButton customerBtn = new JButton("Customer");
    private JButton logout = new JButton("Logout");
    private JTextArea menuSpace = new JTextArea(20,7);
    //panels
    private JPanel userVenuePanel;
    private JPanel customerJPanel;
    private JLabel pic;
    private Color btnBrown = new Color(81,43,40);
    private Color btnBrownSelected = new Color(166,123,119);
    private JButton newUser = new JButton("Create");
    Border blackline = BorderFactory.createLineBorder(Color.black);
    Font f = new Font("Verdana",Font.BOLD,15);
    private NewClient newClient;
    public UserMainGui(NewClient newClient,String userName, String password) throws HeadlessException, IOException {
        super("USER POSTAL");
        this.newClient = newClient;
        customerJPanel = new CustomerGui(this.newClient).getCustomerGui();
        userVenuePanel = new UserVenueGui(this.newClient,userName).getUserVenuePanel(this.newClient);
        setLayout(new BorderLayout(5,5));
        setSize(700,480);
        bookingBtn.setBackground(btnBrown);
        bookingBtn.setBackground(Color.WHITE);
        customerBtn.setBackground(btnBrown);
        customerBtn.setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setVisible(true);

        title.setFont(f);
        title.setSize(4,4);
        this.menu = getMenu();
        BufferedImage myPicture = ImageIO.read(getUserImage());
        pic = new JLabel(new ImageIcon(myPicture));

        buttonsColor();
        centerPanelDeal();

        menu.setBorder(blackline);
        add(centre,BorderLayout.CENTER);
        add(new JPanel(),BorderLayout.NORTH);
        add(new JPanel(),BorderLayout.EAST);
        add(new JPanel(),BorderLayout.SOUTH);
        add(menu,BorderLayout.WEST);

    }
//    add panels and components that are invisible.
    private void centerPanelDeal(){
        userVenuePanel.setVisible(false);
        customerJPanel.setVisible(false);
        centre.add(pic);
        centre.add(customerJPanel);
        centre.add(userVenuePanel);
    }
//    Decorate buttons
    private void buttonsColor(){
        newUser.setBackground(btnBrown);
        bookingBtn.setBackground(btnBrown);
        logout.setBackground(btnBrown);
        bookingBtn.setForeground(Color.WHITE);
        customerBtn.setForeground(Color.WHITE);
        logout.setForeground(Color.WHITE);
        customerBtn.setBackground(btnBrown);
        title.setForeground(btnBrown);
        bookingBtn.addActionListener(this);
        customerBtn.addActionListener(this);
        logout.addActionListener(this);
    }
    //returns a menu panel
    public JPanel getMenu(){
        menu.setLayout(new GridLayout(10,1));
        menu.add(title);
        menu.add(customerBtn);
        menu.add(bookingBtn);
        menu.add(logout);
        return menu;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource()== customerBtn){
        customerBtn.setBackground(btnBrownSelected);
        bookingBtn.setBackground(btnBrown);
        visibilitize("customerPanel");
    }
    if(e.getSource()== bookingBtn){
        //add(new Venue().getCreateVenuePanel(),BorderLayout.CENTER);
        System.out.println("venue clicked");
        visibilitize("venue");
        bookingBtn.setBackground(btnBrownSelected);
        customerBtn.setBackground(btnBrown);
        }
    if(e.getSource()==logout){
        this.dispose();
        //Todo will have to remove the comment bellow.
        //LoginPage loginPage = new LoginPage();
    }
    }
    void visibilitize(String panelName){
        switch (panelName){
            case "customerPanel":
                customerJPanel.setVisible(true);
                pic.setVisible(false);
                userVenuePanel.setVisible(false);
                break;
            case "venue":
                userVenuePanel.setVisible( true);
                customerJPanel.setVisible(false);
                pic.setVisible(false);
                break;
        }
    }
}

