package org.bin.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bin.exception.ListContainNullException;
import org.bin.exception.InitNullException;
import org.bin.exception.PreValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BinUtil {

  private BinUtil(){}

  public static Optional<String> convertToString (List<Boolean> list){
    if (Objects.isNull(list))
      throw new InitNullException("List cannot be null");
    if (CollectionUtils.isEmpty(list))
      return Optional.empty();
    return Optional.of(list.stream()
      .map(bool -> {
        if (Objects.isNull(bool)){
          throw new ListContainNullException("List contains null Boolean objects");
        } else if (bool) {
          return "1";
        } else {
          return "0";
        }
      })
      .collect(Collectors.joining("")));
  }

  public static Optional<List<Boolean>> convertToList(String value) {
    if (StringUtils.isNotEmpty(value)) {
      var list = new ArrayList<Boolean>(value.length());
      for (char c : value.toCharArray()) {
        if (c == '0') {
          list.add(false);
        } else if (c == '1') {
          list.add(true);
        }
        else {
          return Optional.empty();
        }
      }
      return Optional.of(list);
    } else {
      return Optional.empty();
    }
  }

  public static boolean xor(boolean a, boolean b) {
    return a ^ b;
  }

  public static Boolean sum(Boolean a, Boolean b) {
    if (Objects.isNull(a) && Objects.isNull(b))
      throw new PreValidationException("both elements are null");

    if (Objects.isNull(a)) {
      return b;
    }

    if (Objects.isNull(b)) {
      return a;
    }
    return xor(a,b);
  }

  public static List<Boolean> calc (final List<Boolean> list) {
    if (Objects.isNull(list))
      throw new InitNullException("List cannot be null");
    var newList = new ArrayList<Boolean>(list.size());
    for (int i=0;i<list.size();i++){
      Boolean sumResult;
      if (i==0){
        sumResult = BinUtil.sum(null,list.get(i+1));
      } else if (i==list.size()-1){
        sumResult = BinUtil.sum(list.get(list.size()-2),null);
      } else {
        sumResult = BinUtil.sum(list.get(i-1),list.get(i+1));
      }
      newList.add(i,sumResult);
    }
    return newList;
  }
}
