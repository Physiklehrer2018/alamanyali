package com.circleboom.step_definitions;
import com.circleboom.pages.CreateNewPost;
import com.circleboom.pages.LoginFromHomePage;
import com.circleboom.utilities.ConfigurationReader;
import com.circleboom.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Login_StepDefs {
    LoginFromHomePage homePage = new LoginFromHomePage();
    WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
    CreateNewPost createNewPost = new CreateNewPost();


    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        String url = ConfigurationReader.get("url");
        String ScreenSize = System.getProperty("ScreenSize");
        String[] size = ScreenSize.trim().split("_");
        System.out.println(size[0]+" "+size[1]);
        int size1=Integer.parseInt(size[0]);
        int size2=Integer.parseInt(size[1]);
        Dimension dimension=new Dimension(size1, size2);
        Driver.get().manage().window().setSize(dimension);
        System.out.println("**************Windows size :"+Driver.get().manage().window().getSize()+"******************");
        Driver.get().get(url);
        System.out.println("**************URL:"+Driver.get().getCurrentUrl()+"******************");
        //Driver.get().manage().window().maximize();

    }

    @And("I hover over Get Started button")
    public void iHoverOverGetStartedButton() {
        Actions action = new Actions(Driver.get());
        action.moveToElement(homePage.getStartedButton).click().build().perform();

    }

    @And("I click publish button")
    public void iClickPublishButton() {
        wait.until(ExpectedConditions.visibilityOf(homePage.publishButtonHomePage)).click();
    }

    @When("I login right credentials for free verified user")
    public void iLoginRightCredentialsForFreeVerifiedUser() {
        wait.until(ExpectedConditions.visibilityOf(homePage.userName)).sendKeys(ConfigurationReader.get("userNameFreeVerified1"));
        wait.until(ExpectedConditions.visibilityOf(homePage.password)).sendKeys(ConfigurationReader.get("passwordFreeVerified1"));
        wait.until(ExpectedConditions.visibilityOf(homePage.submit)).click();
    }

    @When("I login right credentials for premium verified user")
    public void iLoginRightCredentialsForPremiumVerifiedUser() {
        wait.until(ExpectedConditions.visibilityOf(homePage.userName)).sendKeys(ConfigurationReader.get("userNamePremVerified"));
        wait.until(ExpectedConditions.visibilityOf(homePage.password)).sendKeys(ConfigurationReader.get("passwordPremVerified"));
        wait.until(ExpectedConditions.visibilityOf(homePage.submit)).click();
    }

    @Then("I should go to my account page as verified free user")
    public void iShouldGoToMyAccountPageAsVerifiedFreeUser() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.FreeTag));
        Assert.assertTrue(createNewPost.FreeTag.isDisplayed());
    }

    @Then("I should go to my account page as verified premium user")
    public void iShouldGoToMyAccountPageAsVerifiedPremiumUser() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(createNewPost.PremiumTag)).isDisplayed());
    }
}
