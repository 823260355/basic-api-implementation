package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.RsEvent;
import com.thoughtworks.rslist.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class RsController {

    private List<RsEvent> rsList = initRsList();

  private List<RsEvent> initRsList(){

      ArrayList<RsEvent> tempList = new ArrayList<>();
      tempList.add(new RsEvent("第一条事件","分类1",new UserInfo("xiaowang1",19,"female","a@thoughtworks.com","18888888888")));
      tempList.add(new RsEvent("第二条事件","分类2",new UserInfo("xiaowang2",20,"male","b@thoughtworks.com","18888888888")));
      tempList.add(new RsEvent("第三条事件","分类3",new UserInfo("xiaowang3",21,"female","c@thoughtworks.com","18888888888")));
      return tempList;
  }



    @GetMapping("/rs/list")
  public List<RsEvent> geAllEvent(@RequestParam(required = false) Integer start,
                              @RequestParam(required = false) Integer end
                            ){
      if (start == null || end == null) {
        return rsList;
      }
      return rsList.subList(start-1,end);
  }

  @GetMapping("/rs/{index}")
  public RsEvent getOneEvent(@PathVariable int index){
    return rsList.get(index-1);
  }

  @PostMapping("/rs/event")
  public void addEventHaveEventNameAndKeyword(@Valid @RequestBody RsEvent rsEvent){
      for (int i = 0; i < rsList.size(); i++) {
          if (rsEvent.getUserInfo().getUserName().equals(rsList.get(i).getUserInfo().getUserName())){
              rsList.add(rsEvent);
              return;
          }
      }



  }

  @DeleteMapping("/rs/{index}")
  public void delEvent(@PathVariable int index){
      rsList.remove(index-1);
  }

  @GetMapping("/rss/{index}")
  public RsEvent updateEvent(@PathVariable int index,
                          @RequestParam(required = false) String eventName,
                          @RequestParam(required = false) String keyword){

      if (eventName !=null && keyword ==null){
          RsEvent rsEvent = rsList.get(index - 1);
          rsList.set(index-1,new RsEvent(eventName,rsEvent.getKeyword(),rsEvent.getUserInfo()));
      }
      if (eventName ==null && keyword !=null){
          RsEvent rsEvent = rsList.get(index - 1);
          rsList.set(index-1,new RsEvent(rsEvent.getEventName(),keyword,rsEvent.getUserInfo()));
      }
      if (eventName !=null && keyword != null){
          RsEvent rsEvent = rsList.get(index - 1);
          rsList.set(index-1,new RsEvent(eventName,keyword,rsEvent.getUserInfo()));
      }
      return rsList.get(index-1);
  }
}
