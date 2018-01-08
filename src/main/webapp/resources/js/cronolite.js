$(document).ready(function () {

	var tiempo = {
			minuto: 0,
			segundo: 0,
			centisegundo: 0,
			penalizacion: true,
			dnf:true
	};

	var tiempo_corriendo = null;
	
	var inspeccion = 0;
	var inicio = 0;
	
	var inspeccionando = 0;
	var transcurrido = 0; 
	var actual = 0;

	//(Cuando se oprime la tecla) Esta función detiene el cronometro
	$(document).keydown(function (tecla) {
		if (tecla.keyCode !== 116 && $('#mezclaPersonalizada').is(':hidden') && $('#configuraciones').is(':hidden')) {
			if ($('#lbl-comenzar').text() === 'Detener') {//si el cronometro esta corriendo
				mostrarTiempo();
				if (tiempo.dnf){
					document.getElementById('form:txtTiempo').value = "DNF("+$('#lblTiempo').text()+")";
					document.getElementById('form:txtTiempoInspeccionUsadoMilisegundos').value = parseInt($('#lblTiempoInspeccion').text()*1000)+2000;
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
				inspeccion = 0;
				inicio = 0;
				inspeccionando = 0;
				transcurrido = 0; 
				actual = 0;
			}
		}
	});

	//(Cuando se levanta la tecla) Esta función pone el conteo regresivo o el cronometro según sea el caso
	$(document).keyup(function (tecla) {
		if (tecla.keyCode !== 116 && $('#mezclaPersonalizada').is(':hidden') && $('#configuraciones').is(':hidden')) {
			if ($('#lbl-comenzar').text() === 'Detener')//Entra aca si acaba de detener el cronometro
			{
				$('#lbl-comenzar').text('Comenzar');
				document.getElementById('form:guardarTiempo').click();
			} else {
				if (tecla.keyCode === 32) {//para inspeccionar o poner a andar el cronometro solo se puede con [Espacio]
					if ($('#lblTiempoInspeccion').text()==='0'){ //si no tiene tiempo de inspeccion se quitan penalizaciones
						inspeccion = new Date().getTime();
						inicio = new Date().getTime();
						tiempo.penalizacion = false;
						tiempo.dnf = false;
						$('#lbl-comenzar').text('Inspeccionando');
					}
					if ($('#lbl-comenzar').text() === 'Comenzar'){ //Si hasta ahora va a inspeccionar
						inspeccion = new Date().getTime();
						$('#lbl-comenzar').text('Inspeccionando');
						if($('#lblTiempoInspeccion').text()!=='0'){
							tiempo.segundo = ($('#lblTiempoInspeccion').text());	
						}
					} else if ($('#lbl-comenzar').text() === 'Inspeccionando') { //Si ya estaba inspeccionando se pone a andar el cronometro
						if ($('#lblTiempoInspeccion').text()!=='0'){
						inicio = new Date().getTime();
						}
						$('#lbl-comenzar').text('Detener');
						//aca obtiene el tiempo usado inspeccionando
						document.getElementById('form:txtTiempoInspeccionUsadoMilisegundos').value = inicio-inspeccion;
						document.getElementById('form:txtTiempoInspeccionUsadoTexto').value = (inicio-inspeccion)/1000;
						if(parseInt(inicio-inspeccion) < parseInt($('#lblTiempoInspeccion').text()*1000)){
							tiempo.penalizacion = false;
							tiempo.dnf = false;
						} else if (parseInt(inicio-inspeccion) < parseInt(($('#lblTiempoInspeccion').text()*1000)+2000)) {
							tiempo.dnf = false;
						}
						tiempo.segundo = 0;
						tiempo.centisegundo = 0;
						tiempo.minuto = 0;
						clearInterval(tiempo_corriendo);
					}
					tiempo_corriendo = setInterval(function () {
						mostrarTiempo();
					}, 10);
				}
			}
		}
	});
	
	function mostrarTiempo(){
		actual = new Date().getTime();
		if (inicio!=0) {
			transcurrido = actual - inicio;
			tiempo.minuto = Math.floor(transcurrido/60000);
			transcurrido -= (tiempo.minuto*60000);
			tiempo.segundo = Math.floor(transcurrido/1000);
			transcurrido -= (tiempo.segundo*1000);
			tiempo.centisegundo = Math.floor(transcurrido/10);
			$('#lblTiempo').text((tiempo.minuto < 10 ? '0' + tiempo.minuto : tiempo.minuto) + ':' + (tiempo.segundo < 10 ? '0' + tiempo.segundo : tiempo.segundo) + '.' + (tiempo.centisegundo < 10 ? '0' + tiempo.centisegundo : tiempo.centisegundo));
		} else {
			inspeccionando = actual - inspeccion;
			tiempo.minuto = Math.floor(inspeccionando/60000);
			inspeccionando -= (tiempo.minuto*60000);
			tiempo.segundo = Math.floor(inspeccionando/1000);
			inspeccionando -= (tiempo.segundo*1000);
			tiempo.centisegundo = Math.floor(inspeccionando/10);
			if (parseInt(actual - inspeccion)<parseInt($('#lblTiempoInspeccion').text()*1000)){
				$('#lblTiempo').text($('#lblTiempoInspeccion').text()-tiempo.segundo);
			} else if (parseInt(actual - inspeccion) < parseInt(($('#lblTiempoInspeccion').text()*1000)+2000)) {
				$('#lblTiempo').text("+2");
			} else{
				inicio = new Date().getTime();
				$('#lbl-comenzar').text('Detener');
			}
		}
	}
});	
