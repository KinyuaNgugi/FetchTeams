package ke.co.kinyua.forzateams.adapters.models;

/**
 * Created by Kinyua on 2/18/18.
 */

public class TeamModel {
    private String club_name;
    private boolean is_national;
    private String country_name;

    public TeamModel (String club_name, boolean is_national, String country_name ) {
        this.club_name = club_name;
        this.is_national = is_national;
        this.country_name = country_name;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public boolean isIs_national() {
        return is_national;
    }

    public void setIs_national(boolean is_national) {
        this.is_national = is_national;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
