package ru.er_log;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Frame_About extends JDialog
{
    public JPanel panel = new JPanel();
        public JLabel decKey = new JLabel();
        public JLabel about1  = new JLabel("License Keys Generator");
        public JLabel about2  = new JLabel("version " + Main.version);
        public JLabel about3  = new JLabel("© Coded by CRaFT4ik (Eldar Timraleev)");
        public JLabel about4  = new JLabel("www.er-log.ru");
    
    public Frame_About(Main main) {
        super(main);
        setTitle("О программе");
        setModal(true);
        setResizable(false);
        setSize(new Dimension(300, 200));
        setLocationRelativeTo(main);
        
            try {
             setIconImage(ImageIO.read(ru.er_log.Main.class.getResource("/ru/er_log/data/favicon.png")));
            } catch(IOException e1) {};

            panel = new JPanel() {
                public void paintComponent(Graphics g){
                    ImageIcon bg = new ImageIcon(Frame_About.class.getResource("/ru/er_log/data/bg3.png"));
                    g.drawImage(bg.getImage(), 0, 0, null); 
                }
            };
            
        panel.setLayout(null);
        
        setStyleSettings();
        
        about1.setBounds(58, 25, 184, 18);
        about2.setBounds(112, 55, 76, 18);
        about3.setBounds(40, 127, 220, 18);
        about4.setBounds(107, 145, 86, 18);
        decKey.setBounds(13, 47, 75, 75);
            
            panel.add(about1);
            panel.add(about2);
            panel.add(about3);
            panel.add(about4);
            panel.add(decKey);
        
        setContentPane(panel);
    }
    
    private void setStyleSettings() {
        about1.setHorizontalAlignment(0);
        about2.setHorizontalAlignment(0);
        about3.setHorizontalAlignment(0);
        about4.setHorizontalAlignment(0);
        
        about1.setForeground(new Color(238, 238, 238));
        about2.setForeground(new Color(102, 102, 102));
        about3.setForeground(new Color(102, 102, 102));
        about4.setForeground(new Color(102, 102, 102));
        
        about1.setFont(new Font(null, Font.BOLD, 16));
        about2.setFont(new Font(null, Font.BOLD, 12));
        about3.setFont(new Font(null, Font.BOLD, 12));
        about4.setFont(new Font(null, Font.BOLD, 12));
        
        ImageIcon iconKey = Main.createIcon("/ru/er_log/data/key.png");
        decKey.setIcon(iconKey);
        
        about4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Util.openLink(new URL("http://www.er-log.ru/").toURI());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            public void mousePressed(MouseEvent e) {
                about4.setForeground(new Color(130, 130, 130));
            }
            public void mouseReleased(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
                about4.setForeground(new Color(150, 150, 150));
                about4.setCursor(Cursor.getPredefinedCursor(12));
            }
            public void mouseExited(MouseEvent e) {
                about4.setForeground(new Color(102, 102, 102));
            }
        });
    }
    
    public void show(Main main) {
        setVisible(true);
        setLocationRelativeTo(main);
    }
}