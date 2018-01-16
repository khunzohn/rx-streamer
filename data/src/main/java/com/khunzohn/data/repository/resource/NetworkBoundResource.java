package com.khunzohn.data.repository.resource;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Reactive version of Google's NetworkBoundResource that conforms to
 * the Single Source of Truth principle
 *
 * Created by khunzohn on 12/1/18.
 */
public abstract class NetworkBoundResource<LocalType, RemoteType> {

  private final Subject<Resource<LocalType>> result = PublishSubject.create();

  /**
   * Disposable to keep track of database subscriptions
   */
  private Disposable dbDisposable = null;
  /**
   * Disposable to keep track of network call subscriptions
   */
  private Disposable apiDisposable = null;

  public NetworkBoundResource() {

    emitProgress(null);

    final Flowable<LocalType> dbSource = loadFromDb();

    addDbSubscription(dbSource.subscribe(initLocalType -> {
      disposeDbSubscription();
      if (shouldFetch(initLocalType)) {
        fetchFromNetwork(dbSource);
      } else {
        addDbSubscription(dbSource.subscribe(this::emitSuccess));
      }
    }));
  }

  protected abstract void saveCallResult(@NonNull RemoteType item);

  // Called with the data in the database to decide whether it should be
  // fetched from the network.
  @MainThread protected abstract boolean shouldFetch(@Nullable LocalType data);

  // Called to get the cached data from the database
  @NonNull @MainThread protected abstract Flowable<LocalType> loadFromDb();

  @NonNull protected abstract Single<RemoteType> createCall();

  // Called when the fetch fails. The child class may want to reset components
  // like rate limiter.
  protected void onFetchFailed() {
  }

  public final Flowable<Resource<LocalType>> getAsFlowable() {
    return result
        .doOnDispose(this::disposeBoth) // dispose all subscription on disposing the stream
        .doOnTerminate(this::disposeBoth) // dispose all subscription on terminating the stream
        .toFlowable(BackpressureStrategy.LATEST);
  }

  private void fetchFromNetwork(final Flowable<LocalType> dbSource) {
    Single<RemoteType> apiResponse = createCall();

    // we re-subscribe dbSource as a new source,
    // it will dispatch its latest value quickly
    addDbSubscription(dbSource.subscribe(this::emitProgress));

    addApiSubscription(apiResponse.subscribe(remoteType -> {
      disposeBoth();
      saveCallResult(remoteType);
      // we specially request a new stream,
      // otherwise we will get immediately last cached value,
      // which may not be updated with latest results received from network.
      addDbSubscription(loadFromDb().subscribe(this::emitSuccess));
    }, error -> {
      disposeBoth();
      onFetchFailed();
      addDbSubscription(dbSource.subscribe(localType -> emitError(error, localType)));
    }));
  }

  private void emitSuccess(LocalType data) {
    result.onNext(Resource.success(data));
  }

  private void emitError(Throwable error, @Nullable LocalType data) {
    result.onNext(Resource.error(error, data));
  }

  private void emitProgress(@Nullable LocalType data) {
    result.onNext(Resource.progress(data));
  }

  private void disposeBoth() {
    disposeDbSubscription();
    disposeApiSubscription();
  }

  private void addApiSubscription(Disposable disposable) {
    apiDisposable = disposable;
  }

  private void disposeApiSubscription() {
    if (apiDisposable != null && !apiDisposable.isDisposed()) apiDisposable.dispose();
  }

  private void addDbSubscription(Disposable disposable) {
    dbDisposable = disposable;
  }

  private void disposeDbSubscription() {
    if (dbDisposable != null && !dbDisposable.isDisposed()) dbDisposable.dispose();
  }
}
