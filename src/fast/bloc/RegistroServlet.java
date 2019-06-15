package fast.bloc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usu = req.getParameter("usuario");
		String contra = req.getParameter("clave");
		UsuariosDAO usuarios = (UsuariosDAO) getServletContext().getAttribute("usuarios");
		if(usuarios.crear(usu, contra)) {
			Usuario usuario = usuarios.existe(usu, contra);
			req.getSession().setAttribute("error-r", "false");
			req.getSession().setAttribute("usuario", usuario);
			resp.sendRedirect("menu.jsp");
		} else {
			req.getSession().setAttribute("error-r", "true");
			resp.sendRedirect("index.jsp");
		}
	}
	
	

}
