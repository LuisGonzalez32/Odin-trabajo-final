package uber.objects;


public final class ConductorObj {
    
    private int id;
    private String nombre;
    private String marcaCarro;
    private String correo;
    private int licencia;
    private int DUI;
    private int numero;
    private String antecedentes;
    private String cuentaBancaria;
    private float latitud;
    private float longitud;
    
    
    //este es el constructor
    public ConductorObj (int pId, String pNombre, String pMarcaCarro, String pCorreo, int pLicencia, int pDUI, int pNumero, String pAntecedentes, String pCuentaBancaria, float pLatitud, float pLongitud)
    {
       setId(pId);
       setNombre(pNombre);
       setMarcaCarro(pMarcaCarro);
       setCorreo(pCorreo);
       setLicencia(pLicencia);
       setDUI(pDUI);
       setNumero(pNumero);
       setAntecedentes(pAntecedentes);
       setCuentaBancaria(pCuentaBancaria);
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

    public String getMarcaCarro() {
        return marcaCarro;
    }

    public void setMarcaCarro(String pMarcaCarro) {
        this.marcaCarro = pMarcaCarro;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String pCorreo) {
        this.correo = pCorreo;
    }

    public int getLicencia() {
        return licencia;
    }

    public void setLicencia(int pLicencia) {
        this.licencia = pLicencia;
    }

    public int getDUI() {
        return DUI;
    }

    public void setDUI(int pDUI) {
        this.DUI = pDUI;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int pNumero) {
        this.numero = pNumero;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String pAntecedentes) {
        this.antecedentes = pAntecedentes;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String pCuentaBancaria) {
        this.cuentaBancaria = pCuentaBancaria;
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