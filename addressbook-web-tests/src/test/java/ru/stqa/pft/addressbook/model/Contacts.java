package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegete;

  public Contacts (Contacts contacts) {
    this.delegete = new HashSet<ContactData>(contacts.delegete);
  }

  public Contacts() {
    this.delegete = new HashSet<>();
  }

  public Contacts(Collection<ContactData> contacts) {
    this.delegete = new HashSet<ContactData>(contacts);
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegete;
  }

  public Contacts withAdded(ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts withOut(ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
