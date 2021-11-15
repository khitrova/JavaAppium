package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
@Epic("Tests with app condition changes")
public class ChangeAppConditionTests extends CoreTestCase {

  @Test
  @Features(value = {@Feature(value ="Search"),@Feature(value = "App Condition")})
  @DisplayName("Rotating screen")
  @Description("Rotating screen and check that search results didn't change")
  @Step("Start test ChangeScreenRotationOnSearchResults")
  @Severity(value = SeverityLevel.NORMAL)
  public void testChangeScreenRotationOnSearchResults(){
    if (Platform.getInstance().isMW()){
      return;
    }
    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    ArticlePageObject articlePageObject =ArticlePageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
    String titleBeforeRotation = articlePageObject.getArticleTitle();
    this.rotateScreenLandscape();
    String titleAfterRotation = articlePageObject.getArticleTitle();
    Assert.assertEquals(
            "Article title have been changed after screen rotation",
            titleBeforeRotation,
            titleAfterRotation
    );
    this.rotateScreenPortrait();
    String titleAfterSecondRotation = articlePageObject.getArticleTitle();
    Assert.assertEquals(
            "Article title have been changed after screen rotation",
            titleBeforeRotation,
            titleAfterSecondRotation
    );
  }

  @Test
  @Features(value = {@Feature(value ="Search"),@Feature(value = "App Condition")})
  @DisplayName("Check search in background")
  @Description("Checking that after sending the app to background search results are the same")
  @Step("Start test CheckSearchArticleInBackground")
  @Severity(value = SeverityLevel.MINOR)
  public void testCheckSearchArticleInBackground(){
    if (Platform.getInstance().isMW()){
      return;
    }
    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.waitForSearchResult("Object-oriented programming language");
    this.backgroundApp(2);
    searchPageObject.waitForSearchResult("Object-oriented programming language");
  }
}
