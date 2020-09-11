package ItJobs.repository;

import ItJobs.model.City;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CityDAO {

private static final List<City> CITIES = new ArrayList<>();


static {
    initData();
}

    private static void initData() {
    CITIES.add(new City("warszawa", "Warszawa"));
    CITIES.add(new City("krakow", "Kraków"));
    CITIES.add(new City("lodz", "Łódź"));
    CITIES.add(new City("wroclaw", "Wrocław"));
    CITIES.add(new City("katowice", "Katowice"));
    CITIES.add(new City("gdansk", "Gdańsk"));
    CITIES.add(new City("bialystok", "Białystok"));
    CITIES.add(new City("rzeszow", "Rzeszów"));
    CITIES.add(new City("lublin", "Lublin"));
    CITIES.add(new City("czestochowa", "Częstochowa"));
    CITIES.add(new City("bydgoszcz", "Bydgoszcz"));
    CITIES.add(new City("torun", "Toruń"));
    }

    public List<City> getCities (){
    return CITIES;
    }



}
