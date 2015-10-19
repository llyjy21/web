var express = require('express');

var app = express();

var request = require('request');

var leboncoin = require('./leboncoin');

var lacentrale = require('./lacentrale');

var path = require('path');

var bodyParser = require('body-parser');

var cheerio = require('cheerio');

var async = require('async');


app.set('views', path.join(__dirname, 'views'));

app.use(express.static(path.join(__dirname, 'public')));

app.use(bodyParser.json()); // support json encoded bodies

app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodies


app.post('/scrape', function(req, res) {
     
     var url = req.body.input_value;
     
     request(url, function(error, response, html) {                      // two parameters: an URL and a callback
             
             if(!error) {
             
                 var json = leboncoin(html);
                 
                 lacentrale.getPrixs(json, function myCallback(result){res.json(result);});
             
             }
             
     });

});

app.post('/scrapes', function(req, res) {
     
     var url = req.body.input_value;
     
     var leboncoins_prixs = new Array();
     
     var average_prixs = new Array();
         
     var result_json = {};
     
     request(url, function(error, response, html) {                      // two parameters: an URL and a callback
             
             if(!error) {
             
                 var $ = cheerio.load(html);
                 
                 var sub_url;
                 
                 var each_data;
                 
                 var json_leboncoin;
                 
                 async.forEachOfSeries($('.list-lbc').find("a"), function(value, key, callback) {
                         
                         each_data = $(value);
                         
                         sub_url = each_data.attr("href");
                         
                         request(sub_url, function(error, response, html) {                      // two parameters: an URL and a callback
                                 
                                 if(!error) {
                                 
                                     json_leboncoin = leboncoin(html);
                                     
                                     var json = JSON.parse(json_leboncoin);
                                     
                                     leboncoins_prixs[key] = parseInt(json.prix,10);
                                 
                                     lacentrale.getPrixs(json_leboncoin, function myCallback(result) {
                                                         
                                            average_prixs[key] = result.average;
                                            
                                            callback();
                                            
                                      });
                                
                                 }
                          
                         });
                                 
                 // Handle result after all the prices of the records are recorded
                 }, function(err){
                 
                     if( err ) {
                     
                             console.log('Error happend!');
                     
                     } else {
                     
                             console.log("--- I am going to send the average and the prices of leboncoin to the front-end");
                             
                             result_json["average"] = average_prixs;
                             
                             result_json["leboncoins"] = leboncoins_prixs;
                             
                             console.log("Data is:" + JSON.stringify(result_json));
                             
                             res.json(JSON.stringify(result_json));
                     
                     }
                 
                 });
             
             }
             
     });

});


app.post('/test', function(req, res) {
        
     var json = {"average":[0,17192,20355,15280,15777,20355,0,0,0,24733,0,8410,0,0,14000],"leboncoins":[14999,22500,22890,17900,17990,20600,9990,16000,13990,22990,13290,8990,12490,25990,17800]};
         
     res.send(JSON.stringify(json));
        
});


app.get('/index', function(req, res) {
    
        res.sendFile(path.join(__dirname, '/views', 'index.html'));
    
});


var server = app.listen(3000, function () {
                    
        var host = server.address().address;
        
        var port = server.address().port;
        
        console.log('App is listening at http://%s:%s', host, port);
                    
});