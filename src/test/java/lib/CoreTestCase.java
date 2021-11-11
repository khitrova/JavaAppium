package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.sql.SQLOutput;


public class CoreTestCase extends TestCase {

   protected RemoteWebDriver driver;


  @Override
  protected void setUp() throws Exception {

    super.setUp();
    driver = Platform.getInstance().getDriver();
    this.rotateScreenPortrait();
    this.openWikiPageForMobileWeb();

  }

  @Override
  protected void tearDown() throws Exception{

    driver.quit();
    super.tearDown();
  }

  protected void rotateScreenPortrait(){

    if (driver instanceof AppiumDriver){
      AppiumDriver driver = (AppiumDriver) this.driver;
      driver.rotate(ScreenOrientation.PORTRAIT);
    } else{
      System.out.println("Method rotateScreenPortrait does nothing for platform "+ Platform.getInstance().getPlatformVar());
    }
  }

  protected void rotateScreenLandscape(){
    if (driver instanceof AppiumDriver){
      AppiumDriver driver = (AppiumDriver) this.driver;
    driver.rotate(ScreenOrientation.LANDSCAPE);
    } else{
      System.out.println("Method rotateScreenLandscape does nothing for platform "+ Platform.getInstance().getPlatformVar());
    }
  }

  protected void backgroundApp(int seconds){
    if (driver instanceof AppiumDriver){
      AppiumDriver driver = (AppiumDriver) this.driver;
    driver.runAppInBackground(seconds);
    } else{
      System.out.println("Method backgroundApp does nothing for platform "+ Platform.getInstance().getPlatformVar());
    }
  }

  protected void openWikiPageForMobileWeb(){
    if(Platform.getInstance().isMW()){
      driver.get("https://en.m.wikipedia.org");
    }else{
      System.out.println("Method openWikiPageForMobileWeb does nothing for platform "+ Platform.getInstance().getPlatformVar());
    }
  }

}
