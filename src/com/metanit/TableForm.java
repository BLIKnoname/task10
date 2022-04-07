package com.metanit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class TableForm extends JFrame implements ActionListener {
    private JButton solveButton;
    private JButton chooseFileButton;
    private JTextField n;
    private JTextField minS;
    private JButton changeSize;
    private JButton saveButton;
    private JLabel nLabel;
    private JLabel minSLabel;
    private JTable table;
    private JTextField rows;
    private JLabel rowLabel;
    private JScrollPane scrollPane;

    public TableForm() {
        this.setTitle("Таблица");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container formElementsContainer = getContentPane();
        formElementsContainer.setLayout(null);

        table = new JTable(1, 5);
        setHeader(table);
        scrollPane = new JScrollPane(table);
        scrollPane.setSize(600, 300);
        scrollPane.setLocation(0, 60);
        formElementsContainer.add(scrollPane);

        rows = new JTextField(5);
        rows.setSize(50, 50);
        rows.setLocation(120, 0);
        formElementsContainer.add(rows);

        rowLabel = new JLabel("кол-во квартир: ");
        rowLabel.setLabelFor(rows);
        rowLabel.setSize(120, 15);
        rowLabel.setLocation(0, 25);
        add(rowLabel);

        n = new JTextField(1);
        n.setSize(50, 50);
        n.setLocation(300, 0);
        formElementsContainer.add(n);

        nLabel = new JLabel("кол-во комнат: ");
        nLabel.setLabelFor(n);
        nLabel.setSize(120, 15);
        nLabel.setLocation(190, 25);
        add(nLabel);

        minS = new JTextField(1);
        minS.setSize(50, 50);
        minS.setLocation(510, 0);
        formElementsContainer.add(minS);

        minSLabel = new JLabel("минимальная площадь: ");
        minSLabel.setLabelFor(n);
        minSLabel.setSize(160, 15);
        minSLabel.setLocation(370, 25);
        add(minSLabel);

        solveButton = new JButton("Посчитать");
        solveButton.setSize(100, 50);
        solveButton.setLocation(0, 350);
        solveButton.addActionListener(this);
        formElementsContainer.add(solveButton);

        chooseFileButton = new JButton("Загрузить из файла");
        chooseFileButton.setSize(100, 50);
        chooseFileButton.setLocation(120, 350);
        chooseFileButton.addActionListener(this);
        formElementsContainer.add(chooseFileButton);

        changeSize = new JButton("Изменить размер");
        changeSize.setSize(100, 50);
        changeSize.setLocation(240, 350);
        changeSize.addActionListener(this);
        formElementsContainer.add(changeSize);

        saveButton = new JButton("Сохранить");
        saveButton.setSize(100, 50);
        saveButton.setLocation(340, 350);
        saveButton.addActionListener(this);
        formElementsContainer.add(saveButton);

        this.setSize(700, 700);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solveButton) {
            handleSolveButtonClick();
        } else if (e.getSource() == chooseFileButton) {
            loadDataFromFile();
        } else if (e.getSource() == changeSize) {
            setTableSize();
        } else if (e.getSource() == saveButton) {
            saveTable();
        }
    }

    private void saveTable() {
        JFileChooser fileChooser = new JFileChooser();
        int openStatus = fileChooser.showOpenDialog(null);

        if (openStatus == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            String path = inputFile.getAbsolutePath();
            List<Apartment> result = convertTable(table).getList();
            TaskManager.writeToOutputFile(path, result);
            displayMessage("Сохранено успешно");
        }
    }

    private void setTableSize() {
        try {
            int m = Integer.parseInt(rows.getText());
            if (m < 1) throw new NumberFormatException();
            remove(scrollPane);
            table = new JTable(m,5);
            setHeader(table);
            scrollPane = new JScrollPane(table);
            scrollPane.setSize(600, 300);
            scrollPane.setLocation(0, 60);
            add(scrollPane);
        } catch (NumberFormatException nfe) {
            displayError("введите данные корректно");
        }
    }
    private void setHeader(JTable table){
        table.getColumnModel().getColumn(0).setHeaderValue("район");
        table.getColumnModel().getColumn(1).setHeaderValue("площадь кухни");
        table.getColumnModel().getColumn(2).setHeaderValue("кол-во комнат");
        table.getColumnModel().getColumn(3).setHeaderValue("площадь");
        table.getColumnModel().getColumn(4).setHeaderValue("цена");
    }
    private void loadDataFromFile() {
        JFileChooser fileChooser = new JFileChooser();

        int openStatus = fileChooser.showOpenDialog(null);

        if (openStatus == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            String path = inputFile.getAbsolutePath();
            TripleArg result = TaskManager.parseInputFile(path);
            convertListToTable(result.getList());
            n.setText(String.valueOf(result.getN()));
            minS.setText(String.valueOf(result.getMinS()));
        }
    }

    private void convertListToTable(List<Apartment> result) {
        remove(scrollPane);
        table = new JTable(result.size(),5);
        setHeader(table);
        scrollPane = new JScrollPane(table);
        scrollPane.setSize(600, 300);
        scrollPane.setLocation(0, 60);
        add(scrollPane);
        if(!result.isEmpty()){
            for (int i = 0; i < result.size(); i++) {
                table.getModel().setValueAt(result.get(i).street,i,0);
                table.getModel().setValueAt(result.get(i).sKitchen,i,1);
                table.getModel().setValueAt(result.get(i).countOfRooms,i,2);
                table.getModel().setValueAt(result.get(i).s,i,3);
                table.getModel().setValueAt(result.get(i).price,i,4);
            }
        }
        else{
            displayMessage("Искомых квартир нет");
        }
    }

    private void handleSolveButtonClick() {
        try {
            TripleArg value = convertTable(table);
            List<Apartment> taskAnswer = Solution.findTheMostCheap(value.getList(), value.getN(), value.getMinS());
            convertListToTable(taskAnswer);
            displayMessage("Выполнено успешно");
        } catch (NumberFormatException exception) {
            displayError("Таблица должна быть заполнена корректно");
        }
    }

    private TripleArg convertTable(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel(); // берем модель таблицы

        List<Apartment> result = new ArrayList<>(tableModel.getRowCount());

        for (int r = 0; r < tableModel.getRowCount(); r++) {
            String currentStreet = String.valueOf(tableModel.getValueAt(r,0));
            double currentSKitchen = Double.parseDouble(String.valueOf(tableModel.getValueAt(r,1)));
            int currentCountOfRooms = Integer.parseInt(String.valueOf(tableModel.getValueAt(r,2)));
            double currentS = Double.parseDouble(String.valueOf(tableModel.getValueAt(r,3)));
            double currentPrice = Double.parseDouble(String.valueOf(tableModel.getValueAt(r,4)));

            Apartment currentApartment = new Apartment(currentStreet, currentSKitchen, currentCountOfRooms, currentS, currentPrice);
                result.add(currentApartment);
        }

        return new TripleArg(result,Integer.parseInt(n.getText()),Double.parseDouble(minS.getText()));
    }

    private void displayMessage(String messageText) {
        JOptionPane.showMessageDialog(this, messageText,
                "Сообщение", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayError(String errorText) {
        JOptionPane.showMessageDialog(this, errorText,
                "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
