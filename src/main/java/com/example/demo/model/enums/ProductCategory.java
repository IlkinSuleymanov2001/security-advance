package com.example.demo.model.enums;

public enum ProductCategory {
    ELECTRONICS,
    CLOTHING,
    FOOD,
    FURNITURE,
    BEAUTY,
    SPORTS,
    BOOKS;

    // Optional: You can add methods to customize behavior for each enum value
    public String getCategoryDescription() {

        switch (this) {
            case ELECTRONICS:
                return "Devices and gadgets";
            case CLOTHING:
                return "Apparel and accessories";
            case FOOD:
                return "Groceries and consumables";
            case FURNITURE:
                return "Home and office furniture";
            case BEAUTY:
                return "Beauty and personal care products";
            case SPORTS:
                return "Sports equipment and accessories";
            case BOOKS:
                return "Books and publications";
            default:
                return "Unknown category";
        }
    }
}

