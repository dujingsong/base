package ${package.Service};

import ${package.Entity}.${entity};
import cn.imadc.application.base.mybatisplus.repository.IBaseMPService;
import cn.imadc.application.base.common.response.ResponseW;
import ${package.Parent}.dto.request.${entity}FindReqDTO;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends IBaseMPService<${entity}> {

    /**
     * 查询
     *
     * @param reqDTO 参数
     * @return 结果
     */
    ResponseW find(${entity}FindReqDTO reqDTO);

    /**
     * 添加
     *
     * @param ${entity?uncap_first} 参数
     * @return 结果
     */
    ResponseW add(${entity} ${entity?uncap_first});

    /**
     * 修改
     *
     * @param ${entity?uncap_first} 参数
     * @return 结果
     */
    ResponseW edit(${entity} ${entity?uncap_first});

    /**
     * 删除
     *
     * @param ${entity?uncap_first} 参数
     * @return 结果
     */
    ResponseW delete(${entity} ${entity?uncap_first});
}
</#if>
