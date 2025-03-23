package com.jiayou.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiayou.pet.entity.Activity;
import com.jiayou.pet.mapper.ActivityMapper;
import com.jiayou.pet.service.IActivityService;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

}
