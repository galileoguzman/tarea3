// se define el paquete al cual pertenecera la clase
package tarea3;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class ServletControlador extends HttpServlet
{
	//metodo init
	public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
  	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
											throws ServletException, IOException
	{
		

		//Codigo para poder realizar el forward
		ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher("/ServletContenedor");
        
		Enumeration nombreParametros = request.getParameterNames();

		if (nombreParametros.hasMoreElements() == false)
		{
			System.out.println("La peticion no tiene parametros, corre en circulos :-<");
		}
		else
		{
			//recibimos parametros por request.getParameter
			String operacion = request.getParameter("operacion");
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String password = request.getParameter("password");

			//System.out.println("controlador " + id + login + password);
			
			if(operacion.equals("alta"))
			{
				Usuario user = new Usuario();

				user.setId(id);
				user.setLogin(login);
				user.setPassword(password);
				
				System.out.println("controlador user " + user.getId() + user.getLogin() + user.getPassword());

				request.setAttribute("user", user);
				
			}else if(operacion.equals("consultar"))
			{
				
				Usuario user = new Usuario();
				user.setId(id);
				request.setAttribute("user", user);
				
			}else if(operacion.equals("eliminar"))
			{
		
				Usuario user = new Usuario();
				user.setId(id);
				request.setAttribute("user", user);
				
			}else
			{
				System.out.println("no hubo parametros");
			}
       		rd.forward(request, response);
		}
	}
}