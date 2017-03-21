package Alexandr_Nikitchenko;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/*
Контролируем массу тела
Программа должна считывать введенные пользователем вес в килограммах и рост в сантиметрах и выводить на экран сообщение о
индексе массы тела.
Реализуй метод класса BodyMass. Метод должен определить индекс массы тела, и вывести на экран сообщение:
«Недовес: меньше чем 18.5» — если индекс массы тела меньше 18.5,
«Нормальный: между 18.5 и 24.9» — если индекс массы тела между 18.5 и 24.9,
«Избыточный вес: между 25 и 29.9» — если индекс массы тела между 25 и 29.9,
«Ожирение: 30 или больше» — если индекс массы тела 30 или больше.
 */

public class BodyMass extends JFrame implements ActionListener {

    // Переменные
    private JPanel contentNorth, contentCenter, contentSouth;
    private JLabel textWeight, textHeight, textAnswer;
    private JButton calculate, reset;
    private int widtgh = 310, height = 260;
    private int displayValueWeight = 0;
    private int displayValueHeight = 0;
    private JTextField dispWeight, dispHeight;


    BodyMass() throws ParseException {
        super("Персональный диетолог");

        // Формируем окно
        setSize(widtgh, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Добавляем в окно панель contentNorth
        contentNorth = new JPanel(new GridLayout(2,2, 2,2));
        contentNorth.setBorder(new EmptyBorder(5,5,5,5));

        // Добавляем на панель текстовое поле вес
        textWeight = new JLabel("Введите свой вес в кг.: ");
        contentNorth.add(textWeight);

        // Добавляем поле ввода вес
        dispWeight = new JTextField();
        dispWeight.setColumns(4);
        contentNorth.add(dispWeight);

        // Добавляем на панель текстовое поле роста
        textHeight = new JLabel("Введите свой рост в cм.: ");
        contentNorth.add(textHeight);

        // Добавляем поле ввода рост
        dispHeight = new JTextField();
        dispHeight.setColumns(4);
        contentNorth.add(dispHeight);

        // Добавляем в окно панель contentCenter
        contentCenter = new JPanel(new GridLayout(1,1, 2, 2));
        contentCenter.setBorder(new EmptyBorder(5,5,5,5));

        // Добавляем на панель текстовое поле
        textAnswer = new JLabel();
        textAnswer.setHorizontalAlignment(SwingConstants.CENTER);
        textAnswer.setText("");
        contentCenter.add(textAnswer);

        // Добавляем в окно панель contentSouth
        contentSouth = new JPanel(new GridLayout(1,2));
        contentSouth.setBorder(new EmptyBorder(5,5,5,5));

        // Добавляем на панель кнопки
        calculate = new JButton("Расчитать");
        calculate.addActionListener(this);
        contentSouth.add(calculate);

        reset = new JButton("Сброс");
        reset.addActionListener(this);
        contentSouth.add(reset);

        this.add(contentNorth, "North");
        this.add(contentCenter, "Center");
        this.add(contentSouth, "South");
        setVisible(true);
    }

    public static void main(String[] args) throws ParseException {
	new BodyMass();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Обрабатываем данные введеные в поле ввода веса
        try {
            String displaytext = dispWeight.getText();

            if (!"".equals(displaytext)) {
                displayValueWeight = Integer.parseInt(displaytext);

            }

            // Обрабатываем данные введеные в поле ввода роста
            String displaytext2 = dispHeight.getText();

            if (!"".equals(displaytext2)) {

                displayValueHeight = Integer.parseInt(displaytext2);
            }


            // Создаем источник действия
            Object theButton = e.getSource();

            // Обрабатываем дейстие кнопки "Расчитать"
            if (theButton == calculate) {

                // Расчитываем идекс массы относительно роста
                massIndex(displayValueWeight, displayValueHeight);
            }

            // Обрабатываем действия кнопки "Сброс"
            if (theButton == reset) {
                dispWeight.setText(null);
                dispHeight.setText(null);
                textAnswer.setText(null);
                displayValueHeight = 0;
                displayValueWeight = 0;
            }

        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Введите цифры!!!", "Ошибка!",
                    JOptionPane.ERROR_MESSAGE);
            dispWeight.setText(null);
            dispHeight.setText(null);
            textAnswer.setText(null);
        }
    }
    // Метод расчета индекса
    private void massIndex(int displayValueWeight, int displayValueHeight) {

            int displayValue = displayValueWeight * 10000;
            int s = displayValue / (displayValueHeight * displayValueHeight);

            if (s < 18.5) {
                textAnswer.setText("<html><p><h2 align = center color = red><b>Недовес! " +
                        "<h2 align = center color = red>Вам стоит набрать вес!");
            } else if (s > 18.5 && s < 24.9) {
                textAnswer.setText("<html><p><h2 align = center color = green><b>У вас все замечательно!");
            } else if (s >= 25 && s <= 29.9) {
                textAnswer.setText("<html><p><h2 align = center color = orange><b>Избыточный вес!" +
                        "<h2 align = center color = orange><b>Вам стоит " +
                        "сбросить пару лишних килограммов!");
            } else if (s > 30) {
                textAnswer.setText("<html><p><h2 align = center color = red><b>Ожирение!" +
                        "<h2 color = red>Немедленно садитесь на диету!");
            }

        }
    }


