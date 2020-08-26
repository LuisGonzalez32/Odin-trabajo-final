<%@page import="java.lang.String"%>
<%@page import="java.sql.*"%>
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
    <body>
        <body style="margin-top: 30px">      
       
            
       
        
        <form action="ViajesServlet" method="get">
            
           <h1>Finalizar viaje</h1>
            
            <br><br>
            <div style="margin-left: 5px; margin: 5px;">
                
                   
                    </select>   
                    <input type="hidden" name="idIniciarViaje" value="${idIniciarViaje}" />
                   <br><br>
                   <label for="hora">Fecha y hora:</label>
                     <input type="datetime-local" id="hora" name="hora">
                    <br><br>
                
                    Mueva el cursor para insertar ubicacion de destino:<br>
                    <div id="mapa_ubicacion" class="mapa"></div>
                    <input type="text" readonly="true" name="latitud_final" id="latitud_final" placeholder="latitud"/>
                    <input type="text" readonly="true" name="longitud_final" id="longitud_final" placeholder="longitud" /><br>
                    <br>
                    
                    Inserte su ubicacion inicial y ubicacion de destino para conocer la distancia en metros:
                     <div id="mapa_distancia" class="mapa"></div>
                    <input type="text" readonly="true" name="distancia" id="distancia" placeholder="distancia" /><br>
                    
                    <input type="submit" value="Ingresar"/>
                    <input type="hidden" name="formid" value="3"/>
                    <br><br>
                    <p><a href="index.html">Regresar al menu</a></p>
                    
                    <script>
                        function getlocation(){ 
                            avigator.geolocation.getCurrentPosition(showLoc); 
                        }   
                        function showLoc(pos){
                            document.getElementById("latitud_final").value = pos.coords.latitude;
                            document.getElementById("longitud_final").value = pos.coords.longitude;
            } 
                    </script> 
                </form>
            </div>
    </body> 
        
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
                            document.getElementById("latitud_final").value = this.getLatLng().lat;
                            document.getElementById("longitud_final").value = this.getLatLng().lng;
                        });
                        
                        
                        mapa_distancia = L.map('mapa_distancia').setView([position.coords.latitude, position.coords.longitude], 13);
                        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        }).addTo(mapa_distancia);
                        
                        mapa_distancia.on('click', function(e) {
                            if (!firstLatLng) {
                              firstLatLng = e.latlng;
                                L.marker(firstLatLng).addTo(mapa_distancia).bindPopup('Point A<br/>' + e.latlng).openPopup();
                            } else {
                              secondLatLng = e.latlng;
                                L.marker(secondLatLng).addTo(mapa_distancia).bindPopup('Point B<br/>' + e.latlng).openPopup();
                            }

                            if (firstLatLng && secondLatLng) {
                              // Dibujamos una línea entre los dos puntos
                              L.polyline([firstLatLng, secondLatLng], {
                                color: 'red'
                              }).addTo(mapa_distancia);

                              medirDistancia();
                            }
                          });
                  }, function(error){console.log(error);}
                );
            }

            function medirDistancia() {
                  var distance = mapa_distancia.distance(firstLatLng ,secondLatLng);
                  document.getElementById('distancia').value = distance;
            }
        </script>
        
</html>
