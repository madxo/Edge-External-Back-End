package com.example.edgeexternalbackend.Controllers;

import com.example.edgeexternalbackend.Constants.LoginConstants;
import com.example.edgeexternalbackend.Modal.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public ResponseEntity<?> Login(@RequestParam String email, @RequestParam String password) {
        User user = LoginConstants.USER_MAP.get(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
        }
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect credentials provided");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
