import org.junit.Test;
import static org.junit.Assert.*;


public class SightingTest {
    @Test
    public void Sighting_Instantiating_correctly() {
        Sighting sighting = new Sighting("zone A","Kim");
        assertEquals(true, sighting instanceof Sighting);
    }
    @Test
    public void all_returnsAllInstancesOfSihgting_true() {
        Sighting firstSighting = new Sighting("zone A","Kim");
        Sighting secondSighting = new Sighting("zone B","Paul");
        assertEquals(true, Sighting.all().get(0).equals(firstSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }

    @Test
    public void Animals_instantiatesWithLocation_String(){
        Sighting sighting  = new Sighting("Zone B", "Park");
        assertEquals(sighting.getLocation(), "Park");
    }
    @Test
    public void Animals_instantiatesWithRanger_name_String(){
        Sighting sighting  = new Sighting("zone A", "Kim");
        assertEquals(sighting .getRanger_name(), "Kim");
    }

    @Test
    public void equals_returnsTrueIfNamesAreTheSame(){
        Sighting sighting = new Sighting ("Zone C", "Paul");
        Sighting  firstsighting = new Sighting ("Zone C", "Paul");
        assertTrue(sighting .equals(firstsighting));
    }

}
