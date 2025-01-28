package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
    private String name;
    private Integer age; //int는 null을 표현할 수 X Integer는 null표현 가능

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
