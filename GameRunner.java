
import java.util.Random;

public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		Game aGame = new Game();

		Player Chet = new Player("Chet",0,0,false);
		Player Pat = new Player("Pat",0,0,false);
		Player Sue = new Player("Sue",0,0,false);

		aGame.add(Chet);
		//aGame.add(Pat);
		//aGame.add(Sue);

		Random rand = new Random();

		do {

			if(!aGame.isPlayable()){

				System.out.println("Not enough players!");
				return;

			}

			aGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}

		} while (notAWinner);

	}
}
