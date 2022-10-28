# 基础数结构模块

## 基础数结构模块，包含了一些常用的数据结构

### usage

引入依赖

~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-data-structure</artifactId>
</dependency>
~~~

#### 解析redis info命令

见 cn.imadc.application.base.data.structure.redis.RedisParser

#### rocketmq节点类型

见 cn.imadc.application.base.data.structure.rocketmq.RocketMQNode

#### 树形结构

见 cn.imadc.application.base.data.structure.tree.Tree
~~~
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
~~~