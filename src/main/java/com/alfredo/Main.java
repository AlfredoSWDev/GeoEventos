package com.alfredo;

import com.alfredo.ui.Principal;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {
    static void main() {

        try {
            UIManager.setLookAndFeel( new FlatLightLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        Principal principal = new Principal();


//        JFrame frame = new JFrame("Gestión de Eventos");
//        frame.setContentPane(new Principal().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
    }
}
