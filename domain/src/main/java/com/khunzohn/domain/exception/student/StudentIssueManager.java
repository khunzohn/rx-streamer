package com.khunzohn.domain.exception.student;

import com.khunzohn.domain.exception.base.DomainIssue;
import com.khunzohn.domain.exception.base.DomainIssueManager;
import com.khunzohn.domain.exception.issue.AddressIssue;
import com.khunzohn.domain.exception.issue.EmailIssue;
import com.khunzohn.domain.exception.issue.NameIssue;
import com.khunzohn.domain.exception.issue.PhoneIssue;
import com.khunzohn.domain.exception.issue.SSNIssue;
import java.util.List;

/**
 * Created by codigo on 29/1/18.
 */

public class StudentIssueManager extends DomainIssueManager<StudentException, StudentIssueHandler> {
  @Override public StudentException asException() {
    return new StudentException(this);
  }

  @Override protected void delegate(List<DomainIssue> openIssues, StudentIssueHandler issueHandler) {
    for (DomainIssue issue : openIssues) {
      if (issue instanceof PhoneIssue) {
        issueHandler.handle((PhoneIssue) issue);
      } else if (issue instanceof NameIssue) {
        issueHandler.handle((NameIssue) issue);
      } else if (issue instanceof AddressIssue) {
        issueHandler.handle((AddressIssue) issue);
      } else if (issue instanceof SSNIssue) {
        issueHandler.handle((SSNIssue) issue);
      } else if (issue instanceof EmailIssue) {
        issueHandler.handle((EmailIssue) issue);
      }
    }
  }
}
