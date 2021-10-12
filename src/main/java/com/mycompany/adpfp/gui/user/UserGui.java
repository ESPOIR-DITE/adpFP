package com.mycompany.adpfp.gui.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycompany.adpfp.datas.user.UserCredentials;
import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.gui.venue.UpdateFrame;
import com.mycompany.adpfp.io.NewClient;
import com.mycompany.adpfp.io.user.UserIO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserGui implements ActionListener {
    private JLabel userTitleLabel = new JLabel("NEW USER",JLabel.CENTER);
    private JLabel emailLabel = new JLabel("Email");
    private JLabel nameLabel = new JLabel("Name");
    private JLabel surnameLabel = new JLabel("Surname");
    private JLabel passwordLabel = new JLabel("Password");
    private JLabel UserTypeLabel = new JLabel("User Type");
    private JLabel space = new JLabel("");
    private JTextField emailField = new JTextField(20);
    private JTextField nameField = new JTextField(20);
    private JTextField surnameField = new JTextField(20);
    private JTextField password = new  JTextField(20);
    private JTextField userTypeFiled = new  JTextField(20);
    private JTextArea jTextArea = new JTextArea(4,50);
    private JButton newUser = new JButton("Create User");
    private JButton viewVenue = new JButton("View Users");


    private JPanel userPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();


    JFrame jFrame = new JFrame();
    JFrame updateFrame = new JFrame();
    JTable table = null;

    private Color btnBrown = new Color(81,43,40);
    Border blackline = BorderFactory.createLineBorder(Color.black);
    Font f = new Font("Verdana",Font.BOLD,20);

    static NewClient newClient = null;

    private UserIO userIO = new UserIO();
    public UserGui() {
        userPanel.setLayout(new BorderLayout(10,10));
        newUser.addActionListener(this);
        viewVenue.addActionListener(this);
        newUser.setBackground(btnBrown);
        newUser.setForeground(Color.white);
        viewVenue.setBackground(btnBrown);
        viewVenue.setForeground(Color.white);
        jTextArea.setBorder(blackline);
        userTitleLabel.setFont(f);
        userTitleLabel.setForeground(btnBrown);
        //System.out.println("new client "+this.newClient);
        userPanel.add(userTitleLabel,BorderLayout.NORTH);
        userPanel.add(getEastPanel(),BorderLayout.CENTER);
        userPanel.add(getWestPanel(),BorderLayout.WEST);
        userPanel.add(jTextArea,BorderLayout.SOUTH);
    }

    public JFrame getTableJFrame(List<Users> usersList){
//        JTable table = new JTable();
        jFrame.setTitle("User Table");
        String [] columnNames = {"Email","Name","Surname","Date"};

        table = new JTable(listToArray(usersList),columnNames);
        ListSelectionModel listSelectionModel = table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = table.getSelectedRow();
                getUpdateJFrameGui(row,table);
            }
        });
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
        table.setBounds(30,40,400,400);
        JScrollPane sp = new JScrollPane(table, v, h);
        jFrame.add(sp);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setSize(500,200);
        return jFrame;
    }
    static String[][] listToArray(List<Users> list) {
        int size = list.size();
        String[][] tab2d = new String[size][4];
        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            stringList=(setList2(list.get(i)));
            for(int j = 0; j < 4; j++) {
                tab2d[i][j] = stringList.get(j);
            }
        }
        //System.out.println("looping list: "+size);
        return tab2d;
    }

    static List<String> setList2(Users list){
        List<String> stringList = new ArrayList<>();

            stringList.add(list.getEmail());
            stringList.add(list.getName());
            stringList.add(list.getSurname());
            stringList.add(list.getDate().toString());

        return  stringList;
    }
    void getUpdateJFrameGui(int rowSelected, JTable mode){
        UpdateFrame updateJF = new UpdateFrame();
        updateFrame.add(updateJF.getUpdateVenue(rowSelected,mode));
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
        updateFrame.setSize(550,400);
    }
    public JPanel getUserGui(){
        return userPanel;
    }
    public void setClient(NewClient newClient){
        System.out.println(newClient);
        this.newClient = newClient;
    }
    JPanel getWestPanel(){
        westPanel.setLayout(new GridLayout(9,1,5,5));
        this.westPanel.add(emailLabel);
        this.westPanel.add(nameLabel);
        this.westPanel.add(surnameLabel);
        this.westPanel.add(passwordLabel);
        this.westPanel.add(UserTypeLabel);
        return westPanel;
    }
    JPanel getEastPanel(){
        eastPanel.setLayout(new GridLayout(9,1,5,5));
        this.eastPanel.add(emailField);
        this.eastPanel.add(nameField);
        this.eastPanel.add(surnameField);
        this.eastPanel.add(password);
        this.eastPanel.add(userTypeFiled);
        this.eastPanel.add(space);
        this.eastPanel.add(newUser);
        this.eastPanel.add(viewVenue);
        return eastPanel;
    }

    Users getUsers(){
        return Users.builder()
                .date(new Date().toString())
                .email(emailField.getText())
                .name(nameField.getText())
                .date(new Date().toString())
                .surname(surnameField.getText())
                .build();
    }
    void clearUserField(){
        emailField.setText("");
        nameField.setText("");
        surnameField.setText("");
        password.setText("");
        userTypeFiled.setText("");
    }
    UserCredentials getUserCredential(){
        return UserCredentials.builder()
                .active(true)
                .creator("")
                .email(emailField.getText())
                .userTypeId("user")
                .password(password.getText())
                .build();
    }
    boolean checkUserFields(){
        if (emailField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Email missing");
            return false;
        }
        if (nameField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Name missing");
            return false;
        }
        if (surnameField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Surname missing");
            return false;
        }
        if (password.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Password missing");
            return false;
        }
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newUser){
            //System.out.println("voila");
            if(checkUserFields()){
                String result = userIO.createUser(this.newClient,getUsers(),password.getText(),userTypeFiled.getText());
                jTextArea.append(result);
                clearUserField();
            }
        }
        if(e.getSource() == viewVenue){
            List<Users> usersList = new ArrayList<>();
            try {
                usersList =userIO.readUsers(this.newClient);
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
            TableGui tableGui = new TableGui();
            tableGui.getTableJFrame(usersList);
            //getTableJFrame(usersList);
        }
    }
}

