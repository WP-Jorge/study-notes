package dao;

import bean.zhucehuiyuanBean;
import bean.zhucehuiyuanBeanExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class zhucehuiyuanBeanDAOImpl extends SqlMapClientDaoSupport implements zhucehuiyuanBeanDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public zhucehuiyuanBeanDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int countByExample(zhucehuiyuanBeanExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("zhucehuiyuan.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int deleteByExample(zhucehuiyuanBeanExample example) {
        int rows = getSqlMapClientTemplate().delete("zhucehuiyuan.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int deleteByPrimaryKey(Integer id) {
        zhucehuiyuanBean key = new zhucehuiyuanBean();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("zhucehuiyuan.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public void insert(zhucehuiyuanBean record) {
        getSqlMapClientTemplate().insert("zhucehuiyuan.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public void insertSelective(zhucehuiyuanBean record) {
        getSqlMapClientTemplate().insert("zhucehuiyuan.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    @SuppressWarnings("unchecked")
    public List<zhucehuiyuanBean> selectByExample(zhucehuiyuanBeanExample example) {
        List<zhucehuiyuanBean> list = getSqlMapClientTemplate().queryForList("zhucehuiyuan.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public zhucehuiyuanBean selectByPrimaryKey(Integer id) {
        zhucehuiyuanBean key = new zhucehuiyuanBean();
        key.setId(id);
        zhucehuiyuanBean record = (zhucehuiyuanBean) getSqlMapClientTemplate().queryForObject("zhucehuiyuan.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByExampleSelective(zhucehuiyuanBean record, zhucehuiyuanBeanExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("zhucehuiyuan.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByExample(zhucehuiyuanBean record, zhucehuiyuanBeanExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("zhucehuiyuan.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByPrimaryKeySelective(zhucehuiyuanBean record) {
        int rows = getSqlMapClientTemplate().update("zhucehuiyuan.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByPrimaryKey(zhucehuiyuanBean record) {
        int rows = getSqlMapClientTemplate().update("zhucehuiyuan.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table zhucehuiyuan
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    private static class UpdateByExampleParms extends zhucehuiyuanBeanExample {
        private Object record;

        public UpdateByExampleParms(Object record, zhucehuiyuanBeanExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}