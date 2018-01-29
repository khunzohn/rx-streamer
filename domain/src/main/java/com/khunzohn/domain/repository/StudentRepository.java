package com.khunzohn.domain.repository;

import com.khunzohn.domain.model.Enrolment;
import com.khunzohn.domain.model.Student;
import io.reactivex.Observable;

/**
 * Created by codigo on 29/1/18.
 */

public interface StudentRepository {

  Observable<Enrolment> enrolStudent(Student student);
}
