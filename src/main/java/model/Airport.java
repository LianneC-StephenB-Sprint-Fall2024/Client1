package model;

public class Airport {
    private Integer id;
    private String code;
    private String name;
    private City city; // Changed to hold a City object

    // No-argument constructor
    public Airport() {}

    // Constructor with parameters
    public Airport(Integer id, String code, String name, City city) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.city = city;
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

    public City getCity() { return city; }


    public void setCity(City city) { this.city = city; }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", city=" + (city != null ? city.toString() : "null") + // Updated for better output
                '}';
    }
}
