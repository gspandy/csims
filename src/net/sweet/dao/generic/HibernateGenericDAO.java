package net.sweet.dao.generic;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sweet.dao.generic.support.Page;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

/**
 * Hibernate Dao的泛型基类.
 * <p/>
 * 继承于Spring的<code>HibernateDaoSupport</code>,提供分页函数和若干便捷查询方法，并对返回值作了泛型类型转换.
 * 
 * @author Sweet
 * @see HibernateDaoSupport
 * @see HibernateEntityDAO
 */
@SuppressWarnings("unchecked")
public class HibernateGenericDAO extends HibernateDaoSupport {
    /**
     * 根据ID获取对象. 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常.
     */
    public <T> T get(Class<T> entityClass, Serializable id) {
        return (T) getHibernateTemplate().load(entityClass, id);
    }

    /**
     * 获取全部对象.
     */
    public <T> List<T> getAll(Class<T> entityClass) {
        return getHibernateTemplate().loadAll(entityClass);
    }

    /**
     * 获取全部对象,带排序字段与升降序参数.
     */
    public <T> List<T> getAll(Class<T> entityClass, String orderBy, boolean isAsc) {
        Assert.hasText(orderBy);
        if (isAsc)
            return getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
        else
            return getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
    }

    /**
     * 保存对象.
     */
    public void save(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
    }

    /**
     * 删除对象.
     */
    public void remove(Object o) {
        getHibernateTemplate().delete(o);
    }

    /**
     * 根据ID删除对象.
     */
    public <T> void removeById(Class<T> entityClass, Serializable id) {
        remove(get(entityClass, id));
    }

    public void flush() {
        getHibernateTemplate().flush();
    }

    public void clear() {
        getHibernateTemplate().clear();
    }

    /**
     * 根据hql查询,直接使用HibernateTemplate的find函数.
     * 
     * @param values
     *            可变参数,见{@link #}
     */
    public List find(String hql, Object... values) {
        Assert.hasText(hql);
        return getHibernateTemplate().find(hql, values);
    }

    /**
     * 分页查询函数，使用hql.
     * 
     * @param pageNo
     *            页号,从1开始.
     */
    public Page pagedQuery(final String hql, final int pageNo, final int pageSize,
            final Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        // Count查询
        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        List countlist = getHibernateTemplate().find(countQueryString, values);
        long totalCount = (Long) countlist.get(0);

        if (totalCount < 1)
            return new Page();
        // 实际查询返回分页对象
        final int startIndex = Page.getStartOfPage(pageNo, pageSize);

        List list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
            public List doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }
                query.setFirstResult(startIndex);
                query.setMaxResults(pageSize);
                return query.list();
            }
        });
        return new Page(startIndex, totalCount, pageSize, list);
    }

    /**
     * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
     * 
     * @see #pagedQuery(String,int,int,Object[])
     */
    private static String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    /**
     * 去除hql的orderby 子句，用于pagedQuery.
     * 
     * @see #pagedQuery(String,int,int,Object[])
     */
    private static String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
}