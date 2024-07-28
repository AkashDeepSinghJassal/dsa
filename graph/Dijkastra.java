import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Dijkastra {
    public static void main(String[] args) {
        int source = 0;
        int[][] graph = {
                { 0, 3, 0, 0, 0 },
                { 3, 0, 1, 4, 0 },
                { 0, 1, 0, 1, 1 },
                { 0, 4, 1, 0, 2 },
                { 0, 0, 1, 2, 0 }
        };
        Dijkastra dijkastra = new Dijkastra();

        int[] dist = dijkastra.dijkstraPQ(graph, source);
        for (int i = 0; i < dist.length; i++) {
            System.out.println(source + " to " + i + " = " + dist[i]);
        }
        System.out.println("done");
    }

    // Method to find the vertex with the minimum distance value, from
    // the set of vertices not yet included in the shortest path tree
    public int minDistanceVertex(boolean[] sptSet, int[] dist) {
        int vertU = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dist.length; i++) {
            if (!sptSet[i] && dist[i] < min) {
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

    public int[] dijkstraPQ(int[][] graph, int source) {
        // dist array
        int[] dist = IntStream.generate(() -> Integer.MAX_VALUE)
                .limit(graph.length)
                .toArray();
        dist[source] = 0;
        // put source vertice in priority queue
        PriorityQueue<Pair<Integer, Integer>> heap = new PriorityQueue<>((a, b) -> {
            return b.value - a.value;
        });
        heap.add(new Pair<>(source, 0));

        while (!heap.isEmpty()) {
            Pair<Integer, Integer> vertUPair = heap.poll();
            Integer vertU = vertUPair.key;
            Integer weightU = vertUPair.value;
            for (int vertV = 0; vertV < graph[vertU].length; vertV++) {

                if (graph[vertU][vertV] != 0 && // edge present
                        dist[vertV] > graph[vertU][vertV] + weightU) {
                            dist[vertV] = graph[vertU][vertV] + weightU  ;
                            heap.add(new Pair<>(vertV, dist[vertV]));

                }
            }
        }
        return dist;
    }

    static class Pair<K, V> {
        public K key;
        public V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
