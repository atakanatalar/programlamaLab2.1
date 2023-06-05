
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Dusman extends Karakter {

    private ImageIcon imageIcon;
    private String kapi;
    private int x;
    private int y;

    public Dusman() {}

    Dusman(int id, String ad, String tur, Lokasyon lokasyon, ImageIcon imageIcon, String kapi) {
        super(id, ad, tur, lokasyon);
        this.imageIcon = imageIcon;
        this.kapi = kapi;
    }

    public void EnKisaYol(int[][] maze, int x, int y, List<Lokasyon> path, int tut) {
        int dx;
        int dy;

        dx = 0;
        dy = 1;
        if (y + dy < maze.length && maze[y + dy][x + dx] == 1) {
            maze[y + dy][x + dx] = 2;
            path.add(new Lokasyon(x + dx, y + dy));
        }
        dx = 0;
        dy = -1;
        if (y + dy >= 0 && maze[y + dy][x + dx] == 1) {
            maze[y + dy][x + dx] = 2;
            path.add(new Lokasyon(x + dx, y + dy));
        }
        dx = 1;
        dy = 0;

        if (x + dx < maze[0].length && maze[y + dy][x + dx] == 1) {
            maze[y + dy][x + dx] = 2;
            path.add(new Lokasyon(x + dx, y + dy));
        }
        dx = -1;
        dy = 0;

        if (x + dx >= 0 && maze[y + dy][x + dx] == 1) {
            maze[y + dy][x + dx] = 2;
            path.add(new Lokasyon(x + dx, y + dy));
        }

        if (!(path.get(tut).getX() == this.getX() && path.get(tut).getY()== this.getY()))
            EnKisaYol(maze, path.get(tut).getX(), path.get(tut).getY(), path, tut += 1);
    }

    public List<Lokasyon> DijkstraList(int[][] labirent, int x, int y) {
        ArrayList<Lokasyon> path = new ArrayList<>();
        ArrayList<Lokasyon> path2 = new ArrayList<>();

        this.setX(x);
        this.setY(y);

        int ax = 1, bx = -1, ay = 1, by = -1;
        EnKisaYol(labirent, this.getLokasyon().getX(), this.getLokasyon().getY(), path, 0);

        int oyuncuIndis = 0;
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i).getX() == x && path.get(i).getY() == y) {
                oyuncuIndis = i;
                break;
            }

        }
        for (int i = oyuncuIndis; i >= 1; i--) {
            if ((path.get(i - 1).getX() == x + ax && path.get(i - 1).getY() == y)) {
                path2.add(path.get(i - 1));
                x += ax;
            } else if ((path.get(i - 1).getX() == x && path.get(i - 1).getY() == y + ay)) {
                path2.add(path.get(i - 1));
                y += ay;
            } else if ((path.get(i - 1).getX() == x + bx && path.get(i - 1).getY() == y)) {
                path2.add(path.get(i - 1));
                x += bx;
            } else if ((path.get(i - 1).getX() == x && path.get(i - 1).getY() == y + by)) {
                path2.add(path.get(i - 1));
                y += by;
            }
        }
        path2.add(new Lokasyon(x, y));
        return path2;
    }

    public String getKapi() {
        return kapi;
    }

    public void setKapi(String kapi) {
        this.kapi = kapi;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
