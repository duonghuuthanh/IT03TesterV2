/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.tester;

import com.dht.unittestdemo.NguyenToService;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author admin
 */
public class NguyenToParamsTester {
    @ParameterizedTest
    @ValueSource(ints = {5, 11, 97, 17})
    public void testOddNumber(int n) {
        boolean actual = NguyenToService.isNguyenTo(n);
        Assertions.assertTrue(actual);
    }
    
    @ParameterizedTest
    @CsvSource(value = {"5,true", "8,false", "11,true", "1,false"})
    public void testCsvFormat(int n, boolean expected) {
        boolean actual = NguyenToService.isNguyenTo(n);
        Assertions.assertEquals(expected, actual);
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/data/data.csv", numLinesToSkip = 1)
    public void testCsvFile(int n, boolean expected) {
        boolean actual = NguyenToService.isNguyenTo(n);
        Assertions.assertEquals(expected, actual);
    }
    
    @ParameterizedTest
    @MethodSource(value = "ntData")
    public void testMethod(int n, boolean expected) {
        boolean actual = NguyenToService.isNguyenTo(n);
        Assertions.assertEquals(expected, actual);
    }
    
    static Stream<Arguments> ntData() {
        return Stream.of(
                Arguments.arguments(1,false),
                Arguments.arguments(2,true),
                Arguments.arguments(4,false)
        );
    }
}
