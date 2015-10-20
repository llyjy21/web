var cheerio = require('cheerio');

module.exports = function (html) {
    
    var $ = cheerio.load(html);
    
    // Define the variables to be captured.
    var prix, marque, modele, annee_modele, energie, boite_de_vitesses, kilometrage;
    
    //json
    var json = {prix: "", marque: "", modele: "", annee_modele: "", energie: "", boite_de_vitesses: "", kilometrage: ""};
    
    $('span.price').filter(function() {
                           
                       var data = $(this);
                           
                       prix = data.text();
                           
                       json.prix = prix.replace(/[^0-9]/ig,"");
                           
    });
    
    $('.lbcParams.criterias').filter(function() {
                                     
                           var data = $(this);
                                     
                           marque = (data.find("td").eq(0).text()).replace(/^\s+|\s+$/g,'');
                                     
                           modele = (data.find("td").eq(1).text()).replace(/^\s+|\s+$/g,'');
                                     
                           annee_modele = (data.find("td").eq(2).text()).replace(/^\s+|\s+$/g,'');
                                     
                           kilometrage = (data.find("td").eq(3).text()).replace(/[^0-9]/ig,"");
                                     
                           energie = (data.find("td").eq(4).text()).replace(/^\s+|\s+$/g,'');
                                     
                           boite_de_vitesses = (data.find("td").eq(5).text()).replace(/^\s+|\s+$/g,'');
                                     
                           //If the value of model is "Autres", get the value of modele from the title
                           if(modele === "Autres") {
                                     
                                 var title = $('#ad_subject').text();
                                     
                                 title = title.replace("executive", "");
                                     
                                 var regExp = new RegExp(marque, 'gi');
                                     
                                 title = title.replace(regExp, "");
                                     
                                 var flag_str = title.match(/\b\d{3}.\b/);
                                     
                                 var index = title.indexOf(flag_str);
                                     
                                 modele = title.substring(0, index);
                                     
                                 modele = modele.replace(/^\s+|\s+$/g,'');
                                     
                           }
                                     
                           json.marque = marque;
                                     
                           json.modele = modele;
                                     
                           json.annee_modele = annee_modele;
                                     
                           json.kilometrage = kilometrage;
                                     
                           json.energie = energie;
                                     
                           json.boite_de_vitesses = boite_de_vitesses;
                                     
    });
    
    return JSON.stringify(json);
    
}