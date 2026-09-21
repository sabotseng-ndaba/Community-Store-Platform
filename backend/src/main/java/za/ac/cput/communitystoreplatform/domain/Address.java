package za.ac.cput.communitystoreplatform.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Embeddable
@Table(name = "address")
public class Address {
    private int addressId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    private String addressLine;
    private String city;
    private String province;
    private int postalCode;

    protected Address(){}
    public Address(Builder builder){
        this.addressId = builder.addressId;
        this.user = builder.user;
        this.addressLine = builder.addressLine;
        this.city = builder.city;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
    }
    public int getAddressId(){ return addressId;}
    public User getUser(){ return user;}
    public String getAddressLine(){ return addressLine;}
    public String getCity(){ return city;}
    public String getProvince(){ return province;}
    public int getPostalCode(){ return postalCode;}

    public String toString(){
        return "Address: {" + "\n" +
                "Address Id: " + addressId + "\n" +
                "User: " + user + "\n" +
                "Address Line: " + addressLine + "\n" +
                "City: " + city + "\n" +
                "Province: " + province + "\n" +
                "Postal Code: " + postalCode + "\n" + "}";

    }

    public static class Builder{
        private int addressId;
        private User user;
        private String addressLine;
        private String city;
        private String province;
        private int postalCode;

        public Builder copy(Address address){
            this.addressId = address.addressId;
            this.user = address.user;
            this.addressLine = address.addressLine;
            this.city = address.city;
            this.province = address.province;
            this.postalCode = address.postalCode;
            return this;
        }

        public Builder setAddressId(int addressId){
            this.addressId = addressId;
            return this;
        }
        public Builder setUser(User user){
            this.user = user;
            return this;
        }
        public Builder setAddressLine(String addressLine){
            this.addressLine = addressLine;
            return this;
        }
        public Builder setCity(String city){
            this.city = city;
            return this;
        }
        public Builder setProvince(String province){
            this.province = province;
            return this;
        }
        public Builder setPostalCode(int postalCode){
            this.postalCode = postalCode;
            return this;
        }

        public Address build(){
            return new Address(this);
        }
    }

}
