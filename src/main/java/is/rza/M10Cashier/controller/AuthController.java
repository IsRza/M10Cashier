package is.rza.M10Cashier.controller;

import is.rza.M10Cashier.model.dto.LoginDTO;
import is.rza.M10Cashier.model.dto.RegisterDTO;
import is.rza.M10Cashier.model.dto.UserDTO;
import is.rza.M10Cashier.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    UserDTO login(@RequestBody LoginDTO params) {
        return authService.login(params);
    }

    @PostMapping("/register")
    UserDTO register(@RequestBody RegisterDTO params) {
        return authService.register(params);
    }
}
