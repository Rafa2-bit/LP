/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author faelb
 */
public class SplashScreen extends JFrame {

    public SplashScreen() {
        setTitle("SGCA - Inicializando...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setUndecorated(true);

       
        Color backgroundColor = new Color(100, 100, 100); 
        Color textColor = new Color(220, 220, 220); 
        Color buttonColor = new Color(90, 90, 90); 
        Color buttonTextColor = new Color(200, 200, 200);
        Color borderColor = new Color(50,50,50);

        getContentPane().setBackground(backgroundColor);
        setLayout(new BorderLayout());
        getRootPane().setBorder(BorderFactory.createLineBorder(borderColor, 2));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;


        JLabel titleLabel = new JLabel("SISTEMA DE GERENCIAMENTO DE CURSOS E ALUNOS");
        titleLabel.setFont(new Font("Open Sans", Font.BOLD, 30)); 
        titleLabel.setForeground(textColor);
        mainPanel.add(titleLabel, gbc);

        
        JButton aluno = new JButton("Cadastro de Alunos");
        JButton curso = new JButton("Cadastrar Novo Curso");
        aluno.setFont(new Font("Open Sans", Font.PLAIN, 18));
        aluno.setBackground(buttonColor);
        aluno.setForeground(buttonTextColor);
        aluno.setFocusPainted(false);
        aluno.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor.brighter(), 0x2),
            BorderFactory.createEmptyBorder(10, 30, 10, 30) 
        ));
        curso.setFont(new Font("Open Sans", Font.PLAIN, 18));
        curso.setBackground(buttonColor);
        curso.setForeground(buttonTextColor);
        curso.setFocusPainted(false);
        curso.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor.brighter(), 2),
            BorderFactory.createEmptyBorder(10, 30, 10, 30) 
        ));
    
        aluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aluno.setBackground(buttonColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aluno.setBackground(buttonColor);
            }
        });
        
         curso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                curso.setBackground(buttonColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                curso.setBackground(buttonColor);
            }
        });

        aluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                
                SwingUtilities.invokeLater(() -> {
                    CadastroAluno alu = new CadastroAluno();
                    alu.setVisible(true);
                });
            }
        });
        
        curso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                
                SwingUtilities.invokeLater(() -> {
                    CadastroCurso cur = new CadastroCurso();
                    cur.setVisible(true);
                });
            }
        });
        gbc.insets = new Insets(20, 10, 10, 10); 
        mainPanel.add(aluno, gbc);
        mainPanel.add(curso);

        add(mainPanel, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
       
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        SwingUtilities.invokeLater(() -> {
            SplashScreen splash = new SplashScreen();
            splash.setVisible(true);
        });
    }
}

