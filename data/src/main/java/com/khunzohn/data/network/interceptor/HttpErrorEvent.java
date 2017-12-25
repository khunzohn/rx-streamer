package com.khunzohn.data.network.interceptor;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by khunzohn on 12/25/17.
 */

public class HttpErrorEvent {
  private static final PublishSubject<Event> eventSubject = PublishSubject.create();

  public static Disposable subscribe(Consumer<Event> consumer) {
    return eventSubject.subscribe(consumer);
  }

  static void emit(Event event) {
    eventSubject.onNext(event);
  }

  public static class Event {
    private final String message;
    private final Type type;

    Event(String message, Type type) {
      this.message = message;
      this.type = type;
    }

    public String getMessage() {
      return message;
    }

    public boolean is(Type type) {
      return this.type == type;
    }

    public Type getType() {
      return type;
    }
  }

  public enum Type {
    UNAUTHORIZED, SERVER_ERROR
  }
}
