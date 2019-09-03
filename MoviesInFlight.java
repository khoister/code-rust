import java.util.*;
import java.util.stream.*;

class MoviesInFlight {

  static int[] findMoviePair(int[] durations, int totalDuration) {

    if (durations == null || durations.length == 0)
      return new int[]{};

    if (totalDuration < 0)
      return new int[]{};

    Arrays.sort(durations);
    int low = 0;
    int high = durations.length-1;

    int movie1 = 0;
    int movie2 = 0;
    int maxDuration = 0;
    int limit = totalDuration - 30;

    while (low < high) {
      int sum = durations[low] + durations[high];
      if (sum > maxDuration && sum <= limit) {
        maxDuration = sum;
        movie1 = low;
        movie2 = high;
      }

      if (sum > limit)
        --high;
      else
        ++low;
    }
    return new int[]{durations[movie1], durations[movie2]};
  }

  public static void main(String[] args) {
    int duration = Integer.parseInt(args[0]);
    int[] a = {90, 85, 75, 60, 120, 150, 125};
    System.out.println(Arrays.stream(MoviesInFlight.findMoviePair(a, duration))
      .boxed()
      .collect(Collectors.toList()));
  }
}

