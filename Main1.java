import java.util.Map;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Market market = new Market();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = new User(username, 10000.0); // Starting with $10,000

        while (true) {
            System.out.println("\n1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Simulate Market");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.next().toUpperCase();
                    System.out.print("Enter quantity to buy: ");
                    int buyQty = scanner.nextInt();
                    Stock buyStock = market.getStocks().get(buySymbol);
                    if (buyStock != null) {
                        boolean success = user.getPortfolio().buyStock(buyStock, buyQty);
                        if (success) {
                            System.out.println("Stock purchased successfully.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.next().toUpperCase();
                    System.out.print("Enter quantity to sell: ");
                    int sellQty = scanner.nextInt();
                    Stock sellStock = market.getStocks().get(sellSymbol);
                    if (sellStock != null) {
                        boolean success = user.getPortfolio().sellStock(sellStock, sellQty);
                        if (success) {
                            System.out.println("Stock sold successfully.");
                        } else {
                            System.out.println("Insufficient shares.");
                        }
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 4:
                    user.getPortfolio().displayPortfolio(market.getStocks());
                    break;
                case 5:
                    market.simulateMarket();
                    System.out.println("Market simulated.");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
