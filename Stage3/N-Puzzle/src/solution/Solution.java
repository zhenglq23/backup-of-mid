package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        // initialize
        exploreList.offer(bNode); 
        searchedNodesNum = 0;
        // search
        while(!exploreList.isEmpty()){
            currentJNode = exploreList.poll(); // get the first element in queue
            // find the solution
            if(currentJNode.equals(eNode)){
                // construct the solutionPath
                solutionPath = getPath();
                return true;
            }
            else{
                // update search state
                visitedList.add(currentJNode);
                searchedNodesNum+=1;
                // update exploreList
                for(int i=0; i<4; i++){
                    if(nextNodes[i]){
                        JigsawNode tem = new JigsawNode(currentJNode);
                        tem.setNodeDepth(tem.getNodeDepth()+1);
                        tem.setParent(currentJNode);
                        if(tem.move(i) && !visitedList.contains(tem) && !exploreList.contains(tem))
                            exploreList.add(tem);
                    }
                }
            }
        }
        return false;
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int s = 0; // 后续节点不正确的数码个数
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }
        jNode.setEstimatedValue(s);
    }
}
