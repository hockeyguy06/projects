public class MainInventoryManagementSystem {
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

         // Adding items to the inventory with purchase cost
        ims.addItem("Item1", 100, 100.0, 60.0);
        ims.addItem("Item2", 145, 56.0, 33.6);
        ims.addItem("Item3", 60, 138.0, 82.8);

        // Selling items
        ims.sellItem("Item1", 68);
        ims.sellItem("Item2", 52);
        ims.sellItem("Item3", 24);
        // Generating inventory report
        ims.generateInventoryReport();

        // Generating expense report
        ims.generateExpenseReport();

        // Generating sales report
        ims.generateSalesReport();

        // Generating revenue report
        ims.generateRevenueReport();

        // Additional analytics
        String slowMovingItem = ims.findSlowMovingItem();
        System.out.println("Slow Moving Item: " + slowMovingItem);

        String fastSellingItem = ims.findFastSellingItem();
        System.out.println("Fast Selling Item: " + fastSellingItem);
    }
}
