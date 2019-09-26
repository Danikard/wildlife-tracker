import java.util.List;

import org.sql2o.*;


public class Endangered {

    private String name;
    public boolean is_endagered;
    private int id;
    private String health;
    private String age;

    public Endangered(String name, String health, String age) {
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
        if (!(otherEndangeredAnimal instanceof Endangered)) {
            return false;
        } else {
            Endangered newEndangeredAnimal = (Endangered) otherEndangeredAnimal;
            return this.getName().equals(newEndangeredAnimal.getName())&&this.getHealth().equals(newEndangeredAnimal.getHealth())&&this.getAge().equals(newEndangeredAnimal.getAge());

        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangered_animals (name,health,age) VALUES (:name,:health,:age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Endangered> all() {
        String sql = "SELECT * FROM endangered_animals";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Endangered.class);
        }
    }

}