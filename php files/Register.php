<?php
    $database = new SQLite3('database.sqlite');
    $username = $_GET['username'];
    $passhash = $_GET['passhash'];
    $database->query("INSERT OR REPLACE INTO login (username, passhash) VALUES (
            $username, 
            $passhash);");
?>