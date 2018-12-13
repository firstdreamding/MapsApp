<?php
    $database = new SQLite3('database.sqlite');
    $authorid = $_GET['authorid'];
    $title = $_GET['title'];
    $recipetext = $_GET['recipetext'];
    $database->query("INSERT OR REPLACE INTO recipe (authorid, title, recipetext) VALUES (
            $authorid, 
            $title,
            $recipetext);");
?>