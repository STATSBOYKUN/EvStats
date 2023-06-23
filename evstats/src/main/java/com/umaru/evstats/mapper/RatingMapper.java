package com.umaru.evstats.mapper;

import com.umaru.evstats.dto.RatingDto;
import com.umaru.evstats.model.Rating;

public class RatingMapper {
    public static RatingDto mapToRatingDto(Rating rating){
        RatingDto ratingDto = RatingDto.builder()
                .id(rating.getId())
                .star(rating.getStar())
                .comment(rating.getComment())
                .createdAt(rating.getCreatedAt())
                .updatedAt(rating.getUpdatedAt())
                .build();
        return ratingDto;
    }
    public static Rating mapToRating(RatingDto ratingDto){
        Rating rating = Rating.builder()
                .id(ratingDto.getId())
                .star(ratingDto.getStar())
                .comment(ratingDto.getComment())
                .createdAt(ratingDto.getCreatedAt())
                .updatedAt(ratingDto.getUpdatedAt())
                .build();
        return rating;
    }
}
