package uber.objects;


public final class ClienteObj {
    
    private int id;
    private String nombre;
    private int numero;
    private String correo;
    private String tarjetaCredito;
    private float latitud;
    private float longitud;
    
    
    //este es el constructor
    public ClienteObj (int pId, String pNombre, int pNumero, String pCorreo, String pTarjetaCredito, float pLatitud, float pLongitud)
    {
        setId(pId);
        setNombre(pNombre);
        setNumero(pNumero);
        setCorreo(pCorreo);
        setTarjetaCredito(pTarjetaCredito);
        setLatitud(pLatitud);
        setLongitud(pLongitud);
    }

     public int getId() {
        return id;
    }

    private void setId(int pId) {
        this.id = pId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int pNumero) {
        this.numero = pNumero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String pCorreo) {
        this.correo = pCorreo;
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String pTarjetaCredito) {
        this.tarjetaCredito = pTarjetaCredito;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float pLatitud) {
        this.latitud = pLatitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float pLongitud) {
        this.longitud = pLongitud;
    }
    
}
