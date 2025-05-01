package app;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Main extends JFrame {
    private JTextField txtName;
    private JTextField txtEmail;
    private JButton btnAdd;
    private JButton btnDelete;
    private JTable table;
    private DefaultTableModel tableModel;
    private cs dao;
    

    public Main() {
        dao = new cs();
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

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        btnAdd = new JButton("Tambah");
        formPanel.add(btnAdd);

        btnDelete = new JButton("Hapus");
        formPanel.add(btnDelete);

        add(formPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Email"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadTable();

        // Actions
        btnAdd.addActionListener(e -> {
            String name = txtName.getText();
            String email = txtEmail.getText();
            if (!name.isEmpty() && !email.isEmpty()) {
                dao.insertCustomer(new customer(name, email));
                txtName.setText("");
                txtEmail.setText("");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Isi semua field!");
            }
        });

        btnDelete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                String id = table.getValueAt(selected, 0).toString();
                dao.deleteCustomer(id);
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus.");
            }
        });
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        List<customer> customers = dao.getAllCustomers();
        for (customer c : customers) {
            tableModel.addRow(new Object[]{c.getId(), c.getName(), c.getEmail()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
