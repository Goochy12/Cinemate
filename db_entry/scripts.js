// JavaScript Document
var sLV = 0;
var actorCount = 0;

function addStorylineButtonOnClick(storyLine, storyLineDiv) {
	//	var boxID = "SL"+ sLV++ + "";
	var boxID = storyLine + "[]";
	//	var storyLineDiv = storyLine + "Div";

	var div = document.createElement('div');

	var input = document.createElement('input');
	input.type = "text";
	//	input.id = boxID;
	input.name = boxID;
	input.minlength = 1;

	var removeButton = document.createElement('input');
	removeButton.type = "button";
	removeButton.addEventListener('click', function () {
		removeDiv(storyLineDiv, div);
	});
	removeButton.value = "Remove";

	div.appendChild(input);
	div.appendChild(removeButton);

	document.getElementById(storyLineDiv).appendChild(div);
}

function removeDiv(divLocation, div) {
	document.getElementById(divLocation).removeChild(div);
}

function removeDivByDiv(div) {
	div.parentNode.removeChild(div);
}

function addActorInfo(actorInfoDiv, location) {
	var boxID = "actorInfo";
	var div = document.createElement('div');

	var input = document.createElement('input');
	input.type = "text";
	//	input.id = boxID;
	input.className = boxID;

	var removeButton = document.createElement('input');
	removeButton.type = "button";
	removeButton.addEventListener('click', function () {
		removeDivByDiv(div);
	});
	removeButton.value = "Remove";

	div.appendChild(input);
	div.appendChild(removeButton);

	actorInfoDiv.appendChild(div);
}

function addActor(divLocation) {
	actorCount++;

	//elements - name, character, image, other info, remove button

	var actorDiv = document.createElement('div');
	actorDiv.className = "actor";
	//		actorDiv.id = actorDivID;
	var br = document.createElement('br');

	//remove button
	var removeActorButton = document.createElement('input');
	removeActorButton.type = 'button';
	removeActorButton.value = "REMOVE ACTOR";
	removeActorButton.addEventListener('click', function () {
		removeDiv('actorDivHTML', actorDiv);
	});

	//actor name
	var actorNameText = document.createTextNode("Actor Name: ");
	var actorName = document.createElement('input');
	actorName.type = "text";
	actorName.name = "actorName[]";

	var actorNameDiv = document.createElement("div");
	actorNameDiv.appendChild(actorNameText);
	actorNameDiv.appendChild(actorName);

	//character name
	var characterNameText = document.createTextNode("Character Name: ");
	var characterName = document.createElement('input');
	characterName.type = "text";
	characterName.name = "characterName[]";

	var charNameDiv = document.createElement("div");
	charNameDiv.appendChild(characterNameText);
	charNameDiv.appendChild(characterName);

	//image link
	var imageLinkText = document.createTextNode("Image Link: ");
	var imageLink = document.createElement('input');
	imageLink.type = "link";
	imageLink.name = "imageLink[]";

	var imageLinkDiv = document.createElement("div");
	imageLinkDiv.appendChild(imageLinkText);
	imageLinkDiv.appendChild(imageLink);

	//other info
	var addInfoDiv = document.createElement("div");
	addInfoDiv.className = "actorInfoDiv";

	var addInfoButton = document.createElement('input');
	addInfoButton.type = "button";
	addInfoButton.value = "Add Info";
	addInfoButton.addEventListener('click', function () {
		addActorInfo(addInfoDiv);
	});

	//		addInfoDiv.name = "actorInfoDiv[]";

	//append all to div
	actorDiv.appendChild(removeActorButton);
	actorDiv.appendChild(actorNameDiv);
	actorDiv.appendChild(charNameDiv);
	actorDiv.appendChild(imageLinkDiv);
	actorDiv.appendChild(addInfoDiv);
	actorDiv.appendChild(addInfoButton);

	actorDiv.appendChild(br.cloneNode(true));
	actorDiv.appendChild(br.cloneNode(true));


	document.getElementById(divLocation).appendChild(actorDiv);

}

function verify(form) {
	
	console.log("Verifying form");
	//list of empty fields
	var emptyFields = [];
//	//check important info
//	//title
//	if(form.title.value === ""){
//		emptyFields.push("Title");
//	}
//	//release
//	if(form.release.value === ""){
//		emptyFields.push("Release");
//	}
//	//rating
//	if(form.rating.value === ""){
//		emptyFields.push("Rating");
//	}
//	//runtime
//	if(form.runtime.value === ""){
//		emptyFields.push("Runtime");
//	}
//	//genre
//	if(form.genre.value === ""){
//		emptyFields.push("Genre");
//	}
//	//trailer
//	if(form.trailer.value === ""){
//		emptyFields.push("Trailer");
//	}
//	//poster
//	if(form.poster.value === ""){
//		emptyFields.push("Poster");
//	}
//	//synopsis
//	if(form.synopsis.value === ""){
//		emptyFields.push("Synopsis");
//	}
	
	
	//part_of_a_series
	if(form.part_of_a_series.value === ""){
		emptyFields.push("Part of a Series");
	}
	//franchise_location
	if(form.franchise_location.value === ""){
		emptyFields.push("Franchise Location");
	}
	//timeline_location
	if(form.timeline_location.value === ""){
		emptyFields.push("Timeline Location");
	}
	
	//actors
//	if(form.getElementsByClassName === ""){
//		emptyFields.push("Budget");
//	}
	
	//budget
	if(form.budget.value === ""){
		emptyFields.push("Budget");
	}
	//after credits
	if(form.after_credit.value === ""){
		emptyFields.push("After Credit Scene");
	}
	
	
	if(emptyFields.length > 0){
		console.log("failed to verify form");
		var notice = "You haven't filled out ";
		for(var i=0; i < emptyFields.length; i++){
			notice += "\"";
			if(i === emptyFields.length - 1){
				notice += emptyFields[i] + "\"";
			}else{
				if(i === emptyFields.length - 2){
					notice += emptyFields[i] + "\" and ";
				}else{
					notice += emptyFields[i] + "\", ";
				}
			}
		}
		notice += "! Would you like to continue?";
		if(confirm(notice)){
			return true;
		}
		return false;
	}
}

function compile(form) {
	//important info
	var title = form.title.value;
	var release = form.release.value;
	var genre = form.genre.value;
	var runtime = form.runtime.value;
	var trailer = form.trailer.value;
	var poster = form.poster.value;
	var synopsis = form.synopsis.value;

	alert("Title: " + title + ", Release: " + release);


}

function resetForm() {
	window.location.reload(false);
}

function parseActorData(form) {
	//var form = formID;
	
	var infoDivs = form.getElementsByClassName("actorInfoDiv");
	var info = [];
	
	//push each actor div - contains all info
	var i=0;	
	for (i; i < infoDivs.length; i++) {
		info.push([...infoDivs[i].getElementsByClassName("actorInfo")]);
	}
	
	//extrct the info from each group - actor
	var extractedInfo = [];
	for (var j = 0; j < info.length; j++) {
		var ind = [];
		for (var k = 0; k < info[j].length; k++) {
			ind.push(info[j][k].value);
		}
		extractedInfo.push(ind);
	}
//	console.log(extractedInfo);
	return extractedInfo;
	
	    //POST to PHP
//    request = new XMLHttpRequest();
//    request.open( "POST", "summary.php", true );
//    request.setRequestHeader( "Content-type", "application/json" );
//    request.send( JSON.stringify( extractedInfo ) );
	
}