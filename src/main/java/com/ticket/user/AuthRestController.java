package com.ticket.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthRestController {

    // GET /api/auth/me
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> me(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.ok(Collections.emptyMap());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("userId", userId);
        result.put("userName", session.getAttribute("userName"));
        result.put("recentShowImageList", Collections.emptyList());
        return ResponseEntity.ok(result);
    }
}
