import junit.framework.Test; 
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static junit.framework.Assert.*;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

public class JumperTest extends TestCase
{
    private Grid<Actor> g;
    private Jumper j1;
    private Jumper j2;
    
    public void testNormalMove()
    {
        g = new BoundedGrid(9,9);
    	j1 = new Jumper();
    	j1.putSelfInGrid(g,new Location(0,0)); // put into Grid
    	j1.setDirection(180); // to south
    	j1.act(); // act
    	assertTrue(j1.getLocation().equals(new Location(2,0))); // test Location
    	assertEquals(j1.getDirection(), 180); // test Direction
    }

    public void testJumpOverObject()
    {
        g = new BoundedGrid(9,9);
    	j1 = new Jumper();
    	// jump across Rock
    	j1.putSelfInGrid(g,new Location(0,0)); // put Jumper into Grid
    	j1.setDirection(180); // to south
    	Rock r = new Rock();
    	r.putSelfInGrid(g,new Location(1,0)); // put Rock into Grid
    	j1.act();
    	assertTrue(j1.getLocation().equals(new Location(2,0))); //test Location
    	assertEquals(j1.getDirection(), 180); // test Direction
    	
    	// jump across Flower
    	j1.moveTo(new Location(3,0)); // move Jumper
    	Flower f = new Flower();
    	f.putSelfInGrid(g,new Location(4,0)); // put flower into Grid
    	j1.act();
    	assertTrue(j1.getLocation().equals(new Location(5,0))); //test Location
    	assertEquals(j1.getDirection(), 180); // test Direction
    }

    public void testStopByObject()
    {
        g = new BoundedGrid(9,9);
    	j1 = new Jumper();
    	// Stop across Rock
    	j1.putSelfInGrid(g,new Location(0,0)); // put Jumper into Grid
    	j1.setDirection(180); // to south
    	Rock r = new Rock();
    	r.putSelfInGrid(g,new Location(2,0)); // put Rock into Grid
    	j1.act();
    	assertTrue(j1.getLocation().equals(new Location(0,0))); //test Location
    	assertEquals(j1.getDirection(), 225); // test Direction
    	
    	// Stop across Flower
    	j1.moveTo(new Location(0,4)); // move Jumper
    	Flower f = new Flower();
    	f.putSelfInGrid(g,new Location(2,2)); // put flower into Grid
    	j1.act();
    	assertTrue(j1.getLocation().equals(new Location(0,4))); //test Locaion
    	assertEquals(j1.getDirection(), 270); // test Direction
    }
    
    public void testStopByEdge()
    {
        g = new BoundedGrid(9,9);
    	j1 = new Jumper();
    	j1.putSelfInGrid(g,new Location(7,0)); // put Jumper into Grid
    	j1.setDirection(180); // to south
    	j1.act();
    	assertTrue(j1.getLocation().equals(new Location(7,0))); //test Location
    	assertEquals(j1.getDirection(), 225); // test Direction
    	
    	j1.moveTo(new Location(8,0)); // move Jumper
    	j1.act();
    	assertTrue(j1.getLocation().equals(new Location(8,0))); //test Locaion
    	assertEquals(j1.getDirection(), 270); // test Direction
    } 
    
    public void testMeetJumper()
    {
        g = new BoundedGrid(9,9);
    	j1 = new Jumper();
    	j1.putSelfInGrid(g,new Location(0,0)); // put Jumper1 into Grid
    	j1.setDirection(180); // set Jumper1 to the south
    	j2 = new Jumper();
    	j2.putSelfInGrid(g,new Location(2,0)); // put Jumper2 into Grid
    	j2.setDirection(0); // set Jumper2 to the north
    	j1.act();
    	j2.act();
    	assertTrue(j1.getLocation().equals(new Location(0,0))); //test Location
    	assertEquals(j1.getDirection(), 225); // test Direction
    	assertTrue(j2.getLocation().equals(new Location(2,0))); //test Location
    	assertEquals(j2.getDirection(), 45); // test Direction
    }

    public static Test suite()
    {
	return new TestSuite(JumperTest.class);
    }
	
    public static void main(String[] args)
    {
	junit.textui.TestRunner.run(suite());
    }
}
