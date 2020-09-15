package com.thoughtworks.rslist.api;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class RsController {

  private List<String> rsList = initRsList();

  private List<String> initRsList(){
      ArrayList<String> tempList = new ArrayList<>();
      tempList.add("第一条事件");
      tempList.add("第二条事件");
      tempList.add("第三条事件");
      return tempList;
  }

    @GetMapping("/rs/list")
  public String geAllEvent(@RequestParam(required = false) Integer start,
                              @RequestParam(required = false) Integer end
                            ){
      if (start == null || end == null) {
        return rsList.toString();
      }
      return rsList.subList(start-1,end).toString();
  }

  @GetMapping("/rs/{index}")
  public String getOneEvent(@PathVariable int index){
    return rsList.get(index-1);
  }

  @PostMapping("/rs/event")
  public void addEventHaveEventNameAndKeyword(@RequestBody String addString){
      rsList.add(addString);
  }

}
