package dao;

import bean.system_infoBean;
import bean.system_infoBeanExample;
import java.util.List;

public interface system_infoBeanDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table system_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int countByExample(system_infoBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table system_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int deleteByExample(system_infoBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table system_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table system_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    void insert(system_infoBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table system_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    void insertSelective(system_infoBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table system_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    List<system_infoBean> selectByExample(system_infoBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table system_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    system_infoBean selectByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table system_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByExampleSelective(system_infoBean record, system_infoBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table system_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByExample(system_infoBean record, system_infoBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table system_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByPrimaryKeySelective(system_infoBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table system_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByPrimaryKey(system_infoBean record);
}