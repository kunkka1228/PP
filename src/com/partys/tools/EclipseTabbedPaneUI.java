package com.partys.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class EclipseTabbedPaneUI extends BasicTabbedPaneUI {

	private static final Color SELECTED_TAB_COLOR = new Color(10, 36, 106);
	private static final int TAB_MINIMUM_SIZE = 8;

	@Override
	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
			int x, int y, int w, int h, boolean isSelected) {
		g.setColor(Color.GRAY);
		if (tabPlacement == BOTTOM) {
			g.drawLine(x, y + h, x + w, y + h);
		}

		// right
		g.drawLine(x + w - 1, y, x + w - 1, y + h);

		if (tabPlacement == TOP) {
			// And a white line to the left and top
			g.setColor(Color.WHITE);
			g.drawLine(x, y, x, y + h);
			g.drawLine(x, y, x + w - 2, y);
		}

		if (tabPlacement == BOTTOM && isSelected) {
			g.setColor(Color.WHITE);
			// Top
			g.drawLine(x + 1, y + 1, x + 1, y + h);
			// Right
			g.drawLine(x + w - 2, y, x + w - 2, y + h);
			// Left
			g.drawLine(x + 1, y + 1, x + w - 2, y + 1);
			// Bottom
			g.drawLine(x + 1, y + h - 1, x + w - 2, y + h - 1);
		}
	}


	/**
	 * Give selected tab blue color with a gradient!!.
	 * 
	 * FIXME: with Plastic L&F the unselected background is too dark
	 */
	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement,
			int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		Color color = UIManager.getColor("control");
		if (isSelected) {
			if (tabPlacement == TOP) {
				Graphics2D g2 = (Graphics2D) g;
				Paint storedPaint = g2.getPaint();
				g2.setPaint(new GradientPaint(x, y, SELECTED_TAB_COLOR, x + w,
						y + h, color));
				g2.fillRect(x, y, w, h);
				g2.setPaint(storedPaint);
			}
		} else {
			g.setColor(color);
			g.fillRect(x, y, w - 1, h);
		}
	}

	/**
	 * Do not paint a focus indicator.
	 */
	@Override
	protected void paintFocusIndicator(Graphics arg0, int arg1,
			Rectangle[] arg2, int arg3, Rectangle arg4, Rectangle arg5,
			boolean arg6) {
		// Leave it
	}

	/**
	 * We do not want the tab to "lift up" when it is selected.
	 */
	@Override
	protected void installDefaults() {
		super.installDefaults();
		tabAreaInsets = new Insets(0, 0, 0, 0);
		selectedTabPadInsets = new Insets(0, 0, 0, 0);
		contentBorderInsets = new Insets(1, 0, 0, 0);
	}

	/**
	 * Nor do we want the label to move.
	 */
	@Override
	protected int getTabLabelShiftY(int tabPlacement, int tabIndex,
			boolean isSelected) {
		return 0;
	}

	/**
	 * Increase the tab height a bit
	 */
	@Override
	protected int calculateTabHeight(int tabPlacement, int tabIndex,
			int fontHeight) {
		return fontHeight + 10;
	}


	/**
	 * Selected labels have a white color.
	 */
	@Override
	protected void paintText(Graphics g, int tabPlacement, Font font,
			FontMetrics metrics, int tabIndex, String title,
			Rectangle textRect, boolean isSelected) {
		if (isSelected && tabPlacement == TOP) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.BLACK);
		}
		if (title.length() > TAB_MINIMUM_SIZE) {
			title = "..."
					+ title.substring(title.length() - TAB_MINIMUM_SIZE + 3,
							title.length());
			textRect.x += 14;
		}
		g.drawString(title, textRect.x+2, textRect.y + metrics.getAscent());
	}

	@Override
	protected int calculateTabWidth(int tabPlacement, int tabIndex,
			FontMetrics metrics) {
		int taille = 0;
		String title = tabPane.getTitleAt(tabIndex);

		if (title.length() > TAB_MINIMUM_SIZE) {
			taille = SwingUtilities.computeStringWidth(
					metrics,
					(title.substring(title.length() - TAB_MINIMUM_SIZE,
							title.length()))) + 3;
		} else {
			taille = super.calculateTabWidth(tabPlacement, tabIndex, metrics);
		}

		return taille;
	}

//	@Override
	protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		if (selectedIndex != -1 && tabPlacement == TOP) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(x, y, x + w, y);
		}
	}

	@Override
	protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		g.setColor(Color.GRAY);
		g.drawLine(x, y + h, x + w, y + h);
	}

	@Override
	protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		
	}

	@Override
	protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		
	}



}