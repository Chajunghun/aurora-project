package aurora.presentation.component.std;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uncertain.composite.CompositeMap;
import aurora.presentation.BuildSession;
import aurora.presentation.ViewContext;
import aurora.presentation.component.std.config.ComponentConfig;
import aurora.presentation.component.std.config.EventConfig;

public class Chart extends Component {
	
	public static String INPUT_TYPE = "inputtype";
	public static String DEFAULT_INPUT_TYPE = "input";
	
	
	private static final String PROPERTITY_CHART_TYPE = "type";
	private static final String PROPERTITY_CHART_BORDERWIDTH = "borderWidth";//
	private static final String PROPERTITY_CHART_BORDERRADIUS = "borderRadius";//
	private static final String PROPERTITY_CHART_BORDERCOLOR = "borderColor";//
	private static final String PROPERTITY_CHART_SHADOW = "shadow";//
	
	private static final String PROPERTITY_LABELS = "labels";
	private static final String PROPERTITY_LABELS_STYLE = "style";
	private static final String PROPERTITY_LABELS_LABEL_HTML = "html";
	private static final String PROPERTITY_LABELS_LABEL_STYLE = "style";
	
	private static final String PROPERTITY_TITLE_TEXT = "text";
	private static final String PROPERTITY_TITLE_X = "x";
	private static final String PROPERTITY_TITLE_Y = "y";
	private static final String PROPERTITY_TITLE_ALIGN = "align";
	private static final String PROPERTITY_TITLE_MARGIN = "margin";
	private static final String PROPERTITY_TITLE_FLOATING = "floating";
	private static final String PROPERTITY_TITLE_STYLE = "style";
	private static final String PROPERTITY_TITLE_VERTICALALIGN = "verticalAlign";//
	
	private static final String PROPERTITY_SUBTITLE_TEXT = "text";
	private static final String PROPERTITY_SUBTITLE_X = "x";
	private static final String PROPERTITY_SUBTITLE_Y = "y";
	private static final String PROPERTITY_SUBTITLE_ALIGN = "align";
	private static final String PROPERTITY_SUBTITLE_FLOATING = "floating";
	private static final String PROPERTITY_SUBTITLE_STYLE = "style";
	private static final String PROPERTITY_SUBTITLE_VERTICALALIGN = "verticalAlign";//
	
	private static final String PROPERTITY_LEGEND_ALIGN = "align";
	private static final String PROPERTITY_LEGEND_BACKGROUNDCOLOR = "backgroundColor";
	private static final String PROPERTITY_LEGEND_BORDERCOLOR = "borderColor";
	private static final String PROPERTITY_LEGEND_BORDERRADIUS = "borderRadius";
	private static final String PROPERTITY_LEGEND_BORDERWIDTH = "borderWidth";
	private static final String PROPERTITY_LEGEND_FLOATING = "floating";
	private static final String PROPERTITY_LEGEND_ITEMSTYLE = "itemStyle";
	private static final String PROPERTITY_LEGEND_LAYOUT = "layout";
	private static final String PROPERTITY_LEGEND_LABELFORMATTER = "labelFormatter";
	private static final String PROPERTITY_LEGEND_MARGIN = "margin";
	private static final String PROPERTITY_LEGEND_REVERSED = "reversed";
	private static final String PROPERTITY_LEGEND_SHADOW = "shadow";
	private static final String PROPERTITY_LEGEND_STYLE = "style";
	private static final String PROPERTITY_LEGEND_SYMBOLPADDING = "symbolPadding";
	private static final String PROPERTITY_LEGEND_SYMBOLWIDTH = "symbolWidth";
	private static final String PROPERTITY_LEGEND_VERTICALALIGN = "verticalAlign";
	private static final String PROPERTITY_LEGEND_WIDTH = "width";
	private static final String PROPERTITY_LEGEND_X = "x";
	private static final String PROPERTITY_LEGEND_Y = "y";
	
	private static final String PROPERTITY_TOOLTIP_BACKGROUNDCOLOR = "backgroundColor";
	private static final String PROPERTITY_TOOLTIP_BORDERCOLOR = "borderColor";
	private static final String PROPERTITY_TOOLTIP_BORDERRADIUS = "borderRadius";
	private static final String PROPERTITY_TOOLTIP_BORDERWIDTH = "borderWidth";
	private static final String PROPERTITY_TOOLTIP_ENABLED = "enabled";
	private static final String PROPERTITY_TOOLTIP_FORMATTER = "formatter";
	private static final String PROPERTITY_TOOLTIP_SHADOW = "shadow";
	private static final String PROPERTITY_TOOLTIP_SHARED = "shared";
	private static final String PROPERTITY_TOOLTIP_SNAP = "snap";
	private static final String PROPERTITY_TOOLTIP_STYLE = "style";
	
	
	
	
	private static final String PROPERTITY_AXIS_X = "xAxis";
	private static final String PROPERTITY_AXIS_Y = "yAxis";
	
	private static final String PROPERTITY_AXIS_ALLOWDECIMALS = "allowDecimals";
	private static final String PROPERTITY_AXIS_ALTERNATEGRIDCOLOR = "alternateGridColor"; 
	private static final String PROPERTITY_AXIS_CAGEGORIES = "categories"; 
	//dateTimeLabelFormats 
	//endOnTick
	//events
	private static final String PROPERTITY_AXIS_GRIDLINECOLOR = "gridLineColor";
	private static final String PROPERTITY_AXIS_GRIDLINEDASHSTYLE = "gridLineDashStyle"; 
	private static final String PROPERTITY_AXIS_GRIDLINEWIDTH = "gridLineWidth"; 
	private static final String PROPERTITY_AXIS_ID = "id";  
	private static final String PROPERTITY_AXIS_LINECOLOR = "lineColor"; 
	private static final String PROPERTITY_AXIS_LINEWIDTH = "lineWidth"; 
	private static final String PROPERTITY_AXIS_LINKTO = "linkedTo";
	private static final String PROPERTITY_AXIS_MAX = "max";
	private static final String PROPERTITY_AXIS_MAXPADDING = "maxPadding";
	private static final String PROPERTITY_AXIS_MAXZOOM = "maxZoom";
	private static final String PROPERTITY_AXIS_MIN = "min";
	private static final String PROPERTITY_AXIS_MINPADDING = "minPadding";
	//minorGridLineColor 
	//minorGridLineDashStyle 
	//minorGridLineWidth 
	//minorTickColor 
	//minorTickInterval 
	//minorTickLength 
	//minorTickPosition 
	//minorTickWidth 
	private static final String PROPERTITY_AXIS_OFFSET = "offset";
	private static final String PROPERTITY_AXIS_OPPOSITE = "opposite"; 
	
	private static final String PROPERTITY_AXIS_PLOTBANDS = "plotBands"; 
	private static final String PROPERTITY_AXIS_PLOTBANDS_COLOR = "color";
	//events 
	private static final String PROPERTITY_AXIS_PLOTBANDS_FROM = "from";
	private static final String PROPERTITY_AXIS_PLOTBANDS_ID = "id";
	private static final String PROPERTITY_AXIS_PLOTBANDS_TO = "to";
	private static final String PROPERTITY_AXIS_PLOTBANDS_ZINDEX = "zIndex";
	
	private static final String PROPERTITY_AXIS_PLOT_LABEL = "label";
	private static final String PROPERTITY_AXIS_PLOT_LABEL_ALIGN = "align";
	private static final String PROPERTITY_AXIS_PLOT_LABEL_TEXT = "text";
	private static final String PROPERTITY_AXIS_PLOT_LABEL_VERTICALALIGN = "verticalAlign"; 
	private static final String PROPERTITY_AXIS_PLOT_LABEL_RATATION = "rotation"; 
	private static final String PROPERTITY_AXIS_PLOT_LABEL_STYLE = "style";
	private static final String PROPERTITY_AXIS_PLOT_LABEL_TEXTALIGN = "textAlign"; 
	private static final String PROPERTITY_AXIS_PLOT_LABEL_X = "x";
	private static final String PROPERTITY_AXIS_PLOT_LABEL_Y = "y";
	
	private static final String PROPERTITY_AXIS_PLOTLINES = "plotLines";
	private static final String PROPERTITY_AXIS_PLOTLINES_COLOR = "color";
	//events
	private static final String PROPERTITY_AXIS_PLOTLINES_ID = "id";
	private static final String PROPERTITY_AXIS_PLOTLINES_VALUE = "value";
	private static final String PROPERTITY_AXIS_PLOTLINES_WIDTH = "width"; 
	private static final String PROPERTITY_AXIS_PLOTLINES_ZINDEX = "zIndex"; 
	 
	
	private static final String PROPERTITY_AXIS_REVERSED = "reversed"; 
	private static final String PROPERTITY_AXIS_SHOWFIRSTLABEL = "showFirstLabel"; 
	private static final String PROPERTITY_AXIS_SHOWLASTLABEL = "showLastLabel"; 
	//startOfWeek 
	//startOnTick 
	//tickColor
	//tickInterval 
	//tickLength 
	//tickmarkPlacement
	//tickPixelInterval 
	//tickPosition 
	//tickWidth
	private static final String PROPERTITY_AXIS_TITLE = "title";  
	private static final String PROPERTITY_AXIS_TITLE_ALIGN = "align"; 
	private static final String PROPERTITY_AXIS_TITLE_MARGIN = "margin";
	private static final String PROPERTITY_AXIS_TITLE_ROTATION = "rotation"; 
	private static final String PROPERTITY_AXIS_TITLE_STYLE = "style";
	private static final String PROPERTITY_AXIS_TITLE_TEXT = "text"; 
	
	private static final String PROPERTITY_AXIS_TYPE = "type";  
	
	
	private static final String PROPERTITY_AXIS_LABELS = "labels";
	private static final String PROPERTITY_AXIS_LABELS_ALIGN = "align";
	private static final String PROPERTITY_AXIS_LABELS_FORMATTER = "formatter";
	private static final String PROPERTITY_AXIS_LABELS_ROTATION = "rotation";
	private static final String PROPERTITY_AXIS_LABELS_STAGGERLINES = "staggerLines"; 
	private static final String PROPERTITY_AXIS_LABELS_STEP = "step";
	private static final String PROPERTITY_AXIS_LABELS_STYLE = "style";	
	private static final String PROPERTITY_AXIS_LABELS_X = "x";	
	private static final String PROPERTITY_AXIS_LABELS_Y = "y";	
	
	public void onPreparePageContent(BuildSession session, ViewContext context) throws IOException {
		super.onPreparePageContent(session, context);
		addJavaScript(session, context, "chart/Adapter-min.js");
		addJavaScript(session, context, "chart/Chart-min.js");
		addJavaScript(session, context, "chart/themes/grid.js");
		addJavaScript(session, context, "chart/Exporting-min.js");
	}
	
	protected void addEvent(String id, String eventName, String handler){}
	
	public void onCreateViewContent(BuildSession session, ViewContext context) throws IOException {
		super.onCreateViewContent(session, context);
		Map map = context.getMap();	
		
		processChartConfig(context);
		processTitle(context);
		processSubTitle(context);
		processLegend(context);
		processTooltip(context);
		processLabels(context);
		createAxis(context,PROPERTITY_AXIS_X);
		createAxis(context,PROPERTITY_AXIS_Y);
		
		map.put(CONFIG, getConfigString());
	}
	
	private void putStringCfg(CompositeMap view,String key, Map map){
		String value = view.getString(key.toLowerCase());
		if(value!=null) map.put(key, value);		
	}
	
	private void putIntCfg(CompositeMap view,String key, Map map){
		Integer value = view.getInt(key.toLowerCase());
		if(value!=null) map.put(key, value);		
	}
	
	private void putBooleanCfg(CompositeMap view,String key, Map map){
		Boolean value = view.getBoolean(key.toLowerCase());
		if(value!=null) map.put(key, value);		
	}
	
	private void putFunctionCfg(CompositeMap view,String key, Map map){
		String value = view.getString(key.toLowerCase());
		if(value!=null) map.put(key, new JSONFunction(value));		
	}
	
	private void putStyleCfg(CompositeMap view,String key, Map map) {
		String value = view.getString(key.toLowerCase());
		if(value!=null) {
			JSONObject smap = new JSONObject();
			String[] sts = value.split(";");
			for(int i=0;i<sts.length;i++){
				String style = sts[i];
				if(!"".equals(style)&& style.indexOf(":")!=-1){
					String[] vs = style.split(":");
					String k = vs[0];
					String v = vs[1];
					v = v.replaceAll("'", "");
					
					Integer iv = null;
					try{
						iv = Integer.valueOf(v);
					}catch(Exception e){}
					try {
						if(iv==null){
							smap.put(k,v);
						}else{
							smap.put(k,iv);
						}
					} catch (JSONException e) {
						throw new RuntimeException(e);
					}
				}
			}
			map.put(key, smap);	
		}
	}
	
	
	private void processChartConfig(ViewContext context){
		Map chart = new HashMap();
		CompositeMap view = context.getView();
		Map map = context.getMap();	
		
		chart.put("renderTo", (String)map.get(ComponentConfig.PROPERTITY_ID));
		
		putStringCfg(view,PROPERTITY_CHART_TYPE,chart);
		putIntCfg(view,PROPERTITY_CHART_BORDERWIDTH,chart);
		putIntCfg(view,PROPERTITY_CHART_BORDERRADIUS,chart);
		putStringCfg(view,PROPERTITY_CHART_BORDERCOLOR,chart);
		putBooleanCfg(view, PROPERTITY_CHART_SHADOW, chart);
		putEvents(context,view,chart);
		
		addConfig("chart", new JSONObject(chart));
	}
	
	private void putEvents(ViewContext context, CompositeMap view, Map map){
		CompositeMap events = view.getChild(ComponentConfig.PROPERTITY_EVENTS);
		if(events != null){
			List list = events.getChilds();
			if(list != null){
				JSONObject eo = new JSONObject();
				Iterator it = list.iterator();
				while(it.hasNext()){
					CompositeMap event = (CompositeMap)it.next();
					EventConfig eventConfig = EventConfig.getInstance(event);
					String eventName = eventConfig.getEventName();
					String handler = eventConfig.getHandler();
					if(!"".equals(eventName) && !"".equals(handler))
						try {
							eo.put(eventName,new JSONFunction(handler));
						} catch (JSONException e) {
							throw new RuntimeException(e);
						}
				}
				map.put("events", eo);
			}
		}
		
	}
	
	private void processTitle(ViewContext context){
		Map title = new HashMap();
		CompositeMap cview = context.getView();
		CompositeMap view = cview.getChild("title");
		if(view!=null){
			putStyleCfg(view,PROPERTITY_TITLE_STYLE,title);
			putStringCfg(view,PROPERTITY_TITLE_TEXT,title);
			putIntCfg(view,PROPERTITY_TITLE_X,title);
			putIntCfg(view,PROPERTITY_TITLE_Y,title);
			putStringCfg(view,PROPERTITY_TITLE_ALIGN,title);
			putIntCfg(view,PROPERTITY_TITLE_MARGIN,title);
			putBooleanCfg(view, PROPERTITY_TITLE_FLOATING, title);
			putStringCfg(view,PROPERTITY_TITLE_VERTICALALIGN,title);
		}
		if(!title.isEmpty())
		addConfig("title", new JSONObject(title));
	}
	
	private void processSubTitle(ViewContext context){
		Map title = new HashMap();
		CompositeMap cview = context.getView();
		CompositeMap view = cview.getChild("subtitle");
		if(view!=null){
			putStyleCfg(view,PROPERTITY_SUBTITLE_STYLE,title);
			putStringCfg(view,PROPERTITY_SUBTITLE_TEXT,title);
			putIntCfg(view,PROPERTITY_SUBTITLE_X,title);
			putIntCfg(view,PROPERTITY_SUBTITLE_Y,title);
			putBooleanCfg(view, PROPERTITY_TITLE_FLOATING, title);
			putStringCfg(view,PROPERTITY_SUBTITLE_ALIGN,title);
			putBooleanCfg(view, PROPERTITY_SUBTITLE_FLOATING, title);
			putStringCfg(view,PROPERTITY_SUBTITLE_VERTICALALIGN,title);
		}
		if(!title.isEmpty())
		addConfig("subtitle", new JSONObject(title));
	}
	
	private void processLegend(ViewContext context){
		Map cfg = new HashMap();
		CompositeMap cview = context.getView();
		CompositeMap view = cview.getChild("legend");
		
		if(view !=null) {
			putStyleCfg(view,PROPERTITY_LEGEND_ITEMSTYLE,cfg);
			putStyleCfg(view,PROPERTITY_LEGEND_STYLE,cfg);
			putStringCfg(view,PROPERTITY_LEGEND_ALIGN,cfg);
			putStringCfg(view,PROPERTITY_LEGEND_BACKGROUNDCOLOR,cfg);
			putStringCfg(view,PROPERTITY_LEGEND_BORDERCOLOR,cfg);
			putIntCfg(view,PROPERTITY_LEGEND_BORDERWIDTH,cfg);
			putIntCfg(view,PROPERTITY_LEGEND_BORDERRADIUS,cfg);
			putBooleanCfg(view, PROPERTITY_LEGEND_FLOATING, cfg);
			putStringCfg(view,PROPERTITY_LEGEND_LAYOUT,cfg);
			putFunctionCfg(view,PROPERTITY_LEGEND_LABELFORMATTER,cfg);
			putIntCfg(view,PROPERTITY_LEGEND_MARGIN,cfg);
			putBooleanCfg(view, PROPERTITY_LEGEND_REVERSED, cfg);
			putBooleanCfg(view, PROPERTITY_LEGEND_SHADOW, cfg);
			putIntCfg(view,PROPERTITY_LEGEND_SYMBOLPADDING,cfg);
			putIntCfg(view,PROPERTITY_LEGEND_SYMBOLWIDTH,cfg);
			putStringCfg(view,PROPERTITY_LEGEND_VERTICALALIGN,cfg);
			putIntCfg(view,PROPERTITY_LEGEND_WIDTH,cfg);
			putIntCfg(view,PROPERTITY_LEGEND_X,cfg);
			putIntCfg(view,PROPERTITY_LEGEND_Y,cfg);
		}
		if(!cfg.isEmpty())
		addConfig("legend", new JSONObject(cfg));
	}
	
	private void processTooltip(ViewContext context){
		Map cfg = new HashMap();
		CompositeMap cview = context.getView();
		CompositeMap view = cview.getChild("tooltip");
		
		if(view !=null) {
			putStringCfg(view,PROPERTITY_TOOLTIP_BACKGROUNDCOLOR,cfg);
			putStringCfg(view,PROPERTITY_TOOLTIP_BORDERCOLOR,cfg);
			putIntCfg(view,PROPERTITY_TOOLTIP_BORDERRADIUS,cfg);
			putIntCfg(view,PROPERTITY_TOOLTIP_BORDERWIDTH,cfg);
			putBooleanCfg(view, PROPERTITY_TOOLTIP_ENABLED, cfg);
			putFunctionCfg(view,PROPERTITY_TOOLTIP_FORMATTER,cfg);
			putBooleanCfg(view, PROPERTITY_TOOLTIP_SHADOW, cfg);
			putBooleanCfg(view, PROPERTITY_TOOLTIP_SHARED, cfg);
			putIntCfg(view,PROPERTITY_TOOLTIP_SNAP,cfg);
			putStyleCfg(view,PROPERTITY_TOOLTIP_STYLE,cfg);
		}
		if(!cfg.isEmpty())
		addConfig("tooltip", new JSONObject(cfg));
	}
	
	private void processLabels(ViewContext context){
		Map cfg = new HashMap();
		CompositeMap cview = context.getView();
		CompositeMap view = cview.getChild(PROPERTITY_LABELS);
		
		JSONArray array = null;
		if(view !=null) {
			putStyleCfg(view,PROPERTITY_LABELS_STYLE,cfg);
			List list = view.getChilds();
			if(list!=null){
				array = new JSONArray();
				Iterator it = list.iterator();
				while(it.hasNext()){
					CompositeMap label = (CompositeMap)it.next();
					Map icfg = new HashMap();
					putStringCfg(label,PROPERTITY_LABELS_LABEL_HTML,icfg);
					putStyleCfg(label,PROPERTITY_LABELS_LABEL_STYLE,icfg);
					array.put(icfg);
				}
				
			}
		}
		if(array != null)
		addConfig("labels", array);
	}
	
	private void createLabels(CompositeMap cview, Map map){
		CompositeMap view = cview.getChild(PROPERTITY_AXIS_LABELS);
		Map cfg = new HashMap();
		if(view !=null) {
			putStringCfg(view,PROPERTITY_AXIS_LABELS_ALIGN,cfg);
			putFunctionCfg(view,PROPERTITY_AXIS_LABELS_FORMATTER,cfg);
			putIntCfg(view,PROPERTITY_AXIS_LABELS_ROTATION,cfg);
			putIntCfg(view,PROPERTITY_AXIS_LABELS_STAGGERLINES,cfg);
			putIntCfg(view,PROPERTITY_AXIS_LABELS_STEP,cfg);
			putStyleCfg(view,PROPERTITY_AXIS_LABELS_STYLE,cfg);
			putIntCfg(view,PROPERTITY_AXIS_LABELS_X,cfg);
			putIntCfg(view,PROPERTITY_AXIS_LABELS_Y,cfg);
		}
		if(!cfg.isEmpty())
			map.put(PROPERTITY_AXIS_LABELS, new JSONObject(cfg));
	}
	
	
	
	private void createAxis(ViewContext context,String name){
		CompositeMap view = context.getView();
		CompositeMap axis = view.getChild(name);
		if(axis!=null){
			List list = axis.getChilds();
			JSONArray array = null;
			if(list!=null){
				array = new JSONArray();
				Iterator it = list.iterator();
				while(it.hasNext()){
					Map cfg = new HashMap();
					CompositeMap axi = (CompositeMap)it.next();
					putBooleanCfg(axi,PROPERTITY_AXIS_ALLOWDECIMALS,cfg);
					putStringCfg(axi, PROPERTITY_AXIS_ALTERNATEGRIDCOLOR, cfg);
					createCategories(axi,cfg);
					putStringCfg(axi, PROPERTITY_AXIS_GRIDLINECOLOR, cfg);
					putStringCfg(axi, PROPERTITY_AXIS_GRIDLINEDASHSTYLE, cfg);
					putIntCfg(axi, PROPERTITY_AXIS_GRIDLINEWIDTH, cfg);
					putStringCfg(axi, PROPERTITY_AXIS_ID, cfg);
					createLabels(axi,cfg);
					putStringCfg(axi, PROPERTITY_AXIS_LINECOLOR, cfg);
					putIntCfg(axi, PROPERTITY_AXIS_LINEWIDTH, cfg);
					putIntCfg(axi, PROPERTITY_AXIS_LINKTO, cfg); 
					putIntCfg(axi, PROPERTITY_AXIS_MAX, cfg);
					putIntCfg(axi, PROPERTITY_AXIS_MAXPADDING, cfg);
					putIntCfg(axi, PROPERTITY_AXIS_MAXZOOM, cfg);
					putIntCfg(axi, PROPERTITY_AXIS_MIN, cfg);
					putIntCfg(axi, PROPERTITY_AXIS_MINPADDING, cfg);
					putIntCfg(axi, PROPERTITY_AXIS_OFFSET, cfg);
					putBooleanCfg(axi, PROPERTITY_AXIS_OPPOSITE, cfg);
					creatPlotBands(axi,cfg);
					creatPlotLines(axi,cfg);
					putBooleanCfg(axi, PROPERTITY_AXIS_REVERSED, cfg);
					putBooleanCfg(axi, PROPERTITY_AXIS_SHOWFIRSTLABEL, cfg);
					putBooleanCfg(axi, PROPERTITY_AXIS_SHOWLASTLABEL, cfg);
					putStringCfg(axi, PROPERTITY_AXIS_LINECOLOR, cfg);
					createTitle(axi,cfg);
					putStringCfg(axi, PROPERTITY_AXIS_TYPE, cfg);					
					
					array.put(cfg);
				}
				if(array != null)
					addConfig(name, array);
			}
		}
	}
	
	private void createCategories(CompositeMap view, Map map){
		CompositeMap cats = view.getChild(PROPERTITY_AXIS_CAGEGORIES);
		if(cats!=null){
			List list = cats.getChilds();
			JSONArray array = null;
			if(list!=null) {
				array = new JSONArray();
				Iterator it = list.iterator();
				while(it.hasNext()){
					CompositeMap cat = (CompositeMap)it.next();
					String value = cat.getString("value","");
					array.put(value);
				}
				if(array != null)
					map.put(PROPERTITY_AXIS_CAGEGORIES, array);
			}
		}
	}
	
	private void creatPlotBands(CompositeMap view, Map map){
		CompositeMap pbs = view.getChild(PROPERTITY_AXIS_PLOTBANDS);
		if(pbs!=null){
			List list = pbs.getChilds();
			JSONArray array = null;
			if(list!=null) {
				array = new JSONArray();
				Iterator it = list.iterator();
				while(it.hasNext()){
					Map cfg = new HashMap();
					CompositeMap pb = (CompositeMap)it.next();
					putStringCfg(pb, PROPERTITY_AXIS_PLOTBANDS_COLOR, cfg);
					putIntCfg(pb, PROPERTITY_AXIS_PLOTBANDS_FROM, cfg);
					putStringCfg(pb, PROPERTITY_AXIS_PLOTBANDS_ID, cfg);
					createPlotLabel(pb,cfg);
					putIntCfg(pb, PROPERTITY_AXIS_PLOTBANDS_TO, cfg);
					putIntCfg(pb, PROPERTITY_AXIS_PLOTBANDS_ZINDEX, cfg);
					array.put(cfg);
				}
				if(array != null)
					map.put(PROPERTITY_AXIS_PLOTBANDS, array);
			}
		}
	}
	
	private void creatPlotLines(CompositeMap view, Map map){
		CompositeMap pls = view.getChild(PROPERTITY_AXIS_PLOTLINES);
		if(pls!=null){
			List list = pls.getChilds();
			JSONArray array = null;
			if(list!=null) {
				array = new JSONArray();
				Iterator it = list.iterator();
				while(it.hasNext()){
					Map cfg = new HashMap();
					CompositeMap pl = (CompositeMap)it.next();
					
					putStringCfg(pl, PROPERTITY_AXIS_PLOTLINES_COLOR, cfg);
					putStringCfg(pl, PROPERTITY_AXIS_PLOTLINES_ID, cfg);
					putIntCfg(pl, PROPERTITY_AXIS_PLOTLINES_VALUE, cfg);
					putIntCfg(pl, PROPERTITY_AXIS_PLOTLINES_WIDTH, cfg);
					createPlotLabel(pl,cfg);
					putIntCfg(pl, PROPERTITY_AXIS_PLOTLINES_ZINDEX, cfg);
					array.put(cfg);
				}
				if(array != null)
					map.put(PROPERTITY_AXIS_PLOTLINES, array);
			}
		}
	}
	
	private void createPlotLabel(CompositeMap view, Map map){
		CompositeMap label = view.getChild(PROPERTITY_AXIS_PLOT_LABEL);
		if(label!=null){
			Map cfg = new HashMap();
			putStringCfg(label, PROPERTITY_AXIS_PLOT_LABEL_ALIGN, cfg);
			putStringCfg(label, PROPERTITY_AXIS_PLOT_LABEL_TEXT, cfg);
			putStringCfg(label, PROPERTITY_AXIS_PLOT_LABEL_VERTICALALIGN, cfg);
			putIntCfg(label, PROPERTITY_AXIS_PLOT_LABEL_RATATION, cfg);
			putStyleCfg(label, PROPERTITY_AXIS_PLOT_LABEL_STYLE, cfg);
			putStringCfg(label, PROPERTITY_AXIS_PLOT_LABEL_TEXTALIGN, cfg);
			putIntCfg(label, PROPERTITY_AXIS_PLOT_LABEL_X, cfg);
			putIntCfg(label, PROPERTITY_AXIS_PLOT_LABEL_Y, cfg);
			map.put(PROPERTITY_AXIS_PLOT_LABEL, new JSONObject(cfg));
		}
	}
	private void createTitle(CompositeMap view, Map map){
		CompositeMap title = view.getChild(PROPERTITY_AXIS_TITLE);
		if(title!=null){
			Map cfg = new HashMap();
			putStringCfg(title, PROPERTITY_AXIS_TITLE_ALIGN, cfg);
			putIntCfg(title, PROPERTITY_AXIS_TITLE_MARGIN, cfg);
			putStyleCfg(title, PROPERTITY_AXIS_TITLE_STYLE, cfg);
			
			putIntCfg(title, PROPERTITY_AXIS_TITLE_ROTATION, cfg);
			putStringCfg(title, PROPERTITY_AXIS_TITLE_TEXT, cfg);
			map.put(PROPERTITY_AXIS_TITLE, new JSONObject(cfg));
		}
	}
	
}