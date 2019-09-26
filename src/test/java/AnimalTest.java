import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal("lion");
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    public void Animal_instantiatesWithName_String() {
        Animal testAnimal = new Animal("rakoon");
        assertEquals("rakoon", testAnimal.getName());
    }


    @Test
    public void equals_returnsTrueIfNameAndSightingIdAreSame_true() {
        Animal testAnimal = new Animal("panther");
        Animal anotherAnimal = new Animal("panther");
        assertTrue(testAnimal.equals(anotherAnimal));
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal testAnimal = new Animal("rat");
        Animal secondAnimal = new Animal("kangaroo");
        testAnimal.save();
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(testAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

}



