package dao;

import bean.huiyuanxuankeBean;
import bean.huiyuanxuankeBeanExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class huiyuanxuankeBeanDAOImpl extends SqlMapClientDaoSupport implements huiyuanxuankeBeanDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public huiyuanxuankeBeanDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int countByExample(huiyuanxuankeBeanExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("huiyuanxuanke.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int deleteByExample(huiyuanxuankeBeanExample example) {
        int rows = getSqlMapClientTemplate().delete("huiyuanxuanke.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int deleteByPrimaryKey(Integer id) {
        huiyuanxuankeBean key = new huiyuanxuankeBean();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("huiyuanxuanke.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public void insert(huiyuanxuankeBean record) {
        getSqlMapClientTemplate().insert("huiyuanxuanke.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public void insertSelective(huiyuanxuankeBean record) {
        getSqlMapClientTemplate().insert("huiyuanxuanke.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    @SuppressWarnings("unchecked")
    public List<huiyuanxuankeBean> selectByExample(huiyuanxuankeBeanExample example) {
        List<huiyuanxuankeBean> list = getSqlMapClientTemplate().queryForList("huiyuanxuanke.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public huiyuanxuankeBean selectByPrimaryKey(Integer id) {
        huiyuanxuankeBean key = new huiyuanxuankeBean();
        key.setId(id);
        huiyuanxuankeBean record = (huiyuanxuankeBean) getSqlMapClientTemplate().queryForObject("huiyuanxuanke.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByExampleSelective(huiyuanxuankeBean record, huiyuanxuankeBeanExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("huiyuanxuanke.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByExample(huiyuanxuankeBean record, huiyuanxuankeBeanExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("huiyuanxuanke.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByPrimaryKeySelective(huiyuanxuankeBean record) {
        int rows = getSqlMapClientTemplate().update("huiyuanxuanke.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByPrimaryKey(huiyuanxuankeBean record) {
        int rows = getSqlMapClientTemplate().update("huiyuanxuanke.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table huiyuanxuanke
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    private static class UpdateByExampleParms extends huiyuanxuankeBeanExample {
        private Object record;

        public UpdateByExampleParms(Object record, huiyuanxuankeBeanExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}