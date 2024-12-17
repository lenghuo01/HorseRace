package HorseRace;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

public class RaceSpace extends JFrame {
    public int [] arr ;
    public Double raceTime;
    public Integer horseNum;
    private  JLabel[] label;
    private  JLabel[] label1;
    private  JLabel[] label2;
    private  JButton[] buttons;
    private  Thread[] thread;
    public JTextField DaoTimeField;
    public void raceSpace (Double raceTime,int horseNum){
        this.raceTime=raceTime;
        this.horseNum=horseNum;
      //  System.out.println(this.horseNum);
        label= new JLabel[horseNum];
        label1=new JLabel[horseNum];
        label2=new JLabel[horseNum];
        buttons= new JButton[horseNum];
        thread=new Thread[horseNum];
        arr = new int[horseNum];
    }

    public void BeginRacing() {
        JFrame frame = new JFrame("赛马");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(800, 600);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        JLabel timeLab = new JLabel("请输入比赛时间：");
        timeLab.setBounds(500,10,150,20);
        JTextField TimeField = new JTextField();
        TimeField.setBounds(600,10,100,20);
        JLabel DaotimeLab = new JLabel("倒计时");
         DaoTimeField = new JTextField();
        DaoTimeField.setBounds(710,25,30,30);
        DaotimeLab.setBounds(710,5,100,20);
        JLabel CountLabel = new JLabel("参与马数量：");
        CountLabel.setBounds(500,30,150,20);
        JTextField CountField = new JTextField();
        CountField.setBounds(600,30,100,20);
        JButton startMatchButton = new JButton("开始比赛");
        startMatchButton.setBounds(550,51,90,20);
        JButton endMatchButton = new JButton("显示结果");
        endMatchButton.setBounds(550,75,90,20);
        JButton deleteButton = new JButton("清理现场");
        deleteButton.setBounds(550,100,90,20);
        panel.add(timeLab);
        panel.add(TimeField);
        panel.add(DaotimeLab);
        panel.add(DaoTimeField);
        panel.add(CountLabel);
        panel.add(CountField);
        panel.add(startMatchButton);
        panel.add(endMatchButton);
        panel.add(deleteButton);
        frame.setVisible(true);

        startMatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matchTime = TimeField.getText();
                double num = Double.parseDouble(matchTime);
                int participantCount = Integer.parseInt(CountField.getText());
                raceSpace(num,participantCount);
                panel.repaint();
                begin(panel);
            }
        });
        endMatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

    Integer[][] result = new Integer[arr.length][2];

    for (int i = 0; i < arr.length; i++) {
        result[i][0] = arr[i];
        result[i][1] = i;
    }

    Arrays.sort(result, new Comparator<Integer[]>() {
        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            return o2[0].compareTo(o1[0]);
        }
    });
    JLabel lab0 = new JLabel("比赛排名为：");
    lab0.setBounds(500,120,100,30);
    int count =0;
    for (Integer[] pair : result) {
        count++;
        Integer x=pair[1]+1;
        // System.out.println("马:" + pair[1]+"  "+"路程：" + pair[0]  );
        label1[count-1] = new JLabel("马"+x.toString());
        label2[count-1] = new JLabel("路程："+pair[0].toString());
        label1[count-1].setBounds(510, 120+count*20, 80, 25);
        label2[count-1].setBounds(580, 120+count*20, 80, 25);
        panel.add(lab0);
        panel.add(label1[count-1]);
        panel.add(label2[count-1]);
    }
   panel.repaint();
            }
        });
          deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < horseNum; i++) {
                    panel.remove(buttons[i]);
                    panel.remove(label[i]);
                    panel.remove(label1[i]);
                    panel.remove(label2[i]);
                }
                panel.repaint();

            }
        });
        frame.setVisible(true);
    }

public void begin(JPanel panel){

    for (int i = 0; i < horseNum; i++) {
        label[i] = new JLabel("马" + (i + 1));
        buttons[i]=new JButton("马");
        label[i].setBounds(10,i*50+20,50,20);
        buttons[i].setBounds(30,i*50+20,50,20);
        buttons[i].setBackground(Color.YELLOW);
        panel.add(label[i]);
        panel.add(buttons[i]);
    }
    // 开启马的线程
    for (int i = 0; i < horseNum; i++) {
        Horses horse = new Horses(buttons[i],i,raceTime,arr,DaoTimeField);
        thread[i] = new Thread(horse);
        thread[i].start();
    }
}

}
