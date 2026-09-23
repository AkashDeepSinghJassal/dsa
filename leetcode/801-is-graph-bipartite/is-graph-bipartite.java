class Solution {

    /**
        Implement using color problem 
        BFS solution
        Traverse using queue and mark a color 
        take an array for color value
    
        At every node
        check if adjacent node was already visited and has opposite color
        if not visited color it
    
        if visited and same color then return false
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int[] color = new int[n];
        for (int k = 0; k < n; k++) {
            if (visited[k])
                continue;
            queue.add(k);
            color[k] = 0;
            while (!queue.isEmpty()) {
                int val = queue.remove();

                for (int i = 0; i < graph[val].length; i++) {
                    int adj = graph[val][i];
                    if(visited[adj]) {
                        // check if opposite color
                        if(color[adj] == color[val])
                            return false;
                        continue;
                    }
                    visited[adj] = true;
                    color[adj] = (color[val] + 1) % 2;
                    queue.add(adj);
                }

            }
        }
        return true;

    }
}