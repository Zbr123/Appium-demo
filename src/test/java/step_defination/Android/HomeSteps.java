package step_defination.Android;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Condition;
import core.utils.AndroidCore.AndroidDriverSetup;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import pages.Page;

import java.util.List;
import java.util.Map;

public class HomeSteps extends Page {
    @When("[Home Page] App should open successfully")
    public void UserIsOnHomePageTitleTestingApp() {
        Assert.assertTrue(getHomePage().getTitle().isDisplayed());
    }

//    @And("^\\[Home Page\\] Verify the Version Code (.*)$")
//    public void verifyTheCode(String versionCode) {
//        Assert.assertEquals(getHomePage().getVersionCode().getText(), versionCode, "Verify Version Code");
//    }
//
//    @And("^\\[Home Page\\] Verify the Version Name (.*)$")
//    public void verifyTheName(String versionName) {
//        Assert.assertEquals(getHomePage().getVersionName().getText(), versionName, "Verify Version Name");
//        getHomePage().getVersionName().isDisplayed();
//    }
//
//    @When("\\[Home Page\\] User tap on Button (.*)$")
//    public void homePageUserTapOnButtonXxx(int button) {
//        getHomePage().getButton(button).click();
//    }
//
//    @Then("[Home Page] Verify the Immediate Update button is visible")
//    public void homePageVerifyTheImmediateUpdateButtonIsVisible() {
//        getHomePage().getImmediateButton().isDisplayed();
//    }
//
//    @When("[Home Page] User tap on Immediate Update Button")
//    public void homePageUserTapOnImmediateUpdateButton() {
//        getHomePage().getImmediateButton().click();
//    }

//    @Then("[Home Page] Verify the three button options")
//    public void updatePageVerifyTheThreeButtonOptions(DataTable dataTable) {
//        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> itemData : rows) {
//            if (itemData.containsKey("Button1")) {
//                $(getHomePage().getButton(1)).shouldHave(Condition.exactText(itemData.get("Button1")));
//            }
//            if (itemData.containsKey("Button2")) {
//                $(getHomePage().getButton(2)).shouldHave(Condition.exactText(itemData.get("Button2")));
//            }
//            if (itemData.containsKey("Button3")) {
//                $(getHomePage().getButton(3)).shouldHave(Condition.exactText(itemData.get("Button3")));
//            }
//        }
//    }

//    @Then("\\[Home Page\\] Verify that Result (.*) is displayed$")
//    public void homePageVerifyThatResul1IsVisible(String number) {
//        Assert.assertEquals(getHomePage().getResult().getText(), "RESULT " + number, "Verify Result with Number");
//        getHomePage().backButton();
//    }
//
//    @When("[Home Page] User tap on Flexible Update Button")
//    public void homePageUserTapOnFlexibleUpdateButton() {
//        getHomePage().getFlexibleButton().click();
//    }
//
//    @Then("[Home Page] Verify the Flexible Update button is visible")
//    public void homePageVerifyTheFlexibleUpdateButtonIsVisible() {
//        getHomePage().getFlexibleButton().isDisplayed();
//    }

    @Then("[Home Page] Verify that The App title is displayed on home page")
    public void homePageVerifyThatTheAppTitleIsDisplayedOnHomePage() {
        Assert.assertEquals(getHomePage().getTitleDemoApp().getText(), "The Apvp", "Verify Result");
    }

    @When("^I tap on (.*) tab$")
    public void iTapOnXxxTab(String name) {
        getHomePage().getTabNameDemoApp(name).click();
    }

    @And("I press back button")
    public void iPressBackButton() {
        getHomePage().backButton();
    }

    @When("^I type (.*) in text field on echo box screen$")
    public void iTypeXxxInTextFieldOnEchoBoxScreen(String text) {
        getHomePage().getTextFieldDemoApp().sendKeys(text);
    }

    @When("I tap on save button")
    public void iTapOnSaveButton() {
        getHomePage().getSaveButtonDemoApp().click();
    }

    @Then("^I should see (.*) echoed text on echo box screen$")
    public void iShouldSeeXxxEchoedTextOnEchoBoxScreen(String echo) {
        Assert.assertEquals(getHomePage().getEchoedTextDemoApp(echo).getText(), echo, "Verify Result");
    }

    @When("^I tap on (.*) option$")
    public void iTapOnXxxOption(String option) {
        getHomePage().getOptionDemoApp(option).click();
    }

    @When("^I scroll to (.*) accessibility id$")
    public void iScrollToXxxAccessibilityId(String id) {
        getHomePage().scrollToElementWithAccessibilityId(id);

    }

    @And("I accept popup")
    public void iAcceptPopup() {
        Alert alert = AndroidDriverSetup.getAndroidDriver().switchTo().alert();
        alert.accept();
    }

    @And("I dismiss popup")
    public void iDismissPopup() {
        Alert alert = AndroidDriverSetup.getAndroidDriver().switchTo().alert();
        alert.dismiss();
    }

    @Then("^I should see popup$")
    public void iShouldSeePopup() {
        try {
            // Attempt to switch to the alert
            Alert alert = AndroidDriverSetup.getAndroidDriver().switchTo().alert();
            // If successful, we assume the popup is displayed
            System.out.println("Popup is displayed with message: " + alert.getText());
        } catch (NoAlertPresentException e) {
            // If an exception is caught, it means no popup is present
            throw new AssertionError("Expected a popup, but none was displayed.");
        }
    }

    @And("^I wait (\\d+) seconds$")
    public void iWaitSeconds(int seconds) {
        try {
            // Convert seconds to milliseconds and wait
            Thread.sleep(seconds * 1000L);
            System.out.println("Waited " + seconds + " seconds.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Wait was interrupted.");
        }
    }
}