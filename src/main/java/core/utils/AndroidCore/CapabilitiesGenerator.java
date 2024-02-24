package core.utils.AndroidCore;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Properties;
import java.io.FileInputStream;

public class CapabilitiesGenerator {
    public static ThreadLocal<Scenario> scenario = new ThreadLocal<>();
    public static ThreadLocal<Collection<String>> tags = new ThreadLocal<>();
    public static String App;
    public static int getAnyFreePort() throws IOException {
        ServerSocket s = new ServerSocket(0);
        s.close();
        return s.getLocalPort();
    }

    public static DesiredCapabilities getAndroidCapabilities() throws IOException {
        Properties androidconfig = new Properties();
        androidconfig.load(Files.newInputStream(Paths.get(System.getProperty("user.dir") + "/src/test/java/config/androidconfig.properties")));

        DesiredCapabilities cap = new DesiredCapabilities();
        String appDir = System.getProperty("user.dir") + "/apk/";

        if (Boolean.parseBoolean(androidconfig.getProperty("browserstack"))) {
            cap.setCapability("browserstack.user", androidconfig.getProperty("browserStackUser"));
            cap.setCapability("browserstack.key", androidconfig.getProperty("accessKey"));
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, androidconfig.getProperty("browserstackDevice"));
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidconfig.getProperty("browserstackOS"));
            cap.setCapability(MobileCapabilityType.APP, "bs://7bdc3b9fccacccac9bae0ac0d10bd5f59d9979ff");
        } else {
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, androidconfig.getProperty("platformName"));
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidconfig.getProperty("platformVersion"));
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, androidconfig.getProperty("deviceName"));
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, androidconfig.getProperty("automationName"));
            cap.setCapability(MobileCapabilityType.APP, appDir + androidconfig.getProperty("androidapk"));
            cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        }

        cap.setCapability(MobileCapabilityType.ACCEPT_INSECURE_CERTS, true);
        cap.setCapability(MobileCapabilityType.FULL_RESET, true);
        cap.setCapability(MobileCapabilityType.NO_RESET, false);

        return cap;
    }
}
