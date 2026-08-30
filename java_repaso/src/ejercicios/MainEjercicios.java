package ejercicios;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ejercicios.user_registry.models.User;

public class MainEjercicios {
    public static void main(String[] args) {
        Map<Long, User> registry = new HashMap<>();
        registry.put(1L, new User(1, "William", "Cuba"));
        registry.put(2L, new User(2, "Juan Carlos", "Cuba"));
        registry.put(3L, new User(3, "Ana", "Spain"));
        registry.put(4L, new User(4, "Jose", "Spain"));
        registry.put(5L, new User(5, "Francois", "Francia"));
        registry.put(6L, new User(6, "Jean Pierre", "Francia"));

        Set<String> countries = new HashSet<>();
        Object[] users = registry.values().toArray();

        for (Object object : users) {
            System.out.println(object.toString());
        }
    }
}
