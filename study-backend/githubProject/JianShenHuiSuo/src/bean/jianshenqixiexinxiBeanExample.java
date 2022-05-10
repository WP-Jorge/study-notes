package bean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class jianshenqixiexinxiBeanExample {
    protected String orderByClause;
    protected List<Criteria> oredCriteria;
    public jianshenqixiexinxiBeanExample() {
        oredCriteria = new ArrayList<Criteria>();
    }
    protected jianshenqixiexinxiBeanExample(jianshenqixiexinxiBeanExample example) {
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

        public Criteria andQixiemingchenIsNull() {
            addCriterion("qixiemingchen is null");
            return this;
        }

        public Criteria andQixiemingchenIsNotNull() {
            addCriterion("qixiemingchen is not null");
            return this;
        }

        public Criteria andQixiemingchenEqualTo(String value) {
            addCriterion("qixiemingchen =", value, "qixiemingchen");
            return this;
        }

        public Criteria andQixiemingchenNotEqualTo(String value) {
            addCriterion("qixiemingchen <>", value, "qixiemingchen");
            return this;
        }

        public Criteria andQixiemingchenGreaterThan(String value) {
            addCriterion("qixiemingchen >", value, "qixiemingchen");
            return this;
        }

        public Criteria andQixiemingchenGreaterThanOrEqualTo(String value) {
            addCriterion("qixiemingchen >=", value, "qixiemingchen");
            return this;
        }

        public Criteria andQixiemingchenLessThan(String value) {
            addCriterion("qixiemingchen <", value, "qixiemingchen");
            return this;
        }

        public Criteria andQixiemingchenLessThanOrEqualTo(String value) {
            addCriterion("qixiemingchen <=", value, "qixiemingchen");
            return this;
        }

        public Criteria andQixiemingchenLike(String value) {
            addCriterion("qixiemingchen like", value, "qixiemingchen");
            return this;
        }

        public Criteria andQixiemingchenNotLike(String value) {
            addCriterion("qixiemingchen not like", value, "qixiemingchen");
            return this;
        }

        public Criteria andQixiemingchenIn(List<String> values) {
            addCriterion("qixiemingchen in", values, "qixiemingchen");
            return this;
        }

        public Criteria andQixiemingchenNotIn(List<String> values) {
            addCriterion("qixiemingchen not in", values, "qixiemingchen");
            return this;
        }

        public Criteria andQixiemingchenBetween(String value1, String value2) {
            addCriterion("qixiemingchen between", value1, value2, "qixiemingchen");
            return this;
        }

        public Criteria andQixiemingchenNotBetween(String value1, String value2) {
            addCriterion("qixiemingchen not between", value1, value2, "qixiemingchen");
            return this;
        }

        public Criteria andCaigoushijianIsNull() {
            addCriterion("caigoushijian is null");
            return this;
        }

        public Criteria andCaigoushijianIsNotNull() {
            addCriterion("caigoushijian is not null");
            return this;
        }

        public Criteria andCaigoushijianEqualTo(String value) {
            addCriterion("caigoushijian =", value, "caigoushijian");
            return this;
        }

        public Criteria andCaigoushijianNotEqualTo(String value) {
            addCriterion("caigoushijian <>", value, "caigoushijian");
            return this;
        }

        public Criteria andCaigoushijianGreaterThan(String value) {
            addCriterion("caigoushijian >", value, "caigoushijian");
            return this;
        }

        public Criteria andCaigoushijianGreaterThanOrEqualTo(String value) {
            addCriterion("caigoushijian >=", value, "caigoushijian");
            return this;
        }

        public Criteria andCaigoushijianLessThan(String value) {
            addCriterion("caigoushijian <", value, "caigoushijian");
            return this;
        }

        public Criteria andCaigoushijianLessThanOrEqualTo(String value) {
            addCriterion("caigoushijian <=", value, "caigoushijian");
            return this;
        }

        public Criteria andCaigoushijianLike(String value) {
            addCriterion("caigoushijian like", value, "caigoushijian");
            return this;
        }

        public Criteria andCaigoushijianNotLike(String value) {
            addCriterion("caigoushijian not like", value, "caigoushijian");
            return this;
        }

        public Criteria andCaigoushijianIn(List<String> values) {
            addCriterion("caigoushijian in", values, "caigoushijian");
            return this;
        }

        public Criteria andCaigoushijianNotIn(List<String> values) {
            addCriterion("caigoushijian not in", values, "caigoushijian");
            return this;
        }

        public Criteria andCaigoushijianBetween(String value1, String value2) {
            addCriterion("caigoushijian between", value1, value2, "caigoushijian");
            return this;
        }

        public Criteria andCaigoushijianNotBetween(String value1, String value2) {
            addCriterion("caigoushijian not between", value1, value2, "caigoushijian");
            return this;
        }

        public Criteria andJiageIsNull() {
            addCriterion("jiage is null");
            return this;
        }

        public Criteria andJiageIsNotNull() {
            addCriterion("jiage is not null");
            return this;
        }

        public Criteria andJiageEqualTo(String value) {
            addCriterion("jiage =", value, "jiage");
            return this;
        }

        public Criteria andJiageNotEqualTo(String value) {
            addCriterion("jiage <>", value, "jiage");
            return this;
        }

        public Criteria andJiageGreaterThan(String value) {
            addCriterion("jiage >", value, "jiage");
            return this;
        }

        public Criteria andJiageGreaterThanOrEqualTo(String value) {
            addCriterion("jiage >=", value, "jiage");
            return this;
        }

        public Criteria andJiageLessThan(String value) {
            addCriterion("jiage <", value, "jiage");
            return this;
        }

        public Criteria andJiageLessThanOrEqualTo(String value) {
            addCriterion("jiage <=", value, "jiage");
            return this;
        }

        public Criteria andJiageLike(String value) {
            addCriterion("jiage like", value, "jiage");
            return this;
        }

        public Criteria andJiageNotLike(String value) {
            addCriterion("jiage not like", value, "jiage");
            return this;
        }

        public Criteria andJiageIn(List<String> values) {
            addCriterion("jiage in", values, "jiage");
            return this;
        }

        public Criteria andJiageNotIn(List<String> values) {
            addCriterion("jiage not in", values, "jiage");
            return this;
        }

        public Criteria andJiageBetween(String value1, String value2) {
            addCriterion("jiage between", value1, value2, "jiage");
            return this;
        }

        public Criteria andJiageNotBetween(String value1, String value2) {
            addCriterion("jiage not between", value1, value2, "jiage");
            return this;
        }

        public Criteria andGongnenmiaoshuIsNull() {
            addCriterion("gongnenmiaoshu is null");
            return this;
        }

        public Criteria andGongnenmiaoshuIsNotNull() {
            addCriterion("gongnenmiaoshu is not null");
            return this;
        }

        public Criteria andGongnenmiaoshuEqualTo(String value) {
            addCriterion("gongnenmiaoshu =", value, "gongnenmiaoshu");
            return this;
        }

        public Criteria andGongnenmiaoshuNotEqualTo(String value) {
            addCriterion("gongnenmiaoshu <>", value, "gongnenmiaoshu");
            return this;
        }

        public Criteria andGongnenmiaoshuGreaterThan(String value) {
            addCriterion("gongnenmiaoshu >", value, "gongnenmiaoshu");
            return this;
        }

        public Criteria andGongnenmiaoshuGreaterThanOrEqualTo(String value) {
            addCriterion("gongnenmiaoshu >=", value, "gongnenmiaoshu");
            return this;
        }

        public Criteria andGongnenmiaoshuLessThan(String value) {
            addCriterion("gongnenmiaoshu <", value, "gongnenmiaoshu");
            return this;
        }

        public Criteria andGongnenmiaoshuLessThanOrEqualTo(String value) {
            addCriterion("gongnenmiaoshu <=", value, "gongnenmiaoshu");
            return this;
        }

        public Criteria andGongnenmiaoshuLike(String value) {
            addCriterion("gongnenmiaoshu like", value, "gongnenmiaoshu");
            return this;
        }

        public Criteria andGongnenmiaoshuNotLike(String value) {
            addCriterion("gongnenmiaoshu not like", value, "gongnenmiaoshu");
            return this;
        }

        public Criteria andGongnenmiaoshuIn(List<String> values) {
            addCriterion("gongnenmiaoshu in", values, "gongnenmiaoshu");
            return this;
        }

        public Criteria andGongnenmiaoshuNotIn(List<String> values) {
            addCriterion("gongnenmiaoshu not in", values, "gongnenmiaoshu");
            return this;
        }

        public Criteria andGongnenmiaoshuBetween(String value1, String value2) {
            addCriterion("gongnenmiaoshu between", value1, value2, "gongnenmiaoshu");
            return this;
        }

        public Criteria andGongnenmiaoshuNotBetween(String value1, String value2) {
            addCriterion("gongnenmiaoshu not between", value1, value2, "gongnenmiaoshu");
            return this;
        }

        public Criteria andFujianIsNull() {
            addCriterion("fuJian is null");
            return this;
        }

        public Criteria andFujianIsNotNull() {
            addCriterion("fuJian is not null");
            return this;
        }

        public Criteria andFujianEqualTo(String value) {
            addCriterion("fuJian =", value, "fujian");
            return this;
        }

        public Criteria andFujianNotEqualTo(String value) {
            addCriterion("fuJian <>", value, "fujian");
            return this;
        }

        public Criteria andFujianGreaterThan(String value) {
            addCriterion("fuJian >", value, "fujian");
            return this;
        }

        public Criteria andFujianGreaterThanOrEqualTo(String value) {
            addCriterion("fuJian >=", value, "fujian");
            return this;
        }

        public Criteria andFujianLessThan(String value) {
            addCriterion("fuJian <", value, "fujian");
            return this;
        }

        public Criteria andFujianLessThanOrEqualTo(String value) {
            addCriterion("fuJian <=", value, "fujian");
            return this;
        }

        public Criteria andFujianLike(String value) {
            addCriterion("fuJian like", value, "fujian");
            return this;
        }

        public Criteria andFujianNotLike(String value) {
            addCriterion("fuJian not like", value, "fujian");
            return this;
        }

        public Criteria andFujianIn(List<String> values) {
            addCriterion("fuJian in", values, "fujian");
            return this;
        }

        public Criteria andFujianNotIn(List<String> values) {
            addCriterion("fuJian not in", values, "fujian");
            return this;
        }

        public Criteria andFujianBetween(String value1, String value2) {
            addCriterion("fuJian between", value1, value2, "fujian");
            return this;
        }

        public Criteria andFujianNotBetween(String value1, String value2) {
            addCriterion("fuJian not between", value1, value2, "fujian");
            return this;
        }

        public Criteria andTupianIsNull() {
            addCriterion("tuPian is null");
            return this;
        }

        public Criteria andTupianIsNotNull() {
            addCriterion("tuPian is not null");
            return this;
        }

        public Criteria andTupianEqualTo(String value) {
            addCriterion("tuPian =", value, "tupian");
            return this;
        }

        public Criteria andTupianNotEqualTo(String value) {
            addCriterion("tuPian <>", value, "tupian");
            return this;
        }

        public Criteria andTupianGreaterThan(String value) {
            addCriterion("tuPian >", value, "tupian");
            return this;
        }

        public Criteria andTupianGreaterThanOrEqualTo(String value) {
            addCriterion("tuPian >=", value, "tupian");
            return this;
        }

        public Criteria andTupianLessThan(String value) {
            addCriterion("tuPian <", value, "tupian");
            return this;
        }

        public Criteria andTupianLessThanOrEqualTo(String value) {
            addCriterion("tuPian <=", value, "tupian");
            return this;
        }

        public Criteria andTupianLike(String value) {
            addCriterion("tuPian like", value, "tupian");
            return this;
        }

        public Criteria andTupianNotLike(String value) {
            addCriterion("tuPian not like", value, "tupian");
            return this;
        }

        public Criteria andTupianIn(List<String> values) {
            addCriterion("tuPian in", values, "tupian");
            return this;
        }

        public Criteria andTupianNotIn(List<String> values) {
            addCriterion("tuPian not in", values, "tupian");
            return this;
        }

        public Criteria andTupianBetween(String value1, String value2) {
            addCriterion("tuPian between", value1, value2, "tupian");
            return this;
        }

        public Criteria andTupianNotBetween(String value1, String value2) {
            addCriterion("tuPian not between", value1, value2, "tupian");
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