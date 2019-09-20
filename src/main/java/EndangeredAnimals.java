import java.util.List;
import java.util.Timer;
import org.sql2o.*;
import java.sql.Timestamp;


public class EndangeredAnimals {

    private String name;
    public boolean is_endagered;
    private int id;
    private String health;
    private String age;

    public EndangeredAnimals(String name) {
        this.name = name;
        this.id = id;
        this.health = health;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    @Override
    public boolean equals(Object otherEndangeredAnimal) {
        if (!(otherEndangeredAnimal instanceof EndangeredAnimals)) {
            return false;
        } else {
            EndangeredAnimals newEndangeredAnimal = (EndangeredAnimals) otherEndangeredAnimal;
            return this.getName().equals(newEndangeredAnimal.getName());

        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangered_animals (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<EndangeredAnimals> all() {
        String sql = "SELECT * FROM endangered_animals";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredAnimals.class);
        }
    }

    public static EndangeredAnimals find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM endangered_animals WHERE id=:id";
            EndangeredAnimals endangeredAnimals = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimals.class);
            return endangeredAnimals;
        }
    }

    public void updateName(String health, String age) {
        try (Connection con = DB.sql2o.open()) {
            String updateHealth = "UPDATE endangered_animals SET health = :health WHERE id =:id;";
            String updateAge = "UPDATE endangered_animals SET age = :age WHERE id =:id;";
            con.createQuery(updateHealth)
                    .addParameter("health", health)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(updateAge)
                    .addParameter("age", age)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public List<Sighting> getSightings() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE animal_id =: id;";
            List<Sighting> sightings = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Sighting.class);
            return sightings;


        }
    }
}