package dao;

import bean.jianshenqixiexinxiBean;
import bean.jianshenqixiexinxiBeanExample;
import java.util.List;

public interface jianshenqixiexinxiBeanDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table jianshenqixiexinxi
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int countByExample(jianshenqixiexinxiBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table jianshenqixiexinxi
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int deleteByExample(jianshenqixiexinxiBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table jianshenqixiexinxi
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table jianshenqixiexinxi
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    void insert(jianshenqixiexinxiBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table jianshenqixiexinxi
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    void insertSelective(jianshenqixiexinxiBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table jianshenqixiexinxi
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    List<jianshenqixiexinxiBean> selectByExample(jianshenqixiexinxiBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table jianshenqixiexinxi
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    jianshenqixiexinxiBean selectByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table jianshenqixiexinxi
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByExampleSelective(jianshenqixiexinxiBean record, jianshenqixiexinxiBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table jianshenqixiexinxi
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByExample(jianshenqixiexinxiBean record, jianshenqixiexinxiBeanExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table jianshenqixiexinxi
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByPrimaryKeySelective(jianshenqixiexinxiBean record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table jianshenqixiexinxi
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    int updateByPrimaryKey(jianshenqixiexinxiBean record);
}