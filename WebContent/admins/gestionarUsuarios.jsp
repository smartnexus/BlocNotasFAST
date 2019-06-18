<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, fast.bloc.Nota, fast.bloc.Usuario"%>
	
<jsp:useBean id="usuario" class="fast.bloc.Usuario" scope="session" />
<jsp:useBean id="usuarios" class="fast.bloc.UsuariosDAO" scope="application" />
<%
	List<Usuario> lista = usuarios.obtenerUsuarios();
%>


<!DOCTYPE html>
<html>
  <head>
    <title>Bloc de Notas - FAST: Mostrar notas</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="../css/estilo.css" />
    <script src="js/listarnotas.js"></script>
    <script src="js/gestionarUsuarios.js"></script>
  </head>
	
	<jsp:include page="cabecera.jsp" />

	<div id="lista">
		<h1>Lista de usuarios registrados</h1>
		<div id="lista-div">
			<table id="lista-tabla">
			<tr class="first">
				<td><p class="gestion">Nombre de usuario</p></td>
				<td><p class="gestion">Nivel de permisos</p></td>
				<td><p class="gestion">Opciones</p></td>
			</tr>
			<%
			for (Usuario u: lista) {
				String tipo = "Usuario";
				if(u.getTipo_usu() == 0) tipo = "Administrador";
			%>
				<tr id='fila-<%= u.getNombre() %>'>
					<td>
						<p class="gestion"><strong><%= u.getNombre() %></strong></p>
					</td>
					<td>
						<p class="gestion"><strong><%= tipo %></strong></p>
					</td>
					<td>
						<div class="botones">
							<button class="boton" onclick="cambiartipo_usu('<%= u.getNombre() %>','<%= u.getTipo_usu() %>')">Cambiar tipo</button>&nbsp;<button class="boton">Borrar</button>
						</div>
					</td>
				</tr>
			<%
			}
			%>
			</table>
		</div>

	</div>

	<%@include file="../pie.jsp"%>
  </body>
</html>