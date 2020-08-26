package uber.objects;

import java.sql.Time;
import java.sql.Date;

public final class FacturaObj {
    
    private int id;
    private String nombreCliente;
    private String nombreConductor;
    private String nombreLugar;
    private int distancia;
    private Date dia;
    private Time hora;
    private String total;
    private String tarjetaCredito;
    
    
    //este es el constructor
    public FacturaObj(int pId, String pNombreCliente, String pNombreConductor, String pNombreLugar, int pDistancia, Date pDia, Time pHora, String pTotal, String pTarjetaCredito)
    {
       setId(pId);
       setNombreCliente(pNombreCliente);
       setNombreConductor(pNombreConductor);
       setNombreLugar(pNombreLugar);
       setDistancia(pDistancia);
       setDia(pDia);
       setHora(pHora);
       setTotal(pTotal);
       setTarjetaCredito(pTarjetaCredito);
    }

    public int getId() {
        return id;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String pNombreCliente) {
        this.nombreCliente = pNombreCliente;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String pNombreConductor) {
        this.nombreConductor = pNombreConductor;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String pNombreLugar) {
        this.nombreLugar = pNombreLugar;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int pDistancia) {
        this.distancia = pDistancia;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date pDia) {
        this.dia = pDia;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time pHora) {
        this.hora = pHora;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String pTotal) {
        this.total = pTotal;
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String pTarjetaCredito) {
        this.tarjetaCredito = pTarjetaCredito;
    }

    

    
    
}