package megamek.common.enums;

public enum RatingModifier {
    WINNER(20),
    TEAM_WINNER(10),
    LOSER(-20);

    private final int ratingModifier;

    RatingModifier(int ratingModifier) {
        this.ratingModifier = ratingModifier;
    }

    public int getRatingModifier() {
        return ratingModifier;
    }
}