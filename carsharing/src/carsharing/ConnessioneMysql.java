package carsharing;


import java.sql.*;







public class ConnessioneMysql {
	private Avvio avvio;
	public ConnessioneMysql(Avvio avvio){
		this.avvio=avvio;
	}
	public static void main(String[] args) throws SQLException {
		
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


		
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/auto?user=root&password=");
			//jdbc:mysql://localhost:3306/Contatti?user=root&password=secret
		

		sql = "SELECT * FROM auto;";
		// ________________________________query
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next() == true)
				System.out.println(rs.getString("Id") + "\t" + rs.getString("casacostruttrice") + " " +rs.getString("modello"));
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		} // fine try-catch
		cn.close(); // chiusura connessione
	}// fine main
}

