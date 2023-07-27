package com.remember.dynamic.datasource.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remember.dynamic.datasource.mybatisplus.entity.TMenu;
import com.remember.dynamic.datasource.mybatisplus.mapper.TMenuMapper;
import com.remember.dynamic.datasource.mybatisplus.service.TMenuService;
import org.springframework.stereotype.Service;
@Service
@DS("sqlite")
public class TMenuServiceImpl extends ServiceImpl<TMenuMapper, TMenu> implements TMenuService{

}
