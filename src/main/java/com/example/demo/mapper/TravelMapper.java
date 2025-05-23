package com.example.demo.mapper;

import com.example.demo.dao.entity.Travel;
import com.example.demo.model.request.TravelRequest;
import com.example.demo.model.request.TravelResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TravelMapper {

    Travel toTravel(TravelRequest travelRequest);

    TravelResponse toTravelResponse(Travel travel);


}
