<html>
<head>
	<meta charset="utf-8">
	<title>Summary</title>
</head>

<body>
	<?php

	//important info
	$title = $_POST[ "title" ];
	$release = $_POST[ "release" ];
	$rating = $_POST[ "rating" ];
	$runtime = $_POST[ "runtime" ];
	$genre = $_POST[ "genre" ];
	$trailer = $_POST[ "trailer" ];
	$poster_link = $_POST[ "poster_link" ];
	$synopsis = $_POST[ "genre" ];

	$dbEntry = strtolower( $title );
	$dbEntry = str_replace( " ", "_", $dbEntry );
	//add release date

	//storylines
	//movie
	$movieSLArray = array();
	foreach ( $_POST[ "movieStoryLine" ] as $value ) {
		array_push( $movieSLArray, $value );
	}
	$movieStoryLines = implode( "<br><li>", $movieSLArray );

	//real
	$realSLArray = array();
	foreach ( $_POST[ "realStoryLine" ] as $value ) {
		array_push( $realSLArray, $value );
	}
	$realStoryLines = implode( "<br><li>", $realSLArray );

	//Actors
	$actorArray = array();
	//name
	foreach ( $_POST[ "actorName" ] as $index => $value ) {
		array_push( $actorArray, array( $value ) );
	}

	//character
	foreach ( $_POST[ "characterName" ] as $index => $value ) {
		array_push( $actorArray[ $index ], $value );
	}

	//image
	foreach ( $_POST[ "imageLink" ] as $index => $value ) {
		array_push( $actorArray[ $index ], $value );
	}

	$actorsInfo = json_decode( $_POST[ "actorsInf" ] );
	foreach ( $actorsInfo as $infoIndex => $infoSet ) {
		$info = array();
		foreach ( $infoSet as $index => $value ) {
			array_push( $info, $value );
		}
		array_push( $actorArray[ $infoIndex ], $info );
	}

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
	//	$actors->$actorArray;
	$ac = array();
	foreach ( $actorArray as $actorIndex => $actorValues ) {
		$actorDeats = array(
			"name" => $actorValues[ 0 ],
			"character" => $actorValues[ 1 ],
			"image" => $actorValues[ 2 ],
			"info" => $actorValues[ 3 ]
		);
		array_push( $ac, $actorDeats );
	}

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
	$jObj->actors = $ac;
	$jObj->key_information = $key_information;
	$jObj->other = $other;
	$jsonTitle = $title . $release;
	$jsonMovie = array( $jsonTitle => $jObj );
	$jsonFormat = json_encode( $jsonMovie );
	//JSON_FORCE_OBJECT

	echo "json format: ";
	echo $jsonFormat;

	//convert actors to html	
	$htmlStr = "";
	foreach ( $actorArray as $index => $value ) {
		$htmlStr .= "<b>Name: </b>";
		$htmlStr .= $value[ 0 ];
		$htmlStr .= "<br>";

		$htmlStr .= "<b>Character: </b>";
		$htmlStr .= $value[ 1 ];
		$htmlStr .= "<br>";

		$htmlStr .= "<b>Image: </b>";
		$htmlStr .= $value[ 2 ];
		$htmlStr .= "<br>";

		$htmlStr .= "<li>";
		$htmlStr .= implode( "<br><li>", $value[ 3 ] );

		$htmlStr .= "<br>";
		$htmlStr .= "<br>";

	}

	//EMAIL
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
<li>
{$movieStoryLines}
</div>
	
<h3>Key Real-Life Storylines</h3>

	<div id='realStoryLineDiv'>
	<li>
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
	
	<div id='actorDiv'>
		{$htmlStr}
		<br>
	</div>
	

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

	function sendEmail( $client, $title, $message ) {
		//------- SERVER SIDED MAILING CODE ONLY ---------
		$to = "";
		if ( $client ) {
			$to = 'ljohnson3013@gmail.com';
		} else {
			$to = 'liamgooch@live.com.au';
		}

		$subject = "CINEMATE - {$title}";

		$headers[] = "MIME-Version: 1.0";
		$headers[] = "Content-Type: text/html; charset=UTF-8";

		//	$headers[] = "Cc: ljohnson3013@gmail.com";
		$headers[] = "From: db_entry@cinemate.com";
		$headers[] = "Reply-To: ljohnson3013@gmail.com";

		$success = mail( $to, $subject, $message, implode( "\r\n", $headers ) );
		if ( !$success ) {
			print_r( error_get_last()[ 'message' ] );
		}

//		mail( $to, $subject, $message, implode( "\r\n", $headers ) );
	}

//	sendEmail( True, $title, $message );

	$message .= "
	
		<a href='goochystesting.tk/submitJSON.php?approved=1&jsonCode={$jsonFormat}' target='_blank'>Approve</a>
		
		<br>
		
		<a href='goochystesting.tk/submitJSON.php?approved=0&jsonCode={$jsonFormat}' target='_blank'>Edit</a>
		
		</body>
		</html>
	";

	sendEmail( False, $title, $message );

	?>

	<input type="button" value="Go Back" onClick="goBack()"/>
	<script>
		function goBack() {
			window.location = "../index.html";
		}
	</script>
</body>

</html>