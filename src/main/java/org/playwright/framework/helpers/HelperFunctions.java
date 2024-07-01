package org.playwright.framework.helpers;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.playwright.framework.utils.UtilityFunctions;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelperFunctions extends UtilityFunctions {
    public static final Logger logger = Logger.getLogger(HelperFunctions.class.getName());

    public static void login(Page page) {
        Locator loginButton = page.locator("//div[text()='Login']");
        loginButton.click();
        Locator emailRadioButton = page.locator("(//div[text()='Email']//parent::div/../div[contains(@class,'css-1dbjc4n r-zso239') or (@class='css-1dbjc4n')])[1]");
        emailRadioButton.click();
        Locator Username = page.locator("//input[@data-testid='user-mobileno-input-box']");
        Username.fill(prop.getProperty("emailId"));
        Locator Password = page.locator("//input[@data-testid='password-input-box-cta']");
        Password.fill(prop.getProperty("Password"));
        Locator clickLogin = page.locator("//div[@data-testid='login-cta']");
        clickLogin.click();
        logger.log(Level.INFO, "Logged in successfully");
    }

    public static void saveLoginState(Page page) {
        try {
            page.context().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("state.json")));
            logger.log(Level.INFO, "Login state saved successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to save login state", e);
        }
    }

    public static void loadLoginState(BrowserContext context) {
        try {
            context.addCookies(context.cookies("state.json"));
            logger.log(Level.INFO, "Login state loaded successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to load login state", e);
        }
    }
}