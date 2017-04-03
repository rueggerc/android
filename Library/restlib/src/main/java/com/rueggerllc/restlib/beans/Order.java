package com.rueggerllc.restlib.beans;


public class Order {

    private int id;
    private String itemNumber;
    private int quantity;
    private String confirmationNumber;

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Order.id: " + id);
        buffer.append("\nOrder.itemNumber: " + itemNumber);
        buffer.append("\nOrder.quantity: " + quantity);
        buffer.append("\nOrder.confirmationNumber: " + confirmationNumber);
        return buffer.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
}
