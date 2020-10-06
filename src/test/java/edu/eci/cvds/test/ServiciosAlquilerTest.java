package edu.eci.cvds.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

    ServiciosAlquiler serviciosAlquiler;
    
    private long documento = 1;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

   
    @Test
	public void noItemFound() {
		boolean r = false;
		try {
			Item item = serviciosAlquiler.consultarItem(8);
		} catch(ExcepcionServiciosAlquiler e) {
			r = true;
		} catch(IndexOutOfBoundsException e) {
			r = true;
		}
		// Validate no Client was found;
		Assert.assertTrue(r);
	}
    @Test
    public void consultarCostoAlquiler() throws ParseException{
        try{
            serviciosAlquiler.registrarItem(new Item(new TipoItem(1, "electrodomestico" ),99,
                             "item99", "item99", new SimpleDateFormat("yyyy/MM/dd").parse("2019/09/28"),
                                   99,"Digital","99"));
            System.out.println("SI ENTRAAAAAAAAAAAAAAAAA");
            System.out.println(serviciosAlquiler.consultarCostoAlquiler(99,2));
            
        }
        catch (ExcepcionServiciosAlquiler e ){
            Assert.assertEquals("Error al calcular tarifa",e.getMessage());
        }

    }




//Apoyo para las pruebas.
    public Item generarItem(){        
        TipoItem tipoDeItemAgregado = new TipoItem(15,"Tipo de Item volador");
        Date fecha = convertDate("2012-02-22");
        Item itemAgregado = new Item(tipoDeItemAgregado, 115, "Cloud Treasure", "Tesoro en las alturas vuela a velocidades impresionantes", fecha, 20000, "Renta Express", "Carreras");
        return itemAgregado;
    }
    
    public static Date convertDate(String fecha) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (ParseException e) {
            return null;
        }
    }
}