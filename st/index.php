<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <script src="js/myscript.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-filestyle.min.js"> </script>
    <link rel = "stylesheet" href = "css/style.css" type = "text/css">
</head>
<body>

    <section class="sec">
        <?php
        $url = "https://navigationtest-4838a.firebaseio.com/.json";
        $json_string = file_get_contents($url);
        $result = json_decode($json_string, true);
        $i = 0;
        echo '<table id="ver-minimalist">';
        echo '<tr><th>Index</th><th>Routes</th><th>Stations</th></tr>';
        while ($result['Stations'][$i] != null || $result['Routes'][$i] != null) {
            echo '<tr><td>' . $i . '</td><td>' . $result['Routes'][$i] . '</td><td>' . $result['Stations'][$i] . '</td></tr>';
            $i++;
        }
        echo '</table>';
        ?>
    </section>

    <section class="sec">
        <form action = "index.php" method = "POST" class = "form-group">
            <label for = "route">Name:</label>
            <input type = "text" class = "form-control" name = "route" id = "route" value = "<?php echo $_POST['route']; ?>" />
            <label for = "value">Route ID:</label>
            <input type = "text" class = "form-control" name = "value" id = "value" value = "<?php echo $_POST['value']; ?>"/>
            <label for = "dep_time">Deportation Time:</label>
            <input type = "text" class = "form-control" name = "dep_time" id = "dep_time" value = "<?php echo $_POST['dep_time']; ?>" />
            <label for = "arr_time">Arrival Time:</label>
            <input type = "text" class = "form-control" name = "arr_time" id = "arr_time" value = "<?php echo $_POST['arr_time']; ?>"/>
            <label for = "start_address">Start Address</label>
            <input type = "text" class = "form-control" name = "start_address" id = "start_address" value = "<?php echo $_POST['start_address']; ?>"/>
            <label for = "end_address">End Address</label>
            <input type = "text" class = "form-control" name = "end_address" id = "end_address" value = "<?php echo $_POST['end_address']; ?>"/>
            <label for = "stopovers">Stopovers</label>
            <input type = "text" class = "form-control" name = "stopovers" id = "stopovers" value = "<?php echo $_POST['stopovers']; ?>"/>
            <div class = "btn-group btn-group-justified">
                <div class = "btn-group">
                    <input type = "submit" name = "submit" class = "btn btn-primary" value = "Submit"/>
                </div>
                <div class = "btn-group">
                    <input type="file" class="filestyle" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" name = "upload" id = "upload" data-buttonText="Click for upload JSON">
                </div>
                <div class = "btn-group">
                    <input type = "submit" name = "export" class = "btn btn-primary" value = "Export JSON"/>
                </div>
            </div>
        </form>
    </section>
    
    <div id="map"></div>
    <div id="text"></div>
    <input onclick="showAddresses();" type=button value="Show Addresses">
    <!-- Replace the value of the key parameter with your own API key. -->
    <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANQOCjHtM98KBlAIkSur5qu9KbZF3LnLI&callback=initMap">
    </script>

    

</body>
</html>

<?php
include 'core/init.php';
require_once 'functions/sanitize.php';

if (isset($_POST['submit'])) {

    $city = trim(escape($_POST['route']));
    $value = trim(escape($_POST['value']));
    $dep_time = trim($_POST['dep_time']);
    $arr_time = trim($_POST['arr_time']);
    $end_address = trim(escape($_POST['end_address']));
    $start_address = trim(escape($_POST['start_address']));
    $stopover = trim(escape($_POST['stopovers']));

    $_passed = true;
    
    $dateObj1 = DateTime::createFromFormat('d.m.Y H:i', "29.12.2016 " . $dep_time);
    $dateObj2 = DateTime::createFromFormat('d.m.Y H:i', "29.12.2016 " . $arr_time);

    if (empty($city) || empty($value)) {
        echo '<div class="alert alert-warning">
                  <strong>Ooops!</strong> Name and Route ID fields have to be filled.
              </div>';
        $_passed = false;
    }
    else if ($dateObj1 === false || $dateObj2 === false) {
        echo '<div class="alert alert-warning">
                  <strong>Warning!</strong> HH : MM format is required.
              </div>';
        $_passed = false;
    } 
    else if ($_passed) {
        
        //whole update
        $url = "https://navigationtest-4838a.firebaseio.com/" . $city . ".json";
        $arr = array(
            $value => array(
                'allStopovers' => array(
                    '1' => $stopover,
                ),
                'arrTime' => $arr_time,
                'depTime' => $dep_time,
                'endAddress' => $end_address,
                'startAddress' => $start_address
            )
        );

        $data_string = json_encode($arr);
        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PATCH");
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);
        curl_setopt($ch, CURLOPT_HTTPHEADER, array(
            'Content-Type: application/json',
            'Content-Length: ' . strlen($data_string))
        );
        
        //route table update
        
        $url_for_routes = "https://navigationtest-4838a.firebaseio.com/Routes.json";
        $json_string_for_routes = file_get_contents($url_for_routes);
        $result_for_routes = json_decode($json_string_for_routes, true);
        $i_for_routes = 0;
        while ($result_for_routes[$i_for_routes] != null){
            $i_for_routes++;
        }
        $arr_for_routes = array(
            $i_for_routes => $city
        );

        $data_string_for_routes = json_encode($arr_for_routes);
        $ch_for_routes = curl_init($url_for_routes);
        curl_setopt($ch_for_routes, CURLOPT_CUSTOMREQUEST, "PATCH");
        curl_setopt($ch_for_routes, CURLOPT_POSTFIELDS, $data_string_for_routes);
        curl_setopt($ch_for_routes, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch_for_routes, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($ch_for_routes, CURLOPT_SSL_VERIFYPEER, 0);
        curl_setopt($ch_for_routes, CURLOPT_HTTPHEADER, array(
            'Content-Type: application/json',
            'Content-Length: ' . strlen($data_string_for_routes))
        );
        
        //stations table update
        
        $arr_explode = explode("-",$city);
        $count_same = 0;
        $same_string = "";
        
        $url_for_stations = "https://navigationtest-4838a.firebaseio.com/Stations.json";
        $json_string_for_stations = file_get_contents($url_for_stations);
        $result_for_stations = json_decode($json_string_for_stations, true);
        $i_for_stations = 0;
        while ($result_for_stations[$i_for_stations] != null){
            if($result_for_stations[$i_for_stations] == $arr_explode[0]){
                $same_string = $arr_explode[0];
                $count_same++;
            }
            else if($result_for_stations[$i_for_stations] == $arr_explode[1]){
                $same_string = $arr_explode[1];
                $count_same++;
            }
            $i_for_stations++;
        }
        
        if($count_same == 0){
            $arr_for_stations = array(
                $i_for_stations => $arr_explode[0],
                $i_for_stations + 1 => $arr_explode[1]
            );
        }
        else if($count_same == 1){
            $arr_for_stations = array(
                $i_for_stations => $same_string
            );
        }
        else {
            $arr_for_stations = null;
        }

        $data_string_for_stations = json_encode($arr_for_stations);
        $ch_for_stations = curl_init($url_for_stations);
        curl_setopt($ch_for_stations, CURLOPT_CUSTOMREQUEST, "PATCH");
        curl_setopt($ch_for_stations, CURLOPT_POSTFIELDS, $data_string_for_stations);
        curl_setopt($ch_for_stations, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch_for_stations, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($ch_for_stations, CURLOPT_SSL_VERIFYPEER, 0);
        curl_setopt($ch_for_stations, CURLOPT_HTTPHEADER, array(
            'Content-Type: application/json',
            'Content-Length: ' . strlen($data_string_for_stations))
        );
        
        
        
        if (curl_exec($ch) && curl_exec($ch_for_routes) && curl_exec($ch_for_stations)){
            echo    '<div class="alert alert-success" style = " float: right;margin:0;width:50%;text-align:center;">
                        <strong>OK!</strong> Everything passed successfully.
                    </div>';
        }
        else {
            echo    '<div class="alert alert-warning" style = "float: right;margin:0;background-color:  #ffb3b3;width:50%;text-align:center;">
                        <strong>Warning!</strong> Something went wrong, please try again.
                    </div>';
        }
    }
}

if (isset($_POST['export'])){
    $url = "https://navigationtest-4838a.firebaseio.com/.json?print=pretty&format=export&download=navigationtest-4838a-export.json";
    set_time_limit(0);
    //This is the file where we save the    information
    $fp = fopen ('localfile.json', 'w+') or die("Cant crreate");

    $ch = curl_init($url);
    curl_setopt($ch, CURLOPT_TIMEOUT, 50);

    curl_setopt($ch, CURLOPT_FILE, $fp); 
    curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);

    if(curl_exec($ch)){
        echo    '<script>alert("File is saved successfully")</script>';
    } 
   
    curl_close($ch);
    fclose($fp);
}

if (isset($_POST['upload'])){
    
        echo "Choose file";
    
}

