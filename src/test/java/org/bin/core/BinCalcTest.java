package org.bin.core;

import org.bin.exception.ListContainNullException;
import org.bin.exception.PreValidationException;
import org.bin.util.BinUtil;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BinCalcTest {

  @Test (expected = PreValidationException.class)
  public void whenInitWithInvalidNullList_shouldFail() {
    BinCalc
      .getInstance(null, 1);
  }

  @Test (expected = PreValidationException.class)
  public void whenInitWithInvalidListSize_shouldFail() {
    BinCalc
      .getInstance(Arrays.asList(true), 1);
  }

  @Test (expected = ListContainNullException.class)
  public void whenInitWithInvalidListWithNull_shouldFail() {
    BinCalc
      .getInstance(Arrays.asList(true, false, null), 1);
  }

  @Test (expected = PreValidationException.class)
  public void whenInitWithInvalidNullIterations_shouldFail() {
    BinCalc
      .getInstance(Arrays.asList(true,false), null);
  }

  @Test (expected = PreValidationException.class)
  public void whenInitWithInvalidIterationsZero_shouldFail() {
    BinCalc
      .getInstance(Arrays.asList(true,false), 0);
  }

  @Test (expected = PreValidationException.class)
  public void whenInitWithInvalidIterationsNegative_shouldFail() {
    BinCalc
      .getInstance(Arrays.asList(true,false), -1);
  }

  @Test
  public void InitCalculate_shouldReturnExpected() {
    var input = BinUtil
      .convertToList("01101").get();
    var iterations = 3;
    var expected = "00111";
    assertThat(BinCalc
      .getInstance(input, iterations).calculate(), is(expected));
  }

}
