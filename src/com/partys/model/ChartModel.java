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
		chart = ChartFactory.createBarChart3D("水果产量图", // 图表标题 
				"水果", // 目录轴的显示标签 
				"产量", // 数值轴的显示标签 
				dataset, // 数据集 
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直 
				true, // 是否显示图例(对于简单的柱状图必须是false)(用来解析每一种颜色代表什么) 
				false, // 是否生成工具 
				true // 是否生成URL链接 
				); 
		CategoryPlot plot = chart.getCategoryPlot(); 
		chart.setBackgroundPaint(null);
		chart.setBackgroundImageAlpha(0.0f);
		//设置网格背景颜色 
		plot.setBackgroundAlpha(0);
		//设置网格竖线颜色 
		plot.setDomainGridlinePaint(Color.pink); 
		//设置网格横线颜色 
		plot.setRangeGridlinePaint(Color.pink); 
		
		//显示每个柱的数值，并修改该数值的字体属性 
		BarRenderer3D renderer = new BarRenderer3D(); 
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
		renderer.setBaseItemLabelsVisible(true); 
		
		//默认的数字显示在柱子中，通过如下两句可调整数字的显示 
		//注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题 
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT)); 
		renderer.setItemLabelAnchorOffset(10D); 
		
		//设置每个地区所包含的平行柱的之间距离 
		renderer.setItemMargin(0.3); 
		plot.setRenderer(renderer); 
		//设置地区、销量的显示位置 
		//将下方的“肉类”放到上方 
//		plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT); 
		//将默认放在左边的“销量”放到右方 
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

			
		theme.setExtraLargeFont(new Font("宋体",Font.PLAIN,20));
		theme.setLargeFont(new Font("宋体",Font.PLAIN,14));
		theme.setRegularFont(new Font("宋体",Font.PLAIN,12));
		theme.setSmallFont(new Font("宋体",Font.PLAIN,10));
		ChartFactory.setChartTheme(theme);
	}

	private CategoryDataset getDataSet2() { 
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		dataset.addValue(900, "朝悦1", "2016"); 
		dataset.addValue(100, "朝悦2", "2016"); 
		dataset.addValue(100, "高碑店", "2016"); 
		dataset.addValue(500, "朝悦1", "2017"); 
		dataset.addValue(200, "朝悦2", "2017"); 
		dataset.addValue(2300, "高碑店", "2017"); 
		dataset.addValue(9100, "朝悦1", "2018"); 
		dataset.addValue(500, "朝悦2", "2018"); 
		dataset.addValue(300, "高碑店", "2018"); 
		dataset.addValue(9200, "朝悦1", "2019"); 
		dataset.addValue(1100, "朝悦2", "2019"); 
		dataset.addValue(1200, "高碑店", "2019"); 
		dataset.addValue(9200, "朝悦1", "2020"); 
		dataset.addValue(1100, "朝悦2", "2020"); 
		dataset.addValue(1200, "高碑店", "2020"); 
		dataset.addValue(9200, "朝悦1", "2021"); 
		dataset.addValue(1100, "朝悦2", "2021"); 
		dataset.addValue(1200, "高碑店", "2021"); 
		dataset.addValue(9200, "朝悦1", "2022"); 
		dataset.addValue(1100, "朝悦2", "2022"); 
		dataset.addValue(1200, "高碑店", "2022"); 

		return dataset; 
		} 
		 
}