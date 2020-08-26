
package uber.objects;


public final class ConductorGananciasObj {
    
    private String nombre;
    private int numeroViajes;
    private float ganancias;
    
    
    public ConductorGananciasObj(String pNombre, int pNumeroViajes, float pGanancias){
        
        setNombre(pNombre);
        setNumeroViajes(pNumeroViajes);
        setGanancias(pGanancias);
    }

    public ConductorGananciasObj(String gNombre, String gHora, float gTotal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    public int getNumeroViajes() {
        return numeroViajes;
    }

    public void setNumeroViajes(int pNumeroViajes) {
        this.numeroViajes = pNumeroViajes;
    }

    public float getGanancias() {
        return ganancias;
    }

    public void setGanancias(float pGanancias) {
        this.ganancias = pGanancias;
    }
    
    
}
