package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GoogleTest extends TestBase {

  @Test
  public void testFirst(){
    app.getGoogleMainPage().search("Пенза");
    app.getGoogleMainPage().openFirstLink();
    app.getWikipediaPage().verifyTitle("Пенза");
  }
}
