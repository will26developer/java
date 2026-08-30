package ejercicios.user_registry.models;

public class User {
    private final long id;
    private final String name;
    private final String country;

    public User(long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "User(id=" + id + ", name=" + name + ", country=" + country + ")";
    }
}
