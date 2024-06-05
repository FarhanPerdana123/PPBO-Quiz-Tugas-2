import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String[][] data = {
                {"001", "Harry Poter", "Joko", "Hardcore", "PT cipta jaya", "03-15-2023", "06-05-2024"},
                {"002", "Spongebob", "Stifen", "Comedy", "PT jayapura", "05-21-2022", "03-21-2023"},
                {"003", "Openheimer", "Albert Jungler", "Drama", "PT TulungAgung", "06-23-2021", "06-05-2024"}
        };

        String[] columnNames = {"ID", "Judul", "Penulis", "Genre", "Penerbit", "Tgl Terbit", "Tgl Tambah"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("Update");
        JButton insertButton = new JButton("Insert");
        JButton deleteButton = new JButton("Delete");
        JButton saveButton = new JButton("Save");
        buttonPanel.add(updateButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setTitle("Buku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);
        frame.setVisible(true);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String newID = JOptionPane.showInputDialog("Masukkan ID Baru:", model.getValueAt(selectedRow, 0));
                    String newJudul = JOptionPane.showInputDialog("Masukkan Judul Baru:", model.getValueAt(selectedRow, 1));
                    String newPenulis = JOptionPane.showInputDialog("Masukkan Penulis Baru:", model.getValueAt(selectedRow, 2));
                    String newGenre = JOptionPane.showInputDialog("Masukkan Genre Baru:", model.getValueAt(selectedRow, 3));
                    String newPenerbit = JOptionPane.showInputDialog("Masukkan Penerbit Baru:", model.getValueAt(selectedRow, 4));
                    String newTglTerbit = JOptionPane.showInputDialog("Masukkan Tanggal Terbit Baru (MM-DD-YYYY):", model.getValueAt(selectedRow, 5));
                    String newTglTambah = JOptionPane.showInputDialog("Masukkan Tanggal Tambah Baru (MM-DD-YYYY):", model.getValueAt(selectedRow, 6));

                    if (newID != null && newJudul != null && newPenulis != null && newGenre != null && newPenerbit != null && newTglTerbit != null && newTglTambah != null) {
                        model.setValueAt(newID, selectedRow, 0);
                        model.setValueAt(newJudul, selectedRow, 1);
                        model.setValueAt(newPenulis, selectedRow, 2);
                        model.setValueAt(newGenre, selectedRow, 3);
                        model.setValueAt(newPenerbit, selectedRow, 4);
                        model.setValueAt(newTglTerbit, selectedRow, 5);
                        model.setValueAt(newTglTambah, selectedRow, 6);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Pilih baris yang ingin diupdate", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(frame, "Pilih baris yang ingin dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newID = JOptionPane.showInputDialog("Masukkan ID Baru:" );
                String newJudul = JOptionPane.showInputDialog("Masukkan Judul Baru:" );
                String newPenulis = JOptionPane.showInputDialog("Masukkan Penulis Baru:" );
                String newGenre = JOptionPane.showInputDialog("Masukkan Genre Baru:" );
                String newPenerbit = JOptionPane.showInputDialog("Masukkan Penerbit Baru:" );
                String newTglTerbit = JOptionPane.showInputDialog("Masukkan Tanggal Terbit Baru (MM-DD-YYYY):" );
                String newTglTambah = JOptionPane.showInputDialog("Masukkan Tanggal Tambah Baru (MM-DD-YYYY):" );
                model.addRow(new String[]{newID, newJudul, newPenulis, newGenre, newPenerbit, newTglTerbit, newTglTambah});
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileWriter fw = new FileWriter("data.txt")) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        for (int j = 0; j < model.getColumnCount(); j++) {
                            fw.write(model.getValueAt(i, j).toString() + " ");
                        }
                        fw.write("\n");
                    }
                    JOptionPane.showMessageDialog(frame, "Data berhasil disimpan ke data.txt");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saat menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}