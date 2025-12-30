package br.com.j_fborges.exception;

public class NonUniqueEntryException extends RuntimeException {

    private static final long serialVersionUID = -7509649433607067138L;

    public NonUniqueEntryException(String message) {
        super(message);
    }
}
