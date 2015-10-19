$("#inquiry").click(function() { //Verify the validity of the input url
                    
    var data = $("#urlStr").val();
                    
    var index = data.indexOf('http://www.leboncoin.fr/');
                    
    if (index !== 0) {
                    
        alert("Beta: nous acceptons seulement la petite annonce de leboncoin.fr!");
                    
        return false;
                    
    } else {
                    
        return true;
    }
                    
});


//2 different events acoording to the format of url
$("#Inquiry_form").submit(function() {
                          
      var url_data = $("#urlStr").val();
      
      var index = url_data.indexOf('http://www.leboncoin.fr/voitures/offres/');
      
      if (index === 0) {
      
          var url = "/scrapes";
        
          var opts = {
          
              lines: 13 // The number of lines to draw
              , length: 28 // The length of each line
              , width: 14 // The line thickness
              , radius: 42 // The radius of the inner circle
              , scale: 1 // Scales overall size of the spinner
              , corners: 1 // Corner roundness (0..1)
              , color: '#000' // #rgb or #rrggbb or array of colors
              , opacity: 0.25 // Opacity of the lines
              , rotate: 0 // The rotation offset
              , direction: 1 // 1: clockwise, -1: counterclockwise
              , speed: 1 // Rounds per second
              , trail: 60 // Afterglow percentage
              , fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
              , zIndex: 2e9 // The z-index (defaults to 2000000000)
              , className: 'spinner' // The CSS class to assign to the spinner
              , top: '50%' // Top position relative to parent
              , left: '50%' // Left position relative to parent
              , shadow: false // Whether to render a shadow
              , hwaccel: false // Whether to use hardware acceleration
              , position: 'absolute' // Element positioning
          
          }
          
          var target = document.getElementById('loadingIMG');
                          
          var spinner = new Spinner().spin();
          
          $.ajax({
                 
                 type: "POST",
                 
                 url: url,
                 
                 data: {
                 
                     "input_value": url_data
                 },
                 
                 async: true,
                 
                 timeout: 30000,
                 
                 success: function(data) { //Show the result by using the pie chart
                 
                     //initial interface
                     $('#pie_chart').html("<h1><a href='#'><span id='it'><i class='icon-star'></i>TD2 <i class='icon-star'></i></span><span id='beta'>BETA</span></a></h1>");
                 
                     $('#myStat').html("");
                 
                     $('#intro').html("");
                 
                     $('#detail').html("<h2>Stay tuned, we are launching very soon... </h2><p> Here to query. Here to get</p>");
                     
                     $('#chart1').html("");
                     
                     $('#chart1').show();
                 
                     $.jqplot._noToImageButton = true;
                 
                     var leboncoin_price;
                 
                     data = JSON.parse(data);
                 
                     var la_cote_Argus_length = (data.average).length;
                     
                     var leboncoins_length = (data.leboncoins).length;
                 
                     //get the largest value as the maximum value of the y axis
                     var maxAverage = 0;
                     
                     for (var i = 0; i < leboncoins_length; i++) {
                     
                         if (data.average[i] > data.leboncoins[i]) {
                         
                             if (data.average[i] > maxAverage) {
                             
                             maxAverage = data.average[i];
                             
                             }
                         } else {
                         
                             if (data.leboncoins[i] > maxAverage) {
                             
                                 maxAverage = data.leboncoins[i];
                             
                             }
                         }
                     }
                 
                     var leboncoin_price = [];
                     
                     var la_cote_Argus_price = [];
                     
                     for (var i = 0; i < leboncoins_length; i++) {
                 
                     leboncoin_price[i] = [i, data.leboncoins[i]];
                     
                     }
                     
                     for (var i = 0; i < la_cote_Argus_length; i++) {
                     
                     la_cote_Argus_price[i] = [i, data.average[i]];
                     
                     }
                 
                     var plot1 = $.jqplot("chart1", [leboncoin_price, la_cote_Argus_price], {
                                          
                          seriesColors: ["rgba(78, 135, 194, 0.7)", "rgb(211, 235, 59)"],
                                          
                          title: 'La Cote Argus',
                                          
                          highlighter: {
                                          
                              show: true,
                                          
                              sizeAdjust: 1,
                                          
                              tooltipOffset: 9
                                          
                          },
                                          
                          grid: {
                                          
                              background: 'rgba(57,57,57,0.0)',
                                          
                              drawBorder: false,
                                          
                              shadow: false,
                                          
                              gridLineColor: '#666666',
                                          
                              gridLineWidth: 2
                                          
                          },
                                          
                          seriesDefaults: {
                                          
                              rendererOptions: {
                                          
                                  smooth: true,
                                          
                                  animation: {
                                          
                                      show: true
                                          
                                  }
                                          
                              },
                                          
                              showMarker: false
                          },
                                          
                          series: [
                                   
                               {
                               
                                   fill: true,
                                   
                                   label: '22'
                           
                               },
                               
                               {
                               
                                   label: 'www'
                               
                               }
                                   
                          ],
                                          
                          axesDefaults: {
                                          
                              rendererOptions: {
                                          
                                  baselineWidth: 1.5,
                                          
                                  baselineColor: '#444444',
                                          
                                  drawBaseline: false
                              }
                          },
                                          
                          axes: {
                                          
                              xaxis: {
                                          
                                  tickInterval: 1,
                                              
                                  max: leboncoins_length - 1,
                                              
                                  min: 0,
                                              
                                  tickOptions: {
                                              
                                  showGridline: false
                                              
                                  }
                                          
                              },
                                          
                              yaxis: {
                                          
                                  renderer: $.jqplot.LogAxisRenderer,
                          
                                  tickOptions: {
                                          
                                      formatString: "$%'d",
                          
                                      showMark: false
                                          
                                  }
                                          
                              }
                                          
                          }
                                          
                      });
                 
                     $('.jqplot-highlighter-tooltip').addClass('ui-corner-all');
                 
                     $('#detail').html("<p>Blue: Laboncoins</p><p>Yellow: La Cote Argus</p><p>No highlight? Pas de la cote argus</p>");
                     
                 },
                 
                 beforeSend:function() {
                 
                     target.appendChild(spinner.el);
                 
                 },
                 
                 complete:function() {
                 
                     spinner.stop();
                 
                 },
                 
                 error: function(jqXHR, textStatus, errorThrown) {
                 
                     if(textStatus=='timeout') {
                     
                        alert("Beta: Désolé! Votre réseau est pas bon, la demande a expiré!");
                     
                     }else {
                     
                        alert("Beta: Désolé! Notre système échoue!");
                     
                     }
                 
                 }
                     
          });
                          
      } else {
      
          var url = "/scrape";
                          
          $.ajax({
                 
                 type: "POST",
                 
                 url: url,
                 
                 data: {
                 
                     "input_value": url_data
                 
                 },
                 
                 timeout: 15000,
                 
                 success: function(data) { // Show the result by using the pie chart
                 
                     var total = data.total;
                 
                     var low = data.low;
                 
                     var average = data.average;
                 
                     var perent = ((low / total) * 100).toFixed(1);
                 
                     var discription = data.dis;
                 
                     //initial interface
                     $('#chart1').hide();
                     
                     $('#detail').html("<h2>Stay tuned, we are launching very soon... </h2><p> Here to query. Here to get</p>");
                     
                     $('#pie_chart').html("<h1><a href='#'><span id='it'><i class='icon-star'></i>TD2 <i class='icon-star'></i></span><span id='beta'>BETA</span></a></h1>");
                     
                     $('#myStat').html("");
                     
                     $('#intro').html("");
                 
                     if (total === 0 && low === 0) {
        
                 $('#pie_chart').html("<center><div id='myStat' data-dimension='250' data-text='35%' data-width='17' data-fontsize='38' data-percent='35' data-fgcolor='#ce3638' data-bgcolor='#eee' data-type='half' data-fill='#ddd'></div></center>");
                 
                 $('#myStat').attr("data-total", 10);
                 
                 $('#myStat').attr("data-part", 1);
                 
                 $('#myStat').attr("data-text", 80 + "%");
                 
                 $('#myStat').css("text-align", "centre");
                 
                 $('#myStat').attr("data-info", discription);
                 
                 $('#myStat').circliful();
                 
                 $('#intro').html("<p>La Cote Argus = " + 5 + " €</p>");
                 
                 $('#detail').html("<p>↑↑Good Probability of your deal, which comes from the link↓↓.<a href='"+ url_data + "'> Detail?</a></p>");
                 
                     }else{
                 
                         $('#pie_chart').html("<center><div id='myStat' data-dimension='250' data-text='35%' data-width='17' data-fontsize='38' data-percent='35' data-fgcolor='#ce3638' data-bgcolor='#eee' data-type='half' data-fill='#ddd'></div></center>");
                         
                         $('#myStat').attr("data-total", total);
                         
                         $('#myStat').attr("data-part", low);
                         
                         $('#myStat').attr("data-text", perent + "%");
                         
                         $('#myStat').css("text-align", "centre");
                         
                         $('#myStat').attr("data-info", discription);
                         
                         $('#myStat').circliful();
                         
                         $('#intro').html("<p>La Cote Argus = " + average + " €</p>");
                         
                         $('#detail').html("<p>↑↑Good Probability of your deal, which comes from the link↓↓.<a href='"+ url_data + "'> Detail?</a></p>");
                 
                     }
                 
                 
                 },
                 
                 error: function(jqXHR, textStatus, errorThrown) {
                 
                     if(textStatus=='timeout'){
                 
                        alert("Beta: Désolé! Votre réseau est pas bon, la demande a expiré!");
                 
                     }else {
                 
                        alert("Beta: Désolé! Notre système échoue!");
                 
                     }
                 
                 }
                 
         });
                          
      }
        
      return false; // Avoid to execute the actual submit of the form.
          
});