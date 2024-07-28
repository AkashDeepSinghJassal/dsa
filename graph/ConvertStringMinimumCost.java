import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.LongStream;

public class ConvertStringMinimumCost {
    public static void main(String[] args) {
        ConvertStringMinimumCost minimumCost = new ConvertStringMinimumCost();
        String source = "abcd";
        String target = "acbe";
        char[] original = { 'a', 'b', 'c', 'c', 'e', 'd' };
        char[] changed = { 'b', 'c', 'b', 'e', 'b', 'e' };
        int[] cost = { 2, 5, 5, 1, 2, 20 };
        long costSum = minimumCost.minimumCost(source, target, original, changed, cost);
        System.out.println("Minimum cost to convert " + costSum);
    }

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        long costSum = 0;
        // build graph of alphabets from oringal to changes with cost values
        int[][] graph = new int[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0;
        }
        for (int i = 0; i < original.length; i++) {
            int o = original[i] - 'a';
            int c = changed[i] - 'a';
            // store shortest cost value between o and c
            graph[o][c] = Math.min(graph[o][c], cost[i]);
        }
        for (int i = 0; i < source.length(); i++) {
            int sourceChar = source.charAt(i) - 'a';
            long[] distChanged = dijkstraPQ(graph, sourceChar);
            long costToChangeChar = distChanged[target.charAt(i) - 'a'];
            if (costToChangeChar == Long.MAX_VALUE)
                return -1;
            costSum += costToChangeChar;
        }
        return costSum;
    }

    // Method to find the vertex with the minimum distance value, from
    // the set of vertices not yet included in the shortest path tree
    public int minDistanceVertex(boolean[] sptSet, long[] dist) {
        int vertU = -1;
        long min = Long.MAX_VALUE;
        for (int i = 0; i < dist.length; i++) {
            if (!sptSet[i] && dist[i] < min) {
                min = dist[i];
                vertU = i;
            }
        }
        return vertU;
    }


    public long[] dijkstraPQ(int[][] graph, int source) {
        // dist array
        long[] dist = LongStream.generate(() -> Long.MAX_VALUE)
                .limit(graph.length)
                .toArray();
        dist[source] = 0L;
        // put source vertice in priority queue
        PriorityQueue<Pair<Integer, Long>> heap = new PriorityQueue<>(
                Comparator.comparingLong((Pair<Integer, Long> p) -> p.value));
        heap.add(new Pair<>(source, 0L));

        while (!heap.isEmpty()) {
            Pair<Integer, Long> vertUPair = heap.poll();
            Integer vertU = vertUPair.key;
            Long weightU = vertUPair.value;
            for (int vertV = 0; vertV < graph[vertU].length; vertV++) {

                if (graph[vertU][vertV] != Integer.MAX_VALUE && // edge present
                        dist[vertV] > graph[vertU][vertV] + weightU) {
                    dist[vertV] = graph[vertU][vertV] + weightU;
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
