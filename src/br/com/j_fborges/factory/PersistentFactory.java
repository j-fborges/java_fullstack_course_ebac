package br.com.j_fborges.factory;

import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.exception.InvalidDataException;

public interface PersistentFactory {

    Persistent createObject(String[] data) throws InvalidDataException;

}
