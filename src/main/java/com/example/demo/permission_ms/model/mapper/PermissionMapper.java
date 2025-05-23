package com.example.demo.permission_ms.model.mapper;


import com.example.demo.permission_ms.dao.entity.UserPermission;
import com.example.demo.permission_ms.model.CheckPermissionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    UserPermission getUserPermission(CheckPermissionDto checkPermissionDto);
}
