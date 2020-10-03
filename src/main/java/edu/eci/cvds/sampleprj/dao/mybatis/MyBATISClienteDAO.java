package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import java.util.*;


public class MyBATISClienteDAO implements ClienteDAO {

    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public Cliente load(long id) throws PersistenceException {
        Cliente c = clienteMapper.consultarCliente(id);
        if (c == null) {
            throw new PersistenceException("Error al consultar cliente " + Integer.toString((int) id));
        } else {
            return c;
        }
    }

    @Override
    public void addItemRentado(int idC, int idit, Date fechainicio, Date fechafin) throws PersistenceException {
        try {
            clienteMapper.agregarItemRentadoACliente(idC, idit, fechainicio, fechafin);;
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al agregar el Item Rentado", e);
        }
    }

    @Override
    public List<Cliente> loadAll() throws PersistenceException {
        try {
            return clienteMapper.consultarClientes();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar clientes", e);
        }
    }

    @Override
    public void setVetado(long docu, boolean estado) throws PersistenceException {
        try {
            clienteMapper.actualizarClienteVetado(docu, estado);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("No fue actualizar el estado vetado");
        }
    }

    @Override
    public void addCliente(Cliente c) throws PersistenceException {
        try {
            clienteMapper.insertarCliente(c);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("No fue posible agregar al cliente");
        }
    }
}