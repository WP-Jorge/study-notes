package dao;

import bean.zhucehuiyuanBean;
import bean.zhucehuiyuanBeanExample;
import java.util.List;

public interface zhucehuiyuanBeanDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int countByExample(zhucehuiyuanBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int deleteByExample(zhucehuiyuanBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    void insert(zhucehuiyuanBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    void insertSelective(zhucehuiyuanBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    List<zhucehuiyuanBean> selectByExample(zhucehuiyuanBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    zhucehuiyuanBean selectByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByExampleSelective(zhucehuiyuanBean record, zhucehuiyuanBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByExample(zhucehuiyuanBean record, zhucehuiyuanBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByPrimaryKeySelective(zhucehuiyuanBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByPrimaryKey(zhucehuiyuanBean record);
}