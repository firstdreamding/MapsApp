<?php
	ini_set('display_errors', 1);
	ini_set('display_startup_errors', 1);
	error_reporting(E_ALL);
    $database = new SQLite3('database.sqlite');
    $orderid = $_GET['orderid'];
    $x = $_GET['x'];
    $y = $_GET['y'];
    $database->query("UPDATE orderinfo
				SET x = $x, y = $y
				WHERE orderid = $orderid;");
?>