import java.util.HashMap;
import java.util.Map;

class InventoryManagementSystem {
    private Map<String, Integer> inventory;
    private Map<String, Double> itemPrices;
    private Map<String, Double> itemCosts;
    private Map<String, Integer> itemSales;

    public InventoryManagementSystem() {
        this.inventory = new HashMap<>();
        this.itemPrices = new HashMap<>();
        this.itemCosts = new HashMap<>();
        this.itemSales = new HashMap<>();
    }

    public void addItem(String item, int quantity, double price, double cost) {
        if (inventory.containsKey(item)) {
            int currentQuantity = inventory.get(item);
            inventory.put(item, currentQuantity + quantity);
        } else {
            inventory.put(item, quantity);
        }
        itemPrices.put(item, price);
        itemCosts.put(item, cost);
        itemSales.put(item, 0); // Initialize sales for the item
    }

    public void sellItem(String item, int quantity) {
        if (inventory.containsKey(item)) {
            int currentQuantity = inventory.get(item);
            if (currentQuantity >= quantity) {
                inventory.put(item, currentQuantity - quantity);
                double price = itemPrices.get(item);
                double totalSale = price * quantity;
                itemSales.put(item, itemSales.get(item) + quantity); // Update item sales
                System.out.println(quantity + " " + item + "(s) sold. Total Sale: $" + totalSale);
            } else {
                System.out.println("Insufficient stock for " + item);
            }
        } else {
            System.out.println(item + " is not available in stock.");
        }
    }

    public void generateInventoryReport() {
        System.out.println("--- Inventory Report ---");
        for (String item : inventory.keySet()) {
            int quantity = inventory.get(item);
            double price = itemPrices.get(item);
            double value = quantity * price;
            System.out.println(item + ": " + quantity + " (Value: $" + value + ")");
        }
        System.out.println("-----------------------");
    }

    public void generateExpenseReport() {
        System.out.println("--- Expense Report ---");
        for (String item : inventory.keySet()) {
            int quantity = inventory.get(item);
            double cost = itemCosts.get(item);
            double totalExpense = cost * quantity;
            System.out.println(item + ": Quantity: " + quantity + ", Cost: $" + cost + ", Total Expense: $" + totalExpense);
        }
        System.out.println("----------------------");
    }

    public void generateSalesReport() {
        System.out.println("--- Sales Report ---");
        for (String item : itemSales.keySet()) {
            int quantitySold = itemSales.get(item);
            double price = itemPrices.get(item);
            double totalSale = price * quantitySold;
            System.out.println(item + ": " + quantitySold + " (Total Sale: $" + totalSale + ")");
        }
        System.out.println("--------------------");
    }

     public void generateRevenueReport() {
        System.out.println("--- Revenue Report ---");
        double totalRevenue = 0;
        double totalExpenses = 0;
        for (String item : inventory.keySet()) {
            int quantitySold = itemSales.get(item);
            double price = itemPrices.get(item);
            double totalItemRevenue = price * quantitySold;
            double cost = itemCosts.get(item);
            double totalItemExpenses = cost * quantitySold;
            totalRevenue += totalItemRevenue;
            totalExpenses += totalItemExpenses;
            System.out.println(item + ": Quantity Sold: " + quantitySold + ", Revenue: $" + totalItemRevenue + ", Expenses: $" + totalItemExpenses);
        }
        double revenueAfterExpenses = totalRevenue - totalExpenses;
        System.out.println("Total Revenue: $" + totalRevenue + ", Total Expenses: $" + totalExpenses + ", Revenue After Expenses: $" + revenueAfterExpenses);
        System.out.println("----------------------");
    }

    public double calculateStockTurnoverRate(String item) {
        if (itemSales.containsKey(item)) {
            int quantitySold = itemSales.get(item);
            int averageInventory = inventory.get(item) / 2; // Assuming average inventory as half of initial inventory
            if (averageInventory > 0) {
                return (double) quantitySold / averageInventory;
            }
        }
        return 0.0;
    }

    public String findSlowMovingItem() {
        String slowMovingItem = "";
        double lowestTurnoverRate = Double.MAX_VALUE;
        for (String item : inventory.keySet()) {
            double turnoverRate = calculateStockTurnoverRate(item);
            if (turnoverRate < lowestTurnoverRate) {
                slowMovingItem = item;
                lowestTurnoverRate = turnoverRate;
            }
        }
        return slowMovingItem;
    }

    public String findFastSellingItem() {
        String fastSellingItem = "";
        double highestTurnoverRate = 0.0;
        for (String item : inventory.keySet()) {
            double turnoverRate = calculateStockTurnoverRate(item);
            if (turnoverRate > highestTurnoverRate) {
                fastSellingItem = item;
                highestTurnoverRate = turnoverRate;
            }
        }
        return fastSellingItem;
    }
}