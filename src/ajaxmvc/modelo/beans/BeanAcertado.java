package ajaxmvc.modelo.beans;

public class BeanAcertado {

	private String titPelicula;
	private String user;
	private String error;
	private String numAcierto;
	
	public BeanAcertado() {

	}
	
	public BeanAcertado(String numAcierto) {

		this.numAcierto=numAcierto;

	}
	
	public BeanAcertado(String user, String numAcierto) {

		this.user = user;
		this.numAcierto=numAcierto;

	}
	
	
	public BeanAcertado(String titPelicula, String user, String error, String numAcierto) {
		super();
		this.titPelicula = titPelicula;
		this.user = user;
		this.error = error;
		this.numAcierto=numAcierto;
	}

	public String getTitPelicula() {
		return titPelicula;
	}

	public void setTitPelicula(String titPelicula) {
		this.titPelicula = titPelicula;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getNumAcierto() {
		return numAcierto;
	}

	public void setNumAcierto(String numAcierto) {
		this.numAcierto = numAcierto;
	}
	
	
	
	
}
