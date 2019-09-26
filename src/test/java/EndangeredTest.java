import org.junit.Test;
import static org.junit.Assert.*;

public class EndangeredTest {
    @Test    public void Endangered_Instantiating_correctly() {
        Endangered endangered = new Endangered("rakoon","ill","newborn");
        assertEquals(true, endangered instanceof Endangered);
    }
    @Test
    public void all_returnsAllInstancesOfEndangered_true() {
        Endangered firstEndangered = new Endangered("rakoon","ill","newborn");
        Endangered secondEndangered = new Endangered("rat","healthy","adult");
        assertEquals(true, Endangered.all().get(0).equals(firstEndangered));
        assertEquals(true, Endangered.all().get(1).equals(secondEndangered));
    }
    @Test
    public void Animals_instantiatesWithName_String(){
        Endangered endangered  = new Endangered("panther", "okay", "young");
        assertEquals(endangered.getName(), "panther");
    }
    @Test
    public void Animals_instantiatesWithHealth_String(){
        Endangered endangered  = new Endangered("panther", "ill", "young");
        assertEquals(endangered .getHealth(), "ill");
    }
    @Test
    public void equals_returnsTrueIfNamesAreTheSame(){
        Endangered endangered = new Endangered("snake", "healthy", "young");
        Endangered secondEndangered = new Endangered("snake", "healthy", "young");
        assertTrue(endangered .equals(secondEndangered));
    }

}




