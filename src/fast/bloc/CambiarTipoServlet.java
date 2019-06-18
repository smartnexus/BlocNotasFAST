package fast.bloc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admins/cambiarusu")
public class CambiarTipoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CambiarTipoServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		String usuarioMod = request.getParameter("usuario");
		Integer tipo = Integer.parseInt(request.getParameter("tipo_usu"));
		String mensajeError = "";
	    UsuariosDAO usuarios = (UsuariosDAO) getServletContext().getAttribute("usuarios");
		try {
			if (!usuarios.cambiar(usuarioMod, tipo)) {
				mensajeError = "No se ha podido cambiar";
			}

		} catch (DAOException e) {
			mensajeError = e.getMessage();
		} catch (NumberFormatException e) {
			mensajeError = "Parámetro incorrecto";
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	    response.getWriter().println(
	    		"{ \"usuario\":\"" + usuario + "\", \"error\":\""+ mensajeError +"\" }");
	
	}

}
