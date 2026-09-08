package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Product;
import za.ac.cput.communitystoreplatform.util.Helper;

import java.time.LocalDate;

public class ProductFactory {
    public Product createProduct(int productId, String productName,
                                 String description,double price,
                                 int quantity,String condition,
                                 String listingType, boolean ecoFriendly,
                                 String status, LocalDate dateCreated,
                                 LocalDate dateUpdated){

        if(Helper.isValidInt(productId) ||
        Helper.isValidInt(quantity)){
            return null;
        }

        if(Helper.isPositive(price)){
            return null;
        }
        if(Helper.isNullOrEmpty(productName) ||
        Helper.isNullOrEmpty(description) ||
        Helper.isNullOrEmpty(condition) ||
        Helper.isNullOrEmpty(listingType) ||
        Helper.isNullOrEmpty(status)){
            return null;
        }


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
