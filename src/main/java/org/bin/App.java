package org.bin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.IntegerValidator;
import org.bin.core.BinCalc;
import org.bin.util.BinUtil;

import java.util.Objects;


@Slf4j
public class App {

  public static void main(String[] args) {
    if (args.length != 2) {
      log.error("Invalid number of arguments: <ARRAY> and/or <ITERATIONS>");
      printHelp();
    } else {
      var mayBinSequenceValid = BinUtil.convertToList(args[0]);
      var iterations = IntegerValidator
        .getInstance()
        .validate(args[1]);
      if (!mayBinSequenceValid.isPresent()) {
        log.error("Invalid argument: <ARRAY>");
        printHelp();
      } else if (mayBinSequenceValid.get().size()<2) {
        log.error("Invalid array size, minimum is 2: <ARRAY>");
        printHelp();
      }else if (Objects.isNull(iterations) || iterations<1) {
        log.error("Invalid argument: <ITERATIONS>");
        printHelp();
      } else {
        var finalResult = BinCalc.getInstance(mayBinSequenceValid.get(),iterations).calculate();
        log.info("Result is: {}", finalResult);
      }
    }
  }

  private static void printHelp() {
    log.info("Usage: java app.jar <ARRAY> <ITERATIONS>");
  }

}
