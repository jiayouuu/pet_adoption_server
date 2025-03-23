package com.jiayou.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiayou.pet.entity.Animal;
import com.jiayou.pet.mapper.AnimalMapper;
import com.jiayou.pet.service.IAnimalService;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal> implements IAnimalService {

}
