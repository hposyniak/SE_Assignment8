public class Player {

    private String name;
    private int place;
    private int coins;   // renamed purses to coins because it makes more sense
    private boolean inPenaltyBox;

    public Player(String name, int place, int coins, boolean inPenaltyBox) {
        this.name = name;
        this.place = place;
        this.coins = coins;
        this.inPenaltyBox = inPenaltyBox;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {

        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {

        this.coins = this.coins + coins;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }
}
