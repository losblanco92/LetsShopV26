package utilities;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;

public class CucumberExcelReader {

	Workbook workbook;

	public CucumberExcelReader(String excelPath) {
        try {
            FileInputStream fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public String getCellData(String sheetName, String columnName, int rowNumber) {
		Sheet sheet = workbook.getSheet(sheetName);

		Row headerRow = sheet.getRow(0);
		int colNum = -1;

		for (int i = 0; i < headerRow.getLastCellNum(); i++) {
			if (headerRow.getCell(i) == null)
				continue;

			if (headerRow.getCell(i).getStringCellValue().trim().equalsIgnoreCase(columnName.trim())) {
				colNum = i;
				break;
			}
		}

		if (colNum == -1) {
			throw new IllegalArgumentException("Column NOT found in Excel: " + columnName);
		}

		Row row = sheet.getRow(rowNumber);
		if (row == null)
			return "";

		Cell cell = row.getCell(colNum);
		if (cell == null)
			return "";

		return cell.getStringCellValue();
	}
}
