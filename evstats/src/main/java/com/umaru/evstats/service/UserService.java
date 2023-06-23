
package com.umaru.evstats.service;

import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.model.User;

import java.util.List;


public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
    UserDto findUserDtoByEmail(String email);

    User findUserByName(String name);
    public List<UserDto> getUsers();
    public List<UserDto> getUsersUser();

    int getJmlUser();
}
