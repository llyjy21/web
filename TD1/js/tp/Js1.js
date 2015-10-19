var data = {
    "cars": [
             {
             "id": "p306",
             "vehicule": "peugeot 306",
             "pricePerDay": 20,
             "pricePerKm": 0.10
             },
             {
             "id": "rr-sport",
             "pricePerDay": 60,
             "pricePerKm": 0.30
             },
             {
             "id": "p-boxster",
             "pricePerDay": 100,
             "pricePerKm": 0.45
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
                "returnDate": "2015-09-14",
                "distance": 150
                },
                {
                "id": "2-rs-92",
                "driver": {
                "firstName": "Rebecca",
                "lastName": "Solanas"
                },
                "carId": "rr-sport",
                "pickupDate": "2015-09-09",
                "returnDate": "2015-09-13",
                "distance": 550
                },
                {
                "id": "3-sa-92",
                "driver": {
                "firstName": " Sami",
                "lastName": "Ameziane"
                },
                "car_id": "p-boxster",
                "pickupDate": "2015-09-12",
                "returnDate": "2015-09-14",
                "distance": 100
                }
                ]
};



/*
 *   Calculate the price per driver
 */
var driverCount = data.rentals.length;

var carCount = data.cars.length;

var price = new Array();

var driver = new Array();

for(var i = 0;i < driverCount; i++)
{
    price[i] = 0;
    
    driver[i] = null;
    
    driver[i] = data.rentals[i].driver.firstName + " " + data.rentals[i].driver.lastName;
    
    var days = (((new Date(data.rentals[i].returnDate.replace(/-/g,"\/"))) - (new Date(data.rentals[i].pickupDate.replace(/-/g,"\/")))))/1000/60/60/24 + 1;
    
    var distances = data.rentals[i].distance;
    
    var car = data.rentals[i].carId;
    
    var priceDay,priceKm;
    
    for(var j = 0 ; j< carCount ; j++)
    {
        if(car == data.cars[j].id)
        {
            priceDay = data.cars[j].pricePerDay * days;
            priceKm = data.cars[j].pricePerKm * distances;
        }
    }
    price[i] = priceDay + priceKm;
}


/*
 *   Visulize data
 */
var xs = new Array();

var ys = new Array();

var yPosition = 60;

for(var i = 0; i < driverCount; i++)
{
    xs[i] = 0;
    
    xs[i] += 200 * i;
    
    ys[i] = yPosition;
}

(function () {
 
 var r = Raphael("holder"),
 
 axisy = [0, 0, 0, 0];
 
 r.dotchart(0, 0, 500, 70, xs, ys, price, {       //x-start, y-start, width, height, x-position, y-positon, data, option
            max: 20,                         //diameter of circle
            heat: true,                      //value high --> heat high(color warm)
            axis: "0 0 1 0",                 //render which axe? top right bottom left
            axisxstep: driverCount - 1,      //number of the points
            axisxtype: " ",
            axisytype: " ",
            axisxlabels: driver,
            axisylabels: axisy
            }).hover(function() {
                     this.marker = this.marker || r.tag(this.x, this.y, this.value, 0, this.r + 2).insertBefore(this);
                     this.marker.show();
                     },
                     function() {
                     this.marker && this.marker.hide();
                     });
 })(jQuery);