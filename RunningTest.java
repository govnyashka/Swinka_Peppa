import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import org.openqa.selenium.chrome.ChromeDriver;


public class RunningTest {

    private ChromeDriver driver;
    private String browserName;
    private String browserVersion;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        browserName = "Chrome";
        browserVersion = "46";
        System.out.println("Automated test run. We’re running on " + browserName + " " + browserVersion);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @Test
    public void WaybillCD() throws Exception {
        Login Enter = new Login();
        Enter.User(driver, "втиавт", "уеркурику");
        CreateWaybill FromJournal = new CreateWaybill();
        String CreateWaybillDate;

        CreateWaybillDate = FromJournal.CreateWaybill(driver);
        Filter LookingForWaybill = new Filter();
        LookingForWaybill.ByPlanDepartureDate(driver, CreateWaybillDate);
        CreateAdditionalMission OneMoreMission = new CreateAdditionalMission();
        OneMoreMission.AddMission1(driver);
//        Delete AfterCreation = new Delete();
//        AfterCreation.DeleteWaybill(driver);
    }


//    @AfterClass(alwaysRun = true)
//    public void tearDown() throws Exception {
//        driver.quit();
//    }

}



