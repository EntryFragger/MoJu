package com.me.system.api;

import com.me.common.core.controller.BaseController;
import com.me.common.core.domain.R;
import com.me.common.core.domain.entity.SysDictData;
import com.me.common.utils.StringUtils;
import com.me.system.service.ISysDictDataService;
import com.me.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 *
 * me.system
 */
@RestController
@RequestMapping("/api/dict/data")
public class APIDictDataController extends BaseController
{
    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public R dictType(@PathVariable String dictType)
    {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data))
        {
            data = new ArrayList<SysDictData>();
        }
        return R.success(data);
    }

}
