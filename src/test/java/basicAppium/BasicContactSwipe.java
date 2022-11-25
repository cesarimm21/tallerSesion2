package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasicContactSwipe {

    AppiumDriver driver;
    @BeforeEach
    public void configure() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","EynarH");
        capabilities.setCapability("platformVersion","9.0");
        capabilities.setCapability("appPackage","com.android.contacts");
        capabilities.setCapability("appActivity","com.android.contacts.activities.PeopleActivity");
        capabilities.setCapability("platformName","Android");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        // implicit waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @AfterEach
    public void closeApp(){

        driver.quit();
    }

    @Test
    public void verifyCalculator() throws InterruptedException {

        TouchAction action = new TouchAction((PerformsTouchActions) driver);
        WebElement el1 = driver.findElement(By.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.view.ViewGroup[3]"));
        WebElement el2= driver.findElement(By.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.view.ViewGroup[1]"));

        int el1X= el1.getLocation().getX();
        int el1Y= el1.getLocation().getY();

        int el2X= el2.getLocation().getX();
        int el2Y= el2.getLocation().getY();

        // swipe - scroll
        action.press(PointOption.point(el1X,el1Y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(el2X,el2Y))
                .release().perform();
        Thread.sleep(5000);

    }
}
