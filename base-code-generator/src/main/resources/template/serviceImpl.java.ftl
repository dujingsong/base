package ${package.ServiceImpl};

import cn.imadc.application.base.mybatisplus.repository.impl.BaseMPServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import cn.imadc.application.base.common.response.ResponseW;
import com.wxsbank.base.iamp.core.data.Constant;
import ${package.Parent}.dto.request.${entity}FindReqDTO;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@AllArgsConstructor
@Service
<#if kotlin>
open class ${table.serviceImplName} : BaseMPServiceImpl<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends BaseMPServiceImpl<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public ResponseW find(${entity}FindReqDTO reqDTO) {
        QueryWrapper<${entity}> queryWrapper = buildQueryWrapper(reqDTO);

        if (!reqDTO.pageQuery()) return ResponseW.success(list(queryWrapper));

        Page<${entity}> page = new Page<>(reqDTO.getPageNo(), reqDTO.getPageSize(), true);
        IPage<${entity}> pageData = page(page, queryWrapper);
        return ResponseW.success(pageData);
    }

    private QueryWrapper<${entity}> buildQueryWrapper(${entity}FindReqDTO reqDTO) {
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.DEL_FLAG, Constant.NOT_DEL_VAL);

        return queryWrapper;
    }

    @Override
    public ResponseW add(${entity} ${entity?uncap_first}) {
        return save(${entity?uncap_first}) ? ResponseW.success() : ResponseW.error();
    }

    @Override
    public ResponseW edit(${entity} ${entity?uncap_first}) {
        return updateById(${entity?uncap_first}) ? ResponseW.success() : ResponseW.error();
    }

    @Override
    public ResponseW delete(${entity} ${entity?uncap_first}) {
        UpdateWrapper<${entity}> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", ${entity?uncap_first}.getId());
        userUpdateWrapper.set(Constant.DEL_FLAG, Constant.DEL_VAL);
        return update(userUpdateWrapper) ? ResponseW.success() : ResponseW.error();
    }
}
</#if>
