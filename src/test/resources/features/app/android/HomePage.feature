Feature: Home Page Testing App

 @android @final
  Scenario: Verify the version code and version name display on home page
    Given [Home Page] App should open successfully
    Then  [Home Page] Verify the Version Code version code : 106
    And   [Home Page] Verify the Version Name version name : 12.0

   @android @final
  Scenario: Verify the Immediate button and its feature is working as requirement
    Given [Home Page] App should open successfully
    Then [Home Page] Verify the Immediate Update button is visible
    When [Home Page] User tap on Immediate Update Button
    When [Home Page] User tap on Button 1
    Then [Home Page] Verify that Result 1 is displayed
    When [Home Page] User tap on Button 2
    Then [Home Page] Verify that Result 2 is displayed
    When [Home Page] User tap on Button 3
    Then [Home Page] Verify that Result 3 is displayed

  @android @final
  Scenario: Verify the Flexible button and its feature is working as requirement
    Given [Home Page] App should open successfully
    Then [Home Page] Verify the Flexible Update button is visible
    When [Home Page] User tap on Flexible Update Button
    When [Home Page] User tap on Button 1
    Then [Home Page] Verify that Result 1 is displayed
    When [Home Page] User tap on Button 2
    Then [Home Page] Verify that Result 2 is displayed
    When [Home Page] User tap on Button 3
    Then [Home Page] Verify that Result 3 is displayed
