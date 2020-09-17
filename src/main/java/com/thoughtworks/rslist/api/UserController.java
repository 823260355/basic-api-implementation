package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.RsEvent;
import com.thoughtworks.rslist.dto.UserInfo;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.repository.RsEventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
//    public List<UserInfo> userList = initUserList();
    @Autowired
    UserRepository userRepository;
    @Autowired
    RsEventRepository rsEventRepository;
//    public List<UserInfo> initUserList() {
//        ArrayList<UserInfo> tempList = new ArrayList<>();
//        tempList.add(new UserInfo("xiaowang1",19,"female","a@thoughtworks.com","18888888888"));
//        return tempList;
//    }

//    @GetMapping("/user/list")
//    public ResponseEntity<List<UserInfo>> geAllUser(){
//        return  ResponseEntity.ok().body(userList);
//    }

    @PostMapping("/user/register")
    public void register(@Valid @RequestBody UserInfo userInfo){
        UserEntity userEntity=UserEntity.builder()
                .userName(userInfo.getUserName())
                .email(userInfo.getEmail())
                .age(userInfo.getAge())
                .gender(userInfo.getGender())
                .phone(userInfo.getPhone())
                .voteNum(userInfo.getAge())
                .build();
        userRepository.save(userEntity);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){
        Optional<UserEntity> user = userRepository.findById(id);
        return  ResponseEntity.ok().body(user);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Transactional
    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable Integer id){
        userRepository.deleteById(id);
        rsEventRepository.deleteAllByUserId(id);
    }
}
