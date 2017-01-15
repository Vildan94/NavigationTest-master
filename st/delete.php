<?php

$id = $_GET['id'];
$firebase = "https://navigationtest-4838a.firebaseio.com/Advertisements[0].json";


$data = array(
    "Advertisements" => $id
);
// JSON encoded
$json = json_encode($data);

$curl = curl_init();

curl_setopt( $curl, CURLOPT_URL, $firebase);
curl_setopt($curl, CURLOPT_POSTFIELDS, $json);
curl_setopt( $curl, CURLOPT_CUSTOMREQUEST, "DELETE" );
curl_setopt( $curl, CURLOPT_RETURNTRANSFER, true );
curl_setopt($ch, CURLOPT_HTTPHEADER, array(
            'Content-Type: application/json',
            'Content-Length: ' . strlen($json))
        );
$response = curl_exec( $curl );
curl_close($curl);
// Show result
echo $response . "\n";

