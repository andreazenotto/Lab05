package it.polito.tdp.anagrammi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnagrammaDAO {
	
	public boolean isCorrect(String anagramma) {
		
		final String sql = "SELECT COUNT(*) AS n FROM parola WHERE nome=?";

		int result;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, anagramma);
			
			ResultSet rs = st.executeQuery();

			rs.next();
			
			result = rs.getInt("n");

			conn.close();
			
			if(result==1)
				return true;
			return false;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
