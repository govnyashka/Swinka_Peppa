import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by User on 14.05.2018.
 */
public class Filter {

    private ChromeDriver driver;

    public void ByPlanDepartureDate(ChromeDriver driver, String planDepartureDate) throws Exception {
        this.driver = driver;
        Common common = new Common(driver);
        Common overoverlayOnTopOfIt = common;
        //overoverlayOnTopOfIt.waitUntilTheOverlayDisppears();
        Thread.sleep(7000);
        Common PresenceOfID = common;
        driver.findElement(By.id("show-options-filter")).isEnabled();
        PresenceOfID.waitingForID("show-options-filter").click();
        driver.findElement(By.id("rw_5_input")).sendKeys(planDepartureDate);
        PresenceOfID.waitingForID("apply-filter").click();
    }

    public void CarNumber(ChromeDriver driver, String planDepartureDate) throws Exception {
        this.driver = driver;
        Common common = new Common(driver);
        Thread.sleep(7000);
        Common PresenceOfID = common;
        driver.findElement(By.id("show-options-filter")).isEnabled();
        PresenceOfID.waitingForID("show-options-filter").click();
        driver.findElement(By.id("react-select-8--value")).sendKeys();
        PresenceOfID.waitingForID("apply-filter").click();
    }
}
