package com.circleboom.pages;

import com.circleboom.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateNewPost {

    public CreateNewPost() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//a[contains(@class,'sidenav_new_post')]")
    public WebElement createNewPost;
    @FindBy(xpath = "//span[contains(@class,'caret')]")
    public WebElement selectAccount;
    @FindBy(xpath = "//div[contains(@class,'selector')] | //div[contains(@class,'selector active')]")
    public WebElement selectPlatformTwitterCircleboom11;
    @FindBy(xpath = "//span[.='Free']")
    public WebElement FreeTag;
    @FindBy(xpath = "//span[.='Premium']")
    public WebElement PremiumTag;
    @FindBy(xpath = "//button[contains(@class,'done')]")
    public WebElement done;
    @FindBy(xpath = "//textarea[@id='post_input']")
    public WebElement textArea;
    @FindBy(xpath = "//button[@class='post_post_btn__4hBqd post_post_btn_instant__InpL2']")
    public WebElement postButton;
    @FindBy(xpath = "//*[@class='btn btn-default btn-sm']")
    public WebElement continueWithFreePlan;
    @FindBy(xpath = "//button[contains(@class,'post_post_btn_queue')]")
    public WebElement addToMyQueue;
    @FindBy(xpath = "//button[contains(@class,'post_btn_schedule')]")
    public WebElement scheduleIt;
    @FindBy(xpath = "//strong[contains(text(),'Time/Queue Settings')]")
    public WebElement showQueueSettingsButton;
    @FindBy(xpath = "(//button[contains(@class,'uploader_handler')])[1]")
    public WebElement uploadMedia;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'media_list')]/div")})
    private List<WebElement> uploadMediaList;
    @FindBy(xpath = "//div[contains(@class,'upload_area')]")
    public WebElement clickHereUploadMedia;
    @FindBy(xpath = "(//button[contains(@class,'uploader_handler')])[2]")//??? true, because it is first element
    public WebElement giphy;
    @FindBy(xpath = "(//button[contains(@class,'uploader_handler')])[3]")// wrong??
    public WebElement unsplash;
    @FindBy(xpath = "(//button[contains(@class,'uploader_handler')])[4]")// wrong???
    public WebElement designOnCanva;
    @FindBy(xpath = "//*[contains(text(), 'This message will not be posted to ')]")
    public WebElement willNotBePostedNotification;
    @FindBy(xpath = "//*[@id='__next']/div[1]/main/section/div[2]/div[2]/div/div[3]/div/button[1]")
    public WebElement disabledAddToMyQueue;
    @FindBy(xpath = "//*[@id='__next']/div[1]/main/section/div[2]/div[2]/div/div[3]/div/button[2]")
    public WebElement disabledScheduleIt;
    @FindBy(xpath = "//*[@id='__next']/div[1]/main/section/div[2]/div[2]/div/div[3]/div/button[3]")
    public WebElement disabledPost;
    @FindBy(linkText = "Click here to learn more")
    public WebElement clickHereToLearnMore;
    @FindBy(xpath = "//span[.='Your post has been posted successfully']")
    public WebElement sentMessage;
    @FindBy(xpath = "//*[@id='__next']/div[1]/main/section/div[2]/div[1]/div[2]/div[2]/div[2]/div/button")
    public WebElement threeDots;
    @FindBy(xpath = "//div[contains(@class,'schedule_error')]")
    public WebElement threeDotrule;
    @FindBy(xpath = "//button[contains(@class,'post_close_scheduler')]")
    public WebElement cancelButton;
    @FindBy(xpath = "//a[contains(@class,'usermenu_upgrade')]")
    public WebElement upgradeAfterClickWithFreeAccount;
    @FindBy(xpath = "//p[.='You cannot send more than 280 characters to Twitter']")
    public WebElement willNotSend;
    @FindBy(xpath = "//div[@class='react-datepicker__month']/div/div[contains(@class,'today')]")
    public WebElement  sendDate;
    @FindBy(xpath = "//input[@class='rc-time-picker-input']")
    public WebElement sendTime;
    @FindBy(xpath = "//h2[.='SCHEDULE YOUR POST']")
    public WebElement scheduleYourPostBox;
    @FindBy(xpath = "//button[text()='Schedule it for ']")
    public WebElement scheduleItInScheduleYourPostBox;
    @FindBy(xpath = "//span[contains(text(),'Your post has been scheduled successfully')]")
    public WebElement yourPostHasBeenScheduledAlert;
    @FindBy(xpath = "//div[@class='rc-time-picker-panel-select']/ul/li[.='23']")
    public WebElement calendarTime23;
    @FindBy(xpath = "//div[@class='rc-time-picker-panel-select']/ul/li[.='55']")
    public WebElement calendarMinute55;
    @FindBy(xpath = "//li[contains(@class,'sidenav_new_post')]/i")
    public  WebElement menuToggle;
    @FindBy(xpath = "//span[.='Your post has been posted successfully']")
    public  WebElement postedSuccesfull;
    @FindBy(xpath = "//h3[.='Missing Queue Settings !']")
    public  WebElement message_Missing_Queue_Settings;
    @FindBy(xpath = "//div[contains(@class,'--today')]")
    public  WebElement calendarToday;
    @FindBy(xpath = "//*[contains(text(),'Schedule it for ')]")
    public  WebElement scheduleItFor;
    @FindBy(xpath = "//span[contains(text(),'No preview available for')]")
    public  WebElement postPreviewNotAvailable;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'handler')]/div")})
    private List<WebElement> MultibleAccountList;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'selector')]")})
    private List<WebElement> MultibleAccountSelectList;



    public List<WebElement> getUploadMediaList() {
        return uploadMediaList;
    }

    public List<WebElement> getMultibleAccountList() {
        return MultibleAccountList;
    }

    public List<WebElement> getMultibleAccountSelectList() {
        return MultibleAccountSelectList;
    }








}