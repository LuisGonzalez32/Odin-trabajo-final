
package uber.objects;
import java.sql.Time;
import java.sql.Date;


public final class CalificacionConductorObj {
    
    
    private String nombre;
    private String lugar;
    private Date dia;
    private Time horaInicial;
    private int calificacion;
    
    
    public CalificacionConductorObj(String pNombre, String pLugar, Date pDia, Time pHoraInicial, int pCalificacion){
        
        
        setNombre(pNombre);
        setLugar(pLugar);
        setDia(pDia);
        setHoraInicial(pHoraInicial);
        setCalificacion(pCalificacion);
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String pLugar) {
        this.lugar = pLugar;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date pDia) {
        this.dia = pDia;
    }

    public Time getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Time pHoraInicial) {
        this.horaInicial = pHoraInicial;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int pCalificacion) {
        this.calificacion = pCalificacion;
    }


   
    
    
}