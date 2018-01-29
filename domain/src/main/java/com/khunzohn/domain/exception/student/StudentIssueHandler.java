package com.khunzohn.domain.exception.student;

import com.khunzohn.domain.exception.base.DomainIssueHandler;
import com.khunzohn.domain.exception.issue.AddressIssue;
import com.khunzohn.domain.exception.issue.EmailIssue;
import com.khunzohn.domain.exception.issue.NameIssue;
import com.khunzohn.domain.exception.issue.PhoneIssue;
import com.khunzohn.domain.exception.issue.SSNIssue;

/**
 * Created by codigo on 29/1/18.
 */

public interface StudentIssueHandler extends DomainIssueHandler {

  void handle(PhoneIssue phoneIssue);

  void handle(NameIssue nameIssue);

  void handle(EmailIssue emailIssue);

  void handle(AddressIssue addressIssue);

  void handle(SSNIssue ssnIssue);
}
