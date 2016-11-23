package com.partys.model;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
public class ChartModel{
	private JFreeChart chart;
	
	public JFreeChart getJFreeChart(){
		return chart;
	}
	public ChartModel(){
		setFont();
		iniBarChart3D();
	}
	
	
	
	
	public void iniBarChart3D(){
		CategoryDataset  dataset=getDataSet2(); 
		chart = ChartFactory.createBarChart3D("ˮ������ͼ", // ͼ����� 
				"ˮ��", // Ŀ¼�����ʾ��ǩ 
				"����", // ��ֵ�����ʾ��ǩ 
				dataset, // ���ݼ� 
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ 
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)(��������ÿһ����ɫ����ʲô) 
				false, // �Ƿ����ɹ��� 
				true // �Ƿ�����URL���� 
				); 
		CategoryPlot plot = chart.getCategoryPlot(); 
		chart.setBackgroundPaint(null);
		chart.setBackgroundImageAlpha(0.0f);
		//�������񱳾���ɫ 
		plot.setBackgroundAlpha(0);
		//��������������ɫ 
		plot.setDomainGridlinePaint(Color.pink); 
		//�������������ɫ 
		plot.setRangeGridlinePaint(Color.pink); 
		
		//��ʾÿ��������ֵ�����޸ĸ���ֵ���������� 
		BarRenderer3D renderer = new BarRenderer3D(); 
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
		renderer.setBaseItemLabelsVisible(true); 
		
		//Ĭ�ϵ�������ʾ�������У�ͨ����������ɵ������ֵ���ʾ 
		//ע�⣺�˾�ܹؼ������޴˾䣬�����ֵ���ʾ�ᱻ���ǣ���������û����ʾ���������� 
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT)); 
		renderer.setItemLabelAnchorOffset(10D); 
		
		//����ÿ��������������ƽ������֮����� 
		renderer.setItemMargin(0.3); 
		plot.setRenderer(renderer); 
		//���õ�������������ʾλ�� 
		//���·��ġ����ࡱ�ŵ��Ϸ� 
//		plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT); 
		//��Ĭ�Ϸ�����ߵġ��������ŵ��ҷ� 
//		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT); 
	}
	
	private void setFont(){
		StandardChartTheme theme = new StandardChartTheme("gbk"){

			/**
			 * 
			 */
			private static final long serialVersionUID = 2828579953641053643L;
			/**
			 * 
			 */
			public void apply(JFreeChart chart){
				chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				super.apply(chart);
			}		
		};

			
		theme.setExtraLargeFont(new Font("����",Font.PLAIN,20));
		theme.setLargeFont(new Font("����",Font.PLAIN,14));
		theme.setRegularFont(new Font("����",Font.PLAIN,12));
		theme.setSmallFont(new Font("����",Font.PLAIN,10));
		ChartFactory.setChartTheme(theme);
	}

	private CategoryDataset getDataSet2() { 
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		dataset.addValue(900, "����1", "2016"); 
		dataset.addValue(100, "����2", "2016"); 
		dataset.addValue(100, "�߱���", "2016"); 
		dataset.addValue(500, "����1", "2017"); 
		dataset.addValue(200, "����2", "2017"); 
		dataset.addValue(2300, "�߱���", "2017"); 
		dataset.addValue(9100, "����1", "2018"); 
		dataset.addValue(500, "����2", "2018"); 
		dataset.addValue(300, "�߱���", "2018"); 
		dataset.addValue(9200, "����1", "2019"); 
		dataset.addValue(1100, "����2", "2019"); 
		dataset.addValue(1200, "�߱���", "2019"); 
		dataset.addValue(9200, "����1", "2020"); 
		dataset.addValue(1100, "����2", "2020"); 
		dataset.addValue(1200, "�߱���", "2020"); 
		dataset.addValue(9200, "����1", "2021"); 
		dataset.addValue(1100, "����2", "2021"); 
		dataset.addValue(1200, "�߱���", "2021"); 
		dataset.addValue(9200, "����1", "2022"); 
		dataset.addValue(1100, "����2", "2022"); 
		dataset.addValue(1200, "�߱���", "2022"); 

		return dataset; 
		} 
		 
}