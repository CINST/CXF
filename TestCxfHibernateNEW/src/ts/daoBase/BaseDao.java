package ts.daoBase;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ts.daoBase.IBaseDao;

/**
 * 提供hibernate dao的所有操作 * ，泛型类，实例化该类时需要为类指定类型
 * 泛型介绍：https://blog.csdn.net/s10461/article/details/53941091
 */
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class BaseDao<T,PK extends Serializable> extends HibernateDaoSupport implements IBaseDao<T,PK> {
	protected Class<T> entityClass;			// DAO所管理的Entity类型.
    
	/**
     *让spring提供构造函数注入
     */
    public BaseDao(Class<T> type) {
        this.entityClass = type;
    }
    
    public BaseDao(){
    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    
	public void removeByUId(PK uid) {
		remove(get1(uid));
	}
	
	
	public T get1(PK uid) {    //从数据库中搜索uid得到一个T类
		return getHibernateTemplate().load(getEntityClass(), uid);
	}

	
	@Override
	public T get(PK id) {    //从数据库中搜索id得到一个T类
		return getHibernateTemplate().load(getEntityClass(), id);
	}
	
	@Override
	public List<T> getAll() {
		return (List<T>)(getHibernateTemplate().loadAll(getEntityClass()));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll(String orderBy, boolean isAsc) {
		Assert.hasText(orderBy);
        if (isAsc)
            return (List<T>) getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(getEntityClass()).addOrder(Order.asc(orderBy)));
        else
            return (List<T>) getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(getEntityClass()).addOrder(Order.desc(orderBy)));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findBy(String orderBy, boolean isAsc, Criterion... criterions) {
    	DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClass());
        for (Criterion c : criterions) {
        	System.out.println(c);
        	/*criteria可以使用add()方法添加我们需要的查询条件，就好比SQL语句中的where条件语句*/
            criteria.add(c);
        }
        if (isAsc) {
        	System.out.println("-------------f");
        	criteria.addOrder(Order.asc(orderBy));
        }
        else
        	criteria.addOrder(Order.desc(orderBy));

        System.out.println("sxcghj**"+criteria);
        List<T> list= (List<T>) getHibernateTemplate().findByCriteria(criteria);
        System.out.println("sdfdgh");
        System.out.println(list);
        return list;
	}
	/* DetachedCriteria 提供了 2 个静态方法 forClass(Class) 或 forEntityName(Name)
     * 进行DetachedCriteria 实例的创建
     * Spring 的框架提供了getHibernateTemplate().findByCriteria(detachedCriteria) 
     * 方法可以很方便地根据DetachedCriteria 来返回查询结 果。  */

	@Override
	public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc) {
		Assert.hasText(propertyName);  //确保propertyName不能为空，否则返回异常
        Assert.hasText(orderBy);      //确保orderBy不能为空，否则返回异常
        return findBy(orderBy, isAsc, Restrictions.eq(propertyName, value));
        //return createCriteria(orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
	}
	
	
	@Override
	public List<T> findByTwo(String propertyName1, Object value1,String propertyName2, Object value2, String orderBy, boolean isAsc) {
		Assert.hasText(propertyName1);  //确保propertyName不能为空，否则返回异常
		Assert.hasText(propertyName2);  //确保propertyName不能为空，否则返回异常
        Assert.hasText(orderBy);      //确保orderBy不能为空，否则返回异常
        return findBy(orderBy, isAsc, Restrictions.eq(propertyName1, value1),Restrictions.eq(propertyName2, value2));
        //return createCriteria(orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
	}
	
	@Override
	public List<T> findLike(String propertyName, Object value, String orderBy, boolean isAsc) {
        Assert.hasText(propertyName);
        Assert.hasText(orderBy);
        return findBy(orderBy, isAsc, Restrictions.like(propertyName, value));
        //return createCriteria(orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
	}
	
	//保存或更新某一实体信息
	@Override
	public void save(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}
	
	@Override
	public void remove(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
	@Override
	public void removeById(PK id) {
		remove(get(id));
	}
	
	@Override
	public void evit(T entity) {
		getHibernateTemplate().evict(entity);
	}
	
	@Override
	public void flush() {
		getHibernateTemplate().flush();
	}
	
	@Override
	public void clear() {
		getHibernateTemplate().clear();
	}
}