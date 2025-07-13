import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Market {
    private Map<String, Stock> stocks;
    private Random random;

    public Market() {
        stocks = new HashMap<>();
        random = new Random();
        // Initialize with some stocks
        stocks.put("AAPL", new Stock("AAPL", "Apple Inc.", 150.0));
        stocks.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 2800.0));
        stocks.put("AMZN", new Stock("AMZN", "Amazon.com Inc.", 3400.0));
    }

    public Map<String, Stock> getStocks() { return stocks; }

    public void simulateMarket() {
        for (Stock stock : stocks.values()) {
            double changePercent = (random.nextDouble() - 0.5) * 0.1; // ±5%
            double newPrice = stock.getPrice() * (1 + changePercent);
            stock.setPrice(newPrice);
        }
    }

    public void displayMarket() {
        System.out.println("Market Prices:");
        for (Stock stock : stocks.values()) {
            System.out.printf("%s (%s): %.2f%n", stock.getName(), stock.getSymbol(), stock.getPrice());
        }
    }
}
