/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Otros.VariablesGlobales;
import data.DatosConexion;
import data.DatosConexionORACLE;
import data.DatosConexionPSQL;
import data.DatosMYSQL;
import data.DatosORACLE;
import data.DatosPSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author krozz007
 */
public class JFTablasPostgres extends javax.swing.JFrame {

   
    String tablas;
    String strBases;
    DatosMYSQL objDatosEstudiantes = new DatosMYSQL();
    DatosConexion objConectar = new DatosConexion();
    
    DatosPSQL objDatosPSQL = new DatosPSQL();
    DatosConexionPSQL objConectarPSQL = new DatosConexionPSQL();

    DatosORACLE objDatosOracle = new DatosORACLE();
    DatosConexionORACLE objConectarOracle = new DatosConexionORACLE();
    /**
     * Creates new form MostrasTables
     */
    

    public JFTablasPostgres() throws ClassNotFoundException {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);

    }

    public void mostrarDatos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Tablas");
        tbMostrarTablas.setModel(modelo);
        String[] datos = new String[1];
        try {
            ResultSet rs = objDatosEstudiantes.presentarTablas(strBases);
            while (rs.next()) {
                datos[0] = rs.getString("Tables_in_" + strBases);
                modelo.addRow(datos);
            }
            tbMostrarTablas.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
     public void mostrarDatosPSQL() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Tablas");
        tbMostrarTablas.setModel(modelo);
        String[] datos = new String[1];
        try {
            ResultSet rs = objDatosPSQL.presentarTablasPSQL(strBases);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                modelo.addRow(datos);
            }
            tbMostrarTablas.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
     public void mostrarDatosORACLE() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Tablas");
        tbMostrarTablas.setModel(modelo);
        String[] datos = new String[1];
        try {
            ResultSet rs = objDatosOracle.presentarTablas(strBases);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                modelo.addRow(datos);
            }
            tbMostrarTablas.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void IngresarTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Columnas");
        modelo.addColumn("Dominios");
        tbTabla1.setModel(modelo);
        String[] datos = new String[2];
        try {
            ResultSet rs = objDatosEstudiantes.ingresarTabla(strBases, tablas);
            while (rs.next()) {
                datos[0] = rs.getString("Field");
                datos[1] = rs.getString("Type");
                modelo.addRow(datos);
            }
            tbTabla1.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void IngresarTablasPSQL() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Columnas");
        modelo.addColumn("Dominios");
        tbTabla1.setModel(modelo);
        String[] datos = new String[2];
        try {
            ResultSet rs = objDatosPSQL.ingresarTablaPSQL(strBases, tablas);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                  VariablesGlobales.strNombreColumna1.add(datos[0]);
                datos[1] = rs.getString(2);
                VariablesGlobales.strTipoDto1.add(datos[1]);
                modelo.addRow(datos);
            }
            tbTabla1.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    public void IngresarTablasPSQL2() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Columnas");
        modelo.addColumn("Dominios");
        tbTabla2.setModel(modelo);
        String[] datos = new String[2];
        try {
            ResultSet rs = objDatosPSQL.ingresarTablaPSQL(strBases, tablas);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                  VariablesGlobales.strNombreColumna2.add(datos[0]);
                datos[1] = rs.getString(2);
                VariablesGlobales.strTipoDto2.add(datos[1]);
                modelo.addRow(datos);
            }
            tbTabla2.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
                public void esJointipoDatoPresentar() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Columnas");
        modelo.addColumn("Dominios");
        tbPresentrarJoinsPost.setModel(modelo);
        String[] datos = new String[2];
        ArrayList<String> arrayMayorDato = new ArrayList<String>();
        ArrayList<String> arrayMayorColumna = new ArrayList<String>();

        ArrayList<String> arrayMenorDato = new ArrayList<String>();
        ArrayList<String> arrayMenorColumna = new ArrayList<String>();
        int hayDato = VariablesGlobales.strTipoDto1.size();
        System.out.println("====================================================");

        int hayDato2 = VariablesGlobales.strTipoDto2.size();
        System.out.println("Valores dato 1 hay valor ?=" + hayDato);
        System.out.println("Valores dato 2 hay valor ?=" + hayDato2);
        System.out.println("====================================================");
        int intMenorDato;
        int intMayorDato;
        //varibles valores 
        if (VariablesGlobales.strTipoDto1.size() > VariablesGlobales.strTipoDto2.size() & VariablesGlobales.strNombreColumna1.size() > VariablesGlobales.strNombreColumna2.size()) {
            arrayMayorDato = new ArrayList<String>(VariablesGlobales.strTipoDto1);
            arrayMayorColumna = new ArrayList<String>(VariablesGlobales.strNombreColumna1);
            intMayorDato = VariablesGlobales.strTipoDto1.size();
            arrayMenorDato = new ArrayList<String>(VariablesGlobales.strTipoDto2);
            arrayMenorColumna = new ArrayList<String>(VariablesGlobales.strNombreColumna2);
            intMenorDato = VariablesGlobales.strTipoDto2.size();
        } else {
            arrayMenorDato = new ArrayList<String>(VariablesGlobales.strTipoDto1);
            arrayMenorColumna = new ArrayList<String>(VariablesGlobales.strNombreColumna1);
            intMenorDato = VariablesGlobales.strTipoDto1.size();
            arrayMayorDato = new ArrayList<String>(VariablesGlobales.strTipoDto2);
            arrayMayorColumna = new ArrayList<String>(VariablesGlobales.strNombreColumna2);
            intMayorDato = VariablesGlobales.strTipoDto2.size();
        }

        ArrayList<String> posiblesJoinsTipoDato = new ArrayList<String>();
        System.out.println("=====================================");
        for (int i = 0; i < intMayorDato; i++) {
            for (int j = 0; j < intMenorDato; j++) {
                System.out.println("\t" + arrayMayorDato.get(i));
                System.out.println(arrayMenorDato.get(j));
                if (arrayMayorDato.get(i).equals(arrayMenorDato.get(j))) {

                    posiblesJoinsTipoDato.add(arrayMayorColumna.get(i) + "." + arrayMayorDato.get(i) + "," + arrayMenorColumna.get(j) + "." + arrayMenorDato.get(j));
                    System.out.println(arrayMayorColumna.get(i) + "." + arrayMayorDato.get(i) + "," + arrayMenorColumna.get(j) + "." + arrayMenorDato.get(j));
                    datos[0] = arrayMayorColumna.get(i) + " CON " + arrayMenorColumna.get(j);
                    datos[1] = arrayMayorDato.get(i) + "=" + arrayMenorDato.get(j);
                    modelo.addRow(datos);

                    tbPresentrarJoinsPost.setModel(modelo);
                } else {
                    System.out.println("no se encontraton igualdades");
                }

            }
        }
        System.out.println("===========================");
        System.out.println("          J O I N S");
        System.out.println("============================");
        int intNumJoins = posiblesJoinsTipoDato.size();
        VariablesGlobales.strTipoDtoJoin = posiblesJoinsTipoDato;
        System.out.println("Hay join" + "extencion join " + intNumJoins);
        for (int h = 0; h < intNumJoins; h++) {
            System.out.println(VariablesGlobales.strTipoDtoJoin.get(h));

        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMostrarTablas = new javax.swing.JTable();
        lblTablas = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTabla1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTabla2 = new javax.swing.JTable();
        btnAgregar1 = new javax.swing.JButton();
        cbBases = new javax.swing.JComboBox();
        labelCombo = new javax.swing.JLabel();
        otrolay = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbPresentrarJoinsPost = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblTabla1 = new javax.swing.JLabel();

        jMenuItem1.setText("Agregar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbMostrarTablas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbMostrarTablas.setComponentPopupMenu(jPopupMenu1);
        tbMostrarTablas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMostrarTablasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMostrarTablas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 50, 206, 159));

        lblTablas.setText("Tabla Seleccionada");
        getContentPane().add(lblTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 140, -1, -1));

        btnAgregar.setText("A G R E G A R");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 330, 130, 90));

        tbTabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbTabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTabla1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbTabla1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, 263, 193));

        tbTabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbTabla2);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 280, 259, 193));

        btnAgregar1.setText("A G R E G A R ");
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 330, 140, 90));

        cbBases.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbBases.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                cbBasesComponentShown(evt);
            }
        });
        cbBases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBasesActionPerformed(evt);
            }
        });
        getContentPane().add(cbBases, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 310, 40));

        labelCombo.setText("jLabel6");
        getContentPane().add(labelCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 90, -1, -1));
        getContentPane().add(otrolay, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 188, -1, -1));

        tbPresentrarJoinsPost.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tbPresentrarJoinsPost);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 550, 510, 110));

        jButton1.setText("C A L C U  L A R");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 570, 130, 70));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jButton2.setText("R  E  G  R  E  S  A  R ");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 620, 170, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Fondor1.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1360, 760));

        lblTabla1.setText("jLabel6");
        getContentPane().add(lblTabla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 100, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int fila = tbMostrarTablas.getSelectedRow();
        String id;

        if (fila >= 0) {
            id = tbMostrarTablas.getValueAt(fila, 0).toString();
            lblTablas.setText(id);
            tablas = lblTablas.getText();
            //convertir a entero el id

            // lblTablas.setText(tbMostrarTablas.getValueAt(fila,0).toString());  
        } else {
            JOptionPane.showMessageDialog(null, "NO SELECCIONO FILA");
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        String tabla = lblTablas.getText();
        IngresarTablasPSQL();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tbMostrarTablasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMostrarTablasMouseClicked
        // TODO add your handling code here:
        String url = "C:\\carlos\\base_de_datos\\join\\BaseDatos\\ProyectoFinalBaseDatos1\\src\\img\\fondor3.jpg";
        ImageIcon i = new ImageIcon(url);
        jLabel6.setIcon(i);
        int row = tbMostrarTablas.getSelectedRow();
        String strCadena = tbMostrarTablas.getModel().getValueAt(row, 0).toString();
        lblTablas.setText(strCadena);
        int fila = tbMostrarTablas.getSelectedRow();
        String id;

        if (fila >= 0) {
            id = tbMostrarTablas.getValueAt(fila, 0).toString();
            lblTablas.setText(id);
            tablas = lblTablas.getText();
            //convertir a entero el id

            // lblTablas.setText(tbMostrarTablas.getValueAt(fila,0).toString());  
        } else {
            JOptionPane.showMessageDialog(null, "NO SELECCIONO FILA");
        }
    }//GEN-LAST:event_tbMostrarTablasMouseClicked

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        // TODO add your handling code here:
        String url = "C:\\carlos\\base_de_datos\\join\\BaseDatos\\ProyectoFinalBaseDatos1\\src\\img\\fondor4.jpg";
        ImageIcon i = new ImageIcon(url);
        jLabel6.setIcon(i);
        String tabla = lblTablas.getText();
        IngresarTablasPSQL2();
    }//GEN-LAST:event_btnAgregar1ActionPerformed

    private void tbTabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTabla1MouseClicked
        // TODO add your handling code here:
        String cadena = "";

        int row = tbTabla1.rowAtPoint(evt.getPoint());
        if (row >= 0 && tbTabla1.isEnabled()) {

            for (int j = 0; j < tbTabla1.getColumnCount(); j++) {
                cadena = cadena + " " + tbTabla1.getValueAt(row, j).toString();
            }
        }
        
    }//GEN-LAST:event_tbTabla1MouseClicked

    private void cbBasesComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_cbBasesComponentShown

    }//GEN-LAST:event_cbBasesComponentShown

    private void cbBasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBasesActionPerformed
        String url = "C:\\carlos\\base_de_datos\\join\\BaseDatos\\ProyectoFinalBaseDatos1\\src\\img\\fondor2.jpg";
        ImageIcon i = new ImageIcon(url);
        jLabel6.setIcon(i);
        labelCombo.setText((String) cbBases.getSelectedItem());
        strBases = (String) cbBases.getSelectedItem();
        mostrarDatosPSQL();
    }//GEN-LAST:event_cbBasesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String url = "C:\\carlos\\base_de_datos\\join\\BaseDatos\\ProyectoFinalBaseDatos1\\src\\img\\fondor5.jpg";
        ImageIcon i = new ImageIcon(url);
        if(cbBases.isShowing())
            jLabel6.setIcon(i);
        esJointipoDatoPresentar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        ventanaMenu m = new ventanaMenu();
        m.setVisible(true);
        VariablesGlobales.strNombreColumna1.clear();
        VariablesGlobales.strNombreColumna2.clear();
        VariablesGlobales.strTipoDto1.clear();
         VariablesGlobales.strTipoDto2.clear();
         VariablesGlobales.strTipoDtoJoin.clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFTablasPostgres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFTablasPostgres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFTablasPostgres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFTablasPostgres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFTablasPostgres().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JFTablasPostgres.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregar1;
    public javax.swing.JComboBox cbBases;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelCombo;
    private javax.swing.JLabel lblTabla1;
    private javax.swing.JLabel lblTablas;
    public javax.swing.JLabel otrolay;
    private javax.swing.JTable tbMostrarTablas;
    private javax.swing.JTable tbPresentrarJoinsPost;
    private javax.swing.JTable tbTabla1;
    private javax.swing.JTable tbTabla2;
    // End of variables declaration//GEN-END:variables
}
