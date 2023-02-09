package resources;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="ApiDemoTest_InputData1")
	public Object[][] getDataForEditField()
	{
		Object[][] obj = new Object[][]
				{
			{"testdata1"}, {"testdata2"}
			
			};
			
			return obj;
	}
	
	@DataProvider(name="Ecommerce_InputData1")
	public Object[][] getDataForNameField()
	{
		Object[][] obj = new Object[][]
				{
			{"Sam"}
			
			};
			
			return obj;
		
	}

}
