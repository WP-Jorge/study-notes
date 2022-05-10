package app.huiyuanxinxiguanli.zhucehuiyuan;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.web.servlet.ModelAndView;

import com.ibatis.sqlmap.client.SqlMapClient;

import app.pub.base.BaseFormController;
import app.pub.conf.InitSystemConfig;
import app.pub.database.DBUtils;
import app.pub.database.IbatisUtil;
import app.pub.date.DateUtil;
import app.pub.global.Global;
import app.pub.sysInfo.SysInfo;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Zhucehuiyuan extends BaseFormController {
	// ҵ������
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ����Spring , ModelAndView����
		ModelAndView modelAndView = null;
		SqlMapClient sqlMap = null;
		try {
			// ��������
			sqlMap = DBUtils.getSqlMap(this.getClass());

			// ��������
			sqlMap.startTransaction();

			// ��ȡ��ǰ������ʶ
			String flag = request.getParameter("flag");

			// ��ȡ�б�����
			if (flag != null && flag.equals("getJsonStore")) {
				modelAndView = doGetJsonStore(sqlMap, request, response);
			}// ������¼
			else if (flag != null && flag.equals("doAddSubmit")) {
				modelAndView = doAddWithAttach(sqlMap, request, response);
			}// �޸ļ�¼
			else if (flag != null && flag.equals("doUpdateSubmit")) {
				modelAndView = doUpdateWithAttach(sqlMap, request, response);
			}// ɾ����ⶳ��¼
			else if (flag != null && flag.equals("doDeleOrUnDele")) {
				modelAndView = doDeleOrUnDele(sqlMap, request, response);
			}// ����excel
			else if (flag != null && flag.equals("doExportExcel")) {
				modelAndView = doExportExcel(sqlMap, request, response);
			}// �ֶ�ͳ��
			else if (flag != null && flag.equals("doTongJi")) {
				modelAndView = doTongJi(sqlMap, request, response);
			}// �����ֶ�
			else if (flag != null && flag.equals("doGuanLian")) {
				modelAndView = doGuanLian(sqlMap, request, response);
			}// ����
			else if (flag != null && flag.equals("doShenPi")) {
				modelAndView = doShenPi(sqlMap, request, response);
			}// �ϴ�ͼƬ
			else if (flag != null && flag.equals("doAddPicSubmit")) {
				modelAndView = doAddPicSubmit(sqlMap, request, response);
			}// ��ȡͼƬ����
			else if (flag != null && flag.equals("getPicJsonStore")) {
				modelAndView = doGetPicJsonStore(sqlMap, request, response);
			}// ɾ��ͼƬ
			else if (flag != null && flag.equals("doDelePic")) {
				modelAndView = doDelePic(sqlMap, request, response);
			}else if (flag != null && flag.equals("doChart")) {
				
				HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
				String graphURL = drawXYLineChart(sqlMap, request, response);
				resultMap.put("graphURL", graphURL);
				return toFinish("/WEB-ROOT/app/huiyuanxinxiguanli/zhucehuiyuan/chart.jsp", resultMap, request, response);
			} else {
				HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
				return toFinish("/WEB-ROOT/app/huiyuanxinxiguanli/zhucehuiyuan/zhucehuiyuan.jsp", resultMap, request, response);
			}
			// �ύ���ݿ�
			sqlMap.commitTransaction();
		} catch (Exception e) {
			// ��ӡ�����ջ��Ϣ
			e.printStackTrace();
		} finally {
			// ����������Ϊ�գ��ر�sqlMap
			if (sqlMap != null) {
				DBUtils.closeSqlMap(sqlMap, this.getClass());
			}
		}
		return modelAndView;
	}

	// �õ���ѯ����
	public ModelAndView doGetJsonStore(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) {
		// �������������
		PrintWriter pw = null;
		try {
			// ���ò�ѯ����
			HashMap<String, Object> where = new HashMap<String, Object>();
			String yonghuzhanghaoSearch = request.getParameter("yonghuzhanghaoSearch");
			if (yonghuzhanghaoSearch != null && !yonghuzhanghaoSearch.equals("")) {
				where.put("yonghuzhanghaoSearch", yonghuzhanghaoSearch);
			}
			String huiyuanleixingSearch = request.getParameter("huiyuanleixingSearch");
			if (huiyuanleixingSearch != null && !huiyuanleixingSearch.equals("")) {
				where.put("huiyuanleixingSearch", huiyuanleixingSearch);
			}
			String kaishishijianSearch = request.getParameter("kaishishijianSearch");
			if (kaishishijianSearch != null && !kaishishijianSearch.equals("")) {
				where.put("kaishishijianSearch", kaishishijianSearch);
			}
			String daoqishijianSearch = request.getParameter("daoqishijianSearch");
			if (daoqishijianSearch != null && !daoqishijianSearch.equals("")) {
				where.put("daoqishijianSearch", daoqishijianSearch);
			}
			String shengyucishuSearch = request.getParameter("shengyucishuSearch");
			if (shengyucishuSearch != null && !shengyucishuSearch.equals("")) {
				where.put("shengyucishuSearch", shengyucishuSearch);
			}
			String zhifufangshiSearch = request.getParameter("zhifufangshiSearch");
			if (zhifufangshiSearch != null && !zhifufangshiSearch.equals("")) {
				where.put("zhifufangshiSearch", zhifufangshiSearch);
			}
			String shoulirenSearch = request.getParameter("shoulirenSearch");
			if (shoulirenSearch != null && !shoulirenSearch.equals("")) {
				where.put("shoulirenSearch", shoulirenSearch);
			}
			String shoulishijianSearch = request.getParameter("shoulishijianSearch");
			if (shoulishijianSearch != null && !shoulishijianSearch.equals("")) {
				where.put("shoulishijianSearch", shoulishijianSearch);
			}
			String shenpiSearch = request.getParameter("shenpiSearch");
			if (shenpiSearch != null && !shenpiSearch.equals("")) {
				where.put("shenpiSearch", shenpiSearch);
			}
			String idSearch = request.getParameter("idSearch");
			if (idSearch != null && !idSearch.equals("")) {
				where.put("idSearch", idSearch);
			}
			String itimeSearch = request.getParameter("itimeSearch");
			if (itimeSearch != null && !itimeSearch.equals("")) {
				where.put("itimeSearch", itimeSearch);
			}
			String detailSearch = request.getParameter("detailSearch");
			if (detailSearch != null && !detailSearch.equals("")) {
				where.put("detailSearch", detailSearch);
			}
			String deleteFlagSearch = request.getParameter("deleteFlagSearch");
			if (deleteFlagSearch != null && !deleteFlagSearch.equals("")) {
				where.put("deleteFlagSearch", deleteFlagSearch);
			}
			String erjiguanlianzdSearch = request.getParameter("erjiguanlianzdSearch");
			if (erjiguanlianzdSearch != null && !erjiguanlianzdSearch.equals("")) {
				where.put("erjiguanlianzdSearch", erjiguanlianzdSearch);
			}

			// ����ʱ��
			String itimeStartSearch = request.getParameter("itimeStartSearch");
			if (itimeStartSearch != null && !itimeStartSearch.equals("")) {
				where.put("itimeStartSearch", itimeStartSearch + " 00:00:00");
			}
			String itimeEndSearch = request.getParameter("itimeEndSearch");
			if (itimeEndSearch != null && !itimeEndSearch.equals("")) {
				where.put("itimeEndSearch", itimeEndSearch + " 23:59:59");
			}

			String r = request.getParameter("r");
			if (r != null && r.equals("n")) {

				if (!SysInfo.getLoginUserType(request, response).equals("1")) {
					where.put("dataRight", " ( a.operatorId='" + SysInfo.getLoginUserId(request, response) + "'    or yonghuzhanghao='" + SysInfo.getLoginUserAcct(request, response) + "'   ) ");
				}

			}

			String erjiguanlianzd = request.getParameter("erjiguanlianzd");
			if (erjiguanlianzd != null && !erjiguanlianzd.equals("")) {
				where.put("erjiguanlianzd", erjiguanlianzd);
			}

			// ���ݲ�ѯ������ȡ����
			String json = IbatisUtil.queryForPage(sqlMap, request, response, where, "Zhucehuiyuan.selecteList");

			// ���ñ��뼯Ϊutf8
			response.setCharacterEncoding("utf-8");

			// ��ȡ�����
			pw = response.getWriter();

			// ��ǰ̨�������
			pw.write(json);
		} catch (Exception e) {
			// �������׳��쳣
			e.printStackTrace();
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}
		return null;
	}

	// ��������
	public ModelAndView doAdd(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �������������
		PrintWriter pw = null;
		try {
			// ���ñ���Ϊutf8
			response.setCharacterEncoding("utf-8");

			// ��ʼ�������
			pw = response.getWriter();

			// ��������
			HashMap<String, Object> where = new HashMap<String, Object>();
			where.put("operatorId", SysInfo.getLoginUserId(request, response));

			// �����ݿ���������
			sqlMap.insert("Zhucehuiyuan.insertObj", where);

			// �����ɹ�����ʾ
			pw.write("{success:true,msg:'���������ɹ���'}");

		} catch (Exception e) {
			// ����ʧ�ܺ���ʾ
			pw.write("{success:false,msg:'" + e.getMessage() + "'}");

			// ��ӡ�����ջ��Ϣ
			e.printStackTrace();

			// �׳��쳣
			throw new Exception();
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}
		return null;
	}

	// �����ϴ����ݷ���
	public ModelAndView doAddWithAttach(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �������������
		PrintWriter pw = null;
		try {
			// ���ñ���ΪGB2312����ֹ�������롣
			response.setCharacterEncoding("GB2312");

			// ��ʼ�������
			pw = response.getWriter();

			// ����DiskFileItemFactory����
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// ���û�������СΪ4096k
			factory.setSizeThreshold(4096);

			// �����ϴ�����
			ServletFileUpload upload = new ServletFileUpload(factory);

			// �����ϴ��������
			upload.setSizeMax(Global.UPLOAD_FILE_MAX_SIZE);

			// ����ϴ��ļ���Ϊ���ĵ�����
			upload.setHeaderEncoding("gb2312");

			// ��������
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("operatorId", SysInfo.getLoginUserId(request, response));

			// ��ȡ���ύ����
			List fileItems = upload.parseRequest(request);
			Iterator iter = fileItems.iterator();
			int i = 0;
			int j = 0;

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				/* ���ļ��� */
				if (item.isFormField()) {
					if (i == 0) {
						whereMap.put("yonghuzhanghao", item.getString("gb2312"));
					} else if (i == 1) {
						whereMap.put("huiyuanleixing", item.getString("gb2312"));
					} else if (i == 2) {
						whereMap.put("kaishishijian", item.getString("gb2312"));
					} else if (i == 3) {
						whereMap.put("daoqishijian", item.getString("gb2312"));
					} else if (i == 4) {
						whereMap.put("shengyucishu", item.getString("gb2312"));
					} else if (i == 5) {
						whereMap.put("zhifufangshi", item.getString("gb2312"));
					} else if (i == 6) {
						whereMap.put("shouliren", item.getString("gb2312"));
					} else if (i == 7) {
						whereMap.put("shoulishijian", item.getString("gb2312"));
					} else if (i == 8) {
						whereMap.put("shenpi", item.getString("gb2312"));
					} else if (i == 9) {
						whereMap.put("detail", item.getString("gb2312"));
					}
					whereMap.put("erjiguanlianzd", request.getParameter("erjiguanlianzd"));

					i++;
				} /* �ļ��� */
				else {
					if (j == 0) {
						// ��ȡ�ļ�����
						String filePath = item.getName();

						// �ļ�����Ϊ�գ�����ת�´�ѭ��
						if (filePath == null || filePath.equals("")) {
							whereMap.put("fuJian", "");
							continue;
						}

						// ��ȡ�ļ�·��
						filePath = filePath.substring(filePath.lastIndexOf("\\") + 1);

						// �����ļ����浽��������·��
						String uploadPath = InitSystemConfig.UPLOAD_FILE_PATH + filePath;

						// �½��ļ�
						File file = new File(uploadPath);

						// д�ļ�
						item.write(file);

						// ���ļ�·������
						whereMap.put("fuJian", uploadPath);
					}
					j++;
				}
			}

			// ��������
			sqlMap.insert("Zhucehuiyuan.insertObj", whereMap);

			/* �������ı��ύ���������� ContentTypeΪtext/html */
			response.setContentType("text/html");

			// ��ʾ�����ɹ�
			pw.write("{success:true,msg:'���������ɹ���'}");

		} catch (Exception e) {
			// ��ӡ�����ջ
			e.printStackTrace();
			try {
				// ��ǰ̨��ʾ����ʧ����Ϣ
				pw.write("{success:false,msg:'" + e.getMessage() + "'}");
			} catch (Exception e1) {
				throw new Exception("��������ʧ��");
			}
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}

		return null;
	}

	// �޸����ݷ���
	public ModelAndView doUpdate(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �������������
		PrintWriter pw = null;
		try {
			// ���ñ���Ϊutf8
			response.setCharacterEncoding("utf-8");

			// ��ʼ�������
			pw = response.getWriter();

			// ��������
			HashMap<String, Object> where = new HashMap<String, Object>();

			// �����ݿ��ύ�޸�����
			sqlMap.update("Zhucehuiyuan.updateObj", where);

			// ��ʾ�����ɹ�
			pw.write("{success:true,msg:'�޸ı��ɹ���'}");

		} catch (Exception e) {
			// ��ǰ̨��ʾ����ʧ����Ϣ
			pw.write("{success:false,msg:'" + e.getMessage() + "'}");
			e.printStackTrace();
			throw new Exception();
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}
		return null;
	}

	// �޸ĸ����ķ���
	public ModelAndView doUpdateWithAttach(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �������������
		PrintWriter pw = null;
		try {
			// ���ñ���ΪGB2312����ֹ�������롣
			response.setCharacterEncoding("GB2312");

			// ��ʼ�������
			pw = response.getWriter();

			// ����DiskFileItemFactory����
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// ���û�������СΪ4096k
			factory.setSizeThreshold(4096);

			// �����ϴ�����
			ServletFileUpload upload = new ServletFileUpload(factory);

			// �����ϴ��������
			upload.setSizeMax(Global.UPLOAD_FILE_MAX_SIZE);

			// ����ϴ��ļ���Ϊ���ĵ�����
			upload.setHeaderEncoding("gb2312");

			// ��ȡ����ID
			String id = request.getParameter("id");

			// �жϸ����Ƿ��޸�
			String isChangeAttach = request.getParameter("isChangeAttach");

			// ��������
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("operatorId", SysInfo.getLoginUserId(request, response));

			// ��ȡ���ύ����
			List fileItems = upload.parseRequest(request);
			Iterator iter = fileItems.iterator();
			int i = 0;
			int j = 0;

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				/* ���ļ��� */
				if (item.isFormField()) {
					if (i == 0) {
						whereMap.put("yonghuzhanghao", item.getString("gb2312"));
					} else if (i == 1) {
						whereMap.put("huiyuanleixing", item.getString("gb2312"));
					} else if (i == 2) {
						whereMap.put("kaishishijian", item.getString("gb2312"));
					} else if (i == 3) {
						whereMap.put("daoqishijian", item.getString("gb2312"));
					} else if (i == 4) {
						whereMap.put("shengyucishu", item.getString("gb2312"));
					} else if (i == 5) {
						whereMap.put("zhifufangshi", item.getString("gb2312"));
					} else if (i == 6) {
						whereMap.put("shouliren", item.getString("gb2312"));
					} else if (i == 7) {
						whereMap.put("shoulishijian", item.getString("gb2312"));
					} else if (i == 8) {
						whereMap.put("shenpi", item.getString("gb2312"));
					} else if (i == 9) {
						whereMap.put("detail", item.getString("gb2312"));
					}

					i++;
				} /* �ļ��� */
				else {
					if (j == 0) {
						if (isChangeAttach != null && isChangeAttach.equals("1")) {
							// ��ȡ�ļ�����
							String fileName = item.getName();

							// �ļ�����Ϊ�գ�����ת�´�ѭ��
							if (fileName == null || fileName.equals("")) {
								whereMap.put("fuJian", "");
								continue;
							}
							// ��ȡ�ļ�·��
							fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

							// �����ļ����浽��������·��
							String uploadPath = InitSystemConfig.UPLOAD_FILE_PATH + fileName;

							// �½��ļ�
							File file = new File(uploadPath);

							// д�ļ�
							item.write(file);

							// ���ļ�·������
							whereMap.put("fuJian", uploadPath);
						}
					}
					j++;
				}
			}

			// ������Ҫ�޸����ݵ�ID
			whereMap.put("id", id);

			// �����ݿ��ύ�޸���Ϣ
			sqlMap.update("Zhucehuiyuan.updateObj", whereMap);

			/* �������ı��ύ���������� ContentTypeΪtext/html */
			response.setContentType("text/html");

			// �޸ĳɹ���ʾ
			pw.write("{success:true,msg:'�޸Ĳ����ɹ���'}");

		} catch (Exception e) {
			// ��ӡ�����ջ
			e.printStackTrace();
			try {
				// ��ǰ̨��ʾ����ʧ����Ϣ
				pw.write("{success:false,msg:'" + e.getMessage() + "'}");
			} catch (Exception e1) {
				throw new Exception("��������ʧ��");
			}
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}

		return null;
	}

	// ɾ�����ݷ���
	public ModelAndView doDeleOrUnDele(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �������������
		PrintWriter pw = null;
		String info = "";
		try {
			// ��ȡ��Ҫɾ�����ݵ�ID
			String id = request.getParameter("id");

			// ��ȡɾ����ʶ
			String deleteFlag = request.getParameter("deleteFlag");
			info = (deleteFlag.equals("0")) ? "�ⶳ" : "ɾ��";

			// ���ñ���Ϊutf8
			response.setCharacterEncoding("utf-8");

			// ��ʼ�������
			pw = response.getWriter();

			// ����ɾ��������
			HashMap<String, Object> where = new HashMap<String, Object>();
			where.put("deleteFlag", deleteFlag);
			where.put("id", id);

			// �����ݿ��ύɾ������
			sqlMap.delete("Zhucehuiyuan.doDeleOrUnDele", where);

			// �����ɹ���ʾ
			pw.write("{success:true,msg:'" + info + "���ɹ���'}");

		} catch (Exception e) {
			// ����ʧ����ʾ
			pw.write("{success:false,msg:'" + info + "��ʧ�ܣ�'}");

			// ��ӡ�����ջ��Ϣ
			e.printStackTrace();

			// �׳��쳣
			throw new Exception();
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}
		return null;
	}

	// ��������
	public ModelAndView doShenPi(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �������������
		PrintWriter pw = null;
		String info = "";
		try {
			// ��ȡ��Ҫ������Ϣ��ID
			String id = request.getParameter("id");

			// ��ȡ��Ҫ������Ϣ��״̬
			String shenpi = request.getParameter("shenpi");

			// ���ñ���Ϊutf8
			response.setCharacterEncoding("utf-8");

			// ��ʼ�������
			pw = response.getWriter();

			// ��������
			HashMap<String, Object> where = new HashMap<String, Object>();
			where.put("shenpi", shenpi);
			where.put("id", id);

			// �����ݿ��ύ������Ϣ
			sqlMap.delete("Zhucehuiyuan.doShenPi", where);

			// �����ɹ�����ʾ
			pw.write("{success:true,msg:'�����ɹ���'}");

		} catch (Exception e) {
			// ����ʧ�ܺ���ʾ
			pw.write("{success:false,msg:'����ʧ�ܣ�'}");

			// ��ӡ�����ջ��Ϣ
			e.printStackTrace();

			// �׳��쳣
			throw new Exception();
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}
		return null;
	}

	// ͳ�Ʒ���
	public ModelAndView doTongJi(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �������������
		PrintWriter pw = null;
		try {
			// ���ñ���Ϊutf8
			response.setCharacterEncoding("utf-8");

			// ��ʼ�������
			pw = response.getWriter();

			String m = request.getParameter("m");
			if (m != null && !m.equals("")) {
				/* ��ȡͳ���ֶ� */
				String descTabSql = "select column_name as Field , data_type , column_comment as columnComment ," + "SUBSTRING(column_type,INSTR(column_type,'(') + 1,( INSTR(column_type,')') - INSTR(column_type,'(') ) -1  ) as column_type," + "b.tongji "
						+ "from information_schema.columns a,table_tongji b " + "where a.table_schema='" + Global.DATABASE_NAME + "'  and " + "a.table_name='" + m + "' and  " + "a.column_name <>'id' and " + "a.column_name <>'itime' and " + "a.column_name <>'detail' and "
						+ "a.column_name <>'deleteFlag' and " + "a.column_name <>'operatorId' and " + "a.table_name=b.table_name and " + "a.column_name=b.field_name ";

				// ����ͳ������
				HashMap<String, Object> where = new HashMap<String, Object>();
				where.put("descTabSql", descTabSql);

				// ��ȡͳ������
				List list = IbatisUtil.queryForList(sqlMap, request, response, where, "Util.descTab");

				// ����ͳ������ֵ
				String msg = "";
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						String tongji = ((HashMap) (list.get(i))).get("tongji").toString();
						if (tongji.equals("1")) {
							String column_name = ((HashMap) (list.get(i))).get("Field").toString();
							String columnComment = ((HashMap) (list.get(i))).get("columnComment").toString();
							// ����ͳ������
							HashMap<String, Object> whereMap = new HashMap<String, Object>();
							whereMap.put("column_name", column_name);
							whereMap.put("table_name", m);

							String r = request.getParameter("r");
							if (r != null && r.equals("n")) {

								if (!SysInfo.getLoginUserType(request, response).equals("1")) {
									whereMap.put("dataRight", "  operatorId='" + SysInfo.getLoginUserId(request, response) + "' ");
								}

							}
							String erjiguanlianzd = request.getParameter("erjiguanlianzd");
							if (erjiguanlianzd != null && !erjiguanlianzd.equals("")) {
								whereMap.put("erjiguanlianzd", erjiguanlianzd);
							}
							// ����ͳ�ƽ����Ϣ
							float zongshu = Float.parseFloat(sqlMap.queryForObject("Util.selTongJiTab", whereMap).toString());
							if (msg.equals("")) {
								msg = columnComment + "��" + zongshu + "  ";
							} else {
								msg = msg + " ��" + columnComment + "��" + zongshu + "  ";
							}
						}
					}
				}
				// ��ǰ̨���ͳ�ƽ����Ϣ
				pw.write("{success:true,msg:'" + msg + "'}");
			} else {
				// ��ǰ̨���ͳ�ƽ����Ϣ
				pw.write("{success:true,msg:''}");
			}
		} catch (Exception e) {
			// ��ǰ̨���ͳ�ƽ����Ϣ
			pw.write("{success:false,msg:''}");

			// ��ӡ�����ջ��Ϣ
			e.printStackTrace();

			// �������׳��쳣
			throw new Exception();
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}
		return null;
	}

	public ModelAndView doGuanLian(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �������������
		PrintWriter pw = null;
		try {
			// ���ù�������
			HashMap<String, Object> where = new HashMap<String, Object>();
			String guanLianBiao = request.getParameter("guanLianBiao");
			if (guanLianBiao != null && !guanLianBiao.equals("")) {
				where.put("guanLianBiao", guanLianBiao);
			}
			String guanLianZiDuan = request.getParameter("guanLianZiDuan");
			if (guanLianZiDuan != null && !guanLianZiDuan.equals("")) {
				where.put("guanLianZiDuan", guanLianZiDuan);
			}
			where.put("deleteFlag", 0);

			// �õ�����
			String json = IbatisUtil.queryForPage(sqlMap, request, response, where, "Util.selecteGuanLianList");

			// ���ñ���Ϊutf8
			response.setCharacterEncoding("utf-8");

			// ��ʼ�������
			pw = response.getWriter();

			// ��ǰ̨�����Ϣ
			pw.write(json);
		} catch (Exception e) {
			// ��ӡ�����ջ��Ϣ
			e.printStackTrace();
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}
		return null;
	}

	public static ModelAndView doExportExcel(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ���������
		OutputStream os = null;
		// ����excel�����
		WritableWorkbook wwb = null;
		try {
			// ���ݲ�ѯ�����õ������
			HashMap<String, Object> where = new HashMap<String, Object>();
			List<Object> listInfo = sqlMap.queryForList("Zhucehuiyuan.selecteList", where);

			// ����responseΪ����ģʽ
			Date local = new Date();

			// ��ʽ����ǰʱ����Ϊ�ļ���
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String finalStr = df.format(local);

			// ����response
			response.reset();

			// ���ñ�ͷΪapplication/x-excel;charset=gb2312
			response.setContentType("application/x-excel;charset=gb2312");

			// ���õ���excel�ļ����ļ���
			String filename = new String((new String(finalStr + ".xls")).getBytes("GBK"), "ISO8859-1");

			// �������Ϊ����ģʽ
			response.setHeader("Content-Disposition", "attachement; filename=\"" + filename + "\"");

			// ���ñ��ػ�����Ϊchina
			response.setLocale(Locale.CHINA);

			// ��ȡ�����
			os = response.getOutputStream();

			// ����������
			wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("ͳ��", 0);

			// �����п�
			if (listInfo != null && listInfo.size() > 0) {
				int colSize = ((HashMap) (listInfo.get(0))).size();
				for (int i = 0; i < colSize; i++) {
					ws.setColumnView(i + 1, 25);
				}
			}

			// �ػ湤����
			ws = refreashSheet(listInfo, ws);
			wwb.write();
		} catch (Exception e) {
			// ��ӡ�����ջ��Ϣ
			e.printStackTrace();

			// �׳��쳣
			throw new Exception();
		} finally {
			// ����������Ϊ�գ��ر������
			if (wwb != null) {
				wwb.close();
			}
			// ����������Ϊ�գ��ر������
			if (wwb != null) {
				os.close();
			}
		}
		return null;
	}

	public static WritableSheet refreashSheet(List<Object> al, WritableSheet ws) throws Exception {
		// ��ʽ1
		WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfFC = new WritableCellFormat(wfc);
		// ���ñ���Ϊ��ɫ
		wcfFC.setBackground(jxl.format.Colour.ICE_BLUE);
		// ���ö��뷽ʽΪ���ж���
		wcfFC.setAlignment(Alignment.CENTRE);
		// ���ñ߿���ʽ
		wcfFC.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.MEDIUM);

		// ��ʽ2
		WritableFont wfc1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfFC1 = new WritableCellFormat(wfc1);
		// ���ñ���Ϊ��ɫ
		wcfFC1.setBackground(jxl.format.Colour.WHITE);
		// ���ö��뷽ʽΪ���ж���
		wcfFC1.setAlignment(Alignment.CENTRE);
		// ���ñ߿���ʽ
		wcfFC1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.MEDIUM);

		// ��ʽ3
		WritableFont wfc2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfFC2 = new WritableCellFormat(wfc2);
		// ���ñ���Ϊ��ɫ
		wcfFC2.setBackground(jxl.format.Colour.YELLOW);
		// ���ö��뷽ʽΪ���ж���
		wcfFC2.setAlignment(Alignment.CENTRE);
		// ���ñ߿���ʽ
		wcfFC2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.MEDIUM);

		// ��ʽ4
		WritableFont wfc3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfFC3 = new WritableCellFormat(wfc3);
		// ���ñ���Ϊ��ɫ
		wcfFC3.setBackground(jxl.format.Colour.ICE_BLUE);
		// ���ö��뷽ʽΪ�������
		wcfFC3.setAlignment(Alignment.LEFT);
		// ���ñ߿���ʽ
		wcfFC3.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.MEDIUM);

		// ��ʽ5
		WritableFont wf4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfF4 = new WritableCellFormat(wf4);
		// ���ñ���Ϊ��ɫ
		wcfF4.setBackground(jxl.format.Colour.WHITE);
		// ���ö��뷽ʽΪ���ж���
		wcfF4.setAlignment(Alignment.CENTRE);
		// ���ö��뷽ʽΪ���ж���
		wcfF4.setVerticalAlignment(VerticalAlignment.CENTRE);
		// ���ñ߿���ʽ
		wcfF4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.MEDIUM);

		// ��ʽ6
		WritableFont wfc5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfFC5 = new WritableCellFormat(wfc5);
		// ���ñ���Ϊ��ɫ
		wcfFC5.setBackground(jxl.format.Colour.ORANGE);
		// ���ö��뷽ʽΪ���ж���
		wcfFC5.setAlignment(Alignment.CENTRE);
		// ���ñ߿���ʽ
		wcfFC5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.MEDIUM);

		// �����п�
		int colSize = ((HashMap) (al.get(0))).size();

		// ���ñ�����ʽ
		Label tableHead = new Label(1, 1, "ͳ��Excel", wcfFC);
		ws.addCell(tableHead);
		ws.mergeCells(1, 1, colSize - 3, 1);

		// ���ñ�ͷ(�к�,�к� ,���� )
		Label label_0 = new Label(1, 3, "�û��ʺ�", wcfFC5);
		ws.addCell(label_0);
		Label label_1 = new Label(2, 3, "��Ա����", wcfFC5);
		ws.addCell(label_1);
		Label label_2 = new Label(3, 3, "��ʼʱ��", wcfFC5);
		ws.addCell(label_2);
		Label label_3 = new Label(4, 3, "����ʱ��", wcfFC5);
		ws.addCell(label_3);
		Label label_4 = new Label(5, 3, "ʣ�����", wcfFC5);
		ws.addCell(label_4);
		Label label_5 = new Label(6, 3, "֧����ʽ", wcfFC5);
		ws.addCell(label_5);
		Label label_6 = new Label(7, 3, "������", wcfFC5);
		ws.addCell(label_6);
		Label label_7 = new Label(8, 3, "����ʱ��", wcfFC5);
		ws.addCell(label_7);
		Label label_8 = new Label(9, 3, "����״̬", wcfFC5);
		ws.addCell(label_8);
		Label label_9 = new Label(10, 3, "Id", wcfFC5);
		ws.addCell(label_9);
		Label label_10 = new Label(11, 3, "����ʱ��", wcfFC5);
		ws.addCell(label_10);
		Label label_11 = new Label(12, 3, "��ע", wcfFC5);
		ws.addCell(label_11);

		// ���ñ���
		for (int i = 0; i < al.size(); i++) {
			String yonghuzhanghao = ((HashMap) (al.get(i))).get("yonghuzhanghao").toString();
			label_0 = new Label(1, i + 4, yonghuzhanghao, wcfF4);
			ws.addCell(label_0);
			String huiyuanleixing = ((HashMap) (al.get(i))).get("huiyuanleixing").toString();
			label_1 = new Label(2, i + 4, huiyuanleixing, wcfF4);
			ws.addCell(label_1);
			String kaishishijian = ((HashMap) (al.get(i))).get("kaishishijian").toString();
			label_2 = new Label(3, i + 4, kaishishijian, wcfF4);
			ws.addCell(label_2);
			String daoqishijian = ((HashMap) (al.get(i))).get("daoqishijian").toString();
			label_3 = new Label(4, i + 4, daoqishijian, wcfF4);
			ws.addCell(label_3);
			String shengyucishu = ((HashMap) (al.get(i))).get("shengyucishu").toString();
			label_4 = new Label(5, i + 4, shengyucishu, wcfF4);
			ws.addCell(label_4);
			String zhifufangshi = ((HashMap) (al.get(i))).get("zhifufangshi").toString();
			label_5 = new Label(6, i + 4, zhifufangshi, wcfF4);
			ws.addCell(label_5);
			String shouliren = ((HashMap) (al.get(i))).get("shouliren").toString();
			label_6 = new Label(7, i + 4, shouliren, wcfF4);
			ws.addCell(label_6);
			String shoulishijian = ((HashMap) (al.get(i))).get("shoulishijian").toString();
			label_7 = new Label(8, i + 4, shoulishijian, wcfF4);
			ws.addCell(label_7);
			String shenpi = ((HashMap) (al.get(i))).get("shenpi").toString();
			label_8 = new Label(9, i + 4, shenpi, wcfF4);
			ws.addCell(label_8);
			String id = ((HashMap) (al.get(i))).get("id").toString();
			label_9 = new Label(10, i + 4, id, wcfF4);
			ws.addCell(label_9);
			String itime = ((HashMap) (al.get(i))).get("itime").toString();
			label_10 = new Label(11, i + 4, itime, wcfF4);
			ws.addCell(label_10);
			String detail = ((HashMap) (al.get(i))).get("detail").toString();
			label_11 = new Label(12, i + 4, detail, wcfF4);
			ws.addCell(label_11);
		}

		return ws;
	}

	public ModelAndView doAddPicSubmit(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �������������
		PrintWriter pw = null;
		try {
			// ���ñ���ΪGB2312
			response.setCharacterEncoding("GB2312");

			// ��ʼ�������
			pw = response.getWriter();

			// ��ȡDiskFileItemFactory����
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// ���û�������СΪ4096k
			factory.setSizeThreshold(4096);

			// �����ϴ�����
			ServletFileUpload upload = new ServletFileUpload(factory);

			// �����ϴ��������
			upload.setSizeMax(Global.UPLOAD_FILE_MAX_SIZE);

			// ����ϴ��ļ���Ϊ���ĵ�����
			upload.setHeaderEncoding("gb2312");

			// ��ȡID
			String id = request.getParameter("id");

			// ����ͼƬID
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("id", id);
			String tuPianIndex = "";

			// ��ѯͼƬ��Ϣ
			List list = sqlMap.queryForList("Zhucehuiyuan.getTuPianIndex", whereMap);
			if (list != null && list.size() > 0) {
				if (list.get(0) != null) {
					tuPianIndex = ((HashMap) (list.get(0))).get("tuPian").toString();
				}
			}

			// �����ļ��У����ϴ�ͼƬ
			if (tuPianIndex.equals("")) {
				tuPianIndex = DateUtil.parse(new Date(), "yyyyMMddHHmmss") + (int) (Math.random() * 10);
				whereMap.put("tuPianIndex", tuPianIndex);
				sqlMap.update("Zhucehuiyuan.updateTuPianIndex", whereMap);
				File f = new File(InitSystemConfig.UPLOAD_FILE_PATH + tuPianIndex);
				f.mkdir();
			}

			// �õ����ύ����
			List fileItems = upload.parseRequest(request);
			Iterator iter = fileItems.iterator();

			whereMap.clear();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				/* �ļ��� */
				if (!item.isFormField()) {
					// ��ȡ�ļ�����
					String filePath = item.getName();
					filePath = filePath.substring(filePath.lastIndexOf("\\") + 1);
					whereMap.put("picName", filePath);

					// ��ȡ�ļ�·��
					String uploadPath = InitSystemConfig.UPLOAD_FILE_PATH + tuPianIndex + "/" + filePath;
					whereMap.put("picPath", uploadPath);

					// �ļ������ڣ�������
					if (!new File(uploadPath).exists()) {
						whereMap.put("operatorId", SysInfo.getLoginUserId(request, response));
						whereMap.put("tuPianIndex", tuPianIndex);
						sqlMap.insert("Zhucehuiyuan.insertTuPian", whereMap);
					}

					// �����ļ��ϴ�
					File file = new File(uploadPath);
					item.write(file);

				}
			}

			/* �������ı��ύ���������� ContentTypeΪtext/html */
			response.setContentType("text/html");

			// ��ʾ�����ɹ�
			pw.write("{success:true,msg:'����ͼƬ�ɹ���'}");

		} catch (Exception e) {
			// ��ӡ�����ջ
			e.printStackTrace();
			try {
				// ��ǰ̨��ʾ����ʧ����Ϣ
				pw.write("{success:false,msg:'" + e.getMessage() + "'}");
			} catch (Exception e1) {
				// �׳������쳣
				throw new Exception("����ͼƬʧ��");
			}
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}

		return null;
	}

	public ModelAndView doGetPicJsonStore(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) {
		// �������������
		PrintWriter pw = null;
		try {
			// ���ò�ѯͼƬ����
			HashMap<String, Object> where = new HashMap<String, Object>();
			String id = request.getParameter("id");
			if (id != null && !id.equals("")) {
				where.put("id", id);
			}

			// �õ���ѯͼƬ�����Ϣ
			String json = IbatisUtil.queryForPage(sqlMap, request, response, where, "Zhucehuiyuan.selectePicList");

			// ���ñ���Ϊutf8
			response.setCharacterEncoding("utf-8");

			// ��ʼ�������
			pw = response.getWriter();

			// ��ǰ̨������
			pw.write(json);
		} catch (Exception e) {
			// ��ӡ�����ջ��Ϣ
			e.printStackTrace();
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}
		return null;
	}

	public ModelAndView doDelePic(SqlMapClient sqlMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �������������
		PrintWriter pw = null;
		try {
			// ������Ҫɾ��ͼƬ��ID
			String id = request.getParameter("id");

			// ��ȡͼƬ��·��
			String picPath = request.getParameter("picPath");

			// ���ñ���Ϊutf8
			response.setCharacterEncoding("utf-8");

			// ��ʼ�������
			pw = response.getWriter();

			// ����ɾ������
			HashMap<String, Object> where = new HashMap<String, Object>();
			where.put("id", id);

			// �����ݿ��ύɾ����Ϣ
			sqlMap.delete("Zhucehuiyuan.doDelePic", where);

			// ���ļ�ɾ��
			File f = new File(picPath);
			if (f.exists()) {
				f.delete();
			}

			// �����ɹ���ʾ
			pw.write("{success:true,msg:'ɾ��ͼƬ�ɹ���'}");
		} catch (Exception e) {
			// ����ʧ����ʾ
			pw.write("{success:false,msg:'ɾ��ͼƬʧ�ܣ�'}");

			// ��ӡ�����ջ��Ϣ
			e.printStackTrace();

			// �������׳��쳣
			throw new Exception();
		} finally {
			// ����������Ϊ�գ��ر������
			if (pw != null) {
				pw.close();
			}
		}
		return null;
	}
	
	public String drawXYLineChart(SqlMapClient sqlMap,HttpServletRequest request, HttpServletResponse response) {
		String filename = "";

		String graphURL = "";
		PrintWriter pw = null;
		try {
			
			String guanzhongmingchen = request.getParameter("guanzhongmingchen");
			
			HashMap where = new HashMap();
			List al = IbatisUtil.queryForList(sqlMap, request, response, where, "Xiaofeiqiandao.dochart1");
			
			JFreeChart chart = null;
			
			DefaultCategoryDataset result = new DefaultCategoryDataset();
			for (int i = 0; i <  al.size(); i++) {
				String pinglungeshu = ((HashMap)(al.get(i))).get("cishu").toString();
				String dianyingmingchen = ((HashMap)(al.get(i))).get("huiyuanleixing").toString();
				result.addValue(Double.parseDouble(pinglungeshu), dianyingmingchen, "��Ա�ֲ����");
			}
			
		
			chart = ChartFactory.createBarChart3D("����ͳ��", "ͼ��", "��Ա�ֲ���", result, PlotOrientation.VERTICAL, true, false, false);
			
			TextTitle text = new TextTitle("��Ա�ֲ����ͼ");
			text.setPaint(new Color(102, 102, 102));
			chart.setTitle(text);
			
			CategoryPlot plot = chart.getCategoryPlot();
			// �������񱳾���ɫ
			plot.setBackgroundPaint(Color.white);
			// ��������������ɫ
			plot.setDomainGridlinePaint(Color.pink);
			// �������������ɫ
			plot.setRangeGridlinePaint(new Color(221,221,221));
			// ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
			BarRenderer3D renderer = new BarRenderer3D();
			renderer.setBaseOutlinePaint(Color.BLACK);
			renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);
			// Ĭ�ϵ�������ʾ�������У�ͨ����������ɵ������ֵ���ʾ
			// ע�⣺�˾�ܹؼ������޴˾䣬�����ֵ���ʾ�ᱻ���ǣ���������û����ʾ����������
			// renderer.setBasePositiveItemLabelPosition(new
			// ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
			// TextAnchor.BASELINE_LEFT));
			// renderer.setItemLabelAnchorOffset(10D);
			// ������ʾ��������
			renderer.setPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			renderer.setItemLabelAnchorOffset(10d);
			renderer.setItemLabelsVisible(true);

			//renderer.setMaxBarWidth(0.05); // ���Ŀ��
			renderer.setSeriesPaint(0, new Color(0, 191, 255));// ������ɫ
			// �������Ӹ߶�
			renderer.setMinimumBarLength(0.2);
			// �������ӱ߿���ɫ
			//renderer.setBaseOutlinePaint(new Color(221,221,221));
			// �������ӱ߿�ɼ�
			//renderer.setDrawBarOutline(true);
			// ��������͸����
			plot.setForegroundAlpha(0.65f);

			NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
			numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			// ���ñ��������
			String fontB = "����";
			// �������������Ƶ�����
			numberaxis.setLabelFont(new Font(fontB, Font.PLAIN, 16));
			// ��������������ʾ����������
			numberaxis.setTickLabelFont(new Font(fontB, Font.PLAIN, 13));
			// ���ú��������Ƶ�����
			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis.setLabelFont(new Font(fontB, Font.PLAIN, 12));
			// ���ú���������ʾ����ҵ�����������
			categoryaxis.setTickLabelFont(new Font(fontB, Font.PLAIN, 12));

			// ����ÿ��������������ƽ������֮�����
			// renderer.setItemMargin(0.0);
			plot.setRenderer(renderer);
			
			chart.getLegend().setItemFont(new Font("SimSun", 0, 12));
			
			int width = 880;//����ͼƬ�Ŀ�
			int height = 380;//����ͼƬ�ĸ�

			ByteArrayOutputStream bis = new ByteArrayOutputStream();
			pw = new PrintWriter(bis);
			filename = ServletUtilities.saveChartAsPNG(chart, width, height, null, request.getSession(false));
			graphURL = request.getContextPath() + "/servlet/displayChart?filename=" + filename;
			System.out.println("graphURL====" + graphURL);
			return graphURL;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		return null;
	}
}
