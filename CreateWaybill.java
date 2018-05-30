import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 14.05.2018.
 */
public class CreateWaybill {

    public String tmp = null;

    public String CreateWaybill(ChromeDriver driver) throws Exception {
        Common waitingForClick = new Common(driver);
        Common waitingForVisibility = new Common(driver);
        Common presenceOfID = new Common(driver);


        Thread.sleep(2000);
        presenceOfID.waitingForID("link-waybill-journal");
        presenceOfID.waitingForID("link-waybill-journal").click();
        Thread.sleep(2000);
        presenceOfID.waitingForID("open-create-form").click();
        presenceOfID.waitingForID("plan-departure-date_input").clear();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, +1);
        Date oneHourAhead = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String waybillsPlannedStartDate = sdf.format(oneHourAhead);
        presenceOfID.waitingForID("plan-departure-date_input").clear();
        presenceOfID.waitingForID("plan-departure-date_input").sendKeys(waybillsPlannedStartDate);
        //Сопровождающий
        presenceOfID.waitingForID("accompanying-person-id").click();
        presenceOfID.waitingForID("react-select-28--value").click();
        presenceOfID.waitingForID("1553").click();
        //Режим работы
        presenceOfID.waitingForID("react-select-29--value").click();
        presenceOfID.waitingForID("1").click();
        //Транспортное средство (поиск по рег. номер ТС)
        Thread.sleep(2000);
        presenceOfID.waitingForID("react-select-30--value").click();
        presenceOfID.waitingForID("19926").click();
        waitingForClick.isElementClickable("react-select-30--value-item");
        //Прицеп
        Thread.sleep(900);
        presenceOfID.waitingForID("react-select-31--value").click();
        presenceOfID.waitingForID("91411").click();
        //Создание задания
        presenceOfID.waitingForID("create-mission").click();
        //Задание - Технологическая операция
        Thread.sleep(2000);
        presenceOfID.waitingForID("react-select-35--value").click();
        presenceOfID.waitingForID("react-select-35--value").click();
        presenceOfID.waitingForID("79").click();


        // //Наличие текста: "Дата не должна выходить за пределы путевого листа"
        Common OutOfWaybillsSchedule = new Common(driver);
        List<String> allErrors = OutOfWaybillsSchedule.getAllErrorsTextListed();
        System.out.println(allErrors);
        Thread.sleep(2000);
        if (!allErrors.contains(" Дата не должна выходить за пределы путевого листа")) {
            presenceOfID.waitingForID("date-start_input").click();
            Thread.sleep(500);
            //driver.findElement(By.id("date_end_input")).click();
            presenceOfID.waitingForID("date-start_input").clear();
            presenceOfID.waitingForID("date-start_input").sendKeys(waybillsPlannedStartDate);
        } else
            System.out.println(waybillsPlannedStartDate);
        //Задание - Элемент

        waitingForClick.isElementClickable("react-select-37--value").click();
        driver.findElement(By.id("react-select-37--value")).click();

        waitingForVisibility.isElementVisible("19").click();

        //Создание маршрута
        Thread.sleep(2000);
        presenceOfID.waitingForID("create-route").click();
        //Название маршрута
        Thread.sleep(2000);
        waitingForClick.isElementClickable("route-name");
        presenceOfID.waitingForID("route-name").click();
        presenceOfID.waitingForID("route-name").sendKeys("23");
        //Список выбранных ОДХ
        presenceOfID.waitingForID("react-select-45--value").click();
        presenceOfID.waitingForID("347466").click();
        presenceOfID.waitingForID("react-select-45--value").click();
        presenceOfID.waitingForID("634121").click();
        //Нажатие кнопки "Создать".
        presenceOfID.waitingForID("route-submit").click();
        //Отображается уведомление: "Данные успешно сохранены". - необходимо навешать айдишник на уведомление. Есть только текст и класс
//


        //Наличие текста: "Время выполнения задания для ОДХ должно составлять не более 5 часов"

        Common LessThan5hours = new Common(driver);
        List<String> allErrors1 = LessThan5hours.getAllErrorsTextListed();
        Thread.sleep(2000);
        if (!allErrors.contains("Время выполнения задания для ОДХ должно составлять не более 5 часов")) {
            presenceOfID.waitingForID("date_end_input").click();
            presenceOfID.waitingForID("date_end_input").clear();
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
            presenceOfID.waitingForID("date_end_input").sendKeys(dateStartInput);
            presenceOfID.waitingForID("m-submit").click();
        }
        //Отображается уведомение:"Задание создано успешно".
        //ПЛ.Происходит переход на форму "Создать новый путевой лист".


        //ПЛ."Счетчик моточасов оборудования/Выезд из гаража, м/ч".
        //  Thread.sleep(1000);
        //presenceOfID.waitingForID("motohours-equip-start").clear();
        // presenceOfID.waitingForID("motohours-equip-start").sendKeys("9000");

        //ПЛ."Выдать, л".

//           driver.findElement(By.id("motohours-start")).clear();
//            presenceOfID.waitingForID("motohours-start").sendKeys("9000");
        //       presenceOfID.waitingForID("fuel-to-give").sendKeys("9000");

        //ПЛ."Простои на линии, ч."/Работа. - нужен id
        waitingForClick.isElementClickable("downtime-hours-work");
        presenceOfID.waitingForID("downtime-hours-work").click();
        presenceOfID.waitingForID("downtime-hours-work").clear();
        presenceOfID.waitingForID("downtime-hours-work").sendKeys("1");
        //ПЛ."Простои на линии, ч."/Дежурство. - нужен id
        presenceOfID.waitingForID("downtime-hours-duty").click();
        presenceOfID.waitingForID("downtime-hours-duty").clear();
        presenceOfID.waitingForID("downtime-hours-duty").sendKeys("1");
        //ПЛ."Простои на линии, ч."/Обед. - нужен id
        presenceOfID.waitingForID("downtime-hours-dinner").click();
        presenceOfID.waitingForID("downtime-hours-dinner").clear();
        presenceOfID.waitingForID("downtime-hours-dinner").sendKeys("1");
        //ПЛ."Простои на линии, ч."/Ремонт. - нужен id
        presenceOfID.waitingForID("downtime-hours-repair").click();
        presenceOfID.waitingForID("downtime-hours-repair").clear();
        presenceOfID.waitingForID("downtime-hours-repair").sendKeys("1");
        //Выбор водителя.
        presenceOfID.waitingForID("react-select-32--value").click();
        presenceOfID.waitingForID("react-select-32--value").click();
        presenceOfID.waitingForID("56778").click();
        Common MissionNumber = new Common(driver);
        MissionNumber.getNumbersFromText("react-select-33--value-0");
        //Получить "Выезд план."
        String planDepartureDate = driver.findElement(By.id("plan-departure-date_input")).getAttribute("value");

        //Получить "Транспортное средство"
        Common carFromWaybill = new Common(driver);
        tmp = carFromWaybill.getCarsNumber("react-select-30--value-item");
        //Нажатие кнопки Выдачи ПЛ.
        //Выгрузка "Форма 2 (грузовое ТС)"
        presenceOfID.waitingForID("waybill-print-dropdown_save").click();

        driver.findElement(By.linkText("Форма №2 (грузовое ТС)")).click();
//Возможно сообщение: "Дата планируемого выезда должна быть больше фактической даты возвращения т.с. в предыдущем путевом листе."

        return planDepartureDate;
    }


}