<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <form action="ConductorServlet" method="get">
        <title>ESEN</title>
    </head>
    <body>
        <h1>${rows} pago añadido</h1>
        
           <input type="hidden" name="idUltimoViajeConductor" value="${idUltimoViajeConductor}" />
           
        
        <p><a href="calificacionConductor.jsp">Calificar al conductor</a></p>
        
        <p><a href="index.html">Finalizar</a></p>
        
        
    </body>
</html>
