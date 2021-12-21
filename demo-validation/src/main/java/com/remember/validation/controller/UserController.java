package com.remember.validation.controller;

import com.remember.validation.entity.UserAddDTO;
import com.remember.validation.entity.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangjiahao
 * @date 2020/6/6
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("blank")
    public void checkBlanks(@RequestParam("name") @NotBlank(message = "{user.name}") String name) {
        log.info("name = {}", name);
    }

    @GetMapping("notNull")
    public void notNull(@NotNull String id) {
        log.info("id = {}", id);
    }

    @GetMapping("/min")
    public void min(@RequestParam("id") @Min(value = 1L, message = "编号必须大于 0") Integer id) {
        log.info("[get][id: {}]", id);
    }

    @PostMapping("/valid")
    public void valid(@Valid UserAddDTO addDTO) {
        log.info("[add][addDTO: {}]", addDTO);
    }

    @PostMapping
    public void test2(@Valid UserVO user) {
        log.info("user = {}", user);
    }

    @PostMapping("getArray")
    public void getArray(List<String> list) {
        log.info("list = {}", list);
    }

    @PostMapping("getArray1")
    public void getArray(String ids) {
        List<String> split = Arrays.asList(ids.split(","));
        log.info("list = {}", split);
    }
}
