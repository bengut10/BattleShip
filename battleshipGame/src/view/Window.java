package view;

/**
 * Interface Window allows for different types of views to overwrite the displayWindow Function
 * @RR2
 * @version 1.0
 *
 */
public interface Window {
	/**
	 * Abstract method for displaying a window to the user
	 * @param title - the title of the current window
	 */
	void displayWindow(String title);

}
