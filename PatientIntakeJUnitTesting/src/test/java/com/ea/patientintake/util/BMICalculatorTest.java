package com.ea.patientintake.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BMI Calculator should")
class BMICalculatorTest {

  @Test
  @DisplayName("calculate BMI to two places correctly via inches and pounds")
  void calculateCorrectly() {
    assertEquals(19.2, BMICalculator.calculateBmi(69, 130));
    assertEquals(21.52, BMICalculator.calculateBmi(70, 150));
  }
}
