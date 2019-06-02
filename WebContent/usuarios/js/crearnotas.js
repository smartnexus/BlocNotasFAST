"use strict"
var counter = 1;

function cambiaFondo(def) {
	var color = document.getElementById("color").value;
	document.getElementById("nota").style.backgroundColor = color;
	if(!def) {
		document.getElementById("selector").innerHTML += "<option value='" + color + "'>custom " + counter + "</option>";
		document.getElementById("selector").value = color;
		counter++;
	}
}

function actualizaColor(id) {
	var color = document.getElementById("selector").value;
	document.getElementById("color").value = color;
	cambiaFondo(true);
}

function incorporarCategoria(event) {
	event.preventDefault();
	var nueva = prompt("Escriba el nombre de la categoría");
	document.getElementById("categoria").innerHTML += "<option>" + nueva + "</option>";
	document.getElementById("categoria").value = nueva;
}
window.addEventListener("load", function() {
	var def = "#FF0000"
	document.getElementById("color").value = def;
	document.getElementById("selector").value = def;
	cambiaFondo(def);
});