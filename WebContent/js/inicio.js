/* ECMAScript para el inicio de la aplicación*/

function muestraFormAcceso() {
	document.getElementById("formacceso").style.display = "block";
	document.getElementById("usuarioA").focus();
}
function ocultaFormAcceso() {
	document.getElementById("formacceso").style.display = "none";
}
function muestraFormRegistro() {
	document.getElementById("formregistro").style.display = "block";
	document.getElementById("usuarioR").focus();
}
function ocultaFormRegistro() {
	document.getElementById("formregistro").style.display = "none";
}
window.addEventListener("load", function() {
	document.getElementById("botonAcceso").onclick = muestraFormAcceso;
	document.getElementById("botonCancelarA").onclick = ocultaFormAcceso;
	document.getElementById("botonRegistro").onclick = muestraFormRegistro;
	document.getElementById("botonCancelarR").onclick = ocultaFormRegistro;
});
