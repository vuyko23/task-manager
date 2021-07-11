package task.manager.controller;

import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import task.manager.dto.request.UserRequestDto;
import task.manager.service.AuthenticationService;

@Log4j2
@Controller
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        String login = userRequestDto.getLogin();
        authenticationService.register(login,
                    userRequestDto.getPassword());
        log.info("User with login " + login + " was registered");
    }
}
