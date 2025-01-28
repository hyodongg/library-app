package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,length=20,name="name")
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories=new ArrayList<>();


    protected User(){

    }

    public User(String name, Integer age) {
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.",name));
        }
        // String.format은 name변수를 삽입한 형식화된 문자열을 생성하고 반환함.
        // 이 반환된 문자열이 실제로 예외 객체의 메세지가 됨
        // IllegalArgumentException은 예외 객체를 생성하면서 그 메세지를 포함시킴
        // 이 메세지는 예외가 발생할 때 로그나 콘솔에 출력될 수 있음.
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name){
        this.name=name;
    }

    public void loanBook(String bookName){
        this.userLoanHistories.add(new UserLoanHistory(this,bookName));
    }

    public void returnBook(String bookName){
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }
}



//UserController에 있는 API가 사용되어서 User가 저장되려면 User객체를 만들어서
// 실제 list에 저장한다.

//그냥 DTO가 아닌 따로User class를 만드는 이유
//DTO와 도메인 모델의 차이점.
//DTO는 계층 간 데이터 교환을 위해 사용되는 객체이며, 주로 뷰와 컨트롤러 사이 또는
//서비스계층과 컨트롤러 계층 사이에서 데이터를 전달하는 데 사용됨
//반면 도메인 모델(여기서 User 클래스)는 실제 비즈니스 도메인을 표현하는 객체로
//비즈니스 로직을 포함하고 있으며, 데이터베이스의 테이블과 매핑되어 데이터를
//영구적으로 저장하거나 관리하는 데 사용된다.
//DTO를 사용해 비즈니스 로직을 처리하거나 엔티티의 역할을 하게 하면,
// 그 객체가 담당하는 책임이 모호해지고, 계층 간의 역할 분리 원칙이 흐려짐으로써
// 유지 보수성과 확장성 측면에서 문제가 발생할 수 있습니다.
// 따라서 User 클래스 같은 도메인 모델을 따로 만드는 것은
// 이러한 객체에 명확한 역할과 책임을 부여함으로써,
// 애플리케이션의 아키텍처를 깔끔하게 유지하고 비즈니스 로직의 중심을
// 명확하게 하는 데 있습니다.

//쉽게 말해서, dto : 단순히 어떤 데이터로 통신할건지 정의만 해놓고
//상세한 정의는 domain의 user에서 하는 개념.