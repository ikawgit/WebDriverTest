package com.selenium.init;

import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;  
  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
  
public class ReadExcelUtil {  
    private Workbook wb;  
    private Sheet sheet;  
    private Row rowtitle;
    private List<Map<String, String>> listmap;
    
    public ReadExcelUtil(String filepath) {  
        if(filepath==null){
            return;
        }  
        String ext = filepath.substring(filepath.lastIndexOf("."));  
        try {  
            InputStream is = new FileInputStream(filepath);  
            if(".xls".equals(ext)){  
                wb = new HSSFWorkbook(is); 
            }else if(".xlsx".equals(ext)){  
                wb = new XSSFWorkbook(is);
            }else{  
                wb=null;
            }  
        } catch (FileNotFoundException e) {  
        	System.out.println(e);
        } catch (IOException e) {  
        	System.out.println(e);  
        }  
    }  
      
    public List<Map<String, String>> getRowTitleData(String sheetname) throws Exception{
    	if (wb == null) {
    		readExceptions("Workbook对象为空");
    	}
    	sheet = wb.getSheet(sheetname);
    	rowTitleData();
    	return listmap;   	
    }
        
    public List<Map<String, String>> getRowTitleData(int sheetindex) throws Exception{
    	if (wb == null) {   		
    		readExceptions("Workbook对象为空");
    	}
    	
    	if (sheetindex < wb.getNumberOfSheets()) {
    		sheet = wb.getSheetAt(sheetindex);
        	rowTitleData();
    	}
    	else {
    		readExceptions("Sheet页不存在");
    	}
		return listmap;
    }
    
    public List<Map<String, String>> getRowTitleData() throws Exception{
    	if (wb == null) {
    		readExceptions("Workbook对象为空");
    	}
    	sheet = wb.getSheetAt(0);
        rowTitleData();
        return listmap;
    }
    
    public List<Map<String, String>> getColTitleData(int rows) throws Exception{
    	if (wb == null) {
    		readExceptions("Workbook对象为空");
    	}
    	sheet = wb.getSheetAt(0);
    	colTitleData(rows);
    	return listmap;
    }
    
    public List<Map<String, String>> getColTitleData(String sheetname, int rows) throws Exception{
    	if (wb == null) {
    		readExceptions("Workbook对象为空");
    	}  
    	sheet = wb.getSheet(sheetname);
    	colTitleData(rows);
    	return listmap;
    }
    
    public void colTitleData(int rows) throws Exception{
    	

    	sheet = wb.getSheetAt(0);
    	if (sheet == null) {
    		readExceptions("Sheet页不存在");    		
    	}   	
    	listmap= new ArrayList<Map<String, String>>();
    	int rownow = 0;
    	while (sheet.getRow(rownow)!=null)
    	{
    		Map<String, String> rowmap = new HashMap<String, String>();
    		for (int n = 0; n < rows-1; n++) {
    			Row row = sheet.getRow(rownow);
    			//System.out.println(rownow);
    			//System.out.println(getCellFormatValue(row.getCell(0)));
    			//System.out.println(getCellFormatValue(row.getCell(1)));
/*    			if (row != null) {
    				
    				Cell key = row.getCell(0);
    				Cell value = row.getCell(1);
    			
    				if (key!=null&&value!=null)
    				{
    					rowmap.put(getCellFormatValue(key), getCellFormatValue(value));
    				}
    			}*/
    			rowmap.put(getCellFormatValue(row.getCell(0)), getCellFormatValue(row.getCell(1)));
    			rownow += 1;
    		}
    		//System.out.println(sheet.getRow(rownow).getCell(0));
    		listmap.add(rowmap);
    		rownow += 1;    	
    	}
    	//System.out.println(listmap);
    }
    
    public void rowTitleData() throws Exception {
    	    	    	
    	if (sheet == null) {
    		readExceptions("Sheet页不存在");
    	}
    	
    	int totalrow = sheet.getPhysicalNumberOfRows();
    	//System.out.println(totalrow);
    	rowtitle = sheet.getRow(0);
    	int totalcol = rowtitle.getPhysicalNumberOfCells();
    	//System.out.println(totalcol);
    	
    	listmap= new ArrayList<Map<String, String>>();
    	for (int i = 1; i < totalrow; i++)
    	{
    		Row row = sheet.getRow(i);
    		Map<String, String> rowmap = new HashMap<String, String>();
    		for (int j = 0; j < totalcol; j++)
    		{
    			Cell cell = row.getCell(j);
    			//System.out.println(i);
    			//System.out.println(j);
    			rowmap.put(getCellFormatValue(rowtitle.getCell(j)), getCellFormatValue(cell));
    		}
    		listmap.add(rowmap);
    		//System.out.println(listmap);
    	}	
    }
    
    private String getCellFormatValue(Cell cell) {  
        String cellvalue = "";  
        if (cell != null) {  
            // 判断当前Cell的Type  
            switch (cell.getCellTypeEnum()) {  
            case NUMERIC:// 数字
                cellvalue = cell.getNumericCellValue() + "";
                break;

            case STRING: // 字符串
                cellvalue = cell.getStringCellValue();
                break;

            case BOOLEAN: // Boolean
                cellvalue = cell.getBooleanCellValue() + "";
                break;

            case FORMULA: // 公式
                cellvalue = cell.getCellFormula() + "";
                break;

            case BLANK: // 空值
                cellvalue = "";
                break;

            case ERROR: // 故障
                cellvalue = "非法字符";
                break;

            default:
                cellvalue = "未知类型";
                break;
            }  
        } else {  
           cellvalue = "";  
        }  
        return cellvalue;  
    }  
  
    private void readExceptions(String e) throws Exception {
    	throw new Exception(e);
    }
}  