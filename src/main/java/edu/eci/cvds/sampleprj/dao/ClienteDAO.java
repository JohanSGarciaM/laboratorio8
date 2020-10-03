package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;
import java.util.*;
import org.mybatis.guice.transactional.Transactional;

public interface ClienteDAO {

    public Cliente load(long id) throws PersistenceException;

    @Transactional
    public void addItemRentado(int idC,int idit, Date fechainicio,Date fechafin) throws PersistenceException;

    public List<Cliente> loadAll() throws PersistenceException;


    @Transactional
    public void setVetado(long docu, boolean estado) throws PersistenceException;

    public void addCliente(Cliente c) throws PersistenceException;

}