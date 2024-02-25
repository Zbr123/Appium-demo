package core.utils;

import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.IntStream;

public class BsDeviceHelper {
    private static final Properties androidconfig = ConfigUtil.getConfig("androidconfig");
    private static final ArrayList<String> deviceList = new ArrayList<>();
    private static final ArrayList<Boolean> flagList = new ArrayList<>();

    public static void collectDeviceList() {
        int maxDevice = 5;
        for (int i = 1; i <= maxDevice; i++) {
            String deviceName = System.getenv("DEVICE_PROFILE_" + i);
            if (!(deviceName == null || deviceName.equals("None"))) {
                flagList.add(true);
                deviceList.add(deviceName);
            }
        }
    }

    public static String getAvailableDevice() {
        int i;
        if (deviceList.size() == 0)
            return androidconfig.getProperty("browserstackDevice") + "@" + androidconfig.getProperty("browserstackOS");
        for (i = 0; i < flagList.size(); i++) {
            if (flagList.get(i)) {
                flagList.set(i, false);
                break;
            }
        }
        if (i == flagList.size()) {
            i = 0;
            IntStream.range(0, flagList.size()).forEach(k -> flagList.set(k, true));
            flagList.set(i, false);
        }
        return deviceList.get(i);
    }
}
