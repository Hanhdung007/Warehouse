package warehouse.exam.demo.config;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

public class CodeGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();

    public static String generateRandomCode() {
        // Tạo ký tự in hoa đầu tiên
        char firstChar = CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length()));

        // Tạo 3 số ngẫu nhiên
        int randomNumbers = RANDOM.nextInt(900) + 100;  // Đảm bảo là số 3 chữ số

        // Tạo mã theo yêu cầu: chữ in hoa đầu tiên + 3 số ngẫu nhiên
        return String.format(Locale.US, "%c%d", firstChar, randomNumbers);
    }
}

