/**
 * This program encrypt and decrypt text using rail fence.
 *
 * @author (Ikhwan, Hidayat, Andritz, Faiz)
 * @version (190323)
 */
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class RailFence
{
    public static int key = 3;
    
    public static void main(String[] args)
    {
        /* For testing only
        String txt = "attack";
        String encrypted, decrypted;
        
        System.out.println("Plain text: " + txt);
        
        encrypted = RailFence.encrypt(txt, key);
        System.out.println("Encrypted: " + encrypted);
        
        decrypted = RailFence.decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
        */
        
        // Declare outermost frame
        JFrame outermost = new JFrame("Rail fence");
        
        // Declare panels to be used
        JPanel mainPanel = new JPanel();
        JPanel keyPanel = new JPanel();
        JPanel textPanel = new JPanel();
        JPanel textPanelUp = new JPanel();
        JPanel textPanelDown = new JPanel();
        JPanel buttonPanel = new JPanel();
        
        // Declare all buttons
        JButton encryptButton = new JButton("v ENCRYPT v");
        JButton resetButton = new JButton("RESET");
        JButton decryptButton = new JButton("^ DECRYPT ^");
        JButton keyButton = new JButton("SET KEY");
        
        // Set positioning for panels - X_AXIS for horizontal, Y_AXIS for vertical
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        keyPanel.setLayout(new BoxLayout(keyPanel, BoxLayout.Y_AXIS));
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanelUp.setLayout(new BoxLayout(textPanelUp, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        textPanelDown.setLayout(new BoxLayout(textPanelDown, BoxLayout.Y_AXIS));
        
        // Key panel
        JTextField keytext = new JTextField(3);                  // Declare key text field
        keytext.setText("3");                                    // Default to 3
        keyPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Padding
        keyPanel.add(new JLabel("KEY"));                         // Add label for key into panel
        keyPanel.add(new JLabel("(2-100)"));                     // Add label for key into panel
        keyPanel.add(keytext);                                   // Add text field for key input
        keyPanel.add(keyButton);                                 // Add button for key input
        keyPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Padding
        
        // Text panel (up)
        JTextField plaintext = new JTextField(50);                  // Declare plain text field
        textPanelUp.add(Box.createRigidArea(new Dimension(0, 20))); // Padding
        textPanelUp.add(new JLabel("PLAIN TEXT"));                  // Add label for plain text panel
        textPanelUp.add(plaintext);                                 // Add text field for plain text input/output
        
        // Button panel
        buttonPanel.add(encryptButton);                             // Add button for encrypt action
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Padding
        buttonPanel.add(resetButton);                               // Add button for reset field action
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Padding
        buttonPanel.add(decryptButton);                             // Add button for decrypt action
        
        // Text panel (down)
        JTextField ciphertext = new JTextField(50);                   // Declare plain text field
        textPanelDown.add(ciphertext);                                // Add text field for cipher text input/output
        textPanelDown.add(new JLabel("CIPHER TEXT"));                 // Add label for cipher text panel
        textPanelDown.add(Box.createRigidArea(new Dimension(0, 20))); // Padding
        
        // Text panel
        textPanel.add(textPanelUp);   // Put text pannel (up) into text panel
        textPanel.add(buttonPanel);   // Put button panel into text panel
        textPanel.add(textPanelDown); // Put text panel (down) into text panel
        
        // Main panel
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Padding
        mainPanel.add(textPanel);                                 // Put text panel into main panel
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Padding
        mainPanel.add(keyPanel);                                  // Put key panel into main panel
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Padding
        
        // Set action of key button
        keyButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(keytext.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "You need to input the key!", "Error", JOptionPane.ERROR_MESSAGE);
                else
                {
                    int temp = key;
                    try
                    {
                        temp = Integer.parseInt(keytext.getText());
                        if(temp>1 && temp<101)
                        {
                            key = temp;
                            JOptionPane.showMessageDialog(null, "Key has been changed to "+key+".", "Success", JOptionPane.PLAIN_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Value must be between 2 and 100!", "Error", JOptionPane.ERROR_MESSAGE);
                    }catch(NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null, "The value entered ("+keytext.getText()+") is not a whole number!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        // Set action of encrypt button
        encryptButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(plaintext.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "You need to input the plain text!", "Error", JOptionPane.ERROR_MESSAGE);
                else
                    ciphertext.setText(RailFence.encrypt(plaintext.getText().replaceAll(" ",""), key));
            }
        });
        
        // Set action of reset button
        resetButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                plaintext.setText("");
                ciphertext.setText("");
            }
        });
        
        // Set action of decrypt button
        decryptButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(ciphertext.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "You need to input the cipher text!", "Error", JOptionPane.ERROR_MESSAGE);
                else
                    plaintext.setText(RailFence.decrypt(ciphertext.getText().replaceAll(" ",""), key));
            }
        });
        
        // Put all together
        outermost.add(new JLabel("Rail fence encrypt and decrypt"),BorderLayout.PAGE_START);
        outermost.add(mainPanel, BorderLayout.CENTER);
        outermost.add(new JLabel("<html>Algorithm by Faiz Ikhwan, GUI coding by Muhammad Nur Hidayat<br/>Idea, testing and report by Andritz Loxley and Muhamad Faiz</html>"),BorderLayout.PAGE_END);
        outermost.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        outermost.setSize(500,250);
        outermost.setLocationRelativeTo(null);
        outermost.setVisible(true);
    }
    
    public static String encrypt(String txt, int key)
    {
        String res = "";
        int row = key;
        int column = txt.length();
        char[][] matrix = new char[row][column];
        boolean direction = false;
        // false = upward
        // true = downward

        int j = 0;
        for(int i=0; i<column; i++) //matrix visiting in column order and putting the character of plaintext
        {
            if(j==0||j==row-1) // at edge
                direction=!direction;

            matrix[j][i]=txt.charAt(i);

            if(direction)
                j++; // downward
            else
                j--; // upward
        }

        // read based on row
        for(int i=0; i<row; i++)
            for(j=0; j<column; j++)
                if(Character.isLetterOrDigit(matrix[i][j]))
                    res += matrix[i][j];

        return res;
    }

    public static String decrypt(String txt, int key)
    {
        String res = "";
        boolean direction = false;
        int j=0;
        int row = key;
        int column = txt.length();
        char[][] matrix = new char[row][column];

        //first of all mark the rails position by * in the matrix
        for(int i=0;i<column;i++)
        {
            if(j==0||j==row-1)
                direction=!direction;

            matrix[j][i]='*';

            if(direction)
                j++;
            else
                j--;

        }

        //now enter the character of cipheetext in the matrix positon that have * symbol
        int index = 0;

        for(int i=0;i<row;i++)
            for(int k=0;k<column;k++)
                if(matrix[i][k]=='*'&&index<txt.length())
                    matrix[i][k]=txt.charAt(index++);


        direction = false;
        j = 0;

        for(int i=0;i<column;i++)
        {
            if( j==0||j==row-1)
                direction=!direction;


            res += matrix[j][i];


            if(direction)
                j++;
            else
                j--;

        }

        return res;
    }

    public static void print(char[][] matrix)
    {
        System.out.println(matrix[0][0]);
        System.out.print(" ");
        for(int j=0; j<matrix[0].length; j++)
            System.out.print("- ");
        System.out.println();

        for(int i=0; i<matrix.length; i++)
        {
            System.out.print("|");

            for(int j=0; j<matrix[i].length; j++)
            {
                if(Character.isLetterOrDigit(matrix[i][j]))
                    System.out.print(matrix[i][j] + " ");
                else
                    System.out.print("  ");
            }
            System.out.print("|");
            System.out.println();
        }

        System.out.print(" ");
        for(int j=0; j<matrix[0].length; j++)
            System.out.print("- ");
        System.out.println();
    }

}
