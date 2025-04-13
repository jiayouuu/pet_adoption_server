package com.jiayou.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiayou.pet.entity.Notice;
import java.util.List;

public interface NoticeService extends IService<Notice> {

    List<Notice> limit(int i);

    void push(String id);
}
