package uber.Logic;

import balcorpfw.database.DatabaseX;
import balcorpfw.logic.Logic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import uber.objects.CatalogoObj;
import uber.objects.ClienteObj;


public class CatalogoLogic extends Logic
{

    public CatalogoLogic(String pConnectionString) {
        super(pConnectionString);
    }
    
    public int nCatalogo(String pNombre, String pCategoria, float pLatitud, float pLongitud) 
    {
        //Query a ejecutar
        String query = "INSERT INTO uber.catalogo_lugares_turisticos\n" +
        "(id,nombre,categoria,latitud,longitud)\n" +
        "VALUES\n" +
        "(0,'"+pNombre+"','"+pCategoria+"',"+pLatitud+","+pLongitud+");";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows; 
    }
    
    public ArrayList<CatalogoObj> allCatalogos() throws SQLException
    {
        //Query a ejecutar
        String query = "SELECT * FROM uber.catalogo_lugares_turisticos;";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<CatalogoObj> arrCatalogo = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                int iId;
                String strNombre;
                String strCategoria;
                String strLatitud;
                String strLongitud;
                CatalogoObj tempCatalogo;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    iId = result.getInt("id");
                    strNombre = result.getString("nombre");
                    strCategoria = result.getString("categoria");
                    strLatitud = result.getString("latitud");
                    strLongitud = result.getString("longitud");
                    
                    tempCatalogo = new CatalogoObj(iId, strNombre, strCategoria, strLatitud, strLongitud);
                    arrCatalogo.add(tempCatalogo);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(CatalogoLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrCatalogo;
        
    }
    
    public int dCatalogo(int pId)
    {
        //Query a ejecutar
        String query = "DELETE FROM uber.catalogo_lugares_turisticos where id="+pId+";";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows; 
    }
    
    public CatalogoObj getCatalogoById(int pId)
    {
        String query = "SELECT * FROM uber.catalogo_lugares_turisticos\n" +
        "WHERE id="+pId+";";
        
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Crear Obj donde se almacenará resultado de SQL 
        CatalogoObj tempCatalogo = null;
        
        if(result!=null)
        {
            try {
                int iId;
                String strNombre;
                String strCategoria;
                String strLatitud;
                String strLongitud;
                
                while(result.next())
                {
                    //Llamar datos de SQL 
                    iId = result.getInt("id");
                    strNombre = result.getString("nombre");
                    strCategoria = result.getString("categoria");
                    strLatitud = result.getString("latitud");
                    strLongitud = result.getString("longitud");

                    tempCatalogo = new CatalogoObj(iId, strNombre, strCategoria, strLatitud, strLongitud);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CatalogoLogic.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        return tempCatalogo;
    }
    
    public int uCatalogo(int pId, String pNombre, String pCategoria, float pLatitud, float pLongitud)
    {
        //Query a ejecutar
        String query = "UPDATE uber.catalogo_lugares_turisticos SET\n" +
        "nombre = '"+pNombre+"', categoria = '"+pCategoria+"', latitud = "+pLatitud+", longitud = "+pLongitud+"\n" +
        "WHERE id = "+pId+";";
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        int rows = db.executeNonQueryRows(query); 
        return rows;
    }
    
    public ArrayList<CatalogoObj> allGetCatalogo(String pNombre) throws SQLException
    {
        //Query a ejecutar
        
        
        String query = "call uber.GetCatalogo('"+pNombre+"');";
        
        
        //Ejecutar Query
        DatabaseX db = getDatabase();
        ResultSet result = db.executeQuery(query);
        
        //Arraylist para almacenar información de SQL
        ArrayList<CatalogoObj> arrGetCatalogo = new ArrayList<>();
        
        if(result!=null)
        {
                try {
                int iId;
                String strNombre;
                String strCategoria;
                String strLatitud;
                String strLongitud;
                CatalogoObj tempGetCatalogo;
                
                while (result.next()) {
                    //Llamar datos de SQL, las comillas son como lo llamamos en sql
                    iId = result.getInt("id");
                    strNombre = result.getString("nombre");
                    strCategoria = result.getString("categoria");
                    strLatitud = result.getString("latitud");
                    strLongitud = result.getString("longitud");
                    
                    
                    tempGetCatalogo = new CatalogoObj(iId, strNombre, strCategoria, strLatitud, strLongitud);
                    arrGetCatalogo.add(tempGetCatalogo);
                    
                }
            } catch (SQLException ex) {
               Logger.getLogger(CatalogoLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        return arrGetCatalogo;
    
    }
}
