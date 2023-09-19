package utilClass;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static int rowCount;

	public static void createExcelObject(String excelPath, String sheetName) {
		// TODO Auto-generated constructor stub

		try {

			FileInputStream fis = new FileInputStream(excelPath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getRowCount() {

		rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("Number of Rows : " + rowCount);
	}

	public static String getCellData(int i, int j) throws IOException {

		DataFormatter formatter = new DataFormatter();
		Object value = formatter.formatCellValue(sheet.getRow(i).getCell(j));
		System.out.println(value);
		return null;
	}
}