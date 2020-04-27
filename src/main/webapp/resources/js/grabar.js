/**
 * Grabar vídeo y audio obtenido del micrófono y cámara web
 * con JavaScript, seleccionando el dispositivo de grabación de audio
 * y el dispositivo de vídeo (cámara web) de una lista; 
 * usando MediaRecorder y getUserMedia
 * 
 * @author parzibyte
 * @see https://parzibyte.me/blog
 */
const init = () => {
    const tieneSoporteUserMedia = () =>
        !!(navigator.mediaDevices.getUserMedia)

    // Si no soporta...
    // Amable aviso para que el mundo comience a usar navegadores decentes ;)
    if (typeof MediaRecorder === "undefined" || !tieneSoporteUserMedia())
        return alert("Tu navegador web no cumple los requisitos; por favor, actualiza a un navegador decente como Firefox o Google Chrome");


    // Declaración de elementos del DOM
    const $dispositivosDeAudio = document.querySelector("#dispositivosDeAudio"),
        $dispositivosDeVideo = document.querySelector("#dispositivosDeVideo"),
        $duracion = document.querySelector("#duracion"),
        $video = document.querySelector("#video"),
        $btnComenzarGrabacion = document.querySelector("#btnComenzarGrabacion"),
        $btnDetenerGrabacion = document.querySelector("#btnDetenerGrabacion");

    // Algunas funciones útiles
    const limpiarSelect = elemento => {
        for (let x = elemento.options.length - 1; x >= 0; x--) {
            elemento.options.remove(x);
        }
    }

    const segundosATiempo = numeroDeSegundos => {
        let horas = Math.floor(numeroDeSegundos / 60 / 60);
        numeroDeSegundos -= horas * 60 * 60;
        let minutos = Math.floor(numeroDeSegundos / 60);
        numeroDeSegundos -= minutos * 60;
        numeroDeSegundos = parseInt(numeroDeSegundos);
        if (horas < 10) horas = "0" + horas;
        if (minutos < 10) minutos = "0" + minutos;
        if (numeroDeSegundos < 10) numeroDeSegundos = "0" + numeroDeSegundos;

        return `${horas}:${minutos}:${numeroDeSegundos}`;
    };
    // Variables "globales"
    let tiempoInicio, mediaRecorder, idIntervalo;
    const refrescar = () => {
        $duracion.textContent = segundosATiempo((Date.now() - tiempoInicio) / 1000);
    }

    // Consulta la lista de dispositivos de entrada de audio y llena el select
    const llenarLista = () => {
        navigator
            .mediaDevices
            .enumerateDevices()
            .then(dispositivos => {
                limpiarSelect($dispositivosDeAudio);
                limpiarSelect($dispositivosDeVideo);
                dispositivos.forEach((dispositivo, indice) => {
                    if (dispositivo.kind === "audioinput") {
                        const $opcion = document.createElement("option");
                        // Firefox no trae nada con label, que viva la privacidad
                        // y que muera la compatibilidad
                        $opcion.text = dispositivo.label || `Micrófono ${indice + 1}`;
                        $opcion.value = dispositivo.deviceId;
                        $dispositivosDeAudio.appendChild($opcion);
                    } else if (dispositivo.kind === "videoinput") {
                        const $opcion = document.createElement("option");
                        // Firefox no trae nada con label, que viva la privacidad
                        // y que muera la compatibilidad
                        $opcion.text = dispositivo.label || `Cámara ${indice + 1}`;
                        $opcion.value = dispositivo.deviceId;
                        $dispositivosDeVideo.appendChild($opcion);
                    }
                })
            })
    };
    // Ayudante para la duración; no ayuda en nada pero muestra algo informativo
    const comenzarAContar = () => {
        tiempoInicio = Date.now();
        idIntervalo = setInterval(refrescar, 500);
    };

    // Comienza a grabar el audio con el dispositivo seleccionado
    const comenzarAGrabar = () => {
        if (!$dispositivosDeAudio.options.length) return alert("No hay micrófono");
        if (!$dispositivosDeVideo.options.length) return alert("No hay cámara");
        // No permitir que se grabe doblemente
        if (mediaRecorder) return alert("Ya se está grabando");

        navigator.mediaDevices.getUserMedia({
                audio: {
                    deviceId: $dispositivosDeAudio.value, // Indicar dispositivo de audio
                },
                video: {
                    deviceId: $dispositivosDeAudio.value, // Indicar dispositivo de vídeo
                }
            })
            .then(stream => {
                // Poner stream en vídeo
                $video.srcObject = stream;
                $video.play();
                // Comenzar a grabar con el stream
                mediaRecorder = new MediaRecorder(stream);
                mediaRecorder.start();
                comenzarAContar();
                // En el arreglo pondremos los datos que traiga el evento dataavailable
                const fragmentosDeAudio = [];
                // Escuchar cuando haya datos disponibles
                mediaRecorder.addEventListener("dataavailable", evento => {
                    // Y agregarlos a los fragmentos
                    fragmentosDeAudio.push(evento.data);
                });
                // Cuando se detenga (haciendo click en el botón) se ejecuta esto
                mediaRecorder.addEventListener("stop", () => {
                    // Pausar vídeo
                    $video.pause();
                    // Detener el stream
                    stream.getTracks().forEach(track => track.stop());
                    // Detener la cuenta regresiva
                    detenerConteo();
                    // Convertir los fragmentos a un objeto binario
                    const blobVideo = new Blob(fragmentosDeAudio);
                    var hoy = new Date();
                    var fecha = '' + hoy.getFullYear() + ' ' + ( hoy.getMonth() + 1 ) + ' ' + hoy.getDate() + '_' + hoy.getHours() + ' ' + hoy.getMinutes() + ' ' + hoy.getSeconds();
                    // Crear una URL o enlace para descargar
                    const urlParaDescargar = URL.createObjectURL(blobVideo);
                    // Crear un elemento <a> invisible para descargar el audio
                    let a = document.createElement("a");
                    document.body.appendChild(a);
                    a.style = "display: none";
                    a.href = urlParaDescargar;
                    a.download = "video_"+fecha+".webm";
                    // Hacer click en el enlace
                    a.click();
                    // Y remover el objeto
                    window.URL.revokeObjectURL(urlParaDescargar);
                });
            })
            .catch(error => {
                // Aquí maneja el error, tal vez no dieron permiso
                console.log(error)
            });
    };


    const detenerConteo = () => {
        clearInterval(idIntervalo);
        tiempoInicio = null;
        $duracion.textContent = "";
    }

    const detenerGrabacion = () => {
        if (!mediaRecorder) return alert("No se está grabando");
        mediaRecorder.stop();
        mediaRecorder = null;
    };


    $btnComenzarGrabacion.addEventListener("click", comenzarAGrabar);
    $btnDetenerGrabacion.addEventListener("click", detenerGrabacion);

    // Cuando ya hemos configurado lo necesario allá arriba llenamos la lista

    llenarLista();
}

// Esperar a que el documento esté listo...
document.addEventListener("DOMContentLoaded", init);