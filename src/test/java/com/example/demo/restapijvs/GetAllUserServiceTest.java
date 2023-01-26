package com.example.demo.restapijvs;

import com.example.demo.restapijvs.user.adapter.persistence.UserPersistence;
import com.example.demo.restapijvs.user.adapter.persistence.UserRepository;
import com.example.demo.restapijvs.user.entity.UserEntity;
import com.example.demo.restapijvs.user.service.GetAllUserService;
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

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class GetAllUserServiceTest {
    @Mock
    private UserPersistence userPersistence;
    @InjectMocks
    private GetAllUserService getAllUserService;
    @Test
    public void getAllUsers_WhenProductExist_WillReturnCorrectResponse() {
        //Given
        EasyRandom easyRandom = new EasyRandom();
        List<UserEntity> userList = new ArrayList<UserEntity>();
        userList.add(easyRandom.nextObject(UserEntity.class));
        userList.add(easyRandom.nextObject(UserEntity.class));
        ResponseApi body = new ResponseApi().HttpStatusOK("Success get data", userList);
        var resp = new ResponseEntity<>(body, HttpStatus.OK);
        given(userPersistence.getAllUser()).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualUsers = getAllUserService.getAllUser();

        //Then
        assertEquals(resp, actualUsers);
    }

    @Test
    public void getAllUsers_WhenProductNotExist_WillReturnCorrectResponse(){
        //Given
        List<UserEntity> userList = new ArrayList<UserEntity>();
        ResponseApi body = new ResponseApi().DataNotFound();
        var resp = new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
        given(userPersistence.getAllUser()).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualResp = getAllUserService.getAllUser();

        //Then
        assertEquals(actualResp, resp);
    }
}
