// JavaScript Document
var state = 0; //0:hide 1:show
var id = null;
function show(targetbox)
{
var showtarget = document.getElementById(targetbox);
if(id!=null && id != showtarget){
   id.style.display = "none";
   state = 0;
}
if(state==0){
   showtarget.style.display = "block";
   state=1;
}
else{
   showtarget.style.display = "none";
   state=0;
}
id = showtarget;
}