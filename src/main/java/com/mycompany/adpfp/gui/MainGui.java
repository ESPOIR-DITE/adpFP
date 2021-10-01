/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adpfp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Marilyn
 */
public class MainGui extends JFrame implements ActionListener {
    private JPanel menu = new JPanel();
    private JPanel venuePanel = new JPanel();
    private JPanel userPanel = new JPanel();
    private JLabel title = new JLabel("Admin Menu");
    private JButton venue = new JButton("Venue");
    private JButton user = new JButton("User");
    private JTextArea menuSpace = new JTextArea(20,7);

    private JLabel userTitleLabel = new JLabel("New User");
    private JLabel emailLabel = new JLabel("Email");
    private JLabel nameLabel = new JLabel("Name");
    private JLabel surnameLabel = new JLabel("Surname");
    private JLabel passwordLabel = new JLabel("Surname");
    private JLabel space = new JLabel("");

    private JTextField emailField = new JTextField(10);
    private JTextField nameField = new JTextField(10);
    private JTextField surnameField = new JTextField(10);
    private JTextField password = new  JTextField(10);

    private JButton newUser = new JButton("Create");

    public MainGui(String userName,String password) throws HeadlessException {
        super();
        setLayout(new BorderLayout(5,5));
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setVisible(true);
        title.setSize(4,4);
        this.menu = getMenu();
        add(menu,BorderLayout.WEST);
        venue.addActionListener(this);
        user.addActionListener(this);
        add(new TextArea(10,5),BorderLayout.CENTER);
        add(new JPanel(),BorderLayout.NORTH);
        add(new JPanel(),BorderLayout.EAST);
        add(new JPanel(),BorderLayout.SOUTH);
    }
    public JPanel getMenu(){
        menu.setLayout(new GridLayout(20,1));
        menu.setBackground(Color.PINK);
        menu.add(title);
        menu.add(venue);
        menu.add(user);
        menu.add(menuSpace);
        return menu;
    }
    JPanel getUserPanel(){
        userPanel.setLayout(new GridLayout(20,1));
        this.userPanel.add(userTitleLabel);
        this.userPanel.add(space);
        this.userPanel.add(emailLabel);
        this.userPanel.add(emailField);
        this.userPanel.add(nameLabel);
        this.userPanel.add(nameField);
        this.userPanel.add(surnameLabel);
        this.userPanel.add(surnameField);
        this.userPanel.add(passwordLabel);
        this.userPanel.add(password);
        this.userPanel.add(space);
        this.userPanel.add(newUser);
        return userPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource()==user){
        user.setBackground(Color.CYAN);
        remove(venuePanel);
        add(userPanel);
    }
    }
}

