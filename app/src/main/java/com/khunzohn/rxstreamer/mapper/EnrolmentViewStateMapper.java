package com.khunzohn.rxstreamer.mapper;

import android.content.Context;
import android.view.View;
import com.khunzohn.data.exception.DataException;
import com.khunzohn.domain.exception.issue.AddressIssue;
import com.khunzohn.domain.exception.issue.EmailIssue;
import com.khunzohn.domain.exception.issue.NameIssue;
import com.khunzohn.domain.exception.issue.PhoneIssue;
import com.khunzohn.domain.exception.issue.SSNIssue;
import com.khunzohn.domain.exception.student.StudentException;
import com.khunzohn.domain.exception.student.StudentIssueHandler;
import com.khunzohn.domain.exception.student.StudentIssueManager;
import com.khunzohn.domain.model.Enrolment;
import com.khunzohn.domain.model.Student;
import com.khunzohn.rxstreamer.R;
import com.khunzohn.rxstreamer.feature.student.EnrolmentViewState;
import com.khunzohn.rxstreamer.util.IssueFactory;
import com.khunzohn.rxstreamer.util.Visibility;
import javax.inject.Inject;

/**
 * Created by codigo on 29/1/18.
 */

public class EnrolmentViewStateMapper extends ViewStateMapper<EnrolmentViewState, Enrolment> {

  private final IssueFactory issueFactory;
  private String nameError;
  private String phoneError;
  private String ssnError;
  private String addressError;
  private String emailError;

  private Student student;
  private long id;
  private String status;
  private String enrolDate;

  @Visibility private int progressVisiblility;
  @Visibility private int enrolButtonVisibility;

  private boolean hasDataError;
  private boolean shouldRetry;

  private String dataErrorMessage;

  @Inject public EnrolmentViewStateMapper(Context context, IssueFactory issueFactory) {
    super(context);
    this.issueFactory = issueFactory;
  }

  @Override public EnrolmentViewState map(Enrolment domainModel) {
    reset();
    switch (domainModel.state) {
      case SUCCESS:
        student = domainModel.student;
        status = domainModel.status;
        id = domainModel.id;
        enrolDate = domainModel.enrolDate;
        break;
      case PROGRESS:
        progressVisiblility = View.VISIBLE;
        enrolButtonVisibility = View.GONE;
        break;
      case ERROR:
        handleError(domainModel);
        break;
    }
    return new EnrolmentViewState(nameError, phoneError, ssnError, addressError, emailError,
        student, id, status, enrolDate, progressVisiblility, enrolButtonVisibility, hasDataError,
        shouldRetry, dataErrorMessage);
  }

  private void handleError(Enrolment domainModel) {
    if (domainModel.error instanceof StudentException) {
      handleDomainError((StudentException) domainModel.error);
    } else if (domainModel.error instanceof DataException) {
      handleDataError((DataException) domainModel.error);
    } else {
      hasDataError = true;
      dataErrorMessage = domainModel.error.getLocalizedMessage();
    }
  }

  private void handleDomainError(StudentException studentException) {
    StudentIssueManager issueManager =
        (StudentIssueManager) studentException.getDomainIssueManager();
    issueManager.delegate(new StudentIssueHandler() {
      @Override public void handle(PhoneIssue phoneIssue) {
        phoneError = getContext().getString(R.string.error_message_invalid_phone);
        if (phoneIssue == PhoneIssue.INVALID) {
        } else if (phoneIssue == PhoneIssue.EMPTY) {
        }
      }

      @Override public void handle(NameIssue nameIssue) {
        nameError = "Name is invalid";
        if (nameIssue == NameIssue.INVALID) {
        }
      }

      @Override public void handle(EmailIssue emailIssue) {
        emailError = "Email is empty";
        if (emailIssue == EmailIssue.EMPTY) {
        }
      }

      @Override public void handle(AddressIssue addressIssue) {
        addressError = "Address is invalid";
        if (addressIssue == AddressIssue.INVALID) {
        }
      }

      @Override public void handle(SSNIssue ssnIssue) {
        ssnError = "SSN has more than 12 character";
        if (ssnIssue == SSNIssue.MORE_THAN_12_CHAR) {
        }
      }
    });
  }

  private void handleDataError(DataException dataException) {
    hasDataError = true;
    shouldRetry = dataException.shouldRetry();
    dataErrorMessage = issueFactory.getMessage(dataException.getIssue());
  }

  private void reset() {
    nameError = null;
    phoneError = null;
    ssnError = null;
    addressError = null;
    emailError = null;
    student = null;
    id = -1;
    status = null;
    enrolDate = null;

    progressVisiblility = View.GONE;
    dataErrorMessage = null;
    enrolButtonVisibility = View.VISIBLE;
    shouldRetry = false;
    hasDataError = false;
  }
}
