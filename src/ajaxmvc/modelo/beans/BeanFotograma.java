package ajaxmvc.modelo.beans;

public class BeanFotograma {

	private String archivo;
	private String titPelicula;	
	private String anyoEstreno;
	private String director;
	private String genero;
	private String error;
	
	public BeanFotograma() {

	}
	
	public BeanFotograma(String titPelicula) {
		

		this.titPelicula = titPelicula;
	}
	
	
	public BeanFotograma(String archivo, String titPelicula) {
		
		this.archivo = archivo;
		this.titPelicula = titPelicula;
	}
	
	
	public BeanFotograma(String archivo, String titPelicula, String anyoEstreno, String director, String genero) {
		super();
		this.archivo = archivo;
		this.titPelicula = titPelicula;
		this.anyoEstreno = anyoEstreno;
		this.director = director;
		this.genero = genero;
	}

	public BeanFotograma(String archivo, String titPelicula, String anyoEstreno, String director, String genero, String error) {
		super();
		this.archivo = archivo;
		this.titPelicula = titPelicula;
		this.anyoEstreno = anyoEstreno;
		this.director = director;
		this.genero = genero;
		this.error=error;
	}
	
	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getTitPelicula() {
		return titPelicula;
	}

	public void setTitPelicula(String titPelicula) {
		this.titPelicula = titPelicula;
	}

	public String getAnyoEstreno() {
		return anyoEstreno;
	}

	public void setAnyoEstreno(String anyoEstreno) {
		this.anyoEstreno = anyoEstreno;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
		
}
