package com.khunzohn.data.datasource;

/**
 * Created by khunzohn on 12/23/17.
 */

public abstract class Mapper<D, E> {

  public abstract D map(E entity);
}
