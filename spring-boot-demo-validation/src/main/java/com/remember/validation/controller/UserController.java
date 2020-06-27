package com.remember.validation.controller;

import com.remember.validation.entity.UserAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author wangjiahao
 * @date 2020/6/6
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/users")
public class UserController {


	@GetMapping("/get")
	public void get(@RequestParam("id") @Min(value = 1L, message = "编号必须大于 0") Integer id) {
		log.info("[get][id: {}]", id);
	}

	@PostMapping("/add")
	public void add(@Valid UserAddDTO addDTO) {
		log.info("[add][addDTO: {}]", addDTO);
	}
}
