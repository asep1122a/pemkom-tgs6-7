package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Main extends JFrame {

    private JTextField txtName;
    private JTextField txtPhoneNumber; // Mengubah txtEmail menjadi txtPhoneNumber
    private JButton btnAdd;
    private JButton btnDelete;
    private JTable table;
    private DefaultTableModel tableModel;
    private CustomerService customerService; // Mengubah dao menjadi customerService

    public Main() {
        customerService = new CustomerService();
        setTitle("Customer Manager");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.setBorder(BorderFactory.createTitledBorder("Tambah Customer"));

        formPanel.add(new JLabel("Nama:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Nomor HP:")); // Mengubah label menjadi Nomor HP
        txtPhoneNumber = new JTextField(); // Mengubah txtEmail menjadi txtPhoneNumber
        formPanel.add(txtPhoneNumber);

        btnAdd = new JButton("Tambah");
        formPanel.add(btnAdd);

        btnDelete = new JButton("Hapus");
        formPanel.add(btnDelete);

        add(formPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Nomor HP"}, 0); // Mengubah header tabel
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadTable();

        // Actions
        btnAdd.addActionListener(e -> {
            String name = txtName.getText();
            String phoneNumber = txtPhoneNumber.getText(); // Mengubah email menjadi phoneNumber
            if (!name.isEmpty() && !phoneNumber.isEmpty()) {
                customerService.addCustomer(new Customer(name, phoneNumber)); // Mengubah email menjadi phoneNumber
                txtName.setText("");
                txtPhoneNumber.setText(""); // Mengubah email menjadi phoneNumber
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Isi semua field!");
            }
        });

        btnDelete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                String id = table.getValueAt(selected, 0).toString();
                customerService.removeCustomer(id); // Mengubah deleteCustomer menjadi removeCustomer
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus.");
            }
        });
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        List<Customer> customers = customerService.fetchAllCustomers(); // Mengubah getAllCustomers menjadi fetchAllCustomers
        for (Customer c : customers) {
            tableModel.addRow(new Object[]{c.getId(), c.getName(), c.getPhoneNumber()}); // Mengubah email menjadi phoneNumber
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
