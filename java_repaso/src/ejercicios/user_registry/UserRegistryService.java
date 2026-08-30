package ejercicios.user_registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ejercicios.user_registry.exception.UserExistsException;
import ejercicios.user_registry.exception.UserNoExistsException;
import ejercicios.user_registry.models.User;
import ejercicios.user_registry.repository.UserRegistryRepository;

public class UserRegistryService implements UserRegistryRepository<User> {

    private final Map<Long, User> registry;

    public UserRegistryService() {
        this.registry = new HashMap<>();
    }

    @Override
    public void add(User t) throws UserExistsException {
        User userFinded = this.findById(t.getId());
        if (userFinded != null) {
            throw new UserExistsException("Usuario ya existente");
        }
        this.registry.put(t.getId(), t);
    }

    @Override
    public List<String> findByCountry(String country) {
        List<String> usersByCountry = new ArrayList<>();
        for (User user : registry.values()) {
            if (user.getCountry().equals(country)) {
                usersByCountry.add(user.getName());
            }
        }
        return usersByCountry;
    }

    @Override
    public User findById(long id) {
        return registry.get(id);
    }

    @Override
    public void remove(long id) throws UserNoExistsException {
        User userFind = this.findById(id);
        if (userFind == null) {
            throw new UserNoExistsException("Usuario no existente o no encontrado");
        }
        registry.remove(id, userFind);
    }

    @Override
    public int size() {
        return registry.size();
    }

    @Override
    public Map<String, List<User>> groupByCountry() {
        Map<String, List<User>> byCountry = new HashMap<>();
        Set<String> countries = new HashSet<>();

        for (User user : registry.values()) {
            countries.add(user.getCountry());
        }

        for (String country : countries) {
            List<User> usersByCountry = new ArrayList<>();
            for (User user : registry.values()) {
                if (user.getCountry().equals(country)) {
                    usersByCountry.add(user);
                }
            }
            byCountry.put(country, usersByCountry);
        }

        return byCountry;
    }

}
