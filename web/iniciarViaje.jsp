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
        <%
           Class.forName("com.mysql.jdbc.Driver");
           
            Connection con;
             String connString = "jdbc:mysql://localhost/uber?"
                    + "user=root&password=12345&"
                    + "autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true" 
                    + "&useLegacyDatetimeCode=false" 
                    + "&serverTimezone=UTC";
            con = DriverManager.getConnection(connString);
            
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement("SELECT id, nombre from cliente");
            rs = ps.executeQuery();
            
            
            PreparedStatement psC;
            ResultSet rsC;
            psC = con.prepareStatement("SELECT id, nombre from conductor");
            rsC = psC.executeQuery();
            
            PreparedStatement psS;
            ResultSet rsS;
            psS = con.prepareStatement("SELECT id, nombre from catalogo_lugares_turisticos");
            rsS = psS.executeQuery();
            
            %>
            
       
        
        <form action="ViajesServlet" method="get">
            
           <h1>Iniciar viaje</h1>
            
            <br><br>
            <div style="margin-left: 5px; margin: 5px;">
                
                    Pasajero
                    <select name="cliente">
                        <% while(rs.next()){ %>
                        <option value="<%= rs.getInt("id")%>"><%= rs.getString("nombre")%></option>
                        <% } %>
                    </select>
                    <br><br>
                    Conductor
                    <select name="conductor">
                        <% while(rsC.next()){ %>
                        <option value="<%= rsC.getInt("id")%>"><%= rsC.getString("nombre")%></option>
                        <% } %>
                    </select>
                    <br><br>
                    Lugar
                    <select name="lugar">
                        <% while(rsS.next()){ %>
                        <option value="<%= rsS.getInt("id")%>"><%= rsS.getString("nombre")%></option>
                        <% } %>
                    </select>   
                    <br><br>
                   <label for="hora">Fecha y hora:</label>
                     <input type="datetime-local" id="hora" name="hora">
                    <br><br>
                    <input type="button" class = "geeks" onclick="getlocation()" value="Obtener ubicación actual"/>
                    <input type="text" readonly="true" name="latitud_inicial" id="latitud_inicial" placeholder="latitud" />
                    <input type="text" readonly="true" name="longitud_inicial" id="longitud_inicial" placeholder="longitud" />
            
                    <br><br>
                
                    Mueva el cursor para insertar ubicacion de destino:<br>
                    <div id="mapa_ubicacion" class="mapa"></div>
                    <input type="text" readonly="true" name="latitud_final" id="latitud_final" placeholder="latitud"/>
                    <input type="text" readonly="true" name="longitud_final" id="longitud_final" placeholder="longitud" /><br>
                    
                    <input type="submit" value="Ingresar"/>
                    <input type="hidden" name="formid" value="1"/>
                    <br><br>
                    <p><a href="index.html">Regresar al menu</a></p>
                    
                    <script>
        function getlocation(){ 
                navigator.geolocation.getCurrentPosition(showLoc); 
        }   
        function showLoc(pos){
                document.getElementById("latitud_final").value = pos.coords.latitude;
                document.getElementById("longitud_final").value = pos.coords.longitude;
            } 
        </script> 
                </form>
            </div>
    </body>
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
                            document.getElementById("latitud_final").value = this.getLatLng().lat;
                            document.getElementById("longitud_final").value = this.getLatLng().lng;
                        });
                        
                        
                        
                  }, function(error){console.log(error);}
                );
            }

            
        </script>
        <script>
        function getlocation(){ 
                navigator.geolocation.getCurrentPosition(showLoc); 
        }   
        function showLoc(pos){
                document.getElementById("latitud_inicial").value = pos.coords.latitude;
                document.getElementById("longitud_inicial").value = pos.coords.longitude;
            } 
        </script> 
</html>
