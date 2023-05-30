//Deliverable 1:
// SMELL 1 - Primitive Obsession
//
//    Player and question should be objects, it would be much easier (and cleaner) if player and question were
//    object classes. The player class would keep track where on the board the player is, how many gold coins they have
//    and whether they are in a penalty box. The question class would keep track of all the questions and their types.
//    This smell is bad because it makes the code harder to read and adds unnecessary complications
//    and possible errors.
//SMELL 2 - Long Method
//
//    Method roll() is 38 lines long, usually when a method exceeds 10 lines of code we should look
//    into how to keep them shorter.
//SMELL 3 - Duplicate Code
//
//    In methods roll() and wasCorrectlyAnswered(), parts of code are duplicated.To fix this we
//    should implement an extract method, which will make the code more efficient, readable and
//    consistent. Duplicates affect the complexity of the code and impacts its performance.
//Deliverable 2:
// - the howManyPlayers() method is never used, which means the game never checks if there is enough players to play
// - the code has poor structure, is hard to read. The methods are not in order and have inconsistent names which makes
//	it harder to use them.
// - the code neither handles nor reports errors or exceptions which may lead to unexpected behaviours


import java.util.ArrayList;
import java.util.LinkedList;


public class Game {
	ArrayList<Player> players = new ArrayList<>();

	Question popQuestion = new Question("pop");

	LinkedList popQuestions = new LinkedList();
	LinkedList scienceQuestions = new LinkedList();
	LinkedList sportsQuestions = new LinkedList();
	LinkedList rockQuestions = new LinkedList();

	//int token = 0; // renamed the previous currentPlayer int variable to token, as in the current player has the token
	int currentPlayer = 0;
	boolean isGettingOutOfPenaltyBox;

	public boolean isPlayable() {
		//checks whether there is sufficient amount of players to play

		return (howManyPlayers() >= 2);
	}

	public void add(Player playerName) {

		players.add(playerName);

		System.out.println(playerName.getName() + " was added");
		System.out.println("They are player number " + players.size());

	}


	public Game() {

		for (int i = 0; i < 50; i++) {

			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));

		}
	}

	public String createRockQuestion(int index) {
		return "Rock Question " + index;
	}


	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {

		System.out.println(players.get(currentPlayer).getName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (players.get(currentPlayer).isInPenaltyBox()) {

			if (roll % 2 != 0) {

				isGettingOutOfPenaltyBox = true;

				System.out.println(players.get(currentPlayer).getName() + " is getting out of the penalty box");

				players.get(currentPlayer).setPlace(players.get(currentPlayer).getPlace() + roll);

				turn(players.get(currentPlayer));

			} else {
				System.out.println(players.get(currentPlayer).getName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {

			players.get(currentPlayer).setPlace(players.get(currentPlayer).getPlace()+roll);

			turn(players.get(currentPlayer));
		}

	}

	public void turn(Player player){

		if (player.getPlace() > 11)
			player.setPlace(player.getPlace() - 12);

		System.out.println(player.getName()
				+ "'s new location is "
				+ player.getPlace());
		System.out.println("The category is " + currentCategory());
		askQuestion();

	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.removeFirst());
	}

	private String currentCategory() {
		if (players.get(currentPlayer).getPlace() == 0 || players.get(currentPlayer).getPlace() == 4 || players.get(currentPlayer).getPlace() == 8)
			return "Pop";
		if (players.get(currentPlayer).getPlace() == 1 || players.get(currentPlayer).getPlace() == 5 || players.get(currentPlayer).getPlace() == 9 )
			return "Science";
		if (players.get(currentPlayer).getPlace() == 2 || players.get(currentPlayer).getPlace() == 6 || players.get(currentPlayer).getPlace() == 10)
			return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (players.get(currentPlayer).isInPenaltyBox()) {
			if (isGettingOutOfPenaltyBox) {

				return wasCorrect(players.get(currentPlayer));

			} else {
				currentPlayer++;
				if (currentPlayer == players.size())
					currentPlayer = 0;
				return true;
			}

		} else {

			return wasCorrect(players.get(currentPlayer));
		}
	}

	public boolean wasCorrect(Player player){

		System.out.println("Answer was correct!!!!");
		player.addCoins(1);
		System.out.println(player.getName()
				+ " now has "
				+ player.getCoins()
				+ " Gold Coins.");

		boolean winner = didPlayerWin();
		currentPlayer++;
			if (currentPlayer == players.size())
			currentPlayer = 0;

		return winner;
	}

	public boolean wrongAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
		players.get(currentPlayer).setInPenaltyBox(true);

		currentPlayer++;
		if (currentPlayer == players.size())
			currentPlayer = 0;
		return true;
	}

	private boolean didPlayerWin() {

		return !(players.get(currentPlayer).getCoins() == 6);
	}


}
