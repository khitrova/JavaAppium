package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

  static {
    SEARCH_INIT_ELEMENT = "css:button#searchIcon";
    SEARCH_INPUT = "css:form>input[type='search']";
    SEARCH_CANCEL_BUTTON = "xpath://div/div[2]/button";
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
    SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
    SEARCH_RESULT_LIST_ELEMENT = "id:org.wikipedia:id/page_list_item_title";
    SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION = "xpath://lib.ui.android.widget.TextView[@text='{DESCRIPTION}']/../lib.ui.android.widget.TextView[@text='{TITLE}']";
    SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
  }

  public MWSearchPageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
