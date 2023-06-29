package com.umaru.evstats.service;

import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.entity.Ticket;
import com.umaru.evstats.mapper.TicketMapper;
import com.umaru.evstats.mapper.UserMapper;
import com.umaru.evstats.entity.Role;
import com.umaru.evstats.entity.User;
import com.umaru.evstats.repository.RoleRepository;
import com.umaru.evstats.repository.UserRepository;
import com.umaru.evstats.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        User user;
        user = new User(
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                Arrays.asList(role)
        );

        User userFull = UserMapper.mapToUser(userDto);

        userRepository.save(userFull);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map((user) -> (UserMapper.mapToUserDto(user)))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<UserDto> getUsersUser() {
        List<User> users = userRepository.findAll()
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
        Optional<User> users = userRepository.findById(userId);
        if (users.isPresent()){
            return UserMapper.mapToUserDto(users.get());
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public int getJmlUser() {
        return getUsersUser().size();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public UserDto findUserDtoByEmail(String email) {
        return mapToUserDto(userRepository.findByEmail(email)) ;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
