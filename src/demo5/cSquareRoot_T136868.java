package demo5;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * @author Quang Minh T136868
 *
 */
public class cSquareRoot_T136868 extends JFrame {

	private static JLabel lblnhap = new JLabel("Nhap so :");
	private static JLabel lblSaiSo = new JLabel("Chon sai so :");
	private static JTextArea txtAreaResult = new JTextArea();
	private static JButton btntinh = new JButton("Tinh"), btnxoa = new JButton(
			"Xoa"), btnxoahet = new JButton("Xoa het");
	private static JTextField txtInput = new JTextField();
	private static JScrollPane scrollResult = new JScrollPane(txtAreaResult);
	private static String[] sListNUmber = { "1", "2", "3", "4", "5", "6", "7",
			"8" };
	private static JComboBox cmbNumber = new JComboBox<String>(sListNUmber);
	private static int iSaiSo = 1;

	private static void setSaiSo(int SaiSo) {
		iSaiSo = SaiSo;
	}

	private static int getSaiSo() {
		return iSaiSo;
	}
	public cSquareRoot_T136868() {
		super("T136868 - Square Root");
		// set size and set layout null
		setSize(420, 450);
		setLayout(null);
		add(lblnhap);
		add(txtInput);
		add(btntinh);
		add(btnxoa);

		add(lblSaiSo);
		add(btnxoahet);
		add(cmbNumber);
		cmbNumber.setSelectedIndex(1);
		setSaiSo(2);

		add(scrollResult);
		lblnhap.setBounds(10, 30, 70, 25);
		txtInput.setBounds(100, 30, 290, 25);

		lblSaiSo.setBounds(150, 75, 100, 25);
		cmbNumber.setBounds(240, 75, 150, 25);

		btntinh.setBounds(75, 140, 90, 25);
		btnxoa.setBounds(185, 140, 90, 25);
		btnxoahet.setBounds(295, 140, 90, 25);

		scrollResult.setBounds(10, 185, 380, 200);

		btntinh.addActionListener(actionButton);
		btnxoa.addActionListener(actionButton);
		btnxoahet.addActionListener(actionButton);
		cmbNumber.addActionListener(actionCombobox);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cSquareRoot_T136868 Main = new cSquareRoot_T136868();
		Main.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Main.setVisible(true);
	}

	public static boolean isInput(String sValue) {
		return isStringIsDoubleNumber(sValue);
	}
	public static boolean isStringIsDoubleNumber(String str) {
		int endFor = str.length() - 1;
		for (int i = 0; i <= endFor; i++) {
			if (!isStringHaveInString("0123456789.", str.substring(i, i + 1)))
				return false;
		}
		int iPoint = 0;
		for (int i = 0; i <= endFor; i++) {
			if (str.substring(i, i + 1).equals(".")) {
				iPoint++;
			}
		}
		if (iPoint <= 1)
			return true;
		else
			return false;
	}

	public static boolean isStringHaveInString(String sString, String sValue) {
		int iEndFor = sString.length() - 1;
		for (int i = 0; i <= iEndFor; i++) {
			if (sString.substring(i, i + 1).equals(sValue))
				return true;
		}
		return false;
	}

	public static double sqrt(double iValue, int iSaiSo) {
		double dSqrtBefore = 10 - (10 * 10 - iValue) / (2 * 10);
		double dSqrt = dSqrtBefore - (dSqrtBefore * dSqrtBefore - iValue)
				/ (2 * dSqrtBefore);
		double dE = pow(10, -iSaiSo);
		while (abs(dSqrt - dSqrtBefore) > dE) {
			dSqrtBefore = dSqrt;
			dSqrt = dSqrtBefore - (dSqrtBefore * dSqrtBefore - iValue)
					/ (2 * dSqrtBefore);
		}
		return dSqrt;
	}
	public static double abs(double number) {
		if (number >= 0)
			return number;
		else
			return -number;
	}

	public static double pow(double b, int a) {
		if (a == 0)
			return 1;
		double dR = b;
		if (a > 1) {
			for (int i = 2; i <= a; i++) {
				dR *= b;
			}
		} else if (a <= -1) {
			dR = 1 / b;
			for (int i = 2; i <= -a; i++) {
				dR /= b;
			}
		}
		return dR;
	}

	private static ActionListener actionButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btnxoa) {
				txtInput.setText("");
			} else if (e.getSource() == btnxoahet) {
				txtInput.setText("");
				txtAreaResult.setText("");
				cmbNumber.setSelectedIndex(1);
				setSaiSo(2);
			} else if (e.getSource() == btntinh) {
				String sValue = txtInput.getText();
				String sResult = "";
				if (sValue.equals(""))
					return;
				if (isInput(sValue)) {
					sResult = computer(sValue,
							"" + sqrt(Double.parseDouble(sValue), getSaiSo()));
				} else {
					sResult = "Invalid Input";
				}
				txtAreaResult.append(sResult + "\n");
			}
		}
	};

	public static String computer(String sValue, String sResult) {
		return "Sqrt(" + sValue + ")=" + sResult;
	}

	private static ActionListener actionCombobox = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cmbNumber) {
				setSaiSo(cmbNumber.getSelectedIndex() + 1);
			}
		}
	};
}
