<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uber.objects.ViajesObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <form action="ViajesServlet" method="get">
        <title>Viajes</title>  
    </head>
    
    <% 
        ArrayList<ViajesObj> arr = (ArrayList<ViajesObj>)request.getSession().getAttribute("arrviajes");
    %>  
    
    <body>
        <h1>Viajes realizados</h1>
        <br>
        
            Buscar por nombre del pasajero:<br>
            <input type="text" name="nombreCliente" placeholder="Nombre del pasajero" id="nombreCliente"/>
            <br>
            <input type="submit" value="Ingresar"/>
            <input type="hidden" name="formid" value="4"/>
            <br><br>
            
            
        <table style="width: 100%; text-align: center" border="1">
        
            <tr>
                <th>Nombre del pasajero</th>
                <th>Nombre del conductor</th>
                <th>Nombre del lugar</th>
                <th>Distancia recorrida en metros</th>
                <th>Dia</th>
                <th>Hora</th>
                <th>Total($)</th>
                <th>Tarjeta de credito</th>
            <br><br>
            <p><a href="index.html">Regresar al menu principal</a></p>
            </tr>
            
            <%
              Iterator<ViajesObj> arrIterator = null;
              if(arr!= null)
              {
                  arrIterator = arr.iterator();
                  ViajesObj tempC = null;

                  while(arrIterator.hasNext())
                  {
                      tempC = arrIterator.next();

            %>
            
            <tr>
                <td><%= tempC.getNombreCliente() %></td>
                <td><%= tempC.getNombreConductor() %></td>
                <td><%= tempC.getNombreLugar() %></td>
                <td><%= tempC.getDistancia() %></td>
                <td><%= tempC.getDia() %></td>
                <td><%= tempC.getHora() %></td>
                <td><%= tempC.getTotal() %></td>
                <td><%= tempC.getTarjetaCredito() %></td>
                
            </tr>
            
            <%
                }
              }

            %>
            
        </table>
        
    </body>
</html>
