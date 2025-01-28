package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceV2 userService;

    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }

    //private final List<User> users= new ArrayList<>();
    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }



    /*
        List<UserResponse> responses = new ArrayList<>(); //responses라는 리스트를 만듦
        for(int i=0;i<users.size();i++){
            //responses.add(new UserResponse(i+1,users.get(i).getName(),users.get(i).getAge()));
            responses.add(new UserResponse(i+1,users.get(i)));
            // 직접 다 넣어주는게 아닌 users만 넣어주면 생성자에서 알아서 이름은 이름, 나이는 나이로
            // 매핑해준다 (더 깔끔한 코드임)
        }//저장 API에서 만들어 두었던 user리스트에 담겨있던 User들을
        //반복문을 돌면서 UserResponse형태로 바꿔서
        //결과 list에 추가하고 최종적으로 responses를 반환 -> api정상적으로 동작
        return responses;
    }
}
*/

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }
}

/**
 * @PutMapping("/user") public void updateUser(@RequestBody UserUpdateRequest request){
 * String sql="UPDATE user SET name = ? WHERE id=?";
 * jdbcTemplate.update(sql,request.getName(),request.getId());
 * }
 * @DeleteMapping("/user") public void deleteUser(@RequestParam String name)
 * String sql="DELETE FROM user WHERE name = ?";
 * jdbcTemplate.update(sql,name):
 */