package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

    @Inject
    private ItemDAO itemDAO;

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private TipoItemDAO tipoItemDAO;

    @Inject
    private ItemRentadoDAO itemRentadoDAO;

    private int multaGeneral;
    private int limiteDias;


    public ServiciosAlquilerImpl() {
    }

    @PostConstruct
    private void init() {
        this.multaGeneral=500;
        this.limiteDias=6;
    }

    @Override
    public int valorMultaRetrasoxDia(int itemId) {
        return itemRentadoDAO.consultarDiasAlquiler(itemId)*multaGeneral;
    }


    @Override
    public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        try {
            return clienteDAO.load(docu);
        }
        catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar al cliente "+docu,ex);
        }
    }

    @Override
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        try {
            return itemRentadoDAO.loadItemsCliente(idcliente);
        }
        catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("El cliente "+idcliente+" no está registrado");
        }
    }

    @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try {
            return clienteDAO.loadAll();
        }
        catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar items");
        }
    }


    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.load(id);
        }
        catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
        }
    }

    @Override
    public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
        try {
            return itemDAO.loadAvailableItems();
        }
        catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los items disponibles",e);
        }
    }

    @Override
    public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.loadTipoItem(id);
        }
        catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el tipo de item",e);
        }
    }

    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        try {
            return tipoItemDAO.loadAll();
        }
        catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el tipo de item",e);
        }
    }


    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
        try {
            clienteDAO.addCliente(c);
        }
        catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al registar al cliente",e);
        }
    }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.consultarCostoAlquiler(iditem, numdias);
        }
        catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al calcular la tarifa",e);
        }
    }

    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
        try {
            itemDAO.actualizarTarifa(id, tarifa);
        }
        catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al actualizar la tarifa",e);
        }
    }
    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        try {
            itemDAO.addItem(i);
        }
        catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al registar el Item",e);
        }
    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
        try {
            clienteDAO.setVetado(docu,estado);
        }
        catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al registar el Item",e);
        }
    }
}