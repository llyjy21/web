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
    
    commission[k][0] = price[k] * 0.15;                                      //insurance
    
    commission[k][1] = days[k];                                              //roadside assistance
    
    commission[k][2] = commission[k][0] - commission[k][1];                 //drivy
    
    if(deductionable[k]){
        
        commission[k][2] += 4 * days[k];
    }
    
}


/*
 *   Visulize data
 */
(function ()
 {
 var r = Raphael("holder3");
 
 fin = function ()
 {
 this.flag = r.popup(this.bar.x, this.bar.y, this.bar.value || "0").insertBefore(this);
 
 };
 
 fout = function ()
 {
 this.flag.animate({opacity: 0}, 300, function () {this.remove();});
 
 };
 
 txtattr = { font: "10px 'Montserrat', Fontin-Sans, sans-serif" };
 
 r.text(45, 35, driver[0]).attr(txtattr);
 
 r.text(80, 45, "Deductible Reduction: " + deductionable[0]).attr(txtattr);
 
 r.hbarchart(200, 20, 40, 40, commission[0]).hover(fin, fout);
 
 r.text(500, 35, "rental price: " + price[0]).attr(txtattr);
 
 
 r.text(45, 85, driver[1]).attr(txtattr);
 
 r.text(80, 95, "Deductible Reduction: " + deductionable[0]).attr(txtattr);
 
 r.hbarchart(200, 70, 200, 40, commission[1]).hover(fin, fout);
 
 r.text(500, 85, "rental price: " + price[1]).attr(txtattr);
 
 
 r.text(45, 135, driver[2]).attr(txtattr);
 
 r.text(80, 145, "Deductible Reduction: " + deductionable[0]).attr(txtattr);
 
 r.hbarchart(200, 120, 200, 40, commission[2]).hover(fin, fout);
 
 r.text(500, 135, "rental price: " + price[2]).attr(txtattr);
 
 })(jQuery);