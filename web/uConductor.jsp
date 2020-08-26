<%@page import="uber.objects.ConductorObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <% 
        ConductorObj uConductor = (ConductorObj)request.getSession().getAttribute("conductor");
    %>  
    
    <body>
        <h1>Modificar Conductor</h1>
        
        <form action="ConductorServlet" method="get">
            
           
            Nombre:<br>
            <input type="text" name="nombre" id="nombre" placeholder="Nombre" value="<%= uConductor.getNombre() %>"/>
            <br><br>
            marca de carro:<br>
            <input type="text" id="marcaDeCarro" placeholder="Marca del carro" name="marcaDeCarro" value="<%= uConductor.getMarcaCarro() %>"/>
            <br><br>
            Correo Electronico:<br>
            <input type="email" name="correoElectronico" placeholder="correo electronico" id="correoElectronico" value="<%= uConductor.getCorreo() %>"/>
            <br><br>
            Licencia:<br>
            <input type="number" name="licencia" placeholder="Licencia" id="licencia" value="<%= uConductor.getLicencia() %>"/>
            <br><br>
            Numero de dui:<br>
            <input type="number" name="DUI" placeholder="Numero de DUI" id="DUI" value="<%= uConductor.getDUI() %>"/>
            <br><br>
            Numero de telefono:<br>
            <input type="number" name="numeroDeTelefono" placeholder="Numero de telefono" id="numeroDeTelefono" value="<%= uConductor.getNumero() %>"/>
            <br><br>
            Antecedentes penales?<br>
            <input type="checkbox" id="si" name="antecedentes" value="si">
            <label for="vehicle1"> si</label><br>
            <input type="checkbox" id="no" name="antecedentes" value="no" value="<%= uConductor.getAntecedentes() %>"/>
            <label for="vehicle2"> no</label><br> 
            <br><br>
            Cuenta bancaria:<br>
            <input type="number" name="cuentaBancaria" placeholder="Cuenta bancaria" id="cuentaBancaria" value="<%= uConductor.getCuentaBancaria() %>"/>
            <br><br>
            
            
            
            <input type="button" class = "geeks" onclick="getlocation()" value="Obtener ubicación"/>
            <input type="text" readonly="true" name="latitud" id="latitud" placeholder="latitud" value="<%= uConductor.getLatitud() %>"/>
            <input type="text" readonly="true" name="longitud" id="longitud" placeholder="longitud" value="<%= uConductor.getLongitud() %>"/>
            <br><br>
            <input type="submit" value="Ingresar"/>
            <input type="hidden" name="formid" value="5"/>
            <input type="hidden" name="id" value="<%= uConductor.getId() %>"/>
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
