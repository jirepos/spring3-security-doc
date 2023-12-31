package com.kyoofus.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyoofus.api.dto.UserDto;
import com.kyoofus.framework.core.exception.AjaxExceptoin;
import com.kyoofus.framework.core.exception.ErrorCode;

@RequestMapping("/api/v1")
@Controller
public class NoAuthApiController {

  /** 인증없이 호출할 수 있는 API */
  @GetMapping("/noauth")
  public ResponseEntity<UserDto> hello() {
    UserDto userDto = new UserDto();
    userDto.setUserId("test");
    userDto.setUserName("테스트");

    boolean isTest = true;
    if(isTest) throw new AjaxExceptoin("테스트 에러", ErrorCode.OTHER_ERROR);

    return ResponseEntity.ok(userDto); 
  }



  
}///~





