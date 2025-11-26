/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.perpustakaan;

/**
 *
 * @author USER
 */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class RoundedPanel1 extends JPanel {

    private int cornerRadius;
    private boolean topLeft, topRight, bottomLeft, bottomRight;

    // Constructor lengkap
    public RoundedPanel1(int radius, boolean topLeft, boolean topRight, boolean bottomLeft, boolean bottomRight) {
        super();
        this.cornerRadius = radius;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        setOpaque(false);
    }

    // Constructor singkat (semua sudut melengkung)
    public RoundedPanel1(int radius) {
        this(radius, true, true, true, true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Path2D.Float path = new Path2D.Float();

        // Mulai dari kiri atas
        if (topLeft)
            path.moveTo(0, cornerRadius);
        else
            path.moveTo(0, 0);

        // Kiri atas
        if (topLeft)
            path.quadTo(0, 0, cornerRadius, 0);
        else
            path.lineTo(0, 0);

        // Kanan atas
        if (topRight)
            path.lineTo(width - cornerRadius, 0);
        else
            path.lineTo(width, 0);

        if (topRight)
            path.quadTo(width, 0, width, cornerRadius);
        else
            path.lineTo(width, 0);

        // Kanan bawah
        if (bottomRight)
            path.lineTo(width, height - cornerRadius);
        else
            path.lineTo(width, height);

        if (bottomRight)
            path.quadTo(width, height, width - cornerRadius, height);
        else
            path.lineTo(width, height);

        // Kiri bawah
        if (bottomLeft)
            path.lineTo(cornerRadius, height);
        else
            path.lineTo(0, height);

        if (bottomLeft)
            path.quadTo(0, height, 0, height - cornerRadius);
        else
            path.lineTo(0, height);

        path.closePath();

        // Isi warna background
        g2.setColor(getBackground());
        g2.fill(path);

        // Border (opsional)
        g2.setColor(getForeground());
        g2.draw(path);

        g2.dispose();
    }
}