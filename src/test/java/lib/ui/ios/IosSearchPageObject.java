package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosSearchPageObject extends SearchPageObject {

  static {
    //тут будут локаторы для иос
    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
    SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
    SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    SEARCH_RESULT_ELEMENT = "id:org.wikipedia:id/page_list_item_container";
    SEARCH_RESULT_LIST_ELEMENT = "id:org.wikipedia:id/page_list_item_title";
    SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION = "xpath://lib.ui.android.widget.TextView[@text='{DESCRIPTION}']/../lib.ui.android.widget.TextView[@text='{TITLE}']";
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
  }

  public IosSearchPageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
