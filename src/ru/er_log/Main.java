package ru.er_log;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;

/**
 * @author CRaFT4ik
 */

public class Main extends JFrame {
    
    public static String version	= "1.3.1";
    public static String outFileName	= "SeriesOfKeys.txt";
    
    public JPanel MainPane;
        public JTextField oneGenField	= new JTextField();
        public JButton genButton	= new JButton("Сгенерировать ключ");
        public JButton copyButton	= new JButton();
        public JButton cleanOneBut	= new JButton();
        public JLabel JLText		= new JLabel("Ваш ключ: ");
        public JLabel message		= new JLabel("");
        public JLabel errmessage	= new JLabel("");
        
    public JPanel SerPane;
        public JLabel genLabelNum	= new JLabel("Кол-во ключей:");
        public JTextField genFieldNum	= new JTextField() {
            public void replaceSelection(String content) {
                super.replaceSelection(content);
                String text = getText();
                if ( text.length() > 7 ) {
                    setText( text.substring(0, 7) );
                }
            }
        };
        public JTextField chDirField	= new JTextField();
        public JButton chDirButton	= new JButton("Указать путь");
        public JButton genSeriesButton	= new JButton("Сгенерировать ключи");
        public JButton openSgenDir	= new JButton();
        public JButton cleanTwoBut	= new JButton();
        
    public JButton toSeries		= new JButton();
    public JButton toMainPane		= new JButton();
    
    public JCheckBox useChLetter	= new JCheckBox();
        public JButton iconChLetter	= new JButton();
    
    public JButton Settings		= new JButton();
    public JButton About		= new JButton();
    public JLabel copyright		= new JLabel("www.er-log.ru");
        
    public static Main main;
    public Frame_About about;
    public Frame_Settings settings;
    
    public static File outputDir = FileSystemView.getFileSystemView().getHomeDirectory();
    String homeDir = "" + outputDir;
    
    public static String path;
    public static boolean boolGenerate = false;
    
    public Main() {
        this.setTitle("Генератор лицензионных ключей");
	this.setSize(new Dimension(480, 320));
        
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
        
        try {
         setIconImage(ImageIO.read(Main.class.getResource("/ru/er_log/data/favicon.png")));
        } catch(IOException e1) {};
        MainPane = new JPanel() {
            public void paintComponent(Graphics g){
                ImageIcon bg = new ImageIcon(Main.class.getResource("/ru/er_log/data/bg.png"));
                g.drawImage(bg.getImage(), 0, 0, null); 
            }
        };
        SerPane = new JPanel() {
            public void paintComponent(Graphics g){
                ImageIcon bg = new ImageIcon(Main.class.getResource("/ru/er_log/data/bg2.png"));
                g.drawImage(bg.getImage(), 0, 0, null); 
            }
        };

        about = new Frame_About(main);
        settings = new Frame_Settings(main);
        
        MainPane.setLayout(null);
        SerPane.setLayout(null);
        SerPane.setVisible(false);
        
        ButtonsPerformed();
        setStyleSettings();

        JLText.setBounds(83, 99, 64, 23);
        oneGenField.setBounds(160, 100, 226, 23);
        toSeries.setBounds(8, 260, 60, 23);
        genButton.setBounds(83, 140, 199, 25);
        useChLetter.setBounds(287, 140, 42, 25); iconChLetter.setBounds(287, 140, 42, 25);
        copyButton.setBounds(334, 140, 26, 25);
        cleanOneBut.setBounds(365, 140, 26, 25);
        message.setBounds(115, 170, 250, 28);
        errmessage.setBounds(115, 170, 250, 28);
        
        Settings.setBounds(73, 260, 24, 23);
        About.setBounds(102, 260, 24, 23);
        copyright.setBounds(380, 265, 85, 18);
            
            MainPane.add(JLText);
            MainPane.add(oneGenField);
            MainPane.add(toSeries);
            MainPane.add(genButton);
            MainPane.add(useChLetter); MainPane.add(iconChLetter);
            MainPane.add(copyButton);
            MainPane.add(cleanOneBut);
            MainPane.add(message);
            MainPane.add(errmessage);
            
            MainPane.add(copyright);
            MainPane.add(Settings);
            MainPane.add(About);
            
        genLabelNum.setBounds(92, 70, 91, 23);
        genFieldNum.setBounds(195, 70, 195, 23);
        toMainPane.setBounds(8, 260, 60, 23);
        genSeriesButton.setBounds(83, 140, 199, 25);
        openSgenDir.setBounds(334, 140, 26, 25);
        cleanTwoBut.setBounds(365, 140, 26, 25);
        chDirField.setBounds(198, 100, 189, 23);
        chDirButton.setBounds(83, 100, 100, 23);
        
            SerPane.add(genLabelNum);
            SerPane.add(genFieldNum);
            SerPane.add(toMainPane);
            SerPane.add(genSeriesButton);
            SerPane.add(openSgenDir);
            SerPane.add(cleanTwoBut);
            SerPane.add(chDirField);
            SerPane.add(chDirButton);
            
        this.setContentPane(MainPane);
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("Установка LnF...");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Не удалось установить новый LnF");
        }
        
        Main frame = new Main();
        frame.show();
    }
    
    private void setStyleSettings() {
	oneGenField.setOpaque(false);
	oneGenField.setBorder(null);
	oneGenField.setCaretColor(Color.WHITE);
	oneGenField.setForeground(Color.WHITE);
        oneGenField.setFocusable(false);
        oneGenField.setSelectionColor(new Color(51, 153, 255));
        oneGenField.setSelectedTextColor(Color.WHITE);
        oneGenField.setHorizontalAlignment(0);
        
	genFieldNum.setOpaque(false);
	genFieldNum.setBorder(null);
	genFieldNum.setCaretColor(Color.WHITE);
	genFieldNum.setForeground(Color.WHITE);
        genFieldNum.setFocusable(true);
        genFieldNum.setSelectionColor(new Color(51, 153, 255));
        genFieldNum.setSelectedTextColor(Color.WHITE);
        genFieldNum.setHorizontalAlignment(0);
        
	chDirField.setOpaque(false);
	chDirField.setBorder(null);
	chDirField.setCaretColor(Color.WHITE);
	chDirField.setForeground(Color.WHITE);
        chDirField.setFocusable(false);
        chDirField.setSelectionColor(new Color(51, 153, 255));
        chDirField.setSelectedTextColor(Color.WHITE);
        
        oneGenField.setFont(new Font(null, Font.ROMAN_BASELINE, 12));
        chDirField.setFont(new Font(null, Font.ROMAN_BASELINE, 12));
        genFieldNum.setFont(new Font(null, Font.ROMAN_BASELINE, 12));
        
        JLText.setFont(new Font(null, Font.BOLD, 12));
        genLabelNum.setFont(new Font(null, Font.BOLD, 12));
        message.setFont(new Font(null, Font.BOLD, 12));
        errmessage.setFont(new Font(null, Font.BOLD, 12));
        chDirButton.setFont(new Font(null, Font.BOLD, 12));
        genButton.setFont(new Font(null, Font.BOLD, 12));
        genSeriesButton.setFont(new Font(null, Font.BOLD, 12));
        
        chDirButton.setForeground(new Color(51, 51, 51));
        genButton.setForeground(new Color(51, 51, 51));
        genSeriesButton.setForeground(new Color(51, 51, 51));
        
        JLText.setForeground(Color.WHITE);
        genLabelNum.setForeground(Color.WHITE);
        
        message.setHorizontalAlignment(0);
        errmessage.setHorizontalAlignment(0);
        message.setForeground(new Color(30, 137, 9));
        errmessage.setForeground(new Color(180, 22, 22));
        
        copyright.setForeground(new Color(51, 51, 51));
        copyright.setFont(new Font(null, Font.BOLD, 12));
        
        ImageIcon iconOpenBut = createIcon("/ru/er_log/data/icon-folder-open.png");
         openSgenDir.setIcon(iconOpenBut);
         openSgenDir.setHorizontalAlignment(0);
        ImageIcon iconCopyBut = createIcon("/ru/er_log/data/icon-file.png");
         copyButton.setIcon(iconCopyBut);
         copyButton.setHorizontalAlignment(0);
        ImageIcon iconCleanBut = createIcon("/ru/er_log/data/icon-trash.png");
         cleanOneBut.setIcon(iconCleanBut);
         cleanOneBut.setHorizontalAlignment(0);
         cleanTwoBut.setIcon(iconCleanBut);
         cleanTwoBut.setHorizontalAlignment(0);
        ImageIcon iconToOneGenBut = createIcon("/ru/er_log/data/icon-tag.png");
         toMainPane.setIcon(iconToOneGenBut);
         toMainPane.setHorizontalAlignment(0);
        ImageIcon iconToSerGenBut = createIcon("/ru/er_log/data/icon-tags.png");
         toSeries.setIcon(iconToSerGenBut);
         toSeries.setHorizontalAlignment(0);
        ImageIcon iconAboutBut = createIcon("/ru/er_log/data/icon-user.png");
         About.setIcon(iconAboutBut);
         About.setHorizontalAlignment(0);
        ImageIcon iconSettingBut = createIcon("/ru/er_log/data/icon-cog.png");
         Settings.setIcon(iconSettingBut);
         Settings.setHorizontalAlignment(0);
        ImageIcon iconChLetterLa = createIcon("/ru/er_log/data/icon-font.png");
         iconChLetter.setIcon(iconChLetterLa);
         iconChLetter.setHorizontalAlignment(4);
        
	useChLetter.setFocusable(false);
	useChLetter.setOpaque(false);
	useChLetter.setForeground(Color.WHITE);
	useChLetter.setFont(new Font(null, Font.BOLD, 12));
        
        genButton.setUI(new UI_Button());
        copyButton.setUI(new UI_Button());
        toMainPane.setUI(new UI_Button());
        toSeries.setUI(new UI_Button());
        chDirButton.setUI(new UI_Button());
        genSeriesButton.setUI(new UI_Button());
        openSgenDir.setUI(new UI_Button());
        cleanOneBut.setUI(new UI_Button());
        cleanTwoBut.setUI(new UI_Button());
        Settings.setUI(new UI_Button());
        About.setUI(new UI_Button());
        iconChLetter.setUI(new UI_Button());
        
        chDirField.setText(homeDir);
    }
    
    private void ButtonsPerformed() {
        genButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                if(!(useChLetter.isSelected()) && (settings.noGenNum.isSelected())) {
                    send("", "Ошибка: разногласие во флажках");
                } else {
                    if(useChLetter.isSelected()) {
                        try {
                            String linesStr = settings.numOfGroups.getText(); int lines = new Integer(linesStr);
                            String CHRStr = settings.countNumbers.getText(); int CHR = new Integer(CHRStr);
                            String separator = settings.separator.getText();
                            
                            if((linesStr.equals("")) || (CHRStr.equals("")) || lines == 0 || CHR == 0 || lines == 1 || CHR == 1) {
                                send("", "Ошибка: значения заданы неверно");
                            } else {
                                if(settings.useChBiSmLetter.isSelected()) {
                                    oneGenField.setText(genLetDig(true, false, false, lines, CHR, separator));
                                } else if(settings.useChBigLetter.isSelected()) {
                                    oneGenField.setText(genLetDig(false, true, false, lines, CHR, separator));
                                } else if(settings.useChSmallLetter.isSelected()) {
                                    oneGenField.setText(genLetDig(false, false, true, lines, CHR, separator));
                                } send("", "");
                            }
                        } catch(Exception e) {
                            send("", "Ошибка: значения заданы неверно");
                        }
                    } else {
                        try {
                            String linesStr = settings.numOfGroups.getText(); int lines = new Integer(linesStr);
                            String CHRStr = settings.countNumbers.getText(); int CHR = new Integer(CHRStr);
                            String separator = settings.separator.getText();
                            
                            if((linesStr.equals("")) || (CHRStr.equals("")) || lines == 0 || CHR == 0 || lines == 1 || CHR == 1) {
                                send("", "Ошибка: значения заданы неверно");
                            } else {
                                oneGenField.setText(call(lines, CHR, separator));
                                send("", "");
                            }
                        } catch(Exception e) {
                            send("", "Ошибка: значения заданы неверно");
                        }
                    }
                     outNum = "";
                }
            }
        });
        
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                oneGenField.selectAll();
                oneGenField.copy();
                String txt = oneGenField.getText();
                if(txt.equals("")) send("", "Ошибка при копировании");
                else send("Копирование завершено успешно", "");
            }
        });
        
        genSeriesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                String numTxt = genFieldNum.getText();
                try {
                    long numLong = new Integer(numTxt);
                    String separator = settings.separator.getText();
                    
                    if(!(useChLetter.isSelected()) && (settings.noGenNum.isSelected())) {
                        send("", "Ошибка: разногласие во флажках");
                    } else {
                        if(useChLetter.isSelected()) {
                            if(settings.useChBiSmLetter.isSelected()) {
                                Writter(numLong, true, true, false, false, separator);
                            } else if(settings.useChBigLetter.isSelected()) {
                                Writter(numLong, true, false, true, false, separator);
                            } else if(settings.useChSmallLetter.isSelected()) {
                                Writter(numLong, true, false, false, true, separator);
                            }
                        } else {
                            Writter(numLong, false, false, false, false, separator);
                        } outNum = "";
                    }
                } catch(Exception e) {
                    send("", "Ошибка: число не указано");
                }
            }
        });
        
        toMainPane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                SerPane.setVisible(false);
                MainPane.setVisible(true);
                MainPane.add(Settings);
                MainPane.add(About);
                MainPane.add(useChLetter); MainPane.add(iconChLetter);
                MainPane.add(copyright);
                send("", "");
                MainPane.add(message);
                MainPane.add(errmessage);
                setContentPane(MainPane);
            }
        });
        
        toSeries.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                MainPane.setVisible(false);
                SerPane.setVisible(true);
                SerPane.add(About);
                SerPane.add(Settings);
                SerPane.add(useChLetter); SerPane.add(iconChLetter);
                SerPane.add(copyright);
                send("", "");
                SerPane.add(message);
                SerPane.add(errmessage);
                setContentPane(SerPane);
            }
        });
        
        chDirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        JFileChooser chooser = new JFileChooser();
                        setUpdateUI(chooser);
                        chooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
                        chooser.setDialogTitle("Выбор директории для сохранения файла");
                        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        chooser.setAcceptAllFileFilterUsed(false);
                        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            path = "" + chooser.getSelectedFile();
                            outputDir = chooser.getSelectedFile();
                        } else {
                            if(chDirField.getText().equals("")) {
                                System.out.println("Окно выбора директории закрыто: директория не указана");
                            }
                        }
                        
                        if (path != "") {
                            String openDirOne = chDirField.getText();
                            chDirField.setText(path);
                            
                            if(openDirOne.equals(chDirField.getText())) {
                                if(boolGenerate == true) {
                                    boolGenerate = true;
                                } else { boolGenerate = false; }
                            } else { boolGenerate = false; }
                            
                            System.out.println("Окно выбора директории закрыто: " + path);
                        } else {}
                    }
                }.start();
                send("", "");
            }
        });
        
        openSgenDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(boolGenerate == true) {
                    if(chDirField.getText().equals("")) {
                        send("", "Директория не указана");
                    } else {
                        openDir(outputDir);
                    }
                } else {
                    send("", "Ключи ещё не были сгенерированы");
                }
            }
        });
        
        cleanOneBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(oneGenField.getText().equals("")) {
                    send("", "Ошибка: ключ не сгенерирован");
                } else {
                    oneGenField.setText("");
                    send("Поле очищено успешно", "");
                }
            }
        });
        
        cleanTwoBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if((genFieldNum.getText().equals("")) && (chDirField.getText().equals(""))) {
                    send("", "Ошибка: ни одно из полей не заполнено");
                } else {
                    genFieldNum.setText("");
                    chDirField.setText("");
                    send("Поля очищены успешно", "");
                }
            }
        });
        
        About.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                about.show(main);
            }
        });
        
        Settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settings.show(main);
            }
        });
        
        copyright.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Util.openLink(new URL("http://www.er-log.ru/").toURI());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            public void mousePressed(MouseEvent e) {
                copyright.setForeground(new Color(100, 100, 100));
            }
            public void mouseReleased(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
                copyright.setForeground(new Color(115, 115, 115));
                copyright.setCursor(Cursor.getPredefinedCursor(12));
            }
            public void mouseExited(MouseEvent e) {
                copyright.setForeground(new Color(51, 51, 51));
            }
        });
    }
    
    public void openDir(File dir) {
        try {
            String[] cmd = new String[2];
            cmd[0] = "explorer.exe";
            cmd[1] = dir.getAbsolutePath();
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
        } catch (IOException ex) {
            send("", "Не удалось открыть папку");
        }
    }
    
    public JFileChooser setUpdateUI(JFileChooser choose) {
        UIManager.put("FileChooser.acceptAllFileFilterText", "Все файлы");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Отмена");
        UIManager.put("FileChooser.deleteFileButtonText", "Удалить");
        UIManager.put("FileChooser.deleteFileButtonToolTipText", "Удалить файл");
        UIManager.put("FileChooser.detailsViewButtonAccessibleName", "Подробно");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Подробно");
        UIManager.put("FileChooser.directoryDescriptionText", "Папка");
        UIManager.put("FileChooser.directoryOpenButtonText", "Открыть");
        UIManager.put("FileChooser.directoryOpenButtonToolTipText", "Открыть");
        UIManager.put("FileChooser.enterFilenameLabelText", "Имя");
        UIManager.put("FileChooser.fileDescriptionText", "Описание");
        UIManager.put("FileChooser.fileNameLabelText", "Имя файла");
        UIManager.put("FileChooser.filesLabelText", "Файлы");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put("FileChooser.folderNameLabel", "Директория:");
        UIManager.put("FileChooser.folderNameLabel.textAndMnemonic", "Директория:");
        UIManager.put("FileChooser.filterLabelText", "Тип(ы) файла");
        UIManager.put("FileChooser.foldersLabelText", "Папка");
        UIManager.put("FileChooser.helpButtonText", "Помощь");
        UIManager.put("FileChooser.helpButtonToolTipText", "Помощь");
        UIManager.put("FileChooser.homeFolderAccessibleName", "Дом");
        UIManager.put("FileChooser.homeFolderToolTipText", "Дом");
        UIManager.put("FileChooser.listViewButtonAccessibleName", "Список");
        UIManager.put("FileChooser.listViewButtonToolTipText", "Список");
        UIManager.put("FileChooser.lookInLabelText", "Католог: ");
        UIManager.put("FileChooser.newFolderAccessibleName", "Создать папку");
        UIManager.put("FileChooser.newFolderButtonText", "Создать папку");
        UIManager.put("FileChooser.newFolderButtonToolTipText", "Создать папку");
        UIManager.put("FileChooser.newFolderDialogText", "Создать папку");
        UIManager.put("FileChooser.newFolderErrorSeparator", "Ошибка создания");
        UIManager.put("FileChooser.newFolderErrorText", "Ошибка создания");
        UIManager.put("FileChooser.newFolderToolTipText", "Создать папку");
        UIManager.put("FileChooser.openButtonText", "Выбрать");
        UIManager.put("FileChooser.openButtonToolTipText", "Открыть");
        UIManager.put("FileChooser.openDialogTitleText", "Открыть");
        UIManager.put("FileChooser.other.newFolder", "Создать папку");
        UIManager.put("FileChooser.other.newFolder.subsequent", "Создать папку");
        UIManager.put("FileChooser.win32.newFolder", "Создать папку");
        UIManager.put("FileChooser.win32.newFolder.subsequent", "Создать папку");
        UIManager.put("FileChooser.pathLabelText", "Путь");
        UIManager.put("FileChooser.renameFileButtonText", "Переименовать");
        UIManager.put("FileChooser.renameFileButtonToolTipText", "Переименовать");
        UIManager.put("FileChooser.renameFileDialogText", "Переименовать");
        UIManager.put("FileChooser.renameFileErrorText", "Ошибка переименования");
        UIManager.put("FileChooser.renameFileErrorTitle", "Ошибка переименования");
        UIManager.put("FileChooser.saveButtonText", "Сохранить");
        UIManager.put("FileChooser.saveButtonToolTipText", "Сохранить");
        UIManager.put("FileChooser.saveDialogTitleText", "Сохранить");
        UIManager.put("FileChooser.saveInLabelText", "Католог: ");
        UIManager.put("FileChooser.updateButtonText", "Обновить");
        UIManager.put("FileChooser.updateButtonToolTipText", "Обновить");
        UIManager.put("FileChooser.upFolderAccessibleName", "Вверх");
        UIManager.put("FileChooser.upFolderToolTipText", "Вверх");
        
        choose.updateUI();
        return choose;
    }
    
    protected static ImageIcon createIcon(String path) {
        URL imgURL = Main.class.getResource(path);    
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Файл не существует " + path);
            return null;
        }
    }
    
    public void send(String mes, String err) {
        message.setText(mes);
        errmessage.setText(err);
    }
    
    
   /* ========================================= */
   /* =============== ГЕНЕРАЦИЯ =============== */
   /* ========================================= */
    
    Random random = new Random();
    
    int ran1; int ran2; int ran3; int ran4; int ran5; int ran6;
    static String outNum = ""; String num;
    
    String aBcMas[] = { "A","a","B","b","C","c","D","d","E","e","F","f","G","g","H","h","I","i",
        "J","j","K","k","L","l","M","m","N","n","O","o","P","p","Q","q","R","r","S","s","T","t",
        "U","u","V","v","W","w","X","x","Y","y","Z","z" };
    
    String ABCMas[] = { "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R",
        "S","T","U","V","W","X","Y","Z" };
    
    String abcMas[] = { "a","b","c","d","e","f","g","h","i","h","k","l","m","n","o","p","q","r",
        "s","t","u","v","e","x","y","z" };
    
    public boolean genNums() {
        if(settings.noGenNum.isSelected()) {
            return false;
        } else {
            return true;
        }
    }
    
    public String retLetDig(boolean aBc, boolean ABC, boolean abc) {
        String letter; int ranNum;
        if(aBc == true) {
            if(genNums() == true) {
                int OneTwo = random.nextInt(2)+1;

                if(OneTwo == 1) { // Буквы
                     int randNum = 0+Math.abs(random.nextInt())%(51-(0-1));
                     letter = aBcMas[randNum];
                    return letter;
                } else {          // Цифры
                     String digit = "" + (ranNum = random.nextInt(10));
                    return digit;
                }
            } else {
                 int randNum = 0+Math.abs(random.nextInt())%(51-(0-1));
                 letter = aBcMas[randNum];
                return letter;
            }
        } else if(ABC == true) {
            if(genNums() == true) {
                int OneTwo = random.nextInt(2)+1;

                if(OneTwo == 1) { // Буквы
                     int randNum = 0+Math.abs(random.nextInt())%(25-(0-1));
                     letter = ABCMas[randNum];
                    return letter;
                } else {          // Цифры
                     String digit = "" + (ranNum = random.nextInt(10));
                    return digit;
                }
            } else {
                 int randNum = 0+Math.abs(random.nextInt())%(25-(0-1));
                 letter = ABCMas[randNum];
                return letter;
            }
        } else if(abc == true) {
            if(genNums() == true) {
                int OneTwo = random.nextInt(2)+1;

                if(OneTwo == 1) { // Буквы
                     int randNum = 0+Math.abs(random.nextInt())%(25-(0-1));
                     letter = abcMas[randNum];
                    return letter;
                } else {          // Цифры
                     String digit = "" + (ranNum = random.nextInt(10));
                    return digit;
                }
            } else {
                 int randNum = 0+Math.abs(random.nextInt())%(25-(0-1));
                 letter = abcMas[randNum];
                return letter;
            }
        } else {
             System.err.println("Ошибка в параметрах метода retLetDig(boolean aBc ... (3)");
            return null;
        }
    }
    
    public String removeSymbol(String str, int pos) {
        return str.substring(0,pos)+str.substring(pos+1);
    }
    
    public String genLetDig(boolean aBc, boolean ABC, boolean abc, int lines, int CHR, String separator) {
        String nNum = null;
        if(aBc == true) {
            outNum = "";
            for (int x=0; x<lines; x++) {
                for (int i=0; i<CHR; i++) {
                    String sum = retLetDig(true, false, false);
                    nNum = outNum + sum;
                    outNum = nNum;
                }
                outNum = outNum + separator;
            } if(!(separator.equals(""))) outNum = removeSymbol(outNum, (outNum.length()-1));
        } else if(ABC == true) {
            outNum = "";
            for (int x=0; x<lines; x++) {
                for (int i=0; i<CHR; i++) {
                    String sum = retLetDig(false, true, false);
                    nNum = outNum + sum;
                    outNum = nNum;
                }
                outNum = outNum + separator;
            } if(!(separator.equals(""))) outNum = removeSymbol(outNum, (outNum.length()-1));
        } else if(abc == true) {
            outNum = "";
            for (int x=0; x<lines; x++) {
                for (int i=0; i<CHR; i++) {
                    String sum = retLetDig(false, false, true);
                    nNum = outNum + sum;
                    outNum = nNum;
                }
                outNum = outNum + separator;
            } if(!(separator.equals(""))) outNum = removeSymbol(outNum, (outNum.length()-1));
        } else { System.err.println("Ошибка в параметрах метода genLetDig(boolean aBc ... (2)"); }
        return outNum;
    }
    
    public String call(int lines, int CHR, String separator) {
        num = genRandom(lines, CHR, separator);
        return (num);
    }
    
    public String genRandom(int lines, int CHR, String separator) {
        outNum = "";
        int one = convertNum(CHR, true);
        int two = convertNum(CHR, false);

        for (int x=0; x<lines; x++) {
            outNum = outNum + (random.nextInt(one)+two);
            outNum = outNum + separator;
        } if(!(separator.equals(""))) outNum = removeSymbol(outNum, (outNum.length()-1));

        return outNum;
    }
    
    public int convertNum(int num, boolean q) {
        int sum = 0; int sum2 = 0;
        
            if(num == 1) {
                if(q == true) return sum = 9;
                else return sum2 = 1;
            } else if(num == 2) {
                if(q == true) return sum = 89;
                else return sum2 = 10;
            } else if(num == 3) {
                if(q == true) return sum = 899;
                else return sum2 = 100;
            } else if(num == 4) {
                if(q == true) return sum = 8999;
                else return sum2 = 1000;
            } else if(num == 5) {
                if(q == true) return sum = 89999;
                else return sum2 = 10000;
            } else if(num == 6) {
                if(q == true) return sum = 899999;
                else return sum2 = 100000;
            } else if(num == 7) {
                if(q == true) return sum = 8999999;
                else return sum2 = 1000000;
            } else if(num == 8) {
                if(q == true) return sum = 89999999;
                else return sum2 = 10000000;
            } else if(num == 9) {
                if(q == true) return sum = 899999999;
                else return sum2 = 10000000;
            } else { return sum+sum2; }
    }
    
    public void Writter(long num, boolean letter, boolean aBc, boolean ABC, boolean abc, String separator) {
        String genNum;
        String dir = chDirField.getText();
        String newDir = dir.replace("\\", "/");
        String fileName = newDir + "/" + outFileName;
        
        if(fileName.equals("/SeriesOfKeys.txt")) {
            send("", "Вы не выбрали директорию");
        } else {
            try {
                try {
                    FileWriter fw = new FileWriter(fileName);
                    BufferedWriter bw = bw = new BufferedWriter(fw);
                    
                    try {
                        String linesStr = settings.numOfGroups.getText(); int lines = new Integer(linesStr);
                        String CHRStr = settings.countNumbers.getText(); int CHR = new Integer(CHRStr);

                        if((linesStr.equals("")) || (CHRStr.equals("")) || lines == 0 || CHR == 0 || lines == 1 || CHR == 1) {
                            send("", "Ошибка: значения заданы неверно");
                        } else {
                            String startOStr = "================= License Keys Generator =================";
                            String startTStr = "======================= Ваши ключи: ======================";
                            String startFStr = "==========================================================";
                            String finallStr = "================ CRaFT4ik © www.er-log.ru ================";

                            bw.write(startFStr); bw.newLine(); bw.write(startOStr); bw.newLine(); bw.write(startFStr);
                            bw.newLine(); bw.write(startTStr); bw.newLine(); bw.write(startFStr); bw.newLine(); bw.newLine();

                            if(letter == true) {
                                if(aBc == true) {
                                    for (int i=0; i<num; i++) {
                                        genNum = genLetDig(true, false, false, lines, CHR, separator);
                                        bw.write(genNum);
                                        outNum = "";
                                        bw.newLine();
                                    }
                                } else if(ABC == true) {
                                    for (int i=0; i<num; i++) {
                                        genNum = genLetDig(false, true, false, lines, CHR, separator);
                                        bw.write(genNum);
                                        outNum = "";
                                        bw.newLine();
                                    }
                                } else if(abc == true) {
                                    for (int i=0; i<num; i++) {
                                        genNum = genLetDig(false, false, true, lines, CHR, separator);
                                        bw.write(genNum);
                                        outNum = "";
                                        bw.newLine();
                                    }
                                }
                            } else {
                                if((linesStr.equals("")) || (CHRStr.equals("")) || lines == 0 || CHR == 0 || lines == 1 || CHR == 1) {
                                    send("", "Ошибка: значения заданы неверно");
                                } else {
                                    for (int i=0; i<num; i++) {
                                        genNum = call(lines, CHR, separator);
                                        bw.write(genNum);
                                        bw.newLine();
                                    }
                                }
                            }

                            bw.newLine(); bw.write(finallStr); bw.newLine();

                            bw.close();
                            send("Ключи успешно сгенерированны", "");
                            System.out.println("Файл успешно записан по директории: " + fileName);
                            boolGenerate = true;
                        }
                    } catch(Exception e) {
                        send("", "Ошибка: значения заданы неверно");
                    }
                } catch(IOException e) {
                    send("", "Директория указана неверно");
                }
            } catch(Exception e) {
                e.printStackTrace();
                send("", "Ошибка при серийной генерации");
            }
        }
    }
}