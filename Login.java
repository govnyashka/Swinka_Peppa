import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.utils.DateUtils;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import static jdk.nashorn.internal.parser.DateParser.HOUR;
import static org.openqa.selenium.By.className;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.Assertion;
import sun.util.calendar.BaseCalendar;


public class Login {

    private ChromeDriver driver;
    private String browserName;
    private String browserVersion;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
      //  ChromeOptions options = new ChromeOptions();
        //driver = new ChromeDriver(options);
        //driver.get("http://localhost:3000");
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            browserName = "Chrome";
            browserVersion = "46";
            System.out.println("Automated test run. We’re running on "+browserName+" "+browserVersion);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        //@AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testLogin() throws Exception {
        Common missionNumber = new Common(driver);
        missionNumber.StringToNumber("№1051 (Выборочная мойка)");
        System.out.println(missionNumber);

        driver.get("https://ets.mos.ru/ets-stage2/#/login");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("login")).sendKeys("1");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("1");
        Common PresenceOfID = new Common(driver);
        Common PresenceOfClass = new Common(driver);
        Common EnableID = new Common(driver);

        PresenceOfID.WaitingForID("submit").click();
        //Вход в систему
        // Реестр ПЛ

        Thread.sleep(2000);
        PresenceOfID.WaitingForID("link-waybill-journal").click();
        PresenceOfClass.WaitingForClass("standard-row");
        PresenceOfID.WaitingForID("open-create-form").click();
        PresenceOfID.WaitingForID("plan-departure-date_input").clear();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, +1);
        Date oneHourAhead = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String waybillsPlannedStartDate = sdf.format(oneHourAhead);
        PresenceOfID.WaitingForID("plan-departure-date_input").clear();
        PresenceOfID.WaitingForID("plan-departure-date_input").sendKeys(waybillsPlannedStartDate);
        //Сопровождающий
        PresenceOfID.WaitingForID("accompanying-person-id").click();
        PresenceOfID.WaitingForID("react-select-28--value").click();
        PresenceOfID.WaitingForID("1553").click();
        //Режим работы
        PresenceOfID.WaitingForID("react-select-29--value").click();
        PresenceOfID.WaitingForID("1").click();
        //Транспортное средство (поиск по рег. номер ТС)
        Thread.sleep(2000);
        PresenceOfID.WaitingForID("react-select-30--value").click();
        PresenceOfID.WaitingForID("19926").click();
        //Прицеп
        Thread.sleep(900);
        PresenceOfID.WaitingForID("react-select-31--value").click();
        PresenceOfID.WaitingForID("91411").click();
        //Создание задания
        PresenceOfID.WaitingForID("create-mission").click();
        //Задание - Технологическая операция
        Thread.sleep(2000);
        PresenceOfID.WaitingForID("react-select-35--value").click();
        PresenceOfID.WaitingForID("79").click();


        // //Наличие текста: "Дата не должна выходить за пределы путевого листа"
        Common OutOfWaybillsSchedule = new Common(driver);
        List<String> allErrors = OutOfWaybillsSchedule.getAllErrorsTextListed();
        System.out.println(allErrors);
        Thread.sleep(2000);
        if (!allErrors.contains(" Дата не должна выходить за пределы путевого листа")) {
            PresenceOfID.WaitingForID("date-start_input").click();
            Thread.sleep(500);
            //driver.findElement(By.id("date_end_input")).click();
            PresenceOfID.WaitingForID("date-start_input").clear();
            PresenceOfID.WaitingForID("date-start_input").sendKeys(waybillsPlannedStartDate);}
            else
                System.out.println(waybillsPlannedStartDate);
        //Задание - Элемент

//        EnableID.IsElementAppear("municipal_facility_id");

        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement urnyElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-37--value")));
        urnyElement.click();
        urnyElement.click();
        WebDriverWait wait1 = new WebDriverWait(driver, 5);
        WebElement element19 = wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("19"))));

        element19.click();

        //Создание маршрута
        Thread.sleep(2000);
        PresenceOfID.WaitingForID("create-route").click();
        //Название маршрута
        Thread.sleep(2000);
        PresenceOfID.WaitingForID("route-name").click();
        PresenceOfID.WaitingForID("route-name").sendKeys("23");
        //Список выбранных ОДХ
        PresenceOfID.WaitingForID("react-select-45--value").click();
        PresenceOfID.WaitingForID("347466").click();
        PresenceOfID.WaitingForID("react-select-45--value").click();
        PresenceOfID.WaitingForID("634121").click();
        //Нажатие кнопки "Создать".
        PresenceOfID.WaitingForID("route-submit").click();
        //Отображается уведомление: "Данные успешно сохранены". - необходимо навешать айдишник на уведомление. Есть только текст и класс


            //Наличие текста: "Время выполнения задания для ОДХ должно составлять не более 5 часов"
        Common LessThan5hours = new Common(driver);
        List<String> allErrors1 = LessThan5hours.getAllErrorsTextListed();
        Thread.sleep(2000);
        if (!allErrors.contains("Время выполнения задания для ОДХ должно составлять не более 5 часов")) {
            PresenceOfID.WaitingForID("date_end_input").click();

            //driver.findElement(By.id("date_end_input")).click();
            PresenceOfID.WaitingForID("date_end_input").clear();
            //driver.findElement(By.id("date_end_input")).clear();
            //todo Какого фига он пишет лажа1
            if (!driver.findElement(By.id("date_end_input")).getAttribute("value").isEmpty()) ;
            System.out.println("lazha1");
            //todo end

            String dateStartInput = driver.findElement(By.id("date-start_input")).getAttribute("value");
            if (dateStartInput.isEmpty())
                System.out.println("lazha");
            else
                System.out.println(dateStartInput);


            /**
             * Нормальный TODO
             * научиться парсить String -> в Calendar
             * получившейся переменной прибавить 1 час     *
             * */

            try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date res = new Date(format.parse(dateStartInput).getTime() + 1000*3600);
                dateStartInput = format.format(res);
            System.out.println("Жизнь продолжается");}

            catch (Exception e){
                System.out.println("Не увижу этот текст");
            }
            PresenceOfID.WaitingForID("date_end_input").sendKeys(dateStartInput);
            PresenceOfID.WaitingForID("m-submit").click();

            //Отображается уведомение:"Задание создано успешно".
            //ПЛ.Происходит переход на форму "Создать новый путевой лист".


             //ПЛ."Счетчик моточасов оборудования/Выезд из гаража, м/ч".
          //  Thread.sleep(1000);
            //PresenceOfID.WaitingForID("motohours-equip-start").clear();
           // PresenceOfID.WaitingForID("motohours-equip-start").sendKeys("9000");

            //ПЛ."Выдать, л".

            PresenceOfID.WaitingForID("fuel-to-give").sendKeys("9000");

            //ПЛ."Простои на линии, ч."/Работа. - нужен id
            driver.findElement(By.xpath("(//input[@type='string'])[2]")).click();
            driver.findElement(By.xpath("(//input[@type='string'])[2]")).clear();
            Thread.sleep(500);
            driver.findElement(By.xpath("(//input[@type='string'])[2]")).sendKeys("1");
            //ПЛ."Простои на линии, ч."/Дежурство. - нужен id
            driver.findElement(By.xpath("(//input[@type='string'])[3]")).click();
            driver.findElement(By.xpath("(//input[@type='string'])[3]")).clear();
            Thread.sleep(500);
            driver.findElement(By.xpath("(//input[@type='string'])[3]")).sendKeys("1");
            //ПЛ."Простои на линии, ч."/Обед. - нужен id
            driver.findElement(By.xpath("(//input[@type='string'])[4]")).click();
            driver.findElement(By.xpath("(//input[@type='string'])[4]")).clear();
            Thread.sleep(500);
            driver.findElement(By.xpath("(//input[@type='string'])[4]")).sendKeys("1");
            //ПЛ."Простои на линии, ч."/Ремонт. - нужен id
            driver.findElement(By.xpath("(//input[@type='string'])[5]")).click();
            driver.findElement(By.xpath("(//input[@type='string'])[5]")).clear();
            Thread.sleep(500);
            driver.findElement(By.xpath("(//input[@type='string'])[5]")).sendKeys("1");
            //Выбор водителя.
            PresenceOfID.WaitingForID("react-select-32--value").click();
            PresenceOfID.WaitingForID("56778").click();

            //Нажатие кнопки сохранения ПЛ.
            //driver.findElement(By.id("waybill-submit")).click();
            //Нажатие кнопки Принта ПЛ.
            //driver.findElement(By.id("waybill-print-dropdown")).click();

            //Нажатие кнопки Выдачи ПЛ.
            //Выгрузка формы 4ф
            PresenceOfID.WaitingForID("waybill-print-dropdown_save").click();
            driver.findElement(By.linkText("Форма 2 (грузовое ТС)")).click();
//"Дата планируемого выезда должна быть больше фактической даты возвращения т.с. в предыдущем путевом листе."


            //Фильтр. Поиск созданного ПЛ


            try {
                driver.findElement(By.xpath("//div[2]/div/div/div[2]/div[3]/div/div")).click();
            } catch (Exception e) {
                System.out.println("INFO");
                System.out.println(e.getLocalizedMessage());
                System.out.println("INFO");
                System.out.println(e.getMessage());
                System.out.println("INFO");
                System.out.println(e.getStackTrace());
                System.out.println("END OF INFO");
            }



        }
    }}






