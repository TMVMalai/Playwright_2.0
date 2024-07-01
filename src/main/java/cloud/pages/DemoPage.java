package cloud.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.playwright.framework.annotations.FindBy;
import org.playwright.framework.utils.UtilityFunctions;

public class DemoPage extends UtilityFunctions {

    public Page page;

    public DemoPage(Page page) {
        this.page = page;
        initElements(page, this);
    }

    @FindBy(xpath = "//div[contains(@class,'css-1dbjc4n r-1niwhzg r-1p0dtai')]//parent::div//following-sibling::div/../div[text()='Search Flights']")
    private Locator searchFlight;

    @FindBy(xpath = "//div[text()='Departure Date']//parent::div[contains(@class,'css-1dbjc4n r-14lw9ot r-11u4nky')]")
    private Locator departureDate;

    @FindBy(xpath = "//div[text()='check-in']")
    private Locator checkinTab;

    @FindBy(xpath = "//div[text()='Web Check-In']")
    private Locator checkinTabText;

    @FindBy(xpath = "//div[text()='flight status']")
    private Locator flightstatusTab;

    @FindBy(xpath = "//div[text()='Flight Status']")
    private Locator flightstatusTabText;

    @FindBy(xpath = "//div[text()='manage booking']")
    private Locator manageBookingTab;

    @FindBy(xpath = "//div[text()='Manage Booking']")
    private Locator manageBookingTabText;

    @FindBy(xpath = "//div[text()='BLR to CCU']")
    private Locator flightStatusText;


    public void clickCheckIn() {
        checkinTab.click();
    }

    public String getTextCheckIn() {
        String text = checkinTabText.textContent();
        return text;
    }

    public void clickFlightStatus(){
        flightstatusTab.click();
    }

    public String getTextFlightStatus() {
        String text = flightstatusTabText.textContent();
        return text;
    }

    public void clickDepartureDropdown() {
        departureDate.click();
    }

    public void clickDropdownOption(String dropdownValue){
        Locator element = page.locator("//div[text()='Departure Date']//parent::div//following-sibling::div//div[text()='"+dropdownValue+"']");
        element.click();
    }

    public void SelectFromAndDestination(String fromOrDestination, String fromOrDestinationPlace) {
        Locator fromOrDestinationElement = page.locator("//div[text()='"+fromOrDestination+"']//parent::div//following-sibling::div//input[@type='text']");
        fromOrDestinationElement.click();
        fromOrDestinationElement.fill(fromOrDestinationPlace);
        if(fromOrDestination.equalsIgnoreCase("To")) {
            fromOrDestinationElement.clear();
            fromOrDestinationElement.fill(fromOrDestinationPlace);
        }
    }

    public void clicksearchFlights() {
        searchFlight.click();
    }

    public String getTextFlightRoute() {
        String text = flightStatusText.textContent();
        return text;
    }

    public void clickmanageBooking() {
        manageBookingTab.click();
    }

    public String getTextManageBooking() {
        String text = manageBookingTabText.textContent();
        return text;
    }

}
