<?php
$approved = $_POST['approved'];
$jsonData = $_POST['jsonFormat'];

if($approved ==="1"){
	addMovie()
}else{
	


$page = '
<html version="0.1">
<head>
<meta charset="utf-8">
<script type="text/javascript" src="scripts.js?v=0.2" ></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Cinemate Database Entry</title>
<h1>Cinemate Database Entry</h1>
</head>

<body>
<input type="button" name="resetForm" value="RESET FORM" onclick="resetForm()"/>
<!--action="/action_page.php"--> 
<!--	onSubmit="compile(this)"--> 
<!--	name="movieDataForm" action="summary.php" method="post"-->
<form id="movieDataForm" action="summary.php" method="post">
  <h2>Important Information</h2>
  <strong>Title:</strong>
  <input type="text" name="title" required minlength="1">
  <br>
  <strong>Release:</strong>
  <input type="date" name="release" required minlength="1">
  <br>
  <strong>Rating:</strong>
  <input type="text" name="rating" required minlength="1" maxlength="2">
  <br>
  <strong>Runtime (m):</strong>
  <input type="number" name="runtime" required min="1" max="999">
  <br>
  <strong>Genre:</strong>
  <input type="text" name="genre" required minlength="1">
  <br>
  <strong>Trailer:</strong>
  <input type="link" name="trailer">
  <br>
  <strong>Poster Link:</strong>
  <input type="link" name="poster_link" required minlength="3">
  <br>
  <strong>Synopsis:</strong>
  <input type="text" name="synopsis" required minlength="1">
  <hr>
  <h2>Key Storylines </h2>
  <h3>Key Movie Storylines</h3>
  <div id="movieStoryLineDiv"></div>
  <button type="button" onclick="addStorylineButtonOnClick("movieStoryLine","movieStoryLineDiv")">Add Storyline</button>
  <h3>Key Real-Life Storylines</h3>
  <div id="realStoryLineDiv"></div>
  <button type="button" onclick="addStorylineButtonOnClick("realStoryLine","realStoryLineDiv")">Add Storyline</button>
  <hr>
  <h2>Key Information</h2>
  <strong>Part of a series?</strong>
  <input type="text" name="part_of_a_series">
  <br>
  <strong>Franchise Location?</strong>
  <input type="text" name="franchise_location">
  <br>
  <strong>Timeline Location?</strong>
  <input type="text" name="timeline_location">
  <hr>
  <h2>Actors</h2>
  <div id="actorDivHTML"></div>
  <button type="button" onclick="addActor("actorDivHTML")">Add Actor</button>
  <hr>
  <h2>Other</h2>
  <strong>Budget:</strong>
  <input type="text" name="budget">
  <br>
  <strong>After Credit Scene?</strong>
  <input type="text" name="after_credit">
  <br>
  <input type="submit" name="sub_button" value="Submit">
</form>

</body>
</html>
		
		';





}
	
function addMovie(){
	echo "So in theory this has uploaded to the database";
?>
<!--
<script>
	addMovie($jsonData);
</script>
-->
<?php	
}
?>