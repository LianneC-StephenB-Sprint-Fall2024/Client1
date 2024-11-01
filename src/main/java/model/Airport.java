package model;

public class Airport {
    private Integer id;
    private String code;
    private String name;
    private Integer cityId; // Assuming each airport is linked to a city by city ID

    // No-argument constructor
    public Airport() {}

    // Constructor with parameters
    public Airport(Integer id, String code, String name, Integer cityId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.cityId = cityId;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
