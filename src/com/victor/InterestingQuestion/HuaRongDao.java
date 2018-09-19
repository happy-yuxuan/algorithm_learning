package com.victor.InterestingQuestion;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HuaRongDao {

    private static final int NUMBER_OF_ROW = 3;

    // define direction
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;


    // 3*3
    private int[][] arr;

    // the position of blank space
    private int x;
    private int y;

    // define the track
    private List<Integer> moveArr = new LinkedList<>();

    // define the state of win
    private static final Integer WIN_STATE = 123456780;

    // save the state of search
    private Set<Integer> statusSet = new HashSet<>();

    // the blank is 0
    public HuaRongDao(int[][] arr) {
        this.arr = arr;
        this.arr = arr;
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_ROW; j++) {
                if (arr[i][j] == 0) {
                    this.x = i;
                    this.y = j;
                }
            }
        }
    }

    // 判断是否可以朝某个方向进行移动
    private boolean canMove(int direction) {
        switch (direction) {
            // y > 0才能左移
            case LEFT:
                return y > 0;
            // y < 2才能右移
            case RIGHT:
                return y < 2;
            // x > 0才能上移
            case UP:
                return x > 0;
            // x < 2才能下移
            case DOWN:
                return x < 2;
        }
        return false;
    }

    // 朝某个方向进行移动，该函数不作判断，直接移动
    // 调用前请自行用canMove先行判断
    private void move(int direction) {
        int temp;
        switch (direction) {
            // 空格和左侧数字交换
            case LEFT:
                temp = arr[x][y - 1];
                arr[x][y - 1] = 0;
                arr[x][y] = temp;
                y = y - 1;
                break;
            // 空格和右侧数字交换
            case RIGHT:
                temp = arr[x][y + 1];
                arr[x][y + 1] = 0;
                arr[x][y] = temp;
                y = y + 1;
                break;
            // 空格和上方数字交换
            case UP:
                temp = arr[x - 1][y];
                arr[x - 1][y] = 0;
                arr[x][y] = temp;
                x = x - 1;
                break;
            // 空格和下方数字交换
            case DOWN:
                temp = arr[x + 1][y];
                arr[x + 1][y] = 0;
                arr[x][y] = temp;
                x = x + 1;
                break;
        }
        // 该方向记录
        moveArr.add(direction);
    }

    // 某个方向的回退，该函数不作判断，直接移动
    // 其操作和move方法正好相反
    private void moveBack(int direction) {
        int temp;
        switch (direction) {
            // 空格和左侧数字交换
            case LEFT:
                temp = arr[x][y + 1];
                arr[x][y + 1] = 0;
                arr[x][y] = temp;
                y = y + 1;
                break;
            // 空格和右侧数字交换
            case RIGHT:
                temp = arr[x][y - 1];
                arr[x][y - 1] = 0;
                arr[x][y] = temp;
                y = y - 1;
                break;
            // 空格和上方数字交换
            case UP:
                temp = arr[x + 1][y];
                arr[x + 1][y] = 0;
                arr[x][y] = temp;
                x = x + 1;
                break;
            // 空格和下方数字交换
            case DOWN:
                temp = arr[x - 1][y];
                arr[x - 1][y] = 0;
                arr[x][y] = temp;
                x = x - 1;
                break;
        }
        // 记录的移动步骤出栈
        moveArr.remove(moveArr.size() - 1);
    }

    // 获取状态，这里把9个数字按顺序组成一个整数来代表状态
    // 方法不唯一，只要能区分九宫格状态就行
    private Integer getStatus() {
        int status = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                status = status * 10 + arr[i][j];
            }
        }
        return status;
    }

    // 搜索方法
    private boolean search(int direction) {
        // 如果能够朝该方向行走
        if (canMove(direction)) {
            // 往该方向移动
            move(direction);
            // 移动后的状态
            Integer status = getStatus();
            // 如果已经是胜利状态，返回true
            if (WIN_STATE.equals(status)) {
                return true;
            }
            // 如果是之前走过的状态了，返回false
            if (statusSet.contains(status)) {
                // 这一步走错了，回退
                moveBack(direction);
                return false;
            }
            // 将当前状态存入set
            statusSet.add(status);
            // 继续朝四个方向进行搜索
            boolean searchFourOk = search(RIGHT) || search(DOWN) || search(LEFT) || search(UP);
            if (searchFourOk) {
                return true;
            } else {
                // 这一步走错了，把它的记录去除
                moveBack(direction);
                return false;
            }
        }
        return false;
    }

    // 解题入口方法
    public boolean solve() {
        Integer status = getStatus();
        // 如果已经是胜利状态，返回true
        if (WIN_STATE.equals(status)) {
            return true;
        }
        // 初始状态先记录
        statusSet.add(status);
        // 朝4个方向进行搜索
        return search(RIGHT) || search(DOWN) || search(LEFT) || search(UP);
    }

    // 打印路径
    public void printRoute() {
        for (int i = 0; i < moveArr.size(); i++) {
            System.out.print(getDirString(moveArr.get(i)));
            System.out.print(" ");
        }
    }

    // 方向与其对应的字符串
    private String getDirString(int dir) {
        switch (dir) {
            case LEFT:
                return "左";
            case RIGHT:
                return "右";
            case UP:
                return "上";
            case DOWN:
                return "下";
        }
        return null;
    }

    // 打印当前华容道的状态
    public void print() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {5, 7, 6}, {4, 0, 9}};
        HuaRongDao huaRongDao = new HuaRongDao(arr);
        huaRongDao.print();

        huaRongDao.solve();
        System.out.println();

        huaRongDao.print();
    }

}
