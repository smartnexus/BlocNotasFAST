package fast.bloc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
	private DataSource ds = null;
	
	public UsuariosDAO() {
	}
	
	public UsuariosDAO(DataSource ds) {
		super();
		this.ds = ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public DataSource getDs() {
		return ds;
	}
	
	/**
	 * Comprueba si un usuario existe y la clave es correcta.
	 * Devuelve objeto Usuario relleno si existe
	 * @param nombre Nombre del usuario
	 * @param clave Clave del usuario
	 * @return Objeto Usuario relleno o null si no existe.
	 */
	public boolean crear(String nombre, String clave) {
		//Usuario usuario = new Usuario(nombre, Usuario.CLIENTE);
		Connection conn;
		boolean res = false;
		
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO usuarios (nombre, clave, tipo_usu) VALUES (?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nombre);
			st.setString(2, clave);
			st.setInt(3, Usuario.CLIENTE);
			int contador = st.executeUpdate();
			if (contador == 1) {
				System.out.println("Se ha creado el usuario="+nombre);
				res=true;
			}
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			res=false;
			System.out.println("El usuario ya existe. UsuariosDAO");
		}
		
		return res;
	}
	
	public boolean cambiar(String usuario, int tipo) throws DAOException {
		//El tipo que recibe es al que se quiere cambiar.
		Connection conn;
		boolean res = false;
		try {
			conn = ds.getConnection();
			String sql = "UPDATE usuarios SET tipo_usu = ? WHERE nombre=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, tipo);
			st.setString(2, usuario);
			int contador = st.executeUpdate();
			if(contador == 1 ) {
				res = true;
				System.out.println("Se ha actualizado el tipo del usuario=" + usuario + " a " + (tipo==0?"Administrador":"Usuario"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error de acceso a la base de datos. UsuariosDAO.");
			throw new DAOException("No se ha podido actualizar el tipo de usuario.");
		}
		return res;
		
	}
	
	public List<Usuario> obtenerUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			Connection conn = ds.getConnection();
			String sql = "SELECT nombre, tipo_usu FROM usuarios";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNombre(rs.getString(1));
				usuario.setTipo_usu(rs.getInt(2));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error de acceso a la base de datos. UsuariosDAO.");
		}
		
		return usuarios;
	}
	
	Usuario existe(String nombre, String contra) {
		
		Usuario usuario = null; 
		Connection conn;
		try {
			System.out.println("Se va a comprobar el usuario="+nombre+" y la clave="+contra);
			conn = ds.getConnection();
			String sql = "SELECT * FROM usuarios WHERE nombre=? AND clave=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nombre);
			st.setString(2, contra);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				System.out.println("Se ha encontrado el usuario y la clave coincide.");
				usuario = new Usuario();
				usuario.setNombre(nombre);
				usuario.setTipo_usu(rs.getInt(3));
				System.out.println("El tipo de usuario es="+usuario.getTipo_usu());
			}
			else {
				System.out.println("No se ha encontrado el usuario o la clave no coincide.");
			}
			rs.close();
			st.close();
			conn.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error de acceso a la base de datos. UsuariosDAO.");
		}

		return usuario;
	}
	
	
}
