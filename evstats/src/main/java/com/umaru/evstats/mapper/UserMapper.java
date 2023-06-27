package com.umaru.evstats.mapper;

import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .provinsi(user.getProvinsi())
                .pekerjaan(user.getPekerjaan())
                .umur(user.getUmur())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
        return userDto;
    }
    public static User mapToUser(UserDto userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .provinsi(userDto.getProvinsi())
                .pekerjaan(userDto.getPekerjaan())
                .umur(userDto.getUmur())
                .createdAt(userDto.getCreatedAt())
                .updatedAt(userDto.getUpdatedAt())
                .build();
        return user;
    }
}
