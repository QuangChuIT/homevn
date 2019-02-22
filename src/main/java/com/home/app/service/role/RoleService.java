package com.home.app.service.role;

import com.home.app.model.Role;
import com.home.app.repository.role.RoleException;

public interface RoleService {
    Role findRole(String role) throws RoleException;
}
