package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

  static {
    MENU_SORT_ELEMENTS = "id:org.wikipedia:id/menu_sort_options";
    FOLDER_BY_NAME_TPL = "xpath://lib.ui.android.widget.TextView[@text='{FOLDER_NAME}']";
    ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'mw-mf-watchlist-page-list')]//h3[contains(text(),'{TITLE}')]";
    REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'mw-mf-watchlist-page-list')]//h3[contains(text(),'{TITLE}')]/../../a[contains(@class,'watched')]";
  }

  public MWMyListsPageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
