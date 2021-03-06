package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase{
  public ContactHelper(WebDriver wd){
    super(wd);
  }


  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstName());
    type(By.name("lastname"),contactData.getLastName());
    attach(By.name("photo"),contactData.getPhoto());
    type(By.name("address"),contactData.getAddress());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getHomePhoneNumber());
    type(By.name("mobile"),contactData.getMobilePhone());
    type(By.name("work"),contactData.getWorkPhone());
    type(By.name("email"),contactData.getEmail());
    type(By.name("email2"),contactData.getEmail2());
    type(By.name("email3"),contactData.getEmail3());
    //type(By.name("group"),contactData.getGroups().iterator().next());


    if(creation){
      if(contactData.getGroups().size() > 0){
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("img[alt=Edit]")).get(index).click();
  }
  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
  }

  public void submitContactModificatio() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void create(ContactData contact, boolean n) {
    initContactCreation();
    fillContactForm(contact,n);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModificatio();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    acceptAlert();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if(contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for(WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName)
              .withLastName(lastName).withAllPhones(allPhones).withAddress(address).withAllEmail(allEmails));

    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName  = wd.findElement(By.name("lastname")).getAttribute("value");
    String address  = wd.findElement(By.name("address")).getAttribute("value");
    String homePhoneNumber  = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhoneNumber  = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhoneNumber  = wd.findElement(By.name("work")).getAttribute("value");
    String email  = wd.findElement(By.name("email")).getAttribute("value");
    String email2  = wd.findElement(By.name("email2")).getAttribute("value");
    String email3  = wd.findElement(By.name("email3")).getAttribute("value");

    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName).
            withAddress(address).withHomePhoneNumber(homePhoneNumber).withMobilePhone(mobilePhoneNumber).
            withWorkPhone(workPhoneNumber).withEmail(email).withEmail2(email2).withEmail3(email3);

  }

  public void addContactInGroup(int id, String name) {
    selectContactById(id);
    click(By.name("to_group"));
    click(By.xpath(String.format("//select[@name='to_group']/option[text()='%s']", name)));
    click(By.name("add"));
  }

  public void deleteContactInGroup(int id, String name) {
    selectGroupByName(name);
    selectContactById(id);
    click(By.name("remove"));
  }

  private void selectGroupByName(String name) {
    click(By.name("group"));
    click(By.xpath(String.format("//select[@name='group']/option[text()='%s']", name)));

  }

}
