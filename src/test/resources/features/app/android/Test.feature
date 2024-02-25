#Feature: Home Page Testing App
#
#  @android
#  Scenario: Verify the app opens and see home screen
#    Given [Home Page] App should open successfully
#    Then [Home Page] Verify that The App title is displayed on home page

#  @android
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
#  @android
#  Scenario: Should be able to add text in echo box and assert the result
#    Given [Home Page] App should open successfully
#    When I tap on Echo Box tab
#    And I type hello world in text field on echo box screen
#    And I tap on save button
#    Then I should see hello world echoed text on echo box screen
#
#
#  @android
#  Scenario: Should be able to scroll through options to select below options
#    Given [Home Page] App should open successfully
#    When I tap on List Demo tab
#    And I scroll to Stratus accessibility id
#    And I tap on Stratus option
#    And I wait 4 seconds
#    Then I should see popup
#
#  @android
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
#    And I dismiss pon                                                                                                                        pup