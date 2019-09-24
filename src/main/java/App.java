import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        ProcessBuilder process = new ProcessBuilder();
        int port;

        // This tells our app that if Heroku sets a port for us, we need to use that port.
        // Otherwise, if they do not, continue using port 4567.

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);


        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("endangeredAnimals", EndangeredAnimals.all());
            model.put("sightings",Sighting.all());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int sightingId = Integer.parseInt(request.queryParams("sightingId"));
            String location = request.queryParams("location");
            String ranger_name = request.queryParams("ranger_name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            Animal animals = new Animal(name);
            animals.save();
            EndangeredAnimals endangeredAnimal = new EndangeredAnimals(name,health,age);
            endangeredAnimal.save();
            Sighting sightings = new Sighting(location,ranger_name);
            sightings.save();
            model.put("age",age);
            model.put("health",health);
            model.put("name",name);
            model.put("sightingId",sightingId);
            model.put("location",location);
            model.put("ranger_name",ranger_name);
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangeredAnimal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "endangered-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangeredAnimal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String ranger_name = request.queryParams("ranger_name");
            String location = request.queryParams("location");
            EndangeredAnimals endangeredAnimal = new EndangeredAnimals(name,health,age);
            endangeredAnimal.save();
            Sighting sightings = new Sighting(location,ranger_name);
            sightings.save();
            model.put("age",age);
            model.put("health",health);
            model.put("name",name);
            model.put("ranger_name",ranger_name);
            model.put("location",location);
            return new ModelAndView(model, "endangered.hbs");
        }, new HandlebarsTemplateEngine());

        get("/display", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("allAnimals", Animal.all());
            model.put("endangeredAnimals", EndangeredAnimals.all());
            model.put("allSightings", Sighting.all());
            return new ModelAndView(model, "display.hbs");
        }, new HandlebarsTemplateEngine());



    }
}
