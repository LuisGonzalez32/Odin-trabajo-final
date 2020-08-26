<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uber.objects.CatalogoObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <form action="CatalogoServlet" method="get">
        <title>Clientes</title>  
    </head>
    
    <% 
        ArrayList<CatalogoObj> arr = (ArrayList<CatalogoObj>)request.getSession().getAttribute("arrcatalogo");
    %>  
    
    <body>
        <h1>Catalogos</h1>
        <br>
        
        <p><a href="nCatalogo.html">Nuevo Catalogo</a></p>
         <p><a href="catalogoMenu.html">Regresar</a></p>
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
                <th>Categoria</th>
                <th>Latitud</th>
                <th>Longitud</th>
                <th colspan="2">Acciones</th>
            </tr>
            
            <%
              Iterator<CatalogoObj> arrIterator = null;
              if(arr!= null)
              {
                  arrIterator = arr.iterator();
                  CatalogoObj tempC = null;

                  while(arrIterator.hasNext())
                  {
                      tempC = arrIterator.next();

            %>
            
            <tr>
                <td><%= tempC.getNombre() %></td>
                <td><%= tempC.getCategoria() %></td>
                <td><%= tempC.getLatitud() %></td>
                <td><%= tempC.getLongitud() %></td>
                <td><a href="CatalogoServlet?formid=4&id=<%= tempC.getId() %>">Modificar</a></td>
                <td><a href="CatalogoServlet?formid=3&id=<%= tempC.getId() %>">Borrar</a></td>
            </tr>
            
            <%
                }
              }

            %>
            
        </table>
        
    </body>
</html>
