package model;

public class Airport {
    private Integer id;
    private String code;
    private String name;
    private City city; // Holds a City object
    private String location;

    // No-argument constructor
    public Airport() {}

    // Constructor with all parameters
    public Airport(Integer id, String code, String name, City city, String location) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.city = city;
        this.location = location;
    }

    // Constructor for testing, with only id, code, and name
    public Airport(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Airport(int i, String jfk, String s, City mockCity) {
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", city=" + (city != null ? city.toString() : "null") +
                ", location='" + location + '\'' +
                '}';
    }
}
