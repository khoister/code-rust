import java.util.*;

class RollDice {

  static int minMoves(int[] dice) {
    if (dice == null || dice.length < 2)
      return 0;

    // The maximum possible moves is if every die is moved twice (not technically possible)
    int min = dice.length * 2;

    // Keep track of the number of each pip. Index 0 is not used.
    int[] count = new int[7];
    for (int die : dice)
      ++count[die];

    for (int i = 1; i < 7; i++) {
      int currentDiceCount = count[i];
      int complementDiceCount = count[7-i];

      // The number of moves if complement dice were flipped, each moved by 2
      int complementDiceMoves = complementDiceCount*2;

      // All remaining dice (not including the current value and the complement value)
      // are moved by 1
      int otherDiceMoves = dice.length - (currentDiceCount + complementDiceCount);

      // Note that dice with the current pip values are not moved so total would be 0.
      // In summary, total moves for a particular pip value: 0*current + 2*complement + 1*(all others)
      min = Math.min(min, complementDiceMoves + otherDiceMoves);
    }
    return min;
  }

  public static void main(String[] args) {
    System.out.println(RollDice.minMoves(new int[]{1,2,3}));
    System.out.println(RollDice.minMoves(new int[]{1,1,6}));
    System.out.println(RollDice.minMoves(new int[]{1,6,2,3}));
  }
}

