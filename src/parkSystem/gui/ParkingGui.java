package parkSystem.gui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import parkSystem.*;

public class ParkingGui extends JFrame {
    private ParkingService parkingService = new ParkingService();
    private JTextField plateField;
    private JTextArea outputArea;

    public ParkingGui() {
        setTitle("Abant Otopark");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        

        
        JPanel inputRow = new JPanel(new BorderLayout(10, 10));
        inputRow.add(new JLabel("ARAÇ PLAKASI:"), BorderLayout.WEST);
        plateField = new JTextField();
        plateField.setFont(new Font("Monospaced", Font.BOLD, 18));
        inputRow.add(plateField, BorderLayout.CENTER);

        
        JPanel buttonRow = new JPanel(new GridLayout(1, 2, 20, 0));
        JButton btnEntry = new JButton("GİRİŞ YAP");
        JButton btnExit = new JButton("ÇIKIŞ YAP");
        
        
        btnEntry.setBackground(new Color(35, 161, 0));
        btnEntry.setForeground(Color.WHITE);
        btnExit.setBackground(new Color(231, 76, 60));
        btnExit.setForeground(Color.WHITE);

        buttonRow.add(btnEntry);
        buttonRow.add(btnExit);

        topPanel.add(inputRow);
        topPanel.add(buttonRow);

        
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(229, 200, 245));
        outputArea.setForeground(new Color(109, 0, 0));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        
        btnEntry.addActionListener(e -> handleEntry());
        btnExit.addActionListener(e -> handleExit());
    }

    private void handleEntry() {
        String plate = plateField.getText().trim().toUpperCase();
        if (plate.isEmpty()) {
            outputArea.append("[HATA] Lütfen plaka giriniz!\n");
            return;
        }

        
        Vehicle vehicle = parkingService.findVehicleTypeByPlate(plate);
        String typeStatus = (vehicle instanceof SubscriberVehicle) ? "[ABONE]" : "[ZİYARETÇİ]";
        
        String result = parkingService.vehicleEntry(vehicle);
        outputArea.append(typeStatus + " " + result + "\n");
        plateField.setText("");
    }

    private void handleExit() {
        String plate = plateField.getText().trim().toUpperCase();
        if (plate.isEmpty()) {
            outputArea.append("[HATA] Lütfen çıkış için plaka giriniz!\n");
            return;
        }

       
        Vehicle vehicle = parkingService.findVehicleTypeByPlate(plate);
        double fee = parkingService.vehicleExit(vehicle);

        if (fee >= 0) {
            String feeMsg = String.format("%.2f", fee);
            outputArea.append("[ÇIKIŞ] " + plate + " ayrıldı. Ücret: " + feeMsg + " TL\n");
        } else {
            outputArea.append("[HATA] Bu plaka ile içeride araç bulunamadı!\n");
        }
        plateField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ParkingGui().setVisible(true));
    }
}