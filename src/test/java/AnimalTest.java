import org.junit.Rule;
import org.junit.Test;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal();
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    public void Animal_instantiatesWithName_String() {
        Animal testAnimal = new Animal();
        assertEquals("Bubbles", testAnimal.getName());
    }

    @Test
    public void Animal_instantiatesWithPersonId_int() {
        Animal testAnimal = new Animal();
        assertEquals(1, testAnimal.getId());
    }

    @Test
    public void equals_returnsTrueIfNameAndSightingIdAreSame_true() {
        Animal testAnimal = new Animal();
        Animal anotherAnimal = new Animal();
        assertTrue(testAnimal.equals(anotherAnimal));
    }

    @Test
    public void save_returnsTrueIfDescriptionsAretheSame() {
        Animal testAnimal = new Animal();
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }

    @Test
    public void save_assignsIdToAnimal() {
        Animal testAnimal = new Animal();
        testAnimal.save();
        Animal savedMonster = Animal.all().get(0);
        assertEquals(savedMonster.getId(), testAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal testAnimal = new Animal();
        Animal secondAnimal = new Animal();
        testAnimal.save();
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(testAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId_otherSecondAnimal() {
        Animal testAnimal = new Animal();
        Animal otherSecondAnimal = new Animal();
        testAnimal.save();
        otherSecondAnimal.save();
        assertEquals(Animal.find(otherSecondAnimal.getId()), otherSecondAnimal);
    }

    @Test
    public void save_savesSightingIdIntoDB_true() {
        Sighting testSighting = new Sighting(1, "Henry",2,"zone B");
        testSighting.save();
        Animal testAnimal2 = new Animal();
        testAnimal2.save();
        Animal savedAnimal = Animal.find(testAnimal2.getId());
        assertEquals(savedAnimal.getId(), testSighting.getId());
    }

}



