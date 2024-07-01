package cloud.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.playwright.framework.annotations.FindBy;
import org.playwright.framework.utils.UtilityFunctions;

public class OneWayTripPage extends UtilityFunctions {

    public Page page;

    public OneWayTripPage(Page page) {
        this.page = page;
        initElements(page, this);
    }
    @FindBy(xpath = "//div[text()='From']")
    private Locator fromicon;

    @FindBy(xpath = "(//*[@id='preferredSeata'])[1]")
    private Locator selectSeat;

    @FindBy(xpath = "//div[@data-testid='home-page-flight-cta']")
    private Locator searchFlight;

    @FindBy(xpath = "//*[@color='#dddddd']")
    private Locator checkbox;

    @FindBy(xpath = "//div[@data-testid='continue-search-page-cta']")
    private Locator ContinueButton;

    @FindBy(xpath = "//div[text()='Student ID Card*']//parent::div//following-sibling::div//input[@autocomplete='new-password']")
    private Locator idField;

    @FindBy(xpath = "//div[@data-testid='traveller-info-continue-cta']")
    private Locator TravellercontinueButton;

    @FindBy(xpath = "//div[@data-testid='bookingFlow-seats-add-cta']")
    private Locator addButton;

    @FindBy(xpath = "//div[(@data-testid='add-ons-continue-footer-button') and not(@id='button')]")
    private Locator footercontinueButton;

    @FindBy(xpath = "//div[@class='css-1dbjc4n']//parent::div//following-sibling::div//div[text()='Done']")
    private Locator DoneButton;

    @FindBy(xpath = "//div[text()='Private Row']//parent::div/../parent::div/../parent::div//*[@stroke='#DDDDDD']")
    private Locator checkboxPrivateSeat;

    @FindBy(xpath = "//div[contains(text(),'I accept')]//parent::div/../div//div//*[@color='#000']")
    private Locator checkboxaccept;

    @FindBy(xpath = "//div[contains(@class,'css-1dbjc4n r-173mn98 r-1loqt21')]//parent::div//following-sibling::div//div[text()='Continue']")
    private Locator checkboxContinue;

    @FindBy(xpath = "//div[text()='Adjacent Seat']//parent::div/../parent::div/../parent::div//*[@stroke='#DDDDDD']")
    private Locator adjacentSeatLocator;

    @FindBy(xpath = "//div[@id='at_addon_close_icon']")
    private Locator closeIcon;

    @FindBy(xpath = "//div[text()='Title*']//parent::div//following-sibling::div//div[@data-testid='title-contact-detail-card']")
    private Locator TitleText;

    @FindBy(xpath = "//div[text()='First and Middle Name*']//parent::div//following-sibling::div//input[@data-testid='traveller-0-first-traveller-info-input-box']")
    private Locator firstName;

    @FindBy(xpath = "//div[text()='Last Name*']//parent::div//following-sibling::div//input[@data-testid='traveller-0-last-traveller-info-input-box']")
    private Locator lastName;

    @FindBy(xpath = "//div[text()='SC Member ID / Mobile Number']//parent::div//following-sibling::div//input[@data-testid='sc-member-mobile-number-input-box']")
    private Locator memberNumber;

    @FindBy(xpath = "//div[text()='Modify']")
    private Locator modifyButton;

    @FindBy(xpath = "//div[@data-testid='common-proceed-to-pay']")
    private Locator proceedtoPayButton;

    public void SelectFromAndDestination(String fromOrDestination, String fromOrDestinationPlace) {

        Locator fromOrDestinationElement = page.locator(
                "//div[text()='" + fromOrDestination + "']//parent::div//following-sibling::div//input[@type='text']");
       fromOrDestinationElement.click();
        fromOrDestinationElement.fill(fromOrDestinationPlace);
        actionKeysEnter();
        if (fromOrDestination.equalsIgnoreCase("To")) {
            fromOrDestinationElement.clear();
            fromOrDestinationElement.fill(fromOrDestinationPlace);
            actionKeysEnter();
        }
    }

    public void selectSeat() {
        selectSeat.click();
    }

    public void ClickRadioButton(String radioButtonName) {
        Locator radioButton = page.locator("(//div[text()='" + radioButtonName
                + "']//parent::div/../div[contains(@class,'css-1dbjc4n r-zso239') or (@class='css-1dbjc4n')])[1]");
        radioButton.click();
    }

    public void clickDateSelection(String departureOrReturn) {
        Locator departureOrReturnDate = page.locator("//div[text()='" + departureOrReturn
                + "']//parent::div[contains(@data-testid,'departure-date-dropdown')]");
        departureOrReturnDate.click();
    }

    public int getCurrentMonth() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        return currentMonth;
    }

    public String getFutureMonth(int numofMonths) {
        LocalDate currentDate = LocalDate.now();
        String futureMonth = currentDate.plusMonths(numofMonths).getMonth().name().toLowerCase();
        futureMonth = futureMonth.substring(0, 1).toUpperCase() + futureMonth.substring(1);
        return futureMonth;
    }

    public String getCurrentMonthName() {
        LocalDate currentDate = LocalDate.now();
        String currentMonthName = currentDate.getMonth().name().toLowerCase();
        currentMonthName = currentMonthName.substring(0, 1).toUpperCase() + currentMonthName.substring(1);
        return currentMonthName;
    }

    public int getCurrentYearName() {
        LocalDate currentDate = LocalDate.now();
        int CurrentYear = currentDate.getYear();
        return CurrentYear;
    }

    public int getFutureDate(int numberOfDays) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(numberOfDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedFutureDate = futureDate.format(formatter);
        LocalDate parsedDate = LocalDate.parse(formattedFutureDate, formatter);
        int dayOnly = parsedDate.getDayOfMonth();
        return dayOnly;
    }

    public void selectDate(String month, int year, int day) {
        Locator DateElement =page.locator("//div[@data-testid='undefined-month-" + month + "-" + year
                + "']//parent::div//following-sibling::div[@data-testid='undefined-calendar-day-" + day + "']");
      DateElement.click();
    }

    public void selectDateSelection(String departureOrReturn, String month, int year, int day) {
        Locator getdateAttribute = page.locator("(//div[text()='"+departureOrReturn+"']//parent::div/../following-sibling::div//div[@data-testid])[1]");
        if (getdateAttribute.getAttribute("data-testid").equals("undefined-calendar-picker")) {
            selectDate(month, year, day);
        } else {
            clickDateSelection(departureOrReturn);
            selectDate(month, year, day);
        }
    }

    public void clickSearchFlight() {
        searchFlight.click();
    }

    public void clickCheckBox() {
        checkbox.click();
    }

    public void clickContinue() {
        Locator continueButton = page.locator(
                "//div[text()='Student Discount Bookings']/ancestor::div//following-sibling::div//div[text()='Continue']");
        continueButton.click();
    }

    public void clickContinueButton() {
        ContinueButton.click();
    }

    public void clickCheckBoxBasedOnField(String text) {
        Locator element = page.locator("//div[contains(text(),'" + text + "')]//parent::*//*[@color='#dddddd']");
       element.click();
    }

    public void sendIDNumber(String id) {
        scrollDown();
        Locator locator = page.locator(
                "//div[text()='Student ID Card*']//parent::div//following-sibling::div//input[@autocomplete='new-password']");
        if (isElementExists(locator)){
            idField.fill(id);
        } else {
            System.out.println("ElementNotExists");
        }
    }

    public void clickTravellerContinue() {
        TravellercontinueButton.click();
    }

    public void clickaddButton() {
        addButton.click();
    }

    public String getTextOfAvailableSeatNumbers() {
        scrollDown();
        List<String> seatNumbers =page.locator(
                "//*[@fill='url(#preferredSeata)']//parent::*//following-sibling::*/../parent::*/parent::*/parent::div//following-sibling::div[(contains(@class,'css-76zvg2 r-homxoj r-poiln3 r-1enofrn r-1wyvozj')) and (text())]").allTextContents();
        if (seatNumbers.isEmpty()) {
            throw new NoSuchElementException("No seat numbers found.");
        }
        Random rand = new Random();
        int size = seatNumbers.size();
        int randomIndex = rand.nextInt(size);
        String randomElement = seatNumbers.get(randomIndex);
        System.out.println(randomElement);
        return randomElement;
    }

    public void clickRandomSeat(String text) {
       Locator seatNumbers = page.locator(
               "//*[@fill='url(#preferredSeata)']//parent::*//following-sibling::*/../parent::*/parent::*/parent::div//following-sibling::div[(contains(@class,'css-76zvg2 r-homxoj r-poiln3 r-1enofrn r-1wyvozj')) and (text()='"
                       + text + "')]");
        seatNumbers.click();
    }

    public void clickDoneButton() {
        DoneButton.click();
    }

    public void clickPrivateSeatCheckbox() {
        Locator checkboxLocator = page.locator("//div[text()='Private Row']//parent::div/../parent::div/../parent::div//*[@stroke='#DDDDDD']");
        Locator AdjacentSeatLocator = page.locator(
                "//div[text()='Adjacent Seat']//parent::div/../parent::div/../parent::div//*[@stroke='#DDDDDD']");
        if (isElementExists(checkboxLocator)) {
            checkboxPrivateSeat.click();
            checkboxaccept.click();
            checkboxContinue.click();
            DoneButton.click();
        } else if (isElementExists(AdjacentSeatLocator)) {
            adjacentSeatLocator.click();
            checkboxaccept.click();
            checkboxContinue.click();
            DoneButton.click();
        } else {
            DoneButton.click();
        }
    }

    public void clickCloseIcon() {
        Locator closeIconLocator = page.locator("//div[@id='at_addon_close_icon']");
        if (isElementExists(closeIconLocator)){
           closeIcon.click();
        }
    }

    public String getTextTitle() {
        String text = TitleText.innerText();
        return text;
    }

    public String getTextFirstName() {
        String text = firstName.getAttribute("value");
        return text;
    }

    public String getTextLastName() {
        String text = lastName.getAttribute("value");
        return text;
    }

    public String getTextMobileNumber() {
        String text = memberNumber.getAttribute("value");
        return text;
    }
}
