/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adpfp;

import com.mycompany.adpfp.gui.LoginPage;
import com.mycompany.adpfp.io.NewClient;

/**
 *
 * @author Marilyn
 */
public class Main {
    public static void main(String[] args) {
        NewClient newClient = new NewClient();
    LoginPage loginPage = new LoginPage(newClient);
    }
}
