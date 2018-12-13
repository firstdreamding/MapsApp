<?php
    $database = new SQLite3('database.sqlite');
    $userorder = $_GET['userorder'];
    $deliveryinfo = $_GET['deliveryinfo'];
    $database->query("INSERT OR REPLACE INTO orderinfo (userorder, deliveryinfo) VALUES (
            $userorder, 
            $deliveryinfo);");
    $queryResults = $database->query("SELECT * FROM orderinfo ORDER BY orderid DESC LIMIT 1;");	
    $results = $queryResults->fetchArray();
    echo $results['orderid'];
?>