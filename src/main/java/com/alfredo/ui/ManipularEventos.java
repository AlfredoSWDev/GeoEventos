package com.alfredo.ui;

import com.alfredo.data.Conector;

import javax.swing.*;

public class ManipularEventos {
    private JTextField nombreTextField;
    private JTextArea descripcionTextArea;
    private JTextField vigenciaTextField;
    private JTextField valorTextField;
    private JTextField lugarTextField;
    public JPanel panelPrincipal;
    private JButton listoButton;

    public ManipularEventos(String titulo, String id) {
        this.id = id;

        JFrame frameModificar = new JFrame(titulo);
        frameModificar.setContentPane(panelPrincipal);
        frameModificar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameModificar.pack();
        frameModificar.setLocationRelativeTo(null);
        frameModificar.setVisible(true);
        frameModificar.setAlwaysOnTop(true);

        if (titulo == "Modificar Evento") {

            String[] evento = db.leerEventos(id);
            nombreTextField.setText(evento[0]);
            descripcionTextArea.setText(evento[2]);
            vigenciaTextField.setText(evento[3]);
            valorTextField.setText(evento[4]);
            lugarTextField.setText(evento[5]);

            listoButton.setText(titulo);
            listoButton.addActionListener(e -> {
                nombre = nombreTextField.getText();
                descripcion = descripcionTextArea.getText();
                vigencia = vigenciaTextField.getText();
                valor = valorTextField.getText();
                lugar = lugarTextField.getText();
                db.modificarEvento(id, nombre, fotos, descripcion, vigencia, valor, lugar);
                JOptionPane.showMessageDialog(frameModificar, "Evento modificado correctamente");

            });
        } else if (titulo == "Evento:") {
            String[] evento = db.leerEventos(id);
            nombreTextField.setText(evento[0]);
            descripcionTextArea.setText(evento[2]);
            vigenciaTextField.setText(evento[3]);
            valorTextField.setText(evento[4]);
            lugarTextField.setText(evento[5]);

            listoButton.setVisible(false);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    String nombre = nombreTextField.getText();
    String descripcion = descripcionTextArea.getText();
    String vigencia = vigenciaTextField.getText();
    String valor = valorTextField.getText();
    String lugar = lugarTextField.getText();

    String fotos = "Fotos";

    Conector db = new Conector();

    public ManipularEventos(String titulo) {

        JFrame frameModificar = new JFrame(titulo);
        frameModificar.setContentPane(panelPrincipal);
        frameModificar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameModificar.pack();
        frameModificar.setLocationRelativeTo(null);
        frameModificar.setVisible(true);
        frameModificar.setAlwaysOnTop(true);

        if(titulo == "Crear Evento") {
            listoButton.setText(titulo);
            listoButton.addActionListener(e -> {
                nombre = nombreTextField.getText();
                descripcion = descripcionTextArea.getText();
                vigencia = vigenciaTextField.getText();
                valor = valorTextField.getText();
                lugar = lugarTextField.getText();
                db.crearEvento(nombre, fotos, descripcion, vigencia, valor, lugar);
                JOptionPane.showMessageDialog(frameModificar, "Evento creado correctamente");
            });
        }
    }
}

