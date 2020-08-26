package uber.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uber.Logic.ConductorLogic;
import uber.objects.CalificacionConductorObj;
import uber.objects.ConductorGananciasObj;
import uber.objects.ConductorObj;
import uber.objects.FacturaObj;
import uber.objects.GananciasDetalleObj;


@WebServlet(name = "ConductorServlet", urlPatterns = {"/ConductorServlet"})
public class ConductorServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Recibir formid = Accion a realizar
            String FormId = request.getParameter("formid");
            
            
            
            //Acceso de BD
            String connString = "jdbc:mysql://localhost:3306/uber?"
                + "user=root&password=12345"
                + "&autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true" 
                + "&useLegacyDatetimeCode=false" 
                + "&serverTimezone=UTC";
            ConductorLogic logic = new ConductorLogic(connString);
            
            //Declarar parametros a utilizar en diferentes case
            String strId, strNombre, strMarcaCarro,strCorreo,strLicencia, strDUI, strNumero, strAntecedentes, strCuentaBancaria, strLatitud, strLongitud;
            float fLatitud, fLongitud;
            int iId, iLicencia, iDUI, iNumero;
            
            int rows;
            
            switch(FormId)
            {
                case "1": //Insertar
                    
                    //Obtener parametros formulario
                    strNombre = request.getParameter("nombre");
                    strMarcaCarro = request.getParameter("marcaDeCarro");
                    strCorreo = request.getParameter("correoElectronico");
                    strLicencia = request.getParameter("licencia");
                    strDUI = request.getParameter("DUI");
                    strNumero = request.getParameter("numeroDeTelefono");
                    strAntecedentes = request.getParameter("antecedentes");
                    strCuentaBancaria = request.getParameter("cuentaBancaria");
                    strLatitud = request.getParameter("latitud");
                    strLongitud = request.getParameter("longitud");
            
                    //Transformar números
                    iNumero = Integer.parseInt(strNumero);
                    iLicencia = Integer.parseInt(strLicencia);
                    iDUI = Integer.parseInt(strDUI);
                    iNumero = Integer.parseInt(strNumero);
                    fLatitud = Float.parseFloat(strLatitud);
                    fLongitud = Float.parseFloat(strLongitud);
                    
                    //Envio de Parametros
                    rows = logic.nConductor(strNombre, strMarcaCarro, strCorreo,  iLicencia, iDUI,iNumero,strAntecedentes,strCuentaBancaria, fLatitud, fLongitud);
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("rows", rows);
                    request.getSession().setAttribute("accion", "añadido");
                    request.getSession().setAttribute("persona", "Conductor");
                    response.sendRedirect("respuesta.jsp");
                
                break;
            
                case "2": //Select (para tabla)
                    
                    //Obtener Listado de Base de Datos
                     ArrayList<ConductorObj> arrConductor = logic.allConductores();
                    
                    //Enviar Listado a FrontEnd
                    request.getSession().setAttribute("arrconductor", arrConductor);
                    response.sendRedirect("tablaConductores.jsp");
                
                break;
                
                case "3": //Eliminar
                    
                    //Obtener id de elemento a eliminar
                    strId = request.getParameter("id");
                    
                    //Transformar a número
                    iId = Integer.parseInt(strId);
                    
                    rows = logic.dConductor(iId) ;
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("rows", rows);
                    request.getSession().setAttribute("accion", "eliminado");
                    request.getSession().setAttribute("persona", "Conductor");
                    response.sendRedirect("respuesta.jsp");
                    
                break;  
                
                case "4": //Modificar Parte 1 - Llamado de datos a modificar
                    
                    //Obtener id de elemento a modificar
                    strId = request.getParameter("id");
                    
                    //Transformar a número
                    iId = Integer.parseInt(strId);
                    
                    ConductorObj uConductor = logic.getConductorById(iId);
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("conductor", uConductor);
                    response.sendRedirect("uConductor.jsp");
                    
                break;
                
                case "5": //Modificar Parte 2 - Envio de nuevos datos
                    
                    //Obtener parametros formulario
                    strId = request.getParameter("id");
                     strNombre = request.getParameter("nombre");
                    strMarcaCarro = request.getParameter("marcaDeCarro");
                    strCorreo = request.getParameter("correoelectronico");
                    strLicencia = request.getParameter("licencia");
                    strDUI = request.getParameter("DUI");
                    strNumero = request.getParameter("numeroDeTelefono");
                    strAntecedentes = request.getParameter("antecedentes");
                    strCuentaBancaria = request.getParameter("cuentaBancaria");
                    strLatitud = request.getParameter("latitud");
                    strLongitud = request.getParameter("longitud");
            
                    //Transformar números
                    iId = Integer.parseInt(strId);
                    iNumero = Integer.parseInt(strNumero);
                    iLicencia = Integer.parseInt(strLicencia);
                    iDUI = Integer.parseInt(strDUI);
                    iNumero = Integer.parseInt(strNumero);
                    fLatitud = Float.parseFloat(strLatitud);
                    fLongitud = Float.parseFloat(strLongitud);

                    rows = logic.uCliente(iId, strNombre, strMarcaCarro, strCorreo,  iLicencia, iDUI,iNumero,strAntecedentes,strCuentaBancaria, fLatitud, fLongitud);
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("rows", rows);
                    request.getSession().setAttribute("accion", "modificado");
                    request.getSession().setAttribute("persona", "Conductor");
                    response.sendRedirect("respuesta.jsp");
                    
                break;
                
                case "6": //Insertar
                String strIdUltimoViajeConductor = request.getParameter("idUltimoViajeConductor");
                String strCalificacion = request.getParameter("calificacion");
                
                //Transformar números
                int idUltimoViajeConductor = Integer.parseInt(strIdUltimoViajeConductor);
                int iCalificacion = Integer.parseInt(strCalificacion);

                rows = logic.InsertCalificacionConductor(idUltimoViajeConductor, iCalificacion);

                int idUltimoViajeCliente = logic.getUltimoViaje();
                request.getSession().setAttribute("idUltimoViajeCliente", idUltimoViajeCliente);

                request.getSession().setAttribute("rows", rows);
                response.sendRedirect("respuestaCalificacionConductor.jsp");
            break;
            
                case "7": //Select (para tabla)
                    
                    //Obtener Listado de Base de Datos
                     ArrayList<CalificacionConductorObj> arrCalificacionConductor = logic.allCalificacionesConductores();
                    
                    //Enviar Listado a FrontEnd
                    request.getSession().setAttribute("arrCalificacionConductor", arrCalificacionConductor);
                    response.sendRedirect("tablaCalificacionConductor.jsp");
                
                break;
                
                 case "8": //Select (para tabla)
                    
                    //Obtener Listado de Base de Datos
                     ArrayList<ConductorGananciasObj> arrConductorGanancias = logic.allConductorGanancias();
                    
                    //Enviar Listado a FrontEnd
                    request.getSession().setAttribute("arrConductorGanancias", arrConductorGanancias);
                    response.sendRedirect("tablaConductorGanancias.jsp");
                
                break;
                
                case "9": //Select (para tabla)
                    
                    //Obtener Listado de Base de Datos
                     ArrayList<GananciasDetalleObj> arrGananciasDetalle = logic.allGananciasDetalles();
                    
                    //Enviar Listado a FrontEnd
                    request.getSession().setAttribute("arrgananciasDetalle", arrGananciasDetalle);
                    response.sendRedirect("tablaGananciasDetalle.jsp");
                
                break;
                
                case "10": //Select (para tabla)
                    
                    //Obtener Listado de Base de Datos
                     ArrayList<FacturaObj> arrFactura = logic.allFacturas();
                    
                    //Enviar Listado a FrontEnd
                    request.getSession().setAttribute("arrFactura", arrFactura);
                    response.sendRedirect("tablaFactura.jsp");
                
                break;
                
                 case "11": //Select (para tabla)
                    
                     
                     String Nombre = request.getParameter("nombre");
                    //Obtener Listado de Base de Datos
                     ArrayList<GananciasDetalleObj> arrGetGananciasDetalle = logic.allGetGananciasDetalle(Nombre);
                    
                    //Enviar Listado a FrontEnd
                   request.getSession().setAttribute("arrgananciasDetalle", arrGetGananciasDetalle);
                    response.sendRedirect("tablaGananciasDetalle.jsp");
                
                break;
                
                 case "12": //Select (para tabla)
                    
                     
                     String pNombre = request.getParameter("nombre");
                    //Obtener Listado de Base de Datos
                     ArrayList<ConductorGananciasObj> arrGetConductorGanancias = logic.allGetConductorGanancias(pNombre);
                    
                    //Enviar Listado a FrontEnd
                   request.getSession().setAttribute("arrConductorGanancias", arrGetConductorGanancias);
                    response.sendRedirect("tablaConductorGanancias.jsp");
                
                break;
                
                case "13": //Select (para tabla)
                    
                     
                     String sNombre = request.getParameter("nombre");
                    //Obtener Listado de Base de Datos
                     ArrayList<CalificacionConductorObj> arrGetCalificacionConductor = logic.allGetCalificacionConductor(sNombre);
                    
                    //Enviar Listado a FrontEnd
                   request.getSession().setAttribute("arrCalificacionConductor", arrGetCalificacionConductor);
                    response.sendRedirect("tablaCalificacionConductor.jsp");
                
                break;
                
                case "14": //Select (para tabla)
                    
                     
                     String tNombre = request.getParameter("nombre");
                    //Obtener Listado de Base de Datos
                     ArrayList<ConductorObj> arrGetConductor = logic.allGetConductor(tNombre);
                    
                    //Enviar Listado a FrontEnd
                   request.getSession().setAttribute("arrconductor", arrGetConductor);
                    response.sendRedirect("tablaConductores.jsp");
                
                break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConductorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConductorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
