package dice;

import java.util.Random;

public class SixSidedDice implements Dice {
    private final Random random;

    public SixSidedDice() {
        this.random = new Random();
    }

    public SixSidedDice(Random random) {
        this.random = random;
    }

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}
