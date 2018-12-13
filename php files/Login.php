<?php
	ini_set('display_errors', 1);
	ini_set('display_startup_errors', 1);
	error_reporting(E_ALL);
    $database = new SQLite3('database.sqlite');
    $username = $_GET['username'];
    $queryResults = $database->query("SELECT * FROM login WHERE username=$username;");
    $results = $queryResults->fetchArray();
    echo $results['passhash'];
?>