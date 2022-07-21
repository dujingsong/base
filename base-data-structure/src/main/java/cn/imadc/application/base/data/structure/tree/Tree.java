package cn.imadc.application.base.data.structure.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 树形结构
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-21
 */
@Getter
@Setter
@AllArgsConstructor
public class Tree {
    private List<TreeNode> treeNodeList;

    // 建立树形结构
    public List<TreeNode> buildTree(Long parentId) {
        List<TreeNode> treeMenus = new ArrayList<>();
        for (TreeNode menuNode : getRootNode(parentId)) {
            buildChildTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    // 递归，建立子树形结构
    private TreeNode buildChildTree(TreeNode pNode) {
        List<TreeNode> childMenus = new ArrayList<>();
        for (TreeNode menuNode : treeNodeList) {
            if (menuNode.getParentId().equals(pNode.getId())) {
                childMenus.add(buildChildTree(menuNode));
            }
        }
        pNode.setChildren(childMenus);
        return pNode;
    }

    // 获取根节点
    private List<TreeNode> getRootNode(Long parentId) {
        List<TreeNode> rootMenuLists = new ArrayList<>();
        for (TreeNode menuNode : treeNodeList) {
            if (menuNode.getParentId().equals(parentId)) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }

    public static void main(String[] args) {
        List<TreeNode> treeNodes = new ArrayList<>();
        TreeNode treeNode;

        treeNode = new TreeNode();
        treeNode.setId(1L);
        treeNode.setParentId(0L);
        treeNode.setName("系统管理");
        treeNodes.add(treeNode);

        treeNode = new TreeNode();
        treeNode.setId(2L);
        treeNode.setParentId(1L);
        treeNode.setName("用户管理");
        treeNodes.add(treeNode);

        treeNode = new TreeNode();
        treeNode.setId(4L);
        treeNode.setParentId(1L);
        treeNode.setName("功能管理");
        treeNodes.add(treeNode);

        treeNode = new TreeNode();
        treeNode.setId(6L);
        treeNode.setParentId(5L);
        treeNode.setName("一级菜单1");
        treeNodes.add(treeNode);

        treeNode = new TreeNode();
        treeNode.setId(5L);
        treeNode.setParentId(0L);
        treeNode.setName("一级菜单");
        treeNodes.add(treeNode);

        treeNode = new TreeNode();
        treeNode.setId(3L);
        treeNode.setParentId(1L);
        treeNode.setName("角色管理");
        treeNodes.add(treeNode);


        Tree tree = new Tree(treeNodes);
        List<TreeNode> treeNodes1 = tree.buildTree(0L);

        System.out.println(treeNodes1);
    }
}
