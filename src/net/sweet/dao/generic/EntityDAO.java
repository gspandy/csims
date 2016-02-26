package net.sweet.dao.generic;

import java.io.Serializable;
import java.util.List;

import net.sweet.dao.generic.support.Page;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

/**
 * 针对单个Entity对象的操作定义.不依赖于具体ORM实现方案.
 * 
 * @author Sweet
 */
@SuppressWarnings("unchecked")
public interface EntityDAO<T> {

    /**
     * 根据ID获取对象.
     * 
     * @see HibernateGenericDAO#getId(Class,Object)
     */
    public T get(Serializable id);

    /**
     * 获取全部对象
     * 
     * @see HibernateGenericDAO#getAll(Class)
     */
    public List<T> getAll();

    /**
     * 获取全部对象,带排序参数.
     * 
     * @see HibernateGenericDAO#getAll(Class,String,boolean)
     */
    public List<T> getAll(String orderBy, boolean isAsc);

    /**
     * 保存对象.
     */
    public void save(Object o);

    /**
     * 删除对象.
     */
    public void remove(Object o);

    /**
     * 根据ID删除对象.
     */
    public void removeById(Serializable id);

    /**
     * 根据hql查询,直接使用HibernateTemplate的find函数.
     * 
     * @param values
     *            可变参数,见{@link #createQuery(String,Object...)}
     */
    public List find(String hql, Object... values);

    /**
     * 分页查询函数，使用hql.
     * 
     * @param pageNo
     *            页号,从1开始.
     */
    public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values);

    /**
     * 删除对象，并独立提交事务，无论是否为嵌套事务
     * 
     * @param o
     */

    public void evict(Object o);
}
