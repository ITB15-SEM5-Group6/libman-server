package at.fhv.itb.sem5.team6.libman.server.model;

import java.io.Serializable;

public interface Identifiable<T extends Serializable> {
    T getId();

    void setId(T id);
}
