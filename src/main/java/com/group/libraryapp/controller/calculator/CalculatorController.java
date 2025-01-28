package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController   // API를 개발하려는 클래스에는 가장 위에 쓰기
                    // 바로 아래에 쓴 클래스를 API의 진입지점으로 만들어준다.
public class CalculatorController {
    @GetMapping("/add")// http 명세에 썼었던 GET /add랑 똑같음
    public int addTwoNumbers(CalculatorAddRequest request){
        return request.getNumber1()+request.getNumber2();
    }
    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
        return request.getNumber1()*request.getNumber2();

    }

}
//@RequestBody를 써야 Post API에서 정상적으로 HTTP BODY안에 담긴 JSON을 객체로 변환시킬 수 있음


//HTTP body를 객체로 바꾸는 @RequestBody를 사용하는 경우는, 생성자를 만들지 않아도 괜찮음
