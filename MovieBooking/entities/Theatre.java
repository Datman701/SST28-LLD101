package entities;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    public String id;
    public String name;
    public City city;
    public List<Screen> screens = new ArrayList<>();

    public Theatre(String id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
}
