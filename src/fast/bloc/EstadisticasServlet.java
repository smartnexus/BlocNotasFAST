package fast.bloc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/usuarios/estadisticas")
public class EstadisticasServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EstadisticasServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		
	}

}
