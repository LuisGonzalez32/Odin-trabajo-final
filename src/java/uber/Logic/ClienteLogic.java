package uber.Logic;

import balcorpfw.database.DatabaseX;
import balcorpfw.logic.Logic;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import uber.objects.CalificacionClienteObj;
import uber.objects.ClienteObj;


public class ClienteLogic extends Logic
{

    public ClienteLogic(String pConnectionString) {
        super(pConnectionString);
    }
    
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
    
    public ArrayList<ClienteObj> allClientes() throws SQLException
    {
        //Query a ejecutar
        String query = "SELECT * FROM uber.cliente ;";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<ClienteObj> arrCliente = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                int iId;
                String strNombre;
                int iNumero;
                String strCorreo;
                String strTarjetaCredito;
                float fLatitud;
                float fLongitud;
                ClienteObj tempCliente;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    iId = result.getInt("id");
                    strNombre = result.getString("nombre");
                    iNumero = result.getInt("numero_telefono");
                    strCorreo = result.getString("correo_electronico");
                    strTarjetaCredito = result.getString("tarjeta_credito");
                    fLatitud = result.getFloat("latitud_actual");
                    fLongitud = result.getFloat("longitud_actual");
                    
                    tempCliente = new ClienteObj(iId, strNombre, iNumero, strCorreo, strTarjetaCredito, fLatitud, fLongitud);
                    arrCliente.add(tempCliente);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ClienteLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrCliente;
        
    }
    
    public int dCliente(int pId)
    {
        //Query a ejecutar
        String query = "DELETE FROM uber.cliente where id="+pId+";";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows; 
    }
    
    public ClienteObj getClienteById(int pId)
    {
        String query = "SELECT * FROM uber.cliente\n" +
        "WHERE id="+pId+";";
        
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Crear Obj donde se almacenará resultado de SQL 
        ClienteObj tempCliente = null;
        
        if(result!=null)
        {
            try {
                int iId;
                String strNombre;
                int iTelefono;
                String strCorreo;
                String strTarjetaCredito;
                float fLatitud;
                float fLongitud;
                
                while(result.next())
                {
                    //Llamar datos de SQL 
                    iId = result.getInt("id");
                    strNombre = result.getString("nombre");
                    iTelefono = result.getInt("numero_telefono");
                    strCorreo = result.getString("correo_electronico");
                    strTarjetaCredito = result.getString("tarjeta_credito");
                    fLatitud = result.getFloat("latitud_actual");
                    fLongitud = result.getFloat("longitud_actual");

                    tempCliente = new ClienteObj(iId, strNombre, iTelefono, strCorreo, strTarjetaCredito, fLatitud, fLongitud);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClienteLogic.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        return tempCliente;
    }
    
    public int uCliente(int pId, String pNombre, int pNumero, String pCorreo, String pTarjetaCredito, float pLatitud, float pLongitud)
    {
        //Query a ejecutar
        String query = "UPDATE uber.cliente SET\n" +
        "nombre = '"+pNombre+"', numero_telefono = "+pNumero+", correo_electronico = '"+pCorreo+"',\n" +
        "contraseña = '"+pTarjetaCredito+"', latitud_actual = "+pLatitud+", longitud_actual = "+pLongitud+"\n" +
        "WHERE id = "+pId+";";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows;
    }
    
    
    
    
    
    //aqui comienza la calificacion del cliente
    
    public int InsertCalificacionConductor(int idUltimoViajeCliente, int iCalificacion) {
        
         String query = "insert into uber.calificacion_cliente "
                 +  "(id, id_viajes_inicial, calificacion_cliente)\n" 
                 +  "VALUES (0,"+idUltimoViajeCliente+","+iCalificacion+")";
        
        //Ingresar en la base de datos
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows; 
    }
     
     
     public ArrayList<CalificacionClienteObj> allCalificacionesClientes() throws SQLException
    {
        //Query a ejecutar
        String query = "SELECT * FROM uber.calificacioncliente;";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<CalificacionClienteObj> arrCalificacionCliente = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                String strNombre;
                String strLugar;
                Date iDia;
                Time iHoraInicial;
                int iCalificacion;
                CalificacionClienteObj tempCalificacionCliente;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    strNombre = result.getString("cliente_nombre");
                    strLugar = result.getString("nombre_lugar");
                    iDia = result.getDate("hora_inicial");
                    iHoraInicial = result.getTime("hora_inicial");
                    iCalificacion = result.getInt("calificacion_cliente");
                    
                    
                    tempCalificacionCliente = new CalificacionClienteObj(strNombre, strLugar, iDia, iHoraInicial, iCalificacion);
                    arrCalificacionCliente.add(tempCalificacionCliente);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ClienteLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrCalificacionCliente;
        
    }
     
    public ArrayList<ClienteObj> allGetCliente(String pNombre) throws SQLException
    {
        //Query a ejecutar
        
        
        String query = "call uber.GetNombreCliente('"+pNombre+"');";
        
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<ClienteObj> arrGetCliente = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                 int iId;
                String strNombre;
                int iNumero;
                String strCorreo;
                String strTarjetaCredito;
                float fLatitud;
                float fLongitud;
                ClienteObj tempGetCliente;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                     iId = result.getInt("id");
                    strNombre = result.getString("nombre");
                    iNumero = result.getInt("numero_telefono");
                    strCorreo = result.getString("correo_electronico");
                    strTarjetaCredito = result.getString("tarjeta_credito");
                    fLatitud = result.getFloat("latitud_actual");
                    fLongitud = result.getFloat("longitud_actual");
                    
                    
                    tempGetCliente = new ClienteObj(iId, strNombre, iNumero, strCorreo, strTarjetaCredito, fLatitud, fLongitud);
                    arrGetCliente.add(tempGetCliente);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ClienteLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrGetCliente;
        }
    
    public ArrayList<CalificacionClienteObj> allGetCalificacionCliente(String pNombre) throws SQLException
    {
        //Query a ejecutar
        
        
        String query = "call uber.GetCalificacionCliente('"+pNombre+"');";
        
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<CalificacionClienteObj> arrGetCalificacionCliente = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                String strNombre;
                String strLugar;
                Date iDia;
                Time iHoraInicial;
                int iCalificacion;
                CalificacionClienteObj tempGetCalificacionCliente;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    strNombre = result.getString("cliente_nombre");
                    strLugar = result.getString("nombre_lugar");
                    iDia = result.getDate("hora_inicial");
                    iHoraInicial = result.getTime("hora_inicial");
                    iCalificacion = result.getInt("calificacion_cliente");
                    
                    
                    tempGetCalificacionCliente = new CalificacionClienteObj(strNombre, strLugar, iDia, iHoraInicial, iCalificacion);
                    arrGetCalificacionCliente.add(tempGetCalificacionCliente);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ClienteLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrGetCalificacionCliente;
    
    }
    }

