import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by User on 14.05.2018.
 */
public class DeleteWaybill {
    private ChromeDriver driver;
    public void setUp (ChromeDriver driver) throws Exception {
        driver.findElement(By.xpath("show-options-filter")).click();
        driver.findElement(By.id("remove-element")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[19]")).click();
    }
}