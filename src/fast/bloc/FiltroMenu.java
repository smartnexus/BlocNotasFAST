package fast.bloc;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltroClientes
 */
@WebFilter("/menu.jsp")
public class FiltroMenu implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroMenu() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		if(usuario != null) {
			if(usuario.getTipo_usu() == 0) {
				req.getRequestDispatcher("/admins/menu.jsp").forward(req, res);
			} else {
				req.getRequestDispatcher("/usuarios/menu.jsp").forward(req, res);
			}
		} else {
			res.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso prohibido");
		}
				
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
