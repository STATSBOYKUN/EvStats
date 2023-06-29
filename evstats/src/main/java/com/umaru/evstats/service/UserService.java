package com.umaru.evstats.service;

import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
    UserDto findUserDtoByEmail(String email);

    User findUserByUsername(String username);
    public List<UserDto> getUsers();
    public List<UserDto> getUsersUser();

    public UserDto getUser(Long userId);
    public void deleteUser(Long userId);
    int getJmlUser();
}
