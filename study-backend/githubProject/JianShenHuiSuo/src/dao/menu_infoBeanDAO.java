package dao;

import bean.menu_infoBean;
import bean.menu_infoBeanExample;
import java.util.List;

public interface menu_infoBeanDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table menu_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int countByExample(menu_infoBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table menu_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int deleteByExample(menu_infoBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table menu_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table menu_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    void insert(menu_infoBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table menu_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    void insertSelective(menu_infoBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table menu_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    List<menu_infoBean> selectByExample(menu_infoBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table menu_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    menu_infoBean selectByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table menu_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByExampleSelective(menu_infoBean record, menu_infoBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table menu_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByExample(menu_infoBean record, menu_infoBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table menu_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByPrimaryKeySelective(menu_infoBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table menu_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByPrimaryKey(menu_infoBean record);
}