package HorseRace;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Horses implements Runnable{
    // 马跑过的路
    JButton buttons;
    // 马的代号
    Integer horseId;
    // 马跑的时间
    Double raceTime;
    JTextField DaoTimeField;
    int[] arr;
    public Horses(JButton buttons,Integer horseId,Double raceTime,int[] arr,JTextField DaoTimeField){
       this.raceTime=raceTime;
        this.buttons=buttons;
        this.horseId=horseId;
        this.arr=arr;
        this.DaoTimeField=DaoTimeField;
    }
    @Override
    public void run() {
        try {
            int horseSpeed ;
            int distance=0;
            while (raceTime > 0) {

                Random random = new Random();
                horseSpeed=random.nextInt(10)+1;
                distance+=horseSpeed*2;
               // System.out.println(distance);
                buttons.setLocation(distance+30,horseId*50+20);
               // panel.repaint();
                Thread.sleep(1000);
                raceTime -= 1;
                DaoTimeField.setText(raceTime.toString());
            }
          //  System.out.println("马" + horseId+"跑了"+ distance+"千米");
            arr[horseId]=distance;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
