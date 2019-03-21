//<script src="https://www.gstatic.com/firebasejs/5.8.6/firebase.js"></script>
//<script>
//  // Initialize Firebase
//  var config = {
//    apiKey: "AIzaSyDQn-bj2_3yxW9m06DloFk8lzCfCGHc5Xg",
//    authDomain: "cinemate-liamgooch.firebaseapp.com",
//    databaseURL: "https://cinemate-liamgooch.firebaseio.com",
//    projectId: "cinemate-liamgooch",
//    storageBucket: "cinemate-liamgooch.appspot.com",
//    messagingSenderId: "974238781317"
//  };
//  firebase.initializeApp(config);
//</script>


var firebase = require("firebase");

var config = {
	apiKey: "AIzaSyDQn-bj2_3yxW9m06DloFk8lzCfCGHc5Xg",
	authDomain: "cinemate-liamgooch.firebaseapp.com",
	databaseURL: "https://cinemate-liamgooch.firebaseio.com",
	storageBucket: "cinemate-liamgooch.appspot.com"
};

firebase.initializeApp(config);

// Get a reference to the database service
var database = firebase.database();

function addMovie(movieJSON) {
	database.ref('new_releases/').update(movieJSON);
}
