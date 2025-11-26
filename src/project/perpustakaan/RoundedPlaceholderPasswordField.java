/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.perpustakaan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RoundedPlaceholderPasswordField extends JPasswordField {

    private String placeholder;
    private int radius = 10;
    private char defaultEcho;

    public RoundedPlaceholderPasswordField(String placeholder) {
        this.placeholder = placeholder;
        setOpaque(false);

        // Simpan default echo char
        defaultEcho = getEchoChar();

        // Set awal sebagai placeholder
        setText("");
        setEchoChar((char) 0);
        setForeground(new Color(0, 0, 0));

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String text = new String(getPassword());

                if (text.isEmpty() || text.equals(placeholder)) {
                    setText("");
                }

                // kembali ke echo char normal
                setEchoChar(defaultEcho);
                setForeground(Color.BLACK);
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = new String(getPassword());

                if (text.isEmpty()) {
                    // tampilkan placeholder tanpa echo char
                    setText("");
                    setEchoChar((char) 0);
                    setForeground(Color.BLACK);
                }

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background rounded
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g);

        // Gambar placeholder hanya bila kosong + tidak fokus
        if (!isFocusOwner() && new String(getPassword()).isEmpty()) {
            g2.setColor(new Color(0, 0, 0));
            FontMetrics fm = g2.getFontMetrics();
            int textHeight = fm.getAscent() - fm.getDescent();
            int y = (getHeight() + textHeight) / 2;

            g2.drawString(placeholder, 10, y);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(50, 68, 182));
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, radius, radius);

        g2.dispose();
    }
}

