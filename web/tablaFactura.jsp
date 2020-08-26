<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uber.objects.FacturaObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Viajes</title>  
    </head>
    
    <% 
        ArrayList<FacturaObj> arr = (ArrayList<FacturaObj>)request.getSession().getAttribute("arrFactura");
    %>  
    
    <body>
        <h1>Factura</h1>
        <br>
        
        
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
            <p><a href="index.html">Finalizar</a></p>
            </tr>
            
            <%
              Iterator<FacturaObj> arrIterator = null;
              if(arr!= null)
              {
                  arrIterator = arr.iterator();
                  FacturaObj tempC = null;

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
