import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> holdings;
    private double cash;

    public Portfolio(double initialCash) {
        this.holdings = new HashMap<>();
        this.cash = initialCash;
    }

    public boolean buyStock(Stock stock, int quantity) {
        double totalCost = stock.getPrice() * quantity;
        if (cash >= totalCost) {
            holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
            cash -= totalCost;
            return true;
        }
        return false;
    }

    public boolean sellStock(Stock stock, int quantity) {
        int owned = holdings.getOrDefault(stock.getSymbol(), 0);
        if (owned >= quantity) {
            holdings.put(stock.getSymbol(), owned - quantity);
            cash += stock.getPrice() * quantity;
            return true;
        }
        return false;
    }

    public double getCash() { return cash; }

    public void displayPortfolio(Map<String, Stock> market) {
        System.out.println("Portfolio:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            double price = market.get(symbol).getPrice();
            System.out.printf("%s: %d shares @ %.2f each%n", symbol, quantity, price);
        }
        System.out.printf("Cash Balance: %.2f%n", cash);
    }
}
