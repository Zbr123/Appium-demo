package step_defination.Android;

import core.utils.AndroidCore.AndroidDriverSetup;
import core.utils.AndroidCore.CapabilitiesGenerator;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.util.Collection;

import static core.utils.AndroidCore.AndroidDriverSetup.*;
import static core.utils.AndroidCore.AppiumServerRunner.StartAppiumServer;
import static pages.Page.androidconfig;
import static pages.Page.myProp;

public class AndroidSetup {
    public static String PLATFORM_NAME = myProp.getProperty("platformname").toLowerCase();
    public static String PORT = myProp.getProperty("appiumport");

    @Before("@android")
    public void startTestAndroid() throws Exception {
        if (myProp.getProperty("platformname").toString().equals("Android")) {
            PORT = myProp.getProperty("appiumport");
           // APP= androidconfig.getProperty("androidapk");
            StartAppiumServer(PORT);
            androidDriver(PORT);
        }
    }

//    @Before
//    public void BeforeLogging(Scenario scenario) throws IOException {
//        Collection<String> tags = scenario.getSourceTagNames();
//        CapabilitiesGenerator.scenario.set(scenario);
//        CapabilitiesGenerator.tags.set(tags);
//
//        switch (PLATFORM_NAME) {
//            case "android":
//                if (tags.contains("@android")) {
//                    StartAppiumServer(PORT);
//                    androidDriver(PORT);
//                }
//                break;
//            default:
//                throw new IllegalStateException("Please enter valid OS platform.");
//        }
//    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot if the scenario fails
            final byte[] screenshot = ((TakesScreenshot) getAndroidDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }

        // Quit the Android driver if the "@final" tag is present
        try {
            if (CapabilitiesGenerator.tags.get().contains("@final")) {
                quitAndroidDriver();
            }
        } catch (Exception e) {
            System.out.println("Failed in quiting driver");
        }
    }
}
