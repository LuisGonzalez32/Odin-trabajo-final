<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uber.objects.ConductorObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <form action="ConductorServlet" method="get">
        <title>Conductores</title>  
    </head>
    
    <% 
        ArrayList<ConductorObj> arr = (ArrayList<ConductorObj>)request.getSession().getAttribute("arrconductor");
    %>  
    
    <body>
        <h1>Conductores</h1>
        <br>
        
        <p><a href="nConductor.html">Nuevo Conductor</a></p>
         <p><a href="conductorMenu.html">Regresar</a></p>
        <br>
        Buscar por nombre:<br>
            <input type="text" name="nombre" placeholder="Nombre" id="nombre"/>
            <br>
            <input type="submit" value="Ingresar"/>
            <input type="hidden" name="formid" value="14"/>
            <br><br>
        <table style="width: 100%; text-align: center" border="1">
        
            <tr>
                <th>Nombre</th>
                <th>Marca de carro</th>
                <th>Correo electronico</th>
                <th>licencia</th>
                <th>Numero de DUI</th>
                <th>Numero de telefono</th>
                <th>Antecedentes penales</th>
                <th>Cuenta bancaria</th>
                <th>Latitud</th>
                <th>Longitud</th>
                <th colspan="2">Acciones</th>
            </tr>
            
            <%
              Iterator<ConductorObj> arrIterator = null;
              if(arr!= null)
              {
                  arrIterator = arr.iterator();
                  ConductorObj tempC = null;

                  while(arrIterator.hasNext())
                  {
                      tempC = arrIterator.next();

            %>
            
            <tr>
                <td><%= tempC.getNombre() %></td>
                <td><%= tempC.getMarcaCarro() %></td>
                <td><%= tempC.getCorreo() %></td>
                <td><%= tempC.getLicencia() %></td>
                <td><%= tempC.getDUI() %></td>
                <td><%= tempC.getNumero() %></td>
                <td><%= tempC.getAntecedentes() %></td>
                <td><%= tempC.getCuentaBancaria() %></td>
                <td><%= tempC.getLatitud() %></td>
                <td><%= tempC.getLongitud() %></td>
                <td><a href="ConductorServlet?formid=4&id=<%= tempC.getId() %>">Modificar</a></td>
                <td><a href="ConductorServlet?formid=3&id=<%= tempC.getId() %>">Borrar</a></td>
            </tr>
            
            <%
                }
              }

            %>
            
        </table>
        
    </body>
</html>
