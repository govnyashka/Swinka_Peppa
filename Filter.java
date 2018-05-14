import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by User on 14.05.2018.
 */
public class Filter {

    private ChromeDriver driver;

    public void ByPlanDepartureDate (ChromeDriver driver, String planDepartureDate)throws Exception{
        {
            this.driver = driver;
        }

        Thread.sleep(2000);
        Common WaitingForClick = new Common(driver);
        Common PresenceOfID = new Common(driver);
       // String  planDepartureDate =  driver.findElement(By.id("plan-departure-date_input")).getAttribute("value");
        WaitingForClick.IsElementClickable("show-options-filter");
        PresenceOfID.WaitingForID("show-options-filter").click();
        driver.findElement(By.id("rw_5_input")).click();
        driver.findElement(By.id("rw_5_input")).clear();
        driver.findElement(By.id("rw_5_input")).sendKeys(planDepartureDate);
        PresenceOfID.WaitingForID("apply-filter").click();
}}
