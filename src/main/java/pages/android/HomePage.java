package pages.android;

import core.utils.AndroidCore.AndroidDriverSetup;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.Page;

public class HomePage {

    private String titleXpath = "//android.view.ViewGroup/android.widget.TextView";
    private String immediateButtonID = "com.meritnation.store.testingapp:id/btn1";
    private String flexibleButtonID = "com.meritnation.store.testingapp:id/btn2";
    private String versionCodeID = "com.meritnation.store.testingapp:id/ver_code";
    private String versionNameID = "com.meritnation.store.testingapp:id/ver_name";
    private String resultText = "com.meritnation.store.testingapp:id/txt";
    private String getTitle = "//android.widget.TextView[@text='The App']";
    private String TextFieldDemoApp = "messageInput";
    private String getSaveButton = "//android.view.ViewGroup[@content-desc='messageSaveBtn']/android.widget.TextView";
    private String chromeSearch = "q";


    public HomePage(Page page) {
    }

    public WebElement getTitle() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.xpath(titleXpath)));
    }

    public WebElement getVersionCode() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(versionCodeID)));
    }

    public WebElement getVersionName() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(versionNameID)));
    }

    public WebElement getButton(int num) {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id("com.meritnation.store.testingapp:id/btn" + num)));
    }

    public WebElement getImmediateButton() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(immediateButtonID)));
    }

    public WebElement getFlexibleButton() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(flexibleButtonID)));
    }

    public WebElement getResult() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(resultText)));
    }

    public void backButton() {
        AndroidDriverSetup.getAndroidDriver().navigate().back();
    }

    public WebElement getTitleDemoApp() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.xpath(getTitle)));
    }

    public WebElement getTabNameDemoApp(String name) {
        return (AndroidDriverSetup.getAndroidDriver().findElementByAccessibilityId(name));
    }

    public WebElement getTextFieldDemoApp() {
        return (AndroidDriverSetup.getAndroidDriver().findElementByAccessibilityId(TextFieldDemoApp));
    }

    public WebElement getSaveButtonDemoApp() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.xpath(getSaveButton)));
    }

    public WebElement getEchoedTextDemoApp(String echo) {
        return (AndroidDriverSetup.getAndroidDriver().findElementByAccessibilityId(echo));
    }

    public WebElement getOptionDemoApp(String option) {
        return (AndroidDriverSetup.getAndroidDriver().findElementByAccessibilityId(option));
    }

    public void scrollToElementWithAccessibilityId(String accessibilityId) {
        String uiAutomatorString = String.format("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"%s\"))", accessibilityId);
        AndroidDriverSetup.getAndroidDriver().findElement(MobileBy.AndroidUIAutomator(uiAutomatorString));
    }

    public WebElement getChromeSearchDemoApp() {
        return AndroidDriverSetup.getAndroidDriver().findElement(By.xpath("//android.view.View[2]/android.view.View[2]/android.view.View[1]/android.view.View[1]/android.widget.EditText"));
    }
}
