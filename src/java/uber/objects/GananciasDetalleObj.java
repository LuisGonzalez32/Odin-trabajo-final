
package uber.objects;



   
public final class GananciasDetalleObj {
    
    private String nombre;
    private String hora;
    private float total;
    
    
    public GananciasDetalleObj(String pNombre, String pHora, float pTotal){
        setNombre(pNombre);
        setHora(pHora);
        setTotal(pTotal);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String pHora) {
        this.hora = pHora;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float pHotal) {
        this.total = pHotal;
    }
    
}
