package com.mycompany.adpfp.gui.user.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycompany.adpfp.datas.customer.Customer;
import com.mycompany.adpfp.io.NewClient;
import com.mycompany.adpfp.io.customer.CustomerIO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerGui implements ActionListener {
    private JLabel userTitleLabel = new JLabel("NEW CUSTOMER",JLabel.CENTER);
    private JLabel emailLabel = new JLabel("Email");
    private JLabel nameLabel = new JLabel("Name");
    private JLabel surnameLabel = new JLabel("Surname");
    private JLabel dateLabel = new JLabel("Date Of B");
    private JLabel space = new JLabel("");
    private JTextField emailField = new JTextField(20);
    private JTextField nameField = new JTextField(20);
    private JTextField surnameField = new JTextField(20);
    private JTextField dateOfBirthField = new  JTextField(20);
    private JTextArea jTextArea = new JTextArea(4,50);
    private JButton createCustomer = new JButton("Create Customer");
    private JButton viewCustomer = new JButton("View Customer");

    private JPanel userPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    JTable table = new JTable();
    JFrame jFrame = new JFrame();
    JFrame updateFrame = new JFrame();
    Border blackline = BorderFactory.createLineBorder(Color.black);
    private Color btnBrown = new Color(81,43,40);
    private Color btnBrownSelected = new Color(166,123,119);
    Font f = new Font("Verdana",Font.BOLD,20);

    private NewClient newClient;
    private CustomerIO customerIO = new CustomerIO();
    public CustomerGui(NewClient newClient) {
        this.newClient = newClient;
        userPanel.setLayout(new BorderLayout(10,10));
        createCustomer.addActionListener(this);
        viewCustomer.addActionListener(this);

        createCustomer.setBackground(btnBrown);
        createCustomer.setForeground(Color.WHITE);
        viewCustomer.setBackground(btnBrown);
        viewCustomer.setForeground(Color.WHITE);

        jTextArea.setBorder(blackline);
        userTitleLabel.setFont(f);
        userTitleLabel.setForeground(btnBrown);
        userPanel.add(userTitleLabel,BorderLayout.NORTH);
        userPanel.add(getEastPanel(),BorderLayout.CENTER);
        userPanel.add(getWestPanel(),BorderLayout.WEST);
        userPanel.add(jTextArea,BorderLayout.SOUTH);
    }

    public JFrame getTableJFrame(){
        jFrame.setTitle("CUstomers");

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
        UpdateFrame updateJF = new UpdateFrame(this.newClient);
        updateFrame.add(updateJF.getUpdateVenue(rowSelected,mode));
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
        updateFrame.setSize(500,400);
    }
    public JPanel getCustomerGui(){
        return userPanel;
    }
    JPanel getWestPanel(){
        westPanel.setLayout(new GridLayout(9,1,5,5));
        this.westPanel.add(emailLabel);
        this.westPanel.add(nameLabel);
        this.westPanel.add(surnameLabel);
        this.westPanel.add(dateLabel);
        return westPanel;
    }
    JPanel getEastPanel(){
        eastPanel.setLayout(new GridLayout(9,1,5,5));
        this.eastPanel.add(emailField);
        this.eastPanel.add(nameField);
        this.eastPanel.add(surnameField);
        this.eastPanel.add(dateOfBirthField);
        this.eastPanel.add(space);
        this.eastPanel.add(createCustomer);
        this.eastPanel.add(viewCustomer);
        return eastPanel;
    }
    boolean checkFields(){
        if(emailField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Email missing");
            return false;
        }
        if(nameField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Name missing");
            return false;
        }
        if(dateOfBirthField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Date Of Birth missing");
            return false;
        }
        if(surnameField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Surname missing");
            return false;
        }
        return true;
    }
    void clearFields(){
        emailField.setText("");
        nameField.setText("");
        surnameField.setText("");
        dateOfBirthField.setText("");
    }
    Customer getCustomer(){
       return  Customer.builder()
               .email(emailField.getText())
                .name(nameField.getText())
                .surname(surnameField.getText())
                .date(dateOfBirthField.getText())
                .build();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createCustomer){
            if(checkFields()){
                String result = customerIO.createCustomer(this.newClient,getCustomer());
                clearFields();
                jTextArea.append(result);
            }
        }
        if(e.getSource() == viewCustomer){
            System.out.println("view clicked");
            //getTableJFrame();

            TableGui tableGui = new TableGui();
            try {
                List<Customer> customer = customerIO.readCustomer(newClient);
                System.out.println(customer);
                tableGui.getTableJFrame(this.newClient,customer);
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }

        }
    }
}

