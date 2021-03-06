package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private  String firstName;
  @Expose
  @Column(name = "lastname")
  private  String lastName;
  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private  String address;
  @Transient
  private  String address2;
  @Transient
  private  String allAddress;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private  String homePhoneNumber;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private  String mobilePhone;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private  String workPhone;
  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private  String email;
  @Type(type = "text")
  @Column(name = "email2")
  private  String email2;
  @Type(type = "text")
  @Column(name = "email3")
  private  String email3;
  @Transient
  private  String allEmail;
/*  @Expose
  @Transient
  private  String group;*/
  @Transient
  private  String allPhones;
  @Column(name = "photo")
  @Expose
  @Type(type = "text")
  private  String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();


  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhoneNumber(String homePhoneNumber) {
    this.homePhoneNumber = homePhoneNumber;
    return this;
  }
  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

/*  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }*/


  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  public ContactData withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }


  public ContactData withAllAddress(String allAddress) {
    this.allAddress = allAddress;
    return this;
  }
  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllEmail() {
    return allEmail;
  }

  public ContactData withAllEmail(String allEmail) {
    this.allEmail = allEmail;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }
  public String getAllPhones() {
    return allPhones;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhoneNumber() {
    return homePhoneNumber;
  }

  public String getEmail() {
    return email;
  }

/*  public String getGroup() {
    return group;
  }*/

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }


  public String getAddress2() {
    return address2;
  }
  public String getAllAddress() {
    return allAddress;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData inGroup(GroupData group){
    groups.add(group);
    return this;
  }

  public ContactData deleteGroup(GroupData group) {
    groups.remove(group);
    return this;
  }
}
