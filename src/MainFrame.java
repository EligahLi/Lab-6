import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

public class MainFrame extends JFrame {
    private int width = 500;
    private int height = 450;
    private int time=0;
    private JMenuBar menu = new JMenuBar();
    private JMenu ballsMenu = new JMenu("Мячи");
    private JMenu controlMenu = new JMenu("Управление");
    private Field field=new Field();
    private JMenuItem newBallMenuItem;
    private JMenuItem sandPaperCountMenuItem;
    private JCheckBoxMenuItem sandPaperMenuItem;
    private JCheckBoxMenuItem stopBlueBalls;
    private JCheckBoxMenuItem stopGreenBalls;
 

    MainFrame() {
        field.setPreferredSize(new Dimension(width, height));
        setTitle("lAB 6");
        setJMenuBar(menu);
        menu.add(ballsMenu);
        menu.add(controlMenu);

        newBallMenuItem = ballsMenu.add(newBallMenuItemAction);

        sandPaperCountMenuItem=ballsMenu.add(sandPaperCountMenuItemAction);
        sandPaperCountMenuItem.setEnabled(true);
        sandPaperMenuItem = new JCheckBoxMenuItem("Наждачка");
        controlMenu.add(sandPaperMenuItem);  
        stopGreenBalls=new JCheckBoxMenuItem("Остановить зеленые мячи");
        controlMenu.add(stopGreenBalls);
        stopBlueBalls=new JCheckBoxMenuItem("Остановить голубые мячи");
        controlMenu.add(stopBlueBalls);
        sandPaperMenuItem.addItemListener(new sandPaperMenuItemListener());
        stopGreenBalls.addItemListener(new StopGreenBallsListener());
        stopBlueBalls.addItemListener(new StopBlueBallsListener());
        add(field, BorderLayout.CENTER);
        pack();
    }

    Action newBallMenuItemAction = new AbstractAction("Добавить мяч") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            field.AddBall();
            sandPaperCountMenuItem.setEnabled(true);
       }
    };
    Action sandPaperCountMenuItemAction=new AbstractAction("Наждачка") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String input;
            input = JOptionPane.showInputDialog(MainFrame.this,
                    "Введите значение наждачки", "Наждачка",
                    JOptionPane.QUESTION_MESSAGE);
            try{
                Double in=0.0;
                try{
                in=Double.parseDouble(input);}catch (NullPointerException e){
                    System.out.println("you aren't enter number");
                }
                field.SetSandPaper(in);
            }
            catch (NumberFormatException ex){
                System.out.println("wrong number");
            }
        }
    };
     
    class sandPaperMenuItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
            if(helper.isSelected()){
                System.out.println("sandPaper on");
                field.SandPaperOn();
            }
            else{
                System.out.println("sandPaper off");
                field.SandPaperOff();
            }
        }
    }
    class grossFeederMenuItemListener implements ItemListener {
    @Override
     public void itemStateChanged(ItemEvent itemEvent) {
        JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
        if(helper.isSelected()){
            System.out.println("grossFeeder on");
            field.IsGrossFeederOn();
            field.AddBall(120);
        }
        else{
            System.out.println("grossFeeder off");
            field.IsGrossFeederOff();
        }
     }
    }

    class MouseListener implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }
        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            field.SetCords(mouseEvent.getX(),mouseEvent.getY());
        }
    }
    class StopGreenBallsListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
            if(helper.isSelected()){
                System.out.println("StopGreen on");
               field.IsStopGreenOn();
            }
            else{
                System.out.println("StopGreen off");
                field.IsStopGreenOff();
            }
        }

            }        class StopBlueBallsListener implements ItemListener{

            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
                if(helper.isSelected()){
                    System.out.println("StopBlue on");
                    field.IsStopBlueOn();
                }
                else{
                    System.out.println("StopBlue off");
                    field.IsStopBlueOff();
                }
        }
    }
}