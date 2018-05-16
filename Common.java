import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Common {

    private ChromeDriver driver;
    private String browserName;
    private String browserVersion;

    public Common(ChromeDriver driver) {
        this.driver = driver;
    }

    public void setUp(ChromeDriver driver) throws Exception {


        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        browserName = "Chrome";
        browserVersion = "46";
        System.out.println("Automated test run. We’re running on " + browserName + " " + browserVersion);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public void CloseNotification() {
        if (driver.findElement(By.className("notification notification-success notification-visible")).isEnabled()) {
            driver.findElement(By.className("notification-dismiss")).click();
        }
    }

    //Ждать до отображения элемента по ID
    public WebElement WaitingForID(String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement WaitingForID = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementID)));
        return WaitingForID;
    }

    //Ждать, когда элемент станет "кликабельным"
    public WebElement IsElementClickable(String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 90);
        WebElement IsElementClickable = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementID)));
        return IsElementClickable;
    }

    //Ждать, когда отобразиться элемент
    public WebElement IsElementVisible(String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement IsElementVisible = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(elementID))));
        return IsElementVisible;
    }

    //Наличие класса
    public WebElement WaitingForClass(String elementsClass) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement WaitingForClass = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(elementsClass)));
        return WaitingForClass;
    }

    //Вынуть числа
    public Matcher GetNumbersFromText(String elementID) {
        String MissionTitle = driver.findElement(By.id(elementID)).getText();
        Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pat.matcher(MissionTitle);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        return matcher;
    }

    //Лист всех текстов из каждого элемента класса "error"
    public List<String> getAllErrorsTextListed() throws Exception {
        List<WebElement> errorList = driver.findElements(By.className("error"));


        List<String> errorTextList = new ArrayList<>();
        for (int i = 0; i < errorList.size(); i++)
            errorTextList.add(errorList.get(i).getText());
        return errorTextList;
    }

    //Строку в номер
    public String StringToNumber(String missionNT) {
        String str = missionNT;
        String numberOnly = str.replaceAll("[^0-9]", "");
        return numberOnly;
    }

    //deal with a list of elements
//    public void ClickOnTheCheckbox(String ElementType) {
//
//
//        WebElement someElement = driver.findElement(By.cssSelector("input"));
//        String typeOfElement = someElement.getAttribute("type");
//        //Пробегаемся и выбираем то, что нужно
//        List<WebElement> someElements = driver.findElements(By.cssSelector("input"));
//        for (WebElement anElement : someElements) {
//            if (anElement.getAttribute("type").equals(ElementType)) {
//             //то поисходть  ;
//            }
//        }
//    }
//    public String getAllErrorsList(int error_number) throws Exception{
//       List<WebElement> errorList = driver.findElements(By.className("error"));
//
//
////        String allerrorslist = "";
////        for (int a = 0; a < errorList.size(); a++){
////            System.out.println(errorList.get(a).getText());
////            result = result.concat("|||||").concat(errorList.get(a).getText());
////        }
////        if (errorList.size() > 0) {
////
////        }
////        else {
////        }
//        return errorList.get(error_number).getText();
////        System.out.println(allerrorslist);
    //   }

    public WebElement IsElementAppear(String elementID) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement IsElementAppear = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementID)));
        return IsElementAppear;
    }


}




