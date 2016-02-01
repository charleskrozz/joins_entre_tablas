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
public class DatosPSQL {
     DatosConexionPSQL c = new DatosConexionPSQL();

       public ResultSet presentarDatosPSQL(String strBase) throws ClassNotFoundException, ClassNotFoundException, SQLException{
          Statement st = c.AbrirConexion(strBase).createStatement();
       
        String sentencia = "SELECT * FROM "+strBase;
        System.out.println(sentencia);
        ResultSet res = st.executeQuery(sentencia);
        return res;
    }
       public ResultSet presentarTablasPSQL(String strBase) throws ClassNotFoundException, SQLException{
           Statement st = c.AbrirConexion(strBase).createStatement();
           String sentencia ="SELECT tablename FROM pg_catalog.pg_tables where schemaname ='public';";
           System.out.println(sentencia);
           ResultSet res = st.executeQuery(sentencia);
           System.out.println(res);
           return res;
       }
       public ResultSet ingresarTablaPSQL(String strBase, String strTabla) throws ClassNotFoundException, SQLException{
           String tabla = strTabla;
           Statement st=c.AbrirConexion(strBase).createStatement();
           String sentencia="SELECT\n" +
                           "a.attname as \"Column\",\n" +
                           "pg_catalog.format_type(a.atttypid, a.atttypmod) as \"Datatype\"\n" +
                           "FROM\n" +
                           "pg_catalog.pg_attribute a\n" +
                           "WHERE\n" +
                           "a.attnum > 0\n" +
                           "AND NOT a.attisdropped\n" +
                           "AND a.attrelid = (\n" +
                           "SELECT c.oid\n" +
                           "FROM pg_catalog.pg_class c\n" +
                           "LEFT JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace\n" +
                           "WHERE c.relname ~ '^("+tabla+")$'\n" +
                           "AND pg_catalog.pg_table_is_visible(c.oid)\n" +
                           ")\n" +
                           ";";
            ResultSet res = st.executeQuery(sentencia);
           return res;
       }
}
