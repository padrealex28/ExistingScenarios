package RatingModelToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RM_To_DB_Queries {

	public String NationWide_HO_RM_to_DB(int s_no, List<String> rateFactors) throws Exception {
		String a = "INSERT INTO Allrisks_Regression.NW_ChartRate_HO_Rating_model VALUES(" + "'" + (s_no + 1) + "',";
		String query = QueryBuilder(s_no, rateFactors, a);
		return query;
	}

	public String NationWide_DP_RM_to_DB(int s_no, List<String> rateFactors) throws Exception {
		String a = "INSERT INTO Allrisks_Regression.NW_ChartRate_DP_Rating_model VALUES(" + "'" + (s_no + 1) + "',";
		String query = QueryBuilder(s_no, rateFactors, a);
		return query;

	}

	public String OneLondon_HO_DP_RM_to_DB(int s_no, List<String> rateFactors) throws Exception {
		String a = "INSERT INTO Allrisks_Regression.OneLondon_HO_DP_RatingModel VALUES(" + "'" + (s_no + 1) + "',";
		String query = QueryBuilder(s_no, rateFactors, a);
		return query;

	}

	public String OneLondon_BR_Vac_RM_to_DB(int s_no, List<String> rateFactors) throws Exception {
		String a = "INSERT INTO Allrisks_Regression.OneLondon_BR_Vac_RatingModel VALUES(" + "'" + (s_no + 1) + "',";
		String query = QueryBuilder(s_no, rateFactors, a);
		return query;

	}
	
	public String Evanston_HO_DP_RM_to_DB(int s_no, List<String> rateFactors) throws Exception {
		String a = "INSERT INTO Allrisks_Regression.Evanston_HO_DP_RatingModel VALUES(" + "'" + (s_no + 1) + "',";
		String query = QueryBuilder(s_no, rateFactors, a);
		return query;

	}
	public String Evanston_BR_Vac_RM_to_DB(int s_no, List<String> rateFactors) throws Exception {
		
		String a = "INSERT INTO Allrisks_Regression.Evanston_BR_Vac_RatingModel VALUES(" + "'" + (s_no + 1) + "',";
		String query = QueryBuilder(s_no, rateFactors, a);
		return query;
	}
	
	public String LondonCondo_RM_to_DB(int s_no, List<String> rateFactors) throws Exception {
		
		String a = "INSERT INTO Allrisks_Regression.LondonCondo_RatingModel VALUES(" + "'" + (s_no + 1) + "',";
		String query = QueryBuilder(s_no, rateFactors, a);
		return query;
	}
	
	
	public String QueryBuilder(int s_no, List<String> rateFactors,String a) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://10.200.17.25:3700/?user=root", "root","redhat");
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		StringBuilder result = new StringBuilder();
		String query = null;
		
		for (int i = 0; i < rateFactors.size(); i++) 
		{

			if (rateFactors.size() == (i + 1)) 
			{
				result = stringBuilder.append("'" + rateFactors.get(i) + "'");
			} else 
			{
				result = stringBuilder.append("'" + rateFactors.get(i) + "'" + ",");
			}
			query = a + result + ");";
			
		}
		
		statement.executeUpdate(query);
		return query;
		
	}
}
