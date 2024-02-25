package core.utils.AndroidCore;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.mdb.android.Android;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static pages.Page.androidconfig;

public class AndroidDriverSetup {
    private static ThreadLocal<AndroidDriver<AndroidElement>> androidDriver = new ThreadLocal<>();
    public static final boolean isBrowserStack = Boolean.parseBoolean(androidconfig.getProperty("browserstack"));
    public static final String AUTOMATE_USERNAME = System.getenv("BROWSERSTACK_USERNAME") == null ? androidconfig.getProperty("browserStackUser") : System.getenv("BROWSERSTACK_USERNAME");
    public static final String AUTOMATE_ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY") == null ? androidconfig.getProperty("accessKey") : System.getenv("BROWSERSTACK_ACCESS_KEY");
    public static final String BROWSERSTACK_URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static synchronized void androidDriver(String port) throws IOException {
        AndroidDriver<AndroidElement> driver;
        if (isBrowserStack)
        driver = new AndroidDriver<AndroidElement>(new URL(BROWSERSTACK_URL), CapabilitiesGenerator.getBrowserStackCapabilitiesx());
        else {
            driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:" + port + "/wd/hub"), CapabilitiesGenerator.getBrowserStackCapabilitiesx());
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        androidDriver.set(driver);
        System.out.println("Android driver selected:\n");
    }

    public static void quitAndroidDriver() {
        try {
            AndroidDriverSetup.getAndroidDriver().removeApp("io.appium.uiautomator2.server");
            AndroidDriverSetup.getAndroidDriver().removeApp("io.appium.uiautomator2.server.test");
            AndroidDriverSetup.getAndroidDriver().removeApp("io.appium.settings");
            AndroidDriverSetup.getAndroidDriver().removeApp("io.appium.unlock");
            AndroidDriverSetup.getAndroidDriver().removeApp("com.bykea.pk");
            AndroidDriverSetup.getAndroidDriver().removeApp("com.bykea.pk.partner");
            AppiumServerRunner.stopServer();
            if (AndroidDriverSetup.androidDriver.get() != null) {
                androidDriver.get().quit();
                androidDriver = null;
            } else {
                System.out.println("TestCases Flow");
            }
        } catch (Exception e) {
            System.out.println("Error While Executing the  Quite Driver");
        }
    }

    public static AndroidDriver<AndroidElement> getAndroidDriver() {
        if (androidDriver.get() != null) {
            return androidDriver.get();
        } else {
            System.out.println("TestCases Flow");
            throw new IllegalStateException("Driver has not been initialized. " +
                    "Please use OS platform tag on test scenario and call WebDriverFactory before use this method");
        }
    }

    public static void moveAppInBackground(){
        AndroidDriverSetup.androidDriver.get().runAppInBackground(Duration.ofSeconds(10));;
    }

    public static void toggleWifi(){
        AndroidDriverSetup.androidDriver.get().toggleWifi();
    }

    public static void killApp() throws InterruptedException {
        AndroidDriverSetup.androidDriver.get().terminateApp("");
    }

    public static void launchApp() throws InterruptedException {
        Thread.sleep(3000);
        try{AndroidDriverSetup.androidDriver.get().activateApp("");}catch (Exception e) {
        }
    }

    public static void killApplicationAndOpenApp() throws InterruptedException {
        // drive.closeApp();
        AndroidDriverSetup.androidDriver.get().terminateApp("co.foree.app.release");
        // start app
        Thread.sleep(10000);
//       driver.launchApp();
        try{AndroidDriverSetup.androidDriver.get().activateApp("co.foree.app.release");}catch (Exception e) {
        }
    }

    public static void verifyValidFingerPrint(){
        AndroidDriverSetup.androidDriver.get().fingerPrint(1);
    }

    public static void verifyInValidFingerPrint(){
        AndroidDriverSetup.androidDriver.get().fingerPrint(2);
    }

    public static void moveAppInForegrounds(String appName) throws InterruptedException{
        if(appName.equalsIgnoreCase("customer")) {
            String appPackage = "com.bykea.pk";
            String screenActivity = "com.bykea.pk.screens.activities.SplashActivity";
            AndroidDriverSetup.androidDriver.get().startActivity(new Activity(appPackage, screenActivity));
        } else if(appName.equalsIgnoreCase("partner")) {
            String appPackage = "com.bykea.pk.partner";
            String screenActivity = "com.bykea.pk.partner.ui.activities.SplashActivity";
            AndroidDriverSetup.androidDriver.get().startActivity(new Activity(appPackage, screenActivity));
        }
    }

    public static void switchToApp(String name) {
        Activity activity;
        String cAppPackage = "com.bykea.pk";
        String pAppPackage = "com.bykea.pk.partner";
        String cAppActivity = "com.bykea.pk.screens.activities.SplashActivity";
        String pAppActivity = "com.bykea.pk.partner.ui.activities.SplashActivity";

        if (name.equalsIgnoreCase("customer")) {
            pAppActivity = getAndroidDriver().currentActivity();
            activity = new Activity(cAppPackage, cAppActivity);
        } else {
            cAppActivity = getAndroidDriver().currentActivity();
            activity = new Activity(pAppPackage, pAppActivity);
        }
        try {
            getAndroidDriver().startActivity(activity);
        } catch (Exception e) {
            getAndroidDriver().activateApp(activity.getAppPackage());
        }
    }

    public static List<DeviceDetails> getDevices(){
        List<DeviceDetails> androidDevices = new Android().getDevices();
        System.out.println("Android Devices list : " + androidDevices);
        return androidDevices;
    }
}
