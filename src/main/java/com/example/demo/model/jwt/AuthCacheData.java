package com.example.demo.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthCacheData {

    private final long writeTime = System.currentTimeMillis(); // millis
    private Object data;

    public Object getData() {
        return data;
    }

    public long getWriteTime() {
        return writeTime;
    }
}
