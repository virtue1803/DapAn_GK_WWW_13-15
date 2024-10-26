package dev.skyherobrine.app.backend.repositories;

import java.util.List;
import java.util.Optional;

public interface IRepository<T, P> {
    T insert(T t);
    T update(T t);
    T delete(P p);
    Optional<T> findById(P p);
    List<T> findAll();
}
