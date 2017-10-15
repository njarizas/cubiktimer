$(document).ready(function () {

	var tiempo = {
			minuto: 0,
			segundo: 0,
			centisegundo: 0,
			centisegundos_inspeccionando:0,
			penalizacion: true,
			dnf:true
	};

	var tiempo_corriendo = null;

	//(Cuando se oprime la tecla) Esta función detiene el cronometro
	$(document).keydown(function (tecla) {
		if (tecla.keyCode !== 116 && $('#mezclaPersonalizada').is(':hidden') && $('#configuraciones').is(':hidden')) {
			if ($('#lbl-comenzar').text() === 'Detener') {//si el cronometro esta corriendo
				if (tiempo.dnf){
					document.getElementById('form:txtTiempo').value = "DNF("+$('#lblTiempo').text()+")";
					document.getElementById('form:txtTiempoInspeccionUsadoMilisegundos').value = (parseInt($('#lblTiempoInspeccion').text())+2)*1000;
					document.getElementById('form:txtTiempoInspeccionUsadoTexto').value = parseInt($('#lblTiempoInspeccion').text())+2;
				} else if (tiempo.penalizacion){
					document.getElementById('form:txtTiempo').value = $('#lblTiempo').text() + " +2";
				} else{
					document.getElementById('form:txtTiempo').value = $('#lblTiempo').text();
				}
				document.getElementById('form:txtTiempoMilisegundos').value = (tiempo.minuto*60000)+(tiempo.segundo*1000)+(tiempo.centisegundo*10);
				document.getElementById('form:txtPenalizacion').value = tiempo.penalizacion;
				document.getElementById('form:txtDnf').value = tiempo.dnf;
				clearInterval(tiempo_corriendo);
				tiempo.minuto = 0;
				tiempo.segundo = 0;
				tiempo.centisegundo = 0;
			}
		}
	});

	//(Cuando se levanta la tecla) Esta función pode el conteo regresivo o el cronometro según sea el caso
	$(document).keyup(function (tecla) {
		if (tecla.keyCode !== 116 && $('#mezclaPersonalizada').is(':hidden') && $('#configuraciones').is(':hidden')) {
			if ($('#lbl-comenzar').text() === 'Detener')//Entra aca si acaba de detener el cronometro
			{
				$('#lbl-comenzar').text('Comenzar');
				document.getElementById('form:guardarTiempo').click();
			} else {
				if (tecla.keyCode === 32) {//para inspeccionar o poner a andar el cronometro solo se puede con [Espacio]
					if ($('#lbl-comenzar').text() === 'Comenzar') //Si hasta ahora va a inspeccionar se pone el tiempo en -segundos
					{
						$('#lbl-comenzar').text('Inspeccionando');
						if($('#lblTiempoInspeccion').text()!=0){
							tiempo.segundo = ((-1 * $('#lblTiempoInspeccion').text())-2);	
						}else{ //si no tiene tiempo de inspeccion se quitan penalizaciones
							tiempo.penalizacion = false;
							tiempo.dnf = false;
						}
					} else if ($('#lbl-comenzar').text() === 'Inspeccionando') { //Si ya estaba inspeccionando se pone a andar el cronometro
						$('#lbl-comenzar').text('Detener');
						//aca obtiene el tiempo usado inspeccionando
						document.getElementById('form:txtTiempoInspeccionUsadoMilisegundos').value = ((parseInt($('#lblTiempoInspeccion').text())+2)*1000)+(tiempo.segundo*1000)+(tiempo.centisegundo*10);
						document.getElementById('form:txtTiempoInspeccionUsadoTexto').value = (((parseInt($('#lblTiempoInspeccion').text())+2)*1000)+(tiempo.segundo*1000)+(tiempo.centisegundo*10))/1000;
						if(tiempo.segundo<-2){
							tiempo.penalizacion = false;
							tiempo.dnf = false;
						} else {
							tiempo.dnf = false;
						}
						tiempo.segundo = 0;
						tiempo.centisegundo = 0;
						tiempo.minuto = 0;
						clearInterval(tiempo_corriendo);

					}
					tiempo_corriendo = setInterval(function () {
						// centisegundos
						tiempo.centisegundo += 1;
						if (tiempo.segundo === 0 && tiempo.minuto === 0) {
							$('#lbl-comenzar').text('Detener');

						}
						if (tiempo.centisegundo >= 100) {
							tiempo.centisegundo = 0;
							tiempo.segundo++;
						}
						// Segundos
						if (tiempo.segundo >= 60) {
							tiempo.segundo = 0;
							tiempo.minuto++;
						}
						// Minutos
						if (tiempo.minuto >= 60) {
							tiempo.minuto = 0;
						}
						if (tiempo.segundo < -2) {
							$('#lblTiempo').text((tiempo.segundo * -1)-2);
						} else if (tiempo.segundo < 0) {
							$('#lblTiempo').text("+2");
						} else {
							$('#lblTiempo').text((tiempo.minuto < 10 ? '0' + tiempo.minuto : tiempo.minuto) + ':' + (tiempo.segundo < 10 ? '0' + tiempo.segundo : tiempo.segundo) + '.' + (tiempo.centisegundo < 10 ? '0' + tiempo.centisegundo : tiempo.centisegundo));
						}
					}, 10);
				}
			}
		}
	});
});
