package com.example.demo.restapijvs;

import com.example.demo.restapijvs.user.adapter.persistence.UserPersistence;
import com.example.demo.restapijvs.user.entity.UserEntity;
import com.example.demo.restapijvs.user.service.GetAllUserService;
import com.example.demo.restapijvs.user.service.GetUserByIdService;
import com.example.demo.restapijvs.utils.ResponseApi;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class GetUserByIdServiceTest {
    @Mock
    private UserPersistence userPersistence;
    @InjectMocks
    private GetUserByIdService getUserByIdService;
    @Test
    public void getUserById_WhenUserExist_WillReturnCorrectResponse(){
        //Given
        Integer userId = 1;
        UserEntity user = new UserEntity(1, "hendra", "hendra@gmail.com", "08123456789", "password");
        ResponseApi body = new ResponseApi().HttpStatusOK("Success get data", user);
        var resp = new ResponseEntity<>(body, HttpStatus.OK);
        given(userPersistence.getUserById(userId)).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualUsers = getUserByIdService.getUserById(userId);

        //Then
        assertEquals(resp, actualUsers);
    }

    @Test
    public void getUserById_WhenUserNotExist_WillReturnCorrectResponse(){
        //Given
        Integer userId = -1;
        ResponseApi body = new ResponseApi().DataNotFound();
        var resp = new ResponseEntity<>(body, HttpStatus.OK);
        given(userPersistence.getUserById(userId)).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualUsers = getUserByIdService.getUserById(userId);

        //Then
        assertEquals(resp, actualUsers);
    }
}
