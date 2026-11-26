import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// CUSTOM EXCEPTION FOR DOMAIN/INPUT VALIDATION
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

// ABSTRACTION
abstract class Person {
    private String name;

    // ENCAPSULATION
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract void display();
}

// SEPARATE BATCH CLASS
class Batch {
    private String batchName;
    private int pigCount;
    private double pigPrice;
    private double totalPigCost;
    private String financer;
    private String ownerUsername;

    public Batch(String batchName, int pigCount, double pigPrice,
                 String financer, String ownerUsername) {
        if (pigCount <= 0) {
            throw new IllegalArgumentException("Pig count must be greater than zero.");
        }
        if (pigPrice < 0) {
            throw new IllegalArgumentException("Pig price cannot be negative.");
        }
        this.batchName = batchName;
        this.pigCount = pigCount;
        this.pigPrice = pigPrice;
        this.totalPigCost = pigPrice * pigCount;
        this.financer = financer;
        this.ownerUsername = ownerUsername;
    }

    public String getBatchName() {
        return batchName;
    }

    public int getPigCount() {
        return pigCount;
    }

    public double getPigPrice() {
        return pigPrice;
    }

    public double getTotalPigCost() {
        return totalPigCost;
    }

    public String getFinancer() {
        return financer;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void display(int index) {
        System.out.printf("%-4d %-14s %-6d %-10.2f %-12.2f %-18s%n",
                index, batchName, pigCount, pigPrice, totalPigCost, financer);
    }
}

// INHERITANCE
class HogRaiser extends Person {
    private String role;
    private String username;
    private String password;

    public HogRaiser(String name, String username, String password) {
        setName(name);
        this.role = "Hog Raiser";
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    @Override
    void display() {
        System.out.println("Hog Raiser: " + getName());
        System.out.println("Role: " + role);
    }
}

// FEEDS INVENTORY CLASS
class FeedsInventory {
    private String batchName;
    private String feeds;
    private String variety;
    private double price;
    private int quantity;
    private LocalDate date;

    public FeedsInventory(String batchName, String feeds, String variety, double price,
                          int quantity, LocalDate date) {
        if (price < 0) {
            throw new IllegalArgumentException("Feed price cannot be negative.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Feed quantity must be greater than zero.");
        }
        this.batchName = batchName;
        this.feeds = feeds;
        this.variety = variety;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    public String getBatchName() {
        return batchName;
    }

    public String getFeeds() {
        return feeds;
    }

    public String getVariety() {
        return variety;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void display() {
        System.out.printf("%-12s %-15s %-11s %-9.2f %-6d %-11s%n",
                batchName, feeds, variety, price, quantity,
                date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }
}

// MAIN CLASS
public class Main {

    static Scanner input = new Scanner(System.in);

    static ArrayList<FeedsInventory> inventory =
            new ArrayList<FeedsInventory>();

    static ArrayList<Batch> batches =
            new ArrayList<Batch>();

    static ArrayList<String> history =
            new ArrayList<String>();

    static ArrayList<HogRaiser> accounts =
            new ArrayList<HogRaiser>();

    static HogRaiser activeRaiser = null;

    // SAFE INPUT HELPERS WITH EXCEPTION HANDLING
    static int readInt(String prompt) throws InvalidInputException {
        System.out.print(prompt);
        String raw = input.nextLine().trim();
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid whole number: '" + raw + "'.");
        }
    }

    static double readDouble(String prompt) throws InvalidInputException {
        System.out.print(prompt);
        String raw = input.nextLine().trim();
        try {
            double value = Double.parseDouble(raw);
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                throw new InvalidInputException("Invalid decimal number: '" + raw + "'.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number format: '" + raw + "'.");
        }
    }

    static String readNonEmptyString(String prompt, String fieldLabel) throws InvalidInputException {
        System.out.print(prompt);
        String value = input.nextLine().trim();
        if (value.isEmpty()) {
            throw new InvalidInputException(fieldLabel + " cannot be empty.");
        }
        return value;
    }

    // HEADER DESIGN
    static void header() {
        System.out.println();
        System.out.println("==============================================================");
        System.out.println("              PIGGERY INVENTORY SYSTEM");
        System.out.println("                  FEEDS MANAGEMENT");
        System.out.println("==============================================================");
    }

    // AUTH MENU
    static void authMenu() {
        header();
        System.out.println("              WELCOME TO PIGGERY INVENTORY SYSTEM");
        System.out.println();
        System.out.println("                 [1] LOGIN");
        System.out.println("                 [2] REGISTER");
        System.out.println("                 [3] EXIT");
        System.out.println();
        System.out.println("==============================================================");
    }

    // REGISTER FUNCTION
    static void register() {
        header();
        System.out.println("                      USER REGISTRATION");
        System.out.println();

        try {
            String name = readNonEmptyString("Enter full name: ", "Name");
            String username = readNonEmptyString("Enter username: ", "Username");

            for (HogRaiser acc : accounts) {
                if (acc.getUsername().equalsIgnoreCase(username)) {
                    throw new InvalidInputException("Username already exists. Please choose another.");
                }
            }

            String password = readNonEmptyString("Enter password: ", "Password");

            HogRaiser newAccount = new HogRaiser(name, username, password);
            accounts.add(newAccount);

            System.out.println();
            System.out.println("Registration successful! You can now log in.");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during registration: " + e.getMessage());
        }
    }

    // LOGIN FUNCTION
    static boolean login() {
        int maxAttempts = 3;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            header();
            System.out.println("                         SYSTEM LOGIN");
            System.out.println("             Attempt " + attempt + " of " + maxAttempts);
            System.out.println();

            try {
                String username = readNonEmptyString("Enter username: ", "Username");
                String password = readNonEmptyString("Enter password: ", "Password");

                for (HogRaiser acc : accounts) {
                    if (acc.authenticate(username, password)) {
                        activeRaiser = acc;
                        System.out.println();
                        System.out.println("Login successful! Welcome, " + activeRaiser.getName() + ".");
                        return true;
                    }
                }

                System.out.println();
                System.out.println("Invalid username or password.");
                if (attempt < maxAttempts) {
                    System.out.println("Please try again.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
                if (attempt < maxAttempts) {
                    System.out.println("Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Unexpected login error: " + e.getMessage());
            }
        }

        System.out.println();
        System.out.println("Maximum login attempts reached. Access denied.");
        return false;
    }

    // BATCH HELPERS (SEPARATED FROM HOGRAISER)
    static ArrayList<Batch> getAccountBatches() {
        ArrayList<Batch> result = new ArrayList<Batch>();
        if (activeRaiser == null) {
            return result;
        }
        for (Batch b : batches) {
            if (b.getOwnerUsername().equals(activeRaiser.getUsername())) {
                result.add(b);
            }
        }
        return result;
    }

    static int getAccountTotalPigs() {
        int total = 0;
        for (Batch b : getAccountBatches()) {
            total += b.getPigCount();
        }
        return total;
    }

    static double getAccountTotalPigCost() {
        double totalCost = 0.0;
        for (Batch b : getAccountBatches()) {
            totalCost += b.getTotalPigCost();
        }
        return totalCost;
    }

    // FEEDS DASHBOARD BANNER (SHOWN ABOVE MAIN MENU)
    static void displayDashboardBanner() {
        int totalQuantity = 0;
        double totalValue = 0.0;
        ArrayList<Batch> userBatches = getAccountBatches();
        int totalPigs = getAccountTotalPigs();
        int batchCount = userBatches.size();

        Map<String, Integer> varietyTotals = new LinkedHashMap<String, Integer>();
        varietyTotals.put("Pre-Starter", 0);
        varietyTotals.put("Starter", 0);
        varietyTotals.put("Grower", 0);
        varietyTotals.put("Finisher", 0);
        varietyTotals.put("Breeder", 0);

        for (FeedsInventory feed : inventory) {
            totalQuantity += feed.getQuantity();
            totalValue += feed.getPrice() * feed.getQuantity();

            String var = feed.getVariety();
            varietyTotals.put(var, varietyTotals.getOrDefault(var, 0) + feed.getQuantity());
        }

        // Rules:
        // Pre-Starter: 1 sack per 10 head
        // Starter: 1 feed per pig
        // Grower: 3 feeds per pig
        double preStarterGoal = totalPigs / 10.0;
        int starterGoal = totalPigs * 1;
        int growerGoal = totalPigs * 3;

        System.out.println("                         DASHBOARD");
        System.out.printf("User: %-20s Batches: %-4d Total Pigs: %-6d%n",
                activeRaiser.getName(), batchCount, totalPigs);
        System.out.printf("Entries: %-10d Total Units: %-10d Est. Value: %.2f%n",
                inventory.size(), totalQuantity, totalValue);
        System.out.println("--------------------------------------------------------------");
        System.out.println("             TOTAL FEEDS BY VARIETY (STOCK / GOAL)");

        for (Map.Entry<String, Integer> entry : varietyTotals.entrySet()) {
            String kind = entry.getKey();
            int stock = entry.getValue();

            if (kind.equalsIgnoreCase("Pre-Starter")) {
                System.out.printf("  %-14s: %d / %.2f%n", kind, stock, preStarterGoal);
            } else if (kind.equalsIgnoreCase("Starter")) {
                System.out.printf("  %-14s: %d / %d%n", kind, stock, starterGoal);
            } else if (kind.equalsIgnoreCase("Grower")) {
                System.out.printf("  %-14s: %d / %d%n", kind, stock, growerGoal);
            } else {
                System.out.printf("  %-14s: %d / -%n", kind, stock);
            }
        }

        System.out.println("--------------------------------------------------------------");
        System.out.println("               3 MOST RECENTLY ADDED FEEDS");

        if (inventory.isEmpty()) {
            System.out.println("No feeds added yet.");
            System.out.println("--------------------------------------------------------------");
            return;
        }

        System.out.printf("%-12s %-15s %-11s %-9s %-6s %-11s%n",
                "BATCH", "FEED NAME", "VARIETY", "PRICE", "QTY", "DATE");
        System.out.println("--------------------------------------------------------------");

        int startIndex = inventory.size() - 1;
        int endIndex = Math.max(0, inventory.size() - 3);

        for (int i = startIndex; i >= endIndex; i--) {
            inventory.get(i).display();
        }
        System.out.println("--------------------------------------------------------------");
    }

    // SEPARATE BATCH DASHBOARD BANNER (SHOWN ABOVE BATCH MENU)
    static void displayBatchDashboard() {
        ArrayList<Batch> userBatches = getAccountBatches();
        int batchCount = userBatches.size();
        int totalPigs = getAccountTotalPigs();
        double totalPigCost = getAccountTotalPigCost();
        double avgPigs = batchCount > 0 ? (double) totalPigs / batchCount : 0.0;

        Map<String, Integer> financerTotals = new LinkedHashMap<String, Integer>();
        for (Batch b : userBatches) {
            String fin = b.getFinancer();
            financerTotals.put(fin, financerTotals.getOrDefault(fin, 0) + b.getPigCount());
        }

        System.out.println("                      BATCH DASHBOARD");
        System.out.printf("User: %-20s Batches: %-4d Total Pigs: %-6d%n",
                activeRaiser.getName(), batchCount, totalPigs);
        System.out.printf("Total Pig Cost: %-12.2f Avg Pigs/Batch: %.1f%n",
                totalPigCost, avgPigs);
        System.out.println("--------------------------------------------------------------");
        System.out.println("                   PIGS BY FINANCER");

        if (financerTotals.isEmpty()) {
            System.out.println("  No financers recorded yet.");
        } else {
            for (Map.Entry<String, Integer> entry : financerTotals.entrySet()) {
                System.out.printf("  %-22s: %d pigs%n", entry.getKey(), entry.getValue());
            }
        }

        System.out.println("--------------------------------------------------------------");
        System.out.println("               3 MOST RECENTLY ADDED BATCHES");

        if (userBatches.isEmpty()) {
            System.out.println("No batches added yet.");
            System.out.println("--------------------------------------------------------------");
            return;
        }

        System.out.printf("%-4s %-14s %-6s %-10s %-12s %-18s%n",
                "NO.", "BATCH", "PIGS", "PRICE", "TOTAL COST", "FINANCER");
        System.out.println("--------------------------------------------------------------");

        int startIndex = userBatches.size() - 1;
        int endIndex = Math.max(0, userBatches.size() - 3);

        for (int i = startIndex; i >= endIndex; i--) {
            userBatches.get(i).display(i + 1);
        }
        System.out.println("--------------------------------------------------------------");
    }

    // ADD ONE OR MULTIPLE PIG BATCHES / PACKS
    static void addBatch() {
        header();
        System.out.println("                 ADD PIG BATCHES (" + activeRaiser.getName() + ")");
        System.out.println();

        boolean addingMore = true;

        while (addingMore) {
            try {
                int nextNum = getAccountBatches().size() + 1;

                System.out.print("Enter batch/pack name (press Enter for 'Batch " + nextNum + "'): ");
                String batchName = input.nextLine().trim();
                if (batchName.isEmpty()) {
                    batchName = "Batch " + nextNum;
                }

                int pigs = readInt("How many pigs: ");
                if (pigs <= 0) {
                    throw new InvalidInputException("Pig count must be greater than zero.");
                }

                double pigPrice = readDouble("How much is the pig price (per pig): ");
                if (pigPrice < 0) {
                    throw new InvalidInputException("Pig price cannot be negative.");
                }

                System.out.print("Who is the financer: ");
                String financer = input.nextLine().trim();
                if (financer.isEmpty()) {
                    financer = "Self-Financed";
                }

                Batch newBatch = new Batch(batchName, pigs, pigPrice, financer, activeRaiser.getUsername());
                double totalPigCost = newBatch.getTotalPigCost();

                batches.add(newBatch);
                history.add("Added " + batchName + " (" + pigs + " pigs x " +
                        String.format("%.2f", pigPrice) + " = " +
                        String.format("%.2f", totalPigCost) +
                        " | Financer: " + financer + ") | By: " + activeRaiser.getName());

                System.out.printf("Added '%s' (%d pigs @ %.2f = %.2f total pig cost, Financer: %s)! " +
                                "Account Total Pigs: %d%n",
                        batchName, pigs, pigPrice, totalPigCost, financer, getAccountTotalPigs());

                System.out.print("Add another pig pack/batch? (y/n): ");
                String again = input.nextLine().trim();
                addingMore = again.equalsIgnoreCase("y") || again.equalsIgnoreCase("yes");
                System.out.println();
            } catch (InvalidInputException | IllegalArgumentException e) {
                System.out.println("Input Error: " + e.getMessage());
                System.out.println("Please re-enter this batch.");
                System.out.println();
            } catch (Exception e) {
                System.out.println("Unexpected error while adding batch: " + e.getMessage());
                System.out.println();
            }
        }
    }

    // VIEW BATCHES FOR ACTIVE ACCOUNT
    static void viewBatches() {
        header();
        System.out.println("                 VIEW PIG BATCHES (" + activeRaiser.getName() + ")");
        System.out.println();

        ArrayList<Batch> userBatches = getAccountBatches();
        if (userBatches.isEmpty()) {
            System.out.println("No pig batches added yet for this account.");
            return;
        }

        System.out.printf("%-4s %-14s %-6s %-10s %-12s %-18s%n",
                "NO.", "BATCH", "PIGS", "PRICE", "TOTAL COST", "FINANCER");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < userBatches.size(); i++) {
            userBatches.get(i).display(i + 1);
        }
        System.out.println("--------------------------------------------------------------");
        System.out.printf("Total Batches: %d | Total Pigs: %d | Total Pig Cost: %.2f%n",
                userBatches.size(), getAccountTotalPigs(), getAccountTotalPigCost());
    }

    // SEPARATE BATCH MENU WITH ITS OWN DASHBOARD
    static void batchMenu() {
        boolean inBatchMenu = true;

        while (inBatchMenu) {
            header();
            displayBatchDashboard();
            System.out.println("                         BATCH MENU");
            System.out.println();
            System.out.println("                 [1] ADD BATCH");
            System.out.println("                 [2] VIEW BATCHES");
            System.out.println("                 [3] BACK TO MAIN MENU");
            System.out.println();
            System.out.println("==============================================================");

            try {
                int choice = readInt("Enter your choice: ");

                switch (choice) {
                    case 1:
                        addBatch();
                        break;
                    case 2:
                        viewBatches();
                        break;
                    case 3:
                        inBatchMenu = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1 to 3.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error in Batch Menu: " + e.getMessage());
            }
        }
    }

    // SELECT FEEDS VARIETY HELPER
    static String selectVariety() {
        System.out.println();
        System.out.println("Select Feeds Variety:");
        System.out.println("  [1] Pre-Starter");
        System.out.println("  [2] Starter");
        System.out.println("  [3] Grower");
        System.out.println("  [4] Finisher");
        System.out.println("  [5] Breeder");
        System.out.println("  [6] Custom Variety");

        try {
            int choice = readInt("Enter variety choice: ");

            switch (choice) {
                case 1:
                    return "Pre-Starter";
                case 2:
                    return "Starter";
                case 3:
                    return "Grower";
                case 4:
                    return "Finisher";
                case 5:
                    return "Breeder";
                case 6:
                    System.out.print("Enter custom variety: ");
                    String custom = input.nextLine().trim();
                    return custom.isEmpty() ? "Custom" : custom;
                default:
                    System.out.println("Invalid choice. Defaulting to 'Grower'.");
                    return "Grower";
            }
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage() + " Defaulting to 'Grower'.");
            return "Grower";
        }
    }

    // MAIN MENU (WITH DASHBOARD ABOVE IT)
    static void menu() {
        header();
        displayDashboardBanner();
        System.out.println("                         MAIN MENU");
        System.out.println();
        System.out.println("                 [1] BATCH MENU");
        System.out.println("                 [2] ADD FEEDS");
        System.out.println("                 [3] VIEW FEEDS");
        System.out.println("                 [4] VIEW HISTORY");
        System.out.println("                 [5] COMPUTE");
        System.out.println("                 [6] LOGOUT");
        System.out.println();
        System.out.println("==============================================================");
    }

    // ADD FEEDS (REQUIRES AT LEAST ONE BATCH AND BATCH SELECTION)
    static void addFeeds() {
        header();
        System.out.println("                      ADD FEEDS");
        System.out.println();

        try {
            ArrayList<Batch> userBatches = getAccountBatches();
            if (userBatches.isEmpty()) {
                throw new InvalidInputException(
                        "No batch found for your account. Please go to BATCH MENU -> ADD BATCH first.");
            }

            System.out.println("Select a batch for these feeds:");
            System.out.printf("%-4s %-14s %-6s %-10s %-12s %-18s%n",
                    "NO.", "BATCH", "PIGS", "PRICE", "TOTAL COST", "FINANCER");
            System.out.println("--------------------------------------------------------------");
            for (int i = 0; i < userBatches.size(); i++) {
                userBatches.get(i).display(i + 1);
            }
            System.out.println("--------------------------------------------------------------");

            int batchChoice = readInt("Enter batch number (1-" + userBatches.size() + "): ");
            if (batchChoice < 1 || batchChoice > userBatches.size()) {
                throw new InvalidInputException("Batch number out of range.");
            }

            Batch selectedBatch = userBatches.get(batchChoice - 1);

            String feeds = readNonEmptyString("Enter feed name/brand: ", "Feed name");
            String variety = selectVariety();

            double price = readDouble("Enter price: ");
            if (price < 0) {
                throw new InvalidInputException("Price cannot be negative.");
            }

            int quantity = readInt("Enter quantity: ");
            if (quantity <= 0) {
                throw new InvalidInputException("Quantity must be greater than zero.");
            }

            System.out.print("Enter date (MM/dd/yyyy): ");
            String dateInput = input.nextLine().trim();

            LocalDate date;
            try {
                date = LocalDate.parse(
                        dateInput,
                        DateTimeFormatter.ofPattern("MM/dd/yyyy")
                );
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Invalid date format '" + dateInput + "'. Use MM/dd/yyyy.");
            }

            FeedsInventory newFeed =
                    new FeedsInventory(selectedBatch.getBatchName(), feeds, variety, price, quantity, date);

            inventory.add(newFeed);

            history.add("Added " + feeds + " (" + variety + ") to [" + selectedBatch.getBatchName() + "]" +
                    " | Quantity: " + quantity +
                    " | Date: " + dateInput +
                    " | By: " + activeRaiser.getName());

            System.out.println();
            System.out.println("Feeds successfully added to batch '" + selectedBatch.getBatchName() + "'!");
        } catch (InvalidInputException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error while adding feeds: " + e.getMessage());
        }
    }

    // VIEW FEEDS (USER CHOOSES WHICH BATCH TO VIEW)
    static void viewFeeds() {
        header();
        System.out.println("                      FEEDS INVENTORY");
        System.out.println();

        try {
            if (inventory.isEmpty()) {
                System.out.println("No feeds available in the inventory.");
                return;
            }

            ArrayList<Batch> userBatches = getAccountBatches();
            String selectedBatchName = null; // null means All Batches
            double selectedBatchPigCost = 0.0;

            if (!userBatches.isEmpty()) {
                System.out.println("Choose a batch to view feeds:");
                System.out.println("  [0] All Batches");
                for (int i = 0; i < userBatches.size(); i++) {
                    Batch b = userBatches.get(i);
                    System.out.printf("  [%d] %s (%d pigs | Pig Cost: %.2f)%n",
                            i + 1, b.getBatchName(), b.getPigCount(), b.getTotalPigCost());
                }

                int batchChoice = readInt("Enter choice (0-" + userBatches.size() + "): ");
                if (batchChoice < 0 || batchChoice > userBatches.size()) {
                    throw new InvalidInputException("Batch choice out of range.");
                }

                if (batchChoice > 0) {
                    Batch chosen = userBatches.get(batchChoice - 1);
                    selectedBatchName = chosen.getBatchName();
                    selectedBatchPigCost = chosen.getTotalPigCost();
                }
            }

            ArrayList<FeedsInventory> filteredFeeds = new ArrayList<FeedsInventory>();
            for (FeedsInventory feed : inventory) {
                if (selectedBatchName == null || feed.getBatchName().equalsIgnoreCase(selectedBatchName)) {
                    filteredFeeds.add(feed);
                }
            }

            System.out.println();
            System.out.println("Showing feeds for: " + (selectedBatchName == null ? "All Batches" : selectedBatchName));
            System.out.println();

            if (filteredFeeds.isEmpty()) {
                System.out.println("No feeds recorded for '" + selectedBatchName + "'.");
                return;
            }

            System.out.printf("%-12s %-15s %-11s %-9s %-6s %-11s%n",
                    "BATCH", "FEED NAME", "VARIETY", "PRICE", "QTY", "DATE");

            System.out.println("--------------------------------------------------------------");

            int totalFeedsUsed = 0;
            double totalFeedExpenses = 0.0;

            Map<String, Integer> feedsPerType = new LinkedHashMap<String, Integer>();
            feedsPerType.put("Pre-Starter", 0);
            feedsPerType.put("Starter", 0);
            feedsPerType.put("Grower", 0);
            feedsPerType.put("Finisher", 0);
            feedsPerType.put("Breeder", 0);

            Map<String, Double> expensePerType = new LinkedHashMap<String, Double>();
            expensePerType.put("Pre-Starter", 0.0);
            expensePerType.put("Starter", 0.0);
            expensePerType.put("Grower", 0.0);
            expensePerType.put("Finisher", 0.0);
            expensePerType.put("Breeder", 0.0);

            for (FeedsInventory feed : filteredFeeds) {
                feed.display();

                int qty = feed.getQuantity();
                double cost = feed.getPrice() * qty;
                String type = feed.getVariety();

                totalFeedsUsed += qty;
                totalFeedExpenses += cost;

                feedsPerType.put(type, feedsPerType.getOrDefault(type, 0) + qty);
                expensePerType.put(type, expensePerType.getOrDefault(type, 0.0) + cost);
            }

            double totalPigExpenses = (selectedBatchName == null)
                    ? getAccountTotalPigCost()
                    : selectedBatchPigCost;
            double overallExpenses = totalFeedExpenses + totalPigExpenses;

            System.out.println("--------------------------------------------------------------");
            System.out.println("                 TOTAL FEEDS PER TYPE");
            for (Map.Entry<String, Integer> entry : feedsPerType.entrySet()) {
                String type = entry.getKey();
                int qty = entry.getValue();
                double typeExpense = expensePerType.getOrDefault(type, 0.0);
                System.out.printf("  %-15s: %-6d units | Cost: %.2f%n", type, qty, typeExpense);
            }

            System.out.println("--------------------------------------------------------------");
            System.out.println("                   EXPENSES SUMMARY");
            System.out.println("Total Feed Records   : " + filteredFeeds.size());
            System.out.println("Total Feeds Used     : " + totalFeedsUsed + " units");
            System.out.printf("Total Feed Expenses  : %.2f%n", totalFeedExpenses);
            System.out.printf("Total Pig Expenses   : %.2f%n", totalPigExpenses);
            System.out.printf("Total Expenses       : %.2f%n", overallExpenses);
            System.out.println("--------------------------------------------------------------");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error while viewing feeds: " + e.getMessage());
        }
    }

    // COMPUTE PER SELECTED BATCH
    static void computeBatch() {
        header();
        System.out.println("                      BATCH COMPUTATION");
        System.out.println();

        try {
            ArrayList<Batch> userBatches = getAccountBatches();
            if (userBatches.isEmpty()) {
                throw new InvalidInputException(
                        "No batch found for your account. Please go to BATCH MENU -> ADD BATCH first.");
            }

            System.out.println("Select a batch to compute:");
            System.out.printf("%-4s %-14s %-6s %-10s %-12s %-18s%n",
                    "NO.", "BATCH", "PIGS", "PRICE", "TOTAL COST", "FINANCER");
            System.out.println("--------------------------------------------------------------");
            for (int i = 0; i < userBatches.size(); i++) {
                userBatches.get(i).display(i + 1);
            }
            System.out.println("--------------------------------------------------------------");

            int batchChoice = readInt("Enter batch number (1-" + userBatches.size() + "): ");
            if (batchChoice < 1 || batchChoice > userBatches.size()) {
                throw new InvalidInputException("Batch number out of range.");
            }

            Batch selectedBatch = userBatches.get(batchChoice - 1);
            int pigs = selectedBatch.getPigCount();
            double pigPrice = selectedBatch.getPigPrice();
            double pigCost = selectedBatch.getTotalPigCost();

            int totalFeedUnits = 0;
            double totalFeedCost = 0.0;

            Map<String, Integer> varietyUnits = new LinkedHashMap<String, Integer>();
            varietyUnits.put("Pre-Starter", 0);
            varietyUnits.put("Starter", 0);
            varietyUnits.put("Grower", 0);
            varietyUnits.put("Finisher", 0);
            varietyUnits.put("Breeder", 0);

            Map<String, Double> varietyCosts = new LinkedHashMap<String, Double>();
            varietyCosts.put("Pre-Starter", 0.0);
            varietyCosts.put("Starter", 0.0);
            varietyCosts.put("Grower", 0.0);
            varietyCosts.put("Finisher", 0.0);
            varietyCosts.put("Breeder", 0.0);

            for (FeedsInventory feed : inventory) {
                if (feed.getBatchName().equalsIgnoreCase(selectedBatch.getBatchName())) {
                    int qty = feed.getQuantity();
                    double cost = feed.getPrice() * qty;
                    String type = feed.getVariety();

                    totalFeedUnits += qty;
                    totalFeedCost += cost;

                    varietyUnits.put(type, varietyUnits.getOrDefault(type, 0) + qty);
                    varietyCosts.put(type, varietyCosts.getOrDefault(type, 0.0) + cost);
                }
            }

            double totalExpenses = pigCost + totalFeedCost;
            double costPerPig = pigs > 0 ? totalExpenses / pigs : 0.0;
            double feedCostPerPig = pigs > 0 ? totalFeedCost / pigs : 0.0;

            double preStarterGoal = pigs / 10.0;
            int starterGoal = pigs * 1;
            int growerGoal = pigs * 3;

            System.out.println();
            System.out.println("--------------------------------------------------------------");
            System.out.println("Batch Name           : " + selectedBatch.getBatchName());
            System.out.println("Financer             : " + selectedBatch.getFinancer());
            System.out.println("Pig Count            : " + pigs);
            System.out.printf("Price per Pig        : %.2f%n", pigPrice);
            System.out.printf("Total Pig Cost       : %.2f%n", pigCost);
            System.out.println("--------------------------------------------------------------");
            System.out.println("                 FEEDS BY VARIETY (USED / GOAL)");
            for (Map.Entry<String, Integer> entry : varietyUnits.entrySet()) {
                String type = entry.getKey();
                int used = entry.getValue();
                double cost = varietyCosts.getOrDefault(type, 0.0);

                if (type.equalsIgnoreCase("Pre-Starter")) {
                    System.out.printf("  %-14s: %d / %.2f | Cost: %.2f%n", type, used, preStarterGoal, cost);
                } else if (type.equalsIgnoreCase("Starter")) {
                    System.out.printf("  %-14s: %d / %d | Cost: %.2f%n", type, used, starterGoal, cost);
                } else if (type.equalsIgnoreCase("Grower")) {
                    System.out.printf("  %-14s: %d / %d | Cost: %.2f%n", type, used, growerGoal, cost);
                } else {
                    System.out.printf("  %-14s: %d / - | Cost: %.2f%n", type, used, cost);
                }
            }
            System.out.println("--------------------------------------------------------------");
            System.out.println("                 COMPUTED SUMMARY");
            System.out.println("Total Feed Units     : " + totalFeedUnits);
            System.out.printf("Total Feed Cost      : %.2f%n", totalFeedCost);
            System.out.printf("Total Batch Expenses : %.2f%n", totalExpenses);
            System.out.printf("Feed Cost per Pig    : %.2f%n", feedCostPerPig);
            System.out.printf("Total Cost per Pig   : %.2f%n", costPerPig);
            System.out.println("--------------------------------------------------------------");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Math Error during computation: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during computation: " + e.getMessage());
        }
    }

    // VIEW HISTORY
    static void viewHistory() {
        header();
        System.out.println("                      INVENTORY HISTORY");
        System.out.println();

        if (history.isEmpty()) {
            System.out.println("No history records available.");
            return;
        }

        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {

        // Seed default account (username: admin, password: 1234)
        accounts.add(new HogRaiser("System Admin", "admin", "1234"));

        boolean programRunning = true;

        try {
            while (programRunning) {
                authMenu();

                try {
                    int authChoice = readInt("Enter your choice: ");

                    switch (authChoice) {
                        case 1:
                            if (!login()) {
                                programRunning = false;
                                break;
                            }

                            // POLYMORPHISM
                            Person person = activeRaiser;
                            System.out.println();
                            person.display();

                            boolean inMenu = true;
                            while (inMenu) {
                                menu();

                                try {
                                    int choice = readInt("Enter your choice: ");

                                    switch (choice) {
                                        case 1:
                                            batchMenu();
                                            break;
                                        case 2:
                                            addFeeds();
                                            break;
                                        case 3:
                                            viewFeeds();
                                            break;
                                        case 4:
                                            viewHistory();
                                            break;
                                        case 5:
                                            computeBatch();
                                            break;
                                        case 6:
                                            System.out.println("Logged out from " + activeRaiser.getName() + ".");
                                            activeRaiser = null;
                                            inMenu = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please select 1 to 6.");
                                    }
                                } catch (InvalidInputException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }
                            break;

                        case 2:
                            register();
                            break;

                        case 3:
                            header();
                            System.out.println("Thank you for using Piggery Inventory System!");
                            System.out.println("Program terminated.");
                            programRunning = false;
                            break;

                        default:
                            System.out.println("Invalid choice. Please select 1 to 3.");
                    }
                } catch (InvalidInputException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Fatal system error: " + e.getMessage());
        } finally {
            input.close();
        }
    }
}