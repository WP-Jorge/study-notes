package dao;

import bean.xiaofeiqiandaoBean;
import bean.xiaofeiqiandaoBeanExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class xiaofeiqiandaoBeanDAOImpl extends SqlMapClientDaoSupport implements xiaofeiqiandaoBeanDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public xiaofeiqiandaoBeanDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int countByExample(xiaofeiqiandaoBeanExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("xiaofeiqiandao.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int deleteByExample(xiaofeiqiandaoBeanExample example) {
        int rows = getSqlMapClientTemplate().delete("xiaofeiqiandao.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int deleteByPrimaryKey(Integer id) {
        xiaofeiqiandaoBean key = new xiaofeiqiandaoBean();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("xiaofeiqiandao.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public void insert(xiaofeiqiandaoBean record) {
        getSqlMapClientTemplate().insert("xiaofeiqiandao.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public void insertSelective(xiaofeiqiandaoBean record) {
        getSqlMapClientTemplate().insert("xiaofeiqiandao.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    @SuppressWarnings("unchecked")
    public List<xiaofeiqiandaoBean> selectByExample(xiaofeiqiandaoBeanExample example) {
        List<xiaofeiqiandaoBean> list = getSqlMapClientTemplate().queryForList("xiaofeiqiandao.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public xiaofeiqiandaoBean selectByPrimaryKey(Integer id) {
        xiaofeiqiandaoBean key = new xiaofeiqiandaoBean();
        key.setId(id);
        xiaofeiqiandaoBean record = (xiaofeiqiandaoBean) getSqlMapClientTemplate().queryForObject("xiaofeiqiandao.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByExampleSelective(xiaofeiqiandaoBean record, xiaofeiqiandaoBeanExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("xiaofeiqiandao.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByExample(xiaofeiqiandaoBean record, xiaofeiqiandaoBeanExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("xiaofeiqiandao.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByPrimaryKeySelective(xiaofeiqiandaoBean record) {
        int rows = getSqlMapClientTemplate().update("xiaofeiqiandao.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public int updateByPrimaryKey(xiaofeiqiandaoBean record) {
        int rows = getSqlMapClientTemplate().update("xiaofeiqiandao.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table xiaofeiqiandao
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    private static class UpdateByExampleParms extends xiaofeiqiandaoBeanExample {
        private Object record;

        public UpdateByExampleParms(Object record, xiaofeiqiandaoBeanExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}