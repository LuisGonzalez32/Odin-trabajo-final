
package uber.Logic;

import balcorpfw.database.DatabaseX;
import balcorpfw.logic.Logic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class pagoTarjetaLogic extends Logic {

    public pagoTarjetaLogic(String pConnectionString) {
        super(pConnectionString);
    }
    
    public int InsertPagoTarjeta(int iIdViajes, float iTotal) {
        
         String query = "insert into uber.pago_tarjeta "
                 +  "(id, id_viajes_final, total)"
                 +  "VALUES (0, "+iIdViajes+","+iTotal+")";
        
        //Ingresar en la base de datos
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows; 
}
    public int getUltimoViaje()
    {
        String query = "SELECT id FROM uber.viajes_inicial WHERE 1 ORDER BY id DESC LIMIT 1;";
        
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        int id = 0;
        
        if(result!=null)
        {
            try {
                
                while(result.next())
                {
                    //Llamar datos de SQL 
                    id = result.getInt("id");

                }
            } catch (SQLException ex) {
                Logger.getLogger(pagoTarjetaLogic.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        return id;
    }
    
    
}
