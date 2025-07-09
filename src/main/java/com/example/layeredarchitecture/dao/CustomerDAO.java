package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface CustomerDAO {

    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    public void saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public void updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    public boolean getExitCustomer(String id) throws SQLException, ClassNotFoundException;

    public String getGenerateNewId() throws SQLException, ClassNotFoundException;

    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException;

    public String getLastCustomerId(TableView<CustomerTM> tblCustomers);
}
