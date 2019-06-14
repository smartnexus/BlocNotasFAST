<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="java.util.List, fast.bloc.Nota, fast.bloc.DAOException"%>

<jsp:useBean id="usuario" class="fast.bloc.Usuario" scope="session"/>
<jsp:useBean id="notas" class="fast.bloc.NotasDAO" scope="application"/>
<jsp:useBean id="nota" class="fast.bloc.Nota"/>
<jsp:setProperty property="*" name="nota"/>

<!DOCTYPE html>
<html>
<head>
	<title>Bloc de Notas - FAST: Crear/Editar nota</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="../css/estilo.css"/>
	<script src="js/crearnotas.js"></script>
</head>
<body>
	<jsp:include page="cabecera.jsp" />
	
	<%
	String id = request.getParameter("id");
	String mensajeError = "";
	
	if(request.getParameterMap().containsKey("titulo")) {
		try {
			nota.setNombreUsuario(usuario.getNombre());
			if(!notas.actualizar(Integer.parseInt(id), usuario.getNombre(), nota)) {
				mensajeError = "No se ha podido insertar la nota";
			}
	
		} catch(DAOException e) {
			mensajeError = e.getMessage();
		} catch(NumberFormatException e) {
			mensajeError = "Parámetro incorrecto";
		}
		
		if(!mensajeError.isEmpty()) {
		%>
			<div id="error"><p> ERROR: 
			<%= mensajeError %>
			</p></div>
		<%
		} else { %>
			<div id="exito"><p> INFO: NOTA ACTUALIZADA</p></div>
		<%
		}
	} else if(id!=null) {
		
		try {
			nota = notas.obtener(Integer.parseInt(id), usuario.getNombre());
			if (nota == null) {
				mensajeError = "La nota no existe.";
			}
		} catch (DAOException e) {
			mensajeError = e.getMessage();
		} catch (NumberFormatException e) {
			mensajeError = "Parámetro incorrecto";
		}
	}
	%>
	<div id="crear">
		<h1>Editar nota</h1>
		<div id="formcrear">
	<%
		if(id==null || nota==null || !mensajeError.isEmpty()) {
			out.println("<script>alert('No se ha indicado ninguna nota válida');</script>");
		} else {
			request.setAttribute("nota", nota);
	%>
		<jsp:include page="formularionota.jsp" >
			<jsp:param name="mensajeReset" value="Recargar datos guardados" />
		</jsp:include>
	<% 	} %>		
		</div>
	</div>

	<%@include file="../pie.jsp"%>
  </body>
</html>