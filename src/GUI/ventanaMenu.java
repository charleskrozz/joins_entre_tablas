/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ventanaMenu.java
 *
 * Created on 31-may-2015, 9:50:45
 */
package GUI;

import Otros.VariablesGlobales;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit;
import org.edisoncor.gui.util.Avatar;
import turorial.panel.avatar.ventanaMenu1;

/**
 *
 * @author Israel-ICM
 */
public class ventanaMenu extends javax.swing.JFrame {

    /**
     * Creates new form ventanaMenu
     */
    String strNombreBdd;
    public String strPuerto;
    public String strUsuario;
    public String strContrasenia;

    public ventanaMenu() {
        initComponents();
        llenarMenu();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    public void llenarMenu() {
        List<Avatar> avatars = new ArrayList<Avatar>();

        avatars.add(new Avatar("ORACLE", loadImage("/img/oracle.png")));
        avatars.add(new Avatar("POSTGRESQL", loadImage("/img/postgresql.png")));
        avatars.add(new Avatar("XAMPP", loadImage("/img/comunicate.png")));
        avatars.add(new Avatar("WAMP", loadImage("/img/wamp.png")));
        avatars.add(new Avatar("SQL SERVER", loadImage("/img/sqlserver.png")));
        avatars.add(new Avatar("Salir", loadImage("/img/Salir.png")));

        menu.setAvatars(avatars);
    }
    /*
          
     }*/

    public static Image loadImage(String fileName) {
        try {
            return ImageIO.read(ventanaMenu.class.getResource(fileName));
        } catch (Exception e) {
            return null;
        }
    }

    public void llamarMenu() {
        if (menu.getSelectedtitulo().equals("XAMPP")) {
            VariablesGlobales.strUsuaario = JOptionPane.showInputDialog(null, "Usuario:");
            VariablesGlobales.strClave = JOptionPane.showInputDialog(null, "Clave:");

            try {
                JFTablasMysql obj = new JFTablasMysql();
                obj.cbBases.removeAllItems();
                try {
                    Connection con = null;
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306", VariablesGlobales.strUsuaario, VariablesGlobales.strClave);
                    if (con != null) {
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("show databases;");
                        while (rs.next()) {
                            obj.cbBases.addItem(rs.getString(1));
                        }
                        obj.setVisible(true);
                        this.setVisible(false);
                    }else {
                        System.out.println("Conexion fallida!");
                        JOptionPane.showMessageDialog(rootPane, "Datos Inválidos");                        
                    }
                    
                } catch (ClassNotFoundException e) {
                    System.out.println(e.toString());
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ventanaMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (menu.getSelectedtitulo().equals("WAMP")) {
            try {
                JFTablasMysql obj = new JFTablasMysql();
                obj.cbBases.removeAllItems();

                try {
                    Connection con = null;
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("show databases;");
                    while (rs.next()) {
                        obj.cbBases.addItem(rs.getString(1));
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println(e.toString());
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
                obj.setVisible(true);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ventanaMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (menu.getSelectedtitulo().equals("POSTGRESQL")) {
            VariablesGlobales.strUsuaario = JOptionPane.showInputDialog(null, "Usuario:");
            VariablesGlobales.strClave = JOptionPane.showInputDialog(null, "Clave:");
            String user = "postgres";
            String login = "admin";
            if (VariablesGlobales.strUsuaario.equals(user) & VariablesGlobales.strClave.equals(login)) {

                try {
                    JFTablasPostgres obj = new JFTablasPostgres();
                    obj.cbBases.removeAllItems();
                    try {
                        Connection con = null;
                        Class.forName("org.postgresql.Driver");
                        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "admin");
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM pg_catalog.pg_database;");
                        while (rs.next()) {
                            obj.cbBases.addItem(rs.getString(1));
                        }
                    } catch (ClassNotFoundException e) {
                        System.out.println(e.toString());
                    } catch (SQLException e) {
                        System.out.println(e.toString());
                    }
                    obj.setVisible(true);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ventanaMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Datos Inválidos");
            }

        }
        if (menu.getSelectedtitulo().equals("ORACLE")) {
            VariablesGlobales.strUsuaario = JOptionPane.showInputDialog(null, "Usuario:");
            VariablesGlobales.strClave = JOptionPane.showInputDialog(null, "Clave:");

            try {
                JFTablasOracle obj = new JFTablasOracle();
                obj.cbBases.removeAllItems();
                try {
                    Connection con = null;
                    Class.forName("oracle.jdbc.OracleDriver");
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", VariablesGlobales.strUsuaario, VariablesGlobales.strClave);
                    if (con != null) {
                        System.out.println("Conexion exitosa!");
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT table_name  FROM user_tables");
                        obj.setVisible(true);
                        while (rs.next()) {
                            obj.cbBases.addItem(rs.getString(1));
                        }
                    } else {
                        System.out.println("Conexion fallida!");
                        JOptionPane.showMessageDialog(rootPane, "Datos Inválidos");
                    }

                } catch (ClassNotFoundException e) {
                    System.out.println(e.toString());
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ventanaMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (menu.getSelectedtitulo().equals("Salir")) {
            System.exit(0);
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

        menu = new org.edisoncor.gui.panel.PanelAvatarChooser();
        buttonIpod1 = new org.edisoncor.gui.button.ButtonIpod();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        menu.setBackground(new java.awt.Color(0, 0, 0));
        menu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menu.setToolTipText("none");
        menu.setColorPrimario(new java.awt.Color(0, 0, 0));
        menu.setFont(new java.awt.Font("Stencil", 0, 48)); // NOI18N
        menu.setNextFocusableComponent(menu);
        menu.setTitulos(null);
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMouseClicked(evt);
            }
        });
        menu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menuKeyPressed(evt);
            }
        });

        buttonIpod1.setBackground(new java.awt.Color(0, 0, 0));
        buttonIpod1.setText(".");
        buttonIpod1.setFont(new java.awt.Font("Arial", 0, 3)); // NOI18N
        buttonIpod1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIpod1ActionPerformed(evt);
            }
        });
        buttonIpod1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonIpod1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addComponent(buttonIpod1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(buttonIpod1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menuKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            llamarMenu();
        }
    }//GEN-LAST:event_menuKeyPressed

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_menuMouseClicked

    private void buttonIpod1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonIpod1KeyPressed

    }//GEN-LAST:event_buttonIpod1KeyPressed

    private void buttonIpod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIpod1ActionPerformed
        llamarMenu();
        this.setVisible(false);
    }//GEN-LAST:event_buttonIpod1ActionPerformed

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
            java.util.logging.Logger.getLogger(ventanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ventanaMenu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonIpod buttonIpod1;
    public org.edisoncor.gui.panel.PanelAvatarChooser menu;
    // End of variables declaration//GEN-END:variables
}
