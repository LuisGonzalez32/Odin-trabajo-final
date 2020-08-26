<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <form action="pagoTarjetaServlet" method="get">
        <title>ESEN</title>
    </head>
    <body>
        <h1>${rows} viaje ha concluido</h1>
        <h1>Último viaje: <b>${idFinalizarViaje}</b></h1>
        <input type="hidden" name="idFinalizarViaje" value="${idFinalizarViaje}" />
        <h1>Total a pagar: <b>$${iDistancia}</b></h1>
        <input type="hidden" name="iDistancia" value="${iDistancia}" />
        
        <input type="submit" value="Ingresar"/>
            <input type="hidden" name="formid" value="1"/>
        
        
    </body>
</html>
