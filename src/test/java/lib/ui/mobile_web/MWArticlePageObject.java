package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

  static {
    TITLE = "css:#content h1";
    FOOTER_ELEMENT = "css:footer";
    OPTIONS_BUTTON = "xpath://lib.ui.android.widget.ImageView[@content-desc='More options']";
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@id='ca-watch']";
    ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
    MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
    MY_LIST_OK_BUTTON = "id:lib.ui.android:id/button1";
    MY_LIST_NAME_TPL = "xpath://lib.ui.android.widget.TextView[@text='{LIST_NAME}']";
    OPTIONS_REMOVE_FROM_MT_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";

  }
  public MWArticlePageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
//*[@id="ca-watch"]