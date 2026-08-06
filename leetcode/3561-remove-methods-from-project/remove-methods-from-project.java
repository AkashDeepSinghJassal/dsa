class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // given invocations convert to graph 

        // build graph 

        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inOrder = new int[n];
        for(int i = 0; i < invocations.length; i++) {
            graph.get(invocations[i][0]).add(invocations[i][1]);
            inOrder[invocations[i][1]]++;
        }
        boolean[] sus = new boolean[n];
        // find sus
        check(k, graph, sus, inOrder);
        boolean isSus = true;
        for(int i = 0; i < n; i++) {
            if(sus[i] && inOrder[i] > 0) {
                isSus = false;
                break;
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(!isSus || !sus[i]) {
                res.add(i);
            }
        }

        return res;
    }

    public void check (int val, List<List<Integer>> graph, boolean[] vis, int[] inOrder) {
        Queue<Integer> queue = new LinkedList<>();
        vis[val] = true;
        queue.add(val);
        while(!queue.isEmpty()) {
            int curr = queue.remove();

            for(Integer child: graph.get(curr)) {
                inOrder[child]--;
                if(!vis[child]) {
                    queue.add(child);
                    vis[child] = true;
                    
                }
            }
        }
    }

}