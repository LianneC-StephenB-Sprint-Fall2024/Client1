package model;

public class City {
    private Integer id;
    private String name;
    private String state;
    private Integer population;

    // No-argument constructor
    public City() {}

    // Constructor with parameters
    public City(Integer id, String name, String state, Integer population) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.population = population;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", population=" + population +
                '}';
    }
}
