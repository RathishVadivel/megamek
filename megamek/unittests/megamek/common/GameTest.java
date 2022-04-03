package megamek.common;

import megamek.common.enums.RatingModifier;
import megamek.server.victory.VictoryResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Enumeration;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GameTest {

    private static final int STANDARD_RATING = 1000;

    @Test
    public void testCancelVictory() {
        // Default test
        Game game = new Game();
        game.cancelVictory();
        assertFalse(game.isForceVictory());
        assertSame(Player.PLAYER_NONE, game.getVictoryPlayerId());
        assertSame(Player.TEAM_NONE, game.getVictoryTeam());

        // Test with members set to specific values
        Game game2 = new Game();
        game2.setVictoryPlayerId(10);
        game2.setVictoryTeam(10);
        game2.setForceVictory(true);

        game2.cancelVictory();
        assertFalse(game.isForceVictory());
        assertSame(Player.PLAYER_NONE, game.getVictoryPlayerId());
        assertSame(Player.TEAM_NONE, game.getVictoryTeam());
    }

    @Test
    public void testGetVictoryReport() {
        Game game = new Game();
        game.createVictoryConditions();
        VictoryResult victoryResult = game.getVictoryResult();
        assertNotNull(victoryResult);

        // Note: this accessors are tested in VictoryResultTest
        assertSame(Player.PLAYER_NONE, victoryResult.getWinningPlayer());
        assertSame(Player.TEAM_NONE, victoryResult.getWinningTeam());

        int winningPlayer = 2;
        int winningTeam = 5;

        // Test an actual scenario
        Game game2 = new Game();
        game2.setVictoryTeam(winningTeam);
        game2.setVictoryPlayerId(winningPlayer);
        game2.setForceVictory(true);
        game2.createVictoryConditions();
        VictoryResult victoryResult2 = game2.getVictoryResult();

        assertSame(winningPlayer, victoryResult2.getWinningPlayer());
        assertSame(winningTeam, victoryResult2.getWinningTeam());
    }

    @Test
    public void testRatingModification() {
        int winningPlayer = 2;
        int winningTeam = 2;

        // Test an actual scenario
        Game game = new Game();

        // create three players, a winner, a player in the winning team and a loser
        Player player1 = new Player(1,"Player1");
        player1.setTeam(1);
        Player player2 = new Player(2,"Player2");
        player2.setTeam(2);
        Player player3 = new Player(3,"Player3");
        player3.setTeam(2);

        game.addPlayer(1,player1);
        game.addPlayer(2,player2);
        game.addPlayer(3,player3);

        game.end(winningPlayer,winningTeam);

        Enumeration<Player> players = game.getPlayers();
        while(players.hasMoreElements()){
            Player player = players.nextElement();
            if(player.getId() == winningPlayer) {
                assertEquals(
                        player.getRating(),
                        STANDARD_RATING + RatingModifier.WINNER.getRatingModifier());
            }
            else if(player.getTeam() == winningTeam){
                assertEquals(
                        player.getRating(),
                        STANDARD_RATING + RatingModifier.TEAM_WINNER.getRatingModifier());
            } else {
                assertEquals(
                        player.getRating(),
                        STANDARD_RATING + RatingModifier.LOSER.getRatingModifier());
            }
        }
    }

}
