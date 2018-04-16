import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Common {

    private ChromeDriver driver;
    private String browserName;
    private String browserVersion;

    public Common (ChromeDriver driver)
    {
        this.driver = driver;
    }

    public void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        browserName = "Chrome";
        browserVersion = "46";
        System.out.println("Automated test run. We’re running on "+browserName+" "+browserVersion);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void testLogin() throws Exception {

            driver.get("https://ets.mos.ru/ets-stage2/#/login");
            driver.findElement(By.id("login")).click();
            driver.findElement(By.id("login")).sendKeys("lublino_md");
            driver.findElement(By.id("password")).click();
            driver.findElement(By.id("password")).sendKeys("ets123");
            driver.findElement(By.id("submit")).click();

    }

                         //Ждать до отображения элемента по ID
    public WebElement WaitingForID(String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement WaitingForID = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementID)));
        return WaitingForID;
    }

    public WebElement WaitingForClass(String elementsClass) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement WaitingForClass = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(elementsClass)));
        return WaitingForClass;


    }

                        //Лист всех текстов из каждого элемента класса "error"11
    public List<String> getAllErrorsTextListed() throws Exception{
       List<WebElement> errorList = driver.findElements(By.className("error"));


        List<String> errorTextList = new ArrayList<>();
        for (int i = 0; i<errorList.size(); i++)
            errorTextList.add(errorList.get(i).getText());
        return errorTextList;
    }

    public String StringToNumber(String missionNT){
        String str=missionNT;
        String numberOnly= str.replaceAll("[^0-9]", "");
        return numberOnly;
    }

    public String getAllErrorsList(int error_number) throws Exception{
       List<WebElement> errorList = driver.findElements(By.className("error"));


//        String allerrorslist = "";
//        for (int a = 0; a < errorList.size(); a++){
//            System.out.println(errorList.get(a).getText());
//            result = result.concat("|||||").concat(errorList.get(a).getText());
//        }
//        if (errorList.size() > 0) {
//
//        }
//        else {
//
//        }


        return errorList.get(error_number).getText();
//        System.out.println(allerrorslist);


    }

    public WebElement IsElementAppear(String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement IsElementAppear = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementID)));
        return IsElementAppear;
    }

}




