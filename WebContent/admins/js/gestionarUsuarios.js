"use strict";

function cambiartipo_usu(usuario, tipo) {
	var tipofinal;
	switch(tipo) {
	case '0':
		tipofinal = 1;
		break;
	case '1':
		tipofinal = 0
		break;
	default:
		tipofinal = -1;
		break;
	}
	var tipo = "Usuario";
	if(tipofinal == 0) tipo = "Administrador";
	if(tipofinal != -1) {
		//Peticion AJAX
		var peticion="cambiarusu";
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST",peticion,true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp.onreadystatechange = function(){ 
			if (xmlhttp.readyState==4) {
				if (xmlhttp.status==200) { 
					//Respuesta recibida completamente (4) y sin
					//errores del servidor (codigo HTTP 200) 
					//Analizamos
					var res = JSON.parse(xmlhttp.responseText);
				
					if(res.error.length == 0) {
						document.getElementById("fila-" + usuario).innerHTML = '<td><p class="gestion"><strong>' + usuario + 
						'</strong></p></td><td><p class="gestion"><strong>' + tipo + 
						'</strong></p></td><td><div class="botones"><button class="boton" onclick="cambiartipo_usu(\'' + usuario +'\',\'' + tipofinal + 
						'\')">Cambiar tipo</button>&nbsp;<button class="boton">Borrar</button></div></td>'
					} else {
						alert(res.error);
					}
				} else {
					alert("Error en la petición al cambiar el tipo, por favor inténtelo de nuevo.")
				}
			}
		  };
		xmlhttp.send("usuario="+usuario+"&tipo_usu=" + tipofinal); //enviamos
	} else {
		alert("Error en la petición al cambiar el tipo, por favor recargue la página.")
	}
	
}