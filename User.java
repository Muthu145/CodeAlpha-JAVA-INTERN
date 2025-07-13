public class User {
    private String username;
    private Portfolio portfolio;

    public User(String username, double initialCash) {
        this.username = username;
        this.portfolio = new Portfolio(initialCash);
    }

    public String getUsername() { return username; }
    public Portfolio getPortfolio() { return portfolio; }
}
