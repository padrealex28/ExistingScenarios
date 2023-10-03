package RatingModelToDB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class NationWide_RM_to_DB {
	static Cell resultValue;
    static Logger logger = Logger.getLogger(NationWide_RM_to_DB.class);    
    static RM_To_DB_Queries getQuery = new RM_To_DB_Queries();
	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure("log4j2.properties");
		FileInputStream fis = new FileInputStream("D:\\Rate Matchers\\Nationwide Rater HO_with ABCD.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		List<String> sheetNames = new ArrayList<String>();
		List<String> rateFactors = new ArrayList<String>();				
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": STARTED FOR NATIONWIDE HO RATER ");		
		
	   /* Adding all the Sheets inside a Array */
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheetNames.add(workbook.getSheetName(i));
		}
		/* Removing the elements in the array which do not have the sheet name NW_Rater */
		for (int i = 0; i < sheetNames.size(); i++) {
			sheetNames.removeIf(n -> (!n.contains("NW_Rater ")));
		}
		Sheet rowsSheet = workbook.getSheet("Output_rater");
		for (int i = 0; i < sheetNames.size(); i++) {
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Going to sheet " + sheetNames.get(i));
			Sheet ResultSheet = workbook.getSheet(sheetNames.get(i));
			int rowSheetCount = rowsSheet.getPhysicalNumberOfRows();
			for (int j = 35; j <= 56; j++) {
				Row row = rowsSheet.getRow(j);
				Cell cell_1 = row.getCell(1);
				Cell cell_2 = row.getCell(2);
				String c1 = cell_1.toString();
				String c2 = cell_2.toString();

				resultValue = ResultSheet.getRow(Integer.parseInt(c1)).getCell(Integer.parseInt(c2));
				if (resultValue.getCellType() == CellType.FORMULA) {					
					logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Cell address " + Integer.parseInt(c1) + "," + Integer.parseInt(c2));
					System.out.println("Type of Cell: "+resultValue.getCellType());
					switch (resultValue.getCachedFormulaResultType()) {
					case BOOLEAN:						
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+" "+resultValue.getBooleanCellValue());
						rateFactors.add(String.valueOf(resultValue.getBooleanCellValue()));

						break;
					case NUMERIC:
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+" "+resultValue.getNumericCellValue());
						double value = resultValue.getNumericCellValue();
						String FinalValue = String.valueOf(value);
						rateFactors.add(FinalValue);
						break;
					case STRING:
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+" "+resultValue.getRichStringCellValue());
						rateFactors.add(resultValue.getRichStringCellValue().getString());
						break;
					case ERROR:
						rateFactors.add("NA");
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": NA");
						break;	
					default:
						rateFactors.add("Blank/Formula or NA");
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Blank/Formula or NA");
						break;
					}
				} else {
					logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Cell address " + Integer.parseInt(c1) + "," + Integer.parseInt(c2));
					System.out.println("Type of Cell: "+resultValue.getCellType());
					if (resultValue.getCellType() == CellType.NUMERIC) {
						double value = resultValue.getNumericCellValue();
						String FinalValue = String.valueOf(value);
						rateFactors.add(FinalValue);
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+""+FinalValue);
					} else {
						rateFactors.add(resultValue.getStringCellValue());
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+""+resultValue.getStringCellValue());

					}
				}
			}
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Final Array HO: " + rateFactors);
			//RM_To_DB_Queries getQuery = new RM_To_DB_Queries();
			String query = getQuery.NationWide_HO_RM_to_DB(i,rateFactors);	  
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Executed Query For HO: "+query);	  
			rateFactors.removeAll(rateFactors);
			  		
		}
		fis.close();
		workbook.close();
		System.out.println();
		System.out.println("NATIONWIDE HO COMPLETED..!!!");
		/* CODE FOR NATIONWIDE DP RATER */
		System.out.println();
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": STARTED FOR NATIONWIDE DP RATER ");
		FileInputStream fis2 = new FileInputStream("D:\\Rate Matchers\\NationWide Rater DP_current production with 147464 (1) (1).xlsx");
		XSSFWorkbook workbook2 = new XSSFWorkbook(fis2);
		List<String> sheetNames2 = new ArrayList<String>();
		List<String> rateFactors2 = new ArrayList<String>();
		for (int i = 0; i < workbook2.getNumberOfSheets(); i++) {
			sheetNames2.add(workbook2.getSheetName(i));
		}
		for (int i = 0; i < sheetNames2.size(); i++) {
			sheetNames2.removeIf(n -> (!n.contains("NW_Rater ")));
		}
		Sheet rowsSheet2 = workbook2.getSheet("Output_rater");
		
		for (int i = 0; i < sheetNames2.size(); i++) {
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Going to sheet " + sheetNames2.get(i));
			Sheet ResultSheet2 = workbook2.getSheet(sheetNames2.get(i));
			int rowSheetCount2 = rowsSheet2.getPhysicalNumberOfRows();
			for (int j = 2; j <= 3; j++) {
				Row row = rowsSheet2.getRow(j);
				Cell cell_1 = row.getCell(1);
				Cell cell_2 = row.getCell(2);
				String c1 = cell_1.toString();
				String c2 = cell_2.toString();
        
				resultValue = ResultSheet2.getRow(Integer.parseInt(c1)).getCell(Integer.parseInt(c2));
				if (resultValue.getCellType() == CellType.FORMULA) {
				    logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Cell address " + Integer.parseInt(c1) + "," + Integer.parseInt(c2));
					System.out.println("Type of Cell: "+resultValue.getCellType());
				    switch (resultValue.getCachedFormulaResultType()) {
					case BOOLEAN:
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+" "+resultValue.getBooleanCellValue());
						rateFactors2.add(String.valueOf(resultValue.getBooleanCellValue()));
						break;
					case NUMERIC:
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+" "+resultValue.getNumericCellValue());
						double value = resultValue.getNumericCellValue();
						String FinalValue = String.valueOf(value);
						rateFactors2.add(FinalValue);
						break;
					case STRING:
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+" "+resultValue.getRichStringCellValue());
						rateFactors2.add(resultValue.getRichStringCellValue().getString());
						break;
					case ERROR:
						rateFactors2.add("NA");
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": NA");
						break;
					default:
						rateFactors2.add("Blank/Formula or NA");
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Blank/Formula or NA");
						break;
					}
				} else {
					logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Cell address " + Integer.parseInt(c1) + "," + Integer.parseInt(c2));
					System.out.println("Type of Cell: "+resultValue.getCellType());
					if (resultValue.getCellType() == CellType.NUMERIC) {
						double value = resultValue.getNumericCellValue();
						String FinalValue = String.valueOf(value);
						rateFactors2.add(FinalValue);
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+" "+FinalValue);
					} else {
						rateFactors2.add(resultValue.getStringCellValue());
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+" "+resultValue.getStringCellValue());
                        
					}
				}
			}
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Final Array DP: " + rateFactors2);
			
			/* Adding (i+71) in the below query since DP test data starts from 72 */
			String query = getQuery.NationWide_DP_RM_to_DB((i+71),rateFactors2);	  
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Executed Query For DP: "+query);	  
			rateFactors2.removeAll(rateFactors2);
		}
		
		System.out.println();
		System.out.println("NATIONWIDE DP COMPLETED..!!!");
		Comparison_Queries cq = new Comparison_Queries();
		cq.ComparePremium_NW();	
		System.out.println();
		System.out.println("Comparison Completed");
	}	

}
