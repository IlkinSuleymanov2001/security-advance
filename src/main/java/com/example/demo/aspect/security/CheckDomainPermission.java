package com.example.demo.aspect.security;

import com.example.demo.model.enums.Permission;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)// domainleride satstiklersdiremey olar
@PreAuthorize("@permissionProxyService.checkPermission(T(com.example.demo.model.enums.Permission).#value)")
public @interface CheckDomainPermission {
    Permission value();
}
