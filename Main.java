import javax.swing.*;     //创建图形用户界面（GUI）的类和方法的集合
import java.awt.BorderLayout;   //提供的布局管理器之一，用于在容器中安排组件。
import java.awt.Color;
import java.awt.Toolkit;    //提供了与本机平台相关的工具方法，例如获取屏幕大小和其他系统相关信息
import java.awt.event.ActionEvent;    //用于处理 GUI 中事件的类和接口，例如按钮点击等
import java.awt.event.ActionListener;   //同上
import java.util.Objects;    //提供了操作对象的通用实用方法
import javax.swing.BorderFactory;    //提供了创建和操作边框的工具类
import javax.swing.Box;    //用于创建具有特定排列和尺寸的不可见组件的类
import javax.swing.ButtonGroup;    //用于将按钮组织成单选按钮组的类
import javax.swing.JButton;       // 表示可点击按钮的类
import javax.swing.JFrame;    //代表 GUI 应用程序中窗口的类
import javax.swing.JLabel;    //用于显示文本或图像的标签类
import javax.swing.JOptionPane;     //提供用于创建和显示对话框的类
import javax.swing.JRadioButton;     //表示单选按钮的类
import javax.swing.JTextField;    //示允许用户编辑文本的单行文本字段的类


public class Main extends JFrame {      //Main类继承自JFrame 可被super调用
    private static final int width = 800;
    private static final int height = 600;
    private JTextField resultField;
    private JTextField textFieldX;
    private JTextField textFieldy;
    private JTextField textFieldZ;
    private JLabel image;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box formulaType = Box.createHorizontalBox();
    private ButtonGroup radioMemoryButtons = new ButtonGroup();
    private Box hBoxMemoryType = Box.createHorizontalBox();
    private JTextField memoryTextField;
    private int formulaNumber = 1;
    private int memoryId = 1;

    private double mem1 = 0;
//    private double mem2 = 0;
//    private double mem3 = 0;


    public Double formula1(Double x, Double y, Double z) {
        if (y <= 0 || z <= 0) {
            JOptionPane.showMessageDialog(Main.this,
                    "y 或 z not <= 0", "" +
                            "err", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }

        return Math.sin(y + Math.pow(y, 2) + Math.exp(Math.cos(y))) * Math.pow(Math.log(z) + Math.sin(Math.PI * Math.pow(x, 2)), 0.25);
    }

    public Double formula2(Double x, Double y, Double z) {
        if (x == 0 || y == 0 || z == 0) {
            JOptionPane.showMessageDialog(Main.this,
                    "x、y 或 z not = 0", "" +
                            "err", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        return (Math.tan(Math.pow(x, 2)) + Math.sqrt(y)) / (z * Math.log(x + y));
    }

    private void addMemoryRadioButton(String buttonName, final int memoryId) {
        JRadioButton button = new JRadioButton(buttonName);
//创建的保存的变量
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Main.this.memoryId = memoryId;        //在内部类中引用外部类 Main 的实例。允许通过 Main.this 访问外部类的 memoryId 和 memoryTextField 成员变量。
                if (memoryId == 1) memoryTextField.setText(Double.toString(mem1));
//                if (memoryId == 2) memoryTextField.setText(Double.toString(mem2));
//                if (memoryId == 3) memoryTextField.setText(Double.toString(mem3));
            }
        });
        radioMemoryButtons.add(button);
        hBoxMemoryType.add(button);
    }

    private void addRadioButton(String name, final int formula_number) {
        JRadioButton button = new JRadioButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.this.formulaNumber = formula_number;
                if (formula_number == 1) image.setIcon(new ImageIcon(Objects.requireNonNull(Main.class.getResource("formula_1.png"))));    //这里选择图像

                if (formula_number == 2) image.setIcon(new ImageIcon(Objects.requireNonNull(Main.class.getResource("formula_2.png"))));
            }
        });
        radioButtons.add(button);
        formulaType.add(button);
    }

    public Main() {
        super("lab2");
        setSize(width, height);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - width) / 2, (kit.getScreenSize().height - height) / 2);

        Box picture = Box.createHorizontalBox();
        picture.add(Box.createVerticalGlue());
        picture.add(Box.createHorizontalGlue());
        image = new JLabel(new ImageIcon(Objects.requireNonNull(Main.class.getResource("formula_1.png"))));   //不能加载图片？
        image = new JLabel(new ImageIcon(Objects.requireNonNull(Main.class.getResource("formula_2.png"))));
        picture.add(image);
        picture.add(Box.createHorizontalGlue());
        picture.setBorder(BorderFactory.createLineBorder(Color.black));

        formulaType.add(Box.createHorizontalGlue());
        addRadioButton("formula_1", 1);          //和上面数字关联
        addRadioButton("formula_2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        formulaType.add(Box.createHorizontalGlue());
        formulaType.setBorder(BorderFactory.createLineBorder(Color.blue));


        Box data = Box.createHorizontalBox();
        //data.add(Box.createHorizontalGlue());
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        textFieldy = new JTextField("0", 10);
        textFieldy.setMaximumSize(textFieldX.getPreferredSize());
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldX.getPreferredSize());
        JLabel x_label = new JLabel("X:");
        JLabel y_label = new JLabel("Y:");
        JLabel z_label = new JLabel("Z:");
        data.add(Box.createHorizontalStrut(0));
        //data.add(Box.createHorizontalGlue());   //取消了这个
        data.add(x_label);
        //data.add(Box.createHorizontalStrut(10));
        data.add(textFieldX);





        //data.add(Box.createHorizontalStrut(280));
        data.add(Box.createHorizontalGlue());
        data.add(y_label);

        //data.add(Box.createHorizontalStrut(10));   //
        //data.add(Box.createHorizontalGlue());  //
        data.add(textFieldy);
        //data.add(Box.createHorizontalGlue());
        //data.add(Box.createHorizontalStrut(300));
        data.add(Box.createHorizontalGlue());  //








        data.add(z_label);
        data.add(Box.createHorizontalStrut(10));
        data.add(textFieldZ);
        //data.add(Box.createHorizontalGlue());  //
        //data.add(Box.createHorizontalStrut(100));
        data.setBorder(BorderFactory.createLineBorder(Color.red));

        Box resultArea = Box.createHorizontalBox();
        resultArea.add(Box.createHorizontalGlue());
        JLabel resultlabel = new JLabel("Результат:");
        resultField = new JTextField("0", 15);
        resultField.setMaximumSize(resultField.getPreferredSize());
        resultArea.add(resultlabel);
        resultArea.add(Box.createHorizontalStrut(10));
        resultArea.add(resultField);
        resultArea.add(Box.createHorizontalGlue());
        resultArea.setBorder(BorderFactory.createLineBorder(Color.yellow));

        Box actions = Box.createHorizontalBox();  //创建新项（可以添加行）
        JButton calcButton = new JButton("计算Вычислить");
        calcButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldy.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaNumber == 1)
                        result = formula1(x, y, z);
                    else
                        result = formula2(x, y, z);
                    resultField.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this, "Ошибка в" +
                                    "формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton cleanButton = new JButton("清除Очистить");
        cleanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldy.setText("0");
                textFieldZ.setText("0");
                resultField.setText("0");
            }
        });


//        JButton button1 = new JButton("Button 1");
//        actions.add(button1);

        //actions.add(Box.createHorizontalStrut(10));  //行间距
        //actions.add(Box.createHorizontalGlue());
        actions.add(calcButton);
        actions.add(Box.createHorizontalGlue());
        actions.add(cleanButton);
        //actions.add(Box.createHorizontalGlue());


        //actions.add(Box.createHorizontalStrut(10));
        //actions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//
//        hBoxMemoryType.add(Box.createHorizontalGlue());
//        addMemoryRadioButton("存储变量 1", 1);
//        addMemoryRadioButton("存储变量 2", 2);
//        addMemoryRadioButton("存储变量 3", 3);
//        radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true);
//        hBoxMemoryType.add(Box.createHorizontalGlue());


        Box memoryResult = Box.createHorizontalBox();
        memoryResult.add(Box.createHorizontalGlue());
        JButton mc = new JButton("MC");

        mc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (memoryId == 1) mem1 = (double) 0;
//                if (memoryId == 2) mem2 = (double) 0;
//                if (memoryId == 3) mem3 = (double) 0;
                memoryTextField.setText("0.0");
            }
        });

        JButton memoryPlus = new JButton("M+");
        memoryPlus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Double result = Double.parseDouble(resultField.getText());

                    if (memoryId == 1) {
                        mem1 += result;
                        memoryTextField.setText(Double.toString(mem1));
                    }
//                    if (memoryId == 2) {
//                        mem2 += result;
//                        memoryTextField.setText(Double.toString(mem2));
//                    }
//                    if (memoryId == 3) {
//                        mem3 += result;
//                        memoryTextField.setText(Double.toString(mem3));
//                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка в формате записи числа с плавающей точкой", "" +
                                    "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        memoryTextField = new JTextField("0.0", 15);
        memoryTextField.setMaximumSize(memoryTextField.getPreferredSize());

        memoryResult.add(mc);
        memoryResult.add(Box.createHorizontalStrut(10));
        memoryResult.add(memoryTextField);
        memoryResult.add(Box.createHorizontalStrut(10));
        memoryResult.add(memoryPlus);
        memoryResult.add(Box.createHorizontalGlue());
        memoryResult.setBorder(BorderFactory.createLineBorder(Color.pink));

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(formulaType);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(data);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(resultArea);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(actions);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxMemoryType);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(memoryResult);
        contentBox.add(Box.createVerticalGlue());

        contentBox.setBorder(BorderFactory.createLineBorder(Color.white));

        getContentPane().add(contentBox, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

//            ImageIcon f1 = new ImageIcon(Objects.requireNonNull(Main.class.getResource("formula_1.png")));
//
//        ImageIcon f2 = new ImageIcon(Objects.requireNonNull(Main.class.getResource("formula_2.png")));
//            JLabel label1 = new JLabel(f1);
//        JLabel label2 = new JLabel(f2);
//            JFrame testFram1 = new JFrame();
//        JFrame testFram2 = new JFrame();
//        testFram1.setTitle("Formula 1 Image");
//        testFram2.setTitle("Formula 2 Image");
//            testFram1.add(label1);
//        testFram2.add(label2);
//            testFram1.pack();
//        testFram2.pack();
//            testFram1.setVisible(true);
//        testFram2.setVisible(true);


    }

}