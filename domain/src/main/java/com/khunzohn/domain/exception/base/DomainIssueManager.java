package com.khunzohn.domain.exception.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by khunzohn on 10/24/17.
 */

public abstract class DomainIssueManager<E extends DomainException, H extends DomainIssueHandler> {

  private final Map<DomainIssue, Boolean> issueMap;

  public DomainIssueManager() {
    issueMap = new LinkedHashMap<>();
  }

  public final void delegate(H issueHandler) {
    delegate(getOpenIssues(),issueHandler);
  }

  public final void openIssue(DomainIssue issue) {
    issueMap.put(issue, true);
  }

  public abstract E asException();

  public final boolean hasIssues() {

    for (Map.Entry<DomainIssue, Boolean> entry : issueMap.entrySet()) {
      if (entry.getValue()) return true;
    }
    return false;
  }

  public final void closeIssues() {
    issueMap.clear();
  }

  protected abstract void delegate(List<DomainIssue> openIssues, H issueHandler);

  private List<DomainIssue> getOpenIssues() {
    List<DomainIssue> openIssues = new ArrayList<>();
    for (DomainIssue issue : issueMap.keySet()) {
      if (isOpen(issue)) {
        openIssues.add(issue);
      }
    }
    return openIssues;
  }

  private boolean isOpen(DomainIssue issue) {
    return issueMap.get(issue) == null ? false : issueMap.get(issue);
  }
}
