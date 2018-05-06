package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class ContactCreationTests extends TestBase{
    @DataProvider
    public Iterator<Object[]> validContacts(){
        List<Object[]> list =new ArrayList<>();
        File photo = new File("src/test/resources/stru.png");
        list.add(new Object[] {new ContactData().withFirstName("FirstNameTest12").withLastName("LastNameTest23").withAddress("test address").
                                withPhoto(photo).withHomePhoneNumber("222333").withMobilePhone("88005553535").withWorkPhone("84959602424").
                                withEmail("test@mail.com").withGroup("test1")});
        list.add(new Object[] {new ContactData().withFirstName("FirstNameTest121").withLastName("LastNameTest231").withAddress("test address1").
                                withPhoto(photo).withHomePhoneNumber("2223331").withMobilePhone("880055535351").withWorkPhone("849596024241").
                                withEmail("test@mail.com1").withGroup("test1")});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.contact().all();
/*        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withFirstName("FirstNameTest12").withLastName("LastNameTest23").withAddress("test address").
                withPhoto(photo).withHomePhoneNumber("222333").withMobilePhone("88005553535").withWorkPhone("84959602424").withEmail("test@mail.com").withGroup("test1");*/
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
