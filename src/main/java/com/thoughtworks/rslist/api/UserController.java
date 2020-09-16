package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.RsEvent;
import com.thoughtworks.rslist.dto.UserInfo;
import org.springframework.web.bind.annotation.*;

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
    public List<UserInfo> geAllUser(){
        return  userList;
    }

    @PostMapping("/user/register")
    public void register(@RequestBody UserInfo userInfo){
          userList.add(userInfo);
    }


}
