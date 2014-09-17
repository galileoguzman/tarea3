package tarea3;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class DAOAcceso 
{
	private Connection con;

    public DAOAcceso()
    {
    }
    
    public boolean conectar() 
	{
		boolean resultado = false;
		
		try
		{
			// Context sera un objeto que represente una lista de propiedades asociadas a la aplicacian.
			Context ctx = new InitialContext();
			
			//Se crea un DataSource en base a una propiedad definida en el contexto de la aplicacion y la cual
			// es identificada con el nombre "TestDB"
			DataSource mds = (DataSource) ctx.lookup("java:comp/env/jdbc/JavaDB");
			
			//En base a la fuente de datos previamente instanciada se solicita una conexion a la base de datos
			con = mds.getConnection(); 
				System.out.println("Conectado");
			resultado = true;
			
		}
		
		catch(NamingException ne)
        {
				ne.printStackTrace();
        }
                
        catch (SQLException sqle)
		{
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
				
        }
        
        return resultado;
	}
    
    
}