package dao;

import bean.table_guanlianBean;
import bean.table_guanlianBeanExample;
import java.util.List;

public interface table_guanlianBeanDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int countByExample(table_guanlianBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int deleteByExample(table_guanlianBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    void insert(table_guanlianBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    void insertSelective(table_guanlianBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    List<table_guanlianBean> selectByExample(table_guanlianBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    table_guanlianBean selectByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByExampleSelective(table_guanlianBean record, table_guanlianBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByExample(table_guanlianBean record, table_guanlianBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByPrimaryKeySelective(table_guanlianBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByPrimaryKey(table_guanlianBean record);
}