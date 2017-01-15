<!DOCTYPE html>
<html>
  <head>
    <title>Forms</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- jQuery UI -->
    <link href="https://code.jquery.com/ui/1.10.3/themes/redmond/jquery-ui.css" rel="stylesheet" media="screen">

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="css/styles.css" rel="stylesheet">
	<script src="js/myscript.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-filestyle.min.js"> </script>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link href="vendors/form-helpers/css/bootstrap-formhelpers.min.css" rel="stylesheet">
    <link href="vendors/select/bootstrap-select.min.css" rel="stylesheet">
    <link href="vendors/tags/css/bootstrap-tags.css" rel="stylesheet">

    <link href="css/forms.css" rel="stylesheet">
	<style>
#map {
  height: 80%;
}
html,
body {
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>
	
	
	
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- jQuery UI -->
    <script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>

    <script src="vendors/form-helpers/js/bootstrap-formhelpers.min.js"></script>

    <script src="vendors/select/bootstrap-select.min.js"></script>

    <script src="vendors/tags/js/bootstrap-tags.min.js"></script>

    <script src="vendors/mask/jquery.maskedinput.min.js"></script>

    <script src="vendors/moment/moment.min.js"></script>

    <script src="vendors/wizard/jquery.bootstrap.wizard.min.js"></script>

     <!-- bootstrap-datetimepicker -->
     <link href="vendors/bootstrap-datetimepicker/datetimepicker.css" rel="stylesheet">
     <script src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script> 


    <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
	<script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>

    <script src="js/custom.js"></script>
    <script src="js/forms.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-5">
	              <!-- Logo -->
	              <div class="logo">
	                 <h1><a href="index.html">Transportation Forms Page</a></h1>
	              </div>
	           </div>
	           <div class="col-md-5">
	              <div class="row">
	                <div class="col-lg-12">
	                  <div class="input-group form">
	                       <input type="text" class="form-control" placeholder="Search...">
	                       <span class="input-group-btn">
	                         <button class="btn btn-primary" type="button">Search</button>
	                       </span>
	                  </div>
	                </div>
	              </div>
	           </div>
	           <div class="col-md-2">
	              <div class="navbar navbar-inverse" role="banner">
	                  <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
	                    <ul class="nav navbar-nav">
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
	                        <ul class="dropdown-menu animated fadeInUp">
	                          <li><a href="profile.html">Profile</a></li>
	                          <li><a href="login.html">Logout</a></li>
	                        </ul>
	                      </li>
	                    </ul>
	                  </nav>
	              </div>
	           </div>
	        </div>
	     </div>
	</div>

    <div class="page-content">
    	<div class="row">
		  <div class="col-md-2">
		  	<div class="sidebar content-box" style="display: block;">
                <ul class="nav">
                    <!-- Main menu -->
                    <li><a href="index.php"><i class="glyphicon glyphicon-home"></i> Dashboard</a></li>
                    <li><a href="tables.php"><i class="glyphicon glyphicon-list"></i> Tables</a></li>
					<li class="current"><a href="forms.php"><i class="glyphicon glyphicon-list"></i>Forms</a></li>

                    <li class="submenu">
                         <a href="#">
                            <i class="glyphicon glyphicon-list"></i> Pages
                            <span class="caret pull-right"></span>
                         </a>
                         <!-- Sub menu -->
                         <ul>
                            <li><a href="login.html">Login</a></li>
                            <li><a href="signup.html">Signup</a></li>
                        </ul>
                    </li>
                </ul>
             </div>
		  </div>
		  <div class="col-md-10">
				
	  			
					<form action = "forms.php" method = "POST" class = "form-group">
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
						<label for = "selectday">Select Day</label>
			  			<p>
			  				<select name = "selectday[]" class="selectpicker" multiple>
								<option value = "Monday">Monday</option>
								<option value = "Tuesday">Tuesday</option>
								<option value = "Wednesday">Wednesday</option>
								<option value = "Thursay">Thursday</option>
								<option value = "Friday">Friday</option>
								<option value = "Saturday">Saturday</option>
								<option value = "Sunday">Sunday</option>
							</select>
			  			</p>
						<div class = "btn-group btn-group-justified">
							<div class = "btn-group">
								<input type = "submit" name = "submit" class = "btn btn-primary" value = "Submit"/>
							</div>
							<div class = "btn-group">
								<input type = "submit" name = "export" class = "btn btn-primary" value = "Export JSON"/>
							</div>
						</div>
					</form>	
			</div>	
	  		<!--  Page content -->
		  </div>
		</div>
		<div id="text"></div>
		<div id="map"></div>
		<input onclick="showAddresses();" type="button" value="Show Addresses">
		<!-- Replace the value of the key parameter with your own API key. -->
		<script async defer
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANQOCjHtM98KBlAIkSur5qu9KbZF3LnLI&callback=initMap">
		</script>
  </body>
</html>


<?php


if (isset($_POST['submit'])) {
	
    $city = trim(($_POST['route']));
    $value = trim(($_POST['value']));
    $dep_time = trim($_POST['dep_time']);
    $arr_time = trim($_POST['arr_time']);
    $end_address = trim(($_POST['end_address']));
    $start_address = trim(($_POST['start_address']));
    $stopover = trim(($_POST['stopovers']));
	$days = $_POST['selectday'];
	
	$stopoverS = explode(" ||",$stopover);
	$j = 0;
	foreach ($stop as $stopoverS){
		$j++;
	}
	
		  $arr = array(
            $value => array(
                'allStopovers' => array(
                    '0' => $stopoverS[0],
					'1' => $stopoverS[1],
					'2' => $stopoverS[2],
					'3' => $stopoverS[3],
					'4' => $stopoverS[4],
					'5' => $stopoverS[5],
					'6' => $stopoverS[6],
					'7' => $stopoverS[7]
                ),
                'arrTime' => $arr_time,
                'depTime' => $dep_time,
                'endAddress' => $end_address,
				'operatingDays' => array(
					'1' => $days[0],
					'2' => $days[1],
					'3' => $days[2],
					'4' => $days[3],
					'5' => $days[4],
					'6' => $days[5],
					'7' => $days[6]
				),
                'startAddress' => $start_address
            )
        );
	
	

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
    

    
}

?>