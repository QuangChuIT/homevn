package com.home.app.repository.role;

import com.home.app.model.Role;
import org.springframework.data.mongodb.core.query.Criteria;

public interface RoleRepository {
    Role findRole(Criteria criteria) throws RoleException;
}
