package tarea3;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ServletContenedor extends HttpServlet
{
	//define vector
	private Vector vector;

	private DAOEjercicio control;

	public void init(ServletConfig config) throws ServletException
	{
    	super.init(config);
		//inicializar la dimension del vector y su taza de crecimiento

		this.vector = new Vector(10,1);
		this.control = new DAOEjercicio();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		response.sendRedirect("/tarea3/index.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
											throws ServletException, IOException
	{
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

	public Boolean agregarUsuario(Usuario user)
	{
		/**
		*Metodo agregarUsuario recibe un parametro usr(objeto) de la clase Usuario
		*Corresponde al paquete ejercici2
		*Se encargara de agregar un objeto Usuario al vector
		***/
		this.vector.addElement(user);
		return true;

	}

	public Usuario consultarUsuario(Usuario user)
	{
		/**
		*Metodo consultarUsuario recibe un parametro Usuario(usuario)
		*para user su metodo getID y compararlo con el obj(objeto usuario) que
		*esta en el vector
		***/

		for(int i=0; i<this.vector.size(); i++){
			Usuario obj = (Usuario) this.vector.elementAt(i);
			String idUsuario = obj.getId();
           	if(idUsuario.equals(user.getId())){
   				return obj;
            }	
        }
        return null;
	}

	public Boolean eliminarUsuario(Usuario user)
	{
		/**
		*Metodo eliminarUsuario recibe un paremtro Usuario(usuario)
		*para compararlo al iterar el vector y eliminar el usuario correspondiente
		***/

		for(int i=0; i<this.vector.size(); i++){
			Usuario obj = (Usuario)this.vector.elementAt(i); 
			String idObj = obj.getId();
          	if(idObj.equals(user.getId())){
           		vector.removeElement(obj);
           		return true;
           	}		
        }
        return false;
	}

}