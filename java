import java.util.Random;
import java.util.Scanner;

public class SnakeGame {

    static int height = 20, width = 20;
    static int[] tailX = new int[100];
    static int[] tailY = new int[100];
    static int length;
    static int x, y, fruitX, fruitY, score;
    static boolean gameover;
    static int flag; // 1: Left, 2: Down, 3: Right, 4: Up

    public static void main(String[] args) {
        setup();
        while (!gameover) {
            draw();
            input();
            algorithm();
        }
    }

    static void setup() {
        gameover = false;
        x = height / 2;
        y = width / 2;
        length = 0;
        score = 0;
        fruit();
    }

    static void draw() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    System.out.print("#");
                } else {
                    if (i == x && j == y) {
                        System.out.print("0");
                    } else if (i == fruitX && j == fruitY) {
                        System.out.print("*");
                    } else {
                        boolean isPrinted = false;
                        for (int k = 0; k < length; k++) {
                            if (i == tailX[k] && j == tailY[k]) {
                                System.out.print("o");
                                isPrinted = true;
                            }
                        }
                        if (!isPrinted) {
                            System.out.print(" ");
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println("Score: " + score);
        System.out.println("Press X to quit the game");
    }

    static void input() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            char ch = scanner.next().charAt(0);
            switch (ch) {
                case 'a':
                    flag = 1;
                    break;
                case 's':
                    flag = 2;
                    break;
                case 'd':
                    flag = 3;
                    break;
                case 'w':
                    flag = 4;
                    break;
                case 'x':
                    gameover = true;
                    break;
            }
        }
    }

    static void algorithm() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int prevX = tailX[0];
        int prevY = tailY[0];
        int prev2X, prev2Y;
        tailX[0] = x;
        tailY[0] = y;

        for (int i = 1; i < length; i++) {
            prev2X = tailX[i];
            prev2Y = tailY[i];
            tailX[i] = prevX;
            tailY[i] = prevY;
            prevX = prev2X;
            prevY = prev2Y;
        }

        switch (flag) {
            case 1:
                y--;
                break;
            case 2:
                x++;
                break;
            case 3:
                y++;
                break;
            case 4:
                x--;
                break;
        }

        if (x < 0 || x >= height || y < 0 || y >= width) {
            gameover = true;
        }

        for (int i = 0; i < length; i++) {
            if (tailX[i] == x && tailY[i] == y) {
                gameover = true;
            }
        }

        if (x == fruitX && y == fruitY) {
            score += 10;
            fruit();
            length++;
        }
    }

    static void fruit() {
        Random random = new Random();
        fruitX = random.nextInt(height - 2) + 1;
        fruitY = random.nextInt(width - 2) + 1;
    }
}
