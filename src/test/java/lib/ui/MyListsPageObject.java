package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

  protected static  String
  MENU_SORT_ELEMENTS,
  FOLDER_BY_NAME_TPL,
  ARTICLE_BY_TITLE_TPL,
  REMOVE_FROM_SAVED_BUTTON,
  LIST_ELEMENT;

  public MyListsPageObject(RemoteWebDriver driver) {
    super(driver);
  }

  private static String getFolderXpathByName(String nameOfFolder){
    return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",nameOfFolder);
  }

  private static String getSavedArticleXpathByTitle(String articleTitle){
    return ARTICLE_BY_TITLE_TPL.replace("{TITLE}",articleTitle);
  }
  private static String getRemoveButtonByTitle(String articleTitle){

    return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}",articleTitle);
  }

  public void openFolderByName(String nameOfFolder){
    //check to avoid mis click
    this.waitForElementPresent(
            MENU_SORT_ELEMENTS,
            "Cannot not find sort menu element",
            10
    );

    String folderNameXpath = getFolderXpathByName(nameOfFolder);
    this.waitForElementAndClick(
             folderNameXpath,
            "Cannot find folder by name "+nameOfFolder,
            5
    );
  }

  public void swipeByArticleToDelete(String articleTitle){
    this.waitForArticleToAppearByTitle(articleTitle);
    String articleXpath = getSavedArticleXpathByTitle(articleTitle);

    if (Platform.getInstance().isAndroid()) {
      this.swipeElementToLeft(
              articleXpath,
              "Cannot find saved article"
      );
    } else {
      String removeLocator = getRemoveButtonByTitle(articleTitle);
      this.waitForElementAndClick(
              removeLocator,
              "Cannot click button to remove article from saved",
              10);
    }

    if (Platform.getInstance().isMW()){
      driver.navigate().refresh();
    }

    this.waitForArticleToDisappearByTitle(articleTitle);
  }

  public void waitForArticleToAppearByTitle(String articleTitle){

    String articleXpath = getSavedArticleXpathByTitle(articleTitle);
    this.waitForElementPresent(
            articleXpath,
            "Cannot find saved article "+ articleTitle,
            15
    );
  }

  public void waitForArticleToDisappearByTitle(String articleTitle){

    String articleXpath = getSavedArticleXpathByTitle(articleTitle);
    this.waitForElementNotPresent(
            articleXpath,
            "Saved article still present with title "+ articleTitle,
            15
    );
  }

  public void openSavedArticle(String articleTitle){
    String articleXpath = getSavedArticleXpathByTitle(articleTitle);
    this.waitForElementAndClick(
            articleXpath,
            "Cannot find saved article",
            5
    );
  }

  public String getSavedArticleTitle(){
  WebElement element =   waitForElementPresent(
            LIST_ELEMENT,
            "Cannot find list element",
            1
    );
    String title = element.getAttribute("title");
    return title;
  }
}
