package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {



  @Test
  public void testCompareArticleTitle(){

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    String articleTitle = articlePageObject.getArticleTitle();
    Assert.assertEquals(
            "We see unexpected title",
            "Java (programming language)",
            articleTitle
    );
  }


  @Test
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
  public void testAssertTitle(){
    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.instantTitleCheck();

  }
}
