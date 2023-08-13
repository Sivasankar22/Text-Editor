import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menuBar;

    JMenu file,edit;


    JMenuItem newFile,openFile,saveFile;

    JMenuItem cut,copy,paste,selectAll,close;

    JTextArea textArea;

    JPanel panel = new JPanel();

    TextEditor()
    {
        frame = new JFrame();

        menuBar = new JMenuBar();

        textArea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");

        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);

        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        panel.add(textArea,BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);
        frame.setBounds(0,0,800,800);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);


    }
    public static void main(String[] args)
    {
        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==newFile)
        {
            TextEditor newtextEditor = new TextEditor();
        }
        if (e.getSource()==openFile)
        {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();

                String filePath = file.getPath();

                try {
                    FileReader fileReader = new FileReader(filePath);

                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String currentLine="", output="";

                    while ((currentLine= bufferedReader.readLine())!=null)
                    {
                        output+=currentLine+"\n";
                    }
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundExpection )
                {
                    fileNotFoundExpection.printStackTrace();
                }
            }
        }
        if (e.getSource()==saveFile)
        {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                File file = new File (fileChooser.getSelectedFile().getAbsoluteFile()+".txt");

                try {
                    FileWriter fileWriter = new FileWriter(file);

                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    textArea.write(bufferedWriter);
                }
                catch (IOException ioException )
                {
                    ioException.printStackTrace();
                }
            }
        }
       if (e.getSource()==cut)
       {
            textArea.cut();
       }
       if (e.getSource()==copy)
       {
          textArea.copy();
       }
       if (e.getSource()==paste)
       {
          textArea.paste();
       }
       if (e.getSource()==selectAll)
       {
          textArea.selectAll();
       }
       if (e.getSource()==close)
       {
         System.exit(0);
       }
    }
}