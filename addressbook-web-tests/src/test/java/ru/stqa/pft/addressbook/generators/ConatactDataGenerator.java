package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ConatactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

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
    if(format.equals("csv")){
      saveAsCsv(contacts, new File(file));
    } else if(format.equals("xml")){
      saveAsXml(contacts, new File(file));
    }  else if(format.equals("json")){
      saveAsJson(contacts, new File(file));
    }else{
      System.out.println("Указан неизвестный формат");
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }
  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for(ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getAddress(),
              contact.getHomePhoneNumber(),contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail(), contact.getGroup()));
    }
    writer.close();
  }


  private static List<ContactData> generateContacts(int count) throws IOException {
    List<ContactData> contacts = new ArrayList<>();
    for(int i = 0; i < count; i++){
      contacts.add(new ContactData().withFirstName(String.format("FirstNameTest%s", i)).
              withLastName(String.format("LastNameTest%s", i)).withAddress(String.format("test address%s", i))
              .withHomePhoneNumber(String.format("22233%s", i)).withMobilePhone(String.format("8800555353%s", i)).
              withWorkPhone(String.format("84959602424%s", i)).withEmail(String.format("test%s", i + "@mail.com")).withGroup(String.format("test1")));
    }
    return contacts;
  }
}
