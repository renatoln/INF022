var express = require("express");
    app = express();
    bodyParser = require("body-parser")
    path = require("path");

app.use(express.static(__dirname + '/../'));
app.use(bodyParser.urlencoded( {extended: true} ));

app.get('/', function(req, res){
    res.sendFile(__dirname + "/../index.html");
});

app.get('/es', function(req, res){
    res.sendFile(path.resolve(__dirname + "/../index_es.html"));
});

app.get('*', function (_request, response){
	response.redirect('/');
});

app.listen(3001, function(){
	console.log('App rodando em http://localhost:3001...');
});
