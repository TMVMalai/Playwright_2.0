package org.playwright.framework.constants;

public class FrameworkConstants {
    public static final String BASE_PATH = System.getProperty("user.dir") + "/src/test/resources/jsonfiles/";
    public static final String PROP_PATH = System.getProperty("user.dir") + "/src/main/resources/Data.properties";
    public static final String REPORTS_PATH = System.getProperty("user.dir") + "/reports/extentReport.html";
    public static final String SCREENSHOT_PATH_PREFIX = System.getProperty("user.dir") + "/Screenshot";
    public static final String SCREENSHOT_PATH_SUFFIX = ".png";
    public static int TIME_OUT_SECONDS = 10;

    public static String APP_URL = "https://www.spicejet.com/";

    public static final String PATH_NAME = "state.json";
}
