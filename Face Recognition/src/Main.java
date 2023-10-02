import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Main extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Open");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				JFileChooser fileChooser = new JFileChooser();
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		          File selectedFile = fileChooser.getSelectedFile();
		          String path = selectedFile.getAbsolutePath().replaceAll("\\\\", "/");
		          
		       
		          if(!path.split("/")[4].endsWith(".jpg")) {
		        	  JOptionPane.showMessageDialog(null, "Not compatible format!");
		        	  return;
		          }
		          
		          Image img = FaceRecognition.readFace(path);
		          displayImage(img);
		          
		        }
			}
		});
		btnNewButton.setBounds(148, 160, 100, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("FACE RECOGNITION");
		lblNewLabel.setFont(new Font("Poppins Light", Font.ITALIC, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 22, 364, 127);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CHOOSE FILE TO OPEN");
		lblNewLabel_1.setFont(new Font("Poppins Light", Font.ITALIC, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(92, 144, 206, 14);
		contentPane.add(lblNewLabel_1);
	}
	
	
	public static void displayImage(Image img) {
        try {
            JFrame frame = new JFrame("Image Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel imageLabel = new JLabel(new ImageIcon(img));
            frame.getContentPane().add(imageLabel, BorderLayout.CENTER);

            int width = img.getWidth(null);
            int height = img.getHeight(null);
            JLabel dimensionLabel = new JLabel("Width: " + width + " px, Height: " + height + " px");
            frame.getContentPane().add(dimensionLabel, BorderLayout.SOUTH);

            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        	}
    	}
	}