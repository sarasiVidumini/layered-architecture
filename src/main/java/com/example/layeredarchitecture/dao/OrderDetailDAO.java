package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface OrderDetailDAO {
    public boolean saveOrderDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;
}
