package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListTests extends CoreTestCase {

  private static final String nameOfFolder = "Learning programming";
  private static final String login = "Fjushka";
  private static final String password = "fwLrz4qLXnR4m9W";
  @Test
  public void testSaveFirstArticleToMyList() {

    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.waitForTitleElement();
    String articleTitle = articlePageObject.getArticleTitle();
    if (Platform.getInstance().isAndroid()) {
      articlePageObject.addArticleToMyList(nameOfFolder);
    } else {
      articlePageObject.addArticleToMySaved();
    }
    if (Platform.getInstance().isMW()){
      AuthorizationPageObject auth = new AuthorizationPageObject(driver);
      auth.clickAuthButton();
      auth.enterLoginData(login,password);
      auth.submitForm();

      articlePageObject.waitForTitleElement();
      assertEquals("We are not on the same page after login",
              articleTitle,
              articlePageObject.getArticleTitle());
    }

    articlePageObject.closeArticle();

    NavigationUI navigationUI = NavigationUIPageObjectFactory.get(driver);
    navigationUI.openNavigation();
    navigationUI.clickMyLists();

    MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
    if (Platform.getInstance().isAndroid()) {
      myListsPageObject.openFolderByName(nameOfFolder);
    }

    myListsPageObject.swipeByArticleToDelete(articleTitle);
  }

  @Test
  public void testSavingTwoArticles(){

    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.waitForTitleElement();
    String firstArticleTitle = articlePageObject.getArticleTitle();
    if (Platform.getInstance().isAndroid()) {
      articlePageObject.addArticleToMyList(nameOfFolder);
    } else {
      articlePageObject.addArticleToMySaved();
    }
    if (Platform.getInstance().isMW()){
      AuthorizationPageObject auth = new AuthorizationPageObject(driver);
      auth.clickAuthButton();
      auth.enterLoginData(login,password);
      auth.submitForm();

      articlePageObject.waitForTitleElement();
      assertEquals("We are not on the same page after login",
              firstArticleTitle,
              articlePageObject.getArticleTitle());
    }

    articlePageObject.closeArticle();

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Android");
    searchPageObject.clickByArticleWithSubstring("operating system");
    String secondArticleName = articlePageObject.getArticleTitle();
    if (Platform.getInstance().isAndroid()) {
      articlePageObject.addArticleToExistingList(nameOfFolder);
    } else{
      articlePageObject.addArticleToMySaved();
    }
    articlePageObject.closeArticle();

    NavigationUI navigationUI = NavigationUIPageObjectFactory.get(driver);
    navigationUI.openNavigation();
    navigationUI.clickMyLists();
    MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
    if (Platform.getInstance().isAndroid()) {
      myListsPageObject.openFolderByName(nameOfFolder);
    }
    myListsPageObject.swipeByArticleToDelete(firstArticleTitle);
    String savedArticleName;
    if (Platform.getInstance().isAndroid()) {
      myListsPageObject.openSavedArticle("sland");
      savedArticleName = articlePageObject.getArticleTitle();
    } else{
      savedArticleName = myListsPageObject.getSavedArticleTitle();
    }
      Assert.assertEquals(
              "Article name " + savedArticleName + " is not equal to expected " + secondArticleName,
              savedArticleName,
              secondArticleName
      );

  }
}
