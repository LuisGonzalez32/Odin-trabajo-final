<%@page import="uber.objects.ClienteObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <% 
        ClienteObj uCliente = (ClienteObj)request.getSession().getAttribute("cliente");
    %>  
    
    <body>
        <h1>Modificar Cliente</h1>
        
        <form action="ClienteServlet" method="get">
            
           
            Nombre:<br>
            <input type="text" name="nombre" id="nombre" placeholder="Nombre" value="<%= uCliente.getNombre() %>"/>
            <br><br>
            Numero de telefono:<br>
            <input type="number" name="numerodetelefono" placeholder="numero de telefono"  id="numerodetelefono" value="<%= uCliente.getNumero() %>"/>
            <br><br>
            Correo Electronico:<br>
            <input type="email" name="correoelectronico" placeholder="correo electronico" id="correoelectronico" value="<%= uCliente.getCorreo() %>"/>
            <br><br>
            Tarjeta de credito:<br>
            <input type="password" name="tarjetaCredito" placeholder="Tarjeta de credito" id="tarjetaCredito" value="<%= uCliente.getTarjetaCredito() %>"/>
            <br><br>
            
            
            <input type="button" class = "geeks" onclick="getlocation()" value="Obtener ubicación"/>
            <input type="text" readonly="true" name="latitud" id="latitud" placeholder="latitud" value="<%= uCliente.getLatitud() %>"/>
            <input type="text" readonly="true" name="longitud" id="longitud" placeholder="longitud" value="<%= uCliente.getLongitud() %>"/>
            <br><br>
            <input type="submit" value="Ingresar"/>
            <input type="hidden" name="formid" value="5"/>
            <input type="hidden" name="id" value="<%= uCliente.getId() %>"/>
        </form>
        
        <script>
        function getlocation(){ 
                navigator.geolocation.getCurrentPosition(showLoc); 
        }   
        function showLoc(pos){
                document.getElementById("latitud").value = pos.coords.latitude;
                document.getElementById("longitud").value = pos.coords.longitude;
            } 
        </script> 
        
    </body>
</html>
