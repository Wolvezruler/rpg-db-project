public class Item {
    private int itemID;
    private String itemName;
    private String itemClassification;
    private int itemValue;
    private String itemType;
    private int itemDurability;

    public Item(int itemID, String itemName, String itemClassification, int itemValue, String itemType, int itemDurability) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemClassification = itemClassification;
        this.itemValue = itemValue;
        this.itemType = itemType;
        this.itemDurability = itemDurability;
    }

    public int getItemID() {
        return this.itemID;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getItemClassification() {
        return this.itemClassification;
    }

    public int getItemValue() {
        return this.itemValue;
    }

    public String getItemType() {
        return this.itemType;
    }

    public int getItemDurability() {
        return this.itemDurability;
    }
}
