package ajaxmvc.modelo.beans;

import java.io.Serializable;

/**
 * Encapsula los datos correspondientes a la puntuación asociada a un usuario
 * @author  Eduardo A. Ponce
 * @version Ajax-MVC2
 */
public class Ranking implements Serializable{
	/**
	 * @uml.property  name="login"
	 */
	private String login;
	/**
	 * @uml.property  name="puntos"
	 */
	private String puntos;
	/**
	 * @return
	 * @uml.property  name="login"
	 */
	
	private String error;
	
	
	public Ranking() {

	}	
	
	public Ranking(String login, String puntos) {
		super();
		this.login = login;
		this.puntos = puntos;

	}
	
	
	public Ranking(String login, String puntos, String error) {
		super();
		this.login = login;
		this.puntos = puntos;
		this.error = error;
	}
	public String getLogin() {
		return login;
	}
	/**
	 * @param login
	 * @uml.property  name="login"
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return
	 * @uml.property  name="puntos"
	 */
	public String getPuntos() {
		return puntos;
	}
	/**
	 * @param puntos
	 * @uml.property  name="puntos"
	 */
	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
}
