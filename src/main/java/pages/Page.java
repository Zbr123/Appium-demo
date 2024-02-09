package pages;

import core.utils.ConfigUtil;
import pages.android.HomePage;
import pages.android.UpdatePage;
import java.util.Properties;
import pages.android.*;

public class Page {

    public static Properties myProp = ConfigUtil.getConfig("config");
    public static Properties androidconfig = ConfigUtil.getConfig("androidconfig");

    public HomePage homePage = new HomePage();
    protected HomePage getHomePage() {return homePage;}
    private final UpdatePage updatePage = new UpdatePage(this);
    protected UpdatePage getUpdatePage() {return updatePage;}
}
