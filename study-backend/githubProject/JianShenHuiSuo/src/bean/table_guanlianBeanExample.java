package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class table_guanlianBeanExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public table_guanlianBeanExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    protected table_guanlianBeanExample(table_guanlianBeanExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table table_guanlian
     *
     * @ibatorgenerated Tue Dec 26 10:25:44 CST 2017
     */
    public static class Criteria {
        protected List<String> criteriaWithoutValue;

        protected List<Map<String, Object>> criteriaWithSingleValue;

        protected List<Map<String, Object>> criteriaWithListValue;

        protected List<Map<String, Object>> criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList<String>();
            criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
            criteriaWithListValue = new ArrayList<Map<String, Object>>();
            criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List<String> getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List<Map<String, Object>> getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List<Map<String, Object>> getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List<Map<String, Object>> getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List<? extends Object> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List<Object> list = new ArrayList<Object>();
            list.add(value1);
            list.add(value2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return this;
        }

        public Criteria andFieldNameIsNull() {
            addCriterion("field_name is null");
            return this;
        }

        public Criteria andFieldNameIsNotNull() {
            addCriterion("field_name is not null");
            return this;
        }

        public Criteria andFieldNameEqualTo(String value) {
            addCriterion("field_name =", value, "fieldName");
            return this;
        }

        public Criteria andFieldNameNotEqualTo(String value) {
            addCriterion("field_name <>", value, "fieldName");
            return this;
        }

        public Criteria andFieldNameGreaterThan(String value) {
            addCriterion("field_name >", value, "fieldName");
            return this;
        }

        public Criteria andFieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("field_name >=", value, "fieldName");
            return this;
        }

        public Criteria andFieldNameLessThan(String value) {
            addCriterion("field_name <", value, "fieldName");
            return this;
        }

        public Criteria andFieldNameLessThanOrEqualTo(String value) {
            addCriterion("field_name <=", value, "fieldName");
            return this;
        }

        public Criteria andFieldNameLike(String value) {
            addCriterion("field_name like", value, "fieldName");
            return this;
        }

        public Criteria andFieldNameNotLike(String value) {
            addCriterion("field_name not like", value, "fieldName");
            return this;
        }

        public Criteria andFieldNameIn(List<String> values) {
            addCriterion("field_name in", values, "fieldName");
            return this;
        }

        public Criteria andFieldNameNotIn(List<String> values) {
            addCriterion("field_name not in", values, "fieldName");
            return this;
        }

        public Criteria andFieldNameBetween(String value1, String value2) {
            addCriterion("field_name between", value1, value2, "fieldName");
            return this;
        }

        public Criteria andFieldNameNotBetween(String value1, String value2) {
            addCriterion("field_name not between", value1, value2, "fieldName");
            return this;
        }

        public Criteria andGuanlianbiaoIsNull() {
            addCriterion("guanlianbiao is null");
            return this;
        }

        public Criteria andGuanlianbiaoIsNotNull() {
            addCriterion("guanlianbiao is not null");
            return this;
        }

        public Criteria andGuanlianbiaoEqualTo(String value) {
            addCriterion("guanlianbiao =", value, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianbiaoNotEqualTo(String value) {
            addCriterion("guanlianbiao <>", value, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianbiaoGreaterThan(String value) {
            addCriterion("guanlianbiao >", value, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianbiaoGreaterThanOrEqualTo(String value) {
            addCriterion("guanlianbiao >=", value, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianbiaoLessThan(String value) {
            addCriterion("guanlianbiao <", value, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianbiaoLessThanOrEqualTo(String value) {
            addCriterion("guanlianbiao <=", value, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianbiaoLike(String value) {
            addCriterion("guanlianbiao like", value, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianbiaoNotLike(String value) {
            addCriterion("guanlianbiao not like", value, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianbiaoIn(List<String> values) {
            addCriterion("guanlianbiao in", values, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianbiaoNotIn(List<String> values) {
            addCriterion("guanlianbiao not in", values, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianbiaoBetween(String value1, String value2) {
            addCriterion("guanlianbiao between", value1, value2, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianbiaoNotBetween(String value1, String value2) {
            addCriterion("guanlianbiao not between", value1, value2, "guanlianbiao");
            return this;
        }

        public Criteria andGuanlianziduanIsNull() {
            addCriterion("guanlianziduan is null");
            return this;
        }

        public Criteria andGuanlianziduanIsNotNull() {
            addCriterion("guanlianziduan is not null");
            return this;
        }

        public Criteria andGuanlianziduanEqualTo(String value) {
            addCriterion("guanlianziduan =", value, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianziduanNotEqualTo(String value) {
            addCriterion("guanlianziduan <>", value, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianziduanGreaterThan(String value) {
            addCriterion("guanlianziduan >", value, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianziduanGreaterThanOrEqualTo(String value) {
            addCriterion("guanlianziduan >=", value, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianziduanLessThan(String value) {
            addCriterion("guanlianziduan <", value, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianziduanLessThanOrEqualTo(String value) {
            addCriterion("guanlianziduan <=", value, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianziduanLike(String value) {
            addCriterion("guanlianziduan like", value, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianziduanNotLike(String value) {
            addCriterion("guanlianziduan not like", value, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianziduanIn(List<String> values) {
            addCriterion("guanlianziduan in", values, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianziduanNotIn(List<String> values) {
            addCriterion("guanlianziduan not in", values, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianziduanBetween(String value1, String value2) {
            addCriterion("guanlianziduan between", value1, value2, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianziduanNotBetween(String value1, String value2) {
            addCriterion("guanlianziduan not between", value1, value2, "guanlianziduan");
            return this;
        }

        public Criteria andGuanlianIsNull() {
            addCriterion("guanlian is null");
            return this;
        }

        public Criteria andGuanlianIsNotNull() {
            addCriterion("guanlian is not null");
            return this;
        }

        public Criteria andGuanlianEqualTo(Integer value) {
            addCriterion("guanlian =", value, "guanlian");
            return this;
        }

        public Criteria andGuanlianNotEqualTo(Integer value) {
            addCriterion("guanlian <>", value, "guanlian");
            return this;
        }

        public Criteria andGuanlianGreaterThan(Integer value) {
            addCriterion("guanlian >", value, "guanlian");
            return this;
        }

        public Criteria andGuanlianGreaterThanOrEqualTo(Integer value) {
            addCriterion("guanlian >=", value, "guanlian");
            return this;
        }

        public Criteria andGuanlianLessThan(Integer value) {
            addCriterion("guanlian <", value, "guanlian");
            return this;
        }

        public Criteria andGuanlianLessThanOrEqualTo(Integer value) {
            addCriterion("guanlian <=", value, "guanlian");
            return this;
        }

        public Criteria andGuanlianIn(List<Integer> values) {
            addCriterion("guanlian in", values, "guanlian");
            return this;
        }

        public Criteria andGuanlianNotIn(List<Integer> values) {
            addCriterion("guanlian not in", values, "guanlian");
            return this;
        }

        public Criteria andGuanlianBetween(Integer value1, Integer value2) {
            addCriterion("guanlian between", value1, value2, "guanlian");
            return this;
        }

        public Criteria andGuanlianNotBetween(Integer value1, Integer value2) {
            addCriterion("guanlian not between", value1, value2, "guanlian");
            return this;
        }

        public Criteria andBitianIsNull() {
            addCriterion("bitian is null");
            return this;
        }

        public Criteria andBitianIsNotNull() {
            addCriterion("bitian is not null");
            return this;
        }

        public Criteria andBitianEqualTo(Integer value) {
            addCriterion("bitian =", value, "bitian");
            return this;
        }

        public Criteria andBitianNotEqualTo(Integer value) {
            addCriterion("bitian <>", value, "bitian");
            return this;
        }

        public Criteria andBitianGreaterThan(Integer value) {
            addCriterion("bitian >", value, "bitian");
            return this;
        }

        public Criteria andBitianGreaterThanOrEqualTo(Integer value) {
            addCriterion("bitian >=", value, "bitian");
            return this;
        }

        public Criteria andBitianLessThan(Integer value) {
            addCriterion("bitian <", value, "bitian");
            return this;
        }

        public Criteria andBitianLessThanOrEqualTo(Integer value) {
            addCriterion("bitian <=", value, "bitian");
            return this;
        }

        public Criteria andBitianIn(List<Integer> values) {
            addCriterion("bitian in", values, "bitian");
            return this;
        }

        public Criteria andBitianNotIn(List<Integer> values) {
            addCriterion("bitian not in", values, "bitian");
            return this;
        }

        public Criteria andBitianBetween(Integer value1, Integer value2) {
            addCriterion("bitian between", value1, value2, "bitian");
            return this;
        }

        public Criteria andBitianNotBetween(Integer value1, Integer value2) {
            addCriterion("bitian not between", value1, value2, "bitian");
            return this;
        }

        public Criteria andZhiduIsNull() {
            addCriterion("zhidu is null");
            return this;
        }

        public Criteria andZhiduIsNotNull() {
            addCriterion("zhidu is not null");
            return this;
        }

        public Criteria andZhiduEqualTo(Integer value) {
            addCriterion("zhidu =", value, "zhidu");
            return this;
        }

        public Criteria andZhiduNotEqualTo(Integer value) {
            addCriterion("zhidu <>", value, "zhidu");
            return this;
        }

        public Criteria andZhiduGreaterThan(Integer value) {
            addCriterion("zhidu >", value, "zhidu");
            return this;
        }

        public Criteria andZhiduGreaterThanOrEqualTo(Integer value) {
            addCriterion("zhidu >=", value, "zhidu");
            return this;
        }

        public Criteria andZhiduLessThan(Integer value) {
            addCriterion("zhidu <", value, "zhidu");
            return this;
        }

        public Criteria andZhiduLessThanOrEqualTo(Integer value) {
            addCriterion("zhidu <=", value, "zhidu");
            return this;
        }

        public Criteria andZhiduIn(List<Integer> values) {
            addCriterion("zhidu in", values, "zhidu");
            return this;
        }

        public Criteria andZhiduNotIn(List<Integer> values) {
            addCriterion("zhidu not in", values, "zhidu");
            return this;
        }

        public Criteria andZhiduBetween(Integer value1, Integer value2) {
            addCriterion("zhidu between", value1, value2, "zhidu");
            return this;
        }

        public Criteria andZhiduNotBetween(Integer value1, Integer value2) {
            addCriterion("zhidu not between", value1, value2, "zhidu");
            return this;
        }

        public Criteria andMorenzhiIsNull() {
            addCriterion("morenzhi is null");
            return this;
        }

        public Criteria andMorenzhiIsNotNull() {
            addCriterion("morenzhi is not null");
            return this;
        }

        public Criteria andMorenzhiEqualTo(String value) {
            addCriterion("morenzhi =", value, "morenzhi");
            return this;
        }

        public Criteria andMorenzhiNotEqualTo(String value) {
            addCriterion("morenzhi <>", value, "morenzhi");
            return this;
        }

        public Criteria andMorenzhiGreaterThan(String value) {
            addCriterion("morenzhi >", value, "morenzhi");
            return this;
        }

        public Criteria andMorenzhiGreaterThanOrEqualTo(String value) {
            addCriterion("morenzhi >=", value, "morenzhi");
            return this;
        }

        public Criteria andMorenzhiLessThan(String value) {
            addCriterion("morenzhi <", value, "morenzhi");
            return this;
        }

        public Criteria andMorenzhiLessThanOrEqualTo(String value) {
            addCriterion("morenzhi <=", value, "morenzhi");
            return this;
        }

        public Criteria andMorenzhiLike(String value) {
            addCriterion("morenzhi like", value, "morenzhi");
            return this;
        }

        public Criteria andMorenzhiNotLike(String value) {
            addCriterion("morenzhi not like", value, "morenzhi");
            return this;
        }

        public Criteria andMorenzhiIn(List<String> values) {
            addCriterion("morenzhi in", values, "morenzhi");
            return this;
        }

        public Criteria andMorenzhiNotIn(List<String> values) {
            addCriterion("morenzhi not in", values, "morenzhi");
            return this;
        }

        public Criteria andMorenzhiBetween(String value1, String value2) {
            addCriterion("morenzhi between", value1, value2, "morenzhi");
            return this;
        }

        public Criteria andMorenzhiNotBetween(String value1, String value2) {
            addCriterion("morenzhi not between", value1, value2, "morenzhi");
            return this;
        }

        public Criteria andZiduanleixingIsNull() {
            addCriterion("ziduanleixing is null");
            return this;
        }

        public Criteria andZiduanleixingIsNotNull() {
            addCriterion("ziduanleixing is not null");
            return this;
        }

        public Criteria andZiduanleixingEqualTo(String value) {
            addCriterion("ziduanleixing =", value, "ziduanleixing");
            return this;
        }

        public Criteria andZiduanleixingNotEqualTo(String value) {
            addCriterion("ziduanleixing <>", value, "ziduanleixing");
            return this;
        }

        public Criteria andZiduanleixingGreaterThan(String value) {
            addCriterion("ziduanleixing >", value, "ziduanleixing");
            return this;
        }

        public Criteria andZiduanleixingGreaterThanOrEqualTo(String value) {
            addCriterion("ziduanleixing >=", value, "ziduanleixing");
            return this;
        }

        public Criteria andZiduanleixingLessThan(String value) {
            addCriterion("ziduanleixing <", value, "ziduanleixing");
            return this;
        }

        public Criteria andZiduanleixingLessThanOrEqualTo(String value) {
            addCriterion("ziduanleixing <=", value, "ziduanleixing");
            return this;
        }

        public Criteria andZiduanleixingLike(String value) {
            addCriterion("ziduanleixing like", value, "ziduanleixing");
            return this;
        }

        public Criteria andZiduanleixingNotLike(String value) {
            addCriterion("ziduanleixing not like", value, "ziduanleixing");
            return this;
        }

        public Criteria andZiduanleixingIn(List<String> values) {
            addCriterion("ziduanleixing in", values, "ziduanleixing");
            return this;
        }

        public Criteria andZiduanleixingNotIn(List<String> values) {
            addCriterion("ziduanleixing not in", values, "ziduanleixing");
            return this;
        }

        public Criteria andZiduanleixingBetween(String value1, String value2) {
            addCriterion("ziduanleixing between", value1, value2, "ziduanleixing");
            return this;
        }

        public Criteria andZiduanleixingNotBetween(String value1, String value2) {
            addCriterion("ziduanleixing not between", value1, value2, "ziduanleixing");
            return this;
        }
    }
}