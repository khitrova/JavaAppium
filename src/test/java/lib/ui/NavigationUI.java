package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

  protected static String
    MY_LISTS_LINK,
  OPEN_NAVIGATION;

  public NavigationUI(RemoteWebDriver driver) {
    super(driver);
  }

  @Step("Click My Lists")
  public void clickMyLists() {
    if (Platform.getInstance().isMW()) {
      this.tryClickElementsWithFewAttempts(
              MY_LISTS_LINK,
              "Cannot find 'My lists' button ",
              10
      );
    } else {
      this.waitForElementAndClick(
              MY_LISTS_LINK,
              "Cannot find 'My lists' button ",
              5
      );
    }
  }

  @Step("Open navigation")
  public void openNavigation(){
    if (Platform.getInstance().isMW()){
      this.waitForElementAndClick(OPEN_NAVIGATION, "cannot find and click open navigation button", 5);
    } else {
      System.out.println("Method openNavigation does nothing for platform "+ Platform.getInstance().getPlatformVar());
    }
  }
}
