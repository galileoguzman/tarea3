//Definicion del paquete de la aplicacion
package tarea3;

//Import de librerias para el uso de Vector, SQL y DataSource
import java.sql.*;
import javax.naming.*;
import java.util.*;
import javax.sql.*;

public class DAOEjercicio
{
    //Propiedad conexion que se usara para crear y destruir una conexion con la bd
	private Connection con;

	public DAOEjercicio()
	{
    	//Constructor de la clase
	}

	public Connection conectar() 
	{
		/*
		* Metodo conectar que no recibe parametros
		* el cual regresara un objeto de tipo conexion
		* 
		*
		*/
        System.out.println("Hola conectar");
		try
		{
			if(this.con == null)
			{
				Context ctx = new InitialContext();

				DataSource mds = (DataSource)ctx.lookup("java:comp/env/jdbc/JavaDB");
				
                this.con = mds.getConnection(); 
				
                System.out.println("Conectado");
			}
		}catch(NamingException ne)
		{
			ne.printStackTrace();

            System.out.println("Error en el naming " + ne.getMessage());
		}catch (SQLException sqle)
		{
			sqle.printStackTrace();

            System.out.println("Error en el SQL e " + sqle.getMessage());

		}

		return this.con;
	}

	public void desconectar(Connection conexion)
	{
		/*
		* Metodo que se encarga de desconectar la aplicacion de la
		* base de datos
		**/
		try{
			if(!conexion.isClosed())
			{
				conexion.close();
                this.con = null;
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
	}	

	public int altaUsuario(Usuario user)
	{
        /**
        * Metodo altaUsuario recibe como parametro un obj de la clase Usuario
        * el cual a traves de sus metodos de extracion de datos se pasaran
        * como parametros al query SQL. Retorna 1 exito -1 fallido de la
        * operacion
        **/
		try
		{
			if(!this.con.isClosed())
			{
				String query = "INSERT INTO usuario(id, login, password) values (?, ?, ?)";

				PreparedStatement pstm = this.con.prepareStatement(query);
                pstm.setInt(1,Integer.parseInt(user.getId()));
                pstm.setString(2,user.getLogin());
                pstm.setString(3,user.getPassword());
				pstm.execute();
				return 1;
			}
		}catch (SQLException sqle)
		{
			sqle.printStackTrace();
		} 
		return -1;
	}

	public Usuario consultarUsuario(Usuario user)
	{
    	/**
    	* Metodo consultarUsuario recibe como parametro un obj usuario
    	* por el cual consultara su ID para poder compararlo en la
    	* Base de Datos y retorna el un obj de tipo Usuario
    	**/
    	Usuario uResultado = new Usuario();
    	try
    	{
    		if(!this.con.isClosed())
    		{
    			String query = "SELECT * FROM usuario where id = ?";

    			PreparedStatement pstm = this.con.prepareStatement(query);
                pstm.setInt(1, Integer.parseInt(user.getId()));

    			ResultSet resultado = pstm.executeQuery();

    			System.out.println("Fetch size " + resultado.getFetchSize());

                if(resultado.next()){
                    uResultado.setId(resultado.getString("id"));
                    uResultado.setLogin(resultado.getString("login"));
                    uResultado.setPassword(resultado.getString("password"));
                }
    			return uResultado;
    		}
    	}catch (SQLException sqle)
    	{
    		sqle.printStackTrace();
    	}
    	return null;
    }

    public Vector consultarUsuarios()
    {
    	/**
    	* Metodo consultarUsuarios el cual regresara todos los usuarios 
    	* agregados a la Base de Datos hasta el momento.
    	* Regresa un vector de la clase Vector
    	*
    	**/

    	Vector vector = new Vector(10,1);
    	try
    	{
    		if(!this.con.isClosed())
    		{
    			String query = "SELECT * FROM usuario";

    			PreparedStatement pstm = this.con.prepareStatement(query);
    			ResultSet resultado = pstm.executeQuery();

    			System.out.println("Fetch size " + resultado.getFetchSize());

    			while(resultado.next())
    			{
                    Usuario user = new Usuario();
    				user.setId(resultado.getString("id"));
    				user.setLogin(resultado.getString("login"));
    				user.setPassword(resultado.getString("password"));

    				vector.addElement(user);
    			}

    		}
    	}catch (SQLException sqle)
    	{
    		sqle.printStackTrace();
    	}
    	return vector;
    }

    public int eliminarUsuario(Usuario user)
    {
        /**
        * Metodo eliminarUsuario recibe como parametro un obj usuario
        * por el cual consultara su ID para poder compararlo en la
        * Base de Datos, en caso de encontrarlo lo elimina con un
        * Query SQL, Retorna 1 exito -1 fracaso
        **/
    	try
    	{
    		if(!this.con.isClosed())
    		{
    			String query = "DELETE FROM usuario where id = "+ user.getId();

    			PreparedStatement pstm = this.con.prepareStatement(query);
    			pstm.execute();
    			return 1;
    		}
    	}catch (SQLException sqle)
    	{
    		sqle.printStackTrace();
    	}
    	return -1;
    } 
}