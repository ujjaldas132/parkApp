

function createChargingCars(){
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
carId.innerHTML='AS 06 6789'

var fullPower=document.createElement('p');
fullPower.innerHTML='Full power: 5000'


var curPower=document.createElement('p');
curPower.innerHTML='Cur power: 2000'

infoDev.appendChild(carId);
infoDev.appendChild(fullPower);
infoDev.appendChild(curPower);



	div.appendChild(img);
	div.appendChild(infoDev);
	var chargingCars=document.getElementsByClassName('chargingCars');
	chargingCars[0].appendChild(div);

}








function createQueueCars(){
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
carId.innerHTML='AS 06 6789'

var fullPower=document.createElement('p');
fullPower.innerHTML='Full power: 5000'


var curPower=document.createElement('p');
curPower.innerHTML='Cur power: 2000'

infoDev.appendChild(carId);
infoDev.appendChild(fullPower);
infoDev.appendChild(curPower);



	div.appendChild(img);
	div.appendChild(infoDev);



	

	var queue=document.getElementsByClassName('queue');
	queue[0].appendChild(div);

}