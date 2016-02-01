/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmobasedatos;

import data.DatosConexion;
import java.sql.SQLException;

/**
 *
 * @author krozz007
 */
public class AlgoritmoBaseDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
            DatosConexion objData = new DatosConexion();
            objData.AbrirConexion();
         }
    
}
