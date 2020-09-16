package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.RsEvent;
import com.thoughtworks.rslist.dto.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    public List<UserInfo> userList = initUserList();
    public List<UserInfo> initUserList() {
        ArrayList<UserInfo> tempList = new ArrayList<>();
        tempList.add(new UserInfo("xiaowang1",19,"female","a@thoughtworks.com","18888888888"));
        return tempList;
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<UserInfo>> geAllUser(){
        return  ResponseEntity.ok().body(userList);
    }

    @PostMapping("/user/register")
    public void register(@Valid @RequestBody UserInfo userInfo){
          userList.add(userInfo);
    }


}
