package nl.bogowonto.app.repository.impl;

import nl.bogowonto.app.models.User;
import nl.bogowonto.app.repository.UserRepository;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    final List<User> users = new ArrayList<>();

    @Override
    public User findByName(final String name) {
        if(!users.isEmpty()) {

            return users.stream()
                    .filter(user -> name.equalsIgnoreCase(user.getName()))
                    .findAny()
                    .orElse(null);
        } else {
            return new User("Test user", "testuser@gmail.com");
        }
    }

    @Override
    public <S extends User> S save(final S s) {
        users.add(s);
        return s;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(final Iterable<S> iterable) {
        users.forEach(user -> users.add(user));
        return iterable;
    }

    @Override
    public Optional<User> findById(final Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(final Long aLong) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return users;
    }

    @Override
    public Iterable<User> findAllById(final Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(final Long aLong) {

    }

    @Override
    public void delete(final User user) {

    }

    @Override
    public void deleteAll(final Iterable<? extends User> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
