<?php

	$id = $_GET['id'];
	$url = 'https://navigationtest-4838a.firebaseio.com/Routes/'.$id.'.json';
	
	$json_string = file_get_contents($url);
	$routeName = json_decode($json_string, true);
	
	$url2 = 'https://navigationtest-4838a.firebaseio.com/'.$routeName.'.json';
	
	$curl = curl_init();
	$curl2 = curl_init();
	
	curl_setopt( $curl, CURLOPT_URL, $url);
	curl_setopt( $curl, CURLOPT_CUSTOMREQUEST, "DELETE" );
	curl_setopt( $curl, CURLOPT_RETURNTRANSFER, true );
	$response = curl_exec( $curl );
	curl_close($curl);
	
	curl_setopt( $curl2, CURLOPT_URL, $url2);
	curl_setopt( $curl2, CURLOPT_CUSTOMREQUEST, "DELETE" );
	curl_setopt( $curl2, CURLOPT_RETURNTRANSFER, true );
	$response2 = curl_exec( $curl2 );
	curl_close($curl2);
	
	header("Location: tables.php");
							