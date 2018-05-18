import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 16.05.2018.
 */
public class CreateAdditionalMission {
    private ChromeDriver driver;
    public void AddMission1 (ChromeDriver driver) throws Exception {
        Common PresenceOfID = new Common(driver);
        Common WaitingForClick = new Common(driver);
        Common WaitingForVisibility = new Common(driver);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, +1);
        Date oneHourAhead = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String waybillsPlannedStartDate = sdf.format(oneHourAhead);
Common WaitingForClickByXpath = new Common(driver);

        Thread.sleep(20000);
        //Хватает первый элемент
//        WebElement elementOpenUpdateForm = WaitingForClickByXpath.IsElementClickableByXpath("//*[@id=\"container\"]/div/div[2]/div[1]/div[1]/div[2]/div/div/div/div/table/tbody[1]/tr/td[1]/div/input");
//        WebElement elementOpenUpdateForm = WaitingForClickByXpath.IsElementClickableByXpath("//*[@id=\"container\"]/div/div[2]/div[1]/div[1]/div[2]/div/div/div/div/table/tbody[1]/tr/td[1]/div/input");
//        if (elementOpenUpdateForm.isEnabled()) elementOpenUpdateForm.click();
        driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/div[1]/div[1]/div[2]/div/div/div/div/table/tbody[1]/tr/td[1]/div/input")).click();
        //Форма "Активный путевой лист"
        driver.findElement(By.id("open-update-form")).click();

        PresenceOfID.WaitingForID("create-mission").click();
        //Задание - Технологическая операция
        Thread.sleep(5000);

        PresenceOfID.WaitingForID(" technical-operation-id").click();
        //PresenceOfID.WaitingForID("react-select-35--value").click();
        PresenceOfID.WaitingForID("103").click();


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

        WaitingForVisibility.IsElementVisible("1").click();

        //Создание маршрута
        Thread.sleep(2000);
        PresenceOfID.WaitingForID("create-route").click();
        //Название маршрута
        Thread.sleep(2000);
        PresenceOfID.WaitingForID("route-name").click();
        PresenceOfID.WaitingForID("route-name").sendKeys("Маршрут №1");
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
}}
