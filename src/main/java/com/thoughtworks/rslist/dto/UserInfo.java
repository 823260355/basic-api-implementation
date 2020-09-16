package com.thoughtworks.rslist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
//    {
//        "eventName": "添加一条热搜",
//            "keyword": "娱乐",
//            "user": {
//        "userName": "xiaowang",
//                "age": 19,
//                "gender": "female",
//                "email": "a@thoughtworks.com",
//                "phone": 18888888888
//    }
//    }
//    名称(不超过8位字符，不能为空)
//    性别（不能为空）
//    年龄（18到100岁之间，不能为空）
//    邮箱（符合邮箱规范）
//    手机号（1开头的11位数字，不能为空）
    @Size(max = 8)
    @NotEmpty
    private String userName;
    @Max(100)
    @Min(18)
    @NotNull
    private Integer age;
    @NotNull
    private String gender;
    @NotNull
    @Pattern(regexp = "^1(\\d){10}$")
    private String email;
    private String phone;
}
