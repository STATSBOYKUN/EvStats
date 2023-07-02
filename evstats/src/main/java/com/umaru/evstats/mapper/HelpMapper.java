package com.umaru.evstats.mapper;

import com.umaru.evstats.dto.HelpDto;
import com.umaru.evstats.entity.Help;

public class HelpMapper {
    public static HelpDto mapToHelpDto(Help help) {
        HelpDto helpDto = HelpDto.builder()
                .id(help.getId())
                .name(help.getName())
                .email(help.getEmail())
                .message(help.getMessage())
                .date(help.getDate())
                .build();
        return helpDto;
    }

    public static Help mapToHelp(HelpDto helpDto) {
        Help help = Help.builder()
                .id(helpDto.getId())
                .name(helpDto.getName())
                .email(helpDto.getEmail())
                .message(helpDto.getMessage())
                .date(helpDto.getDate())
                .build();
        return help;
    }
}
