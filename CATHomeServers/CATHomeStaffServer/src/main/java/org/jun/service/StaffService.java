package org.jun.service;

import entity.Result;
import pojo.goods.CatGoodType;
import pojo.goods.CatType;
import pojo.staff.Staffs;

import java.util.List;

public interface StaffService {
    Result<List<CatType>> findAllCatType();

    Result<List<CatGoodType> >findAllCatGoodsType();

    Result<Staffs> staffLogin(Staffs staffs);
}
