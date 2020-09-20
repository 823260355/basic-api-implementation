package com.thoughtworks.rslist.api;


import com.thoughtworks.rslist.dto.RsEvent;
import com.thoughtworks.rslist.entity.RsEventEntity;
import com.thoughtworks.rslist.repository.RsEventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class RsController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RsEventRepository rsEventRepository;

//    private List<RsEvent> rsList = initRsList();
//
//  private List<RsEvent> initRsList(){
//
//      ArrayList<RsEvent> tempList = new ArrayList<>();
//      tempList.add(new RsEvent("第一条事件","分类1",new UserInfo("xiaowang1",19,"female","a@thoughtworks.com","18888888888")));
//      tempList.add(new RsEvent("第二条事件","分类2",new UserInfo("xiaowang2",20,"male","b@thoughtworks.com","18888888888")));
//      tempList.add(new RsEvent("第三条事件","分类3",new UserInfo("xiaowang3",21,"female","c@thoughtworks.com","18888888888")));
//      return tempList;
//  }
//
//
//
//    @GetMapping("/rs/list")
//  public ResponseEntity<List<Event>> geAllEvent(@RequestParam(required = false) Integer start,
//                                                 @RequestParam(required = false) Integer end
//                            ){
//        List<Event> eventList = new ArrayList<>();
//        List<Event> eventList1 = new ArrayList<>();
//
//      if (start == null || end == null) {
//          for (int i = 0; i < rsList.size(); i++) {
//              eventList1.add(i,new Event(rsList.get(i).getEventName(),rsList.get(i).getKeyword()));
//          }
//          return ResponseEntity.ok(eventList1);
//      }
//        for (int i = start-1; i < end; i++) {
//            eventList.add(i,new Event(rsList.get(i).getEventName(),rsList.get(i).getKeyword()));
//        }
//        if (start < 1 || start > rsList.size() || end > rsList.size() || start > end) {
//            throw new MyException();
//        }
//      return ResponseEntity.ok(eventList.subList(start-1,end));
//  }
//
//  @GetMapping("/rs/{index}")
//  public ResponseEntity<List<Event>> getOneEvent(@PathVariable int index){
//      List<Event> eventList = new ArrayList<>();
//      eventList.set(index,new Event(rsList.get(index).getEventName(),rsList.get(index).getKeyword()));
//      if (index >= rsList.size()){
//          throw new IndexOutOfBoundsException();
//      }
//      return ResponseEntity.ok().body(eventList);
//  }

//  @PostMapping("/rs/event")
//  public ResponseEntity addEventHaveEventNameAndKeyword(@Valid @RequestBody RsEvent rsEvent){
//      for (int i = 0; i < rsList.size(); i++) {
//          if (rsEvent.getUserInfo().getUserName().equals(rsList.get(i).getUserInfo().getUserName())){
//              rsList.add(rsEvent);
//              return ResponseEntity.status(201).body(i);
//          }
//      }
//      return ResponseEntity.badRequest().build();
//  }
  @PostMapping("/rs/event")
  public ResponseEntity addEventHaveEventNameAndKeyword(@Valid @RequestBody RsEvent rsEvent){
      if (!userRepository.existsById(rsEvent.getUserId())){
          return ResponseEntity.status(400).build();
      }
      RsEventEntity entity = RsEventEntity.builder()
                      .eventName(rsEvent.getEventName())
                      .keyword(rsEvent.getKeyword())
                      .userId(rsEvent.getUserId())
                      .build();
      rsEventRepository.save(entity);
      return ResponseEntity.created(null).build();

  }
    @PostMapping("/rs/{rsEventId}")
    public ResponseEntity updateRsEvent(@Valid @RequestBody RsEvent rsEvent,@PathVariable Integer rsEventId){
        if (!userRepository.existsById(rsEvent.getUserId())){
            return ResponseEntity.status(400).build();
        }
        if (rsEvent.getEventName() != null && rsEvent.getKeyword() != null){
            RsEventEntity entity = RsEventEntity.builder()
                    .id(rsEventId)
                   .eventName(rsEvent.getEventName())
                   .keyword(rsEvent.getKeyword())
                   .userId(rsEvent.getUserId())
                   .build();
        rsEventRepository.save(entity);
        return ResponseEntity.created(null).build();
        }
        return ResponseEntity.created(null).build();

    }
//
//  @DeleteMapping("/rs/{index}")
//  public void delEvent(@PathVariable int index){
//      if (index < 1 || index > rsList.size()) {
//          throw new MyException("invalid index");
//      }
//      rsList.remove(index-1);
//  }
//
//  @GetMapping("/rss/{index}")
//  public ResponseEntity<RsEvent> updateEvent(@PathVariable int index,
//                          @RequestParam(required = false) String eventName,
//                          @RequestParam(required = false) String keyword){
//
//      if (eventName !=null && keyword ==null){
//          RsEvent rsEvent = rsList.get(index - 1);
//          rsList.set(index-1,new RsEvent(eventName,rsEvent.getKeyword(),rsEvent.getUserInfo()));
//      }
//      if (eventName ==null && keyword !=null){
//          RsEvent rsEvent = rsList.get(index - 1);
//          rsList.set(index-1,new RsEvent(rsEvent.getEventName(),keyword,rsEvent.getUserInfo()));
//      }
//      if (eventName !=null && keyword != null){
//          RsEvent rsEvent = rsList.get(index - 1);
//          rsList.set(index-1,new RsEvent(eventName,keyword,rsEvent.getUserInfo()));
//      }
//      if (index < 1 || index > rsList.size()) {
//          throw new MyException();
//      }
//      return ResponseEntity.ok(rsList.get(index-1));
//  }

//  @ExceptionHandler({IndexOutOfBoundsException.class,NullPointerException.class,MyException.class, MethodArgumentNotValidException.class})
//    public ResponseEntity<CommentError> handleIndexOutOfBoundsException(Exception e){
//      if (e instanceof MethodArgumentNotValidException){
//          CommentError commentError =new CommentError();
//          commentError.setError("invalid param");
//          return ResponseEntity.badRequest().body(commentError);
//      }
//
//
//      CommentError commentError = new CommentError();
//      commentError.setError("invalid index");
//      return ResponseEntity.badRequest().body(commentError);
//  }
}
