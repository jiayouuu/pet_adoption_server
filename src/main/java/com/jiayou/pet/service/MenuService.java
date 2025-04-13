package com.jiayou.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiayou.pet.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
