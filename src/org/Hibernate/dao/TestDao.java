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
		/*�ֶ������������ļ�*/
		ClassPathXmlApplicationContext ac = 
				new ClassPathXmlApplicationContext("org/Hibernate/xml/Spring.xml");
		/*ͨ������sessionFactory*/
		  sf = ac.getBean(SessionFactory.class);
	}
	/**
	 *��� 
	 */
	@Test
	public void fun2(){
		/*����session*/
		Session session = sf.openSession();
		/*��������*/
		Transaction bt = session.beginTransaction();
		/*��������*/
		Emp emp = new Emp();
		Dept dept = new Dept();
		/*�ֱ�ֵ*/
		emp.setEname("����");
		emp.setDept(dept);
		dept.setDname("���Բ�");
		dept.setEmp(emp);
		/*����*/
		session.save(emp);
		/*�����ύ*/
		bt.commit();				
	}
	
	/**
	 * ��ͨ��ѯ
	 */
	@Test
	public void fun3(){
		Session session = sf.openSession();
		Transaction bt = session.beginTransaction();
		/*��ѯ���Ϊ1��Ա����Ϣ*/
		Emp emp = session.get(Emp.class, 1);
		System.out.println(emp);
		bt.commit();
	}
	
	/**
	 *hql��ѯ 
	 */
	@Test	
	public void fun4(){		
		Session session = sf.openSession();
		Transaction bt = session.beginTransaction();
		Query ql = session.createQuery("from Emp");
		/*����ѯ������emp���ж������Hibernate���list��*/
		List <Emp>list = ql.list();
		/*��ǿforѭ���������emp���ж���*/
		for (Emp emp : list) {
			System.out.println(emp);
		}
		bt.commit(); 
	}
	
	/**
	 * QBC��ѯ
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
