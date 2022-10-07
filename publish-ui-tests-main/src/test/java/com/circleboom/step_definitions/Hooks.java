package com.circleboom.step_definitions;


import com.circleboom.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;


public class Hooks {

    @Before
    public void setUp(){
        System.out.println("Before hooks");

    }

    @Before("@database")
    public void setUpDBCOnn(){
        System.out.println("Setting up DB connection");
    }

    @After
    public void tearDown(Scenario scenario){
        System.out.println("After hooks");

        if (scenario.isFailed()){
            // take that screenshot
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            // attach the scenario to the report
             scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }

    @After("@database")
    public void tearDownConnection(){
        System.out.println("Closing DB connection");
    }
}
