package com.mycompany.adpfp.gui.user;

import com.mycompany.adpfp.gui.venue.UpdateFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserGui implements ActionListener {
    private JLabel userTitleLabel = new JLabel("NEW USER",JLabel.CENTER);
    private JLabel emailLabel = new JLabel("Email");
    private JLabel nameLabel = new JLabel("Name");
    private JLabel surnameLabel = new JLabel("Surname");
    private JLabel passwordLabel = new JLabel("Password");
    private JLabel space = new JLabel("");
    private JTextField emailField = new JTextField(20);
    private JTextField nameField = new JTextField(20);
    private JTextField surnameField = new JTextField(20);
    private JTextField password = new  JTextField(20);
    private JTextArea jTextArea = new JTextArea(4,50);
    private JButton newUser = new JButton("Create User");
    private JButton viewVenue = new JButton("View Users");


    private JPanel userPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();

    JTable table = new JTable();
    JFrame jFrame = new JFrame();
    JFrame updateFrame = new JFrame();

    private Color btnBrown = new Color(81,43,40);
    Border blackline = BorderFactory.createLineBorder(Color.black);
    Font f = new Font("Verdana",Font.BOLD,20);
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
        userPanel.add(userTitleLabel,BorderLayout.NORTH);
        userPanel.add(getEastPanel(),BorderLayout.CENTER);
        userPanel.add(getWestPanel(),BorderLayout.WEST);
        userPanel.add(jTextArea,BorderLayout.SOUTH);
    }

    public JFrame getTableJFrame(){
        jFrame.setTitle("Venues");

        String [] columnNames = {"Name","Location","Type","Cost","Max Num Of Ppl"};
        String [][] data = {
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"}
        };

        table = new JTable(data,columnNames);
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
    void getUpdateJFrameGui(int rowSelected, JTable mode){
        UpdateFrame updateJF = new UpdateFrame();
        updateFrame.add(updateJF.getUpdateVenue(rowSelected,mode));
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
        updateFrame.setSize(500,400);
    }
    public JPanel getUserGui(){
        return userPanel;
    }
    JPanel getWestPanel(){
        westPanel.setLayout(new GridLayout(9,1,5,5));
        this.westPanel.add(emailLabel);
        this.westPanel.add(nameLabel);
        this.westPanel.add(surnameLabel);
        this.westPanel.add(passwordLabel);
        return westPanel;
    }
    JPanel getEastPanel(){
        eastPanel.setLayout(new GridLayout(9,1,5,5));
        this.eastPanel.add(emailField);
        this.eastPanel.add(nameField);
        this.eastPanel.add(surnameField);
        this.eastPanel.add(password);
        this.eastPanel.add(space);
        this.eastPanel.add(newUser);
        this.eastPanel.add(viewVenue);
        return eastPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newUser){
            System.out.println("voila");
        }
        if(e.getSource() == viewVenue){
            System.out.println("view clicked");
            getTableJFrame();
        }
    }
}

