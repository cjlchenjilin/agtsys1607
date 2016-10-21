package org.agtsys.dao;

import java.util.List;
import org.agtsys.domain.User;
import org.agtsys.util.MySqlPageTool;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User getUserByUser(User user);
    List<User> getPageUsersByUser(@Param(value = "user")User user,@Param(value = "pt") MySqlPageTool pt)
    throws Exception;
    Integer getCount(User user)throws Exception;
    int updateUser(User user) throws Exception;
}