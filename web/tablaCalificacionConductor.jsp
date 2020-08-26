<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uber.objects.CalificacionConductorObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <form action="ConductorServlet" method="get">
        <title>Clientes</title>  
    </head>
    
    <% 
        ArrayList<CalificacionConductorObj> arr = (ArrayList<CalificacionConductorObj>)request.getSession().getAttribute("arrCalificacionConductor");
    %>  
    
    <body>
        <h1>Calificacion del conductor</h1>
        <br>
        
       
         <p><a href="CalificacionMenu.html">Regresar</a></p>
        <br>
        Buscar por nombre:<br>
            <input type="text" name="nombre" placeholder="Nombre" id="nombre"/>
            <br>
            <input type="submit" value="Ingresar"/>
            <input type="hidden" name="formid" value="13"/>
            <br><br>
        <table style="width: 100%; text-align: center" border="1">
        
            <tr>
                <th>Nombre del conductor</th>
                <th>Lugar</th>
                <th>Dia</th>
                <th>Hora</th>
                <th>Calificacion</th>
                
            </tr>
            
            <%
              Iterator<CalificacionConductorObj> arrIterator = null;
              if(arr!= null)
              {
                  arrIterator = arr.iterator();
                  CalificacionConductorObj tempC = null;

                  while(arrIterator.hasNext())
                  {
                      tempC = arrIterator.next();

            %>
            
            <tr>
                <td><%= tempC.getNombre() %></td>
                <td><%= tempC.getLugar() %></td>
                <td><%= tempC.getDia() %></td>
                <td><%= tempC.getHoraInicial() %></td>
                <td><%= tempC.getCalificacion() %></td>
            </tr>
            
            <%
                }
              }

            %>
            
        </table>
        
    </body>
</html>

