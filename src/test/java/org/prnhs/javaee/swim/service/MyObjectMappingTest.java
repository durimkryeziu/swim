package org.prnhs.javaee.swim.service;

import java.util.ArrayList;
import org.dozer.Mapper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prnhs.javaee.swim.core.entity.User;
import org.prnhs.javaee.swim.dto.MyObjectA;
import org.prnhs.javaee.swim.dto.MyObjectB;
import org.prnhs.javaee.swim.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyObjectMappingTest {

    @Autowired
    private Mapper mapper;

    @Test
    public void test_MappingIntegerToString() {
        assertNotNull(mapper);

        MyObjectA a = new MyObjectA();
        a.setMyIntv(1);
        a.setMyKey(123);

        MyObjectB b = mapper.map(a, MyObjectB.class);

        assertNotNull(b);
        assertEquals("1", b.getMyIntv());
        assertEquals(Integer.valueOf(123), b.getMyId());

    }

    @Test
    public void test_oneWayAToB() {
        MyObjectA a = new MyObjectA();
        a.setPassword("myPass");
        MyObjectB b = mapper.map(a, MyObjectB.class);
        assertNotNull(b);
        assertEquals("myPass", b.getPassword());
    }

    @Test
    public void test_oneWayBToA() {
        MyObjectB b = new MyObjectB();
        b.setPassword("myPass");
        MyObjectA a = mapper.map(b, MyObjectA.class);
        assertNotNull(a);
        assertNull(a.getPassword());
    }

    @Test
    public void test_list() {
        MyObjectA a = new MyObjectA();
        a.setMyList(new ArrayList<>());
        a.getMyList().add("hello");
        a.getMyList().add("world");
        a.getMyList().add("is");
        a.getMyList().add("it");
        a.getMyList().add("me");
        a.getMyList().add("you");
        a.getMyList().add("are");
        a.getMyList().add("looking");
        a.getMyList().add("for");

        MyObjectB b = mapper.map(a, MyObjectB.class);
        assertNotNull(b);
        assertFalse(b.getMyList().isEmpty());
        assertEquals(9, b.getMyList().size());
    }    
    
    @Test
    public void test_listUser() {
        MyObjectA a = new MyObjectA();
        a.setMyUsers(new ArrayList<>());
        UserDto dto = new UserDto();
        dto.setUsername("bla");
        a.getMyUsers().add(dto);

        MyObjectB b = mapper.map(a, MyObjectB.class);
        assertNotNull(b);
        assertFalse(b.getMyUsers().isEmpty());
        assertEquals(1, b.getMyUsers().size());
    }

    @Test
    public void test_existingAToB() {
        MyObjectA a = new MyObjectA();
        a.setMyIntv(1);
        a.setMyKey(123);
        MyObjectB b = new MyObjectB();

        mapper.map(a, b);

        assertEquals(String.valueOf(a.getMyIntv()), b.getMyIntv());
        assertEquals(a.getMyKey(), b.getMyId());
    }

    @Test
    public void test_existingBToA() {
        MyObjectA a = new MyObjectA();
        a.setMyIntv(1);
        a.setMyKey(123);
        a.setPassword("new OLD pass");

        MyObjectB b = new MyObjectB();
        b.setMyIntv("2");
        b.setMyId(124);
        b.setPassword("new pass");

        mapper.map(b, a);

        assertEquals(Integer.valueOf(b.getMyIntv()), a.getMyIntv());
        assertEquals(a.getMyKey(), b.getMyId());
        assertEquals("new OLD pass", a.getPassword());
    }

    @Test
    public void test_existingList() {
        MyObjectA a = new MyObjectA();
        a.setMyList(new ArrayList<>());
        a.getMyList().add("hello");
        a.getMyList().add("world");
        a.getMyList().add("is");
        a.getMyList().add("it");
        a.getMyList().add("me");
        a.getMyList().add("you");
        a.getMyList().add("are");
        a.getMyList().add("looking");
        a.getMyList().add("for");
        MyObjectB b = new MyObjectB();
        b.setMyList(new ArrayList<>());
        b.getMyList().add("helloz");
        b.getMyList().add("worldz");
        b.getMyList().add("isz");

        mapper.map(a, b);
        assertNotNull(b);
        assertFalse(b.getMyList().isEmpty());
        for (String i : b.getMyList()) {
            System.err.println(i);
        }
        assertEquals(9, b.getMyList().size());
    }
    
    

    @Test
    public void test_existingListUser() {
        MyObjectA a = new MyObjectA();
        a.setMyUsers(new ArrayList<>());
        UserDto dto = new UserDto();
        dto.setUsername("bla");
        dto.setPassword("my new pass");
        a.getMyUsers().add(dto);
        MyObjectB b = new MyObjectB();
        b.setMyUsers(new ArrayList<>());
        User u = new User();
        u.setUsername("bla");
        b.getMyUsers().add(u);

        mapper.map(a, b);
        assertNotNull(b);
        assertFalse(b.getMyUsers().isEmpty());
        for (User i : b.getMyUsers()) {
            System.err.println(i.toString());
        }
        assertEquals(2, b.getMyUsers().size());
    }
}
