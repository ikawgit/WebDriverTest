package com.selenium.init;

import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;  
  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.DateUtil;  
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
      
  
    public List<Map<String, String>> rowTitleData(String sheetname) {
    	    	
    	for (int n = 0; n < wb.getNumberOfSheets(); n++)
    	{
    		if (!sheetname.equals(wb.getSheetName(n)))
    		{
    			continue;
    		}
    		else
    		{
    			sheet = wb.getSheet(sheetname);
    			break;
    		}
 //   		System.out.println("输入的Sheet不存在");
    	}

    	if (wb == null) {
    		System.out.println("Workbook对象为空");
    		return null;
    	}
    	int totalrow = sheet.getPhysicalNumberOfRows(); 	
    	rowtitle = sheet.getRow(0);
    	int totalcol = rowtitle.getPhysicalNumberOfCells();
    	for (int i = 1; i <= totalrow; i++)
    	{
    		Row row = sheet.getRow(i);
    		Map<String, String> rowmap = new HashMap<String, String>();
    		for (int j = 0; j <= totalcol; j++)
    		{
    			Cell cell = row.getCell(j);
    			rowmap.put(getCellFormatValue(rowtitle.getCell(j)), getCellFormatValue(cell));
    		}
    		listmap.add(i, rowmap);
    	}	
    	return listmap;
    }
    
        
    /** 
     * 读取Excel数据内容 
     *  
     * @param InputStream 
     * @return Map 包含单元格数据内容的Map对象 
     * @author zengwendong 
     */  
/*    public Map<Integer, Map<Integer,Object>> readExcelContent() throws Exception{  
        if(wb==null){  
            throw new Exception("Workbook对象为空！");  
        }  
        Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();  
          
        sheet = wb.getSheetAt(0);  
        // 得到总行数  
        int rowNum = sheet.getLastRowNum();  
        row = sheet.getRow(0);  
        int colNum = row.getPhysicalNumberOfCells();  
        // 正文内容应该从第二行开始,第一行为表头的标题  
        for (int i = 1; i <= rowNum; i++) {  
            row = sheet.getRow(i);  
            int j = 0;  
            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();  
            while (j < colNum) {  
                Object obj = getCellFormatValue(row.getCell(j));  
                cellValue.put(j, obj);  
                j++;  
            }  
            content.put(i, cellValue);  
        }  
        return content;  
    }  */
  
    /** 
     *  
     * 根据Cell类型设置数据 
     *  
     * @param cell 
     * @return 
     * @author zengwendong 
     */  
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
  
    public static void main(String[] args) {  
      
            String filepath = "D:\\Auto\\bwt\\Pay\\data.xlsx";  
            ReadExcelUtil excelReader = new ReadExcelUtil(filepath);  
            List<Map<String, String>> testdata = excelReader.rowTitleData("商户信息");
            System.out.println("获得Excel表格的内容:");  
            for (int i = 1; i <= testdata.size(); i++) {  
            	Map<String, String> testdatamap = testdata.get(i);
            	System.out.println(testdatamap);
            }  
    }  
}  