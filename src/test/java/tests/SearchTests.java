package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

@Epic("Search")
public class SearchTests extends CoreTestCase {

  @Test
  @Features(value = {@Feature(value ="Search")})
  @DisplayName("Simple search")
  @Description("We search 'Java' and make sure that it presents in results")
  @Step("Starting test Search")
  @Severity(value = SeverityLevel.BLOCKER)
  public void testSearch() {

    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.waitForSearchResult("bject-oriented programming language");
  }

  @Test
  @Features(value = {@Feature(value ="Search")})
  @DisplayName("Cancel search")
  @Description("We open open search input and close it")
  @Step("Starting test CancelSearch")
  @Severity(value = SeverityLevel.NORMAL)
  public void testCancelSearch(){

    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.waitForCancelButtonToAppear();
    searchPageObject.clickCancelSearch();
    searchPageObject.waitForCancelButtonToDisappear();
  }

  @Test
  @Features(value = {@Feature(value ="Search")})
  @DisplayName("Not empty search")
  @Description("We check that correct search has results")
  @Step("Starting test AmountOfNotEmptySearch")
  @Severity(value = SeverityLevel.BLOCKER)
  public void testAmountOfNotEmptySearch(){

    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    String searchLine = "Linkin Park discography";
    searchPageObject.typeSearchLine(searchLine);
    int amountOfSearchElements =searchPageObject.getAmountOfFoundArticles();
    Assert.assertTrue(
            "Found too few results",
            amountOfSearchElements>0
    );
  }

  @Test
  @Features(value = {@Feature(value ="Search")})
  @DisplayName("Empty search results")
  @Description("We search for something without results expected")
  @Step("Starting test AmountOfEmptySearch")
  @Severity(value = SeverityLevel.MINOR)
  public void testAmountOfEmptySearch(){

    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    String searchLine = "dfghdfghdfh";
    searchPageObject.typeSearchLine(searchLine);
    searchPageObject.waitForEmptyResultsLabel();
    searchPageObject.assertThereIsNoResultOfSearch();
  }

  @Test
  @Features(value = {@Feature(value ="Search")})
  @DisplayName("Search element contains text")
  @Description("Check that search input contains text Search Wikipedia")
  @Step("Starting test SearchElementContainsText")
  @Severity(value = SeverityLevel.BLOCKER)
  public void testSearchElementContainsText(){

    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.assertSearchElementHasText("Search Wikipedia");

  }

  @Test
  @Features(value = {@Feature(value ="Search")})
  @DisplayName("Cancel search with search results")
  @Description("We make search and cancel it after receiving results")
  @Step("Starting test CancelSearchWithResults")
  @Severity(value = SeverityLevel.BLOCKER)
  public void testCancelSearchWithResults(){

    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    int amountOfSearchElements =searchPageObject.getAmountOfFoundArticles();
    Assert.assertTrue(
            "Found too few results",
            amountOfSearchElements>0
    );
    searchPageObject.clickCancelSearch();
    searchPageObject.assertSearchResultsAbsent();
  }

  @Test
  @Features(value = {@Feature(value ="Search")})
  @DisplayName("Check all search results contain keyword")
  @Description("We make search and after receiving results check that all of them have the searched word")
  @Step("Starting test SearchResultsContainKeyword")
  @Severity(value = SeverityLevel.MINOR)
  public void testSearchResultsContainKeyword() {

    String keyword = "java";
    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(keyword);
    int amountOfSearchElements =searchPageObject.getAmountOfFoundArticles();
    Assert.assertTrue(
            "Found too few results",
            amountOfSearchElements>0
    );
    Assert.assertTrue(
            "Not all elements contains keyword " + keyword,
            searchPageObject.assertResultsContainKeyword(keyword));
  }

  @Test
  @Features(value = {@Feature(value ="Search")})
  @DisplayName("Checking test results title and description")
  @Description("We search keyword and amke cure to have three expected articles with concrete titles and descriptions")
  @Step("Starting test ResultsTitleAndDescription")
  @Severity(value = SeverityLevel.NORMAL)
  public void testResultsTitleAndDescription(){
    String keyword = "String",
            firstTitle = "String",
            firstDescription = "Wikimedia disambiguation page",
            secondTitle = "String theory",
            secondDescription = "A fundamental theory of physics",
            thirdTitle = "String instrument",
            thirdDescription ="Musical instrument that generates tones by one or more strings stretched between two points";

    SearchPageObject searchPageObject =  SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(keyword);
    int amountOfSearchElements =searchPageObject.getAmountOfFoundArticles();
    Assert.assertTrue(
            "Found too few results",
            amountOfSearchElements>=3
    );
    searchPageObject.waitForElementByTitleAndDescription(firstTitle,firstDescription);
    searchPageObject.waitForElementByTitleAndDescription(secondTitle,secondDescription);
    searchPageObject.waitForElementByTitleAndDescription(thirdTitle,thirdDescription);

  }
}
