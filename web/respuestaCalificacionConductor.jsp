<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <form action="ClienteServlet" method="get">
        <title>ESEN</title>
    </head>
    <body>
        <h1>${rows} Calificacion añadida</h1>
        
       <input type="hidden" name="idUltimoViajeCliente" value="${idUltimoViajeCliente}" />
        
        <p><a href="calificacionCliente.jsp">Calificar al cliente</a></p>
        
        <p><a href="index.html">Finalizar</a></p>
        
    </body>
</html>
