package com.home.app.service.role;

import com.home.app.model.Role;
import com.home.app.repository.role.RoleException;
import com.home.app.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRole(String role) throws RoleException {
        Criteria criteria = Criteria.where("name").is(role);
        return roleRepository.findRole(criteria);
    }

    private final RoleRepository roleRepository;
}
