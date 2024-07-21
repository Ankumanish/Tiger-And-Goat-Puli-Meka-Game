package pack;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JPanel {

    private Point[][] points;
    private ArrayList<Point> goats;
    private ArrayList<Point> tigers;
    private Map<Point, ArrayList<Point>> adjPoints;
    private Map<Point, ArrayList<Point>> killingPoints;

    private boolean isGoatTurn = false;
    private Point selectedTiger = null;
    private Point selectedGoat = null;
    private int goatAliveCount = 15;
    private int killedGoats = 0;
    private int gameCompleted = 0;

    private Image goatImage;
    private Image tigerImage;
    private Image selectedGoatImage;
    private Image selectedTigerImage;
    private Image winningBackgroundImage;

    public GameBoard() {

        points = new Point[][] {
                { new Point(100, 50), new Point(250, 50), new Point(400, 50) },
                { new Point(175, 125), new Point(250, 125), new Point(325, 125) },
                { new Point(50, 200), new Point(150, 200), new Point(250, 200), new Point(350, 200),
                        new Point(450, 200) },
                { new Point(50, 300), new Point(150, 300), new Point(250, 300), new Point(350, 300),
                        new Point(450, 300) },
                { new Point(50, 400), new Point(150, 400), new Point(250, 400), new Point(350, 400),
                        new Point(450, 400) },
                { new Point(50, 500), new Point(150, 500), new Point(250, 500), new Point(350, 500),
                        new Point(450, 500) },
                { new Point(50, 600), new Point(150, 600), new Point(250, 600), new Point(350, 600),
                        new Point(450, 600) },
                { new Point(175, 675), new Point(250, 675), new Point(325, 675) },
                { new Point(100, 750), new Point(250, 750), new Point(400, 750) }
        };
        
        ImageIcon goatIcon = new ImageIcon(getClass().getResource("goat.png"));
        goatImage = goatIcon.getImage();

        ImageIcon selectedGoatIcon = new ImageIcon(getClass().getResource("selectedGoat.png"));
        selectedGoatImage = selectedGoatIcon.getImage();

        ImageIcon tigerIcon = new ImageIcon(getClass().getResource("tiger.png"));
        tigerImage = tigerIcon.getImage();

        ImageIcon selectedTigerIcon = new ImageIcon(getClass().getResource("selectedTiger.png"));
        selectedTigerImage = selectedTigerIcon.getImage();
        
        ImageIcon winningBackgroundIcon = new ImageIcon(getClass().getResource("winningBackgroundImage.png"));
        winningBackgroundImage = winningBackgroundIcon.getImage();

        goats = new ArrayList<>();
        tigers = new ArrayList<>();
        adjPoints = new HashMap<>();
        killingPoints = new HashMap<>();

        addTigers();
        addGoats();
        addAdjPoints();
        addKillingPoints();
        addMouseListener(new BoardMouseListener());
    }

    private void addTigers() {
        tigers.add(points[2][2]);
        tigers.add(points[4][2]);
    }

    private void addGoats() {
        goats.add(points[3][1]);
        goats.add(points[3][2]);
        goats.add(points[3][3]);
        goats.add(points[4][1]);
        goats.add(points[4][3]);
        goats.add(points[5][1]);
        goats.add(points[5][2]);
        goats.add(points[5][3]);
    }

    private void addAdjPoints() {

        Point p1 = points[0][0];
        adjPoints.put(p1, new ArrayList<>());
        adjPoints.get(p1).add(points[0][1]);
        adjPoints.get(p1).add(points[1][0]);

        Point p2 = points[0][1];
        adjPoints.put(p2, new ArrayList<>());
        adjPoints.get(p2).add(points[0][0]);
        adjPoints.get(p2).add(points[0][2]);
        adjPoints.get(p2).add(points[1][1]);

        Point p3 = points[0][2];
        adjPoints.put(p3, new ArrayList<>());
        adjPoints.get(p3).add(points[0][1]);
        adjPoints.get(p3).add(points[1][2]);

        Point p4 = points[1][0];
        adjPoints.put(p4, new ArrayList<>());
        adjPoints.get(p4).add(points[0][0]);
        adjPoints.get(p4).add(points[1][1]);
        adjPoints.get(p4).add(points[2][2]);

        Point p5 = points[1][1];
        adjPoints.put(p5, new ArrayList<>());
        adjPoints.get(p5).add(points[0][1]);
        adjPoints.get(p5).add(points[1][0]);
        adjPoints.get(p5).add(points[1][2]);
        adjPoints.get(p5).add(points[2][2]);

        Point p6 = points[1][2];
        adjPoints.put(p6, new ArrayList<>());
        adjPoints.get(p6).add(points[0][2]);
        adjPoints.get(p6).add(points[1][1]);
        adjPoints.get(p6).add(points[2][2]);

        Point p7 = points[2][0];
        adjPoints.put(p7, new ArrayList<>());
        adjPoints.get(p7).add(points[2][1]);
        adjPoints.get(p7).add(points[3][0]);
        adjPoints.get(p7).add(points[3][1]);

        Point p8 = points[2][1];
        adjPoints.put(p8, new ArrayList<>());
        adjPoints.get(p8).add(points[2][0]);
        adjPoints.get(p8).add(points[2][2]);
        adjPoints.get(p8).add(points[3][1]);

        Point p9 = points[2][2];
        adjPoints.put(p9, new ArrayList<>());
        adjPoints.get(p9).add(points[1][0]);
        adjPoints.get(p9).add(points[1][1]);
        adjPoints.get(p9).add(points[1][2]);
        adjPoints.get(p9).add(points[2][1]);
        adjPoints.get(p9).add(points[2][3]);
        adjPoints.get(p9).add(points[3][1]);
        adjPoints.get(p9).add(points[3][2]);
        adjPoints.get(p9).add(points[3][3]);

        Point p10 = points[2][3];
        adjPoints.put(p10, new ArrayList<>());
        adjPoints.get(p10).add(points[2][2]);
        adjPoints.get(p10).add(points[2][4]);
        adjPoints.get(p10).add(points[3][3]);

        Point p11 = points[2][4];
        adjPoints.put(p11, new ArrayList<>());
        adjPoints.get(p11).add(points[2][3]);
        adjPoints.get(p11).add(points[3][3]);
        adjPoints.get(p11).add(points[3][4]);

        Point p12 = points[3][0];
        adjPoints.put(p12, new ArrayList<>());
        adjPoints.get(p12).add(points[2][0]);
        adjPoints.get(p12).add(points[3][1]);
        adjPoints.get(p12).add(points[4][0]);

        Point p13 = points[3][1];
        adjPoints.put(p13, new ArrayList<>());
        adjPoints.get(p13).add(points[2][0]);
        adjPoints.get(p13).add(points[2][1]);
        adjPoints.get(p13).add(points[2][2]);
        adjPoints.get(p13).add(points[3][0]);
        adjPoints.get(p13).add(points[3][2]);
        adjPoints.get(p13).add(points[4][0]);
        adjPoints.get(p13).add(points[4][1]);
        adjPoints.get(p13).add(points[4][2]);

        Point p14 = points[3][2];
        adjPoints.put(p14, new ArrayList<>());
        adjPoints.get(p14).add(points[2][2]);
        adjPoints.get(p14).add(points[3][1]);
        adjPoints.get(p14).add(points[3][3]);
        adjPoints.get(p14).add(points[4][2]);

        Point p15 = points[3][3];
        adjPoints.put(p15, new ArrayList<>());
        adjPoints.get(p15).add(points[2][2]);
        adjPoints.get(p15).add(points[2][3]);
        adjPoints.get(p15).add(points[2][4]);
        adjPoints.get(p15).add(points[3][2]);
        adjPoints.get(p15).add(points[3][4]);
        adjPoints.get(p15).add(points[4][2]);
        adjPoints.get(p15).add(points[4][3]);
        adjPoints.get(p15).add(points[4][4]);

        Point p16 = points[3][4];
        adjPoints.put(p16, new ArrayList<>());
        adjPoints.get(p16).add(points[2][4]);
        adjPoints.get(p16).add(points[3][3]);
        adjPoints.get(p16).add(points[4][4]);

        Point p17 = points[4][0];
        adjPoints.put(p17, new ArrayList<>());
        adjPoints.get(p17).add(points[3][0]);
        adjPoints.get(p17).add(points[3][1]);
        adjPoints.get(p17).add(points[4][1]);
        adjPoints.get(p17).add(points[5][0]);
        adjPoints.get(p17).add(points[5][1]);

        Point p18 = points[4][1];
        adjPoints.put(p18, new ArrayList<>());
        adjPoints.get(p18).add(points[3][1]);
        adjPoints.get(p18).add(points[4][0]);
        adjPoints.get(p18).add(points[4][2]);
        adjPoints.get(p18).add(points[5][1]);

        Point p19 = points[4][2];
        adjPoints.put(p19, new ArrayList<>());
        adjPoints.get(p19).add(points[3][1]);
        adjPoints.get(p19).add(points[3][2]);
        adjPoints.get(p19).add(points[3][3]);
        adjPoints.get(p19).add(points[4][1]);
        adjPoints.get(p19).add(points[4][3]);
        adjPoints.get(p19).add(points[5][1]);
        adjPoints.get(p19).add(points[5][2]);
        adjPoints.get(p19).add(points[5][3]);

        Point p20 = points[4][3];
        adjPoints.put(p20, new ArrayList<>());
        adjPoints.get(p20).add(points[3][3]);
        adjPoints.get(p20).add(points[4][2]);
        adjPoints.get(p20).add(points[4][4]);
        adjPoints.get(p20).add(points[5][3]);

        Point p21 = points[4][4];
        adjPoints.put(p21, new ArrayList<>());
        adjPoints.get(p21).add(points[3][3]);
        adjPoints.get(p21).add(points[3][4]);
        adjPoints.get(p21).add(points[4][3]);
        adjPoints.get(p21).add(points[5][3]);
        adjPoints.get(p21).add(points[5][4]);

        Point p22 = points[5][0];
        adjPoints.put(p22, new ArrayList<>());
        adjPoints.get(p22).add(points[4][0]);
        adjPoints.get(p22).add(points[5][1]);
        adjPoints.get(p22).add(points[6][0]);

        Point p23 = points[5][1];
        adjPoints.put(p23, new ArrayList<>());
        adjPoints.get(p23).add(points[4][0]);
        adjPoints.get(p23).add(points[4][1]);
        adjPoints.get(p23).add(points[4][2]);
        adjPoints.get(p23).add(points[5][0]);
        adjPoints.get(p23).add(points[5][2]);
        adjPoints.get(p23).add(points[6][0]);
        adjPoints.get(p23).add(points[6][1]);
        adjPoints.get(p23).add(points[6][2]);

        Point p24 = points[5][2];
        adjPoints.put(p24, new ArrayList<>());
        adjPoints.get(p24).add(points[4][2]);
        adjPoints.get(p24).add(points[5][1]);
        adjPoints.get(p24).add(points[5][3]);
        adjPoints.get(p24).add(points[6][2]);

        Point p25 = points[5][3];
        adjPoints.put(p25, new ArrayList<>());
        adjPoints.get(p25).add(points[4][2]);
        adjPoints.get(p25).add(points[4][3]);
        adjPoints.get(p25).add(points[4][4]);
        adjPoints.get(p25).add(points[5][2]);
        adjPoints.get(p25).add(points[5][4]);
        adjPoints.get(p25).add(points[6][2]);
        adjPoints.get(p25).add(points[6][3]);
        adjPoints.get(p25).add(points[6][4]);

        Point p26 = points[5][4];
        adjPoints.put(p26, new ArrayList<>());
        adjPoints.get(p26).add(points[4][4]);
        adjPoints.get(p26).add(points[5][3]);
        adjPoints.get(p26).add(points[6][4]);

        Point p27 = points[6][0];
        adjPoints.put(p27, new ArrayList<>());
        adjPoints.get(p27).add(points[5][0]);
        adjPoints.get(p27).add(points[5][1]);
        adjPoints.get(p27).add(points[6][1]);

        Point p28 = points[6][1];
        adjPoints.put(p28, new ArrayList<>());
        adjPoints.get(p28).add(points[5][1]);
        adjPoints.get(p28).add(points[6][0]);
        adjPoints.get(p28).add(points[6][2]);

        Point p29 = points[6][2];
        adjPoints.put(p29, new ArrayList<>());
        adjPoints.get(p29).add(points[5][1]);
        adjPoints.get(p29).add(points[5][2]);
        adjPoints.get(p29).add(points[5][3]);
        adjPoints.get(p29).add(points[6][1]);
        adjPoints.get(p29).add(points[6][3]);
        adjPoints.get(p29).add(points[7][0]);
        adjPoints.get(p29).add(points[7][1]);
        adjPoints.get(p29).add(points[7][2]);

        Point p30 = points[6][3];
        adjPoints.put(p30, new ArrayList<>());
        adjPoints.get(p30).add(points[5][3]);
        adjPoints.get(p30).add(points[6][2]);
        adjPoints.get(p30).add(points[6][4]);

        Point p31 = points[6][4];
        adjPoints.put(p31, new ArrayList<>());
        adjPoints.get(p31).add(points[5][3]);
        adjPoints.get(p31).add(points[5][4]);
        adjPoints.get(p31).add(points[6][3]);

        Point p32 = points[7][0];
        adjPoints.put(p32, new ArrayList<>());
        adjPoints.get(p32).add(points[6][2]);
        adjPoints.get(p32).add(points[7][1]);
        adjPoints.get(p32).add(points[8][0]);

        Point p33 = points[7][1];
        adjPoints.put(p33, new ArrayList<>());
        adjPoints.get(p33).add(points[6][2]);
        adjPoints.get(p33).add(points[7][0]);
        adjPoints.get(p33).add(points[7][2]);
        adjPoints.get(p33).add(points[8][1]);

        Point p34 = points[7][2];
        adjPoints.put(p34, new ArrayList<>());
        adjPoints.get(p34).add(points[6][2]);
        adjPoints.get(p34).add(points[7][1]);
        adjPoints.get(p34).add(points[8][2]);

        Point p35 = points[8][0];
        adjPoints.put(p35, new ArrayList<>());
        adjPoints.get(p35).add(points[7][0]);
        adjPoints.get(p35).add(points[8][1]);

        Point p36 = points[8][1];
        adjPoints.put(p36, new ArrayList<>());
        adjPoints.get(p36).add(points[7][1]);
        adjPoints.get(p36).add(points[8][0]);
        adjPoints.get(p36).add(points[8][2]);

        Point p37 = points[8][2];
        adjPoints.put(p37, new ArrayList<>());
        adjPoints.get(p37).add(points[7][2]);
        adjPoints.get(p37).add(points[8][1]);

    }

    private void addKillingPoints() {

        Point p1 = points[0][0];
        killingPoints.put(p1, new ArrayList<>());
        killingPoints.get(p1).add(points[0][2]);
        killingPoints.get(p1).add(points[2][2]);

        Point p2 = points[0][1];
        killingPoints.put(p2, new ArrayList<>());
        killingPoints.get(p2).add(points[2][2]);

        Point p3 = points[0][2];
        killingPoints.put(p3, new ArrayList<>());
        killingPoints.get(p3).add(points[0][0]);
        killingPoints.get(p3).add(points[2][2]);

        Point p4 = points[1][0];
        killingPoints.put(p4, new ArrayList<>());
        killingPoints.get(p4).add(points[1][2]);
        killingPoints.get(p4).add(points[3][3]);

        Point p5 = points[1][1];
        killingPoints.put(p5, new ArrayList<>());
        killingPoints.get(p5).add(points[3][2]);

        Point p6 = points[1][2];
        killingPoints.put(p6, new ArrayList<>());
        killingPoints.get(p6).add(points[1][0]);
        killingPoints.get(p6).add(points[3][1]);

        Point p7 = points[2][0];
        killingPoints.put(p7, new ArrayList<>());
        killingPoints.get(p7).add(points[2][2]);
        killingPoints.get(p7).add(points[4][0]);
        killingPoints.get(p7).add(points[4][2]);

        Point p8 = points[2][1];
        killingPoints.put(p8, new ArrayList<>());
        killingPoints.get(p8).add(points[2][3]);
        killingPoints.get(p8).add(points[4][1]);

        Point p9 = points[2][2];
        killingPoints.put(p9, new ArrayList<>());
        killingPoints.get(p9).add(points[0][0]);
        killingPoints.get(p9).add(points[0][1]);
        killingPoints.get(p9).add(points[0][2]);
        killingPoints.get(p9).add(points[2][0]);
        killingPoints.get(p9).add(points[2][4]);
        killingPoints.get(p9).add(points[4][0]);
        killingPoints.get(p9).add(points[4][2]);
        killingPoints.get(p9).add(points[4][4]);

        Point p10 = points[2][3];
        killingPoints.put(p10, new ArrayList<>());
        killingPoints.get(p10).add(points[2][1]);
        killingPoints.get(p10).add(points[4][3]);

        Point p11 = points[2][4];
        killingPoints.put(p11, new ArrayList<>());
        killingPoints.get(p11).add(points[2][2]);
        killingPoints.get(p11).add(points[4][2]);
        killingPoints.get(p11).add(points[4][4]);

        Point p12 = points[3][0];
        killingPoints.put(p12, new ArrayList<>());
        killingPoints.get(p12).add(points[3][2]);
        killingPoints.get(p12).add(points[5][0]);

        Point p13 = points[3][1];
        killingPoints.put(p13, new ArrayList<>());
        killingPoints.get(p13).add(points[1][2]);
        killingPoints.get(p13).add(points[3][3]);
        killingPoints.get(p13).add(points[5][1]);
        killingPoints.get(p13).add(points[5][3]);

        Point p14 = points[3][2];
        killingPoints.put(p14, new ArrayList<>());
        killingPoints.get(p14).add(points[1][1]);
        killingPoints.get(p14).add(points[3][0]);
        killingPoints.get(p14).add(points[3][4]);
        killingPoints.get(p14).add(points[5][2]);

        Point p15 = points[3][3];
        killingPoints.put(p15, new ArrayList<>());
        killingPoints.get(p15).add(points[1][0]);
        killingPoints.get(p15).add(points[3][1]);
        killingPoints.get(p15).add(points[5][1]);
        killingPoints.get(p15).add(points[5][3]);

        Point p16 = points[3][4];
        killingPoints.put(p16, new ArrayList<>());
        killingPoints.get(p16).add(points[3][2]);
        killingPoints.get(p16).add(points[5][4]);

        Point p17 = points[4][0];
        killingPoints.put(p17, new ArrayList<>());
        killingPoints.get(p17).add(points[2][0]);
        killingPoints.get(p17).add(points[2][2]);
        killingPoints.get(p17).add(points[4][2]);
        killingPoints.get(p17).add(points[6][0]);
        killingPoints.get(p17).add(points[6][2]);

        Point p18 = points[4][1];
        killingPoints.put(p18, new ArrayList<>());
        killingPoints.get(p18).add(points[2][1]);
        killingPoints.get(p18).add(points[4][3]);
        killingPoints.get(p18).add(points[6][1]);

        Point p19 = points[4][2];
        killingPoints.put(p19, new ArrayList<>());
        killingPoints.get(p19).add(points[2][0]);
        killingPoints.get(p19).add(points[2][2]);
        killingPoints.get(p19).add(points[2][4]);
        killingPoints.get(p19).add(points[4][0]);
        killingPoints.get(p19).add(points[4][4]);
        killingPoints.get(p19).add(points[6][0]);
        killingPoints.get(p19).add(points[6][2]);
        killingPoints.get(p19).add(points[6][4]);

        Point p20 = points[4][3];
        killingPoints.put(p20, new ArrayList<>());
        killingPoints.get(p20).add(points[2][3]);
        killingPoints.get(p20).add(points[4][1]);
        killingPoints.get(p20).add(points[6][3]);

        Point p21 = points[4][4];
        killingPoints.put(p21, new ArrayList<>());
        killingPoints.get(p21).add(points[2][2]);
        killingPoints.get(p21).add(points[2][4]);
        killingPoints.get(p21).add(points[4][2]);
        killingPoints.get(p21).add(points[6][2]);
        killingPoints.get(p21).add(points[6][4]);

        Point p22 = points[5][0];
        killingPoints.put(p22, new ArrayList<>());
        killingPoints.get(p22).add(points[3][0]);
        killingPoints.get(p22).add(points[5][2]);

        Point p23 = points[5][1];
        killingPoints.put(p23, new ArrayList<>());
        killingPoints.get(p23).add(points[3][1]);
        killingPoints.get(p23).add(points[3][3]);
        killingPoints.get(p23).add(points[5][3]);
        killingPoints.get(p23).add(points[7][2]);

        Point p24 = points[5][2];
        killingPoints.put(p24, new ArrayList<>());
        killingPoints.get(p24).add(points[3][2]);
        killingPoints.get(p24).add(points[5][0]);
        killingPoints.get(p24).add(points[5][4]);
        killingPoints.get(p24).add(points[7][1]);

        Point p25 = points[5][3];
        killingPoints.put(p25, new ArrayList<>());
        killingPoints.get(p25).add(points[3][1]);
        killingPoints.get(p25).add(points[3][3]);
        killingPoints.get(p25).add(points[5][1]);
        killingPoints.get(p25).add(points[7][0]);

        Point p26 = points[5][4];
        killingPoints.put(p26, new ArrayList<>());
        killingPoints.get(p26).add(points[3][4]);
        killingPoints.get(p26).add(points[5][2]);

        Point p27 = points[6][0];
        killingPoints.put(p27, new ArrayList<>());
        killingPoints.get(p27).add(points[4][0]);
        killingPoints.get(p27).add(points[4][2]);
        killingPoints.get(p27).add(points[6][2]);

        Point p28 = points[6][1];
        killingPoints.put(p28, new ArrayList<>());
        killingPoints.get(p28).add(points[4][1]);
        killingPoints.get(p28).add(points[6][3]);

        Point p29 = points[6][2];
        killingPoints.put(p29, new ArrayList<>());
        killingPoints.get(p29).add(points[4][0]);
        killingPoints.get(p29).add(points[4][2]);
        killingPoints.get(p29).add(points[4][4]);
        killingPoints.get(p29).add(points[6][0]);
        killingPoints.get(p29).add(points[6][4]);
        killingPoints.get(p29).add(points[8][0]);
        killingPoints.get(p29).add(points[8][1]);
        killingPoints.get(p29).add(points[8][2]);

        Point p30 = points[6][3];
        killingPoints.put(p30, new ArrayList<>());
        killingPoints.get(p30).add(points[4][3]);
        killingPoints.get(p30).add(points[6][1]);

        Point p31 = points[6][4];
        killingPoints.put(p31, new ArrayList<>());
        killingPoints.get(p31).add(points[4][2]);
        killingPoints.get(p31).add(points[4][4]);
        killingPoints.get(p31).add(points[6][2]);

        Point p32 = points[7][0];
        killingPoints.put(p32, new ArrayList<>());
        killingPoints.get(p32).add(points[5][3]);
        killingPoints.get(p32).add(points[7][2]);

        Point p33 = points[7][1];
        killingPoints.put(p33, new ArrayList<>());
        killingPoints.get(p33).add(points[5][2]);

        Point p34 = points[7][2];
        killingPoints.put(p34, new ArrayList<>());
        killingPoints.get(p34).add(points[5][1]);
        killingPoints.get(p34).add(points[7][0]);

        Point p35 = points[8][0];
        killingPoints.put(p35, new ArrayList<>());
        killingPoints.get(p35).add(points[6][2]);
        killingPoints.get(p35).add(points[8][2]);

        Point p36 = points[8][1];
        killingPoints.put(p36, new ArrayList<>());
        killingPoints.get(p36).add(points[6][2]);

        Point p37 = points[8][2];
        killingPoints.put(p37, new ArrayList<>());
        killingPoints.get(p37).add(points[6][2]);
        killingPoints.get(p37).add(points[8][0]);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawBoard(g2d);
        drawPieces(g2d);

        if (selectedTiger != null) {
            drawPieceTiger(g2d);
            drawPieceAdjPoints(g2d, selectedTiger);
            drawPieceKillingPoints(g2d);
        }
        if (selectedGoat != null) {
            drawPieceGoat(g2d);
            drawPieceAdjPoints(g2d, selectedGoat);
        }
        if(gameCompleted != 0) {
        	showWinningView(g2d);
        }
        
    }

    private void drawBoard(Graphics2D g2d) {
    	// set color
    	g2d.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
    	g2d.setColor(new Color(0,150,0));
        g2d.fillRect(300, 10, 185, 20);
        
        g2d.setColor(Color.WHITE);
        g2d.drawString("Remaining : " + goatAliveCount + "   Kills : " + killedGoats + "/10" , 308, 25);

        // to increase the width of the line
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.WHITE);

        // Draw the horizontal lines
        g2d.drawLine(points[0][0].x, points[0][0].y, points[0][2].x, points[0][2].y);
        g2d.drawLine(points[1][0].x, points[1][0].y, points[1][2].x, points[1][2].y);
        g2d.drawLine(points[2][0].x, points[2][0].y, points[2][4].x, points[2][4].y);
        g2d.drawLine(points[3][0].x, points[3][0].y, points[3][4].x, points[3][4].y);
        g2d.drawLine(points[4][0].x, points[4][0].y, points[4][4].x, points[4][4].y);
        g2d.drawLine(points[5][0].x, points[5][0].y, points[5][4].x, points[5][4].y);
        g2d.drawLine(points[6][0].x, points[6][0].y, points[6][4].x, points[6][4].y);
        g2d.drawLine(points[7][0].x, points[7][0].y, points[7][2].x, points[7][2].y);
        g2d.drawLine(points[8][0].x, points[8][0].y, points[8][2].x, points[8][2].y);

        // Draw the vertical lines
        g2d.drawLine(points[2][0].x, points[2][0].y, points[6][0].x, points[6][0].y);
        g2d.drawLine(points[2][1].x, points[2][1].y, points[6][1].x, points[6][1].y);
        g2d.drawLine(points[0][1].x, points[0][1].y, points[8][1].x, points[8][1].y);
        g2d.drawLine(points[2][3].x, points[2][3].y, points[6][3].x, points[6][3].y);
        g2d.drawLine(points[2][4].x, points[2][4].y, points[6][4].x, points[6][4].y);

        // Draw the diagonal lines
        g2d.drawLine(points[4][0].x, points[4][0].y, points[0][2].x, points[0][2].y);
        g2d.drawLine(points[6][0].x, points[6][0].y, points[2][4].x, points[2][4].y);
        g2d.drawLine(points[8][0].x, points[8][0].y, points[4][4].x, points[4][4].y);
        g2d.drawLine(points[0][0].x, points[0][0].y, points[4][4].x, points[4][4].y);
        g2d.drawLine(points[2][0].x, points[2][0].y, points[6][4].x, points[6][4].y);
        g2d.drawLine(points[4][0].x, points[4][0].y, points[8][2].x, points[8][2].y);

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Point point = points[i][j];
                if (!goats.contains(point) && !tigers.contains(point)) {
                    g2d.setColor(new Color(90, 190, 250));
                    g2d.fillOval(point.x - 8, point.y - 8, 16, 16);
                }
            }
        }
    }

    private void drawPieces(Graphics2D g2d) {
        for (Point goat : goats) {
            g2d.drawImage(goatImage, goat.x - 18, goat.y - 18, 36, 36, null);
        }
        for (Point tiger : tigers) {
            g2d.drawImage(tigerImage, tiger.x - 18, tiger.y - 18, 36, 36, null);
        }
    }

    private void drawPieceGoat(Graphics2D g2d) {
        g2d.drawImage(selectedGoatImage, selectedGoat.x - 18, selectedGoat.y - 18, 36, 36, null);
    }

    private void drawPieceTiger(Graphics2D g2d) {
        g2d.drawImage(selectedTigerImage, selectedTiger.x - 18, selectedTiger.y - 18, 36, 36, null);
    }

    private void drawPieceAdjPoints(Graphics2D g2d, Point selectedPoint) {
        ArrayList<Point> ar = adjPoints.get(selectedPoint);
        for (Point p : ar) {
            if (!goats.contains(p) && !tigers.contains(p)) {
                g2d.setColor(new Color(15, 15, 250));
                g2d.fillOval(p.x - 8, p.y - 8, 16, 16);
            }
        }
    }

    private void drawPieceKillingPoints(Graphics2D g2d) {
        ArrayList<Point> ar = killingPoints.get(selectedTiger);
        for (Point p : ar) {
            BoardMouseListener board = new BoardMouseListener();
            Point capturePoint = board.findCapturePoint(selectedTiger, p);
            if (goats.contains(capturePoint) && !goats.contains(p) && !tigers.contains(p)) {
                g2d.setColor(new Color(15, 15, 250)); // 10,120,230 // 15,15,250
                g2d.fillOval(p.x - 8, p.y - 8, 16, 16);
            }
        }
    }
    
    private void showWinningView(Graphics2D g2d) {
    	g2d.drawImage(winningBackgroundImage, 100, 250, 300, 300, null);
    	
    	g2d.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
        g2d.setColor(Color.GREEN);
        g2d.drawString("Congratulations", 115, 310);
    	
        g2d.setColor(Color.WHITE);
        
        g2d.drawString("Winner : ",150, 370);
        if(gameCompleted==1) {
        	g2d.drawImage(goatImage, 320, 340, 40, 40, null);
        }
        else if(gameCompleted==2) {
        	g2d.drawImage(tigerImage, 320, 340, 40, 40, null);
        }
        
        g2d.drawString("Looser : ",150, 430);
        if(gameCompleted==2) {
        	g2d.drawImage(goatImage, 310, 397, 40, 40, null);
        }
        if(gameCompleted==1) {
        	g2d.drawImage(tigerImage, 310, 397, 40, 40, null);
        }

        // Create the "Re-Play" button and add
        JButton b1 = new JButton("Re-Play");
        b1.setBounds(125, 475, 100, 40);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the current frame and open a new one
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(GameBoard.this);
                topFrame.dispose();
                new TigerAndGoat();
            }
        });
        
     // Create the "Exit" button and add
        JButton b2 = new JButton("Exit");
        b2.setBounds(275, 475, 100, 40);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the current frame and open a new one
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(GameBoard.this);
                topFrame.dispose();
            }
        });
        
        add(b1);
        add(b2);
        
    }

    
    // Mouse Listener
    private class BoardMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Point clickPoint = e.getPoint();
            Point closestPoint = findClosestPoint(clickPoint);

            if (isGoatTurn) {
                if (goatAliveCount > 0) {
                    if (closestPoint != null && !goats.contains(closestPoint) && !tigers.contains(closestPoint)) {
                        goats.add(closestPoint);
                        goatAliveCount--;
                        isGoatTurn = false;
                    }
                }

                else {
                    if (selectedGoat == null) {
                        if (closestPoint != null && goats.contains(closestPoint)) {
                            selectedGoat = closestPoint;
                        }
                    }

                    else {
                        Point targetPoint = closestPoint;
                        if (targetPoint == null || tigers.contains(targetPoint)) {
                            targetPoint = null;
                        } else if (goats.contains(targetPoint)) {
                            selectedGoat = targetPoint;
                            targetPoint = null;
                        }

                        if (targetPoint != null) {
                            if (adjPoints.get(selectedGoat).contains(targetPoint)) {
                                goats.remove(selectedGoat);
                                goats.add(targetPoint);
                                selectedGoat = null;
                                isGoatTurn = false;
                            }
                        }
                    }
                }

            } else {
                if (selectedTiger == null) {
                    if (closestPoint != null && tigers.contains(closestPoint)) {
                        selectedTiger = closestPoint;
                    }
                }

                else {
                    Point targetPoint = closestPoint;
                    if (targetPoint == null || goats.contains(targetPoint)) {
                        targetPoint = null;
                    } else if (tigers.contains(targetPoint)) {
                        selectedTiger = targetPoint;
                        targetPoint = null;
                    }

                    if (targetPoint != null) {
                        if (adjPoints.get(selectedTiger).contains(targetPoint)) {
                            tigers.remove(selectedTiger);
                            tigers.add(targetPoint);
                            selectedTiger = null;
                            isGoatTurn = true;
                        }

                        else if (killingPoints.get(selectedTiger).contains(targetPoint)) {
                            Point capturePoint = findCapturePoint(selectedTiger, targetPoint);
                            if (goats.contains(capturePoint)) {
                                tigers.remove(selectedTiger);
                                tigers.add(targetPoint);
                                goats.remove(capturePoint);
                                selectedTiger = null;
                                isGoatTurn = true;
                                killedGoats++;
                            }
                        }
                    }
                }
            }

            gameCompleted = isGameOver();
            if (gameCompleted ==0) {
                repaint();
            } else if (gameCompleted == 1) {
                repaint();
            } else if (gameCompleted == 2) {
                repaint();
            }

        }

        private Point findClosestPoint(Point clickPoint) {
            Point closestPoint = null;
            double minDistance = Double.MAX_VALUE;

            for (int i = 0; i < points.length; i++) {
                for (int j = 0; j < points[i].length; j++) {
                    Point p = points[i][j];
                    double distance = clickPoint.distance(p);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestPoint = p;
                    }
                }
            }
            return (minDistance < 18.5) ? closestPoint : null;
        }

        private Point findCapturePoint(Point selectedTiger, Point targetPoint) {
            int dx = targetPoint.x + selectedTiger.x;
            int dy = targetPoint.y + selectedTiger.y;

            Point closestPoint = findClosestPoint(new Point(dx / 2, dy / 2));
            return (closestPoint == null) ? null : closestPoint;
        }

        private int isGameOver() {
            // Tigers are win
            if (killedGoats >= 10) {
                return 2;
            }

            // Goats are win
            if (blockedAdjPoints() && blockedKillingPoints()) {
                return 1;
            }
            return 0;
        }

        private boolean blockedAdjPoints() {
            for (Point tiger : tigers) {
                for (Point p : adjPoints.get(tiger)) {
                    if (!goats.contains(p) && !tigers.contains(p)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean blockedKillingPoints() {
            for (Point tiger : tigers) {
                for (Point p : killingPoints.get(tiger)) {
                    Point capturePoint = findCapturePoint(tiger, p);
                    if (goats.contains(capturePoint) && !goats.contains(p) && !tigers.contains(p)) {
                        return false;
                    }
                }
            }
            return true;
        }

    }
}