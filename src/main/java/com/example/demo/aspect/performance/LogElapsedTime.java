package com.example.demo.aspect.performance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Define the annotation that can be applied to classes
@Target(ElementType.TYPE)  // This annotation can be applied to classes
@Retention(RetentionPolicy.RUNTIME)  // The annotation will be available at runtime
public @interface LogElapsedTime {
    // You can define additional parameters here if needed (e.g., log level, custom message, etc.)
}
