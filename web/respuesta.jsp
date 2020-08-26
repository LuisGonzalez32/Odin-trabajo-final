<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Respuesta</title>
    </head>
    <body>
        <h1>${rows} ${persona} ha sido ${accion}.</h1>
        
        <!--Pendiente Colocar Servlet-->
        <p><a href="${persona}Servlet?formid=2">Ver la tabla de ${persona}</a></p>

        <div id="mensaje" style="display: none">
            <h1>el ${persona} no se ha podido ser ${accion} porque su informacion esta en otra tabla</h1>
        </div>
        
        <script>
            if (${rows} == '0') {
                document.getElementById("mensaje").setAttribute("style", "display: block");
            }
        </script>
        <p><a href="index.html">Regresar al menu</a></p>
    </body>
</html>
