package com.jiayou.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiayou.pet.entity.Applcation;
import com.jiayou.pet.mapper.ApplcationMapper;
import com.jiayou.pet.service.IApplcationService;
import org.springframework.stereotype.Service;

@Service
public class ApplcationServiceImpl extends ServiceImpl<ApplcationMapper, Applcation> implements IApplcationService {

}
