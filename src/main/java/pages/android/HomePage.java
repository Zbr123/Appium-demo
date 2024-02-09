package pages.android;

import core.utils.AndroidCore.AndroidDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.Page;

public class HomePage {

    String number;
    private String Title_XPATH = "///hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView";
    private String ImmediateButtonID = "com.meritnation.store.testingapp:id/btn1";
    private String VersionCodeID ="com.meritnation.store.testingapp:id/ver_name";
    private String VersionNameID ="com.meritnation.store.testingapp:id/ver_name";
    private String FirstButtonID ="com.meritnation.store.testingapp:id/btn";
    private String SecondButtonID ="com.meritnation.store.testingapp:id/btn2";
    private String ThirdButtonID ="com.meritnation.store.testingapp:id/btn3";
    private String FirstResult ="com.meritnation.store.testingapp:id/txt";

    public HomePage(Page page) {
    }

    public WebElement getTitle() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.xpath(Title_XPATH)));
    }

    public WebElement getVersionCode() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(VersionCodeID)));
    }

    public WebElement getVersionName() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(VersionNameID)));
    }

    public WebElement getImmediateButton() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(ImmediateButtonID)));
    }

    public WebElement getFirstButton() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(FirstButtonID)));
    }

    public WebElement getSecondButton() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(SecondButtonID)));
    }

    public WebElement getThirdButton() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(ThirdButtonID)));
    }

    public WebElement getFirstResult() {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id(FirstResult)));
    }
    public void backButton() {
         AndroidDriverSetup.getAndroidDriver().navigate().back();
    }

    public WebElement getButton(String button) {
        return (AndroidDriverSetup.getAndroidDriver().findElement(By.id("com.meritnation.store.testingapp:id/btn"+button)));
    }
}