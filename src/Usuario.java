// se define el paquete al cual pertenecera la clase
package tarea3;

public class Usuario
{
	private String id;
	private String login;
	private String password;

	private void Usuario()
	{
		//Constructor
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return this.id;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getLogin()
	{
		return this.login;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return this.password;
	}
	
}