
package uber.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uber.Logic.ViajesLogic;
import uber.objects.ViajesObj;


@WebServlet(name = "ViajesServlet", urlPatterns = {"/ViajesServlet"})
public class ViajesServlet extends HttpServlet {

    
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
            ViajesLogic logic = new ViajesLogic(connString);
            
            switch(strFormId)
            {
               //aqui empieza el iniciar viaje
                case "1":
                    
                    String strCliente = request.getParameter("cliente");
                String strConductor = request.getParameter("conductor");
                String strLugar = request.getParameter("lugar");
                String strHora = request.getParameter("hora");
                String strLatitudInicial = request.getParameter("latitud_inicial");
                String strLongitudInicial = request.getParameter("longitud_inicial");
                String strLatitudFinal = request.getParameter("latitud_final");
                String strLongitudFinal = request.getParameter("longitud_final");
                
     
                //Transformar números
                int iIdCliente = Integer.parseInt(strCliente);
                int iIdConductor = Integer.parseInt(strConductor);
                int iIdLugar = Integer.parseInt(strLugar);
                LocalDateTime iDateTime = LocalDateTime.now();//dateFormatter.parse(strHora);
                float iLatitudInicial = Float.parseFloat(strLatitudInicial);
                float iLongitudInicial = Float.parseFloat(strLongitudInicial);
                float iLatitudFinal = Float.parseFloat(strLatitudFinal);
                float iLongitudFinal = Float.parseFloat(strLongitudFinal);


                int rows = logic.InsertIniciarViaje(iIdCliente, iIdConductor, iIdLugar, iDateTime, iLatitudInicial, iLongitudInicial, iLatitudFinal, iLongitudFinal);

                 int idIniciarViaje = logic.getUltimoViajeInicial();
                request.getSession().setAttribute("idIniciarViaje", idIniciarViaje);

                request.getSession().setAttribute("rows", rows);
                response.sendRedirect("respuestaIniciarViaje.jsp");

            break;
            //aqui finaliza el iniciar viaje
        
        
                case "2": //Select (para tabla)
                    
                    //Obtener Listado de Base de Datos
                     ArrayList<ViajesObj> arrViajes = logic.allViajes();
                    
                    //Enviar Listado a FrontEnd
                    request.getSession().setAttribute("arrviajes", arrViajes);
                    response.sendRedirect("tablaViajes.jsp");
                
                break;
                
                
                case"3":
                    String strIdIniciarViaje = request.getParameter("idIniciarViaje");
                    String fHora = request.getParameter("hora");
                    String fLatitudFinal = request.getParameter("latitud_final");
                    String fLongitudFinal = request.getParameter("longitud_final");
                    String strDistancia = request.getParameter("distancia");


                    //Transformar números
                    int fIdIniciarViaje = Integer.parseInt(strIdIniciarViaje);
                    LocalDateTime fDateTime = LocalDateTime.now();
                    float fLatitudFInal = Float.parseFloat(fLatitudFinal);
                    float fLongitudFInal = Float.parseFloat(fLongitudFinal);
                    float fDistancia = Float.parseFloat(strDistancia);


                    rows = logic.InsertFinalizarViaje(fIdIniciarViaje, fDateTime, fLatitudFInal, fLongitudFInal, fDistancia);
                    int idFinalizarViaje = logic.getUltimoViajeFinal();
                    request.getSession().setAttribute("idFinalizarViaje", idFinalizarViaje);
                    // 4636 / 1000 = 4.636 * 100 = 464 / 100 = 4.64
                    float total = Math.round((fDistancia / 1000)*100);
                    request.getSession().setAttribute("iDistancia", (total/100));
                    request.getSession().setAttribute("rows", rows);
                    response.sendRedirect("respuestaFinalizarViaje.jsp");
            
                break;
            
                case "4": //Select (para tabla)
                    
                     
                    String pNombre = request.getParameter("nombreCliente");
                    //Obtener Listado de Base de Datos
                     ArrayList<ViajesObj> arrGetViajeCliente = logic.allGetViajeCliente(pNombre);
                    
                    //Enviar Listado a FrontEnd
                    request.getSession().setAttribute("arrviajes", arrGetViajeCliente);
                    response.sendRedirect("tablaViajes.jsp");
                
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
            Logger.getLogger(ViajesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ViajesServlet.class.getName()).log(Level.SEVERE, null, ex);
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

        
