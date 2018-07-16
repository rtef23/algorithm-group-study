package day1;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Question2 {

    /**
     * Question2)
     *
     * 안녕하세요, 매일프로그래밍 이번주 문제입니다.
     *
     * 0과 1로 만들어진 2D 정수 배열이 있습니다.
     * 0은 장애물이고 1은 도로일때, 두 좌표가 주어지면, 첫번째 좌표에서 두번째 좌표까지 가장 가까운 거리를 구하시오.
     * 두 좌표는 모두 도로에서 시작되고 좌, 우, 아래, 위로 움직이며 대각선으로는 움직일 수 없습니다.
     * 만약 갈 수 없다면 -1을 리턴하시오.
     *
     */

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 0, 0, 1, 1, 0},
            {1, 0, 0, 1, 0, 0},
            {1, 1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1}
        };

        Node start = new Node(0,0);
        Node end = new Node(0,4);

        System.out.println(new BFS(matrix, start, end).calculate());
    }
}

class Node {
    public int x;
    public int y;
    public int distance;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Node node = (Node)o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}

class BFS {
    private Node start;
    private Node end;
    private int[][] matrix;

    public BFS(int[][] matrix, Node start, Node end) {
        this.matrix = matrix;
        this.start = start;
        this.end = end;
    }

    int calculate() {
        if(matrix[end.x][end.y] == 0) {
            return -1;
        }

        Queue<Node> queue = new LinkedList<>();

        matrix[start.x][start.y] = 0;
        queue.add(start);

        while(!queue.isEmpty()) {
            Node currNode = queue.poll();

            if(currNode.equals(end)) {
                return currNode.distance;
            }

            int x = currNode.x;
            int y = currNode.y;
            int currDistance = currNode.distance;

            addQueueIfPossible(queue, currDistance,x+1, y);
            addQueueIfPossible(queue, currDistance,x-1, y);
            addQueueIfPossible(queue, currDistance, x, y+1);
            addQueueIfPossible(queue, currDistance, x, y-1);
        }

        return -1;
    }

    private void addQueueIfPossible(Queue<Node> queue, int distance, int x, int y) {
        if(isInMatrix(x, y)) {
            Node node = new Node(x, y, distance + 1);
            matrix[x][y] = 0;
            queue.add(node);
        }
    }

    private boolean isInMatrix(int x, int y) {
        int xMax = matrix.length;
        int yMax = matrix[0].length;

        boolean inX = x >= 0 && x < xMax;
        boolean inY = y >= 0 && y < yMax;

        if(inX && inY) {
            return matrix[x][y] == 1;
        }

        return false;
    }
}