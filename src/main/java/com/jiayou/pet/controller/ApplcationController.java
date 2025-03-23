package com.jiayou.pet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiayou.pet.common.R;
import com.jiayou.pet.entity.Animal;
import com.jiayou.pet.entity.Applcation;
import com.jiayou.pet.entity.User;
import com.jiayou.pet.service.IAnimalService;
import com.jiayou.pet.service.IApplcationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 领养 控制层
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Tag(name = "领养管理", description = "领养相关接口")
@RestController
@RequestMapping("/applcation")
public class ApplcationController {

    @Resource
    private IApplcationService applcationService;

    @Resource
    private IAnimalService animalService;

    @Operation(summary = "保存领养申请")
    @PostMapping
    public R save(@RequestBody Applcation applcation) {
        applcationService.saveOrUpdate(applcation);
        return R.success();
    }

    @Operation(summary = "更新领养状态")
    @PostMapping("/state/{id}/{state}")
    public R state(@PathVariable Integer id, @PathVariable String state) {
        Applcation applcation = applcationService.getById(id);
        applcation.setState(state);

        QueryWrapper<Applcation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("animal_id", applcation.getAnimalId());
        List<Applcation> list = applcationService.list(queryWrapper);
        for (Applcation app : list) {
            app.setState("审核不通过");
            applcationService.updateById(app);
        }

        applcationService.updateById(applcation);

        Animal animal = animalService.getById(applcation.getAnimalId());
        animal.setIsAdopt("是");
        animal.setAdopt("不可领养");
        animalService.updateById(animal);
        return R.success();
    }

    @Operation(summary = "删除领养申请")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        applcationService.removeById(id);
        return R.success();
    }

    @Operation(summary = "批量删除领养申请")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        applcationService.removeByIds(ids);
        return R.success();
    }

    @Operation(summary = "查询所有领养申请")
    @GetMapping
    public R findAll() {
        return R.success(applcationService.list());
    }

    @Operation(summary = "根据ID查询领养申请")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(applcationService.getById(id));
    }

    @Operation(summary = "查询我的领养申请")
    @GetMapping("/my")
    public R my() {
        List<Animal> animals = animalService.list();
        QueryWrapper<Applcation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        // todo
        // User currentUser = TokenUtils.getCurrentUser();
        User currentUser = new User(); //todo
        if (currentUser == null) {
            return R.success(new ArrayList<>());
        }
        queryWrapper.eq("user_id", currentUser.getId());
        List<Applcation> applcations = applcationService.list(queryWrapper);
        for (Applcation record : applcations) {
            animals.stream().filter(animal -> animal.getId().equals(record.getAnimalId())).findFirst()
                    .ifPresent(record::setAnimal);
        }
        return R.success(applcations);
    }

    @Operation(summary = "分页查询领养申请")
    @GetMapping("/page")
    public R findPage(@RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        List<Animal> animals = animalService.list();
        QueryWrapper<Applcation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        Page<Applcation> page = applcationService.page(new Page<>(pageNum, pageSize), queryWrapper);
        for (Applcation record : page.getRecords()) {
            animals.stream().filter(animal -> animal.getId().equals(record.getAnimalId())).findFirst()
                    .ifPresent(record::setAnimal);
        }
        return R.success(page);
    }
}
