package com.mycompany.adpfp.gui.venue;

import com.mycompany.adpfp.datas.venue.Venue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class UserVenueGui extends JFrame implements ActionListener {
    private JPanel createVenuePanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JLabel space = new JLabel("");
    private JLabel title = new JLabel("BOOKING VENUE",JLabel.CENTER);
    private JLabel nameLabel = new JLabel("Name");
    private JLabel locationLabel = new JLabel("Location");
    private JLabel typeLabel = new JLabel("Type");
    private JLabel costLabel = new JLabel("Cost");
    private JLabel MaxNumbOfPplLabel = new JLabel("Max Num Of Ppl");

    private JTextField nameJTextField = new JTextField(20);
    private JTextField locationJTextField = new JTextField(20);
    private JTextField typeJTextField = new JTextField(20);
    private JTextField costJTextField = new JTextField(20);
    private JTextField maxNumOfPplJTextField = new JTextField(20);
    private JButton createVenue = new JButton("Create Bookings");
    private JButton viewVenue = new JButton("View Bookings");

    private JLabel selectVenue = new JLabel("Select Venue");
    private JComboBox venues = new JComboBox(getVenues());
    private JLabel selectCustomer = new JLabel("Select Customer");
    private JComboBox customers = new JComboBox(getCustomers());
    private JLabel period = new JLabel("Period");
    private JTextField periodField = new JTextField(20);
    private JLabel date = new JLabel("Date");
    private JTextField dateField = new JTextField(20);
    JTable table = new JTable();
    JFrame jFrame = new JFrame();
    JFrame updateFrame = new JFrame();
    private JPanel userVenue = new JPanel();
    private JTextArea jTextArea = new JTextArea(4,50);
    JPanel venueUpdatePanel = new JPanel();
    Border blackline = BorderFactory.createLineBorder(Color.black);

    private Color btnBrown = new Color(81,43,40);
    Font f = new Font("Verdana",Font.BOLD,20);
    public UserVenueGui() throws HeadlessException {
        createVenuePanel.setLayout(new BorderLayout(10,10));
        createVenue.addActionListener(this);
        viewVenue.addActionListener(this);
        venues.addActionListener(this);
        customers.addActionListener(this);
        jTextArea.setBorder(blackline);
        createVenue.setBackground(btnBrown);
        createVenue.setForeground(Color.WHITE);
        viewVenue.setBackground(btnBrown);
        viewVenue.setForeground(Color.WHITE);
        title.setFont(f);
        title.setForeground(btnBrown);
        createVenuePanel.add(title,BorderLayout.NORTH);
        createVenuePanel.add(getWestPanel(),BorderLayout.WEST);
        createVenuePanel.add(getEastPanel(),BorderLayout.CENTER);
        createVenuePanel.add(jTextArea,BorderLayout.SOUTH);
    }

    String[] getVenues(){
        return new String[]{"espoor", "hjsdllkd", "iudhfdjk", "iuhjkdskdjhf"};
    }
    String[] getCustomers(){
        return new String[]{"espoor", "hjsdllkd", "iudhfdjk", "iuhjkdskdjhf"};
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

    public JPanel getUserVenuePanel() {
        return createVenuePanel;
    }

    public JPanel getWestPanel() {
        westPanel.setLayout(new GridLayout(9,1,5,5));
        westPanel.add(selectVenue);
        westPanel.add(selectCustomer);
        westPanel.add(period);
        westPanel.add(date);
        return westPanel;
    }

    public JPanel getEastPanel() {
        eastPanel.setLayout(new GridLayout(9,1,5,5));
        eastPanel.add(venues);
        eastPanel.add(customers);
        eastPanel.add(periodField);
        eastPanel.add(dateField);
        eastPanel.add(space);
        eastPanel.add(createVenue);
        eastPanel.add(viewVenue);
        return eastPanel;
    }

    private UserVenueGui getVenue(){
        String name = null;
        String location = null;
        String type = null;
        String cost = null;
        String maxNumOfPpl = null;
        Venue venue = null;
        if ((name = checkIfEmpty(nameJTextField.getText(),"Name"))==null) clearFields();
        if ((location = checkIfEmpty(locationJTextField.getText(),"Location"))==null) clearFields();
        if ((type = checkIfEmpty(typeJTextField.getText(),"Category"))==null) clearFields();
        if ((cost = checkIfEmpty(costJTextField.getText(),"Cost"))==null) clearFields();
        if ((maxNumOfPpl = checkIfEmpty(maxNumOfPplJTextField.getText(),"Max"))==null) clearFields();

        try{
            venue = new Venue("389ru",name,location,Double.parseDouble(cost),Integer.parseInt(maxNumOfPpl), LocalDate.now(),type);

            if(venue.isNull()){
                System.out.println(venue.toString());
                clearFields();
            }else {
                System.out.println("Fields missing");
                System.out.println(venue.toString());
                clearFields();
            }

        }catch (NullPointerException nullPointerException){
            clearFields();
            JOptionPane.showMessageDialog(this,"Error");
        }catch (NumberFormatException numberFormatException){
            clearFields();
            JOptionPane.showMessageDialog(this,"Error");
        }

        return null;
    }

    void clearFields(){
        nameJTextField.setText("");
        locationJTextField.setText("");
        typeJTextField.setText("");
        costJTextField.setText("");
        maxNumOfPplJTextField.setText("");
    }

    String checkIfEmpty(String field,String errorName){
        if(field.equals("")){
            JOptionPane.showMessageDialog(this,errorName+" missing");
            return null;
        }
        return field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==createVenue){
            System.out.println("voila");
            getVenue();
        }
        if(e.getSource() == viewVenue){
            System.out.println("view clicked");
            getTableJFrame();
        }
        if(e.getSource() == customers){
            System.out.println("customers");
            System.out.println(customers.getSelectedItem());
        }
        if(e.getSource() == venues){
            System.out.println("venues");
            System.out.println(venues.getSelectedItem());
        }
    }
}
