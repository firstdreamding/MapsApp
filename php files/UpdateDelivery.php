<?php
    $database = new SQLite3('database.sqlite');
    $id = $_GET['id'];
    $x = $_GET['x'];
    $y = $_GET['y'];
    $database->query("INSERT OR REPLACE INTO delivery (id, x, y) VALUES (
            $id, 
            $x,
            $y);");
    //echo "<h2>$id</h2>";
?>