/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.maze_project.utils;

/**
 *
 * @author anadis
 */
public class IndexTree {

    private int[] tree;
    private int size, rows, cols, start;

    public IndexTree(int rows, int cols) {
        this.size = rows * cols;
        this.cols = cols;
        this.rows = rows;
        int s = (int) Math.pow(2, Math.ceil(Math.log(2 * size) / Math.log(2)));
        this.tree = new int[s];
        this.start = s / 2;
        for (int i = start; i < start + size; i++) {
            tree[i] = 2;
        }
        for (int i = start - 1; i > 0; i--) {
            if (tree[i * 2] > 0) {
                tree[i]++;
            }
            if (tree[i * 2 + 1] > 0) {
                tree[i]++;
            }
        }
    }

    public int[] getFreeIndex(int r, int c) {
        int[] cell = new int[2];
        cell[0] = r;
        cell[1] = c;
        int index = (r - 1) * this.cols + c - 1;
        index += start;
        if (tree[index] != 0) {
            return cell;
        }
        int s = index / 2;
        while (s < start) {
            if (s == 1 && tree[s] == 0) {
                cell[0] = 0;
                cell[1] = 0;
                return cell;
            }
            if (tree[s] != 0) {
                if (tree[s * 2 + 1] != 0) {
                    s = s * 2 + 1;
                    continue;
                } else if (tree[s * 2] != 0) {
                    s = s * 2;
                    continue;
                }
            }
            s /= 2;

        }
        s -= start;
        cell[0] = s / this.cols + 1;
        cell[1] = (s % this.cols) + 1;
        return cell;
    }

    public void markVisited(int r, int c) {
        int index = (r - 1) * this.cols + c - 1;
        index += start;
        tree[index] = 0;
        int s = index / 2;
        while (s > 0) {
            if (s == 1 && tree[s] == 0) {
                break;
            }
            if (tree[s] == 1) {
                tree[s] = 0;
                s /= 2;
                continue;
            } else {
                tree[s]--;
                break;
            }
        }
    }

}
