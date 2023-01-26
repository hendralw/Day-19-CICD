package com.example.demo.restapijvs;

import com.example.demo.restapijvs.user.adapter.persistence.UserPersistence;
import com.example.demo.restapijvs.user.model.UserRequestModel;
import com.example.demo.restapijvs.user.service.UpdateUserService;
import com.example.demo.restapijvs.utils.ResponseApi;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class UpdateUserServiceTest {
    @Mock
    private UserPersistence userPersistence;
    @InjectMocks
    private UpdateUserService updateUserService;

    @Test
    public void updateUser_WhenUserExistAndValid_WillReturnCorrectResponse(){
        //Given
        UserRequestModel expectedUser = new UserRequestModel(1, "hendra", "hendra@gmail.com", "08123456789", "password");
        ResponseApi body = new ResponseApi<>().HttpStatusOK("Success add Data", expectedUser);
        var resp = new ResponseEntity<>(body, HttpStatus.OK);
        given(userPersistence.updateUser(expectedUser)).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualUser = updateUserService.updateUser(expectedUser);

        //Then
        assertEquals(resp, actualUser);
    }

    @Test
    public void updateUser_WhenUserNotExistAndValid_WillReturnCorrectResponse(){
        //Given
        UserRequestModel expectedUser = new UserRequestModel(-1, "hendra", "hendra@gmail.com", "08123456789", "password");
        ResponseApi body = new ResponseApi<>().DataNotFound();
        var resp = new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
        given(userPersistence.updateUser(expectedUser)).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualUser = updateUserService.updateUser(expectedUser);

        //Then
        assertEquals(resp, actualUser);
    }

    @Test
    public void updateUser_WhenUserIsEmpty_WillReturnCorrectResponse(){
        //Given
        UserRequestModel expectedUser = new UserRequestModel();
        ResponseApi body = new ResponseApi<>().BadRequest("Bad request");
        var resp = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        given(userPersistence.updateUser(expectedUser)).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualUser = updateUserService.updateUser(expectedUser);

        //Then
        assertEquals(resp, actualUser);
    }

    @Test
    public void updateUser_WhenUserIsNull_WillReturnCorrectResponse(){
        //Given
        UserRequestModel expectedUser = null;
        ResponseApi body = new ResponseApi<>().BadRequest("Bad request");
        var resp = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        given(userPersistence.updateUser(expectedUser)).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualUser = updateUserService.updateUser(expectedUser);

        //Then
        assertEquals(resp, actualUser);
    }
}
