package com.remember.validation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wangjiahao
 * @date 2020/4/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -6130414118159979433L;

    @NotNull
    private Integer id;


    @NotNull(message = "{required}")
    @Size(min = 2, max = 10, message = "{range}")
    private String name;

    @Positive
    @NotNull(message = "{required}")
    private Integer age;

    @Email
    @NotNull(message = "{required}")
    private String email;

    @NotNull(message = "{required}")
    private String address;

    @AssertTrue
    @NotNull(message = "{required}")
    private Boolean flag;

    @Past
    @NotNull(message = "{required}")
    private Date createDate;


}
