package com.khunzohn.rxstreamer.feature.student;

import com.khunzohn.domain.model.Student;
import com.khunzohn.rxstreamer.util.Visibility;

/**
 * Created by codigo on 29/1/18.
 */

public class EnrolmentViewState {
  public final String nameError;
  public final String phoneError;
  public final String ssnError;
  public final String addressError;
  public final String emailError;

  public final Student student;
  public final long id;
  public final String status;
  public final String enrolDate;

  @Visibility public final int progressVisiblility;
  @Visibility public final int enrolButtonVisibility;
  public final boolean hasDataError;
  public final boolean shouldRetry;
  public final String dataErrorMessage;

  public EnrolmentViewState(String nameError, String phoneError, String ssnError,
      String addressError, String emailError, Student student, long id, String status,
      String enrolDate, int progressVisiblility, int enrolButtonVisibility, boolean hasDataError,
      boolean shouldRetry, String dataErrorMessage) {
    this.nameError = nameError;
    this.phoneError = phoneError;
    this.ssnError = ssnError;
    this.addressError = addressError;
    this.emailError = emailError;
    this.student = student;
    this.id = id;
    this.status = status;
    this.enrolDate = enrolDate;
    this.progressVisiblility = progressVisiblility;
    this.hasDataError = hasDataError;
    this.shouldRetry = shouldRetry;
    this.enrolButtonVisibility = enrolButtonVisibility;
    this.dataErrorMessage = dataErrorMessage;
  }
}
