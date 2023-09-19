package RatingModelToDB;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Comparison_Queries {
	static Logger logger = Logger.getLogger(Comparison_Queries.class);

	public void ComparePremium_NW() throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://10.200.17.25:3700/?user=root", "root",
				"redhat");
		Statement statement = connection.createStatement();
		FileWriter fw = new FileWriter("C:\\Users\\padrealex_j\\Desktop\\NW_db_Result.csv");
		PropertyConfigurator.configure("log4j2.properties");

		/*
		 * Loop running 4 times,for matched HO, Unmatched HO, matched DP, Unmatched DP
		 */
		for (int times = 0; times < 4; times++) {
			String Operator = "=";
			String matched_OR_unmatched = "Matched Premiums ";
			String Product = "HO";
			if (times == 1) {
				Operator = "<>";
				matched_OR_unmatched = "UnMatched Premiums ";
			} else if (times == 2) {
				Product = "DP";
			} else if (times == 3) {
				Operator = "<>";
				matched_OR_unmatched = "UnMatched Premiums ";
				Product = "DP";
			}

			try {
				logger.info(
						"SELECT Allrisks_Regression.NW_ChartRate_UI.S_No,Allrisks_Regression.NW_ChartRate_UI.Total_Premium_UI,Allrisks_Regression.NW_ChartRate_"
								+ Product + "_Rating_model.Total_Premium_" + Product
								+ ",NW_ChartRate_UI.QuoteNumber  FROM "
								+ "Allrisks_Regression.NW_ChartRate_UI INNER join Allrisks_Regression.NW_ChartRate_"
								+ Product
								+ "_Rating_model ON Allrisks_Regression.NW_ChartRate_UI.S_No=Allrisks_Regression.NW_ChartRate_"
								+ Product + "_Rating_model.S_No "
								+ "WHERE Allrisks_Regression.NW_ChartRate_UI.Total_Premium_UI" + Operator
								+ "CAST(Allrisks_Regression.NW_ChartRate_" + Product + "_Rating_model.Total_Premium_"
								+ Product + " as Decimal(11,5)) order by S_No");

				ResultSet rs_HO_matched = statement.executeQuery(
						"SELECT Allrisks_Regression.NW_ChartRate_UI.S_No,Allrisks_Regression.NW_ChartRate_UI.Total_Premium_UI,Allrisks_Regression.NW_ChartRate_"
								+ Product + "_Rating_model.Total_Premium_" + Product
								+ ",NW_ChartRate_UI.QuoteNumber  FROM "
								+ "Allrisks_Regression.NW_ChartRate_UI INNER join Allrisks_Regression.NW_ChartRate_"
								+ Product
								+ "_Rating_model ON Allrisks_Regression.NW_ChartRate_UI.S_No=Allrisks_Regression.NW_ChartRate_"
								+ Product + "_Rating_model.S_No "
								+ "WHERE Allrisks_Regression.NW_ChartRate_UI.Total_Premium_UI" + Operator
								+ "CAST(Allrisks_Regression.NW_ChartRate_" + Product + "_Rating_model.Total_Premium_"
								+ Product + " as Decimal(11,5)) order by S_No");

				int cols = rs_HO_matched.getMetaData().getColumnCount();

				fw.append(matched_OR_unmatched + " " + Product);
				fw.append('\n');

				for (int i = 1; i <= cols; i++) {
					fw.append(rs_HO_matched.getMetaData().getColumnLabel(i));
					if (i < cols)
						fw.append(',');
					else
						fw.append('\n');
				}

				while (rs_HO_matched.next()) {

					for (int i = 1; i <= cols; i++) {
						fw.append(rs_HO_matched.getString(i));
						if (i < cols)
							fw.append(',');
					}
					fw.append('\n');

				}
				fw.append('\n');
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		fw.flush();
		fw.close();
		connection.close();
	}

	public void ComparePremium_OneLondon_HO_DP() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://10.200.17.25:3700/?user=root", "root",
				"redhat");
		Statement statement = connection.createStatement();
		FileWriter fw = new FileWriter("C:\\Users\\padrealex_j\\Desktop\\OneLondon_HO_DP_db_Result.csv");
		PropertyConfigurator.configure("log4j2.properties");

		for (int times = 0; times < 2; times++) {
			String Operator = "=";
			String matched_OR_unmatched = "Matched Premiums ";
			if (times == 1) {
				Operator = "<>";
				matched_OR_unmatched = "UnMatched Premiums ";
			}

			try {
				logger.info(
						"SELECT Allrisks_Regression.OneLondon_UW_UI.S_No,Allrisks_Regression.OneLondon_UW_UI.Total_Premium_UI,Allrisks_Regression.OneLondon_HO_DP_RatingModel.Total_Premium,OneLondon_UW_UI.QuoteNumber "
								+ "FROM Allrisks_Regression.OneLondon_UW_UI INNER join Allrisks_Regression.OneLondon_HO_DP_RatingModel ON Allrisks_Regression.OneLondon_UW_UI.S_No=Allrisks_Regression.OneLondon_HO_DP_RatingModel.S_No "
								+ "WHERE Allrisks_Regression.OneLondon_UW_UI.Total_Premium_UI" + Operator
								+ "CAST(Allrisks_Regression.OneLondon_HO_DP_RatingModel.Total_Premium as Decimal(11,5)) order by S_No;");

				ResultSet rs_HO_matched = statement.executeQuery(
						"SELECT Allrisks_Regression.OneLondon_UW_UI.S_No,Allrisks_Regression.OneLondon_UW_UI.Total_Premium_UI,Allrisks_Regression.OneLondon_HO_DP_RatingModel.Total_Premium,OneLondon_UW_UI.QuoteNumber "
								+ "FROM Allrisks_Regression.OneLondon_UW_UI INNER join Allrisks_Regression.OneLondon_HO_DP_RatingModel ON Allrisks_Regression.OneLondon_UW_UI.S_No=Allrisks_Regression.OneLondon_HO_DP_RatingModel.S_No "
								+ "WHERE Allrisks_Regression.OneLondon_UW_UI.Total_Premium_UI" + Operator
								+ "CAST(Allrisks_Regression.OneLondon_HO_DP_RatingModel.Total_Premium as Decimal(11,5)) order by S_No;");

				int cols = rs_HO_matched.getMetaData().getColumnCount();

				fw.append(matched_OR_unmatched);
				fw.append('\n');

				for (int i = 1; i <= cols; i++) {
					fw.append(rs_HO_matched.getMetaData().getColumnLabel(i));
					if (i < cols)
						fw.append(',');
					else
						fw.append('\n');
				}

				while (rs_HO_matched.next()) {

					for (int i = 1; i <= cols; i++) {
						fw.append(rs_HO_matched.getString(i));
						if (i < cols)
							fw.append(',');
					}
					fw.append('\n');

				}
				fw.append('\n');
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		fw.flush();
		fw.close();
		connection.close();

	}

	public void ComparePremium_OneLondon_BR_Vac() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://10.200.17.25:3700/?user=root", "root",
				"redhat");
		Statement statement = connection.createStatement();
		FileWriter fw = new FileWriter("C:\\Users\\padrealex_j\\Desktop\\OneLondon_BR_Vac_db_Result.csv");
		PropertyConfigurator.configure("log4j2.properties");

		for (int times = 0; times < 2; times++) {
			String Operator = "=";
			String matched_OR_unmatched = "Matched Premiums ";
			if (times == 1) {
				Operator = "<>";
				matched_OR_unmatched = "UnMatched Premiums ";
			}

			try {
				logger.info(
						"SELECT Allrisks_Regression.OneLondon_UW_UI.S_No,Allrisks_Regression.OneLondon_UW_UI.Total_Premium_UI,Allrisks_Regression.OneLondon_BR_Vac_RatingModel.Total_Premium,OneLondon_UW_UI.QuoteNumber "
								+ "FROM Allrisks_Regression.OneLondon_UW_UI INNER join Allrisks_Regression.OneLondon_BR_Vac_RatingModel ON Allrisks_Regression.OneLondon_UW_UI.S_No=Allrisks_Regression.OneLondon_BR_Vac_RatingModel.S_No "
								+ "WHERE Allrisks_Regression.OneLondon_UW_UI.Total_Premium_UI" + Operator
								+ "CAST(Allrisks_Regression.OneLondon_BR_Vac_RatingModel.Total_Premium as Decimal(11,5)) order by S_No;");

				ResultSet rs_HO_matched = statement.executeQuery(
						"SELECT Allrisks_Regression.OneLondon_UW_UI.S_No,Allrisks_Regression.OneLondon_UW_UI.Total_Premium_UI,Allrisks_Regression.OneLondon_BR_Vac_RatingModel.Total_Premium,OneLondon_UW_UI.QuoteNumber "
								+ "FROM Allrisks_Regression.OneLondon_UW_UI INNER join Allrisks_Regression.OneLondon_BR_Vac_RatingModel ON Allrisks_Regression.OneLondon_UW_UI.S_No=Allrisks_Regression.OneLondon_BR_Vac_RatingModel.S_No "
								+ "WHERE Allrisks_Regression.OneLondon_UW_UI.Total_Premium_UI" + Operator
								+ "CAST(Allrisks_Regression.OneLondon_BR_Vac_RatingModel.Total_Premium as Decimal(11,5)) order by S_No;");

				int cols = rs_HO_matched.getMetaData().getColumnCount();

				fw.append(matched_OR_unmatched);
				fw.append('\n');

				for (int i = 1; i <= cols; i++) {
					fw.append(rs_HO_matched.getMetaData().getColumnLabel(i));
					if (i < cols)
						fw.append(',');
					else
						fw.append('\n');
				}

				while (rs_HO_matched.next()) {

					for (int i = 1; i <= cols; i++) {
						fw.append(rs_HO_matched.getString(i));
						if (i < cols)
							fw.append(',');
					}
					fw.append('\n');

				}
				fw.append('\n');
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		fw.flush();
		fw.close();
		connection.close();

	}
	public void ComparePremium_Evanston_HO_DP() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://10.200.17.25:3700/?user=root", "root",
				"redhat");
		Statement statement = connection.createStatement();
		FileWriter fw = new FileWriter("C:\\Users\\padrealex_j\\Desktop\\Evanston_HO_DP_db_Result.csv");
		PropertyConfigurator.configure("log4j2.properties");

		for (int times = 0; times < 2; times++) {
			String Operator = "=";
			String matched_OR_unmatched = "Matched Premiums ";
			if (times == 1) {
				Operator = "<>";
				matched_OR_unmatched = "UnMatched Premiums ";
			}

			try {
				logger.info(
						"SELECT Allrisks_Regression.Evanston_UW_UI.S_No,Allrisks_Regression.Evanston_UW_UI.Total_Premium_UI,Allrisks_Regression.Evanston_HO_DP_RatingModel.Total_Premium,Evanston_UW_UI.QuoteNumber "
								+ "FROM Allrisks_Regression.Evanston_UW_UI INNER join Allrisks_Regression.Evanston_HO_DP_RatingModel ON Allrisks_Regression.Evanston_UW_UI.S_No=Allrisks_Regression.Evanston_HO_DP_RatingModel.S_No "
								+ "WHERE Allrisks_Regression.Evanston_UW_UI.Total_Premium_UI" + Operator
								+ "CAST(Allrisks_Regression.Evanston_HO_DP_RatingModel.Total_Premium as Decimal(11,5)) order by S_No;");

				ResultSet rs_HO_matched = statement.executeQuery("SELECT Allrisks_Regression.Evanston_UW_UI.S_No,Allrisks_Regression.Evanston_UW_UI.Total_Premium_UI,Allrisks_Regression.Evanston_HO_DP_RatingModel.Total_Premium,Evanston_UW_UI.QuoteNumber "
						+ "FROM Allrisks_Regression.Evanston_UW_UI INNER join Allrisks_Regression.Evanston_HO_DP_RatingModel ON Allrisks_Regression.Evanston_UW_UI.S_No=Allrisks_Regression.Evanston_HO_DP_RatingModel.S_No "
						+ "WHERE Allrisks_Regression.Evanston_UW_UI.Total_Premium_UI" + Operator
						+ "CAST(Allrisks_Regression.Evanston_HO_DP_RatingModel.Total_Premium as Decimal(11,5)) order by S_No;");

				int cols = rs_HO_matched.getMetaData().getColumnCount();

				fw.append(matched_OR_unmatched);
				fw.append('\n');

				for (int i = 1; i <= cols; i++) {
					fw.append(rs_HO_matched.getMetaData().getColumnLabel(i));
					if (i < cols)
						fw.append(',');
					else
						fw.append('\n');
				}

				while (rs_HO_matched.next()) {

					for (int i = 1; i <= cols; i++) {
						fw.append(rs_HO_matched.getString(i));
						if (i < cols)
							fw.append(',');
					}
					fw.append('\n');

				}
				fw.append('\n');
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		fw.flush();
		fw.close();
		connection.close();

	}
	public void ComparePremium_Evanston_BR_Vac() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://10.200.17.25:3700/?user=root", "root",
				"redhat");
		Statement statement = connection.createStatement();
		FileWriter fw = new FileWriter("C:\\Users\\padrealex_j\\Desktop\\Evanston_BR_Vac_db_Result.csv");
		PropertyConfigurator.configure("log4j2.properties");
		for (int times = 0; times < 2; times++) {
			String Operator = "=";
			String matched_OR_unmatched = "Matched Premiums ";
			if (times == 1) {
				Operator = "<>";
				matched_OR_unmatched = "UnMatched Premiums ";
			}

			try {
				logger.info("SELECT Allrisks_Regression.Evanston_UW_UI.S_No,Allrisks_Regression.Evanston_UW_UI.Total_Premium_UI,Allrisks_Regression.Evanston_BR_Vac_RatingModel.Total_Premium,Evanston_UW_UI.QuoteNumber "
								+ "FROM Allrisks_Regression.Evanston_UW_UI INNER join Allrisks_Regression.Evanston_BR_Vac_RatingModel ON Allrisks_Regression.Evanston_UW_UI.S_No=Allrisks_Regression.Evanston_BR_Vac_RatingModel.S_No "
								+ "WHERE Allrisks_Regression.Evanston_UW_UI.Total_Premium_UI" + Operator
								+ "CAST(Allrisks_Regression.Evanston_HO_DP_RatingModel.Total_Premium as Decimal(11,5)) order by S_No;");

				ResultSet rs_HO_matched = statement.executeQuery("SELECT Allrisks_Regression.Evanston_UW_UI.S_No,Allrisks_Regression.Evanston_UW_UI.Total_Premium_UI,Allrisks_Regression.Evanston_BR_Vac_RatingModel.Total_Premium,Evanston_UW_UI.QuoteNumber "
						+ "FROM Allrisks_Regression.Evanston_UW_UI INNER join Allrisks_Regression.Evanston_BR_Vac_RatingModel ON Allrisks_Regression.Evanston_UW_UI.S_No=Allrisks_Regression.Evanston_BR_Vac_RatingModel.S_No "
						+ "WHERE Allrisks_Regression.Evanston_UW_UI.Total_Premium_UI" + Operator
						+ "CAST(Allrisks_Regression.Evanston_HO_DP_RatingModel.Total_Premium as Decimal(11,5)) order by S_No;");

				int cols = rs_HO_matched.getMetaData().getColumnCount();

				fw.append(matched_OR_unmatched);
				fw.append('\n');

				for (int i = 1; i <= cols; i++) {
					fw.append(rs_HO_matched.getMetaData().getColumnLabel(i));
					if (i < cols)
						fw.append(',');
					else
						fw.append('\n');
				}

				while (rs_HO_matched.next()) {

					for (int i = 1; i <= cols; i++) {
						fw.append(rs_HO_matched.getString(i));
						if (i < cols)
							fw.append(',');
					}
					fw.append('\n');

				}
				fw.append('\n');
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		fw.flush();
		fw.close();
		connection.close();
	}
	
	public void ComparePremium_LondonCondo () throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://10.200.17.25:3700/?user=root", "root",
				"redhat");
		Statement statement = connection.createStatement();
		FileWriter fw = new FileWriter("C:\\Users\\padrealex_j\\Desktop\\Evanston_BR_Vac_db_Result.csv");
		PropertyConfigurator.configure("log4j2.properties");
		for (int times = 0; times < 2; times++) {
			String Operator = "=";
			String matched_OR_unmatched = "Matched Premiums ";
			if (times == 1) {
				Operator = "<>";
				matched_OR_unmatched = "UnMatched Premiums ";
			}

			try {
				logger.info("SELECT Allrisks_Regression.LondonCondo_UW_UI.S_No,Allrisks_Regression.LondonCondo_UW_UI.Total_Premium_UI,LondonCondo_RatingModel.Total_Premium,LondonCondo_UW_UI.QuoteNumber "
								+ "FROM Allrisks_Regression.LondonCondo_UW_UI INNER join Allrisks_Regression.LondonCondo_RatingModel ON Allrisks_Regression.LondonCondo_UW_UI.S_No=Allrisks_Regression.LondonCondo_RatingModel.S_No "
								+ "WHERE Allrisks_Regression.LondonCondo_UW_UI.Total_Premium_UI" + Operator
								+ "CAST(Allrisks_Regression.LondonCondo_RatingModel.Total_Premium as Decimal(11,5)) order by S_No;");

				ResultSet rs_HO_matched = statement.executeQuery("SELECT Allrisks_Regression.LondonCondo_UW_UI.S_No,Allrisks_Regression.LondonCondo_UW_UI.Total_Premium_UI,LondonCondo_RatingModel.Total_Premium,LondonCondo_UW_UI.QuoteNumber "
						+ "FROM Allrisks_Regression.LondonCondo_UW_UI INNER join Allrisks_Regression.LondonCondo_RatingModel ON Allrisks_Regression.LondonCondo_UW_UI.S_No=Allrisks_Regression.LondonCondo_RatingModel.S_No "
						+ "WHERE Allrisks_Regression.LondonCondo_UW_UI.Total_Premium_UI" + Operator
						+ "CAST(Allrisks_Regression.LondonCondo_RatingModel.Total_Premium as Decimal(11,5)) order by S_No;");

				int cols = rs_HO_matched.getMetaData().getColumnCount();

				fw.append(matched_OR_unmatched);
				fw.append('\n');

				for (int i = 1; i <= cols; i++) {
					fw.append(rs_HO_matched.getMetaData().getColumnLabel(i));
					if (i < cols)
						fw.append(',');
					else
						fw.append('\n');
				}

				while (rs_HO_matched.next()) {

					for (int i = 1; i <= cols; i++) {
						fw.append(rs_HO_matched.getString(i));
						if (i < cols)
							fw.append(',');
					}
					fw.append('\n');

				}
				fw.append('\n');
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		fw.flush();
		fw.close();
		connection.close();
	}
	}
	
	

