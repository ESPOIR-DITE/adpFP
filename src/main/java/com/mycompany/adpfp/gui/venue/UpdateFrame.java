//package com.mycompany.adpfp.gui.venue;
//
//import com.mycompany.adpfp.datas.customer.Customer;
//import com.mycompany.adpfp.io.NewClient;
//import com.mycompany.adpfp.io.customer.CustomerIO;
//import com.sun.source.tree.NewArrayTree;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class UpdateFrame  implements ActionListener {
//    JFrame updateJFrame = new JFrame();
//    private JTextArea jTextUpdateArea = new JTextArea(3,5);
//    JPanel userUpdatePanel = new JPanel();
//    private JPanel westUpdatePanel = new JPanel();
//    private JPanel eastUpdatePanel = new JPanel();
//    private JLabel space = new JLabel("");
//    private JLabel title = new JLabel("UPDATE VENUE",JLabel.CENTER);
//    private JLabel nameUpdateLabel = new JLabel("Name");
//    private JLabel emailUpdateLabel = new JLabel("Email");
//    private JLabel surnameUpdateLabel = new JLabel("Surname");
//    private JLabel idLabel = new JLabel("Id");
//
//    private JTextField nameJTextUpdateField = new JTextField(20);
//    private JTextField emailJTextUpdateField = new JTextField(20);
//    private JTextField surnameJTextUpdateField = new JTextField(20);
//    private JTextField idField = new JTextField(20);
//    private JButton updateUser = new JButton("Update");
//    private JButton deleteUser = new JButton("Delete");
//    private Color btnBrown = new Color(81,43,40);
//    Font f = new Font("Verdana",Font.BOLD,20);
//
//    private CustomerIO customerIO = new CustomerIO();
//    private NewClient newClient;
//    public UpdateFrame(NewClient newClient) {
//        this.newClient=newClient;
//        userUpdatePanel.setLayout(new BorderLayout(5,5));
//        updateUser.addActionListener(this);
//        deleteUser.addActionListener(this);
//        jTextUpdateArea.setEditable(false);
//        title.setFont(f);
//        title.setForeground(btnBrown);
//        userUpdatePanel.add(title,BorderLayout.NORTH);
//        userUpdatePanel.add(getWestUpdatePanel(),BorderLayout.WEST);
//        userUpdatePanel.add(getEastUpdatePanel(),BorderLayout.CENTER);
//        userUpdatePanel.add(jTextUpdateArea,BorderLayout.SOUTH);
//    }
//
//    public JPanel getUpdateVenue(int rowSelected, JTable mode) {
//        setUpdateVenueData(
//                mode.getModel().getValueAt(rowSelected,0).toString(),
//                mode.getModel().getValueAt(rowSelected,1).toString(),
//                mode.getModel().getValueAt(rowSelected,2).toString(),
//                mode.getModel().getValueAt(rowSelected,3).toString()
//                );
//        return userUpdatePanel;
//    }
//    private void setUpdateVenueData(String id, String email, String name, String surname){
//        nameJTextUpdateField.setText(name);
//        emailJTextUpdateField.setText(email);
//        idField.setText(id);
//        surnameJTextUpdateField.setText(surname);
//        jTextUpdateArea.setText("READY TO UPDATE !!");
//    }
//    void clearCustomerField(){
//        nameJTextUpdateField.setText("");
//        emailJTextUpdateField.setText("");
//        idField.setText("");
//        surnameJTextUpdateField.setText("");
//    }
//    Customer getCustomer(){
//       return Customer.builder()
//                .id(idField.getText())
//                .name(nameJTextUpdateField.getText())
//                .surname(surnameJTextUpdateField.getText())
//                .email(emailJTextUpdateField.getText()).build();
//    }
//    boolean checkIfFilled(){
//        if(nameJTextUpdateField.getText().equals("")) {
//            JOptionPane.showMessageDialog(null,"Name Missing","Error!",JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        if(emailJTextUpdateField.getText().equals("")) {
//            JOptionPane.showMessageDialog(null,"Name Missing","Error!",JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        if(idField.getText().equals("")) {
//            JOptionPane.showMessageDialog(null,"Name Missing","Error!",JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        if(surnameJTextUpdateField.getText().equals("")) {
//            JOptionPane.showMessageDialog(null,"Name Missing","Error!",JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        return true;
//    }
//
//    public JPanel getWestUpdatePanel() {
//        westUpdatePanel.setLayout(new GridLayout(9,1));
//        westUpdatePanel.add(idLabel);
//        westUpdatePanel.add(emailUpdateLabel);
//        westUpdatePanel.add(nameUpdateLabel);
//        westUpdatePanel.add(surnameUpdateLabel);
//        return westUpdatePanel;
//    }
//
//    public JPanel getEastUpdatePanel() {
//        eastUpdatePanel.setLayout(new GridLayout(9,1));
//        eastUpdatePanel.add(idField);
//        eastUpdatePanel.add(emailJTextUpdateField);
//        eastUpdatePanel.add(nameJTextUpdateField);
//        eastUpdatePanel.add(surnameJTextUpdateField);
//        eastUpdatePanel.add(space);
//        eastUpdatePanel.add(updateUser);
//        eastUpdatePanel.add(deleteUser);
//        return eastUpdatePanel;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource()== updateUser){
//            System.out.println("about to update");
//            if(checkIfFilled()){
//                Customer customer = getCustomer();
//                clearCustomerField();
//                String result = customerIO.updateCustomer(this.newClient,customer);
//                if(result.equals("true")){
//                    jTextUpdateArea.append("Updated successful");
//                }
//            }
//        }
////        if(e.getSource()==deleteUser){
////            System.out.println("about to delete");
////            if(!idField.getText().equals("")){
////                Customer customer = getCustomer();
////                clearCustomerField();
////                String result = customerIO.deleteCustomer(this.newClient,customer);
////                if(result.equals("true")){
////                    jTextUpdateArea.append("Updated successful");
////                }
////            }
////        }
//
//    }
//}
