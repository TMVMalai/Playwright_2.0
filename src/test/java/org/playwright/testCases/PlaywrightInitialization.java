package org.playwright.testCases;

import cloud.BM_Automation.Modules;
import org.playwright.dataproviders.DataProviderClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.playwright.basefunctions.CommonFunctions;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.playwright.framework.constants.FrameworkConstants.APP_URL;
import static org.playwright.framework.constants.FrameworkConstants.PATH_NAME;

public class PlaywrightInitialization extends CommonFunctions {
	@BeforeMethod(alwaysRun = true)
	public synchronized void setPreRequisites(Method m) throws InterruptedException {
		new CommonFunctions().login(APP_URL,PATH_NAME);
	}

	@Test(enabled = true, groups = "Regression", dataProviderClass = DataProviderClass.class, dataProvider = "getHomePageData")
	public void HomePageValidationFunctionality(Object data) {
		Map<String, Object> testData = (Map<String, Object>) data;
		Modules m = new Modules(testData, page);
		m.HomePageValidation();
	}

	@Test(enabled = true, groups = "Regression", dataProviderClass = DataProviderClass.class, dataProvider = "getHomePageData")
	public void searchFlightFunctionality(Object data) {
		Map<String, Object> testData = (Map<String, Object>) data;
		Modules m = new Modules(testData, page);
		m.searchOneWayFlight();
	}



}
