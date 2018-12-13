<?php
	ini_set('display_errors', 1);
	ini_set('display_startup_errors', 1);
	error_reporting(E_ALL);
    $database = new SQLite3('database.sqlite');
    $orderid = $_GET['orderid'];
    $userdeliver = $_GET['userdeliver'];
    $database->query("UPDATE orderinfo
				SET userdeliver = $userdeliver
				WHERE orderid = $orderid;");
?>