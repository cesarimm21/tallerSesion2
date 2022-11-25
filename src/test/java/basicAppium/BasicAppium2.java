package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasicAppium2 {

    AppiumDriver driver;
    @BeforeEach
    public void configure() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","JBNoviembre");
        capabilities.setCapability("platformVersion","9.0");
        capabilities.setCapability("appPackage","com.android.calculator2");
        capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
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
        // click button 8
        driver.findElement(By.xpath("//*[@text='8']")).click();
        // click +
        driver.findElement(By.xpath("//*[@content-desc='plus']")).click();
        // click button 9
        driver.findElement(By.xpath("//*[@text='9']")).click();
        // click button =
        driver.findElement(By.xpath("//*[@content-desc='equals']")).click();

        // explicit
//        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
//        explicitWait.until(ExpectedConditions.textToBe(By.xpath("//*[contains(@resource-id,'result')]"),"17"));

        // fluent
        FluentWait fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.textToBe(By.xpath("//*[contains(@resource-id,'result')]"),"17"));


        String expectedResult="17";
        String actualResult=driver.findElement(By.xpath("//*[contains(@resource-id,'result')]")).getText();
        Assertions.assertEquals(expectedResult,actualResult,"ERROR la suma es incorrecta");
    }
}
