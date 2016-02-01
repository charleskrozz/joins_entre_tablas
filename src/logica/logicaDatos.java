/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import data.DatosMYSQL;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author krozz007
 */
public class logicaDatos {
    DatosMYSQL objDatosMYSQL=new DatosMYSQL();
    
     public ResultSet presetarTablas(String strBase) throws ClassNotFoundException, SQLException{
        
        ResultSet res=objDatosMYSQL.presentarTablas(strBase);
        
        return res;
    }
     
     public ResultSet presentarDatosEstudiantes(String strBase) throws ClassNotFoundException, SQLException {

        ResultSet res = objDatosMYSQL.presentarDatosMYSQL(strBase);
        return res;
    }
     public ResultSet ingresarTablas(String strTablas, String strBase) throws ClassNotFoundException, SQLException{
        
         ResultSet res =objDatosMYSQL.ingresarTabla(strTablas, strBase);
         return res;
     }
     public ResultSet presentarDatosTablas(String strBase, String strTabla) throws ClassNotFoundException, SQLException{
         ResultSet res = objDatosMYSQL.obtenerDatosTablas(strBase,strTabla);
         return res;
        
    }
}
