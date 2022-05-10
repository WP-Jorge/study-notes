package bean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class huiyuanxuankeBeanExample {
    protected String orderByClause;
    protected List<Criteria> oredCriteria;
    public huiyuanxuankeBeanExample() {
        oredCriteria = new ArrayList<Criteria>();
    }
    protected huiyuanxuankeBeanExample(huiyuanxuankeBeanExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    public String getOrderByClause() {
        return orderByClause;
    }
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }
    public void clear() {
        oredCriteria.clear();
    }
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

        public Criteria andHuiyuanzhanghaoIsNull() {
            addCriterion("huiyuanzhanghao is null");
            return this;
        }

        public Criteria andHuiyuanzhanghaoIsNotNull() {
            addCriterion("huiyuanzhanghao is not null");
            return this;
        }

        public Criteria andHuiyuanzhanghaoEqualTo(String value) {
            addCriterion("huiyuanzhanghao =", value, "huiyuanzhanghao");
            return this;
        }

        public Criteria andHuiyuanzhanghaoNotEqualTo(String value) {
            addCriterion("huiyuanzhanghao <>", value, "huiyuanzhanghao");
            return this;
        }

        public Criteria andHuiyuanzhanghaoGreaterThan(String value) {
            addCriterion("huiyuanzhanghao >", value, "huiyuanzhanghao");
            return this;
        }

        public Criteria andHuiyuanzhanghaoGreaterThanOrEqualTo(String value) {
            addCriterion("huiyuanzhanghao >=", value, "huiyuanzhanghao");
            return this;
        }

        public Criteria andHuiyuanzhanghaoLessThan(String value) {
            addCriterion("huiyuanzhanghao <", value, "huiyuanzhanghao");
            return this;
        }

        public Criteria andHuiyuanzhanghaoLessThanOrEqualTo(String value) {
            addCriterion("huiyuanzhanghao <=", value, "huiyuanzhanghao");
            return this;
        }

        public Criteria andHuiyuanzhanghaoLike(String value) {
            addCriterion("huiyuanzhanghao like", value, "huiyuanzhanghao");
            return this;
        }

        public Criteria andHuiyuanzhanghaoNotLike(String value) {
            addCriterion("huiyuanzhanghao not like", value, "huiyuanzhanghao");
            return this;
        }

        public Criteria andHuiyuanzhanghaoIn(List<String> values) {
            addCriterion("huiyuanzhanghao in", values, "huiyuanzhanghao");
            return this;
        }

        public Criteria andHuiyuanzhanghaoNotIn(List<String> values) {
            addCriterion("huiyuanzhanghao not in", values, "huiyuanzhanghao");
            return this;
        }

        public Criteria andHuiyuanzhanghaoBetween(String value1, String value2) {
            addCriterion("huiyuanzhanghao between", value1, value2, "huiyuanzhanghao");
            return this;
        }

        public Criteria andHuiyuanzhanghaoNotBetween(String value1, String value2) {
            addCriterion("huiyuanzhanghao not between", value1, value2, "huiyuanzhanghao");
            return this;
        }

        public Criteria andKechengmingchenIsNull() {
            addCriterion("kechengmingchen is null");
            return this;
        }

        public Criteria andKechengmingchenIsNotNull() {
            addCriterion("kechengmingchen is not null");
            return this;
        }

        public Criteria andKechengmingchenEqualTo(String value) {
            addCriterion("kechengmingchen =", value, "kechengmingchen");
            return this;
        }

        public Criteria andKechengmingchenNotEqualTo(String value) {
            addCriterion("kechengmingchen <>", value, "kechengmingchen");
            return this;
        }

        public Criteria andKechengmingchenGreaterThan(String value) {
            addCriterion("kechengmingchen >", value, "kechengmingchen");
            return this;
        }

        public Criteria andKechengmingchenGreaterThanOrEqualTo(String value) {
            addCriterion("kechengmingchen >=", value, "kechengmingchen");
            return this;
        }

        public Criteria andKechengmingchenLessThan(String value) {
            addCriterion("kechengmingchen <", value, "kechengmingchen");
            return this;
        }

        public Criteria andKechengmingchenLessThanOrEqualTo(String value) {
            addCriterion("kechengmingchen <=", value, "kechengmingchen");
            return this;
        }

        public Criteria andKechengmingchenLike(String value) {
            addCriterion("kechengmingchen like", value, "kechengmingchen");
            return this;
        }

        public Criteria andKechengmingchenNotLike(String value) {
            addCriterion("kechengmingchen not like", value, "kechengmingchen");
            return this;
        }

        public Criteria andKechengmingchenIn(List<String> values) {
            addCriterion("kechengmingchen in", values, "kechengmingchen");
            return this;
        }

        public Criteria andKechengmingchenNotIn(List<String> values) {
            addCriterion("kechengmingchen not in", values, "kechengmingchen");
            return this;
        }

        public Criteria andKechengmingchenBetween(String value1, String value2) {
            addCriterion("kechengmingchen between", value1, value2, "kechengmingchen");
            return this;
        }

        public Criteria andKechengmingchenNotBetween(String value1, String value2) {
            addCriterion("kechengmingchen not between", value1, value2, "kechengmingchen");
            return this;
        }

        public Criteria andXuandingriqiIsNull() {
            addCriterion("xuandingriqi is null");
            return this;
        }

        public Criteria andXuandingriqiIsNotNull() {
            addCriterion("xuandingriqi is not null");
            return this;
        }

        public Criteria andXuandingriqiEqualTo(String value) {
            addCriterion("xuandingriqi =", value, "xuandingriqi");
            return this;
        }

        public Criteria andXuandingriqiNotEqualTo(String value) {
            addCriterion("xuandingriqi <>", value, "xuandingriqi");
            return this;
        }

        public Criteria andXuandingriqiGreaterThan(String value) {
            addCriterion("xuandingriqi >", value, "xuandingriqi");
            return this;
        }

        public Criteria andXuandingriqiGreaterThanOrEqualTo(String value) {
            addCriterion("xuandingriqi >=", value, "xuandingriqi");
            return this;
        }

        public Criteria andXuandingriqiLessThan(String value) {
            addCriterion("xuandingriqi <", value, "xuandingriqi");
            return this;
        }

        public Criteria andXuandingriqiLessThanOrEqualTo(String value) {
            addCriterion("xuandingriqi <=", value, "xuandingriqi");
            return this;
        }

        public Criteria andXuandingriqiLike(String value) {
            addCriterion("xuandingriqi like", value, "xuandingriqi");
            return this;
        }

        public Criteria andXuandingriqiNotLike(String value) {
            addCriterion("xuandingriqi not like", value, "xuandingriqi");
            return this;
        }

        public Criteria andXuandingriqiIn(List<String> values) {
            addCriterion("xuandingriqi in", values, "xuandingriqi");
            return this;
        }

        public Criteria andXuandingriqiNotIn(List<String> values) {
            addCriterion("xuandingriqi not in", values, "xuandingriqi");
            return this;
        }

        public Criteria andXuandingriqiBetween(String value1, String value2) {
            addCriterion("xuandingriqi between", value1, value2, "xuandingriqi");
            return this;
        }

        public Criteria andXuandingriqiNotBetween(String value1, String value2) {
            addCriterion("xuandingriqi not between", value1, value2, "xuandingriqi");
            return this;
        }

        public Criteria andOperatoridIsNull() {
            addCriterion("operatorId is null");
            return this;
        }

        public Criteria andOperatoridIsNotNull() {
            addCriterion("operatorId is not null");
            return this;
        }

        public Criteria andOperatoridEqualTo(String value) {
            addCriterion("operatorId =", value, "operatorid");
            return this;
        }

        public Criteria andOperatoridNotEqualTo(String value) {
            addCriterion("operatorId <>", value, "operatorid");
            return this;
        }

        public Criteria andOperatoridGreaterThan(String value) {
            addCriterion("operatorId >", value, "operatorid");
            return this;
        }

        public Criteria andOperatoridGreaterThanOrEqualTo(String value) {
            addCriterion("operatorId >=", value, "operatorid");
            return this;
        }

        public Criteria andOperatoridLessThan(String value) {
            addCriterion("operatorId <", value, "operatorid");
            return this;
        }

        public Criteria andOperatoridLessThanOrEqualTo(String value) {
            addCriterion("operatorId <=", value, "operatorid");
            return this;
        }

        public Criteria andOperatoridLike(String value) {
            addCriterion("operatorId like", value, "operatorid");
            return this;
        }

        public Criteria andOperatoridNotLike(String value) {
            addCriterion("operatorId not like", value, "operatorid");
            return this;
        }

        public Criteria andOperatoridIn(List<String> values) {
            addCriterion("operatorId in", values, "operatorid");
            return this;
        }

        public Criteria andOperatoridNotIn(List<String> values) {
            addCriterion("operatorId not in", values, "operatorid");
            return this;
        }

        public Criteria andOperatoridBetween(String value1, String value2) {
            addCriterion("operatorId between", value1, value2, "operatorid");
            return this;
        }

        public Criteria andOperatoridNotBetween(String value1, String value2) {
            addCriterion("operatorId not between", value1, value2, "operatorid");
            return this;
        }

        public Criteria andItimeIsNull() {
            addCriterion("itime is null");
            return this;
        }

        public Criteria andItimeIsNotNull() {
            addCriterion("itime is not null");
            return this;
        }

        public Criteria andItimeEqualTo(String value) {
            addCriterion("itime =", value, "itime");
            return this;
        }

        public Criteria andItimeNotEqualTo(String value) {
            addCriterion("itime <>", value, "itime");
            return this;
        }

        public Criteria andItimeGreaterThan(String value) {
            addCriterion("itime >", value, "itime");
            return this;
        }

        public Criteria andItimeGreaterThanOrEqualTo(String value) {
            addCriterion("itime >=", value, "itime");
            return this;
        }

        public Criteria andItimeLessThan(String value) {
            addCriterion("itime <", value, "itime");
            return this;
        }

        public Criteria andItimeLessThanOrEqualTo(String value) {
            addCriterion("itime <=", value, "itime");
            return this;
        }

        public Criteria andItimeLike(String value) {
            addCriterion("itime like", value, "itime");
            return this;
        }

        public Criteria andItimeNotLike(String value) {
            addCriterion("itime not like", value, "itime");
            return this;
        }

        public Criteria andItimeIn(List<String> values) {
            addCriterion("itime in", values, "itime");
            return this;
        }

        public Criteria andItimeNotIn(List<String> values) {
            addCriterion("itime not in", values, "itime");
            return this;
        }

        public Criteria andItimeBetween(String value1, String value2) {
            addCriterion("itime between", value1, value2, "itime");
            return this;
        }

        public Criteria andItimeNotBetween(String value1, String value2) {
            addCriterion("itime not between", value1, value2, "itime");
            return this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
            return this;
        }

        public Criteria andDeleteflagIsNull() {
            addCriterion("deleteFlag is null");
            return this;
        }

        public Criteria andDeleteflagIsNotNull() {
            addCriterion("deleteFlag is not null");
            return this;
        }

        public Criteria andDeleteflagEqualTo(Integer value) {
            addCriterion("deleteFlag =", value, "deleteflag");
            return this;
        }

        public Criteria andDeleteflagNotEqualTo(Integer value) {
            addCriterion("deleteFlag <>", value, "deleteflag");
            return this;
        }

        public Criteria andDeleteflagGreaterThan(Integer value) {
            addCriterion("deleteFlag >", value, "deleteflag");
            return this;
        }

        public Criteria andDeleteflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("deleteFlag >=", value, "deleteflag");
            return this;
        }

        public Criteria andDeleteflagLessThan(Integer value) {
            addCriterion("deleteFlag <", value, "deleteflag");
            return this;
        }

        public Criteria andDeleteflagLessThanOrEqualTo(Integer value) {
            addCriterion("deleteFlag <=", value, "deleteflag");
            return this;
        }

        public Criteria andDeleteflagIn(List<Integer> values) {
            addCriterion("deleteFlag in", values, "deleteflag");
            return this;
        }

        public Criteria andDeleteflagNotIn(List<Integer> values) {
            addCriterion("deleteFlag not in", values, "deleteflag");
            return this;
        }

        public Criteria andDeleteflagBetween(Integer value1, Integer value2) {
            addCriterion("deleteFlag between", value1, value2, "deleteflag");
            return this;
        }

        public Criteria andDeleteflagNotBetween(Integer value1, Integer value2) {
            addCriterion("deleteFlag not between", value1, value2, "deleteflag");
            return this;
        }

        public Criteria andErjiguanlianzdIsNull() {
            addCriterion("erjiguanlianzd is null");
            return this;
        }

        public Criteria andErjiguanlianzdIsNotNull() {
            addCriterion("erjiguanlianzd is not null");
            return this;
        }

        public Criteria andErjiguanlianzdEqualTo(String value) {
            addCriterion("erjiguanlianzd =", value, "erjiguanlianzd");
            return this;
        }

        public Criteria andErjiguanlianzdNotEqualTo(String value) {
            addCriterion("erjiguanlianzd <>", value, "erjiguanlianzd");
            return this;
        }

        public Criteria andErjiguanlianzdGreaterThan(String value) {
            addCriterion("erjiguanlianzd >", value, "erjiguanlianzd");
            return this;
        }

        public Criteria andErjiguanlianzdGreaterThanOrEqualTo(String value) {
            addCriterion("erjiguanlianzd >=", value, "erjiguanlianzd");
            return this;
        }

        public Criteria andErjiguanlianzdLessThan(String value) {
            addCriterion("erjiguanlianzd <", value, "erjiguanlianzd");
            return this;
        }

        public Criteria andErjiguanlianzdLessThanOrEqualTo(String value) {
            addCriterion("erjiguanlianzd <=", value, "erjiguanlianzd");
            return this;
        }

        public Criteria andErjiguanlianzdLike(String value) {
            addCriterion("erjiguanlianzd like", value, "erjiguanlianzd");
            return this;
        }

        public Criteria andErjiguanlianzdNotLike(String value) {
            addCriterion("erjiguanlianzd not like", value, "erjiguanlianzd");
            return this;
        }

        public Criteria andErjiguanlianzdIn(List<String> values) {
            addCriterion("erjiguanlianzd in", values, "erjiguanlianzd");
            return this;
        }

        public Criteria andErjiguanlianzdNotIn(List<String> values) {
            addCriterion("erjiguanlianzd not in", values, "erjiguanlianzd");
            return this;
        }

        public Criteria andErjiguanlianzdBetween(String value1, String value2) {
            addCriterion("erjiguanlianzd between", value1, value2, "erjiguanlianzd");
            return this;
        }

        public Criteria andErjiguanlianzdNotBetween(String value1, String value2) {
            addCriterion("erjiguanlianzd not between", value1, value2, "erjiguanlianzd");
            return this;
        }
    }
}