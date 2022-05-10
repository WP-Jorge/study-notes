package bean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class jiaolianxinxiBeanExample {
    protected String orderByClause;
    protected List<Criteria> oredCriteria;
    public jiaolianxinxiBeanExample() {
        oredCriteria = new ArrayList<Criteria>();
    }
    protected jiaolianxinxiBeanExample(jiaolianxinxiBeanExample example) {
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

        public Criteria andJiaolianmingchenIsNull() {
            addCriterion("jiaolianmingchen is null");
            return this;
        }

        public Criteria andJiaolianmingchenIsNotNull() {
            addCriterion("jiaolianmingchen is not null");
            return this;
        }

        public Criteria andJiaolianmingchenEqualTo(String value) {
            addCriterion("jiaolianmingchen =", value, "jiaolianmingchen");
            return this;
        }

        public Criteria andJiaolianmingchenNotEqualTo(String value) {
            addCriterion("jiaolianmingchen <>", value, "jiaolianmingchen");
            return this;
        }

        public Criteria andJiaolianmingchenGreaterThan(String value) {
            addCriterion("jiaolianmingchen >", value, "jiaolianmingchen");
            return this;
        }

        public Criteria andJiaolianmingchenGreaterThanOrEqualTo(String value) {
            addCriterion("jiaolianmingchen >=", value, "jiaolianmingchen");
            return this;
        }

        public Criteria andJiaolianmingchenLessThan(String value) {
            addCriterion("jiaolianmingchen <", value, "jiaolianmingchen");
            return this;
        }

        public Criteria andJiaolianmingchenLessThanOrEqualTo(String value) {
            addCriterion("jiaolianmingchen <=", value, "jiaolianmingchen");
            return this;
        }

        public Criteria andJiaolianmingchenLike(String value) {
            addCriterion("jiaolianmingchen like", value, "jiaolianmingchen");
            return this;
        }

        public Criteria andJiaolianmingchenNotLike(String value) {
            addCriterion("jiaolianmingchen not like", value, "jiaolianmingchen");
            return this;
        }

        public Criteria andJiaolianmingchenIn(List<String> values) {
            addCriterion("jiaolianmingchen in", values, "jiaolianmingchen");
            return this;
        }

        public Criteria andJiaolianmingchenNotIn(List<String> values) {
            addCriterion("jiaolianmingchen not in", values, "jiaolianmingchen");
            return this;
        }

        public Criteria andJiaolianmingchenBetween(String value1, String value2) {
            addCriterion("jiaolianmingchen between", value1, value2, "jiaolianmingchen");
            return this;
        }

        public Criteria andJiaolianmingchenNotBetween(String value1, String value2) {
            addCriterion("jiaolianmingchen not between", value1, value2, "jiaolianmingchen");
            return this;
        }

        public Criteria andXingbieIsNull() {
            addCriterion("xingbie is null");
            return this;
        }

        public Criteria andXingbieIsNotNull() {
            addCriterion("xingbie is not null");
            return this;
        }

        public Criteria andXingbieEqualTo(String value) {
            addCriterion("xingbie =", value, "xingbie");
            return this;
        }

        public Criteria andXingbieNotEqualTo(String value) {
            addCriterion("xingbie <>", value, "xingbie");
            return this;
        }

        public Criteria andXingbieGreaterThan(String value) {
            addCriterion("xingbie >", value, "xingbie");
            return this;
        }

        public Criteria andXingbieGreaterThanOrEqualTo(String value) {
            addCriterion("xingbie >=", value, "xingbie");
            return this;
        }

        public Criteria andXingbieLessThan(String value) {
            addCriterion("xingbie <", value, "xingbie");
            return this;
        }

        public Criteria andXingbieLessThanOrEqualTo(String value) {
            addCriterion("xingbie <=", value, "xingbie");
            return this;
        }

        public Criteria andXingbieLike(String value) {
            addCriterion("xingbie like", value, "xingbie");
            return this;
        }

        public Criteria andXingbieNotLike(String value) {
            addCriterion("xingbie not like", value, "xingbie");
            return this;
        }

        public Criteria andXingbieIn(List<String> values) {
            addCriterion("xingbie in", values, "xingbie");
            return this;
        }

        public Criteria andXingbieNotIn(List<String> values) {
            addCriterion("xingbie not in", values, "xingbie");
            return this;
        }

        public Criteria andXingbieBetween(String value1, String value2) {
            addCriterion("xingbie between", value1, value2, "xingbie");
            return this;
        }

        public Criteria andXingbieNotBetween(String value1, String value2) {
            addCriterion("xingbie not between", value1, value2, "xingbie");
            return this;
        }

        public Criteria andChushengriqiIsNull() {
            addCriterion("chushengriqi is null");
            return this;
        }

        public Criteria andChushengriqiIsNotNull() {
            addCriterion("chushengriqi is not null");
            return this;
        }

        public Criteria andChushengriqiEqualTo(String value) {
            addCriterion("chushengriqi =", value, "chushengriqi");
            return this;
        }

        public Criteria andChushengriqiNotEqualTo(String value) {
            addCriterion("chushengriqi <>", value, "chushengriqi");
            return this;
        }

        public Criteria andChushengriqiGreaterThan(String value) {
            addCriterion("chushengriqi >", value, "chushengriqi");
            return this;
        }

        public Criteria andChushengriqiGreaterThanOrEqualTo(String value) {
            addCriterion("chushengriqi >=", value, "chushengriqi");
            return this;
        }

        public Criteria andChushengriqiLessThan(String value) {
            addCriterion("chushengriqi <", value, "chushengriqi");
            return this;
        }

        public Criteria andChushengriqiLessThanOrEqualTo(String value) {
            addCriterion("chushengriqi <=", value, "chushengriqi");
            return this;
        }

        public Criteria andChushengriqiLike(String value) {
            addCriterion("chushengriqi like", value, "chushengriqi");
            return this;
        }

        public Criteria andChushengriqiNotLike(String value) {
            addCriterion("chushengriqi not like", value, "chushengriqi");
            return this;
        }

        public Criteria andChushengriqiIn(List<String> values) {
            addCriterion("chushengriqi in", values, "chushengriqi");
            return this;
        }

        public Criteria andChushengriqiNotIn(List<String> values) {
            addCriterion("chushengriqi not in", values, "chushengriqi");
            return this;
        }

        public Criteria andChushengriqiBetween(String value1, String value2) {
            addCriterion("chushengriqi between", value1, value2, "chushengriqi");
            return this;
        }

        public Criteria andChushengriqiNotBetween(String value1, String value2) {
            addCriterion("chushengriqi not between", value1, value2, "chushengriqi");
            return this;
        }

        public Criteria andRuxingshijianIsNull() {
            addCriterion("ruxingshijian is null");
            return this;
        }

        public Criteria andRuxingshijianIsNotNull() {
            addCriterion("ruxingshijian is not null");
            return this;
        }

        public Criteria andRuxingshijianEqualTo(String value) {
            addCriterion("ruxingshijian =", value, "ruxingshijian");
            return this;
        }

        public Criteria andRuxingshijianNotEqualTo(String value) {
            addCriterion("ruxingshijian <>", value, "ruxingshijian");
            return this;
        }

        public Criteria andRuxingshijianGreaterThan(String value) {
            addCriterion("ruxingshijian >", value, "ruxingshijian");
            return this;
        }

        public Criteria andRuxingshijianGreaterThanOrEqualTo(String value) {
            addCriterion("ruxingshijian >=", value, "ruxingshijian");
            return this;
        }

        public Criteria andRuxingshijianLessThan(String value) {
            addCriterion("ruxingshijian <", value, "ruxingshijian");
            return this;
        }

        public Criteria andRuxingshijianLessThanOrEqualTo(String value) {
            addCriterion("ruxingshijian <=", value, "ruxingshijian");
            return this;
        }

        public Criteria andRuxingshijianLike(String value) {
            addCriterion("ruxingshijian like", value, "ruxingshijian");
            return this;
        }

        public Criteria andRuxingshijianNotLike(String value) {
            addCriterion("ruxingshijian not like", value, "ruxingshijian");
            return this;
        }

        public Criteria andRuxingshijianIn(List<String> values) {
            addCriterion("ruxingshijian in", values, "ruxingshijian");
            return this;
        }

        public Criteria andRuxingshijianNotIn(List<String> values) {
            addCriterion("ruxingshijian not in", values, "ruxingshijian");
            return this;
        }

        public Criteria andRuxingshijianBetween(String value1, String value2) {
            addCriterion("ruxingshijian between", value1, value2, "ruxingshijian");
            return this;
        }

        public Criteria andRuxingshijianNotBetween(String value1, String value2) {
            addCriterion("ruxingshijian not between", value1, value2, "ruxingshijian");
            return this;
        }

        public Criteria andCongyejingliIsNull() {
            addCriterion("congyejingli is null");
            return this;
        }

        public Criteria andCongyejingliIsNotNull() {
            addCriterion("congyejingli is not null");
            return this;
        }

        public Criteria andCongyejingliEqualTo(String value) {
            addCriterion("congyejingli =", value, "congyejingli");
            return this;
        }

        public Criteria andCongyejingliNotEqualTo(String value) {
            addCriterion("congyejingli <>", value, "congyejingli");
            return this;
        }

        public Criteria andCongyejingliGreaterThan(String value) {
            addCriterion("congyejingli >", value, "congyejingli");
            return this;
        }

        public Criteria andCongyejingliGreaterThanOrEqualTo(String value) {
            addCriterion("congyejingli >=", value, "congyejingli");
            return this;
        }

        public Criteria andCongyejingliLessThan(String value) {
            addCriterion("congyejingli <", value, "congyejingli");
            return this;
        }

        public Criteria andCongyejingliLessThanOrEqualTo(String value) {
            addCriterion("congyejingli <=", value, "congyejingli");
            return this;
        }

        public Criteria andCongyejingliLike(String value) {
            addCriterion("congyejingli like", value, "congyejingli");
            return this;
        }

        public Criteria andCongyejingliNotLike(String value) {
            addCriterion("congyejingli not like", value, "congyejingli");
            return this;
        }

        public Criteria andCongyejingliIn(List<String> values) {
            addCriterion("congyejingli in", values, "congyejingli");
            return this;
        }

        public Criteria andCongyejingliNotIn(List<String> values) {
            addCriterion("congyejingli not in", values, "congyejingli");
            return this;
        }

        public Criteria andCongyejingliBetween(String value1, String value2) {
            addCriterion("congyejingli between", value1, value2, "congyejingli");
            return this;
        }

        public Criteria andCongyejingliNotBetween(String value1, String value2) {
            addCriterion("congyejingli not between", value1, value2, "congyejingli");
            return this;
        }

        public Criteria andGerenjianjieIsNull() {
            addCriterion("gerenjianjie is null");
            return this;
        }

        public Criteria andGerenjianjieIsNotNull() {
            addCriterion("gerenjianjie is not null");
            return this;
        }

        public Criteria andGerenjianjieEqualTo(String value) {
            addCriterion("gerenjianjie =", value, "gerenjianjie");
            return this;
        }

        public Criteria andGerenjianjieNotEqualTo(String value) {
            addCriterion("gerenjianjie <>", value, "gerenjianjie");
            return this;
        }

        public Criteria andGerenjianjieGreaterThan(String value) {
            addCriterion("gerenjianjie >", value, "gerenjianjie");
            return this;
        }

        public Criteria andGerenjianjieGreaterThanOrEqualTo(String value) {
            addCriterion("gerenjianjie >=", value, "gerenjianjie");
            return this;
        }

        public Criteria andGerenjianjieLessThan(String value) {
            addCriterion("gerenjianjie <", value, "gerenjianjie");
            return this;
        }

        public Criteria andGerenjianjieLessThanOrEqualTo(String value) {
            addCriterion("gerenjianjie <=", value, "gerenjianjie");
            return this;
        }

        public Criteria andGerenjianjieLike(String value) {
            addCriterion("gerenjianjie like", value, "gerenjianjie");
            return this;
        }

        public Criteria andGerenjianjieNotLike(String value) {
            addCriterion("gerenjianjie not like", value, "gerenjianjie");
            return this;
        }

        public Criteria andGerenjianjieIn(List<String> values) {
            addCriterion("gerenjianjie in", values, "gerenjianjie");
            return this;
        }

        public Criteria andGerenjianjieNotIn(List<String> values) {
            addCriterion("gerenjianjie not in", values, "gerenjianjie");
            return this;
        }

        public Criteria andGerenjianjieBetween(String value1, String value2) {
            addCriterion("gerenjianjie between", value1, value2, "gerenjianjie");
            return this;
        }

        public Criteria andGerenjianjieNotBetween(String value1, String value2) {
            addCriterion("gerenjianjie not between", value1, value2, "gerenjianjie");
            return this;
        }

        public Criteria andLianxidianhuaIsNull() {
            addCriterion("lianxidianhua is null");
            return this;
        }

        public Criteria andLianxidianhuaIsNotNull() {
            addCriterion("lianxidianhua is not null");
            return this;
        }

        public Criteria andLianxidianhuaEqualTo(String value) {
            addCriterion("lianxidianhua =", value, "lianxidianhua");
            return this;
        }

        public Criteria andLianxidianhuaNotEqualTo(String value) {
            addCriterion("lianxidianhua <>", value, "lianxidianhua");
            return this;
        }

        public Criteria andLianxidianhuaGreaterThan(String value) {
            addCriterion("lianxidianhua >", value, "lianxidianhua");
            return this;
        }

        public Criteria andLianxidianhuaGreaterThanOrEqualTo(String value) {
            addCriterion("lianxidianhua >=", value, "lianxidianhua");
            return this;
        }

        public Criteria andLianxidianhuaLessThan(String value) {
            addCriterion("lianxidianhua <", value, "lianxidianhua");
            return this;
        }

        public Criteria andLianxidianhuaLessThanOrEqualTo(String value) {
            addCriterion("lianxidianhua <=", value, "lianxidianhua");
            return this;
        }

        public Criteria andLianxidianhuaLike(String value) {
            addCriterion("lianxidianhua like", value, "lianxidianhua");
            return this;
        }

        public Criteria andLianxidianhuaNotLike(String value) {
            addCriterion("lianxidianhua not like", value, "lianxidianhua");
            return this;
        }

        public Criteria andLianxidianhuaIn(List<String> values) {
            addCriterion("lianxidianhua in", values, "lianxidianhua");
            return this;
        }

        public Criteria andLianxidianhuaNotIn(List<String> values) {
            addCriterion("lianxidianhua not in", values, "lianxidianhua");
            return this;
        }

        public Criteria andLianxidianhuaBetween(String value1, String value2) {
            addCriterion("lianxidianhua between", value1, value2, "lianxidianhua");
            return this;
        }

        public Criteria andLianxidianhuaNotBetween(String value1, String value2) {
            addCriterion("lianxidianhua not between", value1, value2, "lianxidianhua");
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