package com.example.demo.util;

import com.example.demo.dao.entity.securtiy.PermissionEntity;
import com.example.demo.model.enums.Permission;
import com.example.demo.service.impl.security.CachePermissionsModel;

public final class PermissionUtil {

//    public static boolean hasPermission(String permissionString, PermissionEntity permissionEntity) {
//        return findPermissionType(permissionString)
//                .map(permission -> switch (permission) {
//                    case READ -> permissionEntity.isRead();
//                    case DELETE -> permissionEntity.isDelete();
//                    case CREATE, UPDATE -> permissionEntity.isWrite();
//                })
//                .orElse(false);
//    }
//
//    public static boolean hasPermission(String permissionString, CachePermissionsModel cachePermissionsModel) {
//        return findPermissionType(permissionString)
//                .map(permission -> switch (permission) {
//                    case READ -> cachePermissionsModel.isRead();
//                    case DELETE -> cachePermissionsModel.isDelete();
//                    case CREATE, UPDATE -> cachePermissionsModel.isWrite();
//                })
//                .orElse(false);
//    }

    public static boolean hasPermission(Permission permission, CachePermissionsModel cachePermissionsModel) {
        return switch (permission) {
            case READ -> cachePermissionsModel.isRead();
            case DELETE -> cachePermissionsModel.isDelete();
            case WRITE -> cachePermissionsModel.isWrite();
        };
    }

    public static boolean hasPermission(Permission permission, PermissionEntity permissionEntity) {
        return switch (permission) {
            case READ -> permissionEntity.isRead();
            case DELETE -> permissionEntity.isDelete();
            case WRITE -> permissionEntity.isWrite();
        };
    }

//    private static Optional<Permission> findPermissionType(String permissionString) {
//        return Arrays.stream(Permission.values())
//                .filter(p -> permissionString.endsWith(p.getValue()))
//                .findFirst();
//    }
//
//
//    private enum Permission {
//        CREATE("_CREATE"),
//        UPDATE("_UPDATE"),
//        READ("_READ"),
//        DELETE("_DELETE");
//
//        private final String value;
//
//        Permission(String value) {
//            this.value = value;
//        }
//
//        public String getValue() {
//            return value;
//        }
//    }
}
