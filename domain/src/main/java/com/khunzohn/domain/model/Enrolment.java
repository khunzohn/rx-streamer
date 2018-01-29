package com.khunzohn.domain.model;

/**
 * Created by codigo on 29/1/18.
 */

public class Enrolment {
  public final long id;
  public final String enrolDate;
  public final String status;
  public final Student student;

  public final Throwable error;
  public final State state;

  public Enrolment(long id, String enrolDate, String status, Student student, Throwable error,
      State state) {
    this.id = id;
    this.enrolDate = enrolDate;
    this.status = status;
    this.student = student;
    this.error = error;
    this.state = state;
  }

  public static Enrolment success(long id, String enrolDate, String status, Student student) {
    return new Enrolment(id, enrolDate, status, student, null, State.SUCCESS);
  }

  public static Enrolment error(Throwable error) {
    return new Enrolment(-1, null, null, null, error, State.ERROR);
  }

  public static Enrolment progress() {
    return new Enrolment(-1, null, null, null, null, State.PROGRESS);
  }
}
