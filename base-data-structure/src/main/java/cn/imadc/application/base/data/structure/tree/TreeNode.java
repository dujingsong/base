package cn.imadc.application.base.data.structure.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 树形节点
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-21
 */
@Getter
@Setter
public class TreeNode {

    /**
     * 节点id
     */
    private Long id;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 节点图标
     */
    private String icon;
    /**
     * 子节点
     */
    private List<TreeNode> children;
}
