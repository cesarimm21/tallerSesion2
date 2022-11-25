package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WhenDoApk {

    AppiumDriver driver;
    @BeforeEach
    public void configure() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","192.168.120.102:5555");
        capabilities.setCapability("platformVersion","9.0");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity","com.vrproductiveapps.whendo.ui.HomeActivity");
        capabilities.setCapability("platformName","Android");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        // implicit waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @AfterEach
    public void closeApp(){

        //driver.quit();
    }

    @Test
    public void createItemList() throws InterruptedException {
        // click +
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        // set title
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys("test taller 2");
        //set nota
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("Notas para el taller 2");
        // click save
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
        // verification
        String expectedResult="test taller 2";
        String actualResult=driver.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_text")).getText();
        Assertions.assertEquals(expectedResult,actualResult,"ERROR!! no se creo el la lista");
    }
}
