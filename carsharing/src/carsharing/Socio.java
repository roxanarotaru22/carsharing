package carsharing;

public class Socio {
	String cognome;
	String nome;
	String cf;
	String Telefono;
	String indirizzo;
	public Socio(String cognome, String nome, String cf, String telefono, String indirizzo) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.cf = cf;
		Telefono = telefono;
		this.indirizzo = indirizzo;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	@Override
	public String toString() {
		return "Socio [cognome=" + cognome + ", nome=" + nome + ", cf=" + cf + ", Telefono=" + Telefono + ", indirizzo="
				+ indirizzo + "]";
	}
	
}
