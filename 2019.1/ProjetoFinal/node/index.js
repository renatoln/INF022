var express = require("express");
    app = express();
    bodyParser = require("body-parser");

app.use(express.static(__dirname + '/../'));
app.use(bodyParser.urlencoded( {extended: true} ));

app.get('/mapa', function(req, res){
    res.sendFile(__dirname + "../index.html");
});

app.get('*', function (_request, response){
	response.redirect('/mapa');
});

app.listen(3001, function(){
	console.log('App rodando em http://localhost:3001...');
});
