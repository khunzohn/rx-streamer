package com.khunzohn.domain.exception.student;

import com.khunzohn.domain.exception.base.DomainException;

/**
 * Created by codigo on 29/1/18.
 */

public class StudentException extends DomainException {
  public StudentException(StudentIssueManager studentIssueManager) {
    super(studentIssueManager);
  }
}
