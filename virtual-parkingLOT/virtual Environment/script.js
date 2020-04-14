
function getData(){
    // do whatever you like here
    const http = new XMLHttpRequest()

http.open("GET", "http://127.0.0.1:5000/")
http.send()

http.onload = () => {
	console.log(http.responseText);
data=JSON.parse(http.responseText);

// for(var key in data){
// 	console.log(key);
// }
var charging=data['charging'];

var chargingEle=document.getElementsByClassName('chargingCarCount')[0];
chargingEle.innerHTML=Object.keys(charging).length;

for(var key in charging){
createChargingCars(charging[key])
}





var charged=data['fullyCharged'];

var chargedEle=document.getElementsByClassName('fullyChargedCarCount')[0];
chargedEle.innerHTML=Object.keys(charged).length;

for(var key in charged){
createChargingCars(charged[key])
}



var queue=data['queue'];

var queueEle=document.getElementsByClassName('queueCarCount')[0];
queueEle.innerHTML=Object.keys(queue).length;

for(var key in queue){
createQueueCars(queue[key])
}



// console.log(data['charging'])

}

    setTimeout(getData, 5000);
}

getData();

























function createChargingCars(data=null){
	var className='col-lg-3 car';
	var div=document.createElement('div');
	div.className=className;

	var img=document.createElement('IMG');
	// img.src="../images/car.PNG";
	img.setAttribute('src',"../images/car.PNG");
	img.className="img-thumbnail border border-info";





var infoDev=document.createElement('div');
infoDev.className='infoDev';

var carId=document.createElement('h5');
carId.innerHTML=data['id']

var fullPower=document.createElement('p');
fullPower.innerHTML='Full power: '+data['fullPowerLevel']


var curPower=document.createElement('p');
curPower.innerHTML='Cur power: '+data['powerLevel']

infoDev.appendChild(carId);
infoDev.appendChild(fullPower);
infoDev.appendChild(curPower);



	div.appendChild(img);
	div.appendChild(infoDev);
	var chargingCars=document.getElementsByClassName('chargingCars');
	chargingCars[0].appendChild(div);

}








function createQueueCars(data=null){
	var className='row queueCar';
	var div=document.createElement('div');
	div.className=className;

	var img=document.createElement('IMG');
	img.src="../images/car.PNG";
	img.setAttribute('src',"../images/car.PNG");
	img.className="img-thumbnail border border-primary";







var infoDev=document.createElement('div');
infoDev.className='infoDev';

var carId=document.createElement('h5');
carId.innerHTML=data['id']

var fullPower=document.createElement('p');
fullPower.innerHTML='Full power: '+data['fullPowerLevel']


var curPower=document.createElement('p');
curPower.innerHTML='Cur power: '+data['powerLevel']

infoDev.appendChild(carId);
infoDev.appendChild(fullPower);
infoDev.appendChild(curPower);



	div.appendChild(img);
	div.appendChild(infoDev);



	

	var queue=document.getElementsByClassName('queue');
	queue[0].appendChild(div);

}