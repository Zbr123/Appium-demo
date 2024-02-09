package pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

import static pages.Page.androidconfig;

public class HomePage {

    private static AppiumDriver<MobileElement> driver;
    private String titleXpath = "//android.view.ViewGroup/android.widget.TextView";
    private String immediateButtonID = "com.meritnation.store.testingapp:id/btn1";
    private String flexibleButtonID = "com.meritnation.store.testingapp:id/btn2";
    private String versionCodeID ="com.meritnation.store.testingapp:id/ver_code";
    private String versionNameID ="com.meritnation.store.testingapp:id/ver_name";
    private String resultText ="com.meritnation.store.testingapp:id/txt";

    public HomePage() {
        if (driver == null) {
            initializeDriver();
        }
    }

    private void initializeDriver() {
        String appDir = System.getProperty("user.dir") + "/apk/";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, androidconfig.getProperty("platformName"));
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidconfig.getProperty("platformVersion"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, androidconfig.getProperty("deviceName"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, androidconfig.getProperty("automationName"));
        caps.setCapability(MobileCapabilityType.APP, appDir + androidconfig.getProperty("androidapk"));
        caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, "true");
        caps.setCapability(MobileCapabilityType.ACCEPT_INSECURE_CERTS, "true");
        caps.setCapability(MobileCapabilityType.FULL_RESET, "true");
        caps.setCapability(MobileCapabilityType.NO_RESET, "false");

        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public MobileElement getTitle() {
        return driver.findElement(By.xpath(titleXpath));
    }

    public MobileElement getVersionCode() {
        return driver.findElement(By.id(versionCodeID));
    }

    public MobileElement getVersionName() {
        return driver.findElement(By.id(versionNameID));
    }

    public MobileElement getButton(String button) {
        return driver.findElement(By.id("com.meritnation.store.testingapp:id/btn" + button));
    }

    public MobileElement getImmediateButton() {
        return driver.findElement(By.id(immediateButtonID));
    }

    public MobileElement getFlexibleButton() {
        return driver.findElement(By.id(flexibleButtonID));
    }

    public MobileElement getResult() {
        return driver.findElement(By.id(resultText));
    }

    public void backButton() {
        driver.navigate().back();
    }

    public static void main(String[] args) {
        HomePage homePage = new HomePage();

        // Example actions using the HomePage
        MobileElement titleElement = homePage.getTitle();
        System.out.println("Title: " + titleElement.getText());

        // Perform more actions as per your test requirements...

        // Close the app or driver session at the end of your tests
        driver.quit();
    }
}
