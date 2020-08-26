<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uber.objects.GananciasDetalleObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <form action="ConductorServlet" method="get">
        <title>Conductores</title>  
    </head>
    
    <% 
        ArrayList<GananciasDetalleObj> arr = (ArrayList<GananciasDetalleObj>)request.getSession().getAttribute("arrgananciasDetalle");
    %>  
    
    <body>
        <h1>Ganancias de los conductores</h1>
        
        <p><a href="tablaConductorGanancias.jsp">Regresar</a></p>
        
        <br>
        
        <table style="width: 100%; text-align: center" border="1">
         Buscar por nombre:<br>
            <input type="text" name="nombre" placeholder="Nombre" id="nombre"/>
            <br>
            <input type="submit" value="Ingresar"/>
            <input type="hidden" name="formid" value="11"/>
            <br><br>
            <tr>
                <th>Nombre del conductor</th>
                <th>Hora</th>
                <th>Total ganado</th>
                
            </tr>
            
            <%
              Iterator<GananciasDetalleObj> arrIterator = null;
              if(arr!= null)
              {
                  arrIterator = arr.iterator();
                  GananciasDetalleObj tempC = null;

                  while(arrIterator.hasNext())
                  {
                      tempC = arrIterator.next();

            %>
            
            <tr>
                <td><%= tempC.getNombre() %></td>
                <td><%= tempC.getHora() %></td>
                <td><%= tempC.getTotal() %></td>
            </tr>
            
            <%
                }
              }

            %>
            
        </table>
        
    </body>
</html>

