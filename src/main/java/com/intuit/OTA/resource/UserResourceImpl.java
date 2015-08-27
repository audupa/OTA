package com.intuit.OTA.resource;


import com.intuit.OTA.dao.UserDAO;
import com.intuit.OTA.dto.UserDO;
import com.intuit.OTA.model.User;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: AUDUPA
 * Date: 2/10/15
 * Time: 10:44 PM
 */
@Component
public class UserResourceImpl implements UserResource {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public User getUserDetails(Long id) throws Exception {
        UserDO userDO = userDAO.findById(id);
        User u = null;
        if (userDO != null) {
            u = mapper.map(userDO, User.class);
        }
        return u;
    }
}

