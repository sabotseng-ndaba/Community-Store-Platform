package za.ac.cput.communitystoreplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.communitystoreplatform.domain.Role;
import za.ac.cput.communitystoreplatform.service.impl.RoleServiceImpl;

@RestController
@RequestMapping("/role")
public class RoleController {
    public final RoleServiceImpl roleService;

    @Autowired
    public RoleController(RoleServiceImpl roleService){
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public Role create(@RequestBody Role role){
        return roleService.create(role);
    }

    @GetMapping("/read/{roleId}")
    public Role read(@PathVariable String roleId){
        return roleService.read(roleId);
    }

    @PutMapping("/update")
    public Role update(@RequestBody Role role){
        return roleService.update(role);
    }

    @DeleteMapping("/delete/{roleId}")
    public boolean delete(@PathVariable String roleId){
        if(roleService.delete(roleId)){
            return true;
        }
        return false;
    }
}
