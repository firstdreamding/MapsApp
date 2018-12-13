<?php
    $database = new SQLite3('database.sqlite');
    $id = $_GET['id'];
    $queryResults = $database->query("SELECT * FROM delivery WHERE id=$id;");
    $results = $queryResults->fetchArray();
    echo $results['x'] . " " . $results['y'];
?>