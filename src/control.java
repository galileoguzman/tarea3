package tarea3;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;

import javax.sql.*;

public class control  extends HttpServlet
{
	private Connection con;
	private DAOAcceso control;
    
    public void init()
    {
    	control = new DAOAcceso();
    }
    
	public void doGet(HttpServletRequest request, HttpServletResponse response)
										   throws ServletException, IOException
    {
    	PrintWriter salida = response.getWriter();
    	for (int x = 0; x < 101; x++)
    	{
    		boolean resultado = control.conectar();
    		if (resultado)
    			salida.println("Conexion #"+ x +" al DataSource mediante DAO");
    		else
    			salida.println("Error de conexion al DataSource en la conexion # "+x);
    	}    
    	
    }
   
      
}