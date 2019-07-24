$(document).ready(function () {
	$("#formulario-fewest\\:solucion").hide();
	
	var cinco = new Audio("resources/audio/cincominutos.mp3");
	var cincoMinutos = true; 
	
	var ya = new Audio("resources/audio/treinta.mp3");
	var treintaSegundos = true; 

	var tiempo = {
			minuto: 0,
			segundo: 0,
			centisegundo: 0,
			penalizacion: true,
			dnf:true
	};

	var tiempo_corriendo = null;
	
	var inicio = 0;
	
	var transcurrido = 0; 
	var limite = 3600000;
	var restante =0;
	var actual = 0;
	
	var estado = 0;

	$("#lbl-comenzar").click(function() {
			if ($('#lbl-comenzar').text() === 'Detener' && estado===2) {//si el cronometro esta corriendo lo detiene
				$("#formulario-fewest\\:solucion").hide();
				estado = 3;
				mostrarTiempo();
				if (tiempo.dnf){
					document.getElementById('formulario-fewest:txtTiempo').value = "DNF";
				} else{
					document.getElementById('formulario-fewest:txtTiempo').value = $('#lblTiempo').text();
				}
				document.getElementById('formulario-fewest:txtTiempoMilisegundos').value = actual - inicio;
				document.getElementById('formulario-fewest:txtDnf').value = tiempo.dnf;
				clearInterval(tiempo_corriendo);
				tiempo.minuto = 0;
				tiempo.segundo = 0;
				tiempo.centisegundo = 0;
				inicio = 0;
				transcurrido = 0; 
				actual = 0;
				$('#lbl-comenzar').text('Comenzar');
				document.getElementById('formulario-fewest:guardarSolucion').click();
			} else { // el cronometro esta detenido entonces lo pone a andar
				$("#formulario-fewest\\:solucion").show();
				inicio = new Date().getTime();
				estado = 2;
				$('#lbl-comenzar').text('Detener');
				tiempo.penalizacion = false;
				tiempo.dnf = false;
				tiempo.segundo = 0;
				tiempo.centisegundo = 0;
				tiempo.minuto = 0;
				clearInterval(tiempo_corriendo);
				tiempo_corriendo = setInterval(function () {
					mostrarTiempo();
				}, 10);
			}
	});
	
	function mostrarTiempo(){
		actual = new Date().getTime();
		if (inicio!=0) { //si ya se inicio el cronometro muestra el tiempo
			transcurrido = actual - inicio;
			restante = limite -transcurrido;
			if (restante < 0){
				tiempo.dnf = true;
			}
			tiempo.minuto = Math.floor(restante/60000);
			restante -= (tiempo.minuto*60000);
			tiempo.segundo = Math.floor(restante/1000);
			restante -= (tiempo.segundo*1000);
			tiempo.centisegundo = Math.floor(restante/10);
			if (tiempo.dnf){
				$('#lblTiempo').text('DNF');
			} else {
				$('#lblTiempo').text((tiempo.minuto < 10 ? '0' + tiempo.minuto : tiempo.minuto) + ':' + (tiempo.segundo < 10 ? '0' + tiempo.segundo : tiempo.segundo));
				if(parseInt(limite - transcurrido)<=300000){
					if (cincoMinutos){
						cinco.play();
						cincoMinutos = false;
					}
					if(parseInt(limite - transcurrido)<=30000){
						if (treintaSegundos){
							ya.play();
							treintaSegundos = false;
						}
					}
				}
			}
		} else {
			inicio = new Date().getTime();
			estado = 2;
			$('#lbl-comenzar').text('Detener');
		}
	}
});	
