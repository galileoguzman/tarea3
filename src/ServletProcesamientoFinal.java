package tarea3;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletProcesamientoFinal extends HttpServlet
{
	private PrintWriter html;

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		response.sendRedirect("/tarea2/index.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
											throws ServletException, IOException
	{
		
		response.setContentType("text/html");
		this.html = response.getWriter();

		this.html.println(
			"<!DOCTYPE html>"+
			"<html lang=\"es\">"+
			"<head>"+
			"<meta charset=\"utf-8\">"+
			"<title> Operacion </title>"+
			"<link rel=\"stylesheet\" href=\"css/estilos.css\">"+
			"</HEAD>"+
			"<body>"+
			"<header>"+
			"<h1>Bienvenido al sistema</h1>"+
			"<nav>"+
			"<ul>"+
			"<li><a href=\"index.html\">Inicio</a></li>"+
			"<li><a href=\"alta.html\">Alta de Usuario</a></li>"+
			"<li><a href=\"consulta.html\">Consulta de Usuario</a></li>"+
			"</ul>"+
			"</nav>"+
			"</header><section>");

		String operacion = request.getParameter("operacion");
		String exito = (String) request.getAttribute("exito");
		Vector usuarios = (Vector) request.getAttribute("users");
		Usuario user = (Usuario) request.getAttribute("user");

		if (operacion.equals("consultar"))
		{
			if (user != null)
			{
				this.html.println("<h2>Mostrando datos del Usuario: " + user.getLogin() + "</h2>");
				this.html.println("<p>ID usuario: " + user.getId() + "</p>");
				this.html.println("<p>Login usuario: " + user.getLogin() + "</p>");
				this.html.println("<p>Password usuario: " + user.getPassword() + "</p>");
			}else{
				this.html.println("<h3 class=\"mensaje error\">El usuario no existe</h3>");
			}
		} else if(operacion.equals("eliminar") || operacion.equals("alta")){
			if(exito.equals("exito")){
 			//Operacion exitosa
				this.html.println("<h2 class=\"mensaje exito\">operacion " + operacion + " exitosa</h2>");

			}else{
			//Operacion fallida
				this.html.println("operacion " + operacion + " fallida</h2>");
			}
		}else{
			if (usuarios == null) {
				this.html.println("<h2>Al parecer la BD esta vacia</h2>");
			}else{
				for(int i=0; i<usuarios.size(); i++){
					Usuario obj = (Usuario) usuarios.elementAt(i); 
			       	this.html.println("<p>UsuarioID " + obj.getId() + " Usuario " + obj.getLogin() + " UsuarioPassword " + obj.getPassword() + "</p>");
		     	}
			}

		}
		this.html.println("</section></body> </html>");

	}
}