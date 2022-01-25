package ohtuesimerkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void containsPlayer() {
        assertEquals("Semenko", stats.search("Semenko").getName());
    }

    @Test
    public void notContainPlayer() {
        assertNull(stats.search("Markus"));
    }

    @Test
    public void playersOfTeamAllContained() {
        assertEquals(3, stats.team("EDM").size());
    }

    @Test
    public void topScorersWork() {
        assertEquals("Gretzky", stats.topScorers(1).get(0).getName());
    }

    @Test
    public void correctAssist() {
        assertEquals(12, stats.search("Semenko").getAssists());
    }
    
    @Test
    public void correctGoals() {
        assertEquals(4, stats.search("Semenko").getGoals());
    }

    @Test
    public void setAssistWorks() {
        stats.search("Semenko").setAssists(2);
        assertEquals(2, stats.search("Semenko").getAssists());
    }

    @Test
    public void setGoalWorks() {
        stats.search("Semenko").setGoals(3);
        assertEquals(3, stats.search("Semenko").getGoals());
    }

    @Test
    public void setNameWorks() {
        stats.search("Semenko").setName("Mikkonen");
        assertEquals("Mikkonen", stats.search("Mikkonen").getName());
    }

    @Test
    public void setTeamWorks() {
        stats.search("Semenko").setTeam("YIK");
        assertEquals("YIK", stats.search("Semenko").getTeam());
    }

    @Test
    public void correctToString() {
        assertEquals("Semenko              EDM  4 + 12 = 16", stats.search("Semenko").toString());
    }
}
