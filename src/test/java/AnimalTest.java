import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal animal = new Animal("lion");
        assertEquals(true, animal instanceof Animal);
    }

    @Test
    public void Animal_instantiatesWithName_String() {
        Animal animal = new Animal("rakoon");
        assertEquals("rakoon", animal.getName());
    }


    @Test
    public void equals_returnsTrueIfNameAndSightingIdAreSame_true() {
        Animal animal = new Animal("panther");
        Animal anotherAnimal = new Animal("panther");
        assertTrue(animal.equals(anotherAnimal));
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal animal = new Animal("rat");
        Animal anotherAnimal = new Animal("kangaroo");
        animal.save();
        anotherAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(animal));
        assertEquals(true, Animal.all().get(1).equals(anotherAnimal));
    }

}



