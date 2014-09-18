//Definicion del paquete de la aplicacion
package tarea3;

//Import de librerias para el uso de Vector y HTTPServLet
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ServletContenedor extends HttpServlet
{
	//Definicion del atributo control
	private DAOEjercicio control;

	public void init(ServletConfig config) throws ServletException
	{
    	super.init(config);

    	//Inicializacion del atributo control
		this.control = new DAOEjercicio();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		/**
		*
		* Implementacion de una Peticion (request) por el metodo Get
		* En dado caso de ser consultado el ServletContenedor por dicho metodo
		* hara una redireccion (sendRedirect) a la pagina de inicio
		*
		**/
		response.sendRedirect("/tarea3/index.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
											throws ServletException, IOException
	{
		/**
		*
		* Implementacion de una Peticion (request) por el metodo Post
		* En dado caso de ser consultado el ServletContenedor por dicho metodo
		* verificara la operacion, en base a ella utilizara los metodos del
		* atributo (obj) control para mandar traer las operaciones en la BD
		* el resultado de cualquier metodo de control lo asiganara como nuevo
		* atributo en la peticion (request) para ser utilizado por
		* ServletProcesamientoFinal
		*
		**/
		ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher("/ServletProcesamientoFinal");

		String operacion = request.getParameter("operacion");
		String exito;

		Connection conexion;
		conexion = this.control.conectar();

		if(operacion.equals("alta"))
		{
			

			if(this.control.altaUsuario((Usuario)request.getAttribute("user")) == 1)
			{
				request.setAttribute("exito", exito = "exito");
			}else{
				request.setAttribute("exito", exito = "fallido");
			}
			

		}else if(operacion.equals("eliminar"))
		{
			if(this.control.eliminarUsuario((Usuario)request.getAttribute("user")) == 1)
			{
				request.setAttribute("exito", exito = "exito");

			}else{
				request.setAttribute("exito", exito = "fallido");
			}

		}else if(operacion.equals("consultar"))
		{
			if(this.control.consultarUsuario((Usuario)request.getAttribute("user")) != null){
				request.setAttribute("user", this.control.consultarUsuario((Usuario)request.getAttribute("user")));
			}else{
				request.setAttribute("user", null);
			}
		}else
		{
			System.out.println("Entro al else con los usuarios");
			request.setAttribute("users", this.control.consultarUsuarios());
		}
		this.control.desconectar(conexion);
		rd.forward(request, response);
	}

}