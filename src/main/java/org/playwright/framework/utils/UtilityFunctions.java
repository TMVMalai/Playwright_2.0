package org.playwright.framework.utils;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.microsoft.playwright.options.WaitForSelectorState;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.playwright.framework.annotations.FindBy;
import org.playwright.framework.constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Level;

import static org.playwright.framework.helpers.HelperFunctions.logger;

public class UtilityFunctions {
    public static Playwright playwright;
    public static Browser browser;
    public static Page page;
    public static BrowserContext context;
    public static Properties prop;

    static {
        prop = new Properties();
        String propath = FrameworkConstants.PROP_PATH;
        try (FileInputStream fis = new FileInputStream(propath)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Page initialization(String PathName) {
        playwright = Playwright.create();
        String browserChoice = prop.getProperty("browser");
        boolean headless;
        if(prop.getProperty("Headless").equals("true")) {
            headless = true;
        }
        else {
            headless = false;
        }
        switch (browserChoice.toLowerCase()) {
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                System.out.println("Chrome Browser Started Successfully");
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                System.out.println("fire Fox Browser Started Successfully");
        }
        if(Files.exists(Paths.get(PathName)) && !isStateExpired(PathName)){
            context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("state.json")));
            page = context.newPage();
        }
        return page;
    }

    private static boolean isStateExpired(String statePath) {
        try {
            long lastModifiedTime = Files.getLastModifiedTime(Paths.get(statePath)).toMillis();
            long currentTime = System.currentTimeMillis();
            long timeDifference = currentTime - lastModifiedTime;
            System.out.println(timeDifference);
            return timeDifference > 24 * 60 * 60 * 1000;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to check state file timestamp", e);
            return true;
        }
    }
    public static void switchToWindowIndex(int index) {
        List<Page> allPages = context.pages();
        Page reqWindow = allPages.get(index);
        if (index < 0 || index >= allPages.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        reqWindow.bringToFront();
    }

    public static void close() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    public static List<Locator> getLocators(Locator element){
        List<Locator> elements = new ArrayList<>();
        int count = element.count();
        for(int i=0;i<count;i++) {
            elements.add(element.nth(i));
        }
        return elements;
    }

    public static void sendText(Locator element, String value) {
        element.fill(value);
    }

    public static void removeElements(List<Locator> elements) {
        for (int i = elements.size(); i >= 0; i--) {
            Locator element = elements.get(i);
            element.click();
        }
    }

    public static void initElements(Page page, Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.getType().isAssignableFrom(Locator.class)) {
                if (field.isAnnotationPresent(FindBy.class)) {
                    FindBy findBy = field.getAnnotation(FindBy.class);
                    String selector = findBy.xpath();

                    if (!selector.isEmpty()) {
                        try {
                            field.setAccessible(true);
                            field.set(obj, page.locator(selector));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void actionKeysEnter(){
        page.keyboard().press("Enter");
    }

    public static void navigateToPreviousPage() {
        page.goBack();
    }

    public void scrollDown(){
        page.evaluate("window.scrollBy(0, window.innerHeight);");
    }

    public void scrollUp(){
        page.evaluate("window.scrollBy(0, -window.innerHeight);");
    }

    public static boolean isElementExists(Locator selector) {
        if(selector.count() > 0){
            return selector.count() > 0;
        } else{
            return false;
        }
    }

    public void waitForLoderClose(Locator element){
        page.waitForSelector(String.valueOf(element), new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
    }
}
