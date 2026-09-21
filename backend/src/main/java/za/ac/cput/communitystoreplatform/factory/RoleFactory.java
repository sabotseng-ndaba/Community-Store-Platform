package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Role;
import za.ac.cput.communitystoreplatform.util.Helper;

public class RoleFactory {
    public static Role createRole(String roleId,String description, String roleName){

        if (Helper.isNullOrEmpty(roleId) ||
                Helper.isNullOrEmpty(description) || Helper.isNullOrEmpty(roleName)){
            return null;
        }

        return new Role.Builder()
                .setRoleId(roleId)
                .setDescription(description)
                .setRoleName(roleName)
                .build();
    }
}
