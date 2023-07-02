package com.umaru.evstats.service;

import com.umaru.evstats.dto.FavoriteDto;
import com.umaru.evstats.dto.HelpDto;
import com.umaru.evstats.dto.NotificationDto;
import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.entity.*;
import com.umaru.evstats.mapper.EventMapper;
import com.umaru.evstats.mapper.HelpMapper;
import com.umaru.evstats.mapper.NotificationMapper;
import com.umaru.evstats.mapper.UserMapper;
import com.umaru.evstats.repository.*;
import com.umaru.evstats.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private HelpRepository helpsRepository;

    @Autowired
    private NotificationRepository notificationsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        User user = new User(
                userDto.getUsername(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getProvinsi(),
                userDto.getPekerjaan(),
                userDto.getUmur(),
                Arrays.asList(role)
        );

        usersRepository.save(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        Role role = roleRepository.findByName(TbConstants.Roles.USER);
        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = UserMapper.mapToUser(userDto);
        user.setRoles(Arrays.asList(role));
        usersRepository.save(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = usersRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map((user) -> (UserMapper.mapToUserDto(user)))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto getUser(Long userId) {
        Optional<User> users = usersRepository.findById(userId);
        if (users.isPresent()) {
            return UserMapper.mapToUserDto(users.get());
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public void favoriteEvent(Long userId, Long eventId) {
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setEventId(eventId);

        FavoriteDto favoriteDto = EventMapper.mapToFavoriteDto(favorite);
        favoriteRepository.save(favorite);
    }

    @Override
    public List<FavoriteDto> getFavoritedEvent() {
        List<Favorite> favorites = favoriteRepository.findAll();
        List<FavoriteDto> favoriteDtos = favorites.stream()
                .map((favorite) -> (EventMapper.mapToFavoriteDto(favorite)))
                .collect(Collectors.toList());
        return favoriteDtos;
    }

    @Override
    public void deleteFavoritedEvent(Long userId, Long eventId) {
        Favorite favorite = favoriteRepository.findByUserIdAndEventId(userId, eventId);
        favoriteRepository.deleteById(favorite.getId());
    }

    @Override
    public void deleteFavoritedEventByUser(Long userId) {
        favoriteRepository.deleteFavoriteByUserId(userId);
    }

    @Override
    public void deleteFavoritedEventByEvent(Long eventId) {
        favoriteRepository.deleteFavoriteByEventId(eventId);
    }

    @Override
    public List<NotificationDto> getNotifications(){
        List<Notification> notifications = notificationsRepository.findAll();
        List<NotificationDto> notificationDtos = notifications.stream()
                .map((notification) -> (NotificationMapper.mapToNotificationDto(notification)))
                .collect(Collectors.toList());
        return notificationDtos;
    }

    @Override
    public void createNotification(Long userId, String notifications) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setNotifications(notifications);

        NotificationDto notificationDto = NotificationMapper.mapToNotificationDto(notification);
        notificationsRepository.save(notification);
    }

    @Override
    public List<NotificationDto> getNotificationsByUser(Long userId){
        List<Notification> notifications = notificationsRepository.findAll();
        List<NotificationDto> notificationDtos = notifications.stream()
                .map((notification) -> (NotificationMapper.mapToNotificationDto(notification)))
                .collect(Collectors.toList());
        if (notificationDtos == null) {
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setUserId(userId);
            notificationDto.setNotifications("Tidak ada notifikasi");
            notificationDto.setCreatedAt(new Date());
            notificationDtos.add(notificationDto);
            return notificationDtos;
        }
        for (NotificationDto notificationDto : notificationDtos) {
            if (notificationDto.getUserId() != userId) {
                notificationDtos.remove(notificationDto);
            }
        }
        return notificationDtos;
    }

    @Override
    public List<HelpDto> getHelps(){
        List<Help> helps = helpsRepository.findAll();
        List<HelpDto> helpDtos = helps.stream()
                .map((help) -> (HelpMapper.mapToHelpDto(help)))
                .collect(Collectors.toList());
        return helpDtos;
    }

    @Override
    public HelpDto getHelp(Long helpId) {
        Optional<Help> helps = helpsRepository.findById(helpId);
        if (helps.isPresent()) {
            return HelpMapper.mapToHelpDto(helps.get());
        } else {
            return null;
        }
    }

    @Override
    public void saveHelp(HelpDto helpDto) {
        Help help = HelpMapper.mapToHelp(helpDto);
        helpsRepository.save(help);
    }

    @Override
    public void deleteHelp(Long helpId) {
        helpsRepository.deleteById(helpId);
    }
}
