<?php
	ini_set('display_errors', 1);
	ini_set('display_startup_errors', 1);
	error_reporting(E_ALL);
	$database = new SQLite3('database.sqlite');
    $queryResults = $database->query("SELECT * FROM orderinfo WHERE userdeliver is null");

        $row = array(); 

		$i = 0; 

        while($res = $queryResults->fetchArray(SQLITE3_ASSOC)){
        	$row[$i]['orderid'] = $res['orderid']; 
            $row[$i]['userorder'] = $res['userorder']; 
            $row[$i]['deliveryinfo'] = $res['deliveryinfo']; 
			$i++;
        } 
		echo json_encode($row);
?>