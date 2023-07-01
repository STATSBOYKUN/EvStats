package com.umaru.evstats.service;

import com.umaru.evstats.dto.FavoriteDto;
import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    public void saveUser(UserDto userDto);
    public void updateUser(UserDto userDto);
    public User findUserByEmail(String email);
    public List<UserDto> getUsers();
    public List<UserDto> getUsersUser();

    public UserDto getUser(Long userId);
    public void deleteUser(Long userId);

    public void favoriteEvent(Long userId, Long eventId);
    public List<FavoriteDto> getFavoritedEvent();
    public void deleteFavoritedEvent(Long userId, Long eventId);
    public int getJmlUser();
}
