import java.util.stream.IntStream;

public class NeighbourWithThreshold {
    int globalThreshold = 0;
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {
            {0, 1, 2},
            {0, 4, 8},
            {1, 2, 3},
            {1, 4, 2},
            {2, 3, 1},
            {3, 4, 1}
        };
        int threshold = 2;
        NeighbourWithThreshold neighbourWithThreshold = new NeighbourWithThreshold();
        neighbourWithThreshold.globalThreshold = threshold;
        int smallestCity = neighbourWithThreshold.findTheCity(n, edges, threshold);
        System.out.println("Smallest city (largest number if conflict) " + smallestCity);
    }

    public int[][] buildGraph(int[][] edges, int n){
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }
        return graph;
    }
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int smallestCity = Integer.MAX_VALUE;
        int smallestCityIdx = -1;
        int[][] graph = buildGraph(edges, n); 
        for (int i = 0; i < n; i++) {
            int[] dist = dijkastra(graph, i);
            for (int k = 0; k < dist.length; k++) {
                System.out.println(i + " to " + k + " = " + dist[k]);
            }
            int cityCount = 0;
            for (int j = 0; j < dist.length; j++) {
                if(i != j && dist[j] <= distanceThreshold) {
                    cityCount++;
                }
            }
            System.out.println("cityCount " + cityCount);

            if(cityCount <= smallestCity){
                smallestCity = cityCount;
                smallestCityIdx = i;
            }
        }
        return smallestCityIdx;
    }


    // Method to find the vertex with the minimum distance value, from
    // the set of vertices not yet included in the shortest path tree
    public int minDistanceVertex(boolean[] sptSet, int[] dist) {
        int vertU = -1;
        int min = globalThreshold;
        for (int i = 0; i < dist.length; i++) {
            if (!sptSet[i] && dist[i] <= min) {
                min = dist[i];
                vertU = i;
            }
        }
        return vertU;
    }

    // Method to update the distance of the adjacent vertices of the picked vertex
    public void updateAdjacentMinDist(int[][] graph, boolean[] sptSet, int[] dist, int vertU) {
        for (int vertV = 0; vertV < graph[vertU].length; vertV++) {
            if (!sptSet[vertV] && // vertex already selected
                    graph[vertU][vertV] != 0 && // edge present
                    dist[vertV] > graph[vertU][vertV] + dist[vertU]) {

                // update vertex V distance to current distance of vertex U + distance b/w vertU
                // and vertV
                dist[vertV] = graph[vertU][vertV] + dist[vertU];
            }
        }
    }

    public int[] dijkastra(int[][] graph, int source) {
        // create distance array to store from source
        int[] dist = IntStream.generate(() -> Integer.MAX_VALUE)
                .limit(graph.length)
                .toArray();
        // initialize to max value
        dist[source] = 0;

        // Boolean array to track vertices included in the shortest path tree
        boolean[] sptSet = new boolean[graph.length];

        int vertU;
        do {
            // Pick the minimum distance vertex from the set of vertices not yet processed
            vertU = minDistanceVertex(sptSet, dist);
            if (vertU == -1)
                break;
            // Mark the picked vertex as processed
            sptSet[vertU] = true;
            // Update the distance values of the adjacent vertices of the picked vertex
            updateAdjacentMinDist(graph, sptSet, dist, vertU);
        } while (true);
        return dist;
    }
}