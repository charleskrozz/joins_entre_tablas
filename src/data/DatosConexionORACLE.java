/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HolgerRafael
 */
public class DatosConexionORACLE {

    Connection con;
    //Abrir conexion oracle
    public Connection AbrirConexion() throws ClassNotFoundException, SQLException {
        con = conectarMysql();

        return con;
    }

    public Connection conectarMysql() throws ClassNotFoundException, SQLException {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr", "hr");
            System.out.println("conexion ORACLE establecida");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return con;

    }

    public void AbrirConexion1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
