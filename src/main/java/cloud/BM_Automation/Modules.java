package cloud.BM_Automation;

import static org.testng.Assert.assertEquals;

import cloud.pages.DemoPage;
import cloud.pages.OneWayTripPage;
import com.microsoft.playwright.Page;
import org.playwright.framework.utils.UtilityFunctions;

import java.util.HashMap;
import java.util.Map;

public class Modules extends UtilityFunctions {
    DemoPage demoPage;
    OneWayTripPage oneWayPage;
    Map<String, Object> data;

    public Modules(Map<String, Object> data, Page page) {
        this.demoPage = new DemoPage(page);
        this.oneWayPage = new OneWayTripPage(page);
        this.data = data;
    }

    public void HomePageValidation() {
        demoPage.clickCheckIn();
        assertEquals(demoPage.getTextCheckIn(), data.get("checkInPageText").toString());
        demoPage.clickFlightStatus();
        assertEquals(demoPage.getTextFlightStatus(), data.get("FlightPageText").toString());
        demoPage.clickDepartureDropdown();
        demoPage.clickDropdownOption("Today");
        demoPage.SelectFromAndDestination("From", data.get("fromPlace").toString());
        demoPage.SelectFromAndDestination("To", data.get("ToPlace").toString());
        demoPage.clicksearchFlights();
        assertEquals(demoPage.getTextFlightRoute(), data.get("FlightRoute").toString());
        navigateToPreviousPage();
        demoPage.clickmanageBooking();
        assertEquals(demoPage.getTextManageBooking(), data.get("ManageBookingPageText"));
    }

    public void searchOneWayFlight() {
        HashMap<String, Object> searchData = (HashMap<String, Object>) data;
        String[] fromandDestinationValues = searchData.get("SearchPlaces").toString().split(",");
        String[] checkBoxTexts = searchData.get("CheckBoxTexts").toString().split(",");
        oneWayPage.SelectFromAndDestination("From", fromandDestinationValues[0]);
        oneWayPage.SelectFromAndDestination("To", fromandDestinationValues[1]);
        String month = oneWayPage.getFutureMonth(1);
        int year = oneWayPage.getCurrentYearName();
        int day = oneWayPage.getFutureDate(5);
        oneWayPage.selectDateSelection("Departure Date", month, year, day);
        oneWayPage.ClickRadioButton("Students");
        oneWayPage.clickSearchFlight();
        oneWayPage.clickCheckBox();
        oneWayPage.clickContinue();
        oneWayPage.clickContinueButton();
        oneWayPage.clickCheckBoxBasedOnField(checkBoxTexts[0]);
        oneWayPage.clickCheckBoxBasedOnField(checkBoxTexts[1]);
        oneWayPage.sendIDNumber(data.get("StudentID").toString());
        oneWayPage.clickTravellerContinue();
        oneWayPage.clickaddButton();
        oneWayPage.clickRandomSeat(oneWayPage.getTextOfAvailableSeatNumbers());
        oneWayPage.clickPrivateSeatCheckbox();
    }

}
