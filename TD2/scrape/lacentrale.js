var request = require('request');

var cheerio = require('cheerio');

var async = require('async');

var random_useragent = require('random-useragent');

module.exports = {
    
    getPrixs: function (jsonStr, myCallback) {
        
        var marque, modele, annee_modele, energie, boite_de_vitesses;
        
        var main_url = "";
        
        var resultLength;
        
        var sub_url;
        
        var json = JSON.parse(jsonStr);
        
        //la cote argue, total data, minimum value, discriptions
        var result_json = {average: 0, total: 0, low: 0, dis: ""};
        
        var prix;
        
        prix = json.prix;
        
        marque = json.marque;
        
        modele = json.modele;
        
        annee_modele = json.annee_modele;
        
        energie = json.energie;
        
        boite_de_vitesses = json.boite_de_vitesses;
        
        main_url = "http://www.lacentrale.fr/cote-voitures-" + marque + "-" + modele + "--" + annee_modele + "-.html";
        
        // gets a random user agent string
        var user_agent = random_useragent.getRandom();
       
        // Set the headers
        var headers = {
            
            'User-Agent':        user_agent,
            
            'Content-Type':     'application/x-www-form-urlencoded'
            
        }
        var options;
        
        var sub_options;
        
        options = {
            
            url: main_url,
            
            method: 'GET',
            
            headers: headers
        }
        
        request(options, function(error, response, html) {                      // two parameters: an URL and a callback function
                
                if(!error) {
                
                    var $ = cheerio.load(html);
                
                    resultLength = $('.tdSD.QuotMarque').length;
                
                    var en, boite;
                
                    var prixs = new Array();
                
                    var t = 0;
                
                    var h = 0;
                
                    async.forEachOf($('.tdSD.QuotMarque'), function(value, key, callback) {
                            
                            t++;
                                    
                            var each_data = $(value);
                                    
                            // add other conditions(energie and boite de vitesses), improve the accuracy of the results
                            var en = (each_data.siblings(".tdSD.QuotNrj").text()).replace(/^\s+|\s+$/g,'');
                                    
                            var boite = (each_data.siblings(".tdSD.QuotBoite").text()).replace(/^\s+|\s+$/g,'');
                                    
                            // lowercase all the lettres to avoid mistakes. For example:Autres and autre
                            en = en.toLowerCase();
                                    
                            energie = energie.toLowerCase();
                                    
                            var index = en.indexOf(energie);
                            
                            // inquiry the price whose record meets the conditions
                            if((index !== -1 || energie.toLowerCase() === "autre"|| energie.toLowerCase() === "autres") && (boite.toLowerCase() === boite_de_vitesses.toLowerCase())) {
                                    
                                    sub_url = "http://www.lacentrale.fr/" + each_data.find("a").attr("href");
                                    
                                    // Configure the request
                                    sub_options = {
                                    
                                        url: sub_url,
                                        
                                        method: 'GET',
                                        
                                        headers: headers
                                    
                                    }
                                    
                                    request(sub_options, function(error, response, html) {     // two parameters: an URL and a callback
                                    
                                        if(!error) {
                                    
                                            var $ = cheerio.load(html);
                                        
                                            prixs[h] = ($(".Result_Cote.arial.tx20").text()).replace(/[^0-9]/ig,"");
                                            
                                            h++;
                                            
                                            callback();
                                            
                                        }
                                            
                                    });
                                    
                            }else {
                                    
                                    callback();
                                    
                            }
                                    
                    // Handle result after all the prices of the records are recorded
                    }, function(err){
                               
                        if( err ) {
                        
                            console.log('Error happend!');
                                    
                            myCallback("no result");
                        
                        } else {
                        
                            console.log(main_url);
                        
                            console.log("Total records: " + t + ", effective record: " + h);
                                    
                            if(prixs.length !== 0){
                                    
                                result_json.total = prixs.length;
                    
                                for(var i=0; i<prixs.length; i++){
                        
                                    result_json.average += (prixs[i] / prixs.length);
                        
                                    if(prixs[i] > prix) {
                        
                                        result_json.low++;
                        
                                    }
                        
                                }
                                   
                                result_json.average = Math.round(result_json.average);
                                    
                                result_json.dis = marque + "-" + modele + "--" + annee_modele;
                                    
                                console.log("---Thers is 'la cote Argus' for this car------");
                        
                                myCallback(result_json);
                                
                            }else {
                                
                                result_json.total = 0;
                                    
                                result_json.average = 0;
                                
                                result_json.low = 0;
                                    
                                result_json.dis = marque + "-" + modele + "--" + annee_modele;
                                    
                                console.log("---There is no 'la cote Argus' for this car------");
                                    
                                myCallback(result_json);
                                    
                            }
                        
                        }
                     
                    });
        
                }
                    
        });
    }
};