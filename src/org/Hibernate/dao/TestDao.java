package org.Hibernate.dao;

import java.awt.Insets;
import java.util.List;

import org.Hibernate.bean.Dept;
import org.Hibernate.bean.Emp;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class TestDao {
	private SessionFactory sf = null;
	@Before
	public void fun1(){
		/*手动加载器配置文件*/
		ClassPathXmlApplicationContext ac = 
				new ClassPathXmlApplicationContext("org/Hibernate/xml/Spring.xml");
		/*通过类获得sessionFactory*/
		  sf = ac.getBean(SessionFactory.class);
	}
	/**
	 *添加 
	 */
	@Test
	public void fun2(){
		/*创建session*/
		Session session = sf.openSession();
		/*创建事务*/
		Transaction bt = session.beginTransaction();
		/*创建对象*/
		Emp emp = new Emp();
		Dept dept = new Dept();
		/*分别赋值*/
		emp.setEname("试试");
		emp.setDept(dept);
		dept.setDname("试试部");
		dept.setEmp(emp);
		/*存入*/
		session.save(emp);
		/*事务提交*/
		bt.commit();				
	}
	
	/**
	 * 普通查询
	 */
	@Test
	public void fun3(){
		Session session = sf.openSession();
		Transaction bt = session.beginTransaction();
		/*查询编号为1的员工信息*/
		Emp emp = session.get(Emp.class, 1);
		System.out.println(emp);
		bt.commit();
	}
	
	/**
	 *hql查询 
	 */
	@Test	
	public void fun4(){		
		Session session = sf.openSession();
		Transaction bt = session.beginTransaction();
		Query ql = session.createQuery("from Emp");
		/*将查询到所有emp表中对象存入Hibernate框架list中*/
		List <Emp>list = ql.list();
		/*加强for循环输出所有emp表中对象*/
		for (Emp emp : list) {
			System.out.println(emp);
		}
		bt.commit(); 
	}
	
	/**
	 * QBC查询
	 */
	@Test
	public void  fun5(){
		Session session = sf.openSession();
		Transaction bt = session.beginTransaction();
		Criteria criteria = session.createCriteria(Emp.class);
		List <Emp>list = criteria.list();
		for (Emp emp : list) {
			System.out.println(emp);
		}
		bt.commit();
	}
	
	

}
