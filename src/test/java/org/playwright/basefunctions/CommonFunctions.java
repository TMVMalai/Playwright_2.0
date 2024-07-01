package org.playwright.basefunctions;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.playwright.framework.utils.UtilityFunctions;
import org.playwright.framework.helpers.HelperFunctions;
import org.testng.annotations.AfterSuite;



public class CommonFunctions extends UtilityFunctions {
	private static final Logger logger = Logger.getLogger(CommonFunctions.class.getName());
	public void login(String url,String pathName) {
		page = initialization(pathName);
		if(!Files.exists(Paths.get(pathName))){
			page.navigate(url);
			HelperFunctions.login(page);
			HelperFunctions.saveLoginState(page);
			logger.log(Level.INFO, "Performed login and saved new state");
		}
		page.navigate(url);
		logger.log(Level.INFO, "Loaded existing login state");
	}

	@AfterSuite
	public void driverClose() {
		page.close();
		playwright.close();
	}
}
