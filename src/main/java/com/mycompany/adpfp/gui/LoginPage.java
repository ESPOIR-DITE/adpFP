/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adpfp.gui;

import com.mycompany.adpfp.datas.user.UserCredentials;
import com.mycompany.adpfp.io.NewClient;
import com.mycompany.adpfp.util.ServerTokenFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.*;

/**
 *
 * @author Marilyn
 */
public class LoginPage extends JFrame implements ActionListener {
    private JPanel loginPanel = new JPanel();
    private JLabel title = new JLabel("LogIn",JLabel.CENTER);
    private JLabel space = new JLabel("  ");
    private JLabel userName = new JLabel("Username");
    private JTextField userNameField = new JTextField(10);
    private JTextField passwordField = new JTextField(10);
    private JLabel password = new JLabel("Password");
    private JButton loginBTN = new JButton("Sing in");
    private Color btnBrown = new Color(81,43,40);
    Font f = new Font("Verdana",Font.BOLD,20);
    private NewClient newClient;
    public LoginPage(NewClient newClient) throws HeadlessException {
        super();
        setLayout(new BorderLayout());
        loginPanel =  getLoginPage();
        setSize(250,250);
        this.newClient = newClient;
        setLocationRelativeTo(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setVisible(true);
        title.setFont(f);
        title.setForeground(btnBrown);
        userName.setForeground(btnBrown);
        password.setForeground(btnBrown);
        this.loginBTN.addActionListener(this);
        this.loginBTN.setBackground(btnBrown);
        this.loginBTN.setForeground(Color.WHITE);
        add(loginPanel,BorderLayout.CENTER);
        add(new JPanel(),BorderLayout.NORTH);
        add(new JPanel(),BorderLayout.WEST);
        add(new JPanel(),BorderLayout.EAST);
        add(new JPanel(),BorderLayout.SOUTH);
    }

    void clearLogin(){
        userNameField.setText("");
        passwordField.setText("");
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
    UserCredentials getUserCredential(String response){
        try{
            StringTokenizer st = new StringTokenizer(response,"/");
            return UserCredentials.builder()
                    .id(st.nextToken())
                    .email(st.nextToken())
                    .password(st.nextToken())
                    .active(Boolean.parseBoolean(st.nextToken()))
                    .creator(st.nextToken())
                    .userTypeId(st.nextToken())
                    .build();
        }catch (NullPointerException nullPointerException){
            return null;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginBTN){
            String userName = getUserName();
            String password = getPassword();
            if(!userName.isEmpty()&&!password.isEmpty()){
                String result = this.newClient.communicate(ServerTokenFactory.logIn(userName,password));
                System.out.println(getUserCredential(result));
                UserCredentials userCredentials = getUserCredential(result);
                //getUser(result);
                if(result != null){
                    if(userCredentials.getActive()){
                        close();
                        if(getUser(result).equals("admin")){
                            try {
                                AdminMainGui adminMainGui = new AdminMainGui(userName,password,this.newClient);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else if(getUser(result).equals("user")){
                            try {
                                UserMainGui userMainGui = new UserMainGui(this.newClient,userName,password);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        } else{
                            LoginPage loginPage = new LoginPage(this.newClient);
                            JOptionPane.showMessageDialog(this,"User Unknown Role","Login Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }else {
                        JOptionPane.showMessageDialog(this,"User Deactivated","Login Error",JOptionPane.ERROR_MESSAGE);
                        clearLogin();
                    }
                }else {
                    JOptionPane.showMessageDialog(this,"Unknown User! \nPlease try again","Login Error",JOptionPane.ERROR_MESSAGE);
                    clearLogin();
                }
            }
        }
    }

    String getUser(String response){
        StringTokenizer st = new StringTokenizer(response,"/");
        st.nextToken();
        st.nextToken();
        st.nextToken();
        st.nextToken();
        st.nextToken();
        return st.nextToken();
    }

   
}

