
package uber.objects;


public final class CatalogoObj {
    
    private int id;
    private String nombre;
    private String categoria;
    private String latitud;
    private String longitud;
    
    public CatalogoObj(int pId, String pNombre, String pCategoria, String pLatitud, String pLongitud){
        
        setId(pId);
        setNombre(pNombre);
        setCategoria(pCategoria);
        setLatitud(pLatitud);
        setLongitud(pLongitud);
    }

    public int getId() {
        return id;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String pCategoria) {
        this.categoria = pCategoria;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String pLatitud) {
        this.latitud = pLatitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String pLongitud) {
        this.longitud = pLongitud;
    }
    
    
    
}
