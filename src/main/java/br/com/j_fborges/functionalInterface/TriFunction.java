package br.com.j_fborges.functionalInterface;

import java.sql.SQLException;

@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v) throws SQLException;
}
