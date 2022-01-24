package application;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dbase.DB;
import dbase.DbException;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement pst = null;
		
		
		try {
			conn = DB.getConnection();
			pst = conn.prepareStatement(
						"INSERT INTO seller "
						+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
						+ "VALUES "
						+ "(?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, "Nilton Vumbi");
			pst.setString(2, "nilton2gmail.com");
			pst.setDate(3, new java.sql.Date(sdf.parse("15/02/1998").getTime() )  );
			pst.setDouble(4, 2000);	
			pst.setInt(5, 2);
			
			int x = pst.executeUpdate();
			if(x > 0) {
				ResultSet rs = pst.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Id = " + id);
				}
			}
			else {
				System.out.println("No rows Affected! ");
			}
					
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(ParseException ex) {
			ex.printStackTrace();
		}
		
		
		
	}

}
