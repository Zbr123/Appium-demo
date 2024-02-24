package core.utils.AndroidCore;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AndroidDriverSetup {
    private static final ThreadLocal<AndroidDriver<AndroidElement>> androidDriver = new ThreadLocal<>();

    public static synchronized void androidDriver(String port) throws IOException {
        Properties props = new Properties();
        props.load(Files.newInputStream(Paths.get(System.getProperty("user.dir") + "/src/test/java/config/androidconfig.properties")));

        DesiredCapabilities capabilities = CapabilitiesGenerator.getAndroidCapabilities();
        AndroidDriver<AndroidElement> driver;

        if (Boolean.parseBoolean(props.getProperty("browserstack"))) {
            // Adjust these capabilities to match your BrowserStack needs
            capabilities.setCapability("browserstack.user", props.getProperty("browserStackUser"));
            capabilities.setCapability("browserstack.key", props.getProperty("accessKey"));
            // Add more BrowserStack specific capabilities as needed
            String browserStackURL = "http://hub-cloud.browserstack.com/wd/hub";
            driver = new AndroidDriver<>(new URL(browserStackURL), capabilities);
        } else {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:" + port + "/wd/hub"), capabilities);
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        androidDriver.set(driver);
        System.out.println("Android driver selected:\n");
    }

    public static void quitAndroidDriver() {
        try {
            if (androidDriver.get() != null) {
                androidDriver.get().quit();
                androidDriver.remove(); // Use remove() instead of setting it to null for ThreadLocal
            } else {
                System.out.println("TestCases Flow");
            }
        } catch (Exception e) {
            System.out.println("Error While Executing the Quite Driver");
        }
    }

    public static AndroidDriver<AndroidElement> getAndroidDriver() {
        if (androidDriver.get() != null) {
            return androidDriver.get();
        } else {
            throw new IllegalStateException("Driver has not been initialized. " +
                    "Please use OS platform tag on test scenario and call WebDriverFactory before use this method");
        }
    }
}
