package org.playwright.dataproviders;

import java.lang.reflect.Method;
import org.playwright.framework.utils.JsonUtils;
import org.playwright.framework.utils.UtilityFunctions;
import org.testng.annotations.DataProvider;

public class DataProviderClass extends UtilityFunctions {
	
	JsonUtils jsonUtils = new JsonUtils();
	
	@DataProvider(name ="getHomePageData")
	public Object[] getHomePageJsonData(Method m) {
		return jsonUtils.readMultiJsonData("HomePage.json", m.getName());
	}

}
