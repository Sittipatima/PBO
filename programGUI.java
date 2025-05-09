package com.example.appjavafx;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class soal5 extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private final String[] columnNames = {"Kode MK", "Matakuliah", "SKS", "Nilai"};
    private File currentFile = null;

    public soal5() {
        super("CSV Editor - Nilai Mahasiswa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(null, columnNames);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnLoad = new JButton("Load CSV");
        JButton btnSave = new JButton("Save CSV");
        JButton btnAdd = new JButton("Add Row");
        JButton btnDelete = new JButton("Delete Row");

        JPanel panel = new JPanel();
        panel.add(btnLoad);
        panel.add(btnSave);
        panel.add(btnAdd);
        panel.add(btnDelete);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Button actions
        btnLoad.addActionListener(e -> loadCSV());
        btnSave.addActionListener(e -> saveCSV());
        btnAdd.addActionListener(e -> model.addRow(new Object[]{"", "", "", ""}));
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus.");
            }
        });
    }

    private void loadCSV() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            model.setRowCount(0); // Clear current data

            try (BufferedReader br = new BufferedReader(new FileReader(currentFile))) {
                String line;
                boolean isFirstLine = true;
                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false; // Skip header
                        continue;
                    }
                    String[] row = line.split(";");
                    model.addRow(row);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            }
        }
    }

    private void saveCSV() {
        if (currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
            } else {
                return;
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(currentFile))) {
            pw.println(String.join(";", columnNames));
            for (int i = 0; i < model.getRowCount(); i++) {
                Vector<?> row = model.getDataVector().get(i);
                pw.println(String.join(";", row.toString().replaceAll("^\\[|\\]$", "").split(", ")));
            }
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new soal5().setVisible(true));
    }
}

