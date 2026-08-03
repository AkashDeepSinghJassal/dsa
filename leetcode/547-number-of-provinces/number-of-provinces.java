class Solution {
    public int findCircleNum(int[][] graph) {
        Queue<Integer> queue = new LinkedList<>();
        int n = graph.length;
        int[] visited = new int[n];
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(visited[i] == 1)
                continue;
            queue.add(i);
            while(!queue.isEmpty()) {
                int curr = queue.remove();
                visited[curr] = 1;
                for(int j = 0; j < n; j++) {
                    if(graph[curr][j] == 1 && visited[j] == 0) {
                        queue.add(j);
                    }
                }

            }
            count++;
        }
        return count;
    }
}