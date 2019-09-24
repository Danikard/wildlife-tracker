import org.junit.Test;
import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Test    public void EndangeredAnimal_Instantiating_correctly() {
        EndangeredAnimals endangeredAnimal = new EndangeredAnimals("Elephant","healthy","adult");
        assertEquals(true, endangeredAnimal instanceof EndangeredAnimals);
    }
    @Test
    public void all_returnsAllInstancesOfEndangeredAnimal_true() {
        EndangeredAnimals firstEndangeredAnimal = new EndangeredAnimals("Elephant","healthy","adult");
        EndangeredAnimals secondEndangeredAnimal = new EndangeredAnimals("dog","healthy","adult");
        assertEquals(true, EndangeredAnimals.all().get(0).equals(firstEndangeredAnimal));
        assertEquals(true, EndangeredAnimals.all().get(1).equals(secondEndangeredAnimal));
    }
    @Test
    public void Animals_instantiatesWithName_String(){
        EndangeredAnimals endangeredAnimal  = new EndangeredAnimals("Dog", "healthy", "young");
        assertEquals(endangeredAnimal.getName(), "Dog");
    }
    @Test
    public void Animals_instantiatesWithHealth_String(){
        EndangeredAnimals endangeredAnimal  = new EndangeredAnimals("Lion", "healthy", "young");
        assertEquals(endangeredAnimal .getHealth(), "healthy");
    }
    @Test
    public void equals_returnsTrueIfNamesAreTheSame(){
        EndangeredAnimals endangeredAnimal = new EndangeredAnimals("Lion", "healthy", "young");
        EndangeredAnimals secondEndangeredAnimal = new EndangeredAnimals("Lion", "healthy", "young");
        assertTrue(endangeredAnimal .equals(secondEndangeredAnimal));
    }
    @Test
    public void save_returnsTrueIfDescriptionIsSame(){
        EndangeredAnimals testEndangeredAnimal = new EndangeredAnimals("Lion", "healthy", "young");
        testEndangeredAnimal.save();
        assertTrue(EndangeredAnimals.all().get(0).equals(testEndangeredAnimal));
    }}




