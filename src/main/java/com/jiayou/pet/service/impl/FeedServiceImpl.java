package com.jiayou.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiayou.pet.entity.Feed;
import com.jiayou.pet.mapper.FeedMapper;
import com.jiayou.pet.service.FeedService;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl extends ServiceImpl<FeedMapper, Feed> implements FeedService {

}
