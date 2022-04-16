package com.remember.dynamic.datasource.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remember.dynamic.datasource.mapper.LogAccessMapper;
import com.remember.dynamic.datasource.entity.LogAccess;
import com.remember.dynamic.datasource.service.LogAccessService;
@Service
public class LogAccessServiceImpl extends ServiceImpl<LogAccessMapper, LogAccess> implements LogAccessService{

}
