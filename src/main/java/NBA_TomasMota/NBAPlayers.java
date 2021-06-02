package NBA_TomasMota;

public class NBAPlayers {

	// Atributos del jugador

	private String nombre;
	private String posicion;
	private String franquicia;

	/**
	 * Constructor
	 * 
	 * @param team
	 * @param position
	 * @param name
	 */
	public NBAPlayers(String nombre, String posicion, String franquicia) {
		super();
		this.franquicia = franquicia;
		this.posicion = posicion;
		this.nombre = nombre;
	}

	//Metodos
	/**
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * 
	 * @return posicion
	 */
	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
	/**
	 * 
	 * @return Franquicia
	 */
	public String getFranquicia() {
		return franquicia;
	}

	public void setFranquicia(String franquicia) {
		this.franquicia = franquicia;
	}


	
}