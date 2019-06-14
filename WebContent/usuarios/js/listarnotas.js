"use strict";

//Indica el id de la ultima nota cuyo detalle se ha mostrado
var ultid=-1;
var s = 0;

//Esta funcion sirve para mostrar un mensaje mientras se obtienen los detalles
function mostrarEsperando(elemento) {
	var html = "<p><strong>Obteniendo detalles...</strong></p>"+
				"<img src='../imagenes/espera.gif' alt='Espera' />";
	elemento.innerHTML=html;
}
//Muestra los detalles obtenidos mediante AJAX.
//objetoDetalle, tiene que ser un objeto con las siguientes propiedades:
//	nota: texto de la nota
//	imagen: url de la imagen asociada a la nota
//  error: texto con el error producido al buscar los detalles de una nota
function mostrarDetalle(elemento, objetoDetalle) {
	if (objetoDetalle.error != null && objetoDetalle.error !="") {
		//error
		elemento.innerHTML="<p>Error: "+objetoDetalle.error+"</p>";
	} else {
		elemento.innerHTML=
			"<p>Categoría: " + objetoDetalle.categoria + "</p>" +
			"<p class='textonota' style='background-color:" + objetoDetalle.color  + "'>"+
			objetoDetalle.nota+"</p><p>"+
			"<img src='"+objetoDetalle.imagen+"' alt='Sin imagen'/><br />"+
			"<button class='boton' onclick='window.open(\"editarnota.jsp?id="+ultid+"\")' target='_blank'>Editar</button>&nbsp;"+
			"<button class='boton' onclick='borrar(event, ultid);'>Borrar</button></p>";
	}
}

//Muestra informacion sobre la nota con el identificador pasado
function mostrar() {
	//el padre tiene un id del tipo "info-num"
	var prefix = "info-";
	var id = parseInt(this.parentElement.id.substring(prefix.length));
	
	if (ultid > 0) {
		//Ocultamos el anterior detalle
		document.getElementById("detalle-"+ultid).style.display="none";
	}
	if (ultid == id)  //en este caso, no mostramos, solo ocultamos
		ultid = -1; 	
	else {
		ultid = id;
		
		//Cambiamos el detalle
		var divDetalle = document.getElementById("detalle-"+ultid);
		mostrarEsperando(divDetalle);
		divDetalle.style.display="block"; //Hacemos visible
		

		//Peticion AJAX
		var peticion="nota?id="+ultid;
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST",peticion,true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp.onreadystatechange = function(){ 
			if (xmlhttp.readyState==4) {
				if (xmlhttp.status==200) { 
					//Respuesta recibida completamente (4) y sin
					//errores del servidor (codigo HTTP 200) 
					//Analizamos
					var detalleNota = JSON.parse(xmlhttp.responseText);
					mostrarDetalle(divDetalle, detalleNota);
				} else {
					divDetalle.innerHTML=cabDetalle+"<p>Error</p>";
				}
			}
		  };
		xmlhttp.send("id="+ultid); //enviamos
	}
}

function deleteAll() {
	var checkboxs = document.querySelectorAll(".infonota input");
	var j = [];
	for(var i=0; i< checkboxs.length; i++) {
		if(checkboxs[i].checked) j.push(checkboxs[i].id.split("-")[1]);
	}
	if(j.length > 0) {
		var ok = confirm("¿Borrar todas las notas selecionadas?");
		for(var k=0; k < j.length; k++) {
			borrar(null, parseInt(j[k]))
		}
	} else {
		alert("No ha seleccionado ninguna nota")
	}
	
}

//Muestra informacion sobre la nota con el identificador pasado
function borrar(event, id) {
	
	//Para evitar que se oculte el detalle
	if(event) event.stopPropagation();
	
	if (!event || ultid == id) { //en este caso, borramos
		
		
		//Peticion AJAX
		var peticion="borranota";
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST",peticion,true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp.onreadystatechange = function(){
			if (xmlhttp.readyState==4) {
				if (xmlhttp.status==200) { 
					//Respuesta recibida completamente (4) y sin
					//errores del servidor (codigo HTTP 200) 
					//Analizamos
					var resultadoBorrar = JSON.parse(xmlhttp.responseText);
					procesarResultadoBorrar(resultadoBorrar);
				} else {
					divDetalle.innerHTML=cabDetalle+"<p>Error</p>";
				}
			}
		  };
		xmlhttp.send("id="+id); //enviamos
	}
	
}

//Procesa resultados de borrar obtenidos mediante AJAX.
//resultadoBorrar, tiene que ser un objeto con las siguientes propiedades:
//	id: identificador numérico de la nota
//error: texto con el error producido al borrar nota. 
//        Es una cadena vacía si se borró correctamente.
function procesarResultadoBorrar(resultadoBorrar) {
	//Si tiene mensaje de error, mostramos como mensaje emergente.
	if (resultadoBorrar.error) {
		alert(resultadoBorrar.error);
	} else {
		//Eliminamos elemento de la tabla
		var fila = document.getElementById("fila-"+resultadoBorrar.id);
		fila.parentNode.removeChild(fila);
		//cambiamos ultid
		ultid = -1;
	}
}
function toggle() {
	var checkboxs = document.querySelectorAll(".infonota input");
	for(var i=0; i< checkboxs.length; i++) {
		checkboxs[i].checked=s==0?true:false;
	}
	if(s == 1) s = 0;
	else s = 1;
	
}

window.addEventListener("load", function() {
	var infos = document.querySelectorAll(".infonota");
	for(var i=0; i< infos.length; i++) {
		infos[i].onclick=mostrar;
	}
});

