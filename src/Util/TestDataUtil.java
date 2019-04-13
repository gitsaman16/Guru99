package Util;

import java.util.ArrayList;

import Base.AutoConstant;
import Base.ExcelDataHandling;

public class TestDataUtil {

	public static ArrayList<Object[]> getTestData(){
		ArrayList<Object[]> testData=new ArrayList<Object[]>();
		ExcelDataHandling xlsData=new ExcelDataHandling(AutoConstant.TEST_DATA_FILE);
		int rowCount=xlsData.getRowCount("Test Data Sheet");
		System.out.println("Row count:"+rowCount);
		
		//Get the data of every row and check login 
		for(int rowNum=2; rowNum<=rowCount; rowNum++){
			String userId=xlsData.getCellData("Test Data Sheet", "Userid", rowNum);
			String password=xlsData.getCellData("Test Data Sheet", "Pwd", rowNum);
			Object[] obj={userId,password};
			testData.add(obj);	
	}
		return testData;
	}
}
