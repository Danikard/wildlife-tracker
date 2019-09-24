import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public class Sighting {
    private String ranger_name;
    private int id;
    private String location;
    private Timestamp date;

    public Sighting( String location,String ranger_name) {

        this.ranger_name = ranger_name;
        this.id = id;
        this.location = location;
        this.date = date;
    }

    public String getRanger_name(){
        return ranger_name;
    }
    public String getLocation(){
        return location;
    }

    public int getId() {
        return id;
    }
    public Timestamp getDate(){
        return date;
    }

    @Override
    public boolean equals(Object otherSighting) {
        if (!(otherSighting instanceof Sighting)) {
            return false;
        } else {
            Sighting newSighting = (Sighting) otherSighting;
            return this.getId()==(newSighting.getId())&&this.getLocation()==(newSighting.getLocation())&&this.getRanger_name()==(newSighting.getRanger_name());

        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (ranger_name,location,date) VALUES (:ranger_name,:location,now())";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("ranger_name", this.ranger_name)
                    .addParameter("location", this.location)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> all() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings";
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id=:id";
            Sighting sightings = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Sighting.class);
            return sightings;
        }
    }

}
