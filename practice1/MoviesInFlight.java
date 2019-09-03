import java.util.*;


class MoviesInFlight {

  static int[] pickTwoMovies(int[] movieDuration, int duration) {
    if (duration <= 30 || movieDuration == null || movieDuration.length == 0)
      return new int[]{-1,-1};

    int target = duration-30;

    int low = 0;
    int high = movieDuration.length-1;

    List<int[]> maxList = new ArrayList<>();
    int max = 0;

    Arrays.sort(movieDuration);
    while (low < high) {
      int sum = movieDuration[low] + movieDuration[high];
      if (sum <= target && sum >= max) {
        max = sum;
        maxList.add(new int[]{movieDuration[low], movieDuration[high]});
      }

      if (sum < target)
        ++low;
      else
        --high;
    }
    Collections.sort(maxList, cmp);
    return maxList.get(0);
  }

  static Comparator<int[]> cmp = (a,b) -> {
    int result = b[0]-a[0];
    if (result != 0) return result;

    result = b[0]-a[1];
    if (result != 0) return result;

    result = b[1]-a[0];
    if (result != 0) return result;

    return b[1]-a[1];
  };

  public static void main(String[] args) {
    int[] pair = MoviesInFlight.pickTwoMovies(new int[]{90, 85, 75, 60, 120, 150, 125}, 250);
    System.out.println(String.format("[%d, %d]", pair[0], pair[1]));
  }
}

