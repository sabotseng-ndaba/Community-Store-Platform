package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Product;
import java.time.LocalDate;

public class ProductFactory {

    public static Product createProduct(
            int productId,
            String productName,
            String description,
            double price,
            int quantity,
            String condition,
            String listingType,
            boolean ecoFriendly,
            String status,
            LocalDate dateCreated,
            LocalDate dateUpdated) {

        return new Product.Builder()
                .setProductId(productId)
                .setProductName(productName)
                .setDescription(description)
                .setPrice(price)
                .setQuantity(quantity)
                .setCondition(condition)
                .setListingType(listingType)
                .setEcoFriendly(ecoFriendly)
                .setStatus(status)
                .setDateCreated(dateCreated)
                .setDateUpdated(dateUpdated)
                .build();
    }
}