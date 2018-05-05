package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData>{

  private Set<GroupData> delegete;

  public Groups(Groups groups) {
    this.delegete = new HashSet<GroupData>(groups.delegete);
  }

  public Groups() {
    this.delegete = new HashSet<>();
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegete;
  }

  public Groups withAdded(GroupData group){
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups withOut(GroupData group){
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }
}
