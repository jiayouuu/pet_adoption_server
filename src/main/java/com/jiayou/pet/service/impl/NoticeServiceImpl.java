package com.jiayou.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiayou.pet.entity.Notice;
import com.jiayou.pet.mapper.NoticeMapper;
import com.jiayou.pet.service.INoticeService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> limit(int i) {
        return noticeMapper.limit(5);
    }
}
