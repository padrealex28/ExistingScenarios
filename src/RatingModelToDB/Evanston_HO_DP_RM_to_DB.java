package RatingModelToDB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Evanston_HO_DP_RM_to_DB {
	static Cell resultValue;
	static Logger logger = Logger.getLogger(NationWide_RM_to_DB.class);
	static RM_To_DB_Queries getQuery = new RM_To_DB_Queries();
	
	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure("log4j2.properties");
		FileInputStream fis = new FileInputStream("D:\\Rate Matchers\\Markel(Evanston) Rating Model - HO&DP _ 4.27.22 Ticket.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		List<String> sheetNames = new ArrayList<String>();
		List<String> rateFactors = new ArrayList<String>();
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber() + ": STARTED FOR Evanston HO/DP RATER ");
		/* Adding all the Sheets inside a Array */
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheetNames.add(workbook.getSheetName(i));
		}
		/*
		 * Removing the elements in the array which do not have the sheet name Evanston_Rater
		 */
		for (int i = 0; i < sheetNames.size(); i++) {
			sheetNames.removeIf(n -> (!n.contains("Evanston_Rater ")));
		}
		Sheet rowsSheet = workbook.getSheet("Output_rater");
		for (int i = 0; i < sheetNames.size(); i++) {
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber() + ": Going to sheet "
					+ sheetNames.get(i));
			Sheet ResultSheet = workbook.getSheet(sheetNames.get(i));
			int rowSheetCount = rowsSheet.getPhysicalNumberOfRows();
			for (int j = 1; j <= 1; j++) {
				Row row = rowsSheet.getRow(j);
				Cell cell_1 = row.getCell(1);
				Cell cell_2 = row.getCell(2);
				String c1 = cell_1.toString();
				String c2 = cell_2.toString();

				resultValue = ResultSheet.getRow(Integer.parseInt(c1)).getCell(Integer.parseInt(c2));
				if (resultValue.getCellType() == CellType.FORMULA) {
					logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber() + ": Cell address "
							+ Integer.parseInt(c1) + "," + Integer.parseInt(c2));
					System.out.println("Type of Cell: " + resultValue.getCellType());
					switch (resultValue.getCachedFormulaResultType()) {
					case BOOLEAN:
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber() + " "
								+ resultValue.getBooleanCellValue());
						rateFactors.add(String.valueOf(resultValue.getBooleanCellValue()));

						break;
					case NUMERIC:
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber() + " "
								+ resultValue.getNumericCellValue());
						double value = resultValue.getNumericCellValue();
						String FinalValue = String.valueOf(value);
						rateFactors.add(FinalValue);
						break;
					case STRING:
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber() + " "
								+ resultValue.getRichStringCellValue());
						rateFactors.add(resultValue.getRichStringCellValue().getString());
						break;
					case ERROR:
						rateFactors.add("NA");
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber() + ": NA");
						break;
					default:
						rateFactors.add("Blank/Formula or NA");
						logger.info(
								Thread.currentThread().getStackTrace()[1].getLineNumber() + ": Blank/Formula or NA");
						break;
					}
				} else {
					logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber() + ": Cell address "
							+ Integer.parseInt(c1) + "," + Integer.parseInt(c2));
					System.out.println("Type of Cell: " + resultValue.getCellType());
					if (resultValue.getCellType() == CellType.NUMERIC) {
						double value = resultValue.getNumericCellValue();
						String FinalValue = String.valueOf(value);
						rateFactors.add(FinalValue);
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber() + "" + FinalValue);
					} else {
						rateFactors.add(resultValue.getStringCellValue());
						logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber() + ""
								+ resultValue.getStringCellValue());

					}
				}
			}
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber() + ": Final Array HO: " + rateFactors);
			// RM_To_DB_Queries getQuery = new RM_To_DB_Queries();
			String query = getQuery.Evanston_HO_DP_RM_to_DB(i, rateFactors);
			logger.info(
					Thread.currentThread().getStackTrace()[1].getLineNumber() + ": Executed Query: " + query);
			rateFactors.removeAll(rateFactors);
	}
		System.out.println();
		System.out.println("COPIED TO DB,COMPARISON IN PROGRESS..!!!");
		Comparison_Queries cq = new Comparison_Queries();
		cq.ComparePremium_Evanston_HO_DP();	
		System.out.println();
		System.out.println("COMPARISON COMPLETED");
	}

}
