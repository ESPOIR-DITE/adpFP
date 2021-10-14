package com.mycompany.adpfp.gui.user.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycompany.adpfp.datas.Booking;
import com.mycompany.adpfp.datas.customer.Customer;
import com.mycompany.adpfp.datas.venue.Venue;
import com.mycompany.adpfp.io.NewClient;
import com.mycompany.adpfp.io.booking.BookingIO;
import com.mycompany.adpfp.io.customer.CustomerIO;
import com.mycompany.adpfp.io.venue.VenueIO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

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
    private JButton createBooking = new JButton("Create Bookings");
    private JButton viewBookings = new JButton("View Bookings");

    private JLabel selectVenue = new JLabel("Select Venue");
    private JComboBox venues = null;
    private JLabel selectCustomer = new JLabel("Select Customer");
    private JComboBox customers = null;
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
    private NewClient newClient;
    private String venueId = null;
    private String customerId = null;

    private Color btnBrown = new Color(81,43,40);
    private String venueSelected = null;
    private String userSelected = null;
    private BookingIO bookingIO = new BookingIO();
    Font f = new Font("Verdana",Font.BOLD,20);
    private VenueIO venueIO = new VenueIO();
    private CustomerIO customerIO = new CustomerIO();
    public UserVenueGui(NewClient newClient) throws HeadlessException {
        createVenuePanel.setLayout(new BorderLayout(10,10));
        createBooking.addActionListener(this);
        viewBookings.addActionListener(this);
        this.newClient = newClient;
        customers = new JComboBox(getCustomers());
        venues = new JComboBox(getVenues());
        venues.addActionListener(this);
        customers.addActionListener(this);
        jTextArea.setBorder(blackline);
        createBooking.setBackground(btnBrown);
        createBooking.setForeground(Color.WHITE);
        viewBookings.setBackground(btnBrown);
        viewBookings.setForeground(Color.WHITE);
        title.setFont(f);
        title.setForeground(btnBrown);

        createVenuePanel.add(title,BorderLayout.NORTH);
        createVenuePanel.add(getWestPanel(),BorderLayout.WEST);
        createVenuePanel.add(getEastPanel(),BorderLayout.CENTER);
        createVenuePanel.add(jTextArea,BorderLayout.SOUTH);
    }

    String[] getVenues(){
        System.out.println(this.newClient);
        List<String> venueList = venueIO.readAvailableVenues(this.newClient);
        System.out.println("venueList "+venueList);
        return  venueIO.readAvailableVenues(this.newClient).toArray(new String[0]);
    }
    String[] getCustomers(){
        String[] customer = {""};
        try {
            return customerIO.readAllCustomer(this.newClient).toArray(new String[0]);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return customer;
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
       UpdateFrame updateJF = new UpdateFrame(this.newClient);
        updateFrame.add(updateJF.getUpdateVenue(rowSelected,mode));
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
        updateFrame.setSize(500,400);
    }

    public JPanel getUserVenuePanel(NewClient newClient) {
        this.newClient = newClient;
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
        eastPanel.add(createBooking);
        eastPanel.add(viewBookings);
        return eastPanel;
    }

    private Booking getBooking(){
        return Booking.builder()
                .customerEmail(this.customerId)
                .venueId(this.venueId)
                .date(new Date().toString())
                .description(periodField.getText()).build();
    }

    void clearFields(){
        nameJTextField.setText("");
        locationJTextField.setText("");
        typeJTextField.setText("");
        costJTextField.setText("");
        maxNumOfPplJTextField.setText("");
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== createBooking){
            System.out.println("voila");
            if(checkIfEmpty()){
                Booking booking = getBooking();
                clearFields();
                System.out.println(booking);
                String result = bookingIO.createBooking(this.newClient,booking);
                jTextArea.append(result);
            }
        }
        if(e.getSource() == viewBookings){
            System.out.println("view clicked");
            TableGui tableGui  = new TableGui();
            try {
                tableGui.getTableJFrame(this.newClient,bookingIO.readAllBooking(this.newClient));
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
           // getTableJFrame();
        }
        if(e.getSource() == customers){
            this.userSelected = (String) customers.getSelectedItem();
            String result = customers.getSelectedItem().toString();
            this.customerId = getIds(result);
            System.out.println(this.customerId);
            //this.userSelected = (String) customers.getSelectedItem();
        }
        if(e.getSource() == venues){
            this.venueSelected = (String) venues.getSelectedItem();
            String result = venues.getSelectedItem().toString();
            this.venueId = getIds(result);
            System.out.println(this.venueId);
            //this.venueSelected = (String) venues.getSelectedItem();
        }
    }
    String getIds(String token){
        StringTokenizer st = new StringTokenizer(token,"#");
        return st.nextToken();
    }
    boolean checkIfEmpty(){
        if(periodField.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Period missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(dateField.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Period missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.venueId.equals("")){
            JOptionPane.showMessageDialog(this,"venue missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.customerId.equals("")){
            JOptionPane.showMessageDialog(this,"Customer missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
