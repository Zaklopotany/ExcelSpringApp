package xyz.zaklopotany.excelApp.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import xyz.zaklopotany.excelApp.service.FileService;

@Component
public class ApachPoiExcelRead {

	// public static String EXCEL_FILE_NAME = "";
	@Autowired
	private FileService fileService;

	public int[][] getColsRowsOfMarkers(String[] markers) {
		
		
		
		return new int[1][1];
	}

	public  void ReadFileAndShowData(MultipartFile file) {
		try {
			File fileOne = fileService.getTempFile(file);
			
			FileInputStream excelFile = new FileInputStream(fileOne);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			System.out.println("last row: " + datatypeSheet.getLastRowNum());;
			Iterator<Row> rowIterator = datatypeSheet.iterator();
			
			while (rowIterator.hasNext()) {
				
				Row currentRow = rowIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						System.out.println("String: column :" + currentCell.getColumnIndex() + " Row: "
								+ currentCell.getRowIndex() + " value " + currentCell.getStringCellValue());
					}
				}
			}
			workbook.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception w) {
			w.printStackTrace();
		}

	}
}
