import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void addEdge(int[][] graph, int v1, int v2, int weight) {
        graph[v1][v2] = weight;
        graph[v2][v1] = weight;
    }

    public static void printPath(List<Integer> path, String[] names) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static List<Integer> bfs(int[][] graph, int start) {
        int size = graph.length;
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll(); // BLANK 1: ambil elemen terdepan dari queue
            order.add(current);

            for (int i = 0; i < size; i++) {
                if (graph[current][i] != 0 && !visited.contains(i)) { // BLANK 2: cek ketetanggaan & belum dikunjungi
                    visited.add(i); // BLANK 3: tandai visited dan masukkan ke queue
                    queue.add(i);
                }
            }
        }
        return order;
    }

    public static void dfs(int[][] graph, int current, Set<Integer> visited, List<Integer> dfsorder) {
        visited.add(current); // BLANK 4: tandai current sebagai visited dan simpan ke dfsorder
        dfsorder.add(current);

        for (int i = 0; i < graph.length; i++) {
            if (graph[current][i] != 0 && !visited.contains(i)) {
                dfs(graph, i, visited, dfsorder); // BLANK 5: panggil rekursi DFS untuk tetangga i
            }
        }
    }

    public static int findTheNextNode(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < min) { // BLANK 6: cari simpul unvisited dengan jarak terkecil
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void route(int[] prev, int current, String[] names) {
        if (prev[current] != -1) { // BLANK 7: telusuri mundur prev[] secara rekursif
            route(prev, prev[current], names);
            System.out.print(" -> ");
        }
        System.out.print(names[current]);
    }

    public static void dijkstraAlg(int[][] graph, int start, int dest, String[] names) {
        int size = graph.length;
        int[] dist = new int[size];
        boolean[] visited = new boolean[size];
        int[] prev = new int[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int count = 0; count < size; count++) {
            int u = findTheNextNode(dist, visited); // BLANK 8: pilih simpul berikutnya dengan jarak terpendek
            if (u == -1) {
                break;
            }
            visited[u] = true; // BLANK 9: tandai simpul u sebagai sudah dikunjungi

            for (int v = 0; v < size; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) { // BLANK 10: relaksasi jarak tetangga v
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");
        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + ": " + dist[i] + " min");
        }

        System.out.println("=== Shortest Route from " + names[start] + " to " + names[dest] + " ===");
        route(prev, dest, names);
        System.out.println();
        System.out.println("Total time: " + dist[dest] + " min");
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("campus.txt"));

            int size = scanner.nextInt();
            String[] names = new String[size];
            for (int i = 0; i < size; i++) {
                names[i] = scanner.next();
            }

            int[][] graph = new int[size][size];
            int edges = scanner.nextInt();
            for (int i = 0; i < edges; i++) {
                int v1 = scanner.nextInt();
                int v2 = scanner.nextInt();
                int minutes = scanner.nextInt();
                addEdge(graph, v1, v2, minutes);
            }

            int start = scanner.nextInt();
            int dest = scanner.nextInt();
            scanner.close();

            System.out.println("=== BFS from " + names[start] + " ===");
            List<Integer> bfsOrder = bfs(graph, start);
            printPath(bfsOrder, names);

            System.out.println("=== DFS from " + names[start] + " ===");
            List<Integer> dfsorder = new ArrayList<>();
            Set<Integer> visited = new HashSet<>();
            dfs(graph, start, visited, dfsorder);
            printPath(dfsorder, names);

            dijkstraAlg(graph, start, dest, names);

        } catch (FileNotFoundException e) {
            System.out.println("File campus.txt tidak ditemukan.");
        }
    }
}