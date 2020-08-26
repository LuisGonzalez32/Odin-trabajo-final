<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <form action="ViajeServlet" method="get">
        <title>ESEN</title>
    </head>
    <body>
        <h1>${rows} viaje añadido</h1>
        
        <input type="hidden" name="idIniciarViaje" value="${idIniciarViaje}" />
        
      
        
        <p><a href="finalizarViaje.jsp">Finalizar viaje</a></p>
        
        
    </body>
</html>
