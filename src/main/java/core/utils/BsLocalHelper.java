package core.utils;

import com.browserstack.local.Local;
import core.utils.AndroidCore.AndroidDriverSetup;

import java.util.HashMap;

public class BsLocalHelper {
    static Local local;

    public static void enableLocalTesting() {
        try {
            local = new Local();
            HashMap<String, String> localArgs = new HashMap<>();
            localArgs.put("key", AndroidDriverSetup.AUTOMATE_ACCESS_KEY);
            local.start(localArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disableLocalTesting() {
        try {
            local.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
