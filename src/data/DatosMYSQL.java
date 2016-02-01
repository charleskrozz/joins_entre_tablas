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
 * @author krozz007
 */
public class DatosMYSQL {
      DatosConexion c = new DatosConexion();

       public ResultSet presentarDatosMYSQL(String strBase) throws ClassNotFoundException, ClassNotFoundException, SQLException{
          Statement st = c.AbrirConexion(strBase).createStatement();
       
        String sentencia = "SELECT * FROM "+strBase;
        System.out.println(sentencia);
        ResultSet res = st.executeQuery(sentencia);
        return res;
    }
       public ResultSet presentarTablas(String strBase) throws ClassNotFoundException, SQLException{
           Statement st = c.AbrirConexion(strBase).createStatement();
           String sentencia ="show tables";
           System.out.println(sentencia);
           ResultSet res = st.executeQuery(sentencia);
           System.out.println(res);
           return res;
       }
       public ResultSet ingresarTabla(String strBase, String strTabla) throws ClassNotFoundException, SQLException{
           String tabla = strTabla;
           Statement st=c.AbrirConexion(strBase).createStatement();
           String sentencia="show columns from "+tabla;
            ResultSet res = st.executeQuery(sentencia);
           return res;
       }
       public ResultSet obtenerDatosTablas(String strBase,String strTabla) throws ClassNotFoundException, SQLException{
           Statement st=c.AbrirConexion(strBase).createStatement();
           String sentencia="select * from "+strTabla;
           ResultSet res =st.executeQuery(sentencia);
           return res;
       }
}
