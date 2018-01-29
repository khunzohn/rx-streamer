package com.khunzohn.rxstreamer.feature.student;

import com.khunzohn.domain.model.Enrolment;
import com.khunzohn.domain.usecase.EnrolStudent;
import com.khunzohn.rxstreamer.feature.BaseViewModel;
import com.khunzohn.rxstreamer.mapper.EnrolmentViewStateMapper;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import javax.inject.Inject;

/**
 * Created by codigo on 29/1/18.
 */

public class AddStudentViewModel extends BaseViewModel {

  private final EnrolStudent enrolStudent;
  private final EnrolmentViewStateMapper enrolmentViewStateMapper;
  private String name;
  private String address;
  private String phone;
  private String ssn;
  private String email;

  private final PublishSubject<EnrolmentViewState> enrolmentSubject = PublishSubject.create();

  @Inject
  public AddStudentViewModel(EnrolStudent enrolStudent,
      EnrolmentViewStateMapper enrolmentViewStateMapper) {
    this.enrolStudent = enrolStudent;
    this.enrolmentViewStateMapper = enrolmentViewStateMapper;
  }

  public void setName(String name) {
    this.name = name;
  }

  void setAddress(String address) {
    this.address = address;
  }

  void setEmail(String email) {
    this.email = email;
  }

  void setSsn(String ssn) {
    this.ssn = ssn;
  }

  void setPhone(String phone) {
    this.phone = phone;
  }

  void enrolStudent() {
    EnrolStudent.Action action = new EnrolStudent.Action(name, phone, email, address, ssn);
    add(Observable.just(action)
        .compose(enrolStudent)
        .map(enrolmentViewStateMapper::map) //change domain model to view state
        .subscribe(enrolmentSubject::onNext, e -> {
          Enrolment errorModel = Enrolment.error(e);
          enrolmentSubject.onNext(enrolmentViewStateMapper.map(errorModel));
        }));
  }

  Observable<EnrolmentViewState> getEnrolmentViewState() {
    return enrolmentSubject;
  }
}
