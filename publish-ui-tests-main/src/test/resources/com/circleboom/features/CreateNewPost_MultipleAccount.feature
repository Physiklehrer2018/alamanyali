@Regression @CreateNewPost_MultipleAccount
Feature: Create New Post with Multiple Account

  Background:
    Given I am on the home page
    When I login right credentials for premium verified user

  @postMessageMultipleAccount
  Scenario: User as a premium verified user should land on my account page as a premium user
    Then I should go to my account page as verified premium user

  @postMessageMultipleAccount
  Scenario: User as a premium verified user should land on Design and schedule page
    When I click create new post button
    Then I should land on Design and schedule page


  @postMessageMultipleAccount
  Scenario: User as a premium verified user should be able to select multiple account

    When I click create new post button
    When I click select account link
    Then I should be able to select multiple account


  @postMessageMultipleAccount
  Scenario: User as a premium verified user should be see Post it button, Add to my queue button, Schedule button,upload media,giphy,unsplash

    When I click create new post button
    When I click select account link
    When I select multiple account
    When I started to enter my message
    Then I should be able to see post preview screen with clickable Post it button
    Then I should be able to see post preview screen with clickable Add to my queue button
    Then I should be able to see post preview screen with clickable Schedule button
    Then I should be able to see upload media
    Then I should be able to see giphy
    Then I should be able to see unsplash
    Then I should be able to see design on canva

  @postMessageMultipleAccount
  Scenario:User as a premium verified user should be able to Post my message

    When I click create new post button
    When I click select account link
    When I select multiple account
    When I started to enter my message
    When click to post my message
    Then I should see a popup shows my message sent

  @AddToMyQueue
  Scenario: Add my message to My Queue multiple account

    When I click create new post button
    When I click select account link
    When I select multiple account
    When I started to enter my message
    When I click Add to my queue button
    Then I should see Time Queue Settings

  @ScheduleMyMessage
  Scenario: Schedule my message multiple account
    When I click create new post button
    When I click select account link
    When I select multiple account
    When I started to enter my message
    When I click Schedule it button
    Then I should see SCHEDULE YOUR POST box
    And I should see send time box
    And I should see send date box
    And I should see clickable cancel button
    And I should see clickable Schedule it button
    When I enter date
    And I enter time
    When I click Schedule it button in Schedule your post box
    Then I should see a popup shows my message scheduled

  @PostMessageSadPath
  Scenario: New Post rules sad path multiple account
    When I click create new post button
    When I click select account link
    When I select multiple account
    When I enter more than 265 character
    Then I should get this post will not be posted to Twitter due to its length notification in Post Prewiev
    Then I should see Post Preview section disabled
    ##And I should see the link with text Click here to learn more
    ##And I should see three dots notification
    ##When I click three dots notification
    ##Then I should see the rule
    ##When I click the learn more link
    ##Then I should lend on help page







