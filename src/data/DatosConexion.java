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
 * @author krozz007
 */
public class DatosConexion {
    //creacion del metodo connection
    Connection con ;
    //metodo para abrir la conexion
     public Connection AbrirConexion(String strBase) throws ClassNotFoundException, SQLException{   
            con = conectarMysql(strBase);
    
        return con;
    }
     //metodo para almacener el drive de la conexion con MySql
     public Connection conectarMysql(String strBase) throws ClassNotFoundException, SQLException{
       Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
            con=DriverManager.getConnection("jdbc:mysql://localhost/"+strBase+"?user=root&password=");
            System.out.println("conexion MySql establecida");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return con;

    }
     public ArrayList<String> conectarCualquierMysql() throws ClassNotFoundException, SQLException{
       String a = "";
         ArrayList b;
        b = new ArrayList();
         try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("show databases;");
            while(rs.next()){                
                b.add(a);
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
       return b;
    }
     //metodo de abrir la conexion
    public void AbrirConexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
