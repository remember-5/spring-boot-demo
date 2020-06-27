package com.remember.jpa.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wangjiahao
 * @date 2020/6/27
 */
@Data
@Entity
@Table(name = "t_del")
public class Del implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "kid")
	private String kid;
}
