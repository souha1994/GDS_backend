package com.souha.gds.controller.authentication;

import com.souha.gds.dto.authentication.AuthenticationRequest;
import com.souha.gds.dto.authentication.AuthenticationResponse;
import com.souha.gds.model.authentication.ExtendedUser;
import com.souha.gds.service.authentication.ApplicationUserDetailService;
import com.souha.gds.util.JWTUtil;
import com.souha.gds.util.StaticRoot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StaticRoot.AUTHENTICATION_ENDPOINT)
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private ApplicationUserDetailService userDetailService;

    @PostMapping("/authenticate")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getMotPasse()
                )
        );
        final UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);
        log.info("jwt "+jwt);
        return ResponseEntity.ok(AuthenticationResponse.builder().jwtAccessToken(jwt).build());
    }
}
