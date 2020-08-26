package uber.Logic;

import balcorpfw.database.DatabaseX;
import balcorpfw.logic.Logic;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import uber.objects.CalificacionConductorObj;
import uber.objects.ConductorGananciasObj;
import uber.objects.ConductorObj;
import uber.objects.FacturaObj;
import uber.objects.GananciasDetalleObj;


public class ConductorLogic extends Logic
{

    public ConductorLogic(String pConnectionString) {
        super(pConnectionString);
    }
    
    public int nConductor(String pNombre, String pMarcaCarro, String pCorreo, int pLicencia, int pDUI, int pNumero, String pAntecedentes, String pCuentaBancaria, float pLatitud, float pLongitud) 
    {
        //Query a ejecutar
        String query = "INSERT INTO uber.conductor\n" +
        "(id,nombre,marca_de_carro,correo_electronico,licencia,numero_DUI,numero_telefono,antecedentes_penales,cuenta_bancaria,latitud_actual,longitud_actual)\n" +
        "VALUES\n" +
        "(0,'"+pNombre+"','"+pMarcaCarro+"','"+pCorreo+"','"+pLicencia+"',"+pDUI+","+pNumero+",'"+pAntecedentes+"','"+pCuentaBancaria+"',"+pLatitud+","+pLongitud+");";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows; 
    }
    
    public ArrayList<ConductorObj> allConductores() throws SQLException
    {
        //Query a ejecutar
        String query = "SELECT * FROM uber.conductor;";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<ConductorObj> arrConductor = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                int iId;
                String strNombre;
                String strMarcaCarro;
                String strCorreo;
                int iLicencia;
                int iDUI;
                int iNumero;
                String strAntecedentes;
                String strCuentaBancaria;
                float fLatitud;
                float fLongitud;
                ConductorObj tempConductor;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    iId = result.getInt("id");
                    strNombre = result.getString("nombre");
                    strMarcaCarro = result.getString("marca_de_carro");
                    strCorreo = result.getString("correo_electronico");
                    iLicencia = result.getInt("licencia");
                    iDUI = result.getInt("numero_DUI");
                    iNumero = result.getInt("numero_telefono");
                    strAntecedentes = result.getString("antecedentes_penales");
                    strCuentaBancaria = result.getString("cuenta_bancaria");
                    fLatitud = result.getFloat("latitud_actual");
                    fLongitud = result.getFloat("longitud_actual");
                    
                    tempConductor = new ConductorObj(iId, strNombre, strMarcaCarro, strCorreo, iLicencia, iDUI, iNumero, strAntecedentes, strCuentaBancaria, fLatitud, fLongitud);
                    arrConductor.add(tempConductor);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ConductorLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrConductor;
        
    }
    
    public int dConductor(int pId)
    {
        //Query a ejecutar
        String query = "DELETE FROM uber.conductor where id="+pId+";";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query);
        return rows;
    }
    
    public ConductorObj getConductorById(int pId)
    {
        String query = "SELECT * FROM uber.conductor\n" +
        "WHERE id="+pId+";";
        
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Crear Obj donde se almacenará resultado de SQL 
        ConductorObj tempConductor = null;
        
        if(result!=null)
        {
            try {
                int iId;
                String strNombre;
                String strMarcaCarro;
                String strCorreo;
                int iLicencia;
                int iDUI;
                int iNumero;
                String strAntecedentes;
                String strCuentaBancaria;
                float fLatitud;
                float fLongitud;
                
                while(result.next())
                {
                    //Llamar datos de SQL 
                    iId = result.getInt("id");
                    strNombre = result.getString("nombre");
                    strMarcaCarro = result.getString("marca_de_carro");
                    strCorreo = result.getString("correo_electronico");
                    iLicencia = result.getInt("licencia");
                    iDUI = result.getInt("numero_DUI");
                    iNumero = result.getInt("numero_telefono");
                    strAntecedentes = result.getString("antecedentes_penales");
                    strCuentaBancaria = result.getString("cuenta_bancaria");
                    fLatitud = result.getFloat("latitud_actual");
                    fLongitud = result.getFloat("longitud_actual");

                    tempConductor = new ConductorObj(iId, strNombre, strMarcaCarro, strCorreo, iLicencia, iDUI, iNumero, strAntecedentes, strCuentaBancaria, fLatitud, fLongitud);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConductorLogic.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        return tempConductor;
    }
    
    public int uCliente(int pId, String pNombre, String pMarcaCarro, String pCorreo, int pLicencia, int pDUI, int pNumero, String pAntecedentes, String pCuentaBancaria, float pLatitud, float pLongitud)
    {
        //Query a ejecutar
        String query = "UPDATE uber.conductor SET\n" +
        "nombre = '"+pNombre+"', marca_de_carro = '"+pMarcaCarro+"', correo_electronico = '"+pCorreo+"',\n" +
        "licencia = "+pLicencia+", numero_DUI = "+pDUI+", numero_telefono = "+pNumero+", antecedentes_penales = '"+pAntecedentes+"',cuenta_bancaria = '"+pCuentaBancaria+"', latitud_actual = "+pLatitud+", longitud_actual = "+pLongitud+"\n" +
        "WHERE id = "+pId+";";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows;
    }
    
    
    
    
    
    
    
    
    //aqui comienza la calificacion del conductor
    
    
     public int InsertCalificacionConductor(int idUltimoViajeConductor, int iCalificacion) {
        
         String query = "insert into uber.calificacion_conductor "
                 +  "(id, id_viajes_inicial, calificacion_conductor)\n" 
                 +  "VALUES (0,"+idUltimoViajeConductor+","+iCalificacion+")";
        
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
                Logger.getLogger(ConductorLogic.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        return id;
    }
      public ArrayList<CalificacionConductorObj> allCalificacionesConductores() throws SQLException
    {
        //Query a ejecutar
        String query = "SELECT * FROM uber.calificacionconductor;";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<CalificacionConductorObj> arrCalificacionConductor = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                String strNombre;
                String strLugar;
                Date iDia;
                Time iHoraInicial;
                int iCalificacion;
                CalificacionConductorObj tempCalificacionConductor;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    strNombre = result.getString("conductor_nombre");
                    strLugar = result.getString("nombre_lugar");
                    iDia = result.getDate("hora_inicial");
                    iHoraInicial = result.getTime("hora_inicial");
                    iCalificacion = result.getInt("calificacion_conductor");
                    
                    
                    tempCalificacionConductor = new CalificacionConductorObj(strNombre, strLugar, iDia, iHoraInicial, iCalificacion);
                    arrCalificacionConductor.add(tempCalificacionConductor);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ConductorLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrCalificacionConductor;
        
    }
      
      //aqui empieza ganancias del conductor
       public ArrayList<ConductorGananciasObj> allConductorGanancias() throws SQLException
    {
        //Query a ejecutar
        String query = "SELECT * FROM uber.conductorganancias;";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<ConductorGananciasObj> arrConductorGanancias = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                String cNombre;
                int cNumeroViajes;
                float cTotal;
                ConductorGananciasObj tempConductorGanancias;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    cNombre = result.getString("nombre_conductor");
                    cNumeroViajes = result.getInt("numero_viajes");
                    cTotal = result.getFloat("total");
                    
                    
                    tempConductorGanancias = new ConductorGananciasObj(cNombre, cNumeroViajes, cTotal);
                    arrConductorGanancias.add(tempConductorGanancias);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ConductorLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrConductorGanancias;
        
    }
       //aqui terminan las ganancias del conductor
       
       
       
       //aqui empiezan las ganancias del conductor detalladas
       public ArrayList<GananciasDetalleObj> allGananciasDetalles() throws SQLException
    {
        //Query a ejecutar
        String query = "SELECT * FROM uber.gananciasdetalle;";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<GananciasDetalleObj> arrGananciasDetalle = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                String gNombre;
                String gHora;
                float gTotal;
                GananciasDetalleObj tempGananciasDetalle;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    gNombre = result.getString("nombre_conductor");
                    gHora = result.getString("hora");
                    gTotal = result.getFloat("total");
                    
                    
                    tempGananciasDetalle = new GananciasDetalleObj(gNombre, gHora, gTotal);
                    arrGananciasDetalle.add(tempGananciasDetalle);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ConductorLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrGananciasDetalle;
        
    }
       //aqui terminan las ganancias del conductor detalladas
       
       
     //aqui empieza la factura
       public ArrayList<FacturaObj> allFacturas() throws SQLException
    {
        //Query a ejecutar
        String query = "SELECT * FROM uber.viajes WHERE 1 ORDER BY id DESC LIMIT 1;";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<FacturaObj> arrFactura = new ArrayList<>();
        
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
                FacturaObj tempFactura;
                
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
                    
                    tempFactura = new FacturaObj(iId, strNombreCliente,strNombreConductor,strNombreLugar,iDistancia, iDia, iHora, strTotal,strTarjetaCredito);
                    arrFactura.add(tempFactura);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ConductorLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrFactura;
    }
       
        public ArrayList<GananciasDetalleObj> allGetGananciasDetalle(String pNombre) throws SQLException
        {
        //Query a ejecutar
        
        
        String query = "call uber.GetGananciasDetalle('"+pNombre+"');";
        
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<GananciasDetalleObj> arrGetGananciasDetalle = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                String gNombre;
                String gHora;
                float gTotal;
                GananciasDetalleObj tempGetGananciasDetalle;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    gNombre = result.getString("nombre_conductor");
                    gHora = result.getString("hora");
                    gTotal = result.getFloat("total");
                    
                    
                    tempGetGananciasDetalle = new GananciasDetalleObj(gNombre, gHora, gTotal);
                    arrGetGananciasDetalle.add(tempGetGananciasDetalle);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ClienteLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrGetGananciasDetalle;
    
    }
        
    public ArrayList<ConductorGananciasObj> allGetConductorGanancias(String pNombre) throws SQLException
        {
        //Query a ejecutar
        
        
        String query = "call uber.GetConductorGanancias('"+pNombre+"');";
        
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<ConductorGananciasObj> arrGetConductorGanancias = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                String cNombre;
                int cNumeroViajes;
                float cTotal;
                ConductorGananciasObj tempGetConductorGanancias;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    cNombre = result.getString("nombre_conductor");
                    cNumeroViajes = result.getInt("numero_viajes");
                    cTotal = result.getFloat("total");
                    
                    
                    tempGetConductorGanancias = new ConductorGananciasObj(cNombre, cNumeroViajes, cTotal);
                    arrGetConductorGanancias.add(tempGetConductorGanancias);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ClienteLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrGetConductorGanancias;
    
    }
    
    public ArrayList<CalificacionConductorObj> allGetCalificacionConductor(String pNombre) throws SQLException
        {
        //Query a ejecutar
        
        
        String query = "call uber.GetCalificacionConductor('"+pNombre+"');";
        
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<CalificacionConductorObj> arrGetCalificacionConductor = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                String strNombre;
                String strLugar;
                Date iDia;
                Time iHoraInicial;
                int iCalificacion;
                CalificacionConductorObj tempGetCalificacionConductor;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    strNombre = result.getString("conductor_nombre");
                    strLugar = result.getString("nombre_lugar");
                    iDia = result.getDate("hora_inicial");
                    iHoraInicial = result.getTime("hora_inicial");
                    iCalificacion = result.getInt("calificacion_conductor");
                    
                    
                    tempGetCalificacionConductor = new CalificacionConductorObj(strNombre, strLugar, iDia, iHoraInicial, iCalificacion);
                    arrGetCalificacionConductor.add(tempGetCalificacionConductor);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ConductorLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrGetCalificacionConductor;
    
    }
    
    public ArrayList<ConductorObj> allGetConductor(String pNombre) throws SQLException
    {
        //Query a ejecutar
        
        
        String query = "call uber.GetNombreConductor('"+pNombre+"');";
        
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<ConductorObj> arrGetConductor = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                int iId;
                String strNombre;
                String strMarcaCarro;
                String strCorreo;
                int iLicencia;
                int iDUI;
                int iNumero;
                String strAntecedentes;
                String strCuentaBancaria;
                float fLatitud;
                float fLongitud;
                ConductorObj tempGetConductor;
                
                while(result.next())
                {
                    //Llamar datos de SQL 
                    iId = result.getInt("id");
                    strNombre = result.getString("nombre");
                    strMarcaCarro = result.getString("marca_de_carro");
                    strCorreo = result.getString("correo_electronico");
                    iLicencia = result.getInt("licencia");
                    iDUI = result.getInt("numero_DUI");
                    iNumero = result.getInt("numero_telefono");
                    strAntecedentes = result.getString("antecedentes_penales");
                    strCuentaBancaria = result.getString("cuenta_bancaria");
                    fLatitud = result.getFloat("latitud_actual");
                    fLongitud = result.getFloat("longitud_actual");

                    tempGetConductor = new ConductorObj(iId, strNombre, strMarcaCarro, strCorreo, iLicencia, iDUI, iNumero, strAntecedentes, strCuentaBancaria, fLatitud, fLongitud);
                    arrGetConductor.add(tempGetConductor);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(ConductorLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrGetConductor;
    
    }
}

