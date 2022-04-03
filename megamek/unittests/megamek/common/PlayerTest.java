package megamek.common;

import megamek.client.ui.swing.util.PlayerColour;
import megamek.common.enums.RatingModifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PlayerTest {
    private static final int STANDARD_RATING = 1000;


    @Test
    public void testGetColorForPlayer() {
        String playerName = "jefke";
        Player player = new Player(0, playerName);
        assertEquals("<B><font color='8080b0'>" + playerName + "</font></B>", player.getColorForPlayer());

        playerName = "Jeanke";
        Player player2 = new Player(1, playerName);
        player2.setColour(PlayerColour.FUCHSIA);
        assertEquals("<B><font color='f000f0'>" + playerName + "</font></B>", player2.getColorForPlayer());

    }

    @Test
    public void testSetRatingWinner() {
        Player player1 = new Player(1, "player1");
        player1.updateRating(RatingModifier.WINNER.getRatingModifier());
        assertEquals(player1.getRating(),STANDARD_RATING + RatingModifier.WINNER.getRatingModifier());
    }

    @Test
    public void testSetRatingTeamWinner(){
        Player player1 = new Player(1, "player1");
        player1.updateRating(RatingModifier.TEAM_WINNER.getRatingModifier());
        assertEquals(player1.getRating(),STANDARD_RATING + RatingModifier.TEAM_WINNER.getRatingModifier());
    }

    @Test
    public void testSetRatingLoser(){
        Player player1 = new Player(1, "player1");
        player1.updateRating(RatingModifier.LOSER.getRatingModifier());
        assertEquals(player1.getRating(),STANDARD_RATING + RatingModifier.LOSER.getRatingModifier());
    }
}
