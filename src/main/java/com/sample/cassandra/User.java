package com.sample.cassandra;

import java.io.Serializable;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "ks", name = "users",
readConsistency = "QUORUM",
writeConsistency = "QUORUM",
caseSensitiveKeyspace = false,
caseSensitiveTable = false)
public class User implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 2726815649347381093L;

  @PartitionKey
  @Column(name = "user_id")
  private UUID id;
  
  @Column(name = "name")
  private String name;
  
  private Address address;
  
  public User(UUID id, String name, Address adress){
    this.id = id;
    this.name = name;
    this.address = adress;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

}
