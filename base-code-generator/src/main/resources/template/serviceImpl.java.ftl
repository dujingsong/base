package ${package.ServiceImpl};

import cn.imadc.application.base.mybatisplus.repository.impl.BaseMPServiceImpl;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

}
</#if>
