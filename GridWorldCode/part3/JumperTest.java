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
    private Grid<Actor> g=new BoundedGrid(9,9);
    private Jumper j1=new Jumper();
    private Jumper j2=new Jumper();
    private Rock r = new Rock();
    private Flower f = new Flower();
    
    public void testNormalMove()
    {
    	j1.putSelfInGrid(g,new Location(4,4)); // put into Grid, default to north
    	j1.act(); // act
    	assertTrue(j1.getLocation().equals(new Location(2,4))&&(j1.getDirection()==0)); // test Location and Direction
    }

    public void testJumpOverObject()
    {
    	// jump across Rock
    	j1.putSelfInGrid(g,new Location(5,5)); // put Jumper into Grid, default to north
    	r.putSelfInGrid(g,new Location(4,5)); // put Rock into Grid
    	j1.act();
    	assertTrue(j1.getLocation().equals(new Location(3,5))); //test Location
    	assertEquals(j1.getDirection(), 0); // test Direction
    	// jump across Flower
    	f.putSelfInGrid(g,new Location(2,5)); // put flower into Grid
    	j1.act();
    	assertTrue(j1.getLocation().equals(new Location(1,5))); //test Location
    	assertEquals(j1.getDirection(), 0); // test Direction
    }

    public void testStopByObject()
    {
    	// Stop across Rock
    	j1.putSelfInGrid(g,new Location(3,3)); // put Jumper into Grid, default to north
    	r.putSelfInGrid(g,new Location(1,3)); // put Rock into Grid
    	j1.act();
    	assertTrue(j1.getLocation().equals(new Location(3,3))); //test Location
    	assertEquals(j1.getDirection(), 45); // test Direction
    	
    	// Stop across Flower
    	f.putSelfInGrid(g,new Location(1,5)); // put flower into Grid
    	j1.act();
    	assertTrue(j1.getLocation().equals(new Location(3,3))); //test Locaion
    	assertEquals(j1.getDirection(), 90); // test Direction
    }
    
    public void testStopByEdge()
    {
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
