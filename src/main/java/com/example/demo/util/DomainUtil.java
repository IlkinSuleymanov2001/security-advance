package com.example.demo.util;

import com.example.demo.model.enums.Domains;

public final class DomainUtil {
    private DomainUtil() {
    }

    public static Domains findDomain(String uri) {
        for (var domain : Domains.values()) {
            if (uri.equals(domain.getServiceURI()) || uri.contains(domain.getServiceURI())) {
                return domain;
            }
        }
        return null;
    }
}
