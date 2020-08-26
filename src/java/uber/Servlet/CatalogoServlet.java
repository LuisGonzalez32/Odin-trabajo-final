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
import uber.Logic.CatalogoLogic;
import uber.objects.CatalogoObj;

@WebServlet(name = "CatalogoServlet", urlPatterns = {"/CatalogoServlet"})
public class CatalogoServlet extends HttpServlet {

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
            CatalogoLogic logic = new CatalogoLogic(connString);
            
            //Declarar parametros a utilizar en diferentes case
            String strId, strNombre, strCategoria, strLatitud, strLongitud;
            float fLatitud, fLongitud;
            int iId;
            
            int rows;
            
            switch(strFormId)
            {
                case "1": //Insertar
                    
                    //Obtener parametros formulario
                    strNombre = request.getParameter("nombre");
                    strCategoria = request.getParameter("categoria");
                    strLatitud = request.getParameter("latitud");
                    strLongitud = request.getParameter("longitud");
            
                    //Transformar números
                    fLatitud = Float.parseFloat(strLatitud);
                    fLongitud = Float.parseFloat(strLongitud);
                    
                    //Envio de Parametros
                    rows = logic.nCatalogo(strNombre, strCategoria, fLatitud, fLongitud);
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("rows", rows);
                    request.getSession().setAttribute("accion", "añadido");
                    request.getSession().setAttribute("persona", "Catalogo");
                    response.sendRedirect("respuesta.jsp");
                
                break;
            
                case "2": //Select (para tabla)
                    
                    //Obtener Listado de Base de Datos
                     ArrayList<CatalogoObj> arrCatalogo = logic.allCatalogos();
                    
                    //Enviar Listado a FrontEnd
                    request.getSession().setAttribute("arrcatalogo", arrCatalogo);
                    response.sendRedirect("tablaCatalogos.jsp");
                
                break;
                
                case "3": //Eliminar
                    
                    //Obtener id de elemento a eliminar
                    strId = request.getParameter("id");
                    
                    //Transformar a número
                    iId = Integer.parseInt(strId);
                    
                    rows = logic.dCatalogo(iId) ;
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("rows", rows);
                    request.getSession().setAttribute("accion", "eliminado");
                    request.getSession().setAttribute("persona", "Catalogo");
                    response.sendRedirect("respuesta.jsp");
                    
                break;  
                
                case "4": //Modificar Parte 1 - Llamado de datos a modificar
                    
                    //Obtener id de elemento a modificar
                    strId = request.getParameter("id");
                    
                    //Transformar a número
                    iId = Integer.parseInt(strId);
                    
                    CatalogoObj uCatalogo = logic.getCatalogoById(iId);
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("catalogo", uCatalogo);
                    response.sendRedirect("uCatalogo.jsp");
                    
                break;
                
                case "5": //Modificar Parte 2 - Envio de nuevos datos
                    
                    //Obtener parametros formulario
                    strId = request.getParameter("id");
                    strNombre = request.getParameter("nombre");
                    strCategoria = request.getParameter("categoria");
                    strLatitud = request.getParameter("latitud");
                    strLongitud = request.getParameter("longitud");
            
                    //Transformar números
                     iId = Integer.parseInt(strId);
                    fLatitud = Float.parseFloat(strLatitud);
                    fLongitud = Float.parseFloat(strLongitud);

                    rows = logic.uCatalogo(iId, strNombre, strCategoria, fLatitud, fLongitud);
                    
                    //Enviar respuesta a Front End
                    request.getSession().setAttribute("rows", rows);
                    request.getSession().setAttribute("accion", "modificado");
                    request.getSession().setAttribute("persona", "Catalogo");
                    response.sendRedirect("respuesta.jsp");
                    
                break;
                
                 case "8": //Select (para tabla)
                    
                     
                     String Nombre = request.getParameter("nombre");
                    //Obtener Listado de Base de Datos
                     ArrayList<CatalogoObj> arrGetCatalogo = logic.allGetCatalogo(Nombre);
                    
                    //Enviar Listado a FrontEnd
                   request.getSession().setAttribute("arrcatalogo", arrGetCatalogo);
                    response.sendRedirect("tablaCatalogos.jsp");
                
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
            Logger.getLogger(CatalogoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CatalogoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
