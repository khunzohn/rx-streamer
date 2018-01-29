package com.khunzohn.data.repository;

import com.khunzohn.data.exception.DataException;
import com.khunzohn.data.exception.Issue;
import com.khunzohn.domain.model.Enrolment;
import com.khunzohn.domain.model.Student;
import com.khunzohn.domain.repository.StudentRepository;
import io.reactivex.Observable;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

/**
 * Created by codigo on 29/1/18.
 */

public class StudentRepositoryImpl implements StudentRepository {

  @Inject public StudentRepositoryImpl() {
  }

  @Override public Observable<Enrolment> enrolStudent(Student student) {
    /*if (new Random().nextBoolean()) {
      return Observable.error(
          new DataException(Issue.NETWORK)); // simulate occasional network error
    }

    if (new Random().nextBoolean()) {
      return Observable.error(new DataException(Issue.API)); //simulate occasional api error
    }*/

    return Observable.just(Enrolment.success(10, "10/2/2017", "enrolled", buidStudent()))
        .delay(2, TimeUnit.SECONDS);
  }

  private Student buidStudent() {
    return new Student("Mg mg", "09448239547587", "mgmg@gmail.com", "Taunggyi", "123212323");
  }
}
