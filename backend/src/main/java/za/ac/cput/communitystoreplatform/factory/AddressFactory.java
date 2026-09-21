package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Address;
import za.ac.cput.communitystoreplatform.domain.User;
import za.ac.cput.communitystoreplatform.util.Helper;

public class AddressFactory {
    public static Address createAddress(int addressId, User user, String addressLine, String city,
                                        String province, int postalCode) {


        if(Helper.isValidInt(addressId)
        && Helper.isNullOrEmpty(addressLine)
        && Helper.isNullOrEmpty(city)
        && Helper.isNullOrEmpty(province)
        && Helper.isNull(user)
        && Helper.isValidCode(postalCode)){
            return null;
        }

        return new Address.Builder()
                .setAddressId(addressId)
                .setUser(user)
                .setAddressLine(addressLine)
                .setCity(city)
                .setProvince(province)
                .setPostalCode(postalCode)
                .build();
    }

}
