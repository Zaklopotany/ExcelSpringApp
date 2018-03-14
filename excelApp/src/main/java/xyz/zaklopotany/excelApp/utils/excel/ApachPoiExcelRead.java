package xyz.zaklopotany.excelApp.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public int[] getColsRowsOfMarker(String marker, Sheet sheet) {
		int[] colsRowsMarkersMap = new int[2];
		// go through sheet and assign positions to cells
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.iterator();
			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				if (currentCell.getCellTypeEnum() == CellType.STRING) {
					if (currentCell.getStringCellValue().equalsIgnoreCase(marker)) {

						colsRowsMarkersMap[0] = currentCell.getColumnIndex();
						colsRowsMarkersMap[1] = currentCell.getRowIndex();
						return colsRowsMarkersMap;
					}
				}
			}
		}
		return colsRowsMarkersMap;
	}

	public Set<String> bindDataByMarkers(int[] markerMap, Sheet sheet) {
		Set<String> bindedData = new HashSet<>();
		// bind data
		int lastRecord = sheet.getLastRowNum();

		Cell cell = sheet.getRow(markerMap[1]).getCell(markerMap[0]);
		
		for 
		
		
		return bindedData;

		// int verPos =

	}

	public void ReadFileAndBindData(MultipartFile file) {
		try {
			FileInputStream excelFile = new FileInputStream(fileService.getTempFile(file));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			System.out.println("last row: " + datatypeSheet.getLastRowNum());

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
