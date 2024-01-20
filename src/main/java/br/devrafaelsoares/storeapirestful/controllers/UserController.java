package br.devrafaelsoares.storeapirestful.controllers;

import br.devrafaelsoares.storeapirestful.domain.user.dto.UserResponse;
import br.devrafaelsoares.storeapirestful.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public ResponseEntity<List<UserResponse>> index() {

        List<UserResponse> userResponseList = userService
                .findAll()
                .stream()
                .map(UserResponse::new)
                .toList();

        return ResponseEntity.ok(userResponseList);

    }

}
