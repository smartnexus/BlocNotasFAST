package fast.bloc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/usuarios/estadisticas")
public class ObtenerEstadisticasServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ObtenerEstadisticasServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		String todos = request.getParameter("todos");
		NotasDAO notas = (NotasDAO) getServletContext().getAttribute("notas");
		List<String> textos = null;
		int numeroMensajes = 0, mediaMensajes = 0;
		
		if(todos != null && usuario.getTipo_usu() == 0) {
			 textos = ((NotasDAO) notas).obtenerTextoNotas(usuario.getNombre(), true);
		} else {
			textos = ((NotasDAO) notas).obtenerTextoNotas(usuario.getNombre(), false);
		}
		
		if(textos.size() > 0) {
			for(String str : textos) {
				mediaMensajes += str.length();
				numeroMensajes++;
			}
			mediaMensajes /= numeroMensajes;
		} else {
			System.out.println("No hay mensajes");
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(
				"{ \"numMensajes\":\"" + numeroMensajes + "\" , \"mediaMensajes\":\"" + mediaMensajes+ "\"}");

	}
	


}
