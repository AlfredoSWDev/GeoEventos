package com.alfredo.ui;

import com.alfredo.data.Conector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Principal {
    public JPanel panel1;
    private JTable tablaEventos;
    private JTextField barraBusqueda;
    private JButton modificarButton;
    private JButton buscarButton;
    private JButton borrarButton;
    private JButton abrirButton;
    private JButton crearButton;


    DefaultTableModel modelo = new DefaultTableModel();

    public Principal() {
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Valor");
        modelo.addColumn("Lugar");

        tablaEventos.setModel(modelo);
        Conector db = new Conector();

        JFrame framePrincipal = desplegarUI(panel1, "Gestion de eventos");

        framePrincipal.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                db.cargarDatosTabla(modelo);
            }
        });

        // Captura de eventos de foco (Gained/Lost)
        framePrincipal.addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                System.out.println("La ventana principal ha ganado el foco.");
                db.cargarDatosTabla(modelo);
            }

            @Override
            public void windowLostFocus(java.awt.event.WindowEvent e) {
                System.out.println("La ventana principal ha perdido el foco.");
            }
        });

        framePrincipal.setVisible(true);

        String actionKey = "Buscar";

        panel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), actionKey);

        panel1.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionBuscar(framePrincipal,db,barraBusqueda.getText());
            }
        });


        buscarButton.addActionListener(e -> {
            accionBuscar(framePrincipal, db, barraBusqueda.getText());
        });

        abrirButton.addActionListener(e -> {
            int filaSeleccionada = tablaEventos.getSelectedRow();
            if (filaSeleccionada != -1) {
                Object idObj = tablaEventos.getValueAt(filaSeleccionada, 0);
                String id = String.valueOf(idObj);

                ManipularEventos ventanaModificar = new ManipularEventos("Evento:", id);
                ventanaModificar.setId(id);
            } else {
                JOptionPane.showMessageDialog(framePrincipal, "Por favor, selecciona un evento de la tabla.");
            }
        });

        crearButton.addActionListener(e -> {
            ManipularEventos ventana = new  ManipularEventos("Crear Evento");

        });

        modificarButton.addActionListener(e -> {


            int filaSeleccionada = tablaEventos.getSelectedRow();
            if (filaSeleccionada != -1) {
                Object idObj = tablaEventos.getValueAt(filaSeleccionada, 0);
                String id = String.valueOf(idObj);

                ManipularEventos ventanaModificar = new ManipularEventos("Modificar Evento", id);
                ventanaModificar.setId(id);

            } else {
                JOptionPane.showMessageDialog(framePrincipal, "Por favor, selecciona un evento de la tabla.");
            }

        });

        borrarButton.addActionListener(e -> {
            // 1. Obtenemos el índice de la fila seleccionada
            int filaSeleccionada = tablaEventos.getSelectedRow();

            // 2. Verificamos que haya una selección válida (diferente de -1)
            if (filaSeleccionada != -1) {

                Object idObj = tablaEventos.getValueAt(filaSeleccionada, 0);
                String id = String.valueOf(idObj);

                int confirmacionBorrar = JOptionPane.showConfirmDialog(framePrincipal, "Seguro que desea eliminar el evento?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmacionBorrar == JOptionPane.YES_OPTION) {
                    System.out.println("ID seleccionado para operar: " + id);

                    db.borrarEvento(id);
                    db.cargarDatosTabla(modelo);
                }
            } else {
                JOptionPane.showMessageDialog(framePrincipal, "Por favor, selecciona un evento de la tabla.");
            }
        });
    }

    public void accionBuscar(JFrame framePadre, Conector db, String busqueda){
        if(db.buscarEvento(busqueda, modelo) == 1){
            db.buscarEvento(busqueda, modelo);
        } else {
            JOptionPane.showMessageDialog(framePadre, "No se encontraron resultados.");
        }
    }


    public JFrame desplegarUI(JPanel panel, String titulo) {
        JFrame frame = new JFrame(titulo);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        return frame;
    }


}
