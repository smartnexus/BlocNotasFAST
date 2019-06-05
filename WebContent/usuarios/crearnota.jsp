<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="java.util.List, fast.bloc.Nota, fast.bloc.DAOException"%>

<jsp:useBean id="usuario" class="fast.bloc.Usuario" scope="session" />
<jsp:useBean id="notas" class="fast.bloc.NotasDAO" scope="application" />
<jsp:useBean id="nota" class="fast.bloc.Nota" />
<jsp:setProperty property="*" name="nota"/>

<!DOCTYPE html>
<html>
<head>
	<title>Bloc de Notas - FAST: Crear nota</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="../css/estilo.css" />
	<script src="js/listarnotas.js"></script>
	<script src="js/crearnotas.js"></script>
</head>

	<jsp:include page="cabecera.jsp" />
	
	<% // Código HTML + JSP  
	//TODO esto se podría hacer con AJAX y se eliminaría el código
	if ( nota.getTitulo() != null) {
		//Creamos notas
		
		String mensajeError = "";
		
		try {
			// el nombre de usuario se obtiene del atributo de sesión usuario
			nota.setNombreUsuario(usuario.getNombre());
			if ( !notas.insertar(nota) ) {
				mensajeError = "No se ha podido insertar la nota";
			}
	
		} catch (DAOException e) {
			mensajeError = e.getMessage();
		}
		
	    //Muestra error o exito
		if ( ! mensajeError.isEmpty() ) {
		%>
			<div id="error"><p> ERROR: 
			<%= mensajeError %>
			</p></div>
		<%
		} else { %>
			<div id="exito"><p> INFO: NOTA CREADA</p></div>
	<%
		}
	} 
	//mostramos formulario
	%>


	<div id="crear">
		<h1>Crear nota</h1>
		<div id="formcrear">
			<form method="post" action="" onsubmit="return validacion();">
			<div class="titulo-div">
				<label for="titulo"><strong>T&iacute;tulo de la nota</strong></label>
				<input id="titulo" type="text" value="" name="titulo" maxlength="100"
					required="required"></input>
			</div>
			<div class="categoria-div">
				<label for="categoria"><strong>Categoría</strong></label>
				<div>
				<select id="categoria" name="categoria">
					<option>personal</option>
					<option>trabajo</option>
				</select>
				<button onclick="incorporarCategoria(event)">+</button>
				</div>
			</div>
			<div class="color-div">
				<label for="color"><strong>Color</strong></label>
				<div>
				<select id="selector" onchange="actualizaColor(this)">
					<option value="#FF0000">red</option>
					<option value="#00FF00">green</option>
					<option value="#0000FF">blue</option>
					<option value="#000000">black</option>
				</select>
				+<input id="color" type="color" onchange="cambiaFondo()" name="color"></input>
				</div>
			</div>
			<div class="imagen-div">
				<label for="urlimagen"><strong>URL de la imagen</strong></label>
				<input id="urlimagen" type="text" value="" name="urlimagen"></input>
			</div>
			<div class="nota-div">
				<label for="nota"><strong>Nota</strong></label>
				<textarea id="nota" name="nota" cols="100%" rows="100%"></textarea>
			</div>
			<input class="boton" id="enviarnota" type="submit" value="Crear" name="enviarnota"></input>
			<input class="boton" id="limpiar" type="reset" value="Limpiar datos" name="limpiar"></input>
			</form>
		</div>
	</div>
	

	<%@include file="../pie.jsp"%>
  </body>
</html>