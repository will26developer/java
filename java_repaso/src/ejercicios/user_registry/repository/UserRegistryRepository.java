package ejercicios.user_registry.repository;

import java.util.List;
import java.util.Map;

import ejercicios.user_registry.exception.UserExistsException;
import ejercicios.user_registry.exception.UserNoExistsException;
import ejercicios.user_registry.models.User;

public interface UserRegistryRepository<T> {
    void add(T t) throws UserExistsException;

    T findById(long id);

    List<String> findByCountry(String country);

    Map<String, List<User>> groupByCountry();

    void remove(long id) throws UserNoExistsException;

    int size();
}
