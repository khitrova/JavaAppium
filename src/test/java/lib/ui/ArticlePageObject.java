package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{

  protected static String
    TITLE,
    FOOTER_ELEMENT,
    OPTIONS_BUTTON,
    OPTIONS_ADD_TO_MY_LIST_BUTTON ,
    OPTIONS_REMOVE_FROM_MT_LIST_BUTTON,
    ADD_TO_MY_LIST_OVERLAY,
    MY_LIST_NAME_INPUT,
    MY_LIST_OK_BUTTON,
    MY_LIST_NAME_TPL,
    CLOSE_ARTICLE_BUTTON;


  private static String getListXpathByName(String nameOfFolder){
    return MY_LIST_NAME_TPL.replace("{LIST_NAME}",nameOfFolder);
  }
  public ArticlePageObject(RemoteWebDriver driver){
    super(driver);
  }

  public WebElement waitForTitleElement() {
    return this.waitForElementPresent(TITLE,
            "Cannot find article title",
            15);
  }

  public void instantTitleCheck() {
    this.assertElementPresent(
            "org.wikipedia:id/view_page_title_text",
            "Element not found"
    );
  }

  public String getArticleTitle() {
    WebElement titleElement = waitForTitleElement();
    if (Platform.getInstance().isAndroid()) {
      return titleElement.getAttribute("text");
    } else {
      return titleElement.getText();
    }
  }

  public void swipeToFooter(){
    if (Platform.getInstance().isAndroid()) {
      this.swipeUpToFindElement(
              FOOTER_ELEMENT,
              "Cannot find the end of the article",
              40
      );
    }else {
      this.scrollWebPageTillElementNotVisible(
              FOOTER_ELEMENT,
              "Cannot find the end of the article",
              40
      );
    }
  }

  public void addArticleToMyList(String nameOfFolder){
    this.waitForElementAndClick(
            OPTIONS_BUTTON,
            "Cannot find button to open article options ",
            5
    );

    this.waitForElementPresent(
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            "Cannot find option to add article to reading list",
            10
    );

    this.waitForElementAndClick(
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            "Cannot find option to add article to reading list",
            5
    );

    this.waitForElementAndClick(
            ADD_TO_MY_LIST_OVERLAY,
            "Cannot find 'got it' tip overlay",
            5
    );

    this.waitForElementAndClear(
            MY_LIST_NAME_INPUT,
            "Cannot find input to set name of articles folder",
            5
    );

    this.waitForElementAndSendKeys(
            MY_LIST_NAME_INPUT,
            nameOfFolder,
            "Cannot find text into articles folder input",
            5
    );

    this.waitForElementAndClick(
           MY_LIST_OK_BUTTON,
            "Cannot press OK",
            5
    );
  }

  public void closeArticle(){

    if (Platform.getInstance().isAndroid()) {
      this.waitForElementAndClick(
              CLOSE_ARTICLE_BUTTON,
              "Cannot close article, no X ",
              5
      );
    } else {
      System.out.println("Method closeArticle does nothing for platform "+ Platform.getInstance().getPlatformVar());
    }
  }

  public void addArticleToExistingList(String nameOfFolder){
    this.waitForElementAndClick(
            OPTIONS_BUTTON,
            "Cannot find button to open article options ",
            5
    );

    this.waitForElementPresent(
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            "Cannot find option to add article to reading list",
            10
    );

    this.waitForElementAndClick(
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            "Cannot find option to add article to reading list",
            5
    );
    String folderNameXpath = getListXpathByName(nameOfFolder);
    this.waitForElementAndClick(
            folderNameXpath,
            "Cannot find created folder",
            5
    );
  }

  public void addArticleToMySaved(){
    if (Platform.getInstance().isMW()){
      this.removeArticleFromSavedIfItIsAdded();
    }
    this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,"Cannot find option to add article to reading list", 5);
  }

  public void removeArticleFromSavedIfItIsAdded(){
    if (this.isElementPresent(OPTIONS_REMOVE_FROM_MT_LIST_BUTTON)){
      this.waitForElementAndClick(
              OPTIONS_REMOVE_FROM_MT_LIST_BUTTON,
              "Cannot click button to remove an article from saved",
              1
      );
      this.waitForElementPresent(
              OPTIONS_ADD_TO_MY_LIST_BUTTON,
              "Cannot find button to add an article to saved after removing it from this list before",
              5
      );
    }
  }
}
