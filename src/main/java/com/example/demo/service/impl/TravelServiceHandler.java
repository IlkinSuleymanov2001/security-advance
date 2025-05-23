package com.example.demo.service.impl;


import com.example.demo.aspect.performance.LogElapsedTime;
import com.example.demo.dao.entity.Travel;
import com.example.demo.dao.repository.TravelRepository;
import com.example.demo.mapper.TravelMapper;
import com.example.demo.model.request.TravelRequest;
import com.example.demo.model.request.TravelResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@LogElapsedTime
public class TravelServiceHandler {

    TravelRepository travelRepository;
    TravelMapper travelMapper;
    //Sender  sender;


    public TravelResponse createTravel(TravelRequest travelRequest) {

        Travel travel = travelMapper.toTravel(travelRequest);
        travelRepository.save(travel);
        return travelMapper.toTravelResponse(travel);

    }

    public TravelResponse getTravel(Long id) {
        return travelMapper.toTravelResponse(getTravelEntity(id));

    }

    Travel getTravelEntity(Long id) {
        return travelRepository.findById(id).orElseThrow(() -> new RuntimeException("Travel not found"));
    }


    public List<TravelResponse> getTravels(String country) {
        List<Travel> allByCountry = travelRepository.findAllByCountry(country);
        return allByCountry.stream().map(travelMapper::toTravelResponse).toList();
    }


//    @PostConstruct
//    public void createDefaultTravels() {
//        TravelRequest az = TravelRequest.builder()
//                .country("az")
//                .fromDate(LocalDateTime.now())
//                .toDate(LocalDateTime.now().plusDays(25)).build();
//
//        sender.sendMessageToQ("TEST_Q", az);


    //  }
}
