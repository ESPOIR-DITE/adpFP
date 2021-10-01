/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adpfp.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Marilyn
 */
public class Gui extends JFrame implements ActionListener {

    private JPanel loginPanel;

    private JPanel adminMenu = new JPanel();
    public Gui() throws HeadlessException {
        super();
        setLayout(new BorderLayout());
        loginPanel = new LoginPage().getLoginPage();
        setSize(250,200);
        setLocationRelativeTo(null);
        add(loginPanel,BorderLayout.CENTER);
        add(new JPanel(),BorderLayout.NORTH);
        add(new JPanel(),BorderLayout.WEST);
        add(new JPanel(),BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
