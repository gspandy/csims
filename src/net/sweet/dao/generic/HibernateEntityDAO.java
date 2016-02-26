package net.sweet.dao.generic;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import net.sweet.dao.generic.utils.GenericsUtils;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * 负责为单个Entity对象提供CRUD操作的Hibernate DAO基类.
 * <p/>
 * 子类只要在类定义时指定所管理Entity的Class, 即拥有对单个Entity对象的CRUD操作.
 * 
 * <pre>
 * public class UserManager extends HibernateEntityDao&lt;User&gt; {
 * }
 * </pre>
 * 
 * @author Sweet
 * @see HibernateGenericDAO
 */
@SuppressWarnings("unchecked")
public class HibernateEntityDAO<T> extends HibernateGenericDAO implements EntityDAO<T> {

    protected Class<T> entityClass;// DAO所管理的Entity类型.

    /**
     * 在构造函数中将泛型T.class赋给entityClass.
     */
    public HibernateEntityDAO() {
        entityClass = GenericsUtils.getSuperClassGenricType(getClass());
    }

    /**
     * 取得entityClass.JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
     */
    protected Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * 根据ID获取对象.
     * 
     * @see HibernateGenericDAO#getId(Class,Object)
     */
    public T get(Serializable id) {
        return get(getEntityClass(), id);
    }

    /**
     * 获取全部对象
     * 
     * @see HibernateGenericDAO#getAll(Class)
     */
    public List<T> getAll() {
        return getAll(getEntityClass());
    }

    /**
     * 获取全部对象,带排序参数.
     * 
     * @see HibernateGenericDAO#getAll(Class,String,boolean)
     */
    public List<T> getAll(String orderBy, boolean isAsc) {
        return getAll(getEntityClass(), orderBy, isAsc);
    }

    /**
     * 根据ID移除对象.
     * 
     * @see HibernateGenericDAO#removeById(Class,Serializable)
     */
    public void removeById(Serializable id) {
        removeById(getEntityClass(), id);
    }

    public void evict(final Object o) {
        this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                session.evict(o);
                return null;
            }
        });
    }

}
