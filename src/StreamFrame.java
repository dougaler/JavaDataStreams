import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StreamFrame extends JFrame{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel displayPnl;
    JPanel searchPnl;
    JLabel titleLbl;
    JLabel searchLbl;
    JPanel inputPnl;

    JButton searchStrBtn;
    JTextArea getSearch;
    JButton quitBtn;

    JButton getFileBtn;

    static JTextArea displayTA;
    static JTextArea displayOG;
    JScrollPane scroller;
    JScrollPane friemd;
    String searchedString;
    Boolean fileChosen = false;

    public StreamFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTitlePanel();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createInputPanel();
        mainPnl.add(inputPnl, BorderLayout.WEST);

        createDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.EAST);

        createSearchPanel();
        mainPnl.add(searchPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void createTitlePanel()
    {
        titlePnl = new JPanel();
        titlePnl.setLayout(new GridLayout(2, 1));
        titleLbl = new JLabel("Search", JLabel.CENTER);
        titleLbl.setFont(new Font("Courier", Font.BOLD,30));
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);


        titlePnl.add(titleLbl);

    }
    private void createInputPanel() {
        inputPnl = new JPanel();
        inputPnl.setLayout(new GridLayout(1, 1));
        displayOG = new JTextArea(10, 80);

        displayOG.setEditable(false);
        displayOG.setFont(new Font("Courier New", Font.PLAIN, 12));

        friemd = new JScrollPane(displayOG);
        inputPnl.add(friemd);

    }

    private void createDisplayPanel()
    {
        displayPnl = new JPanel();

        displayPnl.setLayout(new GridLayout(1, 1));
        displayTA = new JTextArea(10, 80);
        displayTA.setEditable(false);
        displayTA.setFont(new Font("Courier New", Font.PLAIN, 12));
        scroller = new JScrollPane(displayTA);

        displayPnl.add(scroller);

    }
    private void createSearchPanel()
    {
        searchPnl = new JPanel();

        searchPnl.setLayout(new GridLayout(5, 1));

        searchLbl = new JLabel("Search:");
        getSearch = new JTextArea(3, 25);
        searchStrBtn = new JButton("Search File");
        searchStrBtn.addActionListener((ActionEvent ae) ->{
            displayTA.setText("");
            if (fileChosen) {
                searchedString = getSearch.getText();
                getSearch.setText("");
                displayTA.append(FileMaster.getSearchedString(searchedString));
            }else{
                displayTA.append("No file chosen. Please Choose file");
            }
        });
        getFileBtn = new JButton("Choose File");
        getFileBtn.addActionListener((ActionEvent ae) ->{
            displayOG.append(FileMaster.getFile()+"\n");
            fileChosen = true;
        });


        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            FileMaster.getFileClose();
            System.exit(0);
        });

        searchPnl.add(searchLbl);
        searchPnl.add(getSearch);
        searchPnl.add(searchStrBtn);
        searchPnl.add(getFileBtn);
        searchPnl.add(quitBtn);
    }
}
