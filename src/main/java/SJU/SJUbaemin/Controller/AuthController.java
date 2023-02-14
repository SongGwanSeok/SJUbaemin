package SJU.SJUbaemin.Controller;

import SJU.SJUbaemin.Domain.Dto.LoginDto;
import SJU.SJUbaemin.Domain.Dto.TokenDto;
import SJU.SJUbaemin.Jwt.JwtFilter;
import SJU.SJUbaemin.Jwt.TokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        //loginDto에서 아이디와 비밀번호를 받아서 authenticationToken 객체를 생성한다.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getLoginId(), loginDto.getLoginPw());

        // authenticationToken 객체를 이용해서 authentication 객체를 생성하려고
        // authentication 메소드가 실행이 될때 loadUserByLoginId 메서드가 실행된다.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 권한정보를 SecurityContext 에 저장한다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //DB에서 loginId로 찾아온 member로 만든 권한정보를 가지고 token을 생성한다.
        String jwt = tokenProvider.createToken(authentication);


        HttpHeaders httpHeaders = new HttpHeaders();
        // jwt 토큰을 response header에 넣어준다.
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        // TokenDto를 이용해 responseBody에도 넣어주고 리턴한다.
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
