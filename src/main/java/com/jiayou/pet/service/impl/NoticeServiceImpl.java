package com.jiayou.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiayou.pet.entity.Notice;
import com.jiayou.pet.mapper.NoticeMapper;
import com.jiayou.pet.service.NoticeService;
import com.jiayou.pet.utils.WebSocketUtils;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Resource
    private WebSocketUtils webSocketUtils;

    @Override
    public List<Notice> limit(int i) {
        return noticeMapper.limit(5);
    }

    @Override
    public void push(String id) {
        List<String> ids = Arrays.asList(id.split(","));
        List<Notice> notices = noticeMapper.selectBatchIds(ids);
        for (Notice notice : notices) {
            String noticeJson = JSONUtil.toJsonStr(notice);
            webSocketUtils.sendBroadcast("/notice", noticeJson);
        }
    }
}
