<%@page import="java.lang.String"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>     
        <title>JSP Page</title>
    </head>
    <body style="margin-top: 30px">      
        
                
            <div>
                <form action="ClienteServlet" method="get">
                
                 </select>   
                    <input type="hidden" name="idUltimoViajeCliente" value="${idUltimoViajeCliente}" />
                   
                   
                    
                    
                    Que calificacion le pones al pasajero?<br>
                    <br><br>
                    <select name="calificacion">
                        <option value="1">Malo</option>
                        <option value="2">Regular</option>
                        <option value="3">Bueno</option>
                        <option value="4">Muy Bueno</option>
                        <option value="5">Excelente</option>
                    </select>
            <br><br>
                    
                    <input type="submit" value="Ingresar" >
                     <input type="hidden" name="formid" value="6"/>
                    <br><br>
                    <p><a href="index.html">Regresar al menu</a></p>
                    
                    
                </form>
            </div>
    </body>
</html>