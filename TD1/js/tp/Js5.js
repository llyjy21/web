var data = {
    "cars": [
             {
             "id": "p306",
             "vehicule": "peugeot 306",
             "pricePerDay": 20,
             "pricePerKm": 0.10
             }
             ],
    "rentals": [
                {
                "id": "1-pb-92",
                "driver": {
                "firstName": "Paul",
                "lastName": "Bismuth"
                },
                "carId": "p306",
                "pickupDate": "2015-09-12",
                "returnDate": "2015-09-12",
                "distance": 100,
                "options":{
                "deductibleReduction": false
                }
                },
                {
                "id": "2-rs-92",
                "driver": {
                "firstName": "Rebecca",
                "lastName": "Solanas"
                },
                "carId": "p306",
                "pickupDate": "2015-09-10",
                "returnDate": "2015-09-15",
                "distance": 300,
                "options":{
                "deductibleReduction": true
                }
                },
                {
                "id": "3-sa-92",
                "driver": {
                "firstName": " Sami",
                "lastName": "Ameziane"
                },
                "car_id": "p306",
                "pickupDate": "2015-08-31",
                "returnDate": "2015-09-13",
                "distance": 1000,
                "options":{
                "deductibleReduction": true
                }
                }
                ]
};


/*
 *   Calculate the price per driver
 */
var driverCount = data.rentals.length;

var carCount = data.cars.length;

var deductionable = new Array();

var price = new Array();

var driver = new Array();

var days = new Array();

var carID = new Array();

for(var i = 0;i < driverCount;i++)
{
    price[i] = 0;
    
    driver[i] = null;
    
    carID[i] = 0;
    
    driver[i] = data.rentals[i].driver.firstName + " " + data.rentals[i].driver.lastName;
    
    days[i] = (((new Date(data.rentals[i].returnDate.replace(/-/g,"\/"))) - (new Date(data.rentals[i].pickupDate.replace(/-/g,"\/")))))/1000/60/60/24 + 1;
    
    var distances = data.rentals[i].distance;
    
    carID[i] = data.rentals[i].carId;
    
    var priceDay,priceKm;
    
    deductionable[i] = data.rentals[i].options.deductibleReduction;
    
    for(var j = 0 ; j< carCount ; j++)
    {
        if(carID[i] == data.cars[j].id){
            
            priceDay = data.cars[j].pricePerDay * days[i];
            priceKm = data.cars[j].pricePerKm * distances;
        }
        
    }
    
    price[i] = priceDay + priceKm;
    
}


/*
 *   Commission, Insurance, Assistance, Drivy
 */

var commission = new Array();

for(var k = 0; k < driverCount; k++)
{
    commission[k] = [0, 0, 0];
    
    commission[k][0] = price[k] * 0.15;                                         //insurance
    
    commission[k][1] = days[k];                                                 //roadside assistance
    
    commission[k][2] = commission[k][0] - commission[k][1];                     //drivy
    
    if(deductionable[k]){
        
        commission[k][2] += 4 * days[k];
    }
    
}


/*
 *   Visulize data
*/
(function ()
{
    var r = Raphael("holder4", 800, 600);
 
    var targets = r.set();
 
    var labels = r.set();
 
    var movers = r.set();
 
    var labels1 = r.set();
 
    for(var j = 0; j< driverCount; j++)
    {
        //movers
        movers.push(r.circle(11 + 231 * j, 50, 10),
                 r.circle(11 + 231 * j, 70, 10),
                 r.circle(11 + 231 * j, 90, 10),
                 r.circle(11 + 231 * j, 110, 10),
                 r.circle(11 + 231 * j, 130, 10));
 
        for(var k = 5 * j; k< 5 * j + 5; k++){
 
            movers[k].cx = 141 + 231 * j;
        }
 
        movers.attr({fill: "#616161", stroke: "#fff", "fill-opacity": 0});

        labels1.push(r.text(26  + 231 * j, 50, driver[j]),
                  r.text(26  + 231 * j, 70, price[j] * 0.7 + " euros"),
                  r.text(26  + 231 * j, 90, commission[j][0] + " euros"),
                  r.text(26  + 231 * j, 110, commission[j][1] + " euros"),
                  r.text(26  + 231 * j, 130, commission[j][2]+ " euros"));

        labels1.attr({font: "12px Fontin-Sans, Arial", fill: "#616161", "text-anchor": "start"});
 
        //targets
        targets.push(r.circle(141 + 231 * j, 50, 10),
                  r.circle(141 + 231 * j, 70, 10),
                  r.circle(141 + 231 * j, 90, 10),
                  r.circle(141 + 231 * j, 110, 10),
                  r.circle(141 + 231 * j, 130, 10));

        targets.attr({fill: "#616161", stroke: "#fff", "stroke-dasharray": "- ", opacity: .2});

        labels.push(r.text(156 + 231 * j, 50, price[j] + " euros"),
                 r.text(156 + 231 * j, 70, "owner"),
                 r.text(156 + 231 * j, 90, "insurance"),
                 r.text(156 + 231 * j, 110, "assistance"),
                 r.text(156 + 231 * j, 130, "drivy"));

        labels.attr({font: "12px Fontin-Sans, Arial", fill: "#616161", "text-anchor": "start"});
 
    }
    //animation
    movers[0].click(function (){
                          
          for(var i = 0; i < 5; i++)
          {
                  
              movers[i].cx = movers[i].cx || 300;
              
              movers[i].animate({cx: movers[i].cx, "stroke-width": movers[i].cx / 100, fill: movers[i].cx - 100 ? "hsb(0, .75, .75)" : "#000", "fill-opacity": +!!(movers[i].cx - 100)}, 1000);
              
              movers[i].cx = movers[i].cx == 300 ? 100 : 300;
          }
    });
 
    movers[5].click(function (){
                 
         for(var i = 5; i < 10; i++)
         {
             movers[i].cx = movers[i].cx || 300;
             
             movers[i].animate({cx: movers[i].cx, "stroke-width": movers[i].cx / 100, fill: movers[i].cx - 100 ? "hsb(0, .75, .75)" : "#000", "fill-opacity": +!!(movers[i].cx - 100)}, 1000);
             
             movers[i].cx = movers[i].cx == 300 ? 100 : 300;
        }
    });
 
    movers[10].click(function (){
                 
         for(var i = 10; i < 15; i++)
         {
             movers[i].cx = movers[i].cx || 300;
             
             movers[i].animate({cx: movers[i].cx, "stroke-width": movers[i].cx / 100, fill: movers[i].cx - 100 ? "hsb(0, .75, .75)" : "#000", "fill-opacity": +!!(movers[i].cx - 100)}, 1000, "elastic");
             
             movers[i].cx = movers[i].cx == 300 ? 100 : 300;

         }
    });
 
})(jQuery);




