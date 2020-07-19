import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList; // 存储顶点集合
    private int[][] adjacencyMatrix; // 存储图对应的邻接矩阵
    private int numOfEdges; // 表示边的数目
    private boolean[] isVisted; // 由于遍历判断是否已经访问过

    public static void main(String[] args) {
        // 测试
        int n = 5;
        Graph graph = new Graph(5);
        String[] vertexValue = {"A", "B", "C", "D", "E"};
        // 将顶点放到链表中
        for (String ele : vertexValue) {
            graph.insertVertex(ele);
        }
        // 添加边
        graph.insertEdges(0, 1, 1); // A-B
        graph.insertEdges(0, 2, 1); // A-C
        graph.insertEdges(1, 2, 1); // B-C
        graph.insertEdges(1, 3, 1); // B-D
        graph.insertEdges(1, 4, 1); // B-E

        graph.showGraph();

        System.out.println("深度优先遍历：");
        graph.dfs();
        System.out.println("\n广度优先遍历：");
        graph.bfs();
    }


    // 构造器
    public Graph(int n) { // n为顶点数
        // 初始化矩阵和vertexList
        adjacencyMatrix = new int[n][n];
        vertexList = new ArrayList<>(n);
    }

    // 插入顶点
    public void insertVertex(String vertexValue) {
        vertexList.add(vertexValue);
    }

    // 添加边(在邻接矩阵中修改值，weight为0说明没有连接，weight为1说明接通)
    public void insertEdges(int v1, int v2, int weight) {
        adjacencyMatrix[v1][v2] = weight;
        adjacencyMatrix[v2][v1] = weight;
        numOfEdges++;
    }

    // 返回节点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 返回边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回节点i(数组下标)对应的数据 0->A  1->B
    public String getIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return adjacencyMatrix[v1][v2];
    }

    // 显示图的邻接数组
    public void showGraph() {
        for (int[] ele : adjacencyMatrix) {
            System.out.println(Arrays.toString(ele));
        }
    }

    // 深度优先算法
// 得到初始节点v的第一个邻接节点的下标w
    public int getFirstNeighbour(int index) {
        for (int j = index; j < vertexList.size(); j++) {
            if (adjacencyMatrix[index][j] > 0) {
                return j;
            }
        }
        return -1; // 如果存在就返回对应的下标，否则返回-1
    }

    // 查找节点v的下一邻接点w的下一邻接点（对应算法思路步骤5，应对回溯，避免再次找到已经访问过的邻接点）
    public int getNextNeighbour(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (adjacencyMatrix[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 深度优先算法
    private void dfs(boolean[] isVisted, int i) {
        System.out.print(getIndex(i) + " "); // 输出遍历
        // 将该节点设置为已经访问
        isVisted[i] = true;
        // 查找节点i的下一个邻接节点w
        int w = getFirstNeighbour(i);
        while (w != -1) { // 当i存在下一个邻接点w时，不断递归即可
            if (!isVisted[w]) { // 未访问点才递归 避免回溯时 重复递归
                dfs(isVisted, w);
            }
            w = getNextNeighbour(i, w);
        }
    }

    // 对dfs重载，遍历所有的节点
    public void dfs() {
        isVisted = new boolean[vertexList.size()]; // 将标识符先全部重置
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisted[i]) {
                dfs(isVisted, i);
            }
        }
    }


    // 广度优先算法
    private void bfs(boolean[] isVisted, int i) {
        int u; // 表示队列头节点对应的下标
        int w; // 邻接节点
        LinkedList queue = new LinkedList(); // 队列，记录节点访问的顺序

        System.out.print(getIndex(i) + " ");
        isVisted[i] = true;
        queue.addLast(i); // addLast直接在最后插入，add需要指明插入的位置

        while (!queue.isEmpty()) {
            // 取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            // 得到第一个邻接节点的下标w
            w = getFirstNeighbour(u);
            while (w != -1) { // 找到
                if (!isVisted[w]) { //没有访问过
                    System.out.print(getIndex(w) + " ");
                    isVisted[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbour(u, w); // 如果访问过，找下一个邻接点
            }
        }
    }

    // 遍历所有的点，都进行广度优先搜索
    public void bfs() {
        isVisted = new boolean[vertexList.size()]; // 将标识符先全部重置
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisted[i]) {
                bfs(isVisted, i);
            }
        }
    }
}
