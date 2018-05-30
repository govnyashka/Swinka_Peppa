import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

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
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

//        File file = new File("C:/eidriver/IEDriverServer.exe");
//        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
//        WebDriver driver = new InternetExplorerDriver();
//
//        System.setProperty("webdriver.chrome.driver", "C:\\eidriver\\IEDriverServer.exe");
//        driver = new InternetExplorerDriver();
//        browserName = "IEDriver";
//        browserVersion = "9";
//        System.out.println("Automated test run. We’re running on " + browserName + " " + browserVersion);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @Test
    @Parameters
    public void WaybillCD() throws Exception {
        Login Enter = new Login();
        Enter.User(driver, "erregvrsthg4w4", "hw45hwh5");
        CreateWaybill FromJournal = new CreateWaybill();
        String createWaybillDate;

        String currentTMP = FromJournal.tmp;
        FromJournal.tmp = null;

        createWaybillDate = FromJournal.CreateWaybill(driver);
        //Фильтр по "Выезд план"
        Filter lookingForWaybill = new Filter();
        lookingForWaybill.ByPlanDepartureDate(driver, createWaybillDate);

//        Filter lookingForCarNumber = new Filter();
//        lookingForCarNumber.ByCarNumber(driver, currentTMP);

        CreateAdditionalMission OneMoreMission = new CreateAdditionalMission();
        OneMoreMission.AddMission1(driver);

        DeleteAdditionalMission deleteOneMoreMission = new DeleteAdditionalMission();
        deleteOneMoreMission.deleteMission1(driver);
//        Delete AfterCreation = new Delete();
//        AfterCreation.DeleteWaybill(driver);
    }


//    @AfterClass(alwaysRun = true)
//    public void tearDown() throws Exception {
//        driver.quit();
//    }

}



