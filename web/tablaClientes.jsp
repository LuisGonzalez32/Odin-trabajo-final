<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uber.objects.ClienteObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <form action="ClienteServlet" method="get">
        <title>Clientes</title>  
    </head>
    
    <% 
        ArrayList<ClienteObj> arr = (ArrayList<ClienteObj>)request.getSession().getAttribute("arrcliente");
    %>  
    
    <body>
        <h1>Clientes</h1>
        <br>
        
        <p><a href="nCliente.html">Nuevo Cliente</a></p>
         <p><a href="clienteMenu.html">Regresar</a></p>
        <br>
        
            Buscar por nombre:<br>
            <input type="text" name="nombre" placeholder="Nombre" id="nombre"/>
            <br>
            <input type="submit" value="Ingresar"/>
            <input type="hidden" name="formid" value="8"/>
            <br><br>
        <table style="width: 100%; text-align: center" border="1">
        
            <tr>
                <th>Nombre</th>
                <th>Numero de telefono</th>
                <th>Correo electronico</th>
                <th>Tarjeta de credito</th>
                <th>Latitud</th>
                <th>Longitud</th>
                <th colspan="2">Acciones</th>
            </tr>
            
            <%
              Iterator<ClienteObj> arrIterator = null;
              if(arr!= null)
              {
                  arrIterator = arr.iterator();
                  ClienteObj tempC = null;

                  while(arrIterator.hasNext())
                  {
                      tempC = arrIterator.next();

            %>
            
            <tr>
                <td><%= tempC.getNombre() %></td>
                <td><%= tempC.getNumero() %></td>
                <td><%= tempC.getCorreo() %></td>
                <td><%= tempC.getTarjetaCredito() %></td>
                <td><%= tempC.getLatitud() %></td>
                <td><%= tempC.getLongitud() %></td>
                <td><a href="ClienteServlet?formid=4&id=<%= tempC.getId() %>">Modificar</a></td>
                <td><a href="ClienteServlet?formid=3&id=<%= tempC.getId() %>">Borrar</a></td>
            </tr>
            
            <%
                }
              }

            %>
            
        </table>
        
    </body>
</html>
