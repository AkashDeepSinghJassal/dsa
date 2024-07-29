/**
 * Find all possible distance between two points and store minimum of them
 * 
 * 1 -> 2 = 1, 2 -> 3 = 1, 1 -> 3 = 5
 * Find path 1 -> 3 via 1, 2, 3, 2 -> 3 via 1, 2, 3, 2 -> 1 via 1, 2, 3 etc
 * Time complexity (V ^ 3)
 */
public class FloydWarshall {
    public static void main(String[] args) {
        int source = 0;
        int[][] graph = {
                { 0, 3, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
                { 3, 0, 1, 4, Integer.MAX_VALUE },
                { Integer.MAX_VALUE, 1, 0, 1, 1 },
                { Integer.MAX_VALUE, 4, 1, 0, 2 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 2, 0 }
        };
        FloydWarshall floydWarshall = new FloydWarshall();
        long[][] distArr = floydWarshall.floydWarshall(graph);
        for (int i = 0; i < distArr.length; i++) {
            for (int j = 0; j < distArr[i].length; j++) {
                System.out.println(i + " to " + j + " = " + distArr[i][j]);
            }
        }
    }

    public long[][] floydWarshall(int[][] graph) {
        // find all path via vertices
        int n = graph.length;
        long[][] distArr = new long[n][n];
        // copy arr
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < distArr[i].length; j++) {
                distArr[i][j] = graph[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            int viaVertex = i;
            for (int vertU = 0; vertU < n; vertU++) {
                for (int vertV = 0; vertV < n; vertV++) {
                    if (distArr[vertU][viaVertex] != Integer.MAX_VALUE &&
                            distArr[viaVertex][vertV] != Integer.MAX_VALUE &&
                            distArr[vertU][vertV] > distArr[vertU][viaVertex] + distArr[viaVertex][vertV])
                        distArr[vertU][vertV] = distArr[vertU][viaVertex] + distArr[viaVertex][vertV];
                }
            }
        }
        return distArr;
    }
}
