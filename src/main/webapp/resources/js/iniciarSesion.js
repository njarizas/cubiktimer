	function redirigir(){
		console.log('entro');
		var u=document.getElementById('email').value;
		var c=document.getElementById('password').value;
		if(u=="jugador@gmail.com" ){
			console.log('entro como jugador');
			if(c=="jugad"){
			document.getElementById('iniciarSesion').href="registroBasicoDeJuego.jsp"
			}
			else{
				swal("Error!", "Contraseña incorrecta", "error");
				document.getElementById('iniciarSesion').href="#"
			}
		}
		else if (u=="instructor@gmail.com"){
			console.log('entro como instructor');
			if(c=="instr"){
			document.getElementById('iniciarSesion').href="registroIntermedioDeJuego.jsp"
			}
			else{
				swal("Error!", "Contraseña incorrecta", "error");
				document.getElementById('iniciarSesion').href="#"
			}
		}
		else if (u=="administrador@gmail.com"){
			console.log('entro como administrador');
			if(c=="admin"){
			document.getElementById('iniciarSesion').href="tablaUsuarios.jsp"
			}
			else{
				swal("Error!", "Contraseña incorrecta", "error");
				document.getElementById('iniciarSesion').href="#"
			}
		}
		else{
			swal("Atención!", "no existe un usuario con ese nombre y contraseña", "warning");
			//alert('no existe un usuario con ese nombre y contraseña');
			document.getElementById('iniciarSesion').href="#"
		}
	}