import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import org.sql2o.Sql2o;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args){
        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Animal> animals = Animal.all();
            List<EndangeredAnimals> endangeredAnimals =EndangeredAnimals.all();
            List<Sighting> sightings =Sighting.all();
            model.put("animals", animals);
            model.put("endangeredAnimals",endangeredAnimals);
            model.put("sightings",sightings);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
