<?php

	$id = $_GET['id'];
	$url = 'https://navigationtest-4838a.firebaseio.com/Advertisements/'.$id.'.json';
	
	$curl = curl_init();
	
	curl_setopt( $curl, CURLOPT_URL, $url);
	curl_setopt( $curl, CURLOPT_CUSTOMREQUEST, "DELETE" );
	curl_setopt( $curl, CURLOPT_RETURNTRANSFER, true );
	$response = curl_exec( $curl );
	curl_close($curl);
	
	header("Location: tables.php");