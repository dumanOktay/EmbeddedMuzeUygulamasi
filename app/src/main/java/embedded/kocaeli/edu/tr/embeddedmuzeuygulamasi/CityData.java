package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

/**
 * Created by oktay on 14.10.2015.
 */
public class CityData {

    String name;

    public CityData(String muzeISim) {
        this.name= muzeISim;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ""+this.name;
    }
}
