package com.circleboom.pages;

import com.circleboom.utilities.ConfigurationReader;
import com.circleboom.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginFromHomePage {

    public LoginFromHomePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//div[contains(@class,'layout_get_started')]")
    public WebElement getStartedButton;
    @FindBy(css = "a[href='https://twitter.circleboom.com']")
    public WebElement twitterButtonHomePage;
    @FindBy(css = "a[href='https://publish.circleboom.com']")
    public WebElement publishButtonHomePage;
    @FindBy(id = "loginId")
    public WebElement userName;
    @FindBy(id = "password")
    public WebElement password;
    @FindBy(xpath = "//button[@class='blue button']")
    public WebElement submit;
    @FindBy(xpath = "//*[@id='__next']/div[1]/header/div/div/a/text()")
    public WebElement upgradeNotice;
    @FindBy(xpath = "//a[text()='Click here to verify now']")
    public WebElement verifyMessage;
    @FindBy(linkText = "Create new post")
    public WebElement createNewPost;

    public void switchWindowAndLogin(String userNamePremVerified, String passwordPremVerified) {
        for (String handle : Driver.get().getWindowHandles()) {
            Driver.get().switchTo().window(handle);
            if (Driver.get().getTitle().equals("Login | Circleboom")) {
                break;
            }
        }
        userName.sendKeys(ConfigurationReader.get(userNamePremVerified));
        password.sendKeys(ConfigurationReader.get(passwordPremVerified));
        submit.click();
    }

}
