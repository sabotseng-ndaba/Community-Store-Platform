package za.ac.cput.communitystoreplatform.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
    @Id
    private int categoryId;
    private String categoryName;
    private String description;

    protected Category(){}

    public Category(Builder builder){
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
        this.description = builder.description;
    }

    public int getCategoryId(){ return categoryId;}
    public String getCategoryName(){ return categoryName;}

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return "Category { " + "\n" +
                "Category Id: " + categoryId + "\n" +
                "Category Name: " + categoryName + "\n" +
                "Description: " + description + "}";
    }

    public static class Builder{
        private int categoryId;
        private String categoryName;
        private String description;

        public Builder copy(Category category){
            this.categoryId = category.categoryId;
            this.categoryName = category.categoryName;
            this.description = category.description;
            return this;
        }

        public Builder setCategoryId(int categoryId){
            this.categoryId = categoryId;
            return this;
        }
        public Builder setCategoryName(String categoryName){
            this.categoryName = categoryName;
            return this;
        }
        public Builder setDescription(String description){
            this.description = description;
            return this;
        }

        public Category build(){
            return new Category(this);
        }
    }
}
