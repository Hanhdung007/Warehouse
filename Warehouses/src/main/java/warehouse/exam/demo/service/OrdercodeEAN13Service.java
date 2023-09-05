/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author LAPTOP123
 */
@Service
public class OrdercodeEAN13Service {

    public String buildEAN13(String code) {
        int iSum = 0;

        for (int i = code.length(); i >= 1; i--) {
            int iDigit = Character.getNumericValue(code.charAt(i - 1));
            iSum += (i % 2 == 0) ? iDigit * 3 : iDigit;
        }

        int checkSum = (10 - (iSum % 10)) % 10;
        return code + checkSum;
    }
}
