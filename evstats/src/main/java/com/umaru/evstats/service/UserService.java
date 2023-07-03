package com.umaru.evstats.service;

import com.umaru.evstats.dto.FavoriteDto;
import com.umaru.evstats.dto.HelpDto;
import com.umaru.evstats.dto.NotificationDto;
import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.entity.User;

import java.util.List;

public interface UserService {

    public void saveUser(UserDto userDto);

    public void updateUser(UserDto userDto);

    public User findUserByEmail(String email);

    public List<UserDto> getUsers();

    public UserDto getUser(Long userId);

    public void deleteUser(Long userId);

    public void favoriteEvent(Long userId, Long eventId);

    public List<FavoriteDto> getFavoritedEvent();

    public void deleteFavoritedEvent(Long userId, Long eventId);

    public void deleteFavoritedEventByUser(Long userId);

    public void deleteFavoritedEventByEvent(Long eventId);

    public List<NotificationDto> getNotifications();

    public void createNotification(Long userId, String notifications);

    public List<NotificationDto> getNotificationsByUser(Long userId);

    public List<HelpDto> getHelps();

    public HelpDto getHelp(Long helpId);

    public void saveHelp(HelpDto helpDto);

    public void deleteHelp(Long helpId);
}
