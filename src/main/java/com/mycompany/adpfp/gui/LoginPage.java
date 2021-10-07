/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adpfp.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Marilyn
 */
public class LoginPage extends JFrame implements ActionListener {
    private JPanel loginPanel = new JPanel();
    private JLabel title = new JLabel("LogIn");
    private JLabel space = new JLabel("  ");
    private JLabel userName = new JLabel("Username");
    private JTextField userNameField = new JTextField(10);
    private JTextField passwordField = new JTextField(10);
    private JLabel password = new JLabel("Password");
    private JButton loginBTN = new JButton("Sing in");

    public LoginPage() throws HeadlessException {
        super();
        setLayout(new BorderLayout());
        loginPanel =  getLoginPage();
        setSize(250,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setVisible(true);
        this.loginBTN.addActionListener(this);
        add(loginPanel,BorderLayout.CENTER);
        add(new JPanel(),BorderLayout.NORTH);
        add(new JPanel(),BorderLayout.WEST);
        add(new JPanel(),BorderLayout.EAST);
        add(new JPanel(),BorderLayout.SOUTH);
    }

    public JPanel getLoginPage(){
        loginPanel.setLayout(new GridLayout(8,1));
        //loginPanel.setBackground(Color.PINK);
        loginPanel.setPreferredSize(new Dimension(20,20));
        loginPanel.add(title);
        loginPanel.add(space);
        loginPanel.add(userName);
        loginPanel.add(userNameField);
        loginPanel.add(password);
        loginPanel.add(passwordField);
        loginPanel.add(space);
        loginPanel.add(loginBTN);
        return loginPanel;
    }
    public String getUserName(){
        return userNameField.getText();
    }
    public String getPassword(){
        return passwordField.getText();
    }
    public void seVisible(){
        loginPanel.setVisible(false);
    }
    public void seInvisible(){
        loginPanel.setVisible(false);
    }
    public void close(){
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginBTN){
            String userName = getUserName();
            String password = getPassword();
            if(!userName.isEmpty()&&!password.isEmpty()){
                close();
                if(userName == "Admin"){
                    try {
                        AdminMainGui adminMainGui = new AdminMainGui(userName,password);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }else {
                    try {
                        UserMainGui userMainGui = new UserMainGui(userName,password);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            }
        }
    }

   
}

