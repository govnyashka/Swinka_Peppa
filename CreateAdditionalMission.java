import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 16.05.2018.
 */
public class CreateAdditionalMission {
  //  private ChromeDriver driver;

    public void AddMission1(ChromeDriver driver) throws Exception {
        Common PresenceOfID = new Common(driver);
        Common WaitingForClick = new Common(driver);
        Common WaitingForVisibility = new Common(driver);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, +1);
        Date oneHourAhead = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String waybillsPlannedStartDate = sdf.format(oneHourAhead);
        Common WaitingForClickByXpath = new Common(driver);

        Thread.sleep(1000);


        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,450)", "");
        //Хватает первый элемент
        PresenceOfID.waitingForID("checkbox-id").click();
        //Форма "Активный путевой лист"

        PresenceOfID.waitingForID("open-update-form").click();

        PresenceOfID.waitingForID("create-mission").click();
        //Задание - Технологическая операция
        Thread.sleep(5000);
        PresenceOfID.waitingForID("react-select-50--value").click();
        PresenceOfID.waitingForID("100").click();


        // //Наличие текста: "Дата не должна выходить за пределы путевого листа"
        Common OutOfWaybillsSchedule = new Common(driver);
        List<String> allErrors = OutOfWaybillsSchedule.getAllErrorsTextListed();
        System.out.println(allErrors);
        Thread.sleep(2000);
        if (!allErrors.contains(" Дата не должна выходить за пределы путевого листа")) {
            PresenceOfID.waitingForID("date-start_input").click();
            Thread.sleep(500);
            PresenceOfID.waitingForID("date-start_input").clear();
            PresenceOfID.waitingForID("date-start_input").sendKeys(waybillsPlannedStartDate);
        } else
            System.out.println(waybillsPlannedStartDate);

        //Задание - Элемент

        WaitingForClick.isElementClickable("react-select-52--value").click();
        driver.findElement(By.id("react-select-52--value")).click();

        WaitingForVisibility.isElementVisible("101").click();

        //Создание маршрута
        Thread.sleep(2000);
        PresenceOfID.waitingForID("create-route").click();
        //Название маршрута
        Thread.sleep(2000);
        PresenceOfID.waitingForID("route-name").click();
        PresenceOfID.waitingForID("route-name").sendKeys("Маршрут №2");
        //Список выбранных ОДХ
        PresenceOfID.waitingForID("react-select-60--value").click();
        PresenceOfID.waitingForID("462688").click();
        //Нажатие кнопки "Создать".
        PresenceOfID.waitingForID("route-submit").click();
        //Отображается уведомление: "Данные успешно сохранены".

        //Наличие текста: "Время выполнения задания для ОДХ должно составлять не более 5 часов"
        Common LessThan5hours = new Common(driver);
        List<String> allErrors1 = LessThan5hours.getAllErrorsTextListed();
        Thread.sleep(2000);
        if (!allErrors.contains("Время выполнения задания для ОДХ должно составлять не более 5 часов")) {
            PresenceOfID.waitingForID("date_end_input").click();
            PresenceOfID.waitingForID("date_end_input").clear();
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
            PresenceOfID.waitingForID("date_end_input").sendKeys(dateStartInput);
            PresenceOfID.waitingForID("m-submit").click();
            PresenceOfID.waitingForID("waybill-submit").click();

        }
    }
}
