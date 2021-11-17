package com.cydeo.step_definitons;

import com.cydeo.Utils.ConfigReader;
import com.cydeo.Utils.DB_Util;
import com.cydeo.Utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before("@ui")
    public void setupDriver() {

        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().get(ConfigReader.read("library_url"));

    }


    @After("@ui")
    public void tearDown(Scenario scenario) {

        // check if scenario failed or not
        if (scenario.isFailed()) {
            //this is how we take screenshot in selenium
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            // scenario.attach(screenshot,"image/png","what ever we want");
            scenario.attach(screenshot, "image/png", "Image for failed step");
        }
        Driver.closeBrowser();
    }


    @Before("@db")
    public void dbSetup() {

        DB_Util.createConnection(ConfigReader.read("DBurl"),
                ConfigReader.read("DBusername"),
                ConfigReader.read("DBpassword"));

    }


    @After("@db")
    public void dbTearDown() {

        DB_Util.destroy();
    }
}
