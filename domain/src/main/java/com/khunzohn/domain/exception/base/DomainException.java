package com.khunzohn.domain.exception.base;

/**
 * Created by khunzohn on 10/25/17.
 */

public abstract class DomainException extends Exception {

  private final DomainIssueManager domainIssueManager;

  public DomainException(DomainIssueManager domainIssueManager) {
    this.domainIssueManager = domainIssueManager;
  }

  public final DomainIssueManager getDomainIssueManager() {
    return domainIssueManager;
  }
}
