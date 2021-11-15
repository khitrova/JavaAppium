package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;


@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {



  @Test
  @Features(value = {@Feature(value ="Search"),@Feature(value = "Article")})
  @DisplayName("Compare article title with the expected one")
  @Description("We open 'Java Object-oriented programming language' and make sure the title is expected")
  @Step("Starting test CompareArticleTitle")
  @Severity(value = SeverityLevel.BLOCKER)
  public void testCompareArticleTitle(){

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    String articleTitle = articlePageObject.getArticleTitle();
 //   articlePageObject.takeScreenshot("article_page");
    Assert.assertEquals(
            "We see unexpected title",
            "Java (programming language)",
            articleTitle
    );
  }


  @Test
  @Features(value = {@Feature(value ="Search"),@Feature(value = "Article")})
  @DisplayName("Swipe article to the footer")
  @Description("We open an article and swipe it to the footer")
  @Step("Starting test SwipeArticle")
  @Severity(value = SeverityLevel.MINOR)
  public void testSwipeArticle(){

    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.waitForTitleElement();
    articlePageObject.swipeToFooter();

  }

  @Test
  @Features(value = {@Feature(value ="Search"),@Feature(value = "Article")})
  @Severity(value = SeverityLevel.BLOCKER)
  @DisplayName("Instant compare article title with the expected one")
  @Description("We open 'Java Object-oriented programming language' and make sure the title is expected instantly")
  @Step("Starting test Assert title")
  public void testAssertTitle(){
    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.instantTitleCheck();

  }
}
