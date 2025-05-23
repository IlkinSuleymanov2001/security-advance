package com.example.demo.permission_ms.service;


import com.example.demo.permission_ms.dao.entity.UserPermission;
import com.example.demo.permission_ms.dao.repository.UserPermissionRepository;
import com.example.demo.permission_ms.model.CheckPermissionDto;
import com.example.demo.permission_ms.model.mapper.PermissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final UserPermissionRepository userPermissionRepository;
    private final PermissionMapper mapper;


    public boolean checkPermission(CheckPermissionDto dto) {
        var userPermission = userPermissionRepository
                .findByUserIdAndPermissionNameAndProductName(
                        dto.getUserId(),
                        dto.getPermissionName(),
                        dto.getProductName()

                );

        return userPermission.isPresent();
    }


    public void addPermission(CheckPermissionDto dto) {
        UserPermission userPermission = mapper.getUserPermission(dto);
        userPermissionRepository.save(userPermission);

    }

}
