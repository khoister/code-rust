import org.apache.commons.lang3.ArrayUtils;

/**
 * Find maximum single sell profit
 */
public class MaximumProfit {

    public static int maxProfit(final int[] arr) {
        if (ArrayUtils.isEmpty(arr) || arr.length < 2) {
            throw new IllegalArgumentException("Invalid input");
        }

        int minPrice = arr[0];
        int maxPrice = Integer.MIN_VALUE;
        int maxProfit = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; ++i) {
            int currentProfit = arr[i] - minPrice;
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
                maxPrice = arr[i];
            }
            minPrice = Math.min(arr[i], minPrice);
        }
        System.out.println(String.format("Max: %d, Min: %d", maxPrice, maxPrice - maxProfit));
        return maxProfit;
    }

    public static void main(final String[] args) {
        final int arr[] = { 8, 5, 12, 9, 19, 1 };
        System.out.println(maxProfit(arr));

        final int arr1[] = { 21, 12, 11, 9, 6, 3 };
        System.out.println(maxProfit(arr1));
    }
}
