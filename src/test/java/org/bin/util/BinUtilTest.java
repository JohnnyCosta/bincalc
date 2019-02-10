package org.bin.util;

import org.bin.exception.ListContainNullException;
import org.bin.exception.InitNullException;
import org.bin.exception.PreValidationException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresent;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.Is.is;

public class BinUtilTest {

  @Test
  public void whenConvertToArrayValidSequence_shouldReturnSequence() {
    assertThat(BinUtil.convertToList("01010"), isPresentAndIs(Arrays.asList(false, true, false, true, false)));
  }

  @Test
  public void whenConvertToArrayInvalidSequence_shouldNotReturn() {
    assertThat(BinUtil.convertToList("01010ABC"), not(isPresent()));
  }

  @Test
  public void whenConvertToArrayNull_shouldNotReturn() {
    assertThat(BinUtil.convertToList(null), not(isPresent()));
  }

  @Test (expected = InitNullException.class)
  public void whenConvertToStringNullList_shouldNotReturn() {
    BinUtil.convertToString(null);
  }

  @Test (expected = ListContainNullException.class)
  public void whenConvertToStringListContainsNull_shouldThrowException() {
    assertThat(BinUtil.convertToString(Arrays.asList(true,false,null)), not(isPresent()));
  }

  @Test
  public void whenConvertToString_shouldReturn() {
    assertThat(BinUtil.convertToString(Arrays.asList(true,false,true,false)), isPresentAndIs("1010"));
  }

  @Test
  public void whenConvertToStringNoElement_shouldReturnEmptylist() {
    assertThat(BinUtil.convertToString(new ArrayList<>()), not(isPresent()));
  }

  @Test
  public void whenXorFalseFalse_shouldFalse() {
    assertThat(BinUtil.xor(false,false), is(false));
  }

  @Test
  public void whenXorTrueTrue_shouldFalse() {
    assertThat(BinUtil.xor(true,true), is(false));
  }

  @Test
  public void whenXorTrueFalse_shouldTrue() {
    assertThat(BinUtil.xor(true,false), is(true));
  }

  @Test
  public void whenXorFalseTrue_shouldTrue() {
    assertThat(BinUtil.xor(false,true), is(true));
  }


  @Test (expected = PreValidationException.class)
  public void whenSumNullNull_shouldThrowException() {
    BinUtil.sum(null,null);
  }

  @Test
  public void whenSumTrueNull_shouldReturnTrue() {
    assertThat(BinUtil.sum(true,null), is(true));
  }

  @Test
  public void whenSumFalseNull_shouldReturnFalse() {
    assertThat(BinUtil.sum(false,null), is(false));
  }

  @Test
  public void whenSumNullTrue_shouldReturnTrue() {
    assertThat(BinUtil.sum(null,true), is(true));
  }

  @Test
  public void whenSumNullFalse_shouldReturnFalse() {
    assertThat(BinUtil.sum(null,false), is(false));
  }

  @Test
  public void whenSumFalseFalse_shouldFalse() {
    assertThat(BinUtil.sum(false,false), is(false));
  }

  @Test
  public void whenSumTrueTrue_shouldFalse() {
    assertThat(BinUtil.sum(true,true), is(false));
  }

  @Test
  public void whenSumTrueFalse_shouldTrue() {
    assertThat(BinUtil.sum(true,false), is(true));
  }

  @Test
  public void whenSumFalseTrue_shouldTrue() {
    assertThat(BinUtil.sum(false,true), is(true));
  }

  @Test(expected = InitNullException.class)
  public void whenCalcNullList_shouldThrowException() {
    BinUtil.calc(null);
  }

  @Test
  public void whenCalcEmptyList_shouldReturnEmpty() {
    assertThat(BinUtil.calc(new ArrayList<>()).isEmpty(), is(true));
  }

  @Test
  public void whenCalcValidList1_shouldReturnValid() {
    var input = BinUtil.convertToList("01101").get();
    var expected = BinUtil.convertToList("11100").get();
    assertThat(BinUtil.calc(input), is(expected));
  }

  @Test
  public void whenCalcValidList2_shouldReturnValid() {
    var input = BinUtil.convertToList("11100").get();
    var expected = BinUtil.convertToList("10110").get();
    assertThat(BinUtil.calc(input), is(expected));
  }

  @Test
  public void whenCalcValidList3_shouldReturnValid() {
    var input = BinUtil.convertToList("10110").get();
    var expected = BinUtil.convertToList("00111").get();
    assertThat(BinUtil.calc(input), is(expected));
  }


}
