package com.sample.cassandra;

import java.io.Serializable;

import com.datastax.driver.mapping.annotations.UDT;

@UDT(keyspace = "ks", name = "address")
public class Address implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 5609799090660282256L;
  private String street;

  public Address() {}

  public Address(String street) {
    this.street = street;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

}
