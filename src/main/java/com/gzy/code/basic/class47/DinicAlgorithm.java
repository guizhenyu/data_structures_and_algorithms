package com.gzy.code.basic.class47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * description: DinicAlgorithm date: 2022/5/30 08:02
 *
 * @author: guizhenyu
 */
public class DinicAlgorithm {


  public static class Edge{
    public int from;
    public int to;
    public int available;

    public Edge(int from, int to, int available){
      this.from = from;
      this.to = to;
      this.available = available;
    }

  }

  public static class Dinic {

    public int N;
    public List<List<Integer>> nexts;
    public List<Edge> edges;
    public int[] cur;
    public int[] depth;

    public Dinic(int nums) {

      int N = nums + 1;
      nexts = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        nexts.add(new ArrayList<Integer>());
      }
      edges = new ArrayList<>();
      cur = new int[N];
      depth = new int[N];

    }

    public void addEdge(int form, int to, int available) {
      int index = edges.size();
      edges.add(new Edge(form, to, available));
      nexts.get(form).add(index);
      edges.add(new Edge(to, form, available));
      nexts.get(to).add(index + 1);
    }

    public int maxFlow(int s, int t) {
      int flow = 0;
      while (bfs(s, t)) {
        Arrays.fill(cur, 0);
        flow += dfs(s, t, Integer.MAX_VALUE);
        Arrays.fill(depth, 0);
      }

      return flow;
    }

    // 递归，深度优先遍历
    private int dfs(int s, int t, int r) {
      if (s == t || r == 0) {

        // 这边如果是r == 0的话，就没必要再往下进行了
        return r;
      }

      int ans = 0;
      int f = 0;
      int flow = 0;

      for (; cur[s] < nexts.get(s).size(); cur[s]++) {
        // cur[s] 表示，当前遍历到s点的第几个边
        Integer edgeIndex = nexts.get(s).get(cur[s]);
        Edge edge = edges.get(edgeIndex);
        Edge edge1 = edges.get(edgeIndex ^ 1);
        int to = edge.to;

        if (depth[to] == depth[s] + 1
            && (f = dfs(to, t, Math.min(r, edge.available))) != 0) {
          edge.available -= f;
          edge1.available += f;
          flow += f;
          r -= f;
          if (r <= 0) {
            break;
          }
        }
      }
      return flow;
    }

    private boolean bfs(int s, int end) {
      boolean[] visited = new boolean[N];
      LinkedList<Integer> queue = new LinkedList<>();
      queue.addLast(s);
      visited[s] = true;
      while (!queue.isEmpty()) {
        Integer u = queue.pop();

        for (int i = 0; i < nexts.get(u).size(); i++) {
          Edge edge = edges.get(nexts.get(u).get(i));
          int to = edge.to;
          if (!visited[to] && edge.available > 0) {
            // 遍历该点所有的边，而且是没有访问过的
            // available必须要大于0
            visited[to] = true;
            depth[to] = depth[u] + 1;
            if (to == end) {
              // 这边感觉应该是直接return
              break;
            }
            queue.addFirst(edge.to);
          }
        }
      }
      return visited[end];
    }

  }



}
