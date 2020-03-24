package com.blog.service.impl;

import com.blog.dao.TypeDao;
import com.blog.pojo.Tag;
import com.blog.pojo.Type;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    //事务注解
    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }


    @Override
    public List<Type> getBlogType() {
        return typeDao.getBlogType();
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}
