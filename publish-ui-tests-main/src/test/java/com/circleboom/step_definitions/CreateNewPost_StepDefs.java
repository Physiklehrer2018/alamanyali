package com.circleboom.step_definitions;

import com.circleboom.pages.CreateNewPost;
import com.circleboom.pages.LoginFromHomePage;
import com.circleboom.utilities.ConfigurationReader;
import com.circleboom.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class CreateNewPost_StepDefs {
    CreateNewPost createNewPost = new CreateNewPost();
    WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(15));
    Actions action = new Actions(Driver.get());
    Robot robot = new Robot();
    Faker faker = new Faker();

    public CreateNewPost_StepDefs() throws AWTException {
    }


    @When("I click create new post button")
    public void i_click_create_new_post_button() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(createNewPost.menuToggle));
        Thread.sleep(2000);
        action.moveToElement(createNewPost.menuToggle).build().perform();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(createNewPost.createNewPost)).click();

    }

    @Then("I should land on Design and schedule page")
    public void i_should_land_on_Design_and_schedule_page() {
        Assert.assertEquals("Circleboom Publish", Driver.get().getTitle());
    }

    @When("I click select account link")
    public void i_click_select_account_link()  {
        wait.until(ExpectedConditions.visibilityOf (createNewPost.selectAccount)).click();
    }

    @And("I select an account")
    public void i_select_an_account()  {
                if (createNewPost.getMultibleAccountList().size()==1)
        {
                wait.until(ExpectedConditions.elementToBeClickable(createNewPost.getMultibleAccountSelectList().get(0))).click();
        }
        wait.until(ExpectedConditions.visibilityOf(createNewPost.done)).click();
    }

    @And("I select an Twitter account")
    public void     iSelectAnTwitterAccount() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.selectPlatformTwitterCircleboom11));
        if (!createNewPost.selectPlatformTwitterCircleboom11.isDisplayed()){
        wait.until(ExpectedConditions.visibilityOf(createNewPost.selectPlatformTwitterCircleboom11)).click();
        }
        createNewPost.done.click();
    }

    @Then("I should be able to select multiple account")
    public void iShouldBeAbleToSelectMultipleAccount() {
        Assert.assertTrue("'Select All' button ıs not displayed",(createNewPost.getMultibleAccountSelectList().size()>=2));
    }

    @When("I select multiple account")
    public void iSelectMultipleAccount() throws InterruptedException {

        if (createNewPost.getMultibleAccountList().size()==1)
        {
            for (int i=0; i<createNewPost.getMultibleAccountSelectList().size();i++) {
                wait.until(ExpectedConditions.elementToBeClickable(createNewPost.getMultibleAccountSelectList().get(i))).click();
            }
        }
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(createNewPost.done)).click();
    }

    @Given("I selected an account")
    public void i_selected_an_account() {
    }

    @When("I started to enter my message")
    public void i_started_to_enter_my_message() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.textArea)).click();

        createNewPost.textArea.sendKeys("Love is an elixir; a human lives with love, " +
                "is made happy by love and makes those around him or her happy with love. "+faker.name().firstName()+" "+faker.name().lastName());
    }

    @Then("I should be able to see upload media")
    public void iShouldBeAbleToSeeUploadMedia() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.uploadMedia));
        Assert.assertTrue("'Upload media' button is not displayed",createNewPost.uploadMedia.isDisplayed());
    }

    @When("I click upload media")
    public void iClickUploadMedia() {

        wait.until(ExpectedConditions.visibilityOf(createNewPost.uploadMedia)).click();
    }

    @And("I click click here button")
    public void iClickClickHereButton() throws InterruptedException {
        String target = System.getProperty("user.dir") + "/Cant find.jpg";
        wait.until(ExpectedConditions.visibilityOf(createNewPost.clickHereUploadMedia)).click();
        Driver.get().switchTo()
                .activeElement()
                .sendKeys(target);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }

    @Then("I should be able to upload media")
    public void iShouldBeAbleToUploadMedia() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.getUploadMediaList().get(0)));
        Assert.assertTrue("couldn't be uploaded",createNewPost.getUploadMediaList().get(0).isDisplayed());
    }

    @Then("I should be able to see giphy")
    public void iShouldBeAbleToSeeGiphy() {
        Assert.assertTrue("'Giphy' button is not displayed", createNewPost.giphy.isDisplayed());
    }

    @Then("I should be able to see unsplash")
    public void iShouldBeAbleToSeeUnsplash() {
        Assert.assertTrue("'Unsplash' button is not displayed",createNewPost.unsplash.isDisplayed());
    }

    @Then("I should be able to see design on canva")
    public void iShouldBeAbleToSeeDesignOnCanva() {
        Assert.assertTrue("'Design on canva' button is not displayed",createNewPost.designOnCanva.isDisplayed());
    }

    @Then("I should be able to post my message")
    public void iShouldBeAbleToPostMyMessage() {
         wait.until(ExpectedConditions.elementToBeClickable(createNewPost.postButton)).click();
        createNewPost.continueWithFreePlan.click();
    }

    @Then("I should be able to add to my queue my message")
    public void iShouldBeAbleToAddToMyQueueMyMessage() {
        //createNewPost.clickAddToMyQueue();
        Assert.assertTrue(createNewPost.addToMyQueue.isDisplayed());
    }

    @Then("I should be able to schedule my message")
    public void iShouldBeAbleToScheduleMyMessage() {
       Assert.assertTrue("'Schedule it' button is not displayed",createNewPost.scheduleIt.isDisplayed());
    }

    @When("I enter more than 265 character")
    public void i_enter_more_than_265_character() {
       wait.until(ExpectedConditions.elementToBeClickable(createNewPost.textArea));
       createNewPost. textArea.sendKeys("Love is an elixir; a human lives with love, is made happy by love and makes those around him or her happy with love. In the vocabulary of humanity, love is life; we feel and sense each other with love. God Almighty has not created a stronger relation than love, this chain that binds humans one to another. In fact, the Earth is nothing but a ruin without love to keep it fresh and alive. Jinn and humans have sultans; bees, ants and termites have their queens; for each of these there is a throne. Kings and queens are come to power in different ways, and then they ascend their thrones. Love is the sultan that reigns on the throne of our hearts, with no power struggle being involved. The tongue and lips, the eyes and the ears only have a value as long as they carry the flag of love, yet love is only valuable in and of itself. The heart, the pavilion of love, is priceless because of the love it carries. Castles can be conquered without bloodshed merely by waving flags of love in front of them. Sultans become soldiers of affection when conquered by the soldiers of love."+faker.name().firstName()+" "+faker.name().lastName());
    }

    @Then("I should get This message will not be posted notification")
    public void iShouldGetThisMessageWillNotBePosted() {
        Assert.assertTrue(Driver.get().getPageSource().contains("This message will not be posted"));
    }

    @Then("The buttons on Post Preview section disabled")
    public void the_buttons_on_Post_Preview_section_disabled() {
        Assert.assertFalse(createNewPost.disabledPost.isEnabled());
        Assert.assertFalse(createNewPost.disabledScheduleIt.isEnabled());
        Assert.assertFalse(createNewPost.disabledAddToMyQueue.isEnabled());
    }

    @Then("I should see via Circleboom note added at the end of message")
    public void i_should_see_via_Circleboom_note_added_at_the_end_of_message() {
       //  Assert.assertTrue("'via Circleboom' is not displayed", createNewPost.viaCircleboomNote.isDisplayed());
    }

    @Then("I should see the link with text Click here to learn more")
    public void i_should_see_the_link_with_Click_here_to_learn_more() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.clickHereToLearnMore));
        Assert.assertTrue(createNewPost.clickHereToLearnMore.isDisplayed());
    }

    @Then("When I click three dots notification I should see the rule")
    public void when_I_click_three_dots_notification_I_should_see_the_rule() {
        createNewPost.clickHereToLearnMore.click();
        System.out.println(createNewPost.clickHereToLearnMore.getText());
    }

    @Then("I should be able to see post preview screen with clickable Post it button")
    public void iShouldBeAbleToSeePostPreviewScreenWithClickablePostItButton() {
        Assert.assertTrue("'Post it' button is not displayed",createNewPost.postButton.isDisplayed());
    }

    @Then("I should be able to see post preview screen with clickable Add to my queue button")
    public void iShouldBeAbleToSeePostPreviewScreenWithClickableAddToMyQueueButton() {
        Assert.assertTrue("'Add to My Queue' button is not displayed",createNewPost.addToMyQueue.isEnabled());
    }

    @Then("I should be able to see post preview screen with clickable Schedule button")
    public void iShouldBeAbleToSeePostPreviewScreenWithClickableScheduleButton() {
        Assert.assertTrue("'schedule it' button is not displayed",createNewPost.scheduleIt.isEnabled());
    }

    @When("click to post my message")
    public void clickToPostMyMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(createNewPost.postButton)).click();
    }

    @When("I click CONTINUE WITH FREE PLAN button")
    public void iClickCONTINUEWITHFREEPLANButton() {
        createNewPost.continueWithFreePlan.click();
    }

    @Then("I should see CONTINUE WITH FREE PLAN button")
    public void iShouldSeeCONTINUEWITHFREEPLANButton() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.continueWithFreePlan));
        Assert.assertTrue("'CONTINUE WITH FREE PLAN button is not displayed'",createNewPost.continueWithFreePlan.isEnabled());
    }

    @Then("I should see clickable UPGRADE button")
    public void iShouldSeeClickableUPGRADEButton() {
        Assert.assertTrue("'UPGRADE button' is not displayed",createNewPost.upgradeAfterClickWithFreeAccount.isEnabled());
    }

    @Then("I should see a popup shows my message sent")
    public void iShouldSeeAPopupShowsMyMessageSent() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.sentMessage));
        Assert.assertTrue( createNewPost.sentMessage.isDisplayed());
    }

    @Then("I should see Post Preview section disabled")
    public void iShouldSeePostPreviewSectionDisabled() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.postPreviewNotAvailable));
        Assert.assertTrue("'Post Preview section' is not displayed",createNewPost.postPreviewNotAvailable.isDisplayed());
    }

    @Then("I should see three dots notification")
    public void iShouldSeeThreeDotsNotification() {
        Assert.assertTrue("Three dots notification is not displayed",createNewPost.threeDots.isDisplayed());
    }

    @When("I click three dots notification")
    public void iClickThreeDotsNotification() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.threeDots)).click();
    }

    @Then("I should see the rule")
    public void iShouldSeeTheRule() {
        Assert.assertTrue("'the rule' is not displayed",createNewPost.threeDotrule.isDisplayed());
    }

    @Then("I should get this post will not be posted to Twitter due to its length notification in Post Prewiev")
    public void iShouldGetThisPostWillNotBePostedToTwitterDueToItsLengthNotificationInPostPrewiev() {
        Assert.assertTrue(createNewPost.willNotSend.isDisplayed());
        System.out.println(createNewPost.willNotSend.getText());
    }

    @When("I click Add to my queue button")
    public void iClickAddToMyQueueButton() {
       wait.until(ExpectedConditions.elementToBeClickable(createNewPost.addToMyQueue)).click();
    }

    @Then("I should see Time Queue Settings")
    public void iShouldSeeTimeQueueSettings() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.showQueueSettingsButton));
        Assert.assertTrue("'Time Queue Settings' is not displayed",createNewPost.showQueueSettingsButton.isDisplayed());
    }

    @When("I click the learn more link")
    public void iClickTheLearnMoreLink() {
        createNewPost.clickHereToLearnMore.click();
    }

    @Then("I should lend on help page")
    public void iShouldLendOnHelpPage() {
        for (String handle : Driver.get().getWindowHandles()) {
            Driver.get().switchTo().window(handle);
            if (Driver.get().getTitle().equals("Getting Started - Circleboom Twitter / Help")) {
                break;
            }
        }
       Assert.assertEquals("Circleboom Help", Driver.get().getTitle());
    }

    @When("I click Schedule it button")
    public void iClickScheduleItButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createNewPost.scheduleIt)).click();
    }

    @Then("I should see SCHEDULE YOUR POST box")
    public void iShouldSeeSCHEDULEYOURPOSTBox() {
       Assert.assertTrue("'SCHEDULE YOUR POST box' is not displayed",createNewPost.scheduleYourPostBox.isEnabled());
    }

    @And("I should see send time box")
    public void iShouldSeeSendTimeBox() {
       Assert.assertTrue("'send time box' is not displayed",createNewPost.sendTime.isDisplayed());
    }

    @And("I should see send date box")
    public void iShouldSeeSendDateBox() {
       Assert.assertTrue("'send date box' is not displayed",createNewPost.sendDate.isDisplayed());
    }

    @And("I should see clickable cancel button")
    public void iShouldSeeClickableCancelButton() {
        Assert.assertTrue("'Cancel Button' is not displayed",createNewPost.cancelButton.isDisplayed());
    }

    @And("I should see clickable Schedule it button")
    public void iShouldSeeClickableScheduleItButton() {
       Assert.assertTrue("'Schedule it button' is not displayed",createNewPost.scheduleItFor.isEnabled());
    }

    @When("I enter date and time")
    public void iEnterDateAndTime() {
        createNewPost.sendDate.click();
        createNewPost.sendTime.click();
    }

    @Then("I should see a popup shows my message scheduled")
    public void iShouldSeeAPopupShowsMyMessageScheduled() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.yourPostHasBeenScheduledAlert));
        Assert.assertTrue(createNewPost.yourPostHasBeenScheduledAlert.isDisplayed());
    }

    @When("I click Schedule it button in Schedule your post box")
    public void iClickScheduleItButtonInScheduleYourPostBox() {
         wait.until(ExpectedConditions.visibilityOf(createNewPost.scheduleItInScheduleYourPostBox)).click();
    }

    @Then("I should see Todays Post on first page")
    public void iShouldSeeTodaysPostOnFirstPage() {
        Assert.assertTrue(Driver.get().getPageSource().contains(" Todays Posts"));

    }

    @When("I enter date")
    public void iEnterDate() throws InterruptedException {

        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(createNewPost.sendDate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(createNewPost.calendarToday)).click();
    }

    @And("I enter time")
    public void iEnterTime() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.sendTime)).click();
        wait.until(ExpectedConditions.visibilityOf(createNewPost.calendarTime23)).click();
        wait.until(ExpectedConditions.visibilityOf(createNewPost.calendarMinute55)).click();
    }

    @Then("I should see Your post has been posted successfully message")
    public void iShouldSeeYourPostHasBeenPostedSuccessfullyMessage() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.postedSuccesfull));
        Assert.assertTrue("'Your post has been posted successfully' message is not displayed",createNewPost.postedSuccesfull.isDisplayed());
    }

    @Then("I should see a popup Missing Queue Settings")
    public void iShouldSeeAPopupMissingQueueSettings() {
        wait.until(ExpectedConditions.visibilityOf(createNewPost.message_Missing_Queue_Settings));
        Assert.assertTrue("'Missing Queue Settings' message is not displayed",createNewPost.message_Missing_Queue_Settings.isDisplayed());
    }

    @Then("I should land on main page")
    public void iShouldLandOnMainPage() {
        Assert.assertEquals("Login | Circleboom", Driver.get().getTitle());
    }
}
