package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;
import java.sql.SQLException;
import java.util.List;

public class MyBATISItemDAO implements ItemDAO {

    @Inject
    private ItemMapper itemMapper;

    @Override
    public void save(Item it) throws PersistenceException {
        try {
            itemMapper.insertarItem(it);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar el item " + it.toString(), e);
        }

    }

    @Override
    public Item load(int id) throws PersistenceException {
        try {
            return itemMapper.consultarItem(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item " + id, e);
        }

    }

    @Override
    public List<Item> loadAll() throws PersistenceException {
        try {
            return itemMapper.consultarItems();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los items");
        }
    }

    @Override
    public List<Item> loadAvailableItems() throws PersistenceException {
        try {
            return itemMapper.consultarItemsDisponibles();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los items disponibles");
        }
    }

    @Override
    public TipoItem loadTipoItem(int id) throws PersistenceException {
        try {
            return itemMapper.consultarTipoItem(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el tipo de item");
        }
    }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws PersistenceException {
        try {
            return itemMapper.consultarItem(iditem).getTarifaxDia() * numdias;
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("No fue posible consultar el costo de alquiler");
        }
    }

    @Override
    public void actualizarTarifa(int id, long tarifa) throws PersistenceException {
        try {
            itemMapper.actualizarTarifa(id, tarifa);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("No fue posible actualizar la tarifa");
        }
    }

    @Override
    public void addItem(Item i) throws PersistenceException {
        try {
            itemMapper.insertarItem(i);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("No fue posible insertar el Item");
        }
    }

}