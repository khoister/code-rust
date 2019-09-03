import java.util.*;

class ShopkeeperSale {

  static int calculateSalePrices(int[] prices) {
    if (prices == null || prices.length == 0)
      return 0;

    if (prices.length == 1)
      return prices[0];

    final Deque<Integer> nonSaleItems = new LinkedList<>();
    final Stack<Integer> s = new Stack<>();
    s.push(0);

    // Last item in list is never on sale
    int cost = prices[prices.length-1];
    nonSaleItems.addFirst(prices.length-1);

    for (int i = prices.length - 2; i >= 0; i--) {
      int curr = prices[i];
      int prev = prices[i+1];

      if (curr >= prev)
        s.push(prev);

      while (s.peek() > curr)
        s.pop();

      cost += curr - s.peek();
      if (s.peek() == 0)
        nonSaleItems.addFirst(i);
    }

    // Print results
    System.out.println(cost);
    for (int i : nonSaleItems)
      System.out.print(i + " ");
    System.out.println();

    return cost;
  }

  public static void main(String[] args) {
    ShopkeeperSale.calculateSalePrices(new int[]{2, 3, 1, 2, 4, 2});
    ShopkeeperSale.calculateSalePrices(new int[]{2, 4, 6, 8, 10, 10, 8, 6, 4});
    ShopkeeperSale.calculateSalePrices(new int[]{5,1,3,4,6,2});
    ShopkeeperSale.calculateSalePrices(new int[]{1,3,3,2,5});
  }
}

