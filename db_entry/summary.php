<html>
<head>
	<meta charset="utf-8">
	<title>Summary</title>
</head>

<body>
	<?php 
	
	echo json_decode($_POST);
	
	//important info
	$title = $_POST["title"];
	$release = $_POST["release"];
	$rating = $_POST["rating"];
	$runtime = $_POST["runtime"];
	$genre = $_POST["genre"];
	$trailer = $_POST["trailer"];
	$poster_link = $_POST["poster_link"];
	$synopsis = $_POST["genre"];
	
	//storylines
	//movie
	$movieSLArray = array();
	foreach($_POST["movieStoryLine"] as $value){
		array_push($movieSLArray,$value);
	}
	$movieStoryLines = implode("<br>",$movieSLArray);
	
	//real
	$realSLArray = array();
	foreach($_POST["realStoryLine"] as $value){
		array_push($realSLArray,$value);
	}
	$realStoryLines = implode("<br>",$realSLArray);

	//Actors
	$actorArray = array();
	//name
	foreach($_POST["actorName"] as $value){
		array_push($actorArray,array($value));
	}
	//character
	foreach($_POST["characterName"] as $index => $value){
		array_push($actorArray[$index],$value);
	}
	//image
	foreach($_POST["imageLink"] as $index => $value){
		array_push($actorArray[$index],$value);
	}

	$str_json = file_get_contents( 'php://input' );
	print_r( $str_json );
	echo "<br>";

	//testing
	//	print_r($_POST["actorNameTest"]);

	//	
	//	
	//	//info
	//	foreach($_POST["actorDiv"] as $divIndex => $listOfValues){
	//		print_r($divIndex);
	//		print_r($listOfValues);
	//		array_push($actorArray[$divIndex],array());
	//		foreach($listOfValues["actorInfoDiv"] as $index => $value){
	//			print_r($value);
	//			array_push($actorArray[$divIndex][$index],$value);
	//		}
	//	}
	//	
	//	print_r($actorArray);

	//Key Information
	$part_of_a_series = $_POST[ "part_of_a_series" ];
	$franchise_location = $_POST[ "franchise_location" ];
	$timeline_location = $_POST[ "timeline_location" ];

	//Other
	$budget = $_POST[ "budget" ];
	$after_credit_scenes = $_POST[ "after_credit" ];


	//convert to JSON
	//declare variables
	$important_information = NULL;
	$key_storylines = NULL;
	$actors = NULL;
	$key_information = NULL;
	$other = NULL;
	$jObj = NULL;
	$jsonMovie = NULL;

	//importantInfo
	$important_information->title = $title;
	$important_information->release = $release;
	$important_information->rating = $rating;
	$important_information->runtime = $runtime;
	$important_information->genre = $genre;
	$important_information->trailer = $trailer;
	$important_information->poster_link = $poster_link;
	$important_information->synopsis = $synopsis;

	//storylines
	//movie
	$key_storylines->key_movie_storylines = $movieSLArray;
	//real
	$realLifeString = "key_real-life_storylines";
	$key_storylines->$realLifeString = $realSLArray;

	//actors

	//key information
	$part_of_series_string = "part_of_a_series?";
	$key_information->$part_of_series_string = $part_of_a_series;
	$key_information->franchise_location = $franchise_location;
	$key_information->timeline_location = $timeline_location;

	//other
	$other->budget = $budget;

	$after_credit_string = "after_credit_scenes?";
	$other->$after_credit_string = $after_credit_scenes;

	//compile	
	$jObj->important_information = $important_information;
	$jObj->key_storylines = $key_storylines;
	$jObj->key_information = $key_information;
	$jObj->other = $other;
	$jsonMovie = array( $title => $jObj );
	$jsonFormat = json_encode( $jsonMovie );
	//JSON_FORCE_OBJECT

	echo $jsonFormat;

	//	$jsonFile = '
	//	{
	//		'.$title.' : {
	//			"important_information" : {
	//			
	//				"title" : "'.$title.'",
	//				
	//			},
	//			
	//			"actors" : {
	//			
	//			},
	//			
	//			"other" : {
	//			
	//			}
	//		}
	//	}
	//	';

	//	var testMov = {
	//		"test" : {
	//			"title" : "movie",
	//			"genre" : "test"
	//		}
	//	
	//};


	//EMAIL

	$to = 'liamgooch@live.com.au';
	$cc = 'ljohnson3013@gmail.com';
	$subject = "CINEMATE - {$title}";
	$message = "
<html>
<head>
<h1>SUMMARY - Cinemate Database Entry</h1>
</head>

<body>

<h2>Important Information</h2>
<strong>Title:</strong> {$title}
<br>
<strong>Release:</strong> {$release}

<br>
<strong>Rating:</strong> {$rating}

<br>
<strong>Runtime (m):</strong> {$runtime}

<br>
<strong>Genre:</strong> {$genre}

<br>
<strong>Trailer:</strong> {$trailer}

<br>
<strong>Poster Link:</strong> {$poster_link}

<br>
<strong>Synopsis:</strong> {$synopsis}


<hr>

<h2>Key Storylines </h2>
	
<h3>Key Movie Storylines</h3>
	
<div id='movieStoryLineDiv'>
{$movieStoryLines}
</div>
	
<h3>Key Real-Life Storylines</h3>

	<div id='realStoryLineDiv'>
	{$realStoryLines}
	</div>
	
<hr>

<h2>Key Information</h2>
<strong>Part of a series?</strong>
{$part_of_a_series}
<br>
<strong>Franchise Location?</strong>
{$franchise_location}
<br>
<strong>Timeline Location?</strong>
{$timeline_location}

<hr>

<h2>Actors</h2>
	
	<div id='actorDiv'></div>
	

<hr>

<h2>Other</h2>
<strong>Budget:</strong>
{$budget}
<br>
<strong>After Credit Scene?</strong>
{$after_credit_scenes}
<br>
";
	echo $message;

	$message .= "
	
		<input type='button' value='APPROVE'>
		<input type='button' value='EDIT'>
		</body>
		</html>
	";
	//$headers = 'From: db_entry@cinemate.com' . "\r\n" .
	//    'Reply-To: ljohnson3013@gmail.com' . "\r\n";

	$headers[] = "MIME-Version: 1.0";
	$headers[] = "Content-Type: text/html; charset=UTF-8";

	$headers[] = "Cc: ljohnson3013@gmail.com";
	$headers[] = "From: db_entry@cinemate.com";
	$headers[] = "Reply-To: ljohnson3013@gmail.com";

	//	$success = mail($to, $subject, $message, implode("\r\n", $headers));
	//if (!$success) {
	//   print_r(error_get_last()['message']);
	//}

	//mail($to, $subject, $message, $headers);
	?>

	<input type="button" value="Go Back" onClick="goBack()"/>
	<script>
		function goBack() {
			window.location = "../index.html";
		}
	</script>
</body>

</html>