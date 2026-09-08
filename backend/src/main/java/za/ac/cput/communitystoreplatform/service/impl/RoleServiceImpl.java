package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.Role;
import za.ac.cput.communitystoreplatform.repository.RoleRepository;
import za.ac.cput.communitystoreplatform.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

    public final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role read(String roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public boolean delete(String roleId) {
        roleRepository.deleteById(roleId);
        return true;
    }
}
