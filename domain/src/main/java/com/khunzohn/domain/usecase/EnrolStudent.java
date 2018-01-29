package com.khunzohn.domain.usecase;

import com.khunzohn.domain.exception.issue.AddressIssue;
import com.khunzohn.domain.exception.issue.EmailIssue;
import com.khunzohn.domain.exception.issue.NameIssue;
import com.khunzohn.domain.exception.issue.PhoneIssue;
import com.khunzohn.domain.exception.issue.SSNIssue;
import com.khunzohn.domain.exception.student.StudentIssueManager;
import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import com.khunzohn.domain.model.Enrolment;
import com.khunzohn.domain.model.Student;
import com.khunzohn.domain.repository.StudentRepository;
import com.khunzohn.domain.usecase.base.UseCaseWithIssueManager;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * Created by codigo on 29/1/18.
 */

public class EnrolStudent
    extends UseCaseWithIssueManager<EnrolStudent.Action, Enrolment, StudentIssueManager> {

  private final StudentRepository studentRepository;

  @Inject
  public EnrolStudent(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
      StudentRepository studentRepository) {
    super(threadExecutor, postExecutionThread);
    this.studentRepository = studentRepository;
  }

  @Override protected StudentIssueManager provideIssueManager() {
    return new StudentIssueManager();
  }

  @Override public Observable<Enrolment> execute(Action action) {
    if (isNotValid(action)) {
      return Observable.error(asException());
    }
    return studentRepository.enrolStudent(buildStudentFrom(action));
  }

  @Override public Enrolment progress() {
    return Enrolment.progress();
  }

  @Override public Enrolment error(Throwable throwable) {
    return Enrolment.error(throwable);
  }

  private boolean isNotValid(Action action) {

    if (action.name == null || action.name.trim().equals("")) {
      openIssue(NameIssue.EMPTY);
    } else if (action.name.trim().contains("@")) {
      openIssue(NameIssue.INVALID);
    }

    if (action.phone == null || action.phone.trim().equals("")) {
      openIssue(PhoneIssue.EMPTY);
    } else if (action.phone.length() > 12) {
      openIssue(PhoneIssue.INVALID);
    }

    if (!validateEmail(action.email)) {
      openIssue(EmailIssue.INVALID);
    }

    if (action.adderss == null || action.adderss.equals("")) {
      openIssue(AddressIssue.EMPTY);
    }
    if (action.ssn == null || action.ssn.trim().equals("")) {
      openIssue(SSNIssue.EMPTY);
    } else if (action.ssn.length() > 12) {
      openIssue(SSNIssue.MORE_THAN_12_CHAR);
    }
    return hasIssues();
  }

  private boolean validateEmail(String email) {
    return email != null && email.contains("@") && !email.trim().equals("") && email.length() > 5;
  }

  private Student buildStudentFrom(Action action) {
    return new Student(action.name, action.phone, action.email, action.adderss, action.ssn);
  }

  public static class Action {
    public final String name;
    public final String phone;
    public final String email;
    public final String adderss;
    public final String ssn;

    public Action(String name, String phone, String email, String adderss, String ssn) {
      this.name = name;
      this.phone = phone;
      this.email = email;
      this.adderss = adderss;
      this.ssn = ssn;
    }
  }
}
