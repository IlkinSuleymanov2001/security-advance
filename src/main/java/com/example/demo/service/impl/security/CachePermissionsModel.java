package com.example.demo.service.impl.security;

import com.example.demo.dao.entity.securtiy.PermissionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CachePermissionsModel {

    private boolean read;
    private boolean delete;
    private boolean write;

    public static CachePermissionsModel of(PermissionEntity p) {
        return new CachePermissionsModel(p.isRead(), p.isDelete(), p.isWrite());
    }
}
