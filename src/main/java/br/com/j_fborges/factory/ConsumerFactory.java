package br.com.j_fborges.factory;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.exception.InvalidDataException;

public class ConsumerFactory implements PersistentFactory {

    @Override
    public Persistent createObject(String[] parsedInputs) throws InvalidDataException {
        try {
            if(parsedInputs.length >= 9) {
                return new Consumer(parsedInputs[0],parsedInputs[1],parsedInputs[2],parsedInputs[3],parsedInputs[4],parsedInputs[5],parsedInputs[6], parsedInputs[7], parsedInputs[8]);
            } else {
                return new Consumer(parsedInputs[0],parsedInputs[1],parsedInputs[2],parsedInputs[3],parsedInputs[4],parsedInputs[5],parsedInputs[6], parsedInputs[7]);
            }
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidDataException("Consumer Data are invalid");
        }

    }

    @Override
    public Persistent createEmptyObject() {
        return new Consumer();
    }
}
