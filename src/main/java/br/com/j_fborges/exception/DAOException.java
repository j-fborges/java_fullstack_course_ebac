package br.com.j_fborges.exception;

public class DAOException extends RuntimeException {

    private static final long serialVersionUID = 7054379063290825137L;

    public DAOException(String message, Exception e) {
        super(message);
    }
}
