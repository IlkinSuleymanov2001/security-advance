package com.example.demo.service.card;

import com.example.demo.dao.entity.CardEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import static com.example.demo.exception.util.ExceptionUtil.EXCEPTION;
import static com.example.demo.model.enums.ErrorInfo.PAN_NOT_VALID;


@Component
public class CardCvvService {

    // Function to generate a random 3-digit CVV code
    String generateCVV() {
        Random rand = new Random();
        int cvv = rand.nextInt(900) + 100; // Generates a 3-digit number (100 - 999)
        return String.valueOf(cvv); // Return the CVV as a string
    }

    // Function to validate if a CVV is 3 digits
    boolean validateCVV(String cvv) {
        if (cvv == null || cvv.length() != 3) {
            return false; // Invalid if not exactly 3 digits
        }
        try {
            Integer.parseInt(cvv); // Try to convert to integer to ensure it's numeric
            return true;
        } catch (NumberFormatException e) {
            return false; // Invalid if not a number
        }
    }

    public List<CardEntity> setCvv(List<CardEntity> cardEntities) {

        return cardEntities.stream().map(e -> {
            String cvv = generateCVV();
            if (!validateCVV(cvv)) {
                for (int i = 0; i < 100; i++) {
                    cvv = generateCVV();
                    if (validateCVV(cvv)) {
                        break;
                    }
                }
            }

            if (!validateCVV(cvv)) {
                EXCEPTION.badRequest(PAN_NOT_VALID);
            }
            e.setCvv(cvv);
            return e;

        }).toList();
    }

}
