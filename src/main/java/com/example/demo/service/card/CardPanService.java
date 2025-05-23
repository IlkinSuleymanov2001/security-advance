package com.example.demo.service.card;

import com.example.demo.dao.entity.CardEntity;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

import static com.example.demo.exception.util.ExceptionUtil.EXCEPTION;
import static com.example.demo.model.enums.ErrorInfo.CVV_NOT_VALID;


@Component
public class CardPanService {

    // Method to generate a valid 16-digit PAN (Primary Account Number)
    String createPan() {
        // Step 1: Generate the first 15 digits randomly
        SecureRandom random = new SecureRandom();
        StringBuilder pan = new StringBuilder();

        // Generate 15 random digits
        for (int i = 0; i < 15; i++) {
            pan.append(random.nextInt(10));  // Random digit between 0 and 9
        }

        // Step 2: Calculate the check digit using the Luhn algorithm
        int checkDigit = calculateCheckDigit(pan.toString());

        // Step 3: Append the check digit to the PAN
        pan.append(checkDigit);

        return pan.toString();
    }

    // Method to calculate the check digit using the Luhn algorithm
    private int calculateCheckDigit(String pan) {
        int sum = 0;
        boolean alternate = false;

        // Step through the PAN digits from right to left
        for (int i = pan.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(pan.charAt(i));

            // Double every second digit from the right
            if (alternate) {
                digit *= 2;
                // If the result is greater than 9, subtract 9
                if (digit > 9) {
                    digit -= 9;
                }
            }

            // Add the current digit (or modified digit) to the sum
            sum += digit;

            // Toggle the alternate flag
            alternate = !alternate;
        }

        // The check digit is the number needed to make the sum a multiple of 10
        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit;
    }

    boolean isValid(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        // Start from the rightmost digit
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            char c = cardNumber.charAt(i);
            if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);

                // Double every second digit
                if (alternate) {
                    digit = digit * 2;
                    // If doubling results in a number > 9, subtract 9
                    if (digit > 9) {
                        digit -= 9;
                    }
                }

                // Add the digit to the sum
                sum += digit;
                alternate = !alternate;
            }
        }

        // The number is valid if the sum is divisible by 10
        return sum % 10 == 0;
    }

    public List<CardEntity> setPan(List<CardEntity> cardEntities) {

        return cardEntities.stream().map(e -> {
            String pan = createPan();
            if (!isValid(pan)) {
                for (int i = 0; i < 100; i++) {
                    pan = createPan();
                    if (isValid(pan)) {
                        break;
                    }
                }
            }

            if (!isValid(pan)) {
                EXCEPTION.badRequest(CVV_NOT_VALID);
            }
            e.setPan(pan);
            return e;

        }).toList();
    }


}
