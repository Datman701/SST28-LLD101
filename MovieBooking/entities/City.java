package entities;

import java.util.ArrayList;
import java.util.List;

public class City {
    public String id;
    public String name;
    public List<Theatre> theatres = new ArrayList<>();

    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
