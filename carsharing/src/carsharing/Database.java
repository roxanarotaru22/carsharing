package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
	private Avvio avvio;

	public Database(Avvio avvio) {
		this.avvio = avvio;

	}

	public void Connessione() {

		Connection cn;
		Statement st;
		ResultSet rs;
		String sql;
		// ________________________________connessione
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch

		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/auto?user=root&password=");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// jdbc:mysql://localhost:3306/Contatti?user=root&password=secret

		// sql = "SELECT * FROM auto;";
		// ________________________________query
		/*
		 * try { st = cn.createStatement(); rs = st.executeQuery(sql); while
		 * (rs.next() == true) System.out.println(rs.getString("Id") + "\t" +
		 * rs.getString("casacostruttrice") + " " +rs.getString("modello")); }
		 * catch (SQLException e) { System.out.println("errore:" +
		 * e.getMessage()); } // fine try-catch
		 */

	}// fine main

	public ArrayList<Socio> CaricaSoci() throws SQLException {
		Connection cn;
		Statement st;
		ResultSet rs;
		String sql;
		ArrayList<Socio> tabella = new ArrayList<Socio>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/auto?user=root&password=");

		sql = "SELECT * FROM soci";
		System.out.println(sql);
		// ________________________________query
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next() == true) {
				tabella.add(new Socio(rs.getString("cognome"), rs.getString("nome"), rs.getString("cf"),
						rs.getString("telefono"), rs.getString("indirizzo")));

			}
			cn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tabella;

	}

	public ArrayList<Noleggio> CaricaNoleggio(String cf, String inizio, String fine) throws SQLException {
		Connection cn;
		Statement st;
		ResultSet rs;
		String sql;
		ArrayList<Noleggio> noleggi = new ArrayList<Noleggio>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/auto?user=root&password=");

		sql = "SELECT * FROM noleggi WHERE socio='" + cf + "' AND inizio>='" + inizio + "'" + "AND fine<='" + fine
				+ "'";
		System.out.println(sql);
		// ________________________________query
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next() == true) {
				noleggi.add(new Noleggio(rs.getString("codice_noleggio"), rs.getString("auto"), rs.getString("socio"),
						rs.getString("inizio"), rs.getString("fine"), rs.getInt("auto_restituita")));

			}
			// System.out.println(noleggi.get(0).getInizio() + " - " +
			// noleggi.get(0).getFine());
			cn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return noleggi;
	}

	public void AggiungiNoleggio(String auto, String socio, String inizio, String fine) throws SQLException {
		Connection cn;
		Statement st;
		ResultSet rs;
		String sql;
		// String codice_noleggio;

		// ________________________________connessione
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch

		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/auto?user=root&password=");

			// jdbc:mysql://localhost:3306/Contatti?user=root&password=secret

			sql = "INSERT INTO noleggi (auto,socio,inizio,fine,auto_restituita) VALUES ('" + auto + "' ,'" + socio + "', '" + inizio
					+ "' ,'" + fine + "', auto_restituita = '0');";
			System.out.println(sql);
			// ________________________________query

			st = cn.createStatement();
			st.executeUpdate(sql);

			cn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Auto ancora non restituita");
			e1.printStackTrace();
		}

	}

	public ArrayList<Auto> CaricaAuto(String inizio, String fine) throws SQLException {
		Connection cn;
		Statement st;
		ResultSet rs;
		String sql;
		ArrayList<Auto> tabella = new ArrayList<Auto>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/auto?user=root&password=");

		sql = "SELECT * FROM auto INNER JOIN noleggi ON auto.targa=noleggi.auto WHERE auto.targa NOT IN(SELECT targa FROM auto INNER JOIN noleggi ON auto.targa = noleggi.auto WHERE noleggi.inizio <='"
				+ inizio + "' AND noleggi.fine>= '" + fine + "') GROUP BY auto.targa";
		System.out.println(sql);
		// ________________________________query
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next() == true) {
				tabella.add(new Auto(rs.getString("targa"), rs.getString("marca"), rs.getString("modello"),
						rs.getDouble("costo_giornaliero")));

			}
			cn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tabella;

	}
	
	
	public void Elimina(String auto, String socio) throws SQLException {
		Connection cn;
		Statement st;
		ResultSet rs;
		String sql;
		// String codice_noleggio;

		// ________________________________connessione
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch

		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/auto?user=root&password=");

			// jdbc:mysql://localhost:3306/Contatti?user=root&password=secret

			sql = "DELETE * FROM noleggi WHERE auto = " + auto;
			System.out.println(sql);
			// ________________________________query

			st = cn.createStatement();
			st.executeUpdate(sql);

			cn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Auto eliminata");
			e1.printStackTrace();
		}

	}
	public ArrayList<Socio> CaricaAuto() throws SQLException {
		Connection cn;
		Statement st;
		ResultSet rs;
		String sql;
		ArrayList<Auto> tabella = new ArrayList<Auto>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/auto?user=root&password=");

		sql = "SELECT targa FROM auto";
		System.out.println(sql);
		// ________________________________query
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next() == true) {
				tabella.add(new Socio(rs.getString("targa"), rs.getString("marca"), rs.getString("modello"),
						rs.getString("costo_giornaliero")));

			}
			cn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tabella;

	}

}
