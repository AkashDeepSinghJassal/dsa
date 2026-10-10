class Solution {


    public boolean dfs(int node, List<List<Integer>> graph, int[] vis, int[] pathVis) {

        if(pathVis[node] == 1) {
            return true;
        }
        if(vis[node] == 1) {
            return false;
        }

        vis[node] = 1;
        pathVis[node] = 1;

        for(Integer num : graph.get(node)) {

            boolean res = dfs(num, graph, vis, pathVis);
            if(res)
                return res;
        }
        pathVis[node] = 0;
        return false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        // detect cycle in directed graph
        // take visited and path visited array
        // use dfs to travese and apply back tracing to update path visited array

        // first build graph

        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }
        for(int i = 0; i < prerequisites.length; i++) {
            // add edge

            graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        int[] vis = new int[n];
        int[] pathVis = new int[n];
        for(int i = 0; i < n; i++) {
            if(vis[i] == 0) {
                boolean result = dfs(i, graph, vis, pathVis);
                if(result) {
                    return false;
                }
            }
        }
        return true;
    }
}