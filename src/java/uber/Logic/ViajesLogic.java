package uber.Logic;

import balcorpfw.database.DatabaseX;
import balcorpfw.logic.Logic;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import uber.objects.ViajesObj;


public class ViajesLogic extends Logic
{

    public ViajesLogic(String pConnectionString) {
        super(pConnectionString);
    }
    //iniciar viajee
    public int InsertIniciarViaje(int iIdCliente, int iIdConductor, int iIdCatalogo,LocalDateTime iDateTime, float iLatitudInicial, float iLongitudInicial, float iLatitudFinal, float iLongitudFinal) 
     {
        
         String query = ""+"insert into uber.viajes_inicial "
                 +  "(id, id_cliente, id_conductor, id_catalogo_lugares_turisticos,hora_inicial, latitud_inicial, longitud_inicial, latitud_final_sugerida, longitud_final_sugerida)\n"
                 +  "VALUES (0,"+iIdCliente+","+iIdConductor+","+iIdCatalogo+",'"+iDateTime+"',"+iLatitudInicial+","+iLongitudInicial+","+iLatitudFinal+","+iLongitudFinal+")";
        
        //Ingresar en la base de datos
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows; 
    }
        public int getUltimoViajeInicial()
    {
        String query = "SELECT id FROM uber.viajes_inicial WHERE 1 ORDER BY id DESC LIMIT 1;";
        
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        int idViaje = 0;
        
        if(result!=null)
        {
            try {
                
                while(result.next())
                {
                    //Llamar datos de SQL 
                    idViaje = result.getInt("id");

                }
            } catch (SQLException ex) {
                Logger.getLogger(ViajesLogic.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        return idViaje;
    }
    //aqui termina iniciar viaje
        
    
        
        
        
    //aqui comienza finalizar viaje
        public int InsertFinalizarViaje(int idIniciarViaje,LocalDateTime iDateTime, float iLatitudFinal, float iLongitudFinal, float iDistancia) {
        
         String query = "insert into uber.viajes_final "
                 +  "(id, id_viajes_inicial,hora_final,latitud_final, longitud_final, distancia)\n"
                 +  "VALUES (0,"+idIniciarViaje+",'"+iDateTime+"',"+iLatitudFinal+","+iLongitudFinal+","+iDistancia+")";
        
        //Ingresar en la base de datos
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows; 
}
     
     public int getUltimoViajeFinal()
    {
        String query = "SELECT id FROM uber.viajes_final WHERE 1 ORDER BY id DESC LIMIT 1;";
        
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        int idViaje = 0;
        
        if(result!=null)
        {
            try {
                
                while(result.next())
                {
                    //Llamar datos de SQL 
                    idViaje = result.getInt("id");

                }
            } catch (SQLException ex) {
                Logger.getLogger(ViajesLogic.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        return idViaje;
    }
    //aqui termina finalizar viaje
    
    
    
    /*
    public int nCliente(String pNombre, int pNumero, String pCorreo, String pTarjetaCredito, float pLatitud, float pLongitud) 
    {
        //Query a ejecutar
        String query = "INSERT INTO uber.cliente\n" +
        "(id,nombre,numero_telefono,correo_electronico,tarjeta_credito,latitud_actual,longitud_actual)\n" +
        "VALUES\n" +
        "(0,'"+pNombre+"',"+pNumero+",'"+pCorreo+"','"+pTarjetaCredito+"',"+pLatitud+","+pLongitud+");";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows; 
    }
    */
    public ArrayList<ViajesObj> allViajes() throws SQLException
    {
        //Query a ejecutar
        String query = "SELECT * FROM uber.viajes;";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<ViajesObj> arrViajes = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                int iId;
                String strNombreCliente;
                String strNombreConductor;
                String strNombreLugar;
                int iDistancia;
                Date iDia;
                Time iHora;
                String strTotal;
                String strTarjetaCredito;
                ViajesObj tempViajes;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    iId = result.getInt("id");
                    strNombreCliente = result.getString("nombre_pasajero");
                    strNombreConductor = result.getString("nombre_conductor");
                    strNombreLugar = result.getString("nombre_lugar");
                    iDistancia = result.getInt("distancia");
                    iDia = result.getDate("hora");
                    iHora = result.getTime("hora");
                    strTotal = result.getString("total");
                    strTarjetaCredito = result.getString("tarjeta_credito");
                    
                    tempViajes = new ViajesObj(iId, strNombreCliente,strNombreConductor,strNombreLugar,iDistancia, iDia, iHora, strTotal,strTarjetaCredito);
                    arrViajes.add(tempViajes);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ViajesLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrViajes;
        
    }
    
    public ArrayList<ViajesObj> allGetViajeCliente(String pNombre) throws SQLException
    {
        //Query a ejecutar
        
        
        String query = "call uber.GetViajeCliente('"+pNombre+"');";
        
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<ViajesObj> arrGetViajeCiente = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                int iId;
                String strNombreCliente;
                String strNombreConductor;
                String strNombreLugar;
                int iDistancia;
                Date iDia;
                Time iHora;
                String strTotal;
                String strTarjetaCredito;
                ViajesObj tempGetViajeCliente;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    iId = result.getInt("id");
                    strNombreCliente = result.getString("nombre_pasajero");
                    strNombreConductor = result.getString("nombre_conductor");
                    strNombreLugar = result.getString("nombre_lugar");
                    iDistancia = result.getInt("distancia");
                    iDia = result.getDate("hora");
                    iHora = result.getTime("hora");
                    strTotal = result.getString("total");
                    strTarjetaCredito = result.getString("tarjeta_credito");
                    
                    tempGetViajeCliente = new ViajesObj(iId, strNombreCliente,strNombreConductor,strNombreLugar,iDistancia, iDia, iHora, strTotal,strTarjetaCredito);
                    arrGetViajeCiente.add(tempGetViajeCliente);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ViajesLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrGetViajeCiente;
    
    }
    
    public ArrayList<ViajesObj> allGetViajeConductor(String sNombre) throws SQLException
    {
        //Query a ejecutar
        
        
        String query = "call uber.GetViajeConductor('"+sNombre+"');";
        
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<ViajesObj> arrGetViajeConductor = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                int iId;
                String strNombreCliente;
                String strNombreConductor;
                String strNombreLugar;
                int iDistancia;
                Date iDia;
                Time iHora;
                String strTotal;
                String strTarjetaCredito;
                ViajesObj tempGetViajeConductor;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    iId = result.getInt("id");
                    strNombreCliente = result.getString("nombre_pasajero");
                    strNombreConductor = result.getString("nombre_conductor");
                    strNombreLugar = result.getString("nombre_lugar");
                    iDistancia = result.getInt("distancia");
                    iDia = result.getDate("hora");
                    iHora = result.getTime("hora");
                    strTotal = result.getString("total");
                    strTarjetaCredito = result.getString("tarjeta_credito");
                    
                    tempGetViajeConductor = new ViajesObj(iId, strNombreCliente,strNombreConductor,strNombreLugar,iDistancia, iDia, iHora, strTotal,strTarjetaCredito);
                    arrGetViajeConductor.add(tempGetViajeConductor);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ViajesLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrGetViajeConductor;
    
    }
    
   
}
