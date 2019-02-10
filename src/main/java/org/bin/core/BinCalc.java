package org.bin.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.bin.exception.CalculationException;
import org.bin.exception.ListContainNullException;
import org.bin.exception.PreValidationException;
import org.bin.util.BinUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class BinCalc {

  private final List<Boolean> initialList;
  private final Integer iterations;

  public static BinCalc getInstance(final List<Boolean> list, final Integer iterations) {
    preValidation(list, iterations);
    return new BinCalc(list, iterations);
  }

  private static void preValidation(List<Boolean> list, Integer iterations) {
    if (CollectionUtils.isEmpty(list)) {
      throw new PreValidationException("List cannot be empty");
    } else if (list.size() < 2) {
      throw new PreValidationException("List minimum size is 2");
    }
    for (Boolean b : list) {
      if (Objects.isNull(b))
        throw new ListContainNullException("List contains null Boolean");
    }
    if (Objects.isNull(iterations)) {
      throw new PreValidationException("Iterations is null");
    } else if (iterations < 1) {
      throw new PreValidationException("Iterations should be at least of 1");
    }
  }

  private BinCalc(final List<Boolean> list, final Integer iterations) {
    this.initialList = list;
    this.iterations = iterations;
  }

  public String calculate() {
    List<Boolean> finalList = initialList;
    log.debug("Initial list :  {}", BinUtil.convertToString(initialList));
    for (int i=0; i<iterations; i++){
      finalList = BinUtil.calc(finalList);
      log.debug("Iteration {} result :  {}", i+1, BinUtil.convertToString(finalList));
    }
    return BinUtil
      .convertToString(finalList)
      .orElseThrow(() -> new CalculationException("Error to convert to string"));
  }

}
