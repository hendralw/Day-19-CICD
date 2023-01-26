package com.example.demo.restapijvs;

import com.example.demo.restapijvs.user.adapter.persistence.UserPersistence;
import com.example.demo.restapijvs.user.model.UserRequestModel;
import com.example.demo.restapijvs.user.service.CreateUserService;
import com.example.demo.restapijvs.utils.ResponseApi;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreateUserServiceTest {
    @Mock
    private UserPersistence userPersistence;
    @InjectMocks
    private CreateUserService createUserService;
    @Test
    public void createUser_WhenUserValid_WillReturnCorrectResponse(){
        //Given
        UserRequestModel expectedUser = new UserRequestModel(1, "hendra", "hendra@gmail.com", "08123456789", "password");
        ResponseApi body = new ResponseApi<>().HttpStatusOK("Success add Data", expectedUser);
        var resp = new ResponseEntity<>(body, HttpStatus.OK);
        given(userPersistence.createUser(expectedUser)).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualUser = createUserService.createUser(expectedUser);

        //Then
        assertEquals(resp, actualUser);
    }

    @Test
    public void createUser_WhenProductIsEmpty_WillReturnCorrectResponse(){
        //Given
        UserRequestModel user = new UserRequestModel();
        ResponseApi body = new ResponseApi<>().BadRequest("Bad request");
        var resp = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        given(userPersistence.createUser(user)).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualResp = createUserService.createUser(user);

        //Then
        assertEquals(resp, actualResp);
    }

    @Test
    public void createUser_WhenProductIsNull_WillReturnCorrectResponse(){
        //Given
        UserRequestModel user = null;
        ResponseApi body = new ResponseApi<>().BadRequest("Bad request");
        var resp = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        given(userPersistence.createUser(user)).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualResp = createUserService.createUser(user);

        //Then
        assertEquals(resp, actualResp);
    }
}
