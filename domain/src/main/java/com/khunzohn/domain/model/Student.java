package com.khunzohn.domain.model;

/**
 * Created by codigo on 29/1/18.
 */

public class Student {
  public final String name;
  public final String phone;
  public final String email;
  public final String adderss;
  public final String ssn;

  public Student(String name, String phone, String email, String adderss, String ssn) {
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.adderss = adderss;
    this.ssn = ssn;
  }
}
