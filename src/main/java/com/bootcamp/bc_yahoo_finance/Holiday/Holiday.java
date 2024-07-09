package com.bootcamp.bc_yahoo_finance.Holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Holiday {
 private List<LocalDate> holiday2024List;

  public Holiday(){
    this.holiday2024List=
    new ArrayList<>(List.of(LocalDate.of(2024, 1, 1),    
    LocalDate.of(2024, 1, 22) ,   
    LocalDate.of(2024, 1, 23)  ,  
    LocalDate.of(2024, 1, 24)  ,  
    LocalDate.of(2024, 1, 25)  , 
    LocalDate.of(2024, 4, 5)   ,  
    LocalDate.of(2024, 5, 1)  ,
    LocalDate.of(2024, 5, 13)   ,
    LocalDate.of(2024, 6, 19) ,   
    LocalDate.of(2024, 7, 1)  ,  
    LocalDate.of(2024, 9, 16) ,  
    LocalDate.of(2024, 10, 1) ,   
    LocalDate.of(2024, 10, 2)  ,  
    LocalDate.of(2024, 10, 10)));
  }
  public boolean isHoliday(LocalDate date) {
    if (this.holiday2024List.contains(date)) {
      return true;
  }
  
 
  DayOfWeek dayOfWeek = date.getDayOfWeek();
  return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
}
public LocalDate getPreviousWorkingDay(LocalDate date) {
        LocalDate prevDate = date.minusDays(1);
        while (this.isHoliday(prevDate) || prevDate.getDayOfWeek() 
        == DayOfWeek.SATURDAY || prevDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            prevDate = prevDate.minusDays(1);
        }
        return prevDate;
    }
  
}