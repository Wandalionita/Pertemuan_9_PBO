package tugas_pertemuan_9;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NilaiMahasiswaApp extends JFrame {

    // Field Input
    private JTextField tfNIM, tfNama, tfAlamat, tfMataKuliah, tfNilai1, tfNilai2, tfNilai3, tfNilai4, tfNilai5;
    private JLabel lblNilaiAkhir;

    // Tabel Data
    private JTable table;
    private DefaultTableModel tableModel;

    public NilaiMahasiswaApp() {
        setTitle("Program Nilai Mahasiswa");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Panel Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(10, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Field Input
        tfNIM = new JTextField();
        tfNama = new JTextField();
        tfAlamat = new JTextField();
        tfMataKuliah = new JTextField();
        tfNilai1 = new JTextField();
        tfNilai2 = new JTextField();
        tfNilai3 = new JTextField();
        tfNilai4 = new JTextField();
        tfNilai5 = new JTextField();

        inputPanel.add(new JLabel("NIM:"));
        inputPanel.add(tfNIM);
        inputPanel.add(new JLabel("Nama:"));
        inputPanel.add(tfNama);
        inputPanel.add(new JLabel("Alamat:"));
        inputPanel.add(tfAlamat);
        inputPanel.add(new JLabel("Mata Kuliah:"));
        inputPanel.add(tfMataKuliah);
        inputPanel.add(new JLabel("Nilai 1 [10%]:"));
        inputPanel.add(tfNilai1);
        inputPanel.add(new JLabel("Nilai 2 [15%]:"));
        inputPanel.add(tfNilai2);
        inputPanel.add(new JLabel("Nilai 3 - UTS [25%]:"));
        inputPanel.add(tfNilai3);
        inputPanel.add(new JLabel("Nilai 4 [15%]:"));
        inputPanel.add(tfNilai4);
        inputPanel.add(new JLabel("Nilai 5 [35%]:"));
        inputPanel.add(tfNilai5);

        lblNilaiAkhir = new JLabel("Nilai Akhir: ");
        inputPanel.add(lblNilaiAkhir);
        inputPanel.add(new JLabel()); // Placeholder

        // Panel Button
        JPanel buttonPanel = new JPanel();
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        JButton btnSimpan = new JButton("Simpan");
        JButton btnHapus = new JButton("Hapus");
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnHapus);

        // Tabel Data
        String[] columnNames = {"NIM", "Nama", "Alamat", "Mata Kuliah", "Nilai Akhir"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        mainPanel.add(tableScrollPane, BorderLayout.SOUTH);

        // Event Listener Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanData();
            }
        });

        // Event Listener Hapus
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusData();
            }
        });
    }

    private void simpanData() {
        try {
            // Ambil data dari form
            String nim = tfNIM.getText();
            String nama = tfNama.getText();
            String alamat = tfAlamat.getText();
            String mataKuliah = tfMataKuliah.getText();

            if (nim.isEmpty() || nama.isEmpty() || alamat.isEmpty() || mataKuliah.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Harap isi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double nilai1 = Double.parseDouble(tfNilai1.getText()) * 0.1;
            double nilai2 = Double.parseDouble(tfNilai2.getText()) * 0.15;
            double nilai3 = Double.parseDouble(tfNilai3.getText()) * 0.25;
            double nilai4 = Double.parseDouble(tfNilai4.getText()) * 0.15;
            double nilai5 = Double.parseDouble(tfNilai5.getText()) * 0.35;

            // Hitung nilai akhir
            double nilaiAkhir = nilai1 + nilai2 + nilai3 + nilai4 + nilai5;
            lblNilaiAkhir.setText("Nilai Akhir: " + nilaiAkhir);

            // Tambahkan ke tabel
            tableModel.addRow(new Object[]{nim, nama, alamat, mataKuliah, nilaiAkhir});

            // Reset form
            resetForm();

            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapusData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void resetForm() {
        tfNIM.setText("");
        tfNama.setText("");
        tfAlamat.setText("");
        tfMataKuliah.setText("");
        tfNilai1.setText("");
        tfNilai2.setText("");
        tfNilai3.setText("");
        tfNilai4.setText("");
        tfNilai5.setText("");
        lblNilaiAkhir.setText("Nilai Akhir: ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NilaiMahasiswaApp().setVisible(true);
            }
        });
    }
}
