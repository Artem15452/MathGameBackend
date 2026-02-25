package com.math.game.Controller;
import com.math.game.DTO.UserDTO;
import com.math.game.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return ResponseEntity.ok("User created");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserDTO userDTO) {
        if(userService.findUser(userDTO.getEmail()) != null){
            return ResponseEntity.ok("User logged in");
        }else {
            return ResponseEntity.badRequest().body("User not found");
        }

    }

}