package ke.co.kinyua.forzateams.web.result_models;

/**
 * Created by Kinyua on 2/18/18.
 */

public class Team {
    private String name;
    private boolean national;
    private String country_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNational() {
        return national;
    }

    public void setNational(boolean national) {
        this.national = national;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
