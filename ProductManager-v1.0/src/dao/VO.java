package dao;
/**
 * 实体类，用于存放传输对象数据
 * @author Administrator
 *
 */
public class VO {

	private int id;
	private String user_name;
	private String password;
	private int identify;
	
	private int order_number;//订单号
	private String order_name;//订单名称
	private int order_count;//订单数量
	private String order_type;//设备类型
	private int order_state;//订单状态
	private String order_info;//订单信息
	private String order_task;//任务书
	private String order_confirm;//确认单
	private String order_bom;//Bom
}
