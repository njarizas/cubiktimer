$(document).ready(function () {
	
	var ocho = new Audio("resources/audio/ocho.mp3");
	var doce = new Audio("resources/audio/doce.mp3");
	var ochoSegundos = true;
	var doceSegundos = true; 

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
	
	var estado = 0;
	
	$("#boton-movil").click(function() {
		if(/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|ipad|iris|kindle|Android|Silk|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(navigator.userAgent) 
			    || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(navigator.userAgent.substr(0,4))) {
		keyDown(32);
		keyUp(32);
		}
		});

	//(Cuando se oprime la tecla) Esta función detiene el cronometro
	$(document).keydown(function (tecla) {
		keyDown(tecla.keyCode);
	});
	
	function keyDown(keyCode){
		if (keyCode !== 116 && $('#mezclaPersonalizada').is(':hidden')) {
			if ($('#lbl-comenzar').text() === 'Detener' && estado===2) {//si el cronometro esta corriendo
				estado = 3;
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
	}

	//(Cuando se levanta la tecla) Esta función pone el conteo regresivo o el cronometro según sea el caso
	$(document).keyup(function (tecla) {
		keyUp(tecla.keyCode)
	});
	
	function clics(){
		document.getElementById('form:guardarTiempo').click();
	}
	
	function keyUp(keyCode){
		if (keyCode !== 116 && $('#mezclaPersonalizada').is(':hidden')) {
			if ($('#lbl-comenzar').text() === 'Detener' && estado===3)//Entra aca si acaba de detener el cronometro
			{
				$('#lbl-comenzar').text('Comenzar');
				document.getElementById('btnDetenerGrabacion').click();
				setTimeout(clics, 1000);
			} else {
				if (keyCode === 32) {//para inspeccionar o poner a andar el cronometro solo se puede con [Espacio]
					if ($('#lblTiempoInspeccion').text()==='0'){ //si no tiene tiempo de inspeccion se quitan penalizaciones
						$("#secuencia").addClass("hidden");
						inspeccion = new Date().getTime();
						inicio = new Date().getTime();
						tiempo.penalizacion = false;
						tiempo.dnf = false;
						$('#lbl-comenzar').text('Inspeccionando');
						estado = 1;
					}
					if ($('#lbl-comenzar').text() === 'Comenzar' && estado===0){ //Si hasta ahora va a inspeccionar
						$("#secuencia").addClass("hidden");
						estado = 1;
						inspeccion = new Date().getTime();
						$('#lbl-comenzar').text('Inspeccionando');
						if($('#lblTiempoInspeccion').text()!=='0'){
							tiempo.segundo = ($('#lblTiempoInspeccion').text());	
						}
						document.getElementById('btnComenzarGrabacion').click();
					} else if ($('#lbl-comenzar').text() === 'Inspeccionando' && estado===1) { //Si ya estaba inspeccionando se pone a andar el cronometro
						estado = 2;
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
	}
	
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
			if(parseInt(actual - inspeccion)>=12000){
				if (doceSegundos){
					doce.play();
					doceSegundos = false;
				}
			}
			else if(parseInt(actual - inspeccion)>=8000){
				if (ochoSegundos){
					ocho.play();
					ochoSegundos = false;
				}
			}
			if (parseInt(actual - inspeccion)<parseInt($('#lblTiempoInspeccion').text()*1000)){
				$('#lblTiempo').text($('#lblTiempoInspeccion').text()-tiempo.segundo);
			} else if (parseInt(actual - inspeccion) < parseInt(($('#lblTiempoInspeccion').text()*1000)+2000)) {
				$('#lblTiempo').text("+2");
			} else{
				inicio = new Date().getTime();
				estado = 2;
				$('#lbl-comenzar').text('Detener');
			}
		}
	}
});	
