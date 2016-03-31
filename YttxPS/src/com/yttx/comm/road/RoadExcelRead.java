package com.yttx.comm.road;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yttx.comm.DateUtil;


/**
 * Excel导入
 * @author huangtao
 *
 */
public class RoadExcelRead {

	/**
	 * @param filepath //文件路径
	 * @param filename //文件名
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 * @throws NotOLE2FileException 
	 */
	public static List<Object> readExcel(String filepath, String filename, int startrow, int startcol, int sheetnum) throws Exception {
		List<Object> varList = new ArrayList<Object>();

		try {
			File target = new File(filepath, filename);
			InputStream fi = new FileInputStream(target);
			Workbook wb = null;
			boolean isE2007 = false; // 判断是否是excel2007格式
			if (filename.endsWith("xlsx"))
				isE2007 = true;
			if (isE2007)
				wb = new XSSFWorkbook(fi);
			else
				wb = new HSSFWorkbook(fi);
			Sheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
			DecimalFormat df = new DecimalFormat("0");
			int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号

			for (int i = startrow; i < rowNum; i++) {					//行循环开始
				
				Map<String, Object> map = new HashMap<String, Object>();
				Row row = sheet.getRow(i); 							//行
				int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置

				for (int j = startcol; j < cellNum; j++) {				//列循环开始
					
					Cell cell = row.getCell(j);
					String cellValue = null;
					if (null != cell) {
						switch (cell.getCellType()) { 					// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case 0:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								cellValue = DateUtil.getDateString(cell.getDateCellValue(), "yyyy/MM/dd");
								break;
							}
							cellValue = df.format(cell.getNumericCellValue()) + "";
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = df.format(cell.getNumericCellValue()) + "";
							// cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						default:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								cellValue = DateUtil.getDateString(cell.getDateCellValue(), "yyyy/MM/dd");
								break;
							}
						}
					} else {
						cellValue = "";
					}
					
					map.put("var"+j, cellValue);
					
				}
				varList.add(map);
			}

		}catch (Exception e) {
			throw e;
		}
		
		return varList;
	}
}
