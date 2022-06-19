package Difficult.树中距离之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistanceOfTree {

    List<List<Integer>>graph = new ArrayList<>();
    int[] distNum;
    int[] nodeNum;
    int N;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        N = n;
        for (int i=0; i<N; i++){
            graph.add(new ArrayList<>());
        }
        // 对每个点相邻的点都放进了对应的集合里面
        // edges里面存放的是边的信息，终点和起点
        for (int[] edge : edges) {
            int src = edge[0];
            int dst = edge[1];
            graph.get(src).add(dst);
            graph.get(dst).add(src);
        }
        // 这个记录的都是节点的信息，长度当然也得是节点的个数
        distNum = new int[N];
        nodeNum = new int[N];
        Arrays.fill(nodeNum, 1);  // 所有节点自身即为一个
        getNodes(0,-1);
        getDist(0,-1);
        return distNum;
    }

    // 生成当前节点所在子树的总的节点个数
    public void getNodes(int root, int parent){
        for (Integer neighbor: graph.get(root)){
            if (neighbor==parent){  // 如果等于父节点，跳过
                continue;
            }
            getNodes(neighbor, root);
            nodeNum[root] += nodeNum[neighbor];  // 把所有子树的节点个数加起来，就是这个子树的节点总数（在初始化数组的时候都为1，所以根节点是计算了的）
            distNum[root] += nodeNum[neighbor] + distNum[neighbor];  // 等于孩子的路径和加上自己少走的nodeNum[neighbor]长度
        }
    }

    // 生成真正的路径和：
    // 只有根节点的路径和是正确的，因为下面的都是他的孩子节点。
    // 利用根节点往下递推，任何节点的路经总和都等于他这个子树的路径和和子树外的节点到他的距离。
    // 也可以理解为：子树外的节点到他的根节点的距离加上字数外的节点个数，这时候就可以利用已知的根节点信息。
    public void getDist(int root, int parent){
        for (int neighbor: graph.get(root)){
            if (neighbor==parent){  // 如果是父亲节点，跳过
                continue;
            }
            /*
                对于当前节点，他的路径和等于他往下的路径和和他往上的路径和。
                往下的路径和是 distNum[child]+nodeNum[child]。即他的孩子节点的路径和加上他少走的节点个数的步数。
                往上的路径和是 distNum[parent]+(N-nodeNum[child])。即他双亲往上的路径和，加上少走的N-nodeNum[child]节点数的步数。
                综上：distNum[child] = distNum[parent] - nodeNum[child] + (N - nodeNum[child])
             */
            distNum[neighbor] = distNum[root] - nodeNum[neighbor] + (N - nodeNum[neighbor]);
            getDist(neighbor, root);
        }
    }

}
