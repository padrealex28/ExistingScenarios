package testCases;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDF_Checker {
	Logger logger = Logger.getLogger(PDF_Checker.class);

	public void searchText(String pdfFilePath,Map map) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://10.200.17.25:3700/?user=root", "root","redhat");
		Statement statement = connection.createStatement();


		try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {

			String[] UMR_Numbers = map.get("Expected UMR No").toString().split(",");
			PDFTextStripper pdfTextStripper = new PDFTextStripper();
			String pdfText = pdfTextStripper.getText(document);
			
			 for (int pageNum = 1; pageNum <= document.getNumberOfPages(); pageNum++) {
		            pdfTextStripper.setStartPage(pageNum);
		            pdfTextStripper.setEndPage(pageNum);
		            String pageText = pdfTextStripper.getText(document);
		            		            
			for(String UMR_No : UMR_Numbers) {
				if(pageText.contains(UMR_No)) {
					logger.info("UMR_No is Present");
				}
				else {
					logger.info("UMR_No is NOT Present");
				}
			}

			 }
			
			
			if (pdfText.contains(map.get("Expected UMR No").toString())) {
				logger.info("Insert into Allrisks_Regression.PDF_Verification values ("+
						map.get("Test Data No").toString()+",\""+map.get("Carrier").toString()+"\",\""+map.get("Quote Number").toString()+"\",\"Pass\")");
				statement.executeUpdate("Insert into Allrisks_Regression.PDF_Verification values ("+
				map.get("Test Data No").toString()+",\""+map.get("Carrier").toString()+"\",\""+map.get("Quote Number").toString()+"\",\"Pass\")");
			} else {
				logger.info("Insert into Allrisks_Regression.PDF_Verification values ("+
						map.get("Test Data No").toString()+",\""+map.get("Carrier").toString()+"\",\""+map.get("Quote Number").toString()+"\",\"Fail\")");
				statement.executeUpdate
				("Insert into Allrisks_Regression.PDF_Verification values ("+
						map.get("Test Data No").toString()+",\""+map.get("Carrier").toString()+"\",\""+map.get("Quote Number").toString()+"\",\"Fail\")");
			}
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

}
