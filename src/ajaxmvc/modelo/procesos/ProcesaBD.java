package ajaxmvc.modelo.procesos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Random;

import ajaxmvc.modelo.beans.*;

/**
 * Se encarga de proporcionar el servicio de acceso a BD
 * @author Eduardo A. Ponce
 * @version Ajax-MVC2
 */
public class ProcesaBD {
	
	/**
	 * Datasource
	 */
	DataSource ds = null;
	
	/**
	 * Constructor que recibe el datasource
	 * @param ds El datasource para el acceso a la base de datos
	 */
	public ProcesaBD(DataSource ds) {
		this.ds = ds;
	}
	/**
	 * Comprueba si existe un usuario y se valida su clave. En caso de login y clave correctos, 
	 * se devuelve true, en caso contrario, false.
	 * @param login Login del usuario a localizar
	 * @param clave Clave del usuario a localizar
	 * @return
	 */
	public boolean existeUsr(String login, String clave) {
		boolean existe = false;
		Connection conBD = null;
		Statement st = null;
		ResultSet rs = null;
		String sentenciaSQL = null;
	
		sentenciaSQL = "select login, clave from usuarios where login like '"+login+"'";
		try {
        	conBD = ds.getConnection();
        	st = conBD.createStatement();
        	rs = st.executeQuery(sentenciaSQL);

        	if (rs.next())
        	{
        		if (rs.getString("clave").equals(clave))
        			existe = true;
        	}
        }
        catch(Exception excepcion) {
        	excepcion.printStackTrace();
        }
		finally {
			if (conBD!=null)
				try {
					conBD.close();
				}
				catch(SQLException sqle)
				{
					System.out.println(sqle);
				}
		}		
		return existe;
	}
	
	
	
	//comprueba si ha acertado fotograma
	
	public boolean existeFtg(String archivo, String titPelicula) {
		boolean existe = false;
		Connection conBD = null;
		Statement st = null;
		ResultSet rs = null;
		String sentenciaSQL = null;
		sentenciaSQL = "select archivo, titPelicula from fotogramas where archivo like '"+archivo+"'";
		try {
        	conBD = ds.getConnection();
        	st = conBD.createStatement();
        	rs = st.executeQuery(sentenciaSQL);

        	if (rs.next())
        	{
        		if (rs.getString("titPelicula").equals(titPelicula))
        			existe = true;
        	}
        }
        catch(Exception excepcion) {
        	excepcion.printStackTrace();
        }
		finally {
			if (conBD!=null)
				try {
					conBD.close();
				}
				catch(SQLException sqle)
				{
					System.out.println(sqle);
				}
		}		
		return existe;
	}
	
// CONSULTAR TOTAL ACIERTOS
	
	public ArrayList<BeanAcertado> aciertosTotal(String user) {
		ArrayList<BeanAcertado> array = new ArrayList<BeanAcertado>();
		BeanAcertado acertado = null;
		
		Connection conBD = null;
		Statement st = null;
		ResultSet rs = null;
		String sentenciaSQL = null;
		
		sentenciaSQL = "SELECT titAcertado FROM acertados where user like '"+user+"'";
		try {
        	conBD = ds.getConnection();
        	st = conBD.createStatement();
        	rs = st.executeQuery(sentenciaSQL);

        	while (rs.next())
        	{
        		acertado = new BeanAcertado();
        		acertado.setTitPelicula(rs.getString("titAcertado"));
        		System.out.println(acertado.getTitPelicula());

        		array.add(acertado);
        	}
        }
        catch(Exception excepcion) {
        	excepcion.printStackTrace();
        }
		finally {
			if (conBD!=null)
				try {
					conBD.close();
				}
				catch(SQLException sqle)
				{
					System.out.println(sqle);
				}
		}		
		return array;
	}
	

	
	
	//devuelve 3 fotogramas que no ha acertado P<--------
	

	public Partida darPartidap(String usu) {
		
		ArrayList<BeanFotograma> arrayFotograma = new ArrayList<BeanFotograma>();
		BeanFotograma beanFotograma = null;
		Partida partida=null;
		Connection conBD = null;
		Statement st = null;
		ResultSet rs = null;
		String sentenciaSQL = null;
		
		
		sentenciaSQL = "SELECT titPelicula, archivo FROM fotogramas WHERE titPelicula NOT IN ( SELECT titAcertado "
				+ " FROM acertados where user like '"+usu+"') ORDER BY RAND() LIMIT 3";
		try {
        	conBD = ds.getConnection();
        	st = conBD.createStatement();
        	rs = st.executeQuery(sentenciaSQL);
        	partida= new Partida();
        	
        	while (rs.next())
        	{
        		
        		beanFotograma= new BeanFotograma();
        		beanFotograma.setTitPelicula(rs.getString("titPelicula"));
        		beanFotograma.setArchivo(rs.getString("archivo"));
        		arrayFotograma.add(beanFotograma);

        	}
        	
        	Random r = new Random();
        	int valorDado = r.nextInt(3)+1;
        	partida.setFoto(arrayFotograma.get(valorDado).getArchivo());
        	partida.setNumPartida();
			partida.setTitUno(arrayFotograma.get(0).getTitPelicula());
			partida.setTitDos(arrayFotograma.get(1).getTitPelicula());
			partida.setTitTres(arrayFotograma.get(2).getTitPelicula());
			
        	
        }
        catch(Exception excepcion) {
        	excepcion.printStackTrace();
        }
		finally {
			if (conBD!=null)
				try {
					conBD.close();
				}
				catch(SQLException sqle)
				{
					System.out.println(sqle);
				}
		}		
		return partida;
	}


	//devuelve 3 fotogramas que no ha acertado
	

	public ArrayList<BeanFotograma> darPartida(String usu) {
		
		ArrayList<BeanFotograma> arrayFotograma = new ArrayList<BeanFotograma>();
		BeanFotograma beanFotograma = null;
		
		Connection conBD = null;
		Statement st = null;
		ResultSet rs = null;
		String sentenciaSQL = null;
		
		
		sentenciaSQL = "SELECT titPelicula, archivo FROM fotogramas WHERE titPelicula NOT IN ( SELECT titAcertado "
				+ " FROM acertados where user like '"+usu+"') ORDER BY RAND() LIMIT 3";
		try {
        	conBD = ds.getConnection();
        	st = conBD.createStatement();
        	rs = st.executeQuery(sentenciaSQL);
        	
        	
        	while (rs.next())
        	{
        		
        		beanFotograma= new BeanFotograma();
        		beanFotograma.setTitPelicula(rs.getString("titPelicula"));
        		beanFotograma.setArchivo(rs.getString("archivo"));
        		arrayFotograma.add(beanFotograma);


        	}
        	
        	
        	
        }
        catch(Exception excepcion) {
        	excepcion.printStackTrace();
        }
		finally {
			if (conBD!=null)
				try {
					conBD.close();
				}
				catch(SQLException sqle)
				{
					System.out.println(sqle);
				}
		}		
		return arrayFotograma;
	}

		
	
	/**
	 * Devuelve un ArrayList de objetos Ranking que encapsula el listado
	 * de ranking en orden descendente
	 * @return La lista de objetos Ranking en orden descendente
	 */
	public ArrayList<Ranking> getRanking() {
		ArrayList<Ranking> ranking = new ArrayList<Ranking>();
		Ranking rnkUsuario = null;
		
		Connection conBD = null;
		Statement st = null;
		ResultSet rs = null;
		String sentenciaSQL = null;
		
		sentenciaSQL = "select login, puntos from ranking order by puntos desc";
		try {
        	conBD = ds.getConnection();
        	st = conBD.createStatement();
        	rs = st.executeQuery(sentenciaSQL);

        	while (rs.next())
        	{
        		rnkUsuario = new Ranking();
        		rnkUsuario.setLogin(rs.getString("login"));
        		rnkUsuario.setPuntos(rs.getString("puntos"));

        		ranking.add(rnkUsuario);
        	}
        }
        catch(Exception excepcion) {
        	excepcion.printStackTrace();
        }
		finally {
			if (conBD!=null)
				try {
					conBD.close();
				}
				catch(SQLException sqle)
				{
					System.out.println(sqle);
				}
		}		
		return ranking;
	}
	
	
	/**
	 * Devuelve un ArrayList de objetos Ranking que encapsula el listado
	 * de ranking en orden descendente
	 * @return La lista de objetos Ranking en orden descendente
	 */
	public ArrayList<Ranking> getRankingDiez() {
		ArrayList<Ranking> ranking = new ArrayList<Ranking>();
		Ranking rnkUsuario = null;
		
		Connection conBD = null;
		Statement st = null;
		ResultSet rs = null;
		String sentenciaSQL = null;
		
		sentenciaSQL = "select login, puntos from ranking order by puntos desc limit 10";
		try {
        	conBD = ds.getConnection();
        	st = conBD.createStatement();
        	rs = st.executeQuery(sentenciaSQL);

        	while (rs.next())
        	{
        		rnkUsuario = new Ranking();
        		rnkUsuario.setLogin(rs.getString("login"));
        		rnkUsuario.setPuntos(rs.getString("puntos"));

        		ranking.add(rnkUsuario);
        	}
        }
        catch(Exception excepcion) {
        	excepcion.printStackTrace();
        }
		finally {
			if (conBD!=null)
				try {
					conBD.close();
				}
				catch(SQLException sqle)
				{
					System.out.println(sqle);
				}
		}		
		return ranking;
	}
	
	//MODIFICAR RANKING
	
	public boolean modificaRanking(String login, String puntos){
		boolean registrado = false;
		Connection conBD = null;
		Statement st = null;
		ResultSet rs = null;
		String sentenciaSQL = null;
		sentenciaSQL = "select puntos from ranking where login like '"+login+"'";
		try {
        	conBD = ds.getConnection();
        	st = conBD.createStatement();
        	rs = st.executeQuery(sentenciaSQL);
        	System.out.println("antes de la modificacion"+login+" "+puntos+" "+rs.next());
        	int pun=Integer.parseInt(rs.getString("puntos"));
        	if (!rs.next())
        	{
        		System.out.println("entra en el if");
        		pun += Integer.parseInt(puntos);
        		sentenciaSQL = "UPDATE ranking SET puntos = '"+pun+"' WHERE login = '"+login+"'";
        		System.out.println("pasa por la modificacion");
        		st.executeUpdate(sentenciaSQL);
        		registrado=true;
        	}
        }
        catch(Exception excepcion) {
        	excepcion.printStackTrace();
        }
		finally {
			if (conBD!=null)
				try {
					conBD.close();
				}
				catch(SQLException sqle)
				{
					System.out.println(sqle);
				}
		}	
		return registrado;
	}

	
	
	
	
	//REGISTRA EL FOTOGRAMA ACERTADO
	
	public boolean registrarFoto(String titPelicula, String user){
		boolean registrado = false;
		Connection conBD = null;
		Statement st = null;
		ResultSet rs = null;
		String sentenciaSQL = null;
		sentenciaSQL = "select login from usuarios where login like '"+user+"'";
		try {
        	conBD = ds.getConnection();
        	st = conBD.createStatement();
        	rs = st.executeQuery(sentenciaSQL);
        	System.out.println(rs.next());
        	if (!rs.next())
        	{
        		sentenciaSQL = "insert into acertados(titAcertado,user) values('"+titPelicula+"','"+user+"')";
        		st.executeUpdate(sentenciaSQL);
        		registrado=true;
        	}
        }
        catch(Exception excepcion) {
        	excepcion.printStackTrace();
        }
		finally {
			if (conBD!=null)
				try {
					conBD.close();
				}
				catch(SQLException sqle)
				{
					System.out.println(sqle);
				}
		}	
		return registrado;
	}
	
	
	
	
	/**
	 * Realiza el proceso de registro de un usuario. Si el proceso se completa correctamente
	 * se devolverá true, en caso contrario, false.
	 * @param user Objeto que encapsula login y clave del usuario a registrar.
	 * @return true si se ha podido registrar al usuario, false en caso contrario.
	 */
	public boolean registrar(String login, String clave){
		boolean registrado = false;
		Connection conBD = null;
		Statement st = null;
		ResultSet rs = null;
		String sentenciaSQL = null;
	
		sentenciaSQL = "select login, clave from usuarios where login like '"+login+"'";
		try {
        	conBD = ds.getConnection();
        	st = conBD.createStatement();
        	rs = st.executeQuery(sentenciaSQL);

        	if (!rs.next())
        	{
        		sentenciaSQL = "insert into usuarios(login,clave) values('"+login+"','"+clave+"')";
        		st.executeUpdate(sentenciaSQL);
        		sentenciaSQL = "insert into ranking(login,puntos) values('"+login+"','0')";
        		st.executeUpdate(sentenciaSQL);
        		registrado=true;
        	}
        }
        catch(Exception excepcion) {
        	excepcion.printStackTrace();
        }
		finally {
			if (conBD!=null)
				try {
					conBD.close();
				}
				catch(SQLException sqle)
				{
					System.out.println(sqle);
				}
		}	
		return registrado;
	}
}
