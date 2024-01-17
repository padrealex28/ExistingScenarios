package testCases;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class readFromExcel {
                                                    //BR_VAC ISSUE IN 30 (3031), 32(3033) Loc page
	@DataProvider(name = "Datafromexcel", indices = {}) 
	public static Object[][] readDatafromexcel() throws IOException {
		FileInputStream fileIn = new FileInputStream("C:\\Users\\padrealex_j\\Downloads\\Trial_run 1 4.xlsx");
		Workbook wrkBk = new XSSFWorkbook(fileIn);
		Sheet sheet = wrkBk.getSheetAt(0);
		fileIn.close();
		int rowCount = sheet.getLastRowNum();
		int coloumnCount = sheet.getRow(0).getLastCellNum();
		Object[][] objData = new Object[rowCount][1];
		Map<Object, Object> mapData;
		for (int i = 0; i < rowCount; i++) {
			mapData = new HashMap<>();
			for (int j = 0; j < coloumnCount; j++) {	
				DataFormatter dft = new DataFormatter();
				Cell keyCell = sheet.getRow(0).getCell(j);
				String tempKey = dft.formatCellValue(keyCell);
				Cell valueCell = sheet.getRow(i + 1).getCell(j);
				String tempValue = dft.formatCellValue(valueCell);
				mapData.put(tempKey, tempValue);
			}
			objData[i][0] = mapData;
		}

		return objData;
	}

}
