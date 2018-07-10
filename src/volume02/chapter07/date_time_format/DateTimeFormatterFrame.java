package volume02.chapter07.date_time_format;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import volume02.chapter07.util.EnumCombo;
import volume02.chapter07.util.GBC;

/**
 * display formatted date time based on locale
 * @author mhts
 * @date 2018Äê7ÔÂ10ÈÕ
 */
public class DateTimeFormatterFrame extends JFrame {
	private static final long serialVersionUID = -6059622114555808655L;
	private Locale[] locales;
	private LocalDate currentDate;
	private LocalTime currentTime;
	private ZonedDateTime currentDateTime;
	private DateTimeFormatter currentDateFormatter;
	private DateTimeFormatter currentTimeFormatter;
	private DateTimeFormatter currentDateTimeFormatter;
	private JComboBox<String> localeCombo = new JComboBox<>();
	private JButton dateParseButton = new JButton("Parse");
	private JButton timeParseButton = new JButton("Parse");
	private JButton dateTimeParseButton = new JButton("Parse");
	private JTextField dateText = new JTextField(30);
	private JTextField timeText = new JTextField(30);
	private JTextField dateTimeText = new JTextField(30);
	private EnumCombo<FormatStyle> dateStyleCombo = new EnumCombo<>(
			FormatStyle.class, "Short", "Medium", "Long", "Full");
	private EnumCombo<FormatStyle> timeStyleCombo = new EnumCombo<>(
			FormatStyle.class, "Short", "Medium");
	private EnumCombo<FormatStyle> dateTimeStyleCombo = new EnumCombo<>(
			FormatStyle.class, "Short", "Medium", "Long", "Full");
	
	public DateTimeFormatterFrame() {
		setLayout(new GridBagLayout());
		add(new JLabel("Locale"), new GBC(0, 0).setAnchor(GBC.EAST));
		add(localeCombo, new GBC(1, 0, 2, 1).setAnchor(GBC.WEST));
		
		add(new JLabel("Date"), new GBC(0, 1).setAnchor(GBC.EAST));
		add(dateStyleCombo, new GBC(1, 1).setAnchor(GBC.WEST));
		add(dateText, new GBC(2, 1, 2, 1).setFill(GBC.HORIZONTAL));
		add(dateParseButton, new GBC(4, 1).setAnchor(GBC.WEST));
		
		add(new JLabel("Time"), new GBC(0, 2).setAnchor(GBC.EAST));
		add(timeStyleCombo, new GBC(1, 2).setAnchor(GBC.WEST));
		add(timeText, new GBC(2, 2, 2, 1).setFill(GBC.HORIZONTAL));
		add(timeParseButton, new GBC(4, 2).setAnchor(GBC.WEST));
		
		add(new JLabel("Date and Time"), new GBC(0, 3).setAnchor(GBC.EAST));
		add(dateTimeStyleCombo, new GBC(1, 3).setAnchor(GBC.WEST));
		add(dateTimeText, new GBC(2, 3, 2, 1).setFill(GBC.HORIZONTAL));
		add(dateTimeParseButton, new GBC(4, 3).setAnchor(GBC.WEST));
		
		locales = Locale.getAvailableLocales().clone();
		Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
		for (Locale loc : locales) {
			localeCombo.addItem(loc.getDisplayName());
		}
		localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
		currentDate = LocalDate.now();
		currentTime = LocalTime.now();
		currentDateTime = ZonedDateTime.now();
		updateDisplay();
		
		ActionListener listener = event -> updateDisplay();
		
		localeCombo.addActionListener(listener);
		dateStyleCombo.addActionListener(listener);
		timeStyleCombo.addActionListener(listener);
		dateTimeStyleCombo.addActionListener(listener);
		
		dateParseButton.addActionListener(event -> {
			String d = dateText.getText().trim();
			try {
				currentDate = LocalDate.parse(d, currentDateFormatter);
				updateDisplay();
			} catch (Exception e) {
				dateText.setText(e.getMessage());
			}
		});
		
		timeParseButton.addActionListener(event -> {
			String d = timeText.getText().trim();
			try {
				currentTime = LocalTime.parse(d, currentTimeFormatter);
				updateDisplay();
			} catch (Exception e) {
				dateText.setText(e.getMessage());
			}
		});
		
		dateTimeParseButton.addActionListener(event -> {
			String d = dateTimeText.getText().trim();
			try {
				currentDateTime = ZonedDateTime.parse(d, currentDateTimeFormatter);
				updateDisplay();
			} catch (Exception e) {
				dateText.setText(e.getMessage());
			}
		});
		
		pack();
	}
	
	public void updateDisplay() {
		Locale currentLocale = locales[localeCombo.getSelectedIndex()];
		FormatStyle dateStyle = dateStyleCombo.getValue();
		FormatStyle timeStyle = timeStyleCombo.getValue();
		FormatStyle dateTimeStyle = dateTimeStyleCombo.getValue();
		currentDateFormatter = DateTimeFormatter.ofLocalizedDate(dateStyle).withLocale(currentLocale);
		currentTimeFormatter = DateTimeFormatter.ofLocalizedTime(timeStyle).withLocale(currentLocale);
		currentDateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(dateTimeStyle).withLocale(currentLocale);
		dateText.setText(currentDateFormatter.format(currentDate));
		timeText.setText(currentTimeFormatter.format(currentTime));
		dateTimeText.setText(currentDateTimeFormatter.format(currentDateTime));
	}
}
