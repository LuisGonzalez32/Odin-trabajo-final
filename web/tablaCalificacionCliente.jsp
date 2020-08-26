<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uber.objects.CalificacionClienteObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <form action="ClienteServlet" method="get">
        <title>Clientes</title>  
    </head>
    
    <% 
        ArrayList<CalificacionClienteObj> arr = (ArrayList<CalificacionClienteObj>)request.getSession().getAttribute("arrCalificacionCliente");
    %>  
    
    <body>
        <h1>Calificacion del cliente</h1>
        <br>
        
       
         <p><a href="CalificacionMenu.html">Regresar</a></p>
        <br>
        Buscar por nombre:<br>
            <input type="text" name="nombre" placeholder="Nombre" id="nombre"/>
            <br>
            <input type="submit" value="Ingresar"/>
            <input type="hidden" name="formid" value="9"/>
            <br><br>
        <table style="width: 100%; text-align: center" border="1">
        
            <tr>
                <th>Nombre del cliente</th>
                <th>Lugar</th>
                <th>Dia</th>
                <th>Hora</th>
                <th>Calificacion</th>
                
            </tr>
            
            <%
              Iterator<CalificacionClienteObj> arrIterator = null;
              if(arr!= null)
              {
                  arrIterator = arr.iterator();
                  CalificacionClienteObj tempC = null;

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
