<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uber.objects.ConductorGananciasObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <form action="ConductorServlet" method="get">
        <title>Conductores</title>  
    </head>
    
    <% 
        ArrayList<ConductorGananciasObj> arr = (ArrayList<ConductorGananciasObj>)request.getSession().getAttribute("arrConductorGanancias");
    %>  
    
    <body>
        <h1>Ganancias de los conductores</h1>
        <br>
        <p><a href="ConductorServlet?formid=9">Ver mas detalles</a></p>
        
        <p><a href="conductorMenu.html">Regresar al menu del conductor</a></p>
        
        <br>
        Buscar por nombre:<br>
            <input type="text" name="nombre" placeholder="Nombre" id="nombre"/>
            <br>
            <input type="submit" value="Ingresar"/>
            <input type="hidden" name="formid" value="12"/>
            <br><br>
        <table style="width: 100%; text-align: center" border="1">
        
            <tr>
                <th>Nombre del conductor</th>
                <th>Numero de viajes</th>
                <th>Total ganado</th>
                
            </tr>
            
            <%
              Iterator<ConductorGananciasObj> arrIterator = null;
              if(arr!= null)
              {
                  arrIterator = arr.iterator();
                  ConductorGananciasObj tempC = null;

                  while(arrIterator.hasNext())
                  {
                      tempC = arrIterator.next();

            %>
            
            <tr>
                <td><%= tempC.getNombre() %></td>
                <td><%= tempC.getNumeroViajes() %></td>
                <td><%= tempC.getGanancias() %></td>
            </tr>
            
            <%
                }
              }

            %>
            
        </table>
        
    </body>
</html>
