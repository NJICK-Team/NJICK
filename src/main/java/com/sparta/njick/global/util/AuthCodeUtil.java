package com.sparta.njick.global.util;

import java.security.SecureRandom;
import java.util.Random;

public class AuthCodeUtil {
    public static String generateAuthCode(int length) {
        StringBuffer key = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 -> key.append((char) (random.nextInt(26) + 97));
                case 1 -> key.append((char) (random.nextInt(26) + 65));
                case 2 -> key.append(random.nextInt(10));
            }

        }

        return key.toString();
    }

    public static String generateOTP(int otpLength) {
        StringBuffer otp = new StringBuffer();

        String defaultNumber = "0123456789";
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < otpLength; i++) {
            int randomIdx = secureRandom.nextInt(defaultNumber.length());
            char randomDigit = defaultNumber.charAt(randomIdx);
            otp.append(randomDigit);
        }

        return otp.toString();
    }
}
