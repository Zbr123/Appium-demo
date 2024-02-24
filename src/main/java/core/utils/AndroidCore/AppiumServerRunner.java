package core.utils.AndroidCore;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class AppiumServerRunner {
    private static AppiumDriverLocalService service;
    private static AppiumServiceBuilder builder;
    private static DesiredCapabilities cap;

    private static boolean readBrowserStackFlag() {
        Properties props = new Properties();
        try {
            // Adjust the path according to your project structure
            props.load(Files.newInputStream(Paths.get(System.getProperty("user.dir") + "/src/test/java/config/androidconfig.properties")));
            return Boolean.parseBoolean(props.getProperty("browserstack"));
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Default to false if there's an issue reading the property
        }
    }

    public static void startServer(int port) {
        if (readBrowserStackFlag()) {
            System.out.println("BrowserStack execution mode enabled. Skipping local Appium server start.");
            return;
        }

        cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");
        builder = new AppiumServiceBuilder();
        builder.withIPAddress("0.0.0.0");
        builder.usingPort(port);
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public static void stopServer() {
        if (service != null) {
            service.stop();
        }
    }

    public static boolean checkIfServerIsRunnning(int port) {
        if (readBrowserStackFlag()) {
            return false; // Assume not required to check server status for BrowserStack execution
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return false; // If this line executes, the port is available, meaning the server is not running
        } catch (IOException e) {
            return true; // Port is in use, server is likely running
        }
    }

    public static void StartAppiumServer(String port) {
        if (readBrowserStackFlag()) {
            System.out.println("BrowserStack execution mode enabled. Skipping Appium server management.");
            return;
        }

        System.out.println("Checking Appium Server");
        System.out.println("PORT: " + port);

        if (!checkIfServerIsRunnning(Integer.parseInt(port))) {
            System.out.println("Starting an Appium Server");
            startServer(Integer.parseInt(port));
        }
    }
}
