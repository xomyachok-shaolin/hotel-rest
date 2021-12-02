package com.mephi.hotel.controller;

import com.mephi.hotel.email.EmailService;
import com.mephi.hotel.model.Role;
import com.mephi.hotel.model.User;
import com.mephi.hotel.postRequestResponse.AuthenticationRequest;
import com.mephi.hotel.postRequestResponse.AuthenticationResponse;
import com.mephi.hotel.postRequestResponse.UserRegistration;
import com.mephi.hotel.postRequestResponse.response.MessageResponse;
import com.mephi.hotel.postRequestResponse.response.UserInfo;
import com.mephi.hotel.security.MyUserDetails;
import com.mephi.hotel.security.UserAuthService;
import com.mephi.hotel.service.AdminService;
import com.mephi.hotel.service.UserService;
import com.mephi.hotel.JWTutil.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


//@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Transactional
@RestController
//@CrossOrigin(origins = "http://localhost:4000")
public class UserRegAuthController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AdminService adminService;

    private AuthenticationManager authenticate;
    private JwtUtil jwtTokenUtil;
    private UserAuthService userDetailsService;
    private EmailService emailService;

    @Autowired
    public UserRegAuthController(UserService userService, BCryptPasswordEncoder passwordEncoder, AdminService adminService, AuthenticationManager authenticate, JwtUtil jwtTokenUtil, UserAuthService userDetailsService, EmailService emailService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.adminService = adminService;
        this.authenticate = authenticate;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.emailService = emailService;
    }

    private boolean validateEmail(String email) {
        boolean isValid = false;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid = true;
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    @RequestMapping(value = "/registration/createUser", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserRegistration data) {
        try {
            Long cnt = userService.countUserByLogin(data.getLogin());
            if (cnt > 0) {
                return ResponseEntity.badRequest().body(new MessageResponse("Логин уже используется"));
            }

            cnt = userService.countUserByEmail(data.getEmail());
            if (cnt > 0) {
                return ResponseEntity.badRequest().body(new MessageResponse("Email уже используется"));
            }

            if(!validateEmail(data.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Не верный Email"));
            }

            User user = new User();
            Role role = adminService.getUserRoleByName("USER");
            user.setLogin(data.getLogin());
            user.setLast_name(data.getLast_name());
            user.setFirst_name(data.getFirst_name());
            user.setAddress(data.getAddress());
            user.setPhone_number(data.getPhone_number());
            user.setEmail(data.getEmail());
            user.setPassword(passwordEncoder.encode(data.getPassword()));
            user.setRole(role);
            userService.createUser(user);
            emailService.sendSimpleMessage(user.getEmail(), "Регистрация в гостинице", "Регистрация успешно пройдена!");
            return ResponseEntity.ok().body(new MessageResponse("Регистрация прошла успешна"));
        } catch (Exception ex) {
            String strEx = ex.getCause().getCause().getMessage();
            System.out.println("Ошибка! Подробнее: " + strEx);
            return ResponseEntity.badRequest().body(new MessageResponse("Во время регистрирования данных произошла ошибка!"));
        }
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticate.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
//            throw new Exception("Неверное имя пользователя или пароль", e);
            return ResponseEntity.badRequest().body(new MessageResponse("Неверный логин или пароль!"));
        }
        final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getLogin());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        AuthenticationResponse response = new AuthenticationResponse(userDetails.getIdUser(),
                userDetails.getFirst_name(),
                userDetails.getLast_name(),
                userDetails.getPhone_number(),
                userDetails.getAddress(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                jwt);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/user/changeBaseUserData")
    public ResponseEntity<?> changeBaseUserData(@RequestBody UserInfo userInfo) {
        try {
            User user = userService.findUserById(userInfo.getIdUser());

            if (userInfo.getPassword() != null) {
                user.setPassword(userInfo.getPassword());
                return ResponseEntity.ok().body(new MessageResponse("Ваш пароль успешно изменен"));
            }

            user.setLast_name(userInfo.getLast_name());
            user.setFirst_name(userInfo.getFirst_name());
            user.setAddress(userInfo.getAddress());
            user.setPhone_number(userInfo.getPhone_number());
            userService.saveUser(user);
            return ResponseEntity.ok().body(new MessageResponse("Ваши данные успешно изменены"));
        } catch (Exception ex) {
            String strEx = ex.getCause().getCause().getMessage();
            System.out.println("Ошибка! Подробнее: " + strEx);
            return ResponseEntity.badRequest().body(new MessageResponse("Во время редактирования данных произошла ошибка!"));
        }
    }
}
