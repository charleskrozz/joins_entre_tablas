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
 * @author salas
 */
public class DatosConexionPSQL {

    Connection con;
    //abrir conexion de Postgres
    public Connection AbrirConexion(String strBase) throws ClassNotFoundException, SQLException {
        con = conectarMysql(strBase);

        return con;
    }
    //metodo para conectarse a Postgres
    public Connection conectarMysql(String strBase) throws ClassNotFoundException, SQLException {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + strBase, "postgres", "admin");
            System.out.println("conexion PSql establecida");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return con;

    }

    public void AbrirConexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
