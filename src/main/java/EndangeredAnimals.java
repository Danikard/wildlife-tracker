import java.util.List;
import java.util.Timer;
import org.sql2o.*;
import java.sql.Timestamp;


public class EndangeredAnimals {

    private String name;
    public boolean is_endagered;
    private int id;
    private String health;
    private int age;

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

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object otherEndangeredAnimal){
        if (!(otherEndangeredAnimal instanceof EndangeredAnimals)) {
            return false;
        } else {
            EndangeredAnimals newEndangeredAnimal = (EndangeredAnimals) otherEndangeredAnimal;
            return this.getName().equals(newEndangeredAnimal.getName());

        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangered_animals (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<EndangeredAnimals> all() {
        String sql = "SELECT * FROM endangered_animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredAnimals.class);
        }
    }

    public static EndangeredAnimals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id";
            EndangeredAnimals endangeredAnimals= con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimals.class);
            return endangeredAnimals;
        }
    }

    public void updateName(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET = name = :name WHERE id =:id;";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }

}
