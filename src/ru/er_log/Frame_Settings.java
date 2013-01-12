package ru.er_log;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Frame_Settings extends JDialog
{
    public JPanel panel;
        public JLabel frTitle			= new JLabel("Настройки");
        public ButtonGroup group 		= new ButtonGroup();
            public JRadioButton useChBiSmLetter	= new JRadioButton("Генерировать прописные и строчные буквы", true);
            public JRadioButton useChBigLetter	= new JRadioButton("Генерировать все буквы прописными");
            public JRadioButton useChSmallLetter= new JRadioButton("Генерировать все буквы строчными");
        public JCheckBox noGenNum		= new JCheckBox("Не включать числа в генерацию");
        
        public JTextField numOfGroups		= new JTextField("6") {
            public void replaceSelection(String content) {
                super.replaceSelection(content);
                String text = getText();
                if ( text.length() > 1 ) {
                    setText( text.substring(0, 1) );
                }
            }
        }; public JLabel numOfGrLabel		= new JLabel("Число групп: ");
        public JTextField countNumbers		= new JTextField("4") {
            public void replaceSelection(String content) {
                super.replaceSelection(content);
                String text = getText();
                if ( text.length() > 1 ) {
                    setText( text.substring(0, 1) );
                }
            }
        }; public JLabel countNumLabel		= new JLabel("Символов в группе: ");
        public JTextField separator		= new JTextField("-") {
            public void replaceSelection(String content) {
                super.replaceSelection(content);
                String text = getText();
                if ( text.length() > 1 ) {
                    setText( text.substring(0, 1) );
                }
            }
        }; public JLabel separatorLabel		= new JLabel("Разделительный символ: ");
    
    public Frame_Settings(Main main) {
        super(main);
        setTitle("Настройки");
        setModal(true);
        setResizable(false);
        setSize(new Dimension(350, 265));
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
        
        frTitle.setBounds(117, 25, 110, 25);
         useChBiSmLetter.setBounds(15, 62, 252, 25);
         useChBigLetter.setBounds(15, 82, 216, 25);
         useChSmallLetter.setBounds(15, 102, 210, 25);
         noGenNum.setBounds(15, 132, 210, 25);
        numOfGroups.setBounds(110, 172, 36, 20);
         numOfGrLabel.setBounds(35, 172, 69, 20);
        countNumbers.setBounds(270, 172, 36, 20);
         countNumLabel.setBounds(161, 172, 103, 20);
        separator.setBounds(227, 203, 36, 20);
         separatorLabel.setBounds(86, 203, 131, 20);
            
            panel.add(frTitle);
            panel.add(useChBiSmLetter);
            panel.add(useChBigLetter);
            panel.add(useChSmallLetter);
                group.add(useChBiSmLetter);
                group.add(useChBigLetter);
                group.add(useChSmallLetter);
            panel.add(noGenNum);
            panel.add(numOfGroups);
                panel.add(numOfGrLabel);
            panel.add(countNumbers);
                panel.add(countNumLabel);
            panel.add(separator);
                panel.add(separatorLabel);
        setContentPane(panel);
    }
    
    private void setStyleSettings() {
	numOfGroups.setOpaque(false);
	numOfGroups.setBorder(null);
	numOfGroups.setCaretColor(Color.WHITE);
	numOfGroups.setForeground(Color.WHITE);
        numOfGroups.setSelectionColor(new Color(51, 153, 255));
        numOfGroups.setSelectedTextColor(Color.WHITE);
        numOfGroups.setHorizontalAlignment(0);
	  countNumbers.setOpaque(false);
	  countNumbers.setBorder(null);
	  countNumbers.setCaretColor(Color.WHITE);
	  countNumbers.setForeground(Color.WHITE);
          countNumbers.setSelectionColor(new Color(51, 153, 255));
          countNumbers.setSelectedTextColor(Color.WHITE);
          countNumbers.setHorizontalAlignment(0);
	separator.setOpaque(false);
	separator.setBorder(null);
	separator.setCaretColor(Color.WHITE);
	separator.setForeground(Color.WHITE);
        separator.setSelectionColor(new Color(51, 153, 255));
        separator.setSelectedTextColor(Color.WHITE);
        separator.setHorizontalAlignment(0);
	  separatorLabel.setFocusable(false);
	  separatorLabel.setOpaque(false);
	  separatorLabel.setForeground(Color.WHITE);
        frTitle.setHorizontalAlignment(0);
        frTitle.setForeground(new Color(238, 238, 238));
        frTitle.setFont(new Font(null, Font.BOLD, 21));
	  useChBiSmLetter.setFocusable(false);
	  useChBiSmLetter.setOpaque(false);
	  useChBiSmLetter.setForeground(Color.WHITE);
	useChBigLetter.setFocusable(false);
	useChBigLetter.setOpaque(false);
	useChBigLetter.setForeground(Color.WHITE);
	  useChSmallLetter.setFocusable(false);
	  useChSmallLetter.setOpaque(false);
	  useChSmallLetter.setForeground(Color.WHITE);
        noGenNum.setFocusable(false);
        noGenNum.setOpaque(false);
        noGenNum.setForeground(Color.WHITE);
          numOfGroups.setHorizontalAlignment(0);
             numOfGrLabel.setForeground(Color.WHITE);
          countNumbers.setHorizontalAlignment(0);  
             countNumLabel.setForeground(Color.WHITE);
    }
    
    public void show(Main main) {
        setVisible(true);
        setLocationRelativeTo(main);
    }
}