package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

  static {
    MENU_SORT_ELEMENTS = "id:org.wikipedia:id/menu_sort_options";
    FOLDER_BY_NAME_TPL = "xpath://lib.ui.android.widget.TextView[@text='{FOLDER_NAME}']";
    ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
  }

  public AndroidMyListsPageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
