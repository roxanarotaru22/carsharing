package carsharing;

public class Noleggio {
String codice_noleggio;
String auto;
String socio;
String inizio;
String fine;
int auto_restituita;
public String getCodice_noleggio() {
	return codice_noleggio;
}
public void setCodice_noleggio(String codice_noleggio) {
	this.codice_noleggio = codice_noleggio;
}
public String getAuto() {
	return auto;
}
public void setAuto(String auto) {
	this.auto = auto;
}
public String getSocio() {
	return socio;
}
public void setSocio(String socio) {
	this.socio = socio;
}
public String getInizio() {
	return inizio;
}
public void setInizio(String inizio) {
	this.inizio = inizio;
}
public String getFine() {
	return fine;
}
public void setFine(String fine) {
	this.fine = fine;
}
public int getAuto_restituita() {
	return auto_restituita;
}
public void setAuto_restituita(int auto_restituita) {
	this.auto_restituita = auto_restituita;
}
public Noleggio(String codice_noleggio, String auto, String socio, String inizio, String fine, int auto_restituita) {
	super();
	this.codice_noleggio = codice_noleggio;
	this.auto = auto;
	this.socio = socio;
	this.inizio = inizio;
	this.fine = fine;
	this.auto_restituita = auto_restituita;
}
@Override
public String toString() {
	return "Noleggio [codice_noleggio=" + codice_noleggio + ", auto=" + auto + ", socio=" + socio + ", inizio=" + inizio
			+ ", fine=" + fine + ", auto_restituita=" + auto_restituita + "]";
}


}
