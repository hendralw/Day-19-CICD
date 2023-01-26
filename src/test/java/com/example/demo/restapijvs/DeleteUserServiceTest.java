package com.example.demo.restapijvs;

import com.example.demo.restapijvs.user.adapter.persistence.UserPersistence;
import com.example.demo.restapijvs.user.service.DeleteUserService;
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
public class DeleteUserServiceTest {
    @Mock
    private UserPersistence userPersistence;
    @InjectMocks
    private DeleteUserService deleteUserService;
    @Test
    public void deleteUser_WhenDataExist_WillReturnCorrectResponse(){
        //Given
        Integer userId = 1;
        ResponseApi body = new ResponseApi().HttpStatusOK("Success delete data", null);
        var resp = new ResponseEntity<>(body, HttpStatus.OK);
        given(userPersistence.deleteUser(userId)).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualResp = deleteUserService.deleteUser(userId);

        //Then
        assertEquals(resp, actualResp);
    }

    @Test
    public void deleteUser_WhenDataNotExist_WillReturnCorrectResponse(){
        //Given
        Integer userId = -1;
        ResponseApi body = new ResponseApi().DataNotFound();
        var resp = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        given(userPersistence.deleteUser(userId)).willReturn(resp);

        //When
        ResponseEntity<ResponseApi> actualResp = deleteUserService.deleteUser(userId);

        //Then
        assertEquals(resp, actualResp);
    }
}
