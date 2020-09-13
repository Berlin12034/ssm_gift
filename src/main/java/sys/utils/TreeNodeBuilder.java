package sys.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {


    /*把简单的集合转成有层级关系的集合*/
    public static List<TreeNode> builder(List<TreeNode> nodes, Integer topPid) {
        List<TreeNode> treeNodes = new ArrayList<>();
        //把子节点加入父节点的children中
        for (TreeNode n1 : nodes) {
            if (n1.getPid() == 1) {
                treeNodes.add(n1);
            }
            for (TreeNode n2 : nodes) {
                if (n2.getPid() == n1.getId()) {
                    n1.getChildren().add(n2);
                }
            }
        }
        return treeNodes;
    }

    }

