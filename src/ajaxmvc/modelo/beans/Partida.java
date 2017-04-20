package ajaxmvc.modelo.beans;

public class Partida {

	
	
	private int numPartida;
	private String foto;
	private String titUno;
	private String titDos;
	private String titTres;
	private String error;	
	
	
	
	public Partida() {

	}
	
	public Partida(int numPartida, String foto, String titUno, String titDos, String titTres) {
		
		this.numPartida = numPartida;
		this.foto = foto;
		this.titUno = titUno;
		this.titDos = titDos;
		this.titTres = titTres;
	}

	public Partida(int numPartida, String foto, String titUno, String titDos, String titTres, String error) {
		
		this.numPartida = numPartida;
		this.foto = foto;
		this.titUno = titUno;
		this.titDos = titDos;
		this.titTres = titTres;
		this.error = error;
	}
	
	
	public int getNumPartida() {
		return numPartida;
	}

	public void setNumPartida() {
		this.numPartida ++;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTitUno() {
		return titUno;
	}

	public void setTitUno(String titUno) {
		this.titUno = titUno;
	}

	public String getTitDos() {
		return titDos;
	}

	public void setTitDos(String titDos) {
		this.titDos = titDos;
	}

	public String getTitTres() {
		return titTres;
	}

	public void setTitTres(String titTres) {
		this.titTres = titTres;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}	
	
	
	
}
