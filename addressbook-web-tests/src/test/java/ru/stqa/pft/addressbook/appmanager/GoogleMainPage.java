package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleMainPage extends HelperBase {

  public GoogleMainPage(WebDriver wd) {
    super(wd);
    PageFactory.initElements(wd, this);
  }

  private final String URL = "https://www.google.ru/";

//  @FindBy(xpath = "//*[@name='q']")
  private By searchString = By.xpath("//*[@name='q']");

  @FindBy(xpath = "//*[@name='btnK']")
  private WebElement searchButton;

//  @FindBy(xpath = "(.//*[@id='rso']//h3)[1]")
  private By firstLink = By.xpath("(.//*[@id='rso']//h3)[1]");



  public void search(String text){
    type(searchString,text);
  }


  public void openFirstLink(){
    click(firstLink);
  }


  public void openSearchPage(){
    wd.get(URL);
  }

}
