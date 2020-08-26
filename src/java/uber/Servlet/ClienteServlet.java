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
import uber.Logic.ClienteLogic;
import uber.objects.CalificacionClienteObj;
import uber.objects.ClienteObj;


@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {
  
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Recibir formid = Accion a realizar
            String strFormId = request.getParameter("formid");
            
            
            
            //Acceso de BD
            String connString = "jdbc:mysql://localhost:3306/uber?"
                + "user=root&password=12345"
                + "&autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true" 
                + "&useLegacyDatetimeCode=false" 
                + "&serverTimezone=UTC";
            ClienteLogic logic = new ClienteLogic(connString);
            
            //Declarar parametros a utilizar en diferentes case
            String strId, strNombre, strNumero, strCorreo, strTarjetaCredito, strLatitud, strLongitud;
            float fLatitud, fLongitud;
            int iId, iNumero;
            
            int rows;
            
            switch(strFormId)
            {
                case "1": //Insertar
                    
                    //Obtener parametros formulario
                    strNombre = request.getParameter("nombre");
                    strNumero = request.getParameter("numerodetelefono");
                    strCorreo = request.getParameter("correoelectronico");
                    strTarjetaCredito = request.getParameter("tarjetaCredito");
                    strLatitud = request.getParameter("latitud");
                    strLongitud = request.getParameter("longitud");
            
                    //Transformar números
                    iNumero = Integer.parseInt(strNumero);
                    fLatitud = Float.parseFloat(strLatitud);
                    fLongitud = Float.parseFloat(strLongitud);
                    
                    //Envio de Parametros
                    rows = logic.nCliente(strNombre, iNumero, strCorreo, strTarjetaCredito, fLatitud, fLongitud);
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("rows", rows);
                    request.getSession().setAttribute("accion", "añadido");
                    request.getSession().setAttribute("persona", "Cliente");
                    response.sendRedirect("respuesta.jsp");
                
                break;
            
                case "2": //Select (para tabla)
                    
                    //Obtener Listado de Base de Datos
                     ArrayList<ClienteObj> arrCliente = logic.allClientes();
                    
                    //Enviar Listado a FrontEnd
                    request.getSession().setAttribute("arrcliente", arrCliente);
                    response.sendRedirect("tablaClientes.jsp");
                
                break;
                
                case "3": //Eliminar
                    
                    //Obtener id de elemento a eliminar
                    strId = request.getParameter("id");
                    
                    //Transformar a número
                    iId = Integer.parseInt(strId);
                    
                    rows = logic.dCliente(iId) ;
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("rows", rows);
                    request.getSession().setAttribute("accion", "eliminado");
                     request.getSession().setAttribute("persona", "Cliente");
                    response.sendRedirect("respuesta.jsp");
                    
                break;  
                
                case "4": //Modificar Parte 1 - Llamado de datos a modificar
                    
                    //Obtener id de elemento a modificar
                    strId = request.getParameter("id");
                    
                    //Transformar a número
                    iId = Integer.parseInt(strId);
                    
                    ClienteObj uCliente = logic.getClienteById(iId);
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("cliente", uCliente);
                    response.sendRedirect("uCliente.jsp");
                    
                break;
                
                case "5": //Modificar Parte 2 - Envio de nuevos datos
                    
                    //Obtener parametros formulario
                    strId = request.getParameter("id");
                   strNombre = request.getParameter("nombre");
                    strNumero = request.getParameter("numerodetelefono");
                    strCorreo = request.getParameter("correoelectronico");
                    strTarjetaCredito = request.getParameter("tarjetaCredito");
                    strLatitud = request.getParameter("latitud");
                    strLongitud = request.getParameter("longitud");
            
                    //Transformar números
                     iId = Integer.parseInt(strId);
                    iNumero = Integer.parseInt(strNumero);
                    fLatitud = Float.parseFloat(strLatitud);
                    fLongitud = Float.parseFloat(strLongitud);

                    rows = logic.uCliente(iId, strNombre, iNumero, strCorreo, strTarjetaCredito, fLatitud, fLongitud);
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("rows", rows);
                    request.getSession().setAttribute("accion", "modificado");
                     request.getSession().setAttribute("persona", "Cliente");
                    response.sendRedirect("respuesta.jsp");
                    
                break;
                
                
                
                
                
                
                
                
                
                case "6": //Insertar
                String strIdUltimoViajeCliente = request.getParameter("idUltimoViajeCliente");
                String strCalificacion = request.getParameter("calificacion");
                
            
                //Transformar números
                int idUltimoViajeCliente = Integer.parseInt(strIdUltimoViajeCliente);
                int iCalificacion = Integer.parseInt(strCalificacion);

                rows = logic.InsertCalificacionConductor(idUltimoViajeCliente, iCalificacion);

                request.getSession().setAttribute("rows", rows);
                response.sendRedirect("respuestaCalificacionCliente.jsp");
            break;
            
                case "7": //Select (para tabla)
                    
                    //Obtener Listado de Base de Datos
                     ArrayList<CalificacionClienteObj> arrCalificacionCliente = logic.allCalificacionesClientes();
                    
                    //Enviar Listado a FrontEnd
                    request.getSession().setAttribute("arrCalificacionCliente", arrCalificacionCliente);
                    response.sendRedirect("tablaCalificacionCliente.jsp");
                
                break;
                
                 case "8": //Select (para tabla)
                    
                     
                     String Nombre = request.getParameter("nombre");
                    //Obtener Listado de Base de Datos
                     ArrayList<ClienteObj> arrGetCliente = logic.allGetCliente(Nombre);
                    
                    //Enviar Listado a FrontEnd
                   request.getSession().setAttribute("arrcliente", arrGetCliente);
                    response.sendRedirect("tablaClientes.jsp");
                
                break;
                
                 case "9": //Select (para tabla)
                    
                     
                     String pNombre = request.getParameter("nombre");
                    //Obtener Listado de Base de Datos
                     ArrayList<CalificacionClienteObj> arrGetCalificacionCliente = logic.allGetCalificacionCliente(pNombre);
                    
                    //Enviar Listado a FrontEnd
                   request.getSession().setAttribute("arrCalificacionCliente", arrGetCalificacionCliente);
                    response.sendRedirect("tablaCalificacionCliente.jsp");
                
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
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
