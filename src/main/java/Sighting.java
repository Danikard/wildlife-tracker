import java.util.List;
import java.util.Timer;
import org.sql2o.*;
import java.sql.Timestamp;

public class Sighting {
    private int animal_id;
    private String rangers_name;
    private int id;
    private String location;
    private Timestamp date;

    public Sighting(int animal_id, String rangers_name, int id, String location, Timestamp date) {
        this.animal_id = animal_id;
        this.rangers_name = rangers_name;
        this.id = id;
        this.location = location;
        this.date = date;
    }
    public int getAnimal_id(){
        return animal_id;
    }
    public String getRangers_name(){
        return rangers_name;
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
            return this.getAnimal_id()==(newSighting.getAnimal_id())&&this.getLocation()==(newSighting.getLocation())&&this.getRangers_name()==(newSighting.getRangers_name());

        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangered_animals (animal_id,location,rangers_name,date) VALUES (:animal_id,:location,:rangers_name,now();)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animal_id", this.animal_id)
                    .addParameter("location", this.location)
                    .addParameter("rangers_name", this.rangers_name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> all() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings";
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id=:id";
            Sighting sightings = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sightings;
        }
    }

}
