<html>
    <head>
        <script src="js/myscript.js"></script>
         <link rel = "stylesheet" href = "css/style.css" type = "text/css">
    </head>
    <body>
        <input type="text" name ="name" size ="20">
        <div id="map"></div>
        <div id="text"></div>
        <input onclick="showAddresses();" type=button value="Show Addresses">
        <!-- Replace the value of the key parameter with your own API key. -->
        <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANQOCjHtM98KBlAIkSur5qu9KbZF3LnLI&callback=initMap">
        </script>
    </body>
</html>


 