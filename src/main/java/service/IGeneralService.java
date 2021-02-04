package service;

import java.util.List;

public interface IGeneralService<E> {
    List<E> findAll();

    boolean update(int id, E e);

    void add(E e);

    boolean delete(int id);

    E findById(int id);
}
