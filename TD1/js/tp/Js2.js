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
                "distance": 100
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
                "distance": 300
                },
                {
                "id": "3-sa-92",
                "driver": {
                "firstName": " Sami",
                "lastName": "Ameziane"
                },
                "carId": "p306",
                "pickupDate": "2015-08-31",
                "returnDate": "2015-09-13",
                "distance": 1000
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

for(var i = 0; i < driverCount; i++)
{
    price[i] = 0;
    
    driver[i] = null;
    
    driver[i] = data.rentals[i].driver.firstName + " " + data.rentals[i].driver.lastName;
    
    var days = (((new Date(data.rentals[i].returnDate.replace(/-/g,"\/"))) - (new Date(data.rentals[i].pickupDate.replace(/-/g,"\/")))))/1000/60/60/24 + 1;
    
    var distances = data.rentals[i].distance;
    
    var car = data.rentals[i].carId;
    
    var priceDay,priceKm;
    
    if(days==1)
    {
        price[i] += priceCal(car, days, 1);
    }
    else if(days<4 && days>1)
    {
        price[i] = priceCal(car, days, 0.9);
        
    }else if(days<10 && days>=4)
    {
        price[i] = priceCal(car, days, 0.7);
    }
    else
    {
        price[i] = priceCal(car, days, 0.5);
    }
}

/*
 *   Calculate the price per day
 */
function priceCal(car, days, ratio){
    
    for(var j = 0 ; j< carCount ; j++)
    {
        if(car == data.cars[j].id){
            
            priceDay = data.cars[j].pricePerDay * days * ratio;
        }
    }
    
    return priceDay;
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
 
 var r = Raphael("holder1"),
 
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