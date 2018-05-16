import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 14.05.2018.
 */
public class CreateWaybill {
    private ChromeDriver driver;
    private String browserName;
    private String browserVersion;

    public String CreateWaybill(ChromeDriver driver) throws Exception {
        {
            this.driver = driver;
        }
        Common WaitingForClick = new Common(driver);
        Common WaitingForVisibility = new Common(driver);
        Common PresenceOfID = new Common(driver);
        Common PresenceOfClass = new Common(driver);
        Common EnableID = new Common(driver);
        Common fwefew = new Common(driver);
        Thread.sleep(2000);
        PresenceOfID.WaitingForID("link-waybill-journal").click();
        PresenceOfClass.WaitingForClass("standard-row");
        Thread.sleep(2000);
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
        WaitingForClick.IsElementClickable("react-select-30--value-item");
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
            PresenceOfID.WaitingForID("date-start_input").sendKeys(waybillsPlannedStartDate);
        } else
            System.out.println(waybillsPlannedStartDate);
        //Задание - Элемент

        WaitingForClick.IsElementClickable("react-select-37--value").click();
        driver.findElement(By.id("react-select-37--value")).click();

        WaitingForVisibility.IsElementVisible("19").click();

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
//        fwefew.CloseNotification();


        //Наличие текста: "Время выполнения задания для ОДХ должно составлять не более 5 часов"

        Common LessThan5hours = new Common(driver);
        List<String> allErrors1 = LessThan5hours.getAllErrorsTextListed();
        Thread.sleep(2000);
        if (!allErrors.contains("Время выполнения задания для ОДХ должно составлять не более 5 часов")) {
            PresenceOfID.WaitingForID("date_end_input").click();
            PresenceOfID.WaitingForID("date_end_input").clear();
            if (!driver.findElement(By.id("date_end_input")).getAttribute("value").isEmpty()) ;
            String dateStartInput = driver.findElement(By.id("date-start_input")).getAttribute("value");
            if (dateStartInput.isEmpty())
                System.out.println("lazha");
            else
                System.out.println(dateStartInput);


            try {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                Date res = new Date(format.parse(dateStartInput).getTime() + 1000 * 3600);
                dateStartInput = format.format(res);
            } catch (Exception e) {
                System.out.println("Не увижу этот текст");
            }
            PresenceOfID.WaitingForID("date_end_input").sendKeys(dateStartInput);
            PresenceOfID.WaitingForID("m-submit").click();
        }
        //Отображается уведомение:"Задание создано успешно".
        //ПЛ.Происходит переход на форму "Создать новый путевой лист".


        //ПЛ."Счетчик моточасов оборудования/Выезд из гаража, м/ч".
        //  Thread.sleep(1000);
        //PresenceOfID.WaitingForID("motohours-equip-start").clear();
        // PresenceOfID.WaitingForID("motohours-equip-start").sendKeys("9000");

        //ПЛ."Выдать, л".

//           driver.findElement(By.id("motohours-start")).clear();
//            PresenceOfID.WaitingForID("motohours-start").sendKeys("9000");
        //       PresenceOfID.WaitingForID("fuel-to-give").sendKeys("9000");

        //ПЛ."Простои на линии, ч."/Работа. - нужен id
        Thread.sleep(500);
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
        Common MissionNumber = new Common(driver);
        MissionNumber.GetNumbersFromText("react-select-33--value-0");
        //Получить "Выезд план."
        String planDepartureDate = driver.findElement(By.id("plan-departure-date_input")).getAttribute("value");
        //Нажатие кнопки сохранения ПЛ.
        //driver.findElement(By.id("waybill-submit")).click();
        //Нажатие кнопки Принта ПЛ.
        //driver.findElement(By.id("waybill-print-dropdown")).click();

        //Нажатие кнопки Выдачи ПЛ.
        //Выгрузка "Форма 2 (грузовое ТС)"
        PresenceOfID.WaitingForID("waybill-print-dropdown_save").click();

        driver.findElement(By.linkText("Форма №2 (грузовое ТС)")).click();
//"Дата планируемого выезда должна быть больше фактической даты возвращения т.с. в предыдущем путевом листе."


//        WebElement a = driver.findElement(By.className("notification.notification-success.notification-visible"));
//        WebElement b = driver.findElement(By.className("notification-dismiss"));
//        System.out.println(a + "-------------------------------------" + b);

        return planDepartureDate;
    }
}