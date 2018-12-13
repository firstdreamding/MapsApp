<?php
	ini_set('display_errors', 1);
	ini_set('display_startup_errors', 1);
	error_reporting(E_ALL);
	$database = new SQLite3('database.sqlite');
    $queryResults = $database->query("SELECT * FROM recipe");

        $row = array(); 

		$i = 0; 

        while($res = $queryResults->fetchArray(SQLITE3_ASSOC)){
        	$row[$i]['authorid'] = $res['authorid']; 
            $row[$i]['title'] = $res['title']; 
            $row[$i]['recipetext'] = $res['recipetext']; 
			$i++;
        } 
		echo json_encode($row);

?>