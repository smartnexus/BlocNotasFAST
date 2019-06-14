<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="java.util.List"%>
<jsp:useBean id="notas" class="fast.bloc.NotasDAO" scope="application" />
<jsp:useBean id="usuario" class="fast.bloc.Usuario" scope="session" />
<jsp:useBean id="nota" class="fast.bloc.Nota" scope="request" />

<form method="post" action="" id="notaform">
	<div class="titulo-div">
		<label for="titulo"><strong>T&iacute;tulo de la nota</strong></label>
		<input id="titulo" type="text" name="titulo" maxlength="100"
			required="required" value="${nota.titulo}"></input>
	</div>
	<div class="categoria-div">
		<label for="categoria"><strong>Categoría</strong></label>
		<div>
		<select id="categoria" name="categoria">
			<% if(nota.getCategoria() != null) { %>
			<option>${nota.categoria}</option>
			<% } %>
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
		+<input id="color" type="color" onchange="cambiaFondo()" name="color" value="${nota.color}"></input>
		</div>
	</div>
	<div class="imagen-div">
		<label for="urlimagen"><strong>URL de la imagen</strong></label>
		<input id="urlimagen" type="text" name="urlimagen" value="${nota.urlimagen}"></input>
	</div>
	<div class="nota-div">
		<label for="nota"><strong>Nota</strong></label>
		<textarea id="nota" name="nota" cols="100%" rows="100%">${nota.nota}</textarea>
	</div>
	<input class="boton" id="enviarnota" type="submit" value="Enviar" name="enviarnota"></input>
	<input class="boton" id="limpiar" type="reset" value="Limpiar datos" name="limpiar"></input>
</form>