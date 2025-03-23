package com.jiayou.pet.controller;

import com.jiayou.pet.service.ValidateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jiayou.pet.common.R;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private final ValidateService validateService;

    public PublicController(ValidateService validateService) {
        this.validateService = validateService;
    }

    @GetMapping("/email/code")
    public R sendCode(@RequestParam String email) {
        try {
            return validateService.sendEmailCode(email);
        } catch (Exception e) {
            return R.error(500, e.getMessage());
        }
    }

    @GetMapping("/imgCode")
    public R sendImgCode() {
        try {
            return validateService.sendImgCode();
        } catch (Exception e) {
            return R.error(500, e.getMessage());
        }
    }

    @PostMapping("/validateImgCode")
    public R validateImgCode(@RequestBody HashMap<String, Object> map) {
        try {
            String id = (String) map.get("id");
            String code = (String) map.get("code");
            return validateService.validateImgCode(id, code);
        } catch (Exception e) {
            return R.error(500, e.getMessage());
        }
    }

    @PostMapping("/validateEmailCode")
    public R validateEmailCode(@RequestBody HashMap<String, Object> map) {
        try {
            String email = (String) map.get("email");
            String code = (String) map.get("code");
            return validateService.validateEmailCode(email, code);
        } catch (Exception e) {
            return R.error(500, e.getMessage());
        }
    }
}