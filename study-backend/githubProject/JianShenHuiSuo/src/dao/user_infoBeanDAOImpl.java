package dao;

import bean.user_infoBean;
import bean.user_infoBeanExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class user_infoBeanDAOImpl extends SqlMapClientDaoSupport implements user_infoBeanDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public user_infoBeanDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int countByExample(user_infoBeanExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("user_info.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int deleteByExample(user_infoBeanExample example) {
        int rows = getSqlMapClientTemplate().delete("user_info.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int deleteByPrimaryKey(Integer id) {
        user_infoBean key = new user_infoBean();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("user_info.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public void insert(user_infoBean record) {
        getSqlMapClientTemplate().insert("user_info.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public void insertSelective(user_infoBean record) {
        getSqlMapClientTemplate().insert("user_info.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    @SuppressWarnings("unchecked")
    public List<user_infoBean> selectByExample(user_infoBeanExample example) {
        List<user_infoBean> list = getSqlMapClientTemplate().queryForList("user_info.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public user_infoBean selectByPrimaryKey(Integer id) {
        user_infoBean key = new user_infoBean();
        key.setId(id);
        user_infoBean record = (user_infoBean) getSqlMapClientTemplate().queryForObject("user_info.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByExampleSelective(user_infoBean record, user_infoBeanExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("user_info.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByExample(user_infoBean record, user_infoBeanExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("user_info.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByPrimaryKeySelective(user_infoBean record) {
        int rows = getSqlMapClientTemplate().update("user_info.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByPrimaryKey(user_infoBean record) {
        int rows = getSqlMapClientTemplate().update("user_info.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table user_info
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    private static class UpdateByExampleParms extends user_infoBeanExample {
        private Object record;

        public UpdateByExampleParms(Object record, user_infoBeanExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}