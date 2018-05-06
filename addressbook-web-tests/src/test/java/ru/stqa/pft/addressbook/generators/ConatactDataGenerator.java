package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConatactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;


  public static void main(String[] args) throws IOException {

    ConatactDataGenerator generator = new ConatactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException e){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }
  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for(ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getAddress(),
              contact.getHomePhoneNumber(),contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail(), contact.getGroup() ));
    }
    writer.close();
  }


  private static List<ContactData> generateContacts(int count) throws IOException {
    List<ContactData> contacts = new ArrayList<>();
    for(int i = 0; i < count; i++){
      contacts.add(new ContactData().withFirstName(String.format("FirstNameTest%s", i)).
              withLastName(String.format("LastNameTest%s", i)).withAddress(String.format("test address%s", i)).
              withHomePhoneNumber(String.format("22233%s", i)).withMobilePhone(String.format("8800555353%s", i)).
              withWorkPhone(String.format("84959602424%s", i)).withEmail(String.format("test%s", i + "@mail.com")).withGroup(String.format("test1")));
    }
    return contacts;
  }
}
