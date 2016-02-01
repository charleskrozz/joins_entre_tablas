/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author HolgerRafael
 */
public class DatosORACLE {
    DatosConexionORACLE c = new DatosConexionORACLE();

       public ResultSet presentarDatosORACLE(String strBase) throws ClassNotFoundException, ClassNotFoundException, SQLException{
          Statement st = c.AbrirConexion().createStatement();
       
        String sentencia = "SELECT * FROM ";
        System.out.println(sentencia);
        ResultSet res = st.executeQuery(sentencia);
        return res;
    }
       public ResultSet presentarTablas(String strBase) throws ClassNotFoundException, SQLException{
           Statement st = c.AbrirConexion().createStatement();
           String sentencia ="SELECT table_name FROM user_tables";
           System.out.println(sentencia);
           ResultSet res = st.executeQuery(sentencia);
           System.out.println(res);
           return res;
       }
       public ResultSet ingresarTabla(String strBase,String strTabla) throws ClassNotFoundException, SQLException{
           String tabla = strTabla;
           Statement st=c.AbrirConexion().createStatement();
           String sentencia="select COLUMN_NAME, DATA_TYPE from USER_TAB_COLUMNS where TABLE_NAME='"+tabla+"'";
            ResultSet res = st.executeQuery(sentencia);
           return res;
       }
}
