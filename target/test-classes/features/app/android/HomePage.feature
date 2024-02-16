Feature: Home Page Testing App

# @android @final
#  Scenario: Verify the version code and version name display on home page
#    Given [Home Page] App should open successfully
#    Then  [Home Page] Verify the Version Code version code : 106
#    And   [Home Page] Verify the Version Name version name : 12.0
#
#   @android @final
#  Scenario: Verify the Immediate button and its feature is working as requirement
#    Given [Home Page] App should open successfully
#    Then [Home Page] Verify the Immediate Update button is visible
#    When [Home Page] User tap on Immediate Update Button
#    When [Home Page] User tap on Button 1
#    Then [Home Page] Verify that Result 1 is displayed
#    When [Home Page] User tap on Button 2
#    Then [Home Page] Verify that Result 2 is displayed
#    When [Home Page] User tap on Button 3
#    Then [Home Page] Verify that Result 3 is displayed
#
#  @android @final
#  Scenario: Verify the Flexible button and its feature is working as requirement
#    Given [Home Page] App should open successfully
#    Then [Home Page] Verify the Flexible Update button is visible
#    When [Home Page] User tap on Flexible Update Button
#    When [Home Page] User tap on Button 1
#    Then [Home Page] Verify that Result 1 is displayed
#    When [Home Page] User tap on Button 2
#    Then [Home Page] Verify that Result 2 is displayed
#    When [Home Page] User tap on Button 3
#    Then [Home Page] Verify that Result 3 is displayed


#  @android @final
#  Scenario: Verify the app opens and see home screen
#    Given [Home Page] App should open successfully
#    Then [Home Page] Verify that The App title is displayed on home page
#
#  @android @final
#  Scenario: Should be able to navigate through different tabs
#    Given [Home Page] App should open successfully
#    When I tap on Echo Box tab
#    And I press back button
#    When I tap on Login Screen tab
#    And I press back button
#    When I tap on Clipboard Demo tab
#    And I press back button
#    Then [Home Page] Verify that The App title is displayed on home page
#
#
#  @android @final
#  Scenario: Should be able to add text in echo box and assert the result
#    Given [Home Page] App should open successfully
#    When I tap on Echo Box tab
#    And I type hello world in text field on echo box screen
#    And I tap on save button
#    Then I should see hello world echoed text on echo box screen
#
#
#  @android @final
#  Scenario: Should be able to scroll through options to select below options
#    Given [Home Page] App should open successfully
#    When I tap on List Demo tab
#    And I scroll to Stratus accessibility id
#    And I tap on Stratus option
#    And I wait 4 seconds
#    Then I should see popup
#
#  @android @final
#  Scenario: Should be able to accept and dismiss popup
#    Given [Home Page] App should open successfully
#    When I tap on List Demo tab
#    And I scroll to Stratus accessibility id
#    And I tap on Stratus option
#    And I wait 4 seconds
#    Then I should see popup
#    And I accept popup
#    And I tap on Stratus option
#    And I wait 4 seconds
#    Then I should see popup
#    And I dismiss popup


  @android @final
  Scenario: Should open chrome and navigate to google.com
    Given I navigate to https://www.google.com
    And I wait 10 seconds
    And I search for Appium automation on chrome