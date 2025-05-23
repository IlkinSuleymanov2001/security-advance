package com.example.demo.model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ProductMark {
    NIKE("Nike", "Sportswear"),
    ADIDAS("Adidas", "Sportswear"),
    PUMA("Puma", "Sportswear"),
    ZARA("Zara", "Casual"),
    H_M("H&M", "Casual"),
    LEVIS("Levi's", "Casual"),
    UNIQLO("Uniqlo", "Casual"),
    GUCCI("Gucci", "Luxury"),
    PRADA("Prada", "Luxury"),
    CALVIN_KLEIN("Calvin Klein", "Luxury");

    private final String brandName;
    private final String category;

/*
    public static ProductMark fromString(String brandName) {
        for (ProductMark mark : ProductMark.values()) {
            if (mark.getBrandName().equalsIgnoreCase(brandName)) {
                return mark;
            }
        }
        return null;
    }*/

}


