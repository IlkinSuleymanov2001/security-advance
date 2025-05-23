package com.example.demo.service.impl;


import com.example.demo.aspect.performance.LogElapsedTime;
import com.example.demo.dao.entity.Guest;
import com.example.demo.dao.repository.GuestRepository;
import com.example.demo.mapper.GuestMapper;
import com.example.demo.model.request.GuestDto;
import com.example.demo.model.request.GuestResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static com.example.demo.exception.util.ExceptionUtil.EXCEPTION;
import static com.example.demo.model.enums.ErrorInfo.GUEST_NOT_FOUND;
import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@LogElapsedTime
public class GuestServiceHandler {

    GuestMapper guestMapper;
    GuestRepository guestRepository;


    public GuestResponseDto createGuest(GuestDto guestDto) {
        return guestMapper.toGuestResponseDto(createGuestEntity(guestDto));
    }

    Guest createGuestEntity(GuestDto guestDto) {
        Guest guest = guestMapper.toGuest(guestDto);
        return guestRepository.save(guest);
    }

    Guest getGuestEntity(Long id) {
        return guestRepository.findById(id).orElseThrow(
                () -> EXCEPTION.notFoundThrowable(GUEST_NOT_FOUND)
        );
    }

    Guest getGuestEntity(String legalId) {
        return guestRepository.findByLegalId(legalId).orElseThrow(
                () -> EXCEPTION.notFoundThrowable(GUEST_NOT_FOUND)
        );
    }

    public GuestResponseDto getGuest(Long id) {
        Guest guestEntity = getGuestEntity(id);
        return guestMapper.toGuestResponseDto(guestEntity);
    }

    public GuestResponseDto getGuest(String legalId) {
        Guest guestEntity = getGuestEntity(legalId);
        return guestMapper.toGuestResponseDto(guestEntity);
    }


}
