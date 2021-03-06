<%@page import="uber.objects.CatalogoObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>ESEN</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
            integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
            crossorigin=""/>
        <style>
            * { margin: 1px;}
            html, body {
              height: 100%;
              margin: 0;
              padding: 0;
            }
            .mapa {
              width: 50%;
              height: 40%;
              margin-bottom: 5px;
              border: 1px solid #777;
            }
            #coords{width: 500px;}
        </style>
    </head>
     <% 
        CatalogoObj uCatalogo = (CatalogoObj)request.getSession().getAttribute("catalogo");
    %>  
    <body>
        <h1> Catalogo de lugares turisticos </h1>
        
        <form action="CatalogoServlet" method="get">
            
           
            
            <br><br>
            <div style="margin-left: 5px; margin: 5px;">
                Nombre del lugar:<br>
                <input type="text" name="nombre" placeholder="Nombre del lugar" id="nombre" value="<%= uCatalogo.getNombre() %>"/>
                <br><br>
                Categoria:<br>
                <input type="text" name="categoria" placeholder="Categoria del lugar turistico" id="categoria" value="<%= uCatalogo.getCategoria() %>"/>
                <br><br>
                Insertar ubicacion:<br>
                <div id="mapa_ubicacion" class="mapa"></div>
                <input type="text" readonly="true" name="latitud" id="latitud" placeholder="latitud" value="<%= uCatalogo.getLatitud() %>"/>
                <input type="text" readonly="true" name="longitud" id="longitud" placeholder="longitud" value="<%= uCatalogo.getLongitud() %>"/><br>

                
                
                <input type="submit" value="Ingresar"/>
            <input type="hidden" name="formid" value="5"/>
            <input type="hidden" name="id" value="<%= uCatalogo.getId() %>"/>
            </div>


            <br>
            <p><a href="index.html">Regresar al menu</a></p>
        </form>
            
        <!-- Carga de la libreria de google maps -->
        <!--<script async defer src="https://maps.googleapis.com/maps/api/js?callback=initMap"></script> -->
        <script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js" integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew==" crossorigin=""></script>
        <script>
            var mapa_ubicacion, mapa_distancia, firstLatLng, secondLatLng;
            window.onload = function () {
                navigator.geolocation.getCurrentPosition(
                    function (position) {
                        mapa_ubicacion = L.map('mapa_ubicacion').setView([position.coords.latitude, position.coords.longitude], 13);
                        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        }).addTo(mapa_ubicacion);

                        var marker = L.marker([position.coords.latitude, position.coords.longitude], {'draggable': true}).addTo(mapa_ubicacion);

                        marker.on('dragend', function(ev) {
                            document.getElementById("latitud").value = this.getLatLng().lat;
                            document.getElementById("longitud").value = this.getLatLng().lng;
                        });
                        
                        
                        
                  }, function(error){console.log(error);}
                );
            }

            function medirDistancia() {
                  var distance = mapa_distancia.distance(firstLatLng ,secondLatLng);
                  document.getElementById('distancia').value = distance;
            }
        </script>
    </body>
</html>