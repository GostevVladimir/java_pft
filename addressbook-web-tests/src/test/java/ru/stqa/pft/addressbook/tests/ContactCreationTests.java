package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withFirstName("FirstNameTest12").withLastName("LastNameTest23").withAddress("test address").
                withPhoto(photo).withHomePhoneNumber("222333").withMobilePhone("88005553535").withWorkPhone("84959602424").withEmail("test@mail.com").withGroup("test1");
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(
                after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
    @Test(enabled = false)
    public void testBadContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("FirstNameTest12").withLastName("LastNameTest23").withAddress("test address").
                withHomePhoneNumber("222'333").withEmail("test@mail.com").withGroup("test1");
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
    @Test(enabled = false)
    public  void testCurrent(){
        File curr = new File(".");
        System.out.println(curr.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
