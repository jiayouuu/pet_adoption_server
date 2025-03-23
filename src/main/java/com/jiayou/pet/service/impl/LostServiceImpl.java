package com.jiayou.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiayou.pet.entity.Lost;
import com.jiayou.pet.mapper.LostMapper;
import com.jiayou.pet.service.ILostService;
import org.springframework.stereotype.Service;

@Service
public class LostServiceImpl extends ServiceImpl<LostMapper, Lost> implements ILostService {

}
