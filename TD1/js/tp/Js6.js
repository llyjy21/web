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
                "carId": "p306",
                "pickupDate": "2015-08-31",
                "returnDate": "2015-09-13",
                "distance": 1000,
                "options":{
                "deductibleReduction": true
                }
                }
                ],
    "rentalModifications": [
            {
            "id": 1,
            "rentalId": "1-pb-92" ,
            "returnDate": "2015-09-13",
            "distance": 150
            },
            {
            "id": 2,
            "rentalId": "3-sa-92",
            "pickupDate": "2015-09-01"
            }
    ]
};


/*
 *   Calculate the price per driver
 */
var rentalCount = data.rentals.length;

var carCount = data.cars.length;

var deductionable = new Array();

var price = new Array();

var driver = new Array();

var days = new Array();

var carIds = new Array();

for(var i = 0;i < rentalCount;i++)
{
    price[i] = 0;
    
    driver[i] = null;
    
    carIds[i] = 0;
    
    driver[i] = data.rentals[i].driver.firstName + " " + data.rentals[i].driver.lastName;
    
    var distances = data.rentals[i].distance;
    
    carIds[i] = data.rentals[i].carId;
    
    deductionable[i] = data.rentals[i].options.deductibleReduction;
    
    price[i] = price_cal("price", i, carIds[i],data.rentals[i].pickupDate, data.rentals[i].returnDate, distances);
    
}


/*
 *   Commission, Insurance, Assistance, Drivy
 */

var commission = new Array();

for(var k = 0; k < rentalCount; k++)
{
    commission[k] = [0, 0, 0];
    
    commission[k][0] = price[k] * 0.15;                                     //insurance
    
    commission[k][1] = days[k];                                             //roadside assistance
    
    commission[k][2] = commission[k][0] - commission[k][1];                 //drivy
    
    if(deductionable[k]){
        
        commission[k][2] += 4 * days[k];
    }
    
}


/*
 *   Calculate the new price per driver after modification
 */
var new_price = new Array();

var new_days = new Array();

var modiCount = data.rentalModifications.length;

var new_pickupDate = new Array();

var new_returnDate = new Array();

var new_distance = new Array();

for(var i = 0;i < data.rentalModifications.length ;i++)
{
    
    for(var j = 0 ; j< rentalCount ; j++)
    {
        new_pickupDate[j] = null;
        
        new_returnDate[j] = null;
        
        if(data.rentalModifications[i].rentalId == data.rentals[j].id){
            
            for(var key in data.rentalModifications[i])
            {

                if(key == "pickupDate"){
                    
                    new_pickupDate[j] = data.rentalModifications[i][key];
                }
                else if(key == "returnDate"){
                    
                    new_returnDate[j] = data.rentalModifications[i][key];
                    
                }else if(key == "distance"){
                    
                    new_distance[j] = data.rentalModifications[i][key];
                }
            }
            
            if(new_pickupDate[j] != null){
                
                if(new_returnDate[j] != null){
                    
                    if(new_distance[j] != null){
                        
                        new_price[j] = price_cal("new_price", j, data.rentals[j].carId, new_pickupDate[j], new_returnDate[j], new_distance[j]);
                    }else{
                        
                        new_price[j] = price_cal("new_price", j, data.rentals[j].carId, new_pickupDate[j], new_returnDate[j], data.rentals[j].distance);
                    }
                }else{
                    
                    if(new_distance[j] != null){
                        
                        new_price[j] = price_cal("new_price", j, data.rentals[j].carId, new_pickupDate[j], data.rentals[j].returnDate, new_distance[j]);
                    }else{
                        
                        new_price[j] = price_cal("new_price", j, data.rentals[j].carId, new_pickupDate[j], data.rentals[j].returnDate, data.rentals[j].distance);
                    }
                    
                }
            }else{
                
                if(new_returnDate[j] != null){
                    
                    if(new_distance[j] != null){
                        
                        new_price[j] = price_cal("new_price", j, data.rentals[j].carId, data.rentals[j].pickupDate, new_returnDate[j], new_distance[j]);
                    }else{
                        
                        new_price[j] = price_cal("new_price", j, data.rentals[j].carId, data.rentals[j].pickupDate, new_returnDate[j], data.rentals[j].distance);
                    }
                }else{
                    
                    if(new_distance[j] != null){
                        
                        new_price[j] = price_cal("new_price", j, data.rentals[j].carId, data.rentals[j].pickupDate, data.rentals[j].returnDate, new_distance[j]);
                    }
                }
                
            }

        }
    
    }

}


/*
 *   Calculate the price per driver
 */

function price_cal(price, driver, carId, pickupDate, returnDate, distances)
{
    var priceDay;
    
    var priceKm;

    if(price == "price")
    {
        days[driver] = (((new Date(returnDate.replace(/-/g,"\/"))) - (new Date(pickupDate.replace(/-/g,"\/")))))/1000/60/60/24 + 1;
        
        for(var j = 0 ; j< carCount ; j++)
        {
            if(carId == data.cars[j].id){
                
                priceDay = data.cars[j].pricePerDay * days[driver];
                
                priceKm = data.cars[j].pricePerKm * distances;
                
            }
            
        }
    }else if(price == "new_price")
    {
        
        new_days[driver] = (((new Date(returnDate.replace(/-/g,"\/"))) - (new Date(pickupDate.replace(/-/g,"\/")))))/1000/60/60/24 + 1;
        
        for(var j = 0 ; j< carCount ; j++)
        {
            if(carId == data.cars[j].id){
                
                priceDay = data.cars[j].pricePerDay * new_days[driver];
                
                priceKm = data.cars[j].pricePerKm * distances;
                
            }
            
        }
        
    }
    
    return (priceDay + priceKm);
}
/*
 *   Calculate the new Commission, Insurance, Assistance, Drivy
 */

var new_commission = new Array();

for(var k = 0; k < rentalCount; k++)
{
    if(new_price[k] != null){
    
        new_commission[k] = [0, 0, 0];
        
        new_commission[k][0] = new_price[k] * 0.15;                                      //insurance
        
        new_commission[k][1] = new_days[k];                                              //roadside assistance
        
        new_commission[k][2] = commission[k][0] - commission[k][1];                      //drivy
        
        if(deductionable[k]){
            
            new_commission[k][2] += 4 * new_days[k];
        }
        
    }else{
        
        new_commission[k] = commission[k];
        
        new_commission[k][0] = commission[k][0];                                      //insurance
        
        new_commission[k][1] = commission[k][1];                                              //roadside assistance
        
        new_commission[k][2] = commission[k][2];                      //drivy
        
        new_price[k] = price[k];
        
    }
    
}



/*
 *   Visulize data
 */
(function ()
 {
 var r = Raphael("holder5");
 
 fin = function ()
 {
 this.flag = r.popup(this.bar.x, this.bar.y, this.bar.value || "0").insertBefore(this);
 
 };
 
 fout = function ()
 {
 this.flag.animate({opacity: 0}, 300, function () {this.remove();});
 
 };
 
 
 txtattr = { font: "11px 'Montserrat', Fontin-Sans, sans-serif" ,fill: '#616161'};
 
 r.text(150, 10, "blue: before the modification").attr(txtattr);
 
 r.text(150, 20, "green: after the modification").attr(txtattr);

 
 r.hbarchart(20, 40, 180, 50, [price, new_price], {stacked: true}).hover(fin, fout);
 
 r.text(150, 60, "rental price").attr(txtattr);
 
 var owner = new Array;
 
 var new_owner = new Array;
 
 for(var i = 0; i < rentalCount; i++)
 {
    owner[i] = price[i] * 0.7;
 
    new_owner[i] = new_price[i] * 0.7;

 }
 r.hbarchart(20, 100, 180, 50, [owner, new_owner], {stacked: true}).hover(fin, fout);

 r.text(150, 120, "owner").attr(txtattr);
 
 
 r.hbarchart(20, 160, 180, 50, [commission[0], new_commission[0]], {stacked: true}).hover(fin, fout);
 r.text(150, 190, "insurance").attr(txtattr);
 
 
 r.hbarchart(350, 40, 180, 50, [commission[1], new_commission[1]], {stacked: true}).hover(fin, fout);
 r.text(500, 60, "assistance").attr(txtattr);

 r.hbarchart(350, 100, 180, 50, [commission[2], new_commission[2]], {stacked: true}).hover(fin, fout);
 r.text(500, 120, "drivy").attr(txtattr);
 
 })(jQuery);