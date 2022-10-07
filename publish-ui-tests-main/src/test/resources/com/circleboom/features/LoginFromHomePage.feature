@Regression @LoginFromHomePage
Feature: Login from HomePage to Publish



  Background:
    Given I am on the home page


  Scenario:
    Then I should land on main page


  @postMessage
  Scenario: Login to Publish from homepage as free verified user
    When I login right credentials for free verified user
    Then I should go to my account page as verified free user

  @premVerified
  Scenario: Login to Publish from homepage as verified premium user
    When I login right credentials for premium verified user
    Then I should go to my account page as verified premium user

