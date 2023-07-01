package com.umaru.evstats.service;

import com.umaru.evstats.dto.FavoriteDto;
import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.entity.Favorite;
import com.umaru.evstats.entity.Ticket;
import com.umaru.evstats.mapper.EventMapper;
import com.umaru.evstats.mapper.TicketMapper;
import com.umaru.evstats.mapper.UserMapper;
import com.umaru.evstats.entity.Role;
import com.umaru.evstats.entity.User;
import com.umaru.evstats.repository.EventRepository;
import com.umaru.evstats.repository.FavoriteRepository;
import com.umaru.evstats.repository.RoleRepository;
import com.umaru.evstats.repository.UserRepository;
import com.umaru.evstats.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.umaru.evstats.mapper.UserMapper.mapToUserDto;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

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
    public List<UserDto> getUsersUser() {
        List<User> users = usersRepository.findAll()
                .stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.getName().equals(TbConstants.Roles.USER)))
                .collect(Collectors.toList());
        List<UserDto> userDtos = users.stream()
                .map((user) -> (UserMapper.mapToUserDto(user)))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto getUser(Long userId) {
        Optional<User> users = usersRepository.findById(userId);
        if (users.isPresent()){
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
    public int getJmlUser() {
        return getUsersUser().size();
    }

    @Override
    public User findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public void favoriteEvent(Long userId, Long eventId){
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setEventId(eventId);

        FavoriteDto favoriteDto = EventMapper.mapToFavoriteDto(favorite);
        favoriteRepository.save(favorite);
    }

    @Override
    public List<FavoriteDto> getFavoritedEvent(){
        List<Favorite> favorites = favoriteRepository.findAll();
        List<FavoriteDto> favoriteDtos = favorites.stream()
                .map((favorite) -> (EventMapper.mapToFavoriteDto(favorite)))
                .collect(Collectors.toList());
        return favoriteDtos;
    }

    public void deleteFavoritedEvent(Long userId, Long eventId) {
        Favorite favorite = favoriteRepository.findByUserIdAndEventId(userId, eventId);
        favoriteRepository.deleteById(favorite.getId());
    }
}
